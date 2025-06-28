package com.sz.admin.cms.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.sz.admin.cms.mapper.CmsCategoryMapper;
import com.sz.admin.cms.mapper.CmsContentMapper;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryCreateDTO;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryListDTO;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryUpdateDTO;
import com.sz.admin.cms.pojo.po.CmsCategory;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryTreeVO;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryVO;
import com.sz.admin.cms.service.CmsCategoryClosureService;
import com.sz.admin.cms.service.CmsCategoryService;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.core.common.exception.common.BusinessException;
import com.sz.core.util.BeanCopyUtils;
import com.sz.core.util.PageUtils;
import com.sz.core.util.TreeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// MyBatis Flex表定义将在编译时自动生成
// import static com.sz.admin.cms.pojo.po.table.CmsCategoryTableDef.CMS_CATEGORY;

/**
 * <p>
 * CMS内容分类表 Service实现类
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Service
@RequiredArgsConstructor
public class CmsCategoryServiceImpl extends ServiceImpl<CmsCategoryMapper, CmsCategory> implements CmsCategoryService {

    private final CmsCategoryClosureService categoryClosureService;
    private final CmsContentMapper cmsContentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CmsCategoryCreateDTO dto) {
        // 检查分类名称是否重复
        checkCategoryNameUnique(dto.getName(), dto.getPid(), null);
        
        // 创建分类实体
        CmsCategory category = BeanCopyUtils.copy(dto, CmsCategory.class);
        
        // 设置层级深度
        if (dto.getPid() == 0) {
            category.setDeep(1);
        } else {
            CmsCategory parent = getById(dto.getPid());
            if (parent == null) {
                throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "父级分类不存在");
            }
            category.setDeep(parent.getDeep() + 1);
        }
        
        // 设置默认值
        if (!StringUtils.hasText(category.getStatus())) {
            category.setStatus("enable");
        }
        if (category.getSort() == null) {
            category.setSort(0);
        }
        category.setHasChildren("F");
        category.setDelFlag("F");
        
        // 保存分类
        save(category);
        
        // 插入闭包表关系
        if (dto.getPid() != 0) {
            categoryClosureService.insertClosure(category.getId(), dto.getPid());
            // 更新父级分类的hasChildren字段
            mapper.updateHasChildren(dto.getPid(), "T");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CmsCategoryUpdateDTO dto) {
        CmsCategory existing = getById(dto.getId());
        if (existing == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "分类不存在");
        }
        
        // 检查分类名称是否重复
        if (StringUtils.hasText(dto.getName())) {
            checkCategoryNameUnique(dto.getName(), existing.getPid(), dto.getId());
        }
        
        // 更新分类信息
        CmsCategory category = BeanCopyUtils.copy(dto, CmsCategory.class);
        updateById(category);
    }

    @Override
    public PageResult<CmsCategoryVO> page(CmsCategoryListDTO dto) {
        Page<CmsCategoryVO> page = pageAs(PageUtils.getPage(dto), buildQueryWrapper(dto), CmsCategoryVO.class);
        return PageUtils.getPageResult(page);
    }
    
    private QueryWrapper buildQueryWrapper(CmsCategoryListDTO dto) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(CmsCategory::getDelFlag).eq("F");
        
        if (StringUtils.hasText(dto.getName())) {
            queryWrapper.and(CmsCategory::getName).like(dto.getName());
        }
        if (StringUtils.hasText(dto.getStatus())) {
            queryWrapper.and(CmsCategory::getStatus).eq(dto.getStatus());
        }
        if (dto.getPid() != null) {
            queryWrapper.and(CmsCategory::getPid).eq(dto.getPid());
        }
        queryWrapper.orderBy(CmsCategory::getSort, true).orderBy(CmsCategory::getId, true);
        
        return queryWrapper;
    }

    @Override
    public List<CmsCategoryTreeVO> tree() {
        List<CmsCategoryTreeVO> list = mapper.selectCategoryTree();
        CmsCategoryTreeVO root = TreeUtils.getRoot(CmsCategoryTreeVO.class);
        List<CmsCategoryTreeVO> trees = TreeUtils.buildTree(list, root);
        return trees.isEmpty() ? new ArrayList<>() : trees.get(0).getChildren();
    }

    @Override
    public CmsCategoryVO detail(Long id) {
        CmsCategory category = getById(id);
        if (category == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "分类不存在");
        }
        return BeanCopyUtils.copy(category, CmsCategoryVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(SelectIdsDTO dto) {
        for (Object idObj : dto.getIds()) {
            Long id = Long.valueOf(idObj.toString());
            if (!canDelete(id)) {
                CmsCategory category = getById(id);
                throw new BusinessException(CommonResponseEnum.INVALID, null,
                    "分类【" + (category != null ? category.getName() : id) + "】下存在内容或子分类，无法删除");
            }
            
            // 删除分类
            removeById(id);
            
            // 删除闭包表关系
            categoryClosureService.deleteByCategoryId(id);
            
            // 更新父级分类的hasChildren字段
            CmsCategory category = getById(id);
            if (category != null && category.getPid() != 0) {
                List<CmsCategory> siblings = mapper.selectByPid(category.getPid());
                if (siblings.isEmpty()) {
                    mapper.updateHasChildren(category.getPid(), "F");
                }
            }
        }
    }

    @Override
    public boolean canDelete(Long categoryId) {
        // 检查是否有子分类
        List<CmsCategory> children = mapper.selectByPid(categoryId);
        if (!children.isEmpty()) {
            return false;
        }
        
        // 检查是否有内容
        Long contentCount = cmsContentMapper.countByCategoryId(categoryId);
        return contentCount == 0;
    }

    @Override
    public void updateStatus(Long id, String status) {
        CmsCategory category = new CmsCategory();
        category.setId(id);
        category.setStatus(status);
        updateById(category);
    }

    @Override
    public List<CmsCategoryTreeVO> options() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(CmsCategory::getDelFlag).eq("F")
                .and(CmsCategory::getStatus).eq("enable")
                .orderBy(CmsCategory::getSort, true)
                .orderBy(CmsCategory::getId, true);
        
        List<CmsCategory> list = list(queryWrapper);
        List<CmsCategoryTreeVO> treeList = BeanCopyUtils.copyList(list, CmsCategoryTreeVO.class);
        CmsCategoryTreeVO root = TreeUtils.getRoot(CmsCategoryTreeVO.class);
        List<CmsCategoryTreeVO> trees = TreeUtils.buildTree(treeList, root);
        return trees.isEmpty() ? new ArrayList<>() : trees.get(0).getChildren();
    }

    /**
     * 检查分类名称唯一性
     */
    private void checkCategoryNameUnique(String name, Long pid, Long excludeId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(CmsCategory::getName).eq(name)
                .and(CmsCategory::getPid).eq(pid)
                .and(CmsCategory::getDelFlag).eq("F");
        
        if (excludeId != null) {
            queryWrapper.and(CmsCategory::getId).ne(excludeId);
        }
        
        CmsCategory existing = getOne(queryWrapper);
        if (existing != null) {
            throw new BusinessException(CommonResponseEnum.EXISTS, null, "同级分类名称已存在");
        }
    }
}