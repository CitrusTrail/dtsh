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
  `carbon` decimal(10, 2),
  `avatar` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123456', '福建省福州市闽侯县福州大学', '13500000000', 233, 999.99, '/server/image/avatar/1.png');
INSERT INTO `user` VALUES (2, 'lisi', '123456', '福建省福州市闽侯县福州大学', '13900000000', 999, 999.99, '/server/image/avatar/2.png');
INSERT INTO `user` VALUES (3, 'wangwu', '123456', '福建省福州市闽侯县福州大学', '13700000000', 999, 999.99, '/server/image/avatar/3.png');

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
INSERT INTO `admin` VALUES (1, 'admin', '123456', '/server/image/avatar/4.png');
INSERT INTO `admin` VALUES (2, 'root', '123456', '/server/image/avatar/5.png');

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
  `image` varchar(255),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, '骑行1公里', '使用出行功能完成骑行1公里', 1.99, 999,'/server/image/task/img.png');
INSERT INTO `task` VALUES (2, '垃圾分类知识问答', '在规定的时间内完成垃圾分类知识问答题', 1.99, 999,'/server/image/task/img.png');
INSERT INTO `task` VALUES (3, '“绿意盎然”家庭节能挑战赛', '“绿意盎然”家庭节能挑战赛是一项为期一个月的家庭低碳生活实践活动。参与的家庭将在挑战期间尽可能减少能源消耗，如电力、天然气等，同时记录并分享他们的节能方法和成果。活动结束后，将根据节能效果评选出优胜家庭，并颁发奖品以资鼓励。此活动旨在提高家庭成员的节能意识，并帮助他们养成低碳生活的好习惯。', 1.99, 999,'/server/image/task/img.png');
INSERT INTO `task` VALUES (4, '绿色出行周', '“绿色出行周”活动鼓励市民在一周内采用低碳出行方式，如步行、骑行、乘坐公共交通等，减少私家车使用。活动期间，将设立多个绿色出行站点，提供自行车租赁、公交卡充值等便利服务，并举办相关宣传活动，如环保讲座、绿色出行摄影展等。此活动旨在提高市民对绿色出行的认识，并推动形成低碳出行的社会风尚。', 1.99, 999,'/server/image/task/img.png');
INSERT INTO `task` VALUES (5, '环保创意市集', '“环保创意市集”是一个集环保理念、创意设计和手工艺术于一体的活动。市集上，将展示和销售各种低碳环保的生活用品、艺术品和创意作品，如手工肥皂、再生纸制品、旧物改造的家居饰品等。同时，还将邀请环保专家和手工艺人进行现场教学，传授环保知识和手工制作技巧。此活动旨在推广环保理念，激发公众的创意和环保意识。', 1.99, 999,'/server/image/task/img.png');
INSERT INTO `task` VALUES (6, '“零废弃”生活体验营', '“零废弃”生活体验营是一个为期几天的低碳生活体验活动。参与者将在活动期间学习并实践零废弃的生活方式，如垃圾分类、厨余堆肥、自制环保清洁剂等。活动还将组织参观零废弃社区和企业，了解他们的实践经验和成果。此活动旨在让参与者深入了解零废弃生活的可行性和好处，并鼓励他们将所学应用到日常生活中。', 1.99, 999,'/server/image/task/img.png');
INSERT INTO `task` VALUES (7, '低碳校园行', '“低碳校园行”是一个面向学校学生的低碳生活宣传活动。活动将通过举办讲座、展览、互动游戏等形式，向学生介绍低碳生活的重要性和实践方法。同时，还将鼓励学生参与校园节能、节水、节电等实践活动，培养他们的环保意识和社会责任感。此活动旨在让学生在校园中体验和实践低碳生活，为未来的环保事业培养更多的人才。', 1.99, 999,'/server/image/task/img.png');

-- ----------------------------
-- Table structure for user_task
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `completed_time` datetime,
  `image` varchar(255),
  `status` varchar(255),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_task
