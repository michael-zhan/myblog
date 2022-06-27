/*
 Navicat MySQL Data Transfer

 Source Server         : 本机mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 27/06/2022 23:29:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `desrciption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `view` int(11) NULL DEFAULT NULL,
  `author` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1, '测试文档', '这是一篇测试文档', 'blog', '2022-06-27 16:03:15', 1, '18720167619');
INSERT INTO `t_blog` VALUES (2, '测试文档', '这是第二篇测试文档', 'blog', '2022-06-27 16:32:15', 1, '18720167619');
INSERT INTO `t_blog` VALUES (3, '测试文档', '这是第三篇测试文档', 'blog', '2021-06-27 16:32:15', 1, '18720167619');
INSERT INTO `t_blog` VALUES (4, '测试文档', '这是第四篇测试文档', 'blog', '2021-06-27 16:55:15', 1, '18720167619');
INSERT INTO `t_blog` VALUES (5, '测试文档', '这是第五篇测试文档', 'blog', '2020-06-27 16:55:15', 1, '18720167619');

-- ----------------------------
-- Table structure for t_friendship
-- ----------------------------
DROP TABLE IF EXISTS `t_friendship`;
CREATE TABLE `t_friendship`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `friend_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_friendship
-- ----------------------------

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'0',
  `sendtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` bit(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('13870257545', 'zyh', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL);
INSERT INTO `t_user` VALUES ('18720167619', 'michael', 'a7f07f38fd64c670c18b5419a733df51', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
