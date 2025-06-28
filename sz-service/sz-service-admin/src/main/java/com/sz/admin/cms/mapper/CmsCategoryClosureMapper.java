package com.sz.admin.cms.mapper;

import com.mybatisflex.core.BaseMapper;
import com.sz.admin.cms.pojo.po.CmsCategoryClosure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * CMS分类祖籍关系表 Mapper 接口
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Mapper
public interface CmsCategoryClosureMapper extends BaseMapper<CmsCategoryClosure> {

    /**
     * 插入分类闭包关系
     * @param descendantId 后代分类ID
     * @param ancestorId 祖先分类ID
     */
    void insertClosure(@Param("descendantId") Long descendantId, @Param("ancestorId") Long ancestorId);

    /**
     * 删除分类的所有闭包关系
     * @param categoryId 分类ID
     */
    void deleteByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 查询分类的所有子分类ID
     * @param categoryId 分类ID
     * @return 子分类ID列表
     */
    List<Long> selectDescendantIds(@Param("categoryId") Long categoryId);

    /**
     * 查询分类的所有父分类ID
     * @param categoryId 分类ID
     * @return 父分类ID列表
     */
    List<Long> selectAncestorIds(@Param("categoryId") Long categoryId);
}