-- ----------------------------
INSERT INTO `user_task` VALUES (1, 1, 1, '2024-06-01 00:00:00', '/server/image/usertask/1.png', null);
INSERT INTO `user_task` VALUES (2, 2, 1, '2024-06-01 00:00:00', '/server/image/usertask/1.png', null);
INSERT INTO `user_task` VALUES (3, 3, 1, '2024-06-01 00:00:00', '/server/image/usertask/1.png', null);
INSERT INTO `user_task` VALUES (4, 2, 2, '2024-06-01 00:00:00', '/server/image/usertask/1.png', null);
INSERT INTO `user_task` VALUES (5, 1, 2, null, null, null);
INSERT INTO `user_task` VALUES (6, 3, 2, null, null, null);
INSERT INTO `user_task` VALUES (7, 1, 6, '2024-06-01 00:00:00', '/server/image/usertask/1.png', '通过');
INSERT INTO `user_task` VALUES (8, 2, 6, '2024-06-01 00:00:00', '/server/image/usertask/1.png', '不通过');
INSERT INTO `user_task` VALUES (9, 1, 7, null, null, null);

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel
-- ----------------------------
INSERT INTO `travel` VALUES (1, 1, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '骑行', 999.99, '从旗山湖公园出发，途径金山公园，最后到达飞凤山公园');
INSERT INTO `travel` VALUES (2, 2, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '步行', 999.99, NULL);
INSERT INTO `travel` VALUES (3, 2, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '地铁', 999.99, NULL);
INSERT INTO `travel` VALUES (4, 3, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '公交', 999.99, NULL);
INSERT INTO `travel` VALUES (5, 2, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '骑行', 999.99, NULL);
INSERT INTO `travel` VALUES (6, 4, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '公交', 999.99, NULL);
INSERT INTO `travel` VALUES (7, 4, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '公交', 999.99, NULL);
INSERT INTO `travel` VALUES (8, 4, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '骑行', 999.99, NULL);
INSERT INTO `travel` VALUES (9, 4, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '步行', 999.99, NULL);
INSERT INTO `travel` VALUES (10, 4, '2024-06-01 00:00:00', '2024-06-01 12:00:00', 999, '步行', 999.99, NULL);

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content` text,
  `time` datetime NOT NULL,
  `image` varchar(255),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 1, '今天又是低碳出行的一天', '2024-05-01 12:00:00', '/server/image/usertask/1.png');
INSERT INTO `share` VALUES (2, 2, '风景真美！', '2024-05-01 12:00:00', '/server/image/usertask/1.png');

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
  `image` varchar(255),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '玩偶', 999, 999, '吉祥物','/server/image/goods/1.png');
INSERT INTO `goods` VALUES (2, '水杯', 999, 999, '印有绿叶图案的水杯','/server/image/goods/2.png');
INSERT INTO `goods` VALUES (3, '灯泡', 999, 999, '绿源节能灯泡采用先进的LED技术，相较于传统白炽灯，能够节省高达85%的能源。它的设计寿命长，减少了频繁更换灯泡的需求，同时也降低了电子垃圾的产生。此外，它的光线柔和舒适，为家庭提供环保且健康的照明环境。','/server/image/goods/3.png');
INSERT INTO `goods` VALUES (4, '笔记本', 999, 999, '碳迹竹制笔记本使用可再生的竹子作为原材料，减少了对树木的砍伐。每一页纸张都经过特殊处理，确保书写流畅且易于保存。这款笔记本不仅环保，而且其独特的竹质纹理也为使用者带来了独特的书写体验。','/server/image/goods/4.png');
INSERT INTO `goods` VALUES (5, '背包', 999, 999, '绿途环保背包采用可回收材料制成，如再生聚酯纤维，减少了新材料的开采和加工过程。背包设计有多个口袋和隔层，方便用户整理和携带物品。此外，它的肩带和背部都有舒适的透气设计，确保用户在长时间背负时仍能保持舒适。','/server/image/goods/5.png');
INSERT INTO `goods` VALUES (6, '绿荫植物盆栽', 999, 999, '绿荫植物盆栽不仅能为室内增添绿色，还有助于净化空气，吸收二氧化碳并释放氧气。这些盆栽植物种类繁多，包括绿萝、吊兰等易于养护的品种，适合放在办公室或家中。通过养植这些植物，人们可以更加接近自然，同时也为环保贡献一份力量。','/server/image/goods/6.png');
INSERT INTO `goods` VALUES (7, '大米', 999, 999, '一袋东北大米','/server/image/goods/7.png');
INSERT INTO `goods` VALUES (8, '花生油', 999, 999, '家庭炒菜必备','/server/image/goods/8.png');

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES (1, 1, 1, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (2, 1, 2, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (3, 1, 3, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (4, 2, 1, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (5, 2, 4, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (6, 3, 1, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (7, 2, 4, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (8, 3, 8, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (9, 1, 6, '2024-05-01 12:00:00', 999, 999);
INSERT INTO `buy` VALUES (10, 2, 6, '2024-05-01 12:00:00', 999, 999);

SET FOREIGN_KEY_CHECKS = 1;
