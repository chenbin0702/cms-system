package com.sz.admin.cms.mapper;

import com.mybatisflex.core.BaseMapper;
import com.sz.admin.cms.pojo.po.CmsCategory;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * CMS内容分类表 Mapper 接口
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Mapper
public interface CmsCategoryMapper extends BaseMapper<CmsCategory> {

    /**
     * 查询分类树形结构
     * @return 分类树形列表
     */
    List<CmsCategoryTreeVO> selectCategoryTree();

    /**
     * 根据父级ID查询子分类
     * @param pid 父级ID
     * @return 子分类列表
     */
    List<CmsCategory> selectByPid(@Param("pid") Long pid);

    /**
     * 查询分类及其所有子分类ID
     * @param categoryId 分类ID
     * @return 分类ID列表
     */
    List<Long> selectCategoryAndChildren(@Param("categoryId") Long categoryId);

    /**
     * 更新分类的hasChildren字段
     * @param categoryId 分类ID
     * @param hasChildren 是否有子分类
     */
    void updateHasChildren(@Param("categoryId") Long categoryId, @Param("hasChildren") String hasChildren);
}