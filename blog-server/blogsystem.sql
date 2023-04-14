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

 Date: 14/04/2023 09:28:35
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
INSERT INTO `t_blog` VALUES (1, 1, 1, '我的第一篇博客', '这是我的第一篇博客，我想与大家分享我的学习经历...', b'1', '2023-04-01 10:00:00.000000', '2023-04-01 10:00:00.000000', 10, 5, b'1');
INSERT INTO `t_blog` VALUES (2, 1, 2, '学习Python的心得', '我最近学习了Python，发现它是一门非常有趣的编程语言...', b'1', '2023-04-02 10:00:00.000000', '2023-04-02 10:00:00.000000', 15, 7, b'1');
INSERT INTO `t_blog` VALUES (3, 2, 1, '如何提高编程能力', '作为一名程序员，如何提高自己的编程能力至关重要...', b'1', '2023-04-03 10:00:00.000000', '2023-04-03 10:00:00.000000', 20, 10, b'1');
INSERT INTO `t_blog` VALUES (4, 3, 1, '前端开发的一些建议', '前端开发是一项非常重要的工作，我在此给大家分享一些经验...', b'0', '2023-04-04 10:00:00.000000', '2023-04-04 10:00:00.000000', 5, 2, b'0');
INSERT INTO `t_blog` VALUES (5, 4, 1, '数据库优化技巧', '数据库优化对于提高程序性能非常关键，这里有一些实用技巧...', b'1', '2023-04-05 10:00:00.000000', '2023-04-05 10:00:00.000000', 25, 12, b'1');

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
INSERT INTO `t_collect` VALUES (1, 3);
INSERT INTO `t_collect` VALUES (2, 1);
INSERT INTO `t_collect` VALUES (2, 5);
INSERT INTO `t_collect` VALUES (3, 1);
INSERT INTO `t_collect` VALUES (4, 2);
INSERT INTO `t_collect` VALUES (5, 2);
INSERT INTO `t_collect` VALUES (6, 1);

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
INSERT INTO `t_comment` VALUES (1, '2', '1', '0', '非常有用的文章，谢谢分享！', '2023-04-01 12:00:00');
INSERT INTO `t_comment` VALUES (2, '3', '1', '0', '写得很好，期待您的下一篇博客！', '2023-04-01 13:00:00');
INSERT INTO `t_comment` VALUES (3, '4', '1', '1', '同意，这篇文章让我学到了很多！', '2023-04-01 14:00:00');
INSERT INTO `t_comment` VALUES (4, '5', '2', '0', '学习Python确实很有趣，感谢分享！', '2023-04-02 11:00:00');
INSERT INTO `t_comment` VALUES (5, '6', '2', '0', '我也在学Python，希望能向您请教！', '2023-04-02 12:30:00');
INSERT INTO `t_comment` VALUES (6, '1', '3', '0', '很实用的建议，谢谢！', '2023-04-03 15:00:00');

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
INSERT INTO `t_communicate` VALUES ('你好，最近在学什么技术呢？', 1, 2);
INSERT INTO `t_communicate` VALUES ('好的，期待与你一起学习和进步！', 1, 3);
INSERT INTO `t_communicate` VALUES ('我最近在学习前端开发，你呢？', 2, 1);
INSERT INTO `t_communicate` VALUES ('我也在学前端开发，我们可以一起交流！', 3, 1);

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
INSERT INTO `t_user` VALUES (1, '张三', 'zhangsan123', 'zhangsan@example.com', '13800138001');
INSERT INTO `t_user` VALUES (2, '李四', 'lisi123', 'lisi@example.com', '13800138002');
INSERT INTO `t_user` VALUES (3, '王五', 'wangwu123', 'wangwu@example.com', '13800138003');
INSERT INTO `t_user` VALUES (4, '赵六', 'zhaoliu123', 'zhaoliu@example.com', '13800138004');
INSERT INTO `t_user` VALUES (5, '陈七', 'chenqi123', 'chenqi@example.com', '13800138005');
INSERT INTO `t_user` VALUES (6, '钱八', 'qianba123', 'qianba@example.com', '13800138006');

SET FOREIGN_KEY_CHECKS = 1;
