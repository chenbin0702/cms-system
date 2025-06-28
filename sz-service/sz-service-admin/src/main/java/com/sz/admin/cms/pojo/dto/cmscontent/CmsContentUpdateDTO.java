package com.sz.admin.cms.pojo.dto.cmscontent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * CMS内容更新DTO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Schema(description = "CMS内容更新DTO")
public class CmsContentUpdateDTO {

    @Schema(description = "内容ID")
    @NotNull(message = "内容ID不能为空")
    private Long id;

    @Schema(description = "内容标题")
    private String title;

    @Schema(description = "内容正文")
    private String content;

    @Schema(description = "内容摘要")
    private String summary;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "状态：draft-草稿，reviewing-审核中，published-已发布，offline-已下线")
    private String status;

    @Schema(description = "内容类型：article-文章，page-页面，notice-公告")
    private String contentType;

    @Schema(description = "封面图片ID")
    private Long coverImageId;

    @Schema(description = "排序序号")
    private Integer sort;

    @Schema(description = "是否置顶")
    private String isTop;

    @Schema(description = "是否热门")
    private String isHot;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "SEO标题")
    private String seoTitle;

    @Schema(description = "SEO关键词")
    private String seoKeywords;

    @Schema(description = "SEO描述")
    private String seoDescription;
}