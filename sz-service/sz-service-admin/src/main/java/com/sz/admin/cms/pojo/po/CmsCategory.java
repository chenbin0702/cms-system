package com.sz.admin.cms.pojo.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.sz.mysql.EntityChangeListener;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * CMS内容分类表
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Table(value = "cms_category", onInsert = EntityChangeListener.class, onUpdate = EntityChangeListener.class)
@Schema(description = "CMS内容分类表")
public class CmsCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    @Schema(description = "分类ID")
    private Long id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "父级分类ID，0为顶级分类")
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

    @Schema(description = "删除标识")
    private String delFlag;

    @Schema(description = "创建人ID")
    private Long createId;

    @Schema(description = "更新人ID")
    private Long updateId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}