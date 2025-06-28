package com.sz.admin.cms.pojo.dto.cmscategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * CMS分类添加DTO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Schema(description = "CMS分类添加DTO")
public class CmsCategoryCreateDTO {

    @Schema(description = "分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String name;

    @Schema(description = "父级分类ID，0为顶级分类")
    @NotNull(message = "父级分类ID不能为空")
    private Long pid;

    @Schema(description = "排序序号")
    private Integer sort;

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
}