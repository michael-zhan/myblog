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

 Date: 30/06/2022 01:31:55
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
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `view` int(11) NULL DEFAULT NULL,
  `author` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1, '文档一', '这是第一篇文档', '文一', '2022-06-23 15:15:15', 3, '18720167619', 2);
INSERT INTO `t_blog` VALUES (2, '文档二', '这是第二篇文档', '文二', '2022-06-23 15:15:15', 2, '18720167619', 2);
INSERT INTO `t_blog` VALUES (3, '文档三', '这是第三篇文档', '文三', '2022-06-23 15:15:35', 3, '18720167619', 1);
INSERT INTO `t_blog` VALUES (4, '文档四', '这是第四篇文档', '文四', '2022-06-23 15:15:45', 2, '18720167619', 3);
INSERT INTO `t_blog` VALUES (5, '文档五', '这是第五篇文档', '文五', '2022-06-23 15:15:55', 2, '18720167619', 3);
INSERT INTO `t_blog` VALUES (6, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (7, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (8, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (9, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (10, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (11, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (12, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (13, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);
INSERT INTO `t_blog` VALUES (14, '文档N', '这是第N篇文档', '文N', '2022-06-23 15:15:15', 0, '18720167619', 2);

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
  `sendtime` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (1, '18720167619', '13870257545', b'0', '2022-06-29 17:16:52');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, 'a');
INSERT INTO `t_type` VALUES (2, 'b');
INSERT INTO `t_type` VALUES (3, 'c');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('13870257545', 'zyh', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL);
INSERT INTO `t_user` VALUES ('18720167619', 'michael', 'a7f07f38fd64c670c18b5419a733df51', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
