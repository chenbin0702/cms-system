package com.sz.admin.cms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentCreateDTO;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentListDTO;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentUpdateDTO;
import com.sz.admin.cms.pojo.vo.cmscontent.CmsContentVO;
import com.sz.admin.cms.service.CmsContentService;
import com.sz.core.common.constant.GlobalConstant;
import com.sz.core.common.entity.ApiResult;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * CMS内容表 前端控制器
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Tag(name = "CMS内容管理")
@RestController
@RequestMapping("/cms/content")
@RequiredArgsConstructor
public class CmsContentController {

    private final CmsContentService contentService;

    @Operation(summary = "创建内容")
    @SaCheckPermission("cms:content:create")
    @PostMapping
    public ApiResult<Void> create(@Valid @RequestBody CmsContentCreateDTO dto) {
        contentService.create(dto);
        return ApiResult.success();
    }

    @Operation(summary = "更新内容")
    @SaCheckPermission("cms:content:update")
    @PutMapping
    public ApiResult<Void> update(@Valid @RequestBody CmsContentUpdateDTO dto) {
        contentService.update(dto);
        return ApiResult.success();
    }

    @Operation(summary = "分页查询内容列表")
    @SaCheckPermission("cms:content:page")
    @PostMapping("/page")
    public ApiResult<PageResult<CmsContentVO>> page(@RequestBody CmsContentListDTO dto) {
        return ApiResult.success(contentService.page(dto));
    }

    @Operation(summary = "查询内容详情")
    @SaCheckPermission("cms:content:detail")
    @GetMapping("/{id}")
    public ApiResult<CmsContentVO> detail(@Parameter(description = "内容ID") @PathVariable Long id) {
        return ApiResult.success(contentService.detail(id));
    }

    @Operation(summary = "批量删除内容")
    @SaCheckPermission("cms:content:delete")
    @DeleteMapping
    public ApiResult<Void> deleteBatch(@Valid @RequestBody SelectIdsDTO dto) {
        contentService.deleteBatch(dto);
        return ApiResult.success();
    }

    @Operation(summary = "发布内容")
    @SaCheckPermission("cms:content:publish")
    @PutMapping("/{id}/publish")
    public ApiResult<String> publish(@Parameter(description = "内容ID") @PathVariable Long id) {
        contentService.publish(id);
        return ApiResult.success("发布成功");
    }

    @Operation(summary = "下线内容")
    @SaCheckPermission("cms:content:offline")
    @PutMapping("/{id}/offline")
    public ApiResult<String> offline(@Parameter(description = "内容ID") @PathVariable Long id) {
        contentService.offline(id);
        return ApiResult.success("下线成功");
    }

    @Operation(summary = "更新内容状态")
    @SaCheckPermission("cms:content:status")
    @PutMapping("/{id}/status/{status}")
    public ApiResult<Void> updateStatus(
            @Parameter(description = "内容ID") @PathVariable Long id,
            @Parameter(description = "状态") @PathVariable String status) {
        contentService.updateStatus(id, status);
        return ApiResult.success();
    }

    @Operation(summary = "置顶/取消置顶")
    @SaCheckPermission("cms:content:top")
    @PutMapping("/{id}/top/{isTop}")
    public ApiResult<Void> updateTop(
            @Parameter(description = "内容ID") @PathVariable Long id,
            @Parameter(description = "是否置顶") @PathVariable String isTop) {
        contentService.updateTop(id, isTop);
        return ApiResult.success();
    }

    @Operation(summary = "设置/取消热门")
    @SaCheckPermission("cms:content:hot")
    @PutMapping("/{id}/hot/{isHot}")
    public ApiResult<Void> updateHot(
            @Parameter(description = "内容ID") @PathVariable Long id,
            @Parameter(description = "是否热门") @PathVariable String isHot) {
        contentService.updateHot(id, isHot);
        return ApiResult.success();
    }

    @Operation(summary = "增加查看次数", description = "前端查看内容时调用，无需权限验证")
    @PostMapping("/{id}/view")
    public ApiResult<String> incrementViewCount(@Parameter(description = "内容ID") @PathVariable Long id) {
        contentService.incrementViewCount(id);
        return ApiResult.success("查看次数已更新");
    }
}