/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : blogsystem

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 11/04/2023 18:44:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `bid` int NOT NULL COMMENT '博客id',
  `uid` int NOT NULL COMMENT '用户id',
  `bno` int NOT NULL COMMENT '用户博客排序',
  `btitle` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '博客标题',
  `bcontent` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '博客内容',
  `bpublished` bit(1) NOT NULL COMMENT '博客是否发布',
  `create_time` datetime(6) NOT NULL COMMENT '博客创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '博客更新时间',
  `goodNum` int NOT NULL COMMENT '点赞量',
  `collectNum` int NOT NULL COMMENT '收藏量',
  `state` bit(1) NOT NULL COMMENT '审核状态',
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_blog
-- ----------------------------

-- ----------------------------
-- Table structure for t_collect
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect`  (
  `uid` int NOT NULL COMMENT '用户id',
  `bid` int NOT NULL COMMENT '博客id',
  PRIMARY KEY (`uid`, `bid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_collect
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `cid` int NOT NULL COMMENT '评论id',
  `user_id` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论用户id',
  `bid` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '博客id',
  `parent_comment_id` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论父评论id',
  `content` tinytext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_communicate
-- ----------------------------
DROP TABLE IF EXISTS `t_communicate`;
CREATE TABLE `t_communicate`  (
  `content` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '内容',
  `uid1` int NOT NULL COMMENT '用户1',
  `uid2` int NOT NULL COMMENT '用户2',
  PRIMARY KEY (`uid1`, `uid2`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_communicate
-- ----------------------------
INSERT INTO `t_communicate` VALUES ('{type=0, sender=0, content=1}{type=0, sender=0, content=2}', 1, 2);

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow`  (
  `uid_self` int NOT NULL COMMENT '用户id',
  `uid_follow` int NOT NULL COMMENT '关注用户id',
  PRIMARY KEY (`uid_self`, `uid_follow`) USING BTREE,
  INDEX `uid_follow`(`uid_follow` ASC) USING BTREE,
  CONSTRAINT `uid_follow` FOREIGN KEY (`uid_follow`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid_self` FOREIGN KEY (`uid_self`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_follow
-- ----------------------------
INSERT INTO `t_follow` VALUES (1, 2);
INSERT INTO `t_follow` VALUES (1, 3);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `pwd` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '小美', '123456789', ' sf', NULL);
INSERT INTO `t_user` VALUES (2, '张三', '3', '3231s5f', '');
INSERT INTO `t_user` VALUES (3, '李四', '5661', 'sfsf', NULL);
INSERT INTO `t_user` VALUES (5, 'wsvg', '123456789', '3229600691@qq.com', NULL);
INSERT INTO `t_user` VALUES (6, '123', '123', '1234567', NULL);

SET FOREIGN_KEY_CHECKS = 1;
