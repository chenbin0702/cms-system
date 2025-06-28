package com.sz.admin.cms.pojo.dto.cmscontent;

import com.sz.core.common.entity.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * CMS内容查询DTO
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "CMS内容查询DTO")
public class CmsContentListDTO extends PageQuery {

    @Schema(description = "内容标题")
    private String title;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "作者ID")
    private Long authorId;

    @Schema(description = "状态：draft-草稿，reviewing-审核中，published-已发布，offline-已下线")
    private String status;

    @Schema(description = "内容类型：article-文章，page-页面，notice-公告")
    private String contentType;

    @Schema(description = "是否置顶")
    private String isTop;

    @Schema(description = "是否热门")
    private String isHot;

    @Schema(description = "发布时间开始")
    private LocalDateTime publishTimeStart;

    @Schema(description = "发布时间结束")
    private LocalDateTime publishTimeEnd;
}