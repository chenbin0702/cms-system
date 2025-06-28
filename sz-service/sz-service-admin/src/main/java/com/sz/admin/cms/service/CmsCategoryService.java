package com.sz.admin.cms.service;

import com.mybatisflex.core.service.IService;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryCreateDTO;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryListDTO;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryUpdateDTO;
import com.sz.admin.cms.pojo.po.CmsCategory;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryTreeVO;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryVO;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;

import java.util.List;

/**
 * <p>
 * CMS内容分类表 Service
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
public interface CmsCategoryService extends IService<CmsCategory> {

    /**
     * 创建分类
     * @param dto 创建DTO
     */
    void create(CmsCategoryCreateDTO dto);

    /**
     * 更新分类
     * @param dto 更新DTO
     */
    void update(CmsCategoryUpdateDTO dto);

    /**
     * 分页查询分类列表
     * @param dto 查询条件
     * @return 分页结果
     */
    PageResult<CmsCategoryVO> page(CmsCategoryListDTO dto);

    /**
     * 查询分类树形结构
     * @return 树形结构列表
     */
    List<CmsCategoryTreeVO> tree();

    /**
     * 根据ID查询分类详情
     * @param id 分类ID
     * @return 分类VO
     */
    CmsCategoryVO detail(Long id);

    /**
     * 批量删除分类
     * @param dto 删除DTO
     */
    void deleteBatch(SelectIdsDTO dto);

    /**
     * 检查分类是否可以删除
     * @param categoryId 分类ID
     * @return 是否可以删除
     */
    boolean canDelete(Long categoryId);

    /**
     * 更新分类状态
     * @param id 分类ID
     * @param status 状态
     */
    void updateStatus(Long id, String status);

    /**
     * 获取分类选项列表（用于下拉框）
     * @return 分类选项列表
     */
    List<CmsCategoryTreeVO> options();
}