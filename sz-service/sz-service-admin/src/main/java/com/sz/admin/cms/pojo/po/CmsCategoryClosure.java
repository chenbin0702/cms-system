package com.sz.admin.cms.pojo.po;

import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * CMS分类祖籍关系表（闭包表）
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Table(value = "cms_category_closure")
@Schema(description = "CMS分类祖籍关系表")
public class CmsCategoryClosure implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "祖先分类ID")
    private Long ancestorId;

    @Schema(description = "后代分类ID")
    private Long descendantId;

    @Schema(description = "祖先分类到后代分类的距离")
    private Integer depth;
}