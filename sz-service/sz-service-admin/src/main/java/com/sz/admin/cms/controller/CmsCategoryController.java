package com.sz.admin.cms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryCreateDTO;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryListDTO;
import com.sz.admin.cms.pojo.dto.cmscategory.CmsCategoryUpdateDTO;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryTreeVO;
import com.sz.admin.cms.pojo.vo.cmscategory.CmsCategoryVO;
import com.sz.admin.cms.service.CmsCategoryService;
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

import java.util.List;

/**
 * <p>
 * CMS内容分类表 前端控制器
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Tag(name = "CMS内容分类管理")
@RestController
@RequestMapping("/cms/category")
@RequiredArgsConstructor
public class CmsCategoryController {

    private final CmsCategoryService categoryService;

    @Operation(summary = "创建分类")
    @SaCheckPermission("cms:category:create")
    @PostMapping
    public ApiResult<Void> create(@Valid @RequestBody CmsCategoryCreateDTO dto) {
        categoryService.create(dto);
        return ApiResult.success();
    }

    @Operation(summary = "更新分类")
    @SaCheckPermission("cms:category:update")
    @PutMapping
    public ApiResult<Void> update(@Valid @RequestBody CmsCategoryUpdateDTO dto) {
        categoryService.update(dto);
        return ApiResult.success();
    }

    @Operation(summary = "分页查询分类列表")
    @SaCheckPermission("cms:category:page")
    @PostMapping("/page")
    public ApiResult<PageResult<CmsCategoryVO>> page(@RequestBody CmsCategoryListDTO dto) {
        return ApiResult.success(categoryService.page(dto));
    }

    @Operation(summary = "查询分类树形结构")
    @SaCheckPermission("cms:category:tree")
    @GetMapping("/tree")
    public ApiResult<List<CmsCategoryTreeVO>> tree() {
        return ApiResult.success(categoryService.tree());
    }

    @Operation(summary = "查询分类选项列表")
    @SaCheckPermission("cms:category:options")
    @GetMapping("/options")
    public ApiResult<List<CmsCategoryTreeVO>> options() {
        return ApiResult.success(categoryService.options());
    }

    @Operation(summary = "查询分类详情")
    @SaCheckPermission("cms:category:detail")
    @GetMapping("/{id}")
    public ApiResult<CmsCategoryVO> detail(@Parameter(description = "分类ID") @PathVariable Long id) {
        return ApiResult.success(categoryService.detail(id));
    }

    @Operation(summary = "批量删除分类")
    @SaCheckPermission("cms:category:delete")
    @DeleteMapping
    public ApiResult<Void> deleteBatch(@Valid @RequestBody SelectIdsDTO dto) {
        categoryService.deleteBatch(dto);
        return ApiResult.success();
    }

    @Operation(summary = "更新分类状态")
    @SaCheckPermission("cms:category:status")
    @PutMapping("/{id}/status/{status}")
    public ApiResult<Void> updateStatus(
            @Parameter(description = "分类ID") @PathVariable Long id,
            @Parameter(description = "状态") @PathVariable String status) {
        categoryService.updateStatus(id, status);
        return ApiResult.success();
    }
}