package com.sz.admin.cms.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.sz.admin.cms.mapper.CmsCategoryClosureMapper;
import com.sz.admin.cms.pojo.po.CmsCategoryClosure;
import com.sz.admin.cms.service.CmsCategoryClosureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * CMS分类祖籍关系表 Service实现类
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
@Service
@RequiredArgsConstructor
public class CmsCategoryClosureServiceImpl extends ServiceImpl<CmsCategoryClosureMapper, CmsCategoryClosure> implements CmsCategoryClosureService {

    @Override
    public void insertClosure(Long descendantId, Long ancestorId) {
        mapper.insertClosure(descendantId, ancestorId);
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        mapper.deleteByCategoryId(categoryId);
    }

    @Override
    public List<Long> getDescendantIds(Long categoryId) {
        return mapper.selectDescendantIds(categoryId);
    }

    @Override
    public List<Long> getAncestorIds(Long categoryId) {
        return mapper.selectAncestorIds(categoryId);
    }
}