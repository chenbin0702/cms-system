package com.sz.admin.cms.pojo.vo.cmscategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * CMS分类VO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Schema(description = "CMS分类VO")
public class CmsCategoryVO {

    @Schema(description = "分类ID")
    private Long id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "父级分类ID")
    private Long pid;

    @Schema(description = "层级深度")
    private Integer deep;

    @Schema(description = "排序序号")
    private Integer sort;

    @Schema(description = "是否有子分类")
    private String hasChildren;

    @Schema(description = "状态：enable-启用，disable-禁用")
    private String status;

    @Schema(description = "分类图标")
    private String icon;

    @Schema(description = "分类描述")
    private String description;

    @Schema(description = "SEO关键词")
    private String seoKeywords;

    @Schema(description = "SEO描述")
    private String seoDescription;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}