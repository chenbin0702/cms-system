package com.sz.admin.cms.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.sz.admin.cms.mapper.CmsContentMapper;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentCreateDTO;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentListDTO;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentUpdateDTO;
import com.sz.admin.cms.pojo.po.CmsContent;
import com.sz.admin.cms.pojo.vo.cmscontent.CmsContentVO;
import com.sz.admin.cms.service.CmsCategoryService;
import com.sz.admin.cms.service.CmsContentService;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import com.sz.core.common.enums.CommonResponseEnum;
import com.sz.core.common.exception.common.BusinessException;
import com.sz.core.util.BeanCopyUtils;
import com.sz.core.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

// MyBatis Flex表定义将在编译时自动生成
// import static com.sz.admin.cms.pojo.po.table.CmsContentTableDef.CMS_CONTENT;

/**
 * <p>
 * CMS内容表 Service实现类
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Service
@RequiredArgsConstructor
public class CmsContentServiceImpl extends ServiceImpl<CmsContentMapper, CmsContent> implements CmsContentService {

    private final CmsCategoryService categoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CmsContentCreateDTO dto) {
        // 检查分类是否存在
        if (categoryService.getById(dto.getCategoryId()) == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "分类不存在");
        }
        
        // 创建内容实体
        CmsContent content = BeanCopyUtils.copy(dto, CmsContent.class);
        
        // 设置作者为当前用户
        content.setAuthorId(Long.valueOf(StpUtil.getLoginIdAsString()));
        
        // 设置默认值
        if (!StringUtils.hasText(content.getStatus())) {
            content.setStatus("draft");
        }
        if (!StringUtils.hasText(content.getContentType())) {
            content.setContentType("article");
        }
        if (content.getSort() == null) {
            content.setSort(0);
        }
        if (!StringUtils.hasText(content.getIsTop())) {
            content.setIsTop("F");
        }
        if (!StringUtils.hasText(content.getIsHot())) {
            content.setIsHot("F");
        }
        if (content.getViewCount() == null) {
            content.setViewCount(0);
        }
        content.setDelFlag("F");
        
        // 如果状态为发布，设置发布时间
        if ("published".equals(content.getStatus()) && content.getPublishTime() == null) {
            content.setPublishTime(LocalDateTime.now());
        }
        
        // 保存内容
        save(content);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CmsContentUpdateDTO dto) {
        CmsContent existing = getById(dto.getId());
        if (existing == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "内容不存在");
        }
        
        // 检查分类是否存在
        if (dto.getCategoryId() != null && categoryService.getById(dto.getCategoryId()) == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "分类不存在");
        }
        
        // 更新内容信息
        CmsContent content = BeanCopyUtils.copy(dto, CmsContent.class);
        
        // 如果状态变为发布，设置发布时间
        if ("published".equals(content.getStatus()) && !"published".equals(existing.getStatus())) {
            content.setPublishTime(LocalDateTime.now());
        }
        
        updateById(content);
    }

    @Override
    public PageResult<CmsContentVO> page(CmsContentListDTO dto) {
        Page<CmsContentVO> page = pageAs(PageUtils.getPage(dto), buildQueryWrapper(dto), CmsContentVO.class);
        return PageUtils.getPageResult(page);
    }
    
    private QueryWrapper buildQueryWrapper(CmsContentListDTO dto) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(CmsContent::getDelFlag).eq("F");
        
        if (StringUtils.hasText(dto.getTitle())) {
            queryWrapper.and(CmsContent::getTitle).like(dto.getTitle());
        }
        if (dto.getCategoryId() != null) {
            queryWrapper.and(CmsContent::getCategoryId).eq(dto.getCategoryId());
        }
        if (dto.getAuthorId() != null) {
            queryWrapper.and(CmsContent::getAuthorId).eq(dto.getAuthorId());
        }
        if (StringUtils.hasText(dto.getStatus())) {
            queryWrapper.and(CmsContent::getStatus).eq(dto.getStatus());
        }
        if (StringUtils.hasText(dto.getContentType())) {
            queryWrapper.and(CmsContent::getContentType).eq(dto.getContentType());
        }
        if (StringUtils.hasText(dto.getIsTop())) {
            queryWrapper.and(CmsContent::getIsTop).eq(dto.getIsTop());
        }
        if (StringUtils.hasText(dto.getIsHot())) {
            queryWrapper.and(CmsContent::getIsHot).eq(dto.getIsHot());
        }
        if (dto.getPublishTimeStart() != null) {
            queryWrapper.and(CmsContent::getPublishTime).ge(dto.getPublishTimeStart());
        }
        if (dto.getPublishTimeEnd() != null) {
            queryWrapper.and(CmsContent::getPublishTime).le(dto.getPublishTimeEnd());
        }
        queryWrapper.orderBy(CmsContent::getSort, true).orderBy(CmsContent::getId, false);
        
        return queryWrapper;
    }

    @Override
    public CmsContentVO detail(Long id) {
        CmsContentVO contentVO = mapper.selectContentById(id);
        if (contentVO == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "内容不存在");
        }
        return contentVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(SelectIdsDTO dto) {
        for (Object idObj : dto.getIds()) {
            Long id = Long.valueOf(idObj.toString());
            removeById(id);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publish(Long id) {
        CmsContent content = getById(id);
        if (content == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "内容不存在");
        }
        
        content.setStatus("published");
        content.setPublishTime(LocalDateTime.now());
        updateById(content);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void offline(Long id) {
        CmsContent content = getById(id);
        if (content == null) {
            throw new BusinessException(CommonResponseEnum.NOT_EXISTS, null, "内容不存在");
        }
        
        content.setStatus("offline");
        updateById(content);
    }

    @Override
    public void incrementViewCount(Long id) {
        mapper.incrementViewCount(id);
    }

    @Override
    public void updateStatus(Long id, String status) {
        CmsContent content = new CmsContent();
        content.setId(id);
        content.setStatus(status);
        
        // 如果状态变为发布，设置发布时间
        if ("published".equals(status)) {
            content.setPublishTime(LocalDateTime.now());
        }
        
        updateById(content);
    }

    @Override
    public void updateTop(Long id, String isTop) {
        CmsContent content = new CmsContent();
        content.setId(id);
        content.setIsTop(isTop);
        updateById(content);
    }

    @Override
    public void updateHot(Long id, String isHot) {
        CmsContent content = new CmsContent();
        content.setId(id);
        content.setIsHot(isHot);
        updateById(content);
    }
}