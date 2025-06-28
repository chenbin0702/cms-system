-- ----------------------------
-- CMS内容管理系统 字典数据
-- 版本: V2.8
-- 日期: 2025-06-28
-- 描述: 添加CMS系统相关的字典类型和字典数据
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- CMS字典类型数据
-- ----------------------------

-- CMS分类状态字典类型
INSERT INTO `sys_dict_type` (`id`, `type_name`, `type_code`, `is_lock`, `is_show`, `del_flag`, `remark`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`, `type`) 
VALUES (2001, 'CMS分类状态', 'cms_category_status', 'F', 'T', 'F', 'CMS内容分类的状态', NOW(), NULL, NULL, 1, NULL, NULL, 'system');

-- CMS内容状态字典类型
INSERT INTO `sys_dict_type` (`id`, `type_name`, `type_code`, `is_lock`, `is_show`, `del_flag`, `remark`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`, `type`) 
VALUES (2002, 'CMS内容状态', 'cms_content_status', 'F', 'T', 'F', 'CMS内容的发布状态', NOW(), NULL, NULL, 1, NULL, NULL, 'system');

-- CMS内容类型字典类型
INSERT INTO `sys_dict_type` (`id`, `type_name`, `type_code`, `is_lock`, `is_show`, `del_flag`, `remark`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`, `type`) 
VALUES (2003, 'CMS内容类型', 'cms_content_type', 'F', 'T', 'F', 'CMS内容的类型分类', NOW(), NULL, NULL, 1, NULL, NULL, 'system');

-- ----------------------------
-- CMS字典数据
-- ----------------------------

-- CMS分类状态字典数据
INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2001001, '2001', '启用', 'enable', 1, 'success', '分类状态为启用', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2001002, '2001', '禁用', 'disable', 2, 'danger', '分类状态为禁用', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

-- CMS内容状态字典数据
INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2002001, '2002', '草稿', 'draft', 1, 'info', '内容为草稿状态', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2002002, '2002', '审核中', 'reviewing', 2, 'warning', '内容正在审核中', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2002003, '2002', '已发布', 'published', 3, 'success', '内容已发布', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2002004, '2002', '已下线', 'offline', 4, 'danger', '内容已下线', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

-- CMS内容类型字典数据
INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2003001, '2003', '文章', 'article', 1, 'primary', '普通文章内容', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2003002, '2003', '页面', 'page', 2, 'success', '单页面内容', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

INSERT INTO `sys_dict` (`id`, `sys_dict_type_id`, `code_name`, `alias`, `sort`, `callback_show_style`, `remark`, `is_lock`, `is_show`, `del_flag`, `create_time`, `update_time`, `delete_time`, `create_id`, `update_id`, `delete_id`) 
VALUES (2003003, '2003', '公告', 'notice', 3, 'warning', '公告通知内容', 'F', 'T', 'F', NOW(), NULL, NULL, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;