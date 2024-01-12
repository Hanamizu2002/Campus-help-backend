/*
 Navicat Premium Data Transfer

 Source Server         : Docker
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 192.168.31.5:3306
 Source Schema         : campus_help

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 12/01/2024 16:34:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名',
  `school_id` int NOT NULL COMMENT '学校id',
  `dept_id` int NOT NULL COMMENT '系别id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_CLASS_SCHOOL` (`school_id`) USING BTREE,
  KEY `FK_CLASS_DEPT` (`dept_id`) USING BTREE,
  CONSTRAINT `FK_CLASS_DEPT` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_CLASS_SCHOOL` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
BEGIN;
INSERT INTO `class` (`id`, `name`, `school_id`, `dept_id`) VALUES (16, '软件工程', 13, 1);
INSERT INTO `class` (`id`, `name`, `school_id`, `dept_id`) VALUES (18, '动漫制作技术', 13, 15);
COMMIT;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '系别id',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系别名称',
  `school_id` int NOT NULL COMMENT '学校id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_DEPT_SCHOOL` (`school_id`) USING BTREE,
  CONSTRAINT `FK_DEPT_SCHOOL` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dept
-- ----------------------------
BEGIN;
INSERT INTO `dept` (`id`, `name`, `school_id`) VALUES (1, '信息工程学院', 13);
INSERT INTO `dept` (`id`, `name`, `school_id`) VALUES (15, '高职学院', 13);
COMMIT;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '学校id',
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学校名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of school
-- ----------------------------
BEGIN;
INSERT INTO `school` (`id`, `name`) VALUES (13, '西安欧亚学院');
COMMIT;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `publish_user_id` int NOT NULL COMMENT '用户发布id',
  `accept_user_id` int DEFAULT NULL COMMENT '接受任务用户id',
  `user_school_id` int NOT NULL COMMENT '用户所在的学校id',
  `reward` double DEFAULT '0' COMMENT '任务奖励',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `order_time` timestamp NULL DEFAULT NULL COMMENT '接单时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `task_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务标题',
  `task_context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务内容',
  `state` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_TASK_PUBLISH_USER` (`publish_user_id`) USING BTREE,
  KEY `FK_TASK_ACCEPT_USER` (`accept_user_id`) USING BTREE,
  KEY `FK_TASK_USER_SCHOOL` (`user_school_id`) USING BTREE,
  CONSTRAINT `FK_TASK_ACCEPT_USER` FOREIGN KEY (`accept_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_TASK_PUBLISH_USER` FOREIGN KEY (`publish_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_TASK_USER_SCHOOL` FOREIGN KEY (`user_school_id`) REFERENCES `school` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
INSERT INTO `task` (`id`, `publish_user_id`, `accept_user_id`, `user_school_id`, `reward`, `create_time`, `order_time`, `end_time`, `task_title`, `task_context`, `state`) VALUES (19, 13, 14, 13, 20, '2023-12-08 10:40:02', '2023-12-08 10:40:54', '2023-12-08 10:41:34', 'test', '测试测试', 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `student_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `password` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `school_id` int NOT NULL COMMENT '学校id',
  `dept_id` int DEFAULT NULL COMMENT '系别id',
  `class_id` int DEFAULT NULL COMMENT '班级id',
  `sex` int DEFAULT '0' COMMENT '性别',
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `balance` double DEFAULT '0' COMMENT '余额',
  `state` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_USER_SCHOOL` (`school_id`) USING BTREE,
  KEY `FK_USER_DEPT` (`dept_id`) USING BTREE,
  KEY `FK_USER_CLASS` (`class_id`) USING BTREE,
  CONSTRAINT `FK_USER_CLASS` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_USER_DEPT` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_USER_SCHOOL` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `student_id`, `password`, `phone`, `school_id`, `dept_id`, `class_id`, `sex`, `username`, `create_time`, `balance`, `state`) VALUES (13, '20140702152025', '202cb962ac59075b964b07152d234b70', '15603443334', 13, 1, 16, 0, '麻曦彤', '2023-12-08 09:58:05', 1500, 0);
INSERT INTO `user` (`id`, `student_id`, `password`, `phone`, `school_id`, `dept_id`, `class_id`, `sex`, `username`, `create_time`, `balance`, `state`) VALUES (14, '20140882133075', '202cb962ac59075b964b07152d234b70', '13803471204', 13, 15, 18, 1, '纪天瑞', '2023-12-08 10:01:00', 50, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
