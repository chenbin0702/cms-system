package com.sz.admin.cms.pojo.dto.cmscategory;

import com.sz.core.common.entity.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * CMS分类查询DTO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "CMS分类查询DTO")
public class CmsCategoryListDTO extends PageQuery {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "状态：enable-启用，disable-禁用")
    private String status;

    @Schema(description = "父级分类ID")
    private Long pid;
}