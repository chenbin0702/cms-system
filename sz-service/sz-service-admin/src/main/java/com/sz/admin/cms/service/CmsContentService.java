package com.sz.admin.cms.service;

import com.mybatisflex.core.service.IService;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentCreateDTO;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentListDTO;
import com.sz.admin.cms.pojo.dto.cmscontent.CmsContentUpdateDTO;
import com.sz.admin.cms.pojo.po.CmsContent;
import com.sz.admin.cms.pojo.vo.cmscontent.CmsContentVO;
import com.sz.core.common.entity.PageResult;
import com.sz.core.common.entity.SelectIdsDTO;

/**
 * <p>
 * CMS内容表 Service
 * </p>
 *
 * @author sz-admin
 * @since 2025-06-28
 */
public interface CmsContentService extends IService<CmsContent> {

    /**
     * 创建内容
     * @param dto 创建DTO
     */
    void create(CmsContentCreateDTO dto);

    /**
     * 更新内容
     * @param dto 更新DTO
     */
    void update(CmsContentUpdateDTO dto);

    /**
     * 分页查询内容列表
     * @param dto 查询条件
     * @return 分页结果
     */
    PageResult<CmsContentVO> page(CmsContentListDTO dto);

    /**
     * 根据ID查询内容详情
     * @param id 内容ID
     * @return 内容VO
     */
    CmsContentVO detail(Long id);

    /**
     * 批量删除内容
     * @param dto 删除DTO
     */
    void deleteBatch(SelectIdsDTO dto);

    /**
     * 发布内容
     * @param id 内容ID
     */
    void publish(Long id);

    /**
     * 下线内容
     * @param id 内容ID
     */
    void offline(Long id);

    /**
     * 增加查看次数
     * @param id 内容ID
     */
    void incrementViewCount(Long id);

    /**
     * 更新内容状态
     * @param id 内容ID
     * @param status 状态
     */
    void updateStatus(Long id, String status);

    /**
     * 置顶/取消置顶
     * @param id 内容ID
     * @param isTop 是否置顶
     */
    void updateTop(Long id, String isTop);

    /**
     * 设置/取消热门
     * @param id 内容ID
     * @param isHot 是否热门
     */
    void updateHot(Long id, String isHot);
}