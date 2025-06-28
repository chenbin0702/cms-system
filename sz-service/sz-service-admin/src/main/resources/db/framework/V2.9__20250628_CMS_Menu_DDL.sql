-- ----------------------------
-- CMS内容管理系统 菜单权限配置
-- 版本: V2.9
-- 日期: 2025-06-28
-- 描述: 添加CMS系统的菜单和权限配置
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- CMS模块菜单数据
-- ----------------------------

-- CMS主菜单
INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_main_menu', '0', '/cms', 'CmsModule', '内容管理', 'EditDocument', 'LAYOUT', '/cms/category', 800, 1, '1002001', '', 'F', 'T', 'F', 'F', 'F', 'F', 'F');

-- CMS分类管理菜单
INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_menu', 'cms_main_menu', '/cms/category', 'CmsCategoryView', '分类管理', 'Folder', '/cms/category/index', '', 100, 2, '1002002', 'cms:category:page', 'F', 'T', 'F', 'F', 'F', 'T', 'F');

-- CMS分类管理权限按钮
INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_create', 'cms_category_menu', '', '', '新增分类', '', '', '', 100, 3, '1002003', 'cms:category:create', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_update', 'cms_category_menu', '', '', '修改分类', '', '', '', 200, 3, '1002003', 'cms:category:update', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_delete', 'cms_category_menu', '', '', '删除分类', '', '', '', 300, 3, '1002003', 'cms:category:delete', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_detail', 'cms_category_menu', '', '', '查看分类', '', '', '', 400, 3, '1002003', 'cms:category:detail', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_tree', 'cms_category_menu', '', '', '分类树', '', '', '', 500, 3, '1002003', 'cms:category:tree', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_options', 'cms_category_menu', '', '', '分类选项', '', '', '', 600, 3, '1002003', 'cms:category:options', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_category_status', 'cms_category_menu', '', '', '更新状态', '', '', '', 700, 3, '1002003', 'cms:category:status', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

-- CMS内容管理菜单
INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_menu', 'cms_main_menu', '/cms/content', 'CmsContentView', '内容管理', 'Document', '/cms/content/index', '', 200, 2, '1002002', 'cms:content:page', 'F', 'T', 'F', 'F', 'F', 'T', 'F');

-- CMS内容管理权限按钮
INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_create', 'cms_content_menu', '', '', '新增内容', '', '', '', 100, 3, '1002003', 'cms:content:create', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_update', 'cms_content_menu', '', '', '修改内容', '', '', '', 200, 3, '1002003', 'cms:content:update', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_delete', 'cms_content_menu', '', '', '删除内容', '', '', '', 300, 3, '1002003', 'cms:content:delete', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_detail', 'cms_content_menu', '', '', '查看内容', '', '', '', 400, 3, '1002003', 'cms:content:detail', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_publish', 'cms_content_menu', '', '', '发布内容', '', '', '', 500, 3, '1002003', 'cms:content:publish', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_offline', 'cms_content_menu', '', '', '下线内容', '', '', '', 600, 3, '1002003', 'cms:content:offline', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_status', 'cms_content_menu', '', '', '更新状态', '', '', '', 700, 3, '1002003', 'cms:content:status', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_top', 'cms_content_menu', '', '', '置顶管理', '', '', '', 800, 3, '1002003', 'cms:content:top', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

INSERT INTO `sys_menu` (`id`, `pid`, `path`, `name`, `title`, `icon`, `component`, `redirect`, `sort`, `deep`, `menu_type_cd`, `permissions`, `is_hidden`, `has_children`, `is_link`, `is_full`, `is_affix`, `is_keep_alive`, `del_flag`)
VALUES ('cms_content_hot', 'cms_content_menu', '', '', '热门管理', '', '', '', 900, 3, '1002003', 'cms:content:hot', 'F', 'F', 'F', 'F', 'F', 'F', 'F');

-- ----------------------------
-- 为管理员角色分配CMS权限
-- ----------------------------

-- CMS主菜单权限
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_main_menu', 1);

-- CMS分类管理权限
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_menu', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_create', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_update', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_delete', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_detail', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_tree', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_options', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_category_status', 1);

-- CMS内容管理权限
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_menu', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_create', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_update', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_delete', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_detail', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_publish', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_offline', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_status', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_top', 1);
INSERT INTO `sys_role_menu` (`menu_id`, `role_id`) VALUES ('cms_content_hot', 1);

SET FOREIGN_KEY_CHECKS = 1;