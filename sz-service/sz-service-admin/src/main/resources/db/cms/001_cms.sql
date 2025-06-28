-- liquibase formatted sql

-- changeset cms:1
-- ----------------------------
-- CMS内容管理系统 数据库表
-- 版本: V1.1.0
-- 日期: 2025-06-28
-- 描述: 添加CMS内容分类管理和内容管理模块
-- ----------------------------

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
    `pid` int NOT NULL DEFAULT 0 COMMENT '父级分类ID，0为顶级分类',
    `deep` int NULL DEFAULT 1 COMMENT '层级深度',
    `sort` int NULL DEFAULT 0 COMMENT '排序序号',
    `has_children` enum('T','F') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'F' COMMENT '是否有子分类',
    `status` enum('enable','disable') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'enable' COMMENT '状态：enable-启用，disable-禁用',
    `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类图标',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类描述',
    `seo_keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO关键词',
    `seo_description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO描述',
    `del_flag` enum('T','F') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'F' COMMENT '删除标识',
    `create_id` int NULL DEFAULT NULL COMMENT '创建人ID',
    `update_id` int NULL DEFAULT NULL COMMENT '更新人ID',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_pid`(`pid` ASC) USING BTREE,
    INDEX `idx_status`(`status` ASC) USING BTREE,
    INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'CMS内容分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cms_category_closure
-- ----------------------------
DROP TABLE IF EXISTS `cms_category_closure`;
CREATE TABLE `cms_category_closure` (
    `ancestor_id` int NOT NULL COMMENT '祖先分类ID',
    `descendant_id` int NOT NULL COMMENT '后代分类ID',
    `depth` int NOT NULL COMMENT '祖先分类到后代分类的距离',
    PRIMARY KEY (`ancestor_id`, `descendant_id`) USING BTREE,
    INDEX `idx_descendant`(`descendant_id` ASC) USING BTREE,
    INDEX `idx_depth`(`depth` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'CMS分类祖籍关系表（闭包表）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for cms_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_content`;
CREATE TABLE `cms_content` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '内容ID',
    `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容标题',
    `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容正文',
    `summary` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容摘要',
    `category_id` int NOT NULL COMMENT '分类ID',
    `author_id` int NOT NULL COMMENT '作者ID',
    `status` enum('draft','reviewing','published','offline') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'draft' COMMENT '状态：draft-草稿，reviewing-审核中，published-已发布，offline-已下线',
    `content_type` enum('article','page','notice') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'article' COMMENT '内容类型：article-文章，page-页面，notice-公告',
    `cover_image_id` int NULL DEFAULT NULL COMMENT '封面图片ID，关联sys_file表',
    `view_count` int NULL DEFAULT 0 COMMENT '查看次数',
    `sort` int NULL DEFAULT 0 COMMENT '排序序号',
    `is_top` enum('T','F') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'F' COMMENT '是否置顶',
    `is_hot` enum('T','F') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'F' COMMENT '是否热门',
    `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
    `seo_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO标题',
    `seo_keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO关键词',
    `seo_description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO描述',
    `del_flag` enum('T','F') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'F' COMMENT '删除标识',
    `create_id` int NULL DEFAULT NULL COMMENT '创建人ID',
    `update_id` int NULL DEFAULT NULL COMMENT '更新人ID',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_category_id`(`category_id` ASC) USING BTREE,
    INDEX `idx_author_id`(`author_id` ASC) USING BTREE,
    INDEX `idx_status`(`status` ASC) USING BTREE,
    INDEX `idx_content_type`(`content_type` ASC) USING BTREE,
    INDEX `idx_publish_time`(`publish_time` ASC) USING BTREE,
    INDEX `idx_is_top_sort`(`is_top` ASC, `sort` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'CMS内容表' ROW_FORMAT = DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;