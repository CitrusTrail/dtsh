/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : dtsh

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 23/05/2024 18:00:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50),
  `password` varchar(50),
  `address` text,
  `tel` varchar(50),
  `point` int(11), -- 积分
  `avatar` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123456', '福建省福州市闽侯县福州大学', '13500000000', 233, 'http://localhost:8099/upload/picture/1.png');
INSERT INTO `user` VALUES (2, 'lisi', '123456', '福建省福州市闽侯县福州大学', '13900000000', 999, 'http://localhost:8099/upload/picture/2.png');
INSERT INTO `user` VALUES (3, 'wangwu', '123456', '福建省福州市闽侯县福州大学', '13700000000', 999, 'http://localhost:8099/upload/picture/3.png');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50),
  `password` varchar(50),
  `avatar` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 'http://localhost:8099/upload/picture/4.png');
INSERT INTO `admin` VALUES (2, 'root', '123456', 'http://localhost:8099/upload/picture/5.png');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `description` text,
  `carbon` decimal(10, 2),  -- 减少的碳排放量
  `point` int(11), -- 积分
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '骑行1公里', '使用出行功能完成骑行1公里', 1.99, 999);
INSERT INTO `task` VALUES (2, '垃圾分类知识问答', '在规定的时间内完成垃圾分类知识问答题', 0.00, 999);

-- ----------------------------
-- Table structure for user_task
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `completed_time` datetime,
  PRIMARY KEY (`user_id`, `task_id`) USING BTREE
);

-- ----------------------------
-- Records of user_task
-- ----------------------------
INSERT INTO `user_task` VALUES (1, 1, '2024-06-01 00:00:00');
INSERT INTO `user_task` VALUES (2, 1, '2024-06-01 00:00:00');

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `start_time` datetime,
  `end_time` datetime,
  `total_time` int(11),
  `mode` varchar(50),  -- 出行方式，如：步行、骑行、公交等
  `carbon` decimal(10, 2),  -- 此次出行的减少的碳排放量
  `description` text,  -- 路线详情或其他备注
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel
-- ----------------------------
INSERT INTO `travel` VALUES (1, 1, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '骑行', 999.99, '从旗山湖公园出发，途径金山公园，最后到达飞凤山公园');
INSERT INTO `travel` VALUES (2, 2, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '步行', 999.99, NULL);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `description` text,
  `creator` int(11),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1, '生态大家庭', '我们都爱大自然！', 1);
INSERT INTO `group` VALUES (2, '低碳2群', '这里聚集了对低碳感兴趣的友友', 1);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES (1, 1);
INSERT INTO `user_group` VALUES (2, 1);

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (1, 1, 1, '666', '2024-05-01 12:00:00');
INSERT INTO `chat` VALUES (2, 1, 2, '我认为。。。', '2024-05-01 12:00:00');

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content` text,
  `time` datetime NOT NULL,
  `likes` INT DEFAULT 0,  -- 点赞数
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 1, '今天又是低碳出行的一天', '2024-05-01 12:00:00', 2);
INSERT INTO `share` VALUES (2, 2, '风景真美！', '2024-05-01 12:00:00', 4);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50),
  `point` int(11),
  `stock` int(11), -- 库存
  `description` text,  -- 备注
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '玩偶', 999, 999, '吉祥物');
INSERT INTO `goods` VALUES (2, '水杯', 999, 999, '印有绿叶图案的水杯');

-- ----------------------------
-- Table structure for buy
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `time` datetime,
  `point` int(11),
  `num` int(11),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES (1, 1, 1, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (2, 1, 1, '2024-05-01 12:00:00', 999, 999);

-- ----------------------------
-- Table structure for user_carbon
-- ----------------------------
DROP TABLE IF EXISTS `user_carbon`;
CREATE TABLE `user_carbon` (
  `user_id` int(11) NOT NULL,
  `date` date,
  `carbon` decimal(10, 2),
  PRIMARY KEY (`user_id`, `date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_carbon
-- ----------------------------
INSERT INTO `user_carbon` VALUES (1, '2024-05-01', 999.99);
INSERT INTO `user_carbon` VALUES (1, '2024-05-02', 999.99);

SET FOREIGN_KEY_CHECKS = 1;
