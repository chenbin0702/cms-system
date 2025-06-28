package com.sz.admin.cms.pojo.vo.cmscategory;

import com.sz.core.common.service.Treeable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * CMS分类树形结构VO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Schema(description = "CMS分类树形结构VO")
public class CmsCategoryTreeVO implements Treeable<CmsCategoryTreeVO> {

    @Schema(description = "分类ID")
    private Long id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "父级分类ID")
    private Long pid;

    @Schema(description = "层级深度")
    private Long deep;

    @Schema(description = "排序序号")
    private Long sort;

    @Schema(description = "是否有子分类")
    private String hasChildren;

    @Schema(description = "状态：enable-启用，disable-禁用")
    private String status;

    @Schema(description = "分类图标")
    private String icon;

    @Schema(description = "分类描述")
    private String description;

    @Schema(description = "子分类列表")
    private List<CmsCategoryTreeVO> children;
}