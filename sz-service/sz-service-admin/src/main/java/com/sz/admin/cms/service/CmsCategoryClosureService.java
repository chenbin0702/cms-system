package com.sz.admin.cms.service;

import com.mybatisflex.core.service.IService;
import com.sz.admin.cms.pojo.po.CmsCategoryClosure;

import java.util.List;

/**
 * <p>
 * CMS分类祖籍关系表 Service
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
public interface CmsCategoryClosureService extends IService<CmsCategoryClosure> {

    /**
     * 插入分类闭包关系
     * @param descendantId 后代分类ID
     * @param ancestorId 祖先分类ID
     */
    void insertClosure(Long descendantId, Long ancestorId);

    /**
     * 删除分类的所有闭包关系
     * @param categoryId 分类ID
     */
    void deleteByCategoryId(Long categoryId);

    /**
     * 查询分类的所有子分类ID
     * @param categoryId 分类ID
     * @return 子分类ID列表
     */
    List<Long> getDescendantIds(Long categoryId);

    /**
     * 查询分类的所有父分类ID
     * @param categoryId 分类ID
     * @return 父分类ID列表
     */
    List<Long> getAncestorIds(Long categoryId);
}