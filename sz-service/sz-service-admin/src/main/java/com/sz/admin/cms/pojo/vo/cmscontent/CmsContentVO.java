package com.sz.admin.cms.pojo.vo.cmscontent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * CMS内容VO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Data
@Schema(description = "CMS内容VO")
public class CmsContentVO {

    @Schema(description = "内容ID")
    private Long id;

    @Schema(description = "内容标题")
    private String title;

    @Schema(description = "内容正文")
    private String content;

    @Schema(description = "内容摘要")
    private String summary;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "作者ID")
    private Long authorId;

    @Schema(description = "作者名称")
    private String authorName;

    @Schema(description = "状态：draft-草稿，reviewing-审核中，published-已发布，offline-已下线")
    private String status;

    @Schema(description = "内容类型：article-文章，page-页面，notice-公告")
    private String contentType;

    @Schema(description = "封面图片ID")
    private Long coverImageId;

    @Schema(description = "封面图片URL")
    private String coverImageUrl;

    @Schema(description = "查看次数")
    private Integer viewCount;

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

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}