/*
 Navicat Premium Data Transfer

 Source Server         : 47.98.184.122-gerry
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 47.98.184.122:33061
 Source Schema         : cj_ts

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 29/11/2019 00:35:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin` VALUES (1, 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', NULL, '测试账号', NULL, '2018-09-29 13:55:30', '2018-09-29 13:55:39', 1);
INSERT INTO `ums_admin` VALUES (3, 'admin', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-03-20 15:38:50', 1);
INSERT INTO `ums_admin` VALUES (5, '温国力', '$2a$10$AvhA7/WcaYDDt1NGCdcWPu0BBBzEM7gwRA7vcEb8jRXaZ48gag1l.', '', 'blog@gerrywen.com', 'GerryWen', '', '2019-08-23 17:23:54', NULL, 1);
INSERT INTO `ums_admin` VALUES (6, 'wenguoli', '$2a$10$F0ESZ1IaIjc29WTmSSnBU.e7eKal9z5214pgncX3A0VFu9GoBbqE2', 'https://cdn2.jianshu.io/assets/default_avatar/7-0993d41a595d6ab6ef17b19496eb2f21.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240', '396735426@qq.com', '温国力', 'java开发者', '2019-08-26 11:27:46', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';

-- ----------------------------
-- Records of ums_admin_login_log
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin_login_log` VALUES (5, 3, '2018-12-06 13:59:12', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (6, 3, '2018-12-17 13:23:20', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (7, 3, '2018-12-18 13:51:42', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (8, 3, '2018-12-18 13:51:51', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (9, 3, '2019-01-28 16:20:41', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (10, 3, '2019-01-29 09:16:25', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (11, 3, '2019-01-29 10:10:51', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (12, 3, '2019-02-18 11:03:06', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (13, 3, '2019-03-12 10:03:55', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (14, 3, '2019-03-12 10:06:19', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (15, 3, '2019-03-12 10:15:22', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (16, 3, '2019-03-20 15:35:33', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (17, 3, '2019-03-20 15:38:50', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (18, 5, '2019-08-23 17:24:22', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (19, 5, '2019-08-23 17:28:34', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (20, 5, '2019-08-23 17:45:18', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (21, 5, '2019-08-26 11:12:25', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (22, 5, '2019-08-26 11:14:24', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (23, 5, '2019-08-26 11:22:24', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (24, 3, '2019-09-28 11:10:56', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (25, 3, '2019-09-28 11:12:30', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (26, 3, '2019-10-22 09:53:12', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (27, 3, '2019-10-22 09:53:28', '0:0:0:0:0:0:0:1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (28, 3, '2019-11-29 00:16:52', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (29, 3, '2019-11-29 00:19:09', '127.0.0.1', NULL, NULL);
INSERT INTO `ums_admin_login_log` VALUES (30, 3, '2019-11-29 00:27:00', '127.0.0.1', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission`;
CREATE TABLE `ums_admin_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of ums_admin_permission
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin_permission` VALUES (1, 0, '商品', NULL, NULL, 0, NULL, 1, '2018-09-29 16:15:14', 0);
INSERT INTO `ums_admin_permission` VALUES (2, 1, '商品列表', 'pms:product:read', NULL, 1, '/pms/product/index', 1, '2018-09-29 16:17:01', 0);
INSERT INTO `ums_admin_permission` VALUES (3, 1, '添加商品', 'pms:product:create', NULL, 1, '/pms/product/add', 1, '2018-09-29 16:18:51', 0);
INSERT INTO `ums_admin_permission` VALUES (4, 1, '商品分类', 'pms:productCategory:read', NULL, 1, '/pms/productCate/index', 1, '2018-09-29 16:23:07', 0);
INSERT INTO `ums_admin_permission` VALUES (5, 1, '商品类型', 'pms:productAttribute:read', NULL, 1, '/pms/productAttr/index', 1, '2018-09-29 16:24:43', 0);
INSERT INTO `ums_admin_permission` VALUES (6, 1, '品牌管理', 'pms:brand:read', NULL, 1, '/pms/brand/index', 1, '2018-09-29 16:25:45', 0);
INSERT INTO `ums_admin_permission` VALUES (7, 2, '编辑商品', 'pms:product:update', NULL, 2, '/pms/product/updateProduct', 1, '2018-09-29 16:34:23', 0);
INSERT INTO `ums_admin_permission` VALUES (8, 2, '删除商品', 'pms:product:delete', NULL, 2, '/pms/product/delete', 1, '2018-09-29 16:38:33', 0);
INSERT INTO `ums_admin_permission` VALUES (9, 4, '添加商品分类', 'pms:productCategory:create', NULL, 2, '/pms/productCate/create', 1, '2018-09-29 16:43:23', 0);
INSERT INTO `ums_admin_permission` VALUES (10, 4, '修改商品分类', 'pms:productCategory:update', NULL, 2, '/pms/productCate/update', 1, '2018-09-29 16:43:55', 0);
INSERT INTO `ums_admin_permission` VALUES (11, 4, '删除商品分类', 'pms:productCategory:delete', NULL, 2, '/pms/productAttr/delete', 1, '2018-09-29 16:44:38', 0);
INSERT INTO `ums_admin_permission` VALUES (12, 5, '添加商品类型', 'pms:productAttribute:create', NULL, 2, '/pms/productAttr/create', 1, '2018-09-29 16:45:25', 0);
INSERT INTO `ums_admin_permission` VALUES (13, 5, '修改商品类型', 'pms:productAttribute:update', NULL, 2, '/pms/productAttr/update', 1, '2018-09-29 16:48:08', 0);
INSERT INTO `ums_admin_permission` VALUES (14, 5, '删除商品类型', 'pms:productAttribute:delete', NULL, 2, '/pms/productAttr/delete', 1, '2018-09-29 16:48:44', 0);
INSERT INTO `ums_admin_permission` VALUES (15, 6, '添加品牌', 'pms:brand:create', NULL, 2, '/pms/brand/add', 1, '2018-09-29 16:49:34', 0);
INSERT INTO `ums_admin_permission` VALUES (16, 6, '修改品牌', 'pms:brand:update', NULL, 2, '/pms/brand/update', 1, '2018-09-29 16:50:55', 0);
INSERT INTO `ums_admin_permission` VALUES (17, 6, '删除品牌', 'pms:brand:delete', NULL, 2, '/pms/brand/delete', 1, '2018-09-29 16:50:59', 0);
INSERT INTO `ums_admin_permission` VALUES (18, 0, '首页', NULL, NULL, 0, NULL, 1, '2018-09-29 16:51:57', 0);
INSERT INTO `ums_admin_permission` VALUES (19, 0, '订单', '', '', 0, '', 1, '2019-08-26 16:39:20', 0);
INSERT INTO `ums_admin_permission` VALUES (20, 19, '订单列表', 'oms:order:read', '', 1, '/oms/order/read', 1, '2019-08-26 16:40:00', 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
CREATE TABLE `ums_admin_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';

-- ----------------------------
-- Records of ums_admin_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin_permission_relation` VALUES (25, 6, 4, 1);
INSERT INTO `ums_admin_permission_relation` VALUES (26, 6, 7, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (27, 6, 8, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (28, 6, 12, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (29, 6, 13, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (30, 6, 14, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (31, 6, 6, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (32, 6, 15, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (33, 6, 16, -1);
INSERT INTO `ums_admin_permission_relation` VALUES (34, 6, 17, -1);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role`;
CREATE TABLE `ums_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of ums_admin_role
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin_role` VALUES (1, '商品管理员', '商品管理员', 0, '2018-09-30 15:46:11', 1, 0);
INSERT INTO `ums_admin_role` VALUES (2, '商品分类管理员', '商品分类管理员', 0, '2018-09-30 15:53:45', 1, 0);
INSERT INTO `ums_admin_role` VALUES (3, '商品类型管理员', '商品类型管理员', 0, '2018-09-30 15:53:56', 1, 0);
INSERT INTO `ums_admin_role` VALUES (4, '品牌管理员', '品牌管理员', 0, '2018-09-30 15:54:12', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_permission_relation`;
CREATE TABLE `ums_admin_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of ums_admin_role_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `ums_admin_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `ums_admin_role_permission_relation` VALUES (3, 1, 3);
INSERT INTO `ums_admin_role_permission_relation` VALUES (4, 1, 7);
INSERT INTO `ums_admin_role_permission_relation` VALUES (5, 1, 8);
INSERT INTO `ums_admin_role_permission_relation` VALUES (6, 2, 4);
INSERT INTO `ums_admin_role_permission_relation` VALUES (7, 2, 9);
INSERT INTO `ums_admin_role_permission_relation` VALUES (8, 2, 10);
INSERT INTO `ums_admin_role_permission_relation` VALUES (9, 2, 11);
INSERT INTO `ums_admin_role_permission_relation` VALUES (10, 3, 5);
INSERT INTO `ums_admin_role_permission_relation` VALUES (11, 3, 12);
INSERT INTO `ums_admin_role_permission_relation` VALUES (12, 3, 13);
INSERT INTO `ums_admin_role_permission_relation` VALUES (13, 3, 14);
INSERT INTO `ums_admin_role_permission_relation` VALUES (18, 4, 6);
INSERT INTO `ums_admin_role_permission_relation` VALUES (19, 4, 15);
INSERT INTO `ums_admin_role_permission_relation` VALUES (20, 4, 16);
INSERT INTO `ums_admin_role_permission_relation` VALUES (21, 4, 17);
INSERT INTO `ums_admin_role_permission_relation` VALUES (22, 4, 18);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin_role_relation` VALUES (13, 3, 1);
INSERT INTO `ums_admin_role_relation` VALUES (15, 3, 2);
INSERT INTO `ums_admin_role_relation` VALUES (16, 3, 4);
INSERT INTO `ums_admin_role_relation` VALUES (31, 6, 1);
INSERT INTO `ums_admin_role_relation` VALUES (32, 6, 3);
INSERT INTO `ums_admin_role_relation` VALUES (33, 6, 4);
COMMIT;

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(128) NOT NULL DEFAULT '' COMMENT '创建用户',
  `resource_id` varchar(256) NOT NULL DEFAULT '' COMMENT '资源编号(相当于uuid)',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` varchar(128) NOT NULL DEFAULT '' COMMENT '修改用户',
  `verify_status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '审核状态',
  `version` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '记录版本',
  `user_type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '用户类型:1=手机号注册,2=微信',
  `account` varchar(64) NOT NULL DEFAULT '' COMMENT '用户帐号',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(30) NOT NULL DEFAULT '' COMMENT '密码盐',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `entry_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '入伍时间',
  `birthday` date NOT NULL DEFAULT '1970-01-01' COMMENT '生日',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别:0=保密,1=男,2=女',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '等级',
  `money` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '余额',
  `score` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '积分',
  `lasted_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `login_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '登陆次数',
  `login_ip` varchar(50) NOT NULL DEFAULT '' COMMENT '登录IP',
  `lockout_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帐号锁定时间',
  `login_failure` tinyint(1) unsigned NOT NULL DEFAULT '10' COMMENT '密码重试次数:超过10次提示联系管理员',
  `changed_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '密码最后修改时间',
  `change_account` tinyint(1) unsigned NOT NULL DEFAULT '10' COMMENT '允修改帐号的次数:每修改一次帐号减1，大0前允许修改',
  `company` varchar(255) NOT NULL DEFAULT '' COMMENT '所属连队',
  `card_type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '证件类型:1=身份证',
  `card_no` varchar(50) NOT NULL DEFAULT '' COMMENT '证件号码',
  `card_url` varchar(500) NOT NULL DEFAULT '' COMMENT '证件Url',
  `card_back_url` varchar(500) NOT NULL DEFAULT '' COMMENT '证件反面Url',
  `token` varchar(50) NOT NULL DEFAULT '' COMMENT 'Token',
  `memo` varchar(255) NOT NULL DEFAULT '' COMMENT '备注:审核意见填此',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '审核状态:1=审核通过(正常),0=待审核,2=审核失败,9=禁用',
  `is_locked_out` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否已被锁定:0=否,1=是',
  `is_expert` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否专家:0=否,1=是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_idx_account` (`account`) USING BTREE,
  KEY `idx_mobile` (`mobile`),
  KEY `idx_status` (`status`),
  KEY `idx_is_locked_out` (`is_locked_out`),
  KEY `idx_is_expert` (`is_expert`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of ums_user
-- ----------------------------
BEGIN;
INSERT INTO `ums_user` VALUES (2, '2019-09-27 16:52:20', 'register', 'chxgytx.law.api.UmsUser.20190927165219699.051729614', '2019-09-27 16:52:20', 'register', 1, 0, 1, '15160333779', '', '', '123456', '12121313', '', '1970-01-01', '1970-01-01', 0, '', '', 0, 0.00, 0, '2019-09-27 16:52:28', '2019-09-27 16:52:28', 0, '', '2019-09-27 16:52:28', 10, '2019-09-27 16:52:28', 10, '', 1, '', '', '', '', '', 0, 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
