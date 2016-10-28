/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : admin

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-09-21 11:03:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_operator_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operator_info`;
CREATE TABLE `tbl_operator_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_name` varchar(100) NOT NULL,
  `operator_company` varchar(100) DEFAULT NULL,
  `operator_email` varchar(100) DEFAULT NULL,
  `operator_phone` varchar(20) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `account_type` char(2) DEFAULT NULL COMMENT '账号角色，0为管理员，1为cp,2为sp,3为渠道',
  `account_type_id` int(11) DEFAULT NULL COMMENT '对应渠道、cp、sp的编号',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0正常，1关闭',
  `remark` varchar(1024) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_operator_info
-- ----------------------------
INSERT INTO `tbl_operator_info` VALUES ('11', '权限管理员', '', '', '', 'admin', '60242dd897eaf42b90bbad113e775e65', '0', '0', '0', '', '2012-09-17 14:29:23', '2012-12-16 14:33:41');

-- ----------------------------
-- Table structure for tbl_operator_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_operator_role`;
CREATE TABLE `tbl_operator_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_operator_role
-- ----------------------------
INSERT INTO `tbl_operator_role` VALUES ('104', '11', '32', '2012-12-16 14:33:41', null);
INSERT INTO `tbl_operator_role` VALUES ('105', '11', '30', '2012-12-16 14:33:41', null);
INSERT INTO `tbl_operator_role` VALUES ('106', '11', '23', '2012-12-16 14:33:41', null);

-- ----------------------------
-- Table structure for tbl_resource_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_resource_info`;
CREATE TABLE `tbl_resource_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(50) NOT NULL,
  `type` char(1) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `level` int(11) NOT NULL COMMENT '排列，越小越前',
  `base_url` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_resource_info
-- ----------------------------
INSERT INTO `tbl_resource_info` VALUES ('16', '权限管理', '1', 'permission.do', null, '4', null, '2012-09-14 11:58:28', '2012-09-17 21:58:02');
INSERT INTO `tbl_resource_info` VALUES ('17', '资源管理', '2', 'resourceQuery.do', '16', '1', null, '2012-09-14 11:59:27', '2012-09-14 11:59:53');
INSERT INTO `tbl_resource_info` VALUES ('18', '角色管理', '2', 'roleQuery.do', '16', '2', null, '2012-09-14 11:59:48', null);
INSERT INTO `tbl_resource_info` VALUES ('19', '用户管理', '2', 'operatorQuery.do', '16', '3', null, '2012-09-14 12:00:14', '2012-09-17 23:39:08');
INSERT INTO `tbl_resource_info` VALUES ('38', '个人资料', '1', 'person.do', null, '10', null, '2012-09-18 19:39:48', null);
INSERT INTO `tbl_resource_info` VALUES ('39', '我的信息', '2', 'personalQuery.do', '38', '0', null, '2012-09-18 19:40:56', '2012-09-18 19:47:52');

-- ----------------------------
-- Table structure for tbl_resource_operate
-- ----------------------------
DROP TABLE IF EXISTS `tbl_resource_operate`;
CREATE TABLE `tbl_resource_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `resource_id` int(11) NOT NULL COMMENT '资源编号',
  `operate` varchar(10) NOT NULL COMMENT '操作类型',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_resource_operate
-- ----------------------------
INSERT INTO `tbl_resource_operate` VALUES ('85', '18', 'QUERY', '2012-09-14 11:59:48', null);
INSERT INTO `tbl_resource_operate` VALUES ('86', '18', 'ADD', '2012-09-14 11:59:48', null);
INSERT INTO `tbl_resource_operate` VALUES ('87', '18', 'EDIT', '2012-09-14 11:59:48', null);
INSERT INTO `tbl_resource_operate` VALUES ('88', '18', 'DELETE', '2012-09-14 11:59:48', null);
INSERT INTO `tbl_resource_operate` VALUES ('89', '17', 'QUERY', '2012-09-14 11:59:53', null);
INSERT INTO `tbl_resource_operate` VALUES ('90', '17', 'ADD', '2012-09-14 11:59:53', null);
INSERT INTO `tbl_resource_operate` VALUES ('91', '17', 'EDIT', '2012-09-14 11:59:53', null);
INSERT INTO `tbl_resource_operate` VALUES ('92', '17', 'DELETE', '2012-09-14 11:59:53', null);
INSERT INTO `tbl_resource_operate` VALUES ('97', '19', 'QUERY', '2012-09-17 23:39:08', null);
INSERT INTO `tbl_resource_operate` VALUES ('98', '19', 'ADD', '2012-09-17 23:39:08', null);
INSERT INTO `tbl_resource_operate` VALUES ('99', '19', 'EDIT', '2012-09-17 23:39:08', null);
INSERT INTO `tbl_resource_operate` VALUES ('100', '19', 'DELETE', '2012-09-17 23:39:08', null);
INSERT INTO `tbl_resource_operate` VALUES ('119', '39', 'QUERY', '2012-09-18 19:47:52', null);
INSERT INTO `tbl_resource_operate` VALUES ('120', '39', 'EDIT', '2012-09-18 19:47:52', null);

-- ----------------------------
-- Table structure for tbl_role_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_info`;
CREATE TABLE `tbl_role_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role_info
-- ----------------------------
INSERT INTO `tbl_role_info` VALUES ('23', '权限分配管理员', '2012-09-17 14:28:47', '2012-09-26 15:26:03');
INSERT INTO `tbl_role_info` VALUES ('30', '个人信息管理员', '2012-09-18 19:42:28', null);

-- ----------------------------
-- Table structure for tbl_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_resource`;
CREATE TABLE `tbl_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role_resource
-- ----------------------------
INSERT INTO `tbl_role_resource` VALUES ('62', '30', '38', '2012-09-18 19:42:28', null);
INSERT INTO `tbl_role_resource` VALUES ('63', '30', '39', '2012-09-18 19:42:28', null);
INSERT INTO `tbl_role_resource` VALUES ('113', '23', '16', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource` VALUES ('114', '23', '19', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource` VALUES ('115', '23', '18', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource` VALUES ('116', '23', '17', '2012-09-26 15:26:03', null);

-- ----------------------------
-- Table structure for tbl_role_resource_operate
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_resource_operate`;
CREATE TABLE `tbl_role_resource_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `operate` varchar(10) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role_resource_operate
-- ----------------------------
INSERT INTO `tbl_role_resource_operate` VALUES ('103', '30', '39', 'QUERY', '2012-09-18 19:42:28', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('104', '30', '39', 'EDIT', '2012-09-18 19:42:28', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('195', '23', '19', 'QUERY', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('196', '23', '19', 'ADD', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('197', '23', '19', 'EDIT', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('198', '23', '19', 'DELETE', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('199', '23', '18', 'QUERY', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('200', '23', '18', 'ADD', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('201', '23', '18', 'EDIT', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('202', '23', '18', 'DELETE', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('203', '23', '17', 'QUERY', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('204', '23', '17', 'ADD', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('205', '23', '17', 'EDIT', '2012-09-26 15:26:03', null);
INSERT INTO `tbl_role_resource_operate` VALUES ('206', '23', '17', 'DELETE', '2012-09-26 15:26:03', null);
