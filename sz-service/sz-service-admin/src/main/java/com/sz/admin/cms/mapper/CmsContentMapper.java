package com.sz.admin.cms.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentListDTO;
import com.sz.admin.cms.pojo.po.CmsContent;
import com.sz.admin.cms.pojo.vo.cmscontent.CmsContentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * CMS内容表 Mapper 接口
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Mapper
public interface CmsContentMapper extends BaseMapper<CmsContent> {

    /**
     * 分页查询内容列表
     * @param page 分页对象
     * @param dto 查询条件
     * @return 内容VO列表
     */
    Page<CmsContentVO> selectContentPage(@Param("page") Page<CmsContentVO> page, @Param("dto") CmsContentListDTO dto);

    /**
     * 根据ID查询内容详情
     * @param id 内容ID
     * @return 内容VO
     */
    CmsContentVO selectContentById(@Param("id") Long id);

    /**
     * 增加查看次数
     * @param id 内容ID
     */
    void incrementViewCount(@Param("id") Long id);

    /**
     * 根据分类ID统计内容数量
     * @param categoryId 分类ID
     * @return 内容数量
     */
    Long countByCategoryId(@Param("categoryId") Long categoryId);
}