/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : club

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 17/07/2022 13:08:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_activity
-- ----------------------------
DROP TABLE IF EXISTS `tbl_activity`;
CREATE TABLE `tbl_activity`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `activity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `price` int(0) NOT NULL COMMENT '报名费',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动介绍',
  `start` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动开始结束时间',
  `ctime` datetime(0) NOT NULL COMMENT '发布时间',
  `uid` int(0) NOT NULL COMMENT '发起人，用户id',
  `hot` int(0) NOT NULL COMMENT '热门活动 1 / 0',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动宣传图，图片保存地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_activity_tbl_user`(`uid`) USING BTREE,
  CONSTRAINT `FK_tbl_activity_tbl_user` FOREIGN KEY (`uid`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_activity
-- ----------------------------
INSERT INTO `tbl_activity` VALUES (14, '实训', 1, '在家实训', '2022年7月13号开始 - 7月18号结束', '2022-07-13 14:41:46', 1, 1, '/club/logo/java.png');
INSERT INTO `tbl_activity` VALUES (16, '操场野餐', 20, '开吃咯', '9月13日', '2022-07-15 08:29:18', 1, 1, '/club/logo/野餐.png');
INSERT INTO `tbl_activity` VALUES (17, '爬笔架山', 1, '比泰山小', '8月15日', '2022-07-17 11:40:07', 1, 1, '/club/logo/爬山.png');

-- ----------------------------
-- Table structure for tbl_buy
-- ----------------------------
DROP TABLE IF EXISTS `tbl_buy`;
CREATE TABLE `tbl_buy`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cid` int(0) NULL DEFAULT NULL COMMENT '顾客id',
  `gid` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `amount` int(0) NULL DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_buy
-- ----------------------------
INSERT INTO `tbl_buy` VALUES (2, 1, 1, 2);
INSERT INTO `tbl_buy` VALUES (3, 1, 2, 3);
INSERT INTO `tbl_buy` VALUES (4, 2, 3, 1);
INSERT INTO `tbl_buy` VALUES (5, 1, 3, 1);
INSERT INTO `tbl_buy` VALUES (6, 1, 4, 2);
INSERT INTO `tbl_buy` VALUES (7, 1, 5, 1);
INSERT INTO `tbl_buy` VALUES (8, 1, 3, 4);
INSERT INTO `tbl_buy` VALUES (9, 1, 4, 3);
INSERT INTO `tbl_buy` VALUES (10, 1, 3, 1);

-- ----------------------------
-- Table structure for tbl_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_comment`;
CREATE TABLE `tbl_comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL DEFAULT 0 COMMENT '评论用户id',
  `ptime` datetime(0) NOT NULL COMMENT '发表时间',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `pid` int(0) NOT NULL COMMENT '被评论的评论id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_comments_tbl_postings`(`pid`) USING BTREE,
  INDEX `FK_tbl_comments_tbl_user`(`uid`) USING BTREE,
  CONSTRAINT `FK_tbl_comments_tbl_postings` FOREIGN KEY (`pid`) REFERENCES `tbl_postings` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_tbl_comments_tbl_user` FOREIGN KEY (`uid`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_comment
-- ----------------------------
INSERT INTO `tbl_comment` VALUES (3, 1, '2022-07-17 08:02:35', '111', 2);
INSERT INTO `tbl_comment` VALUES (13, 1, '2022-07-17 09:40:33', '1', 3);
INSERT INTO `tbl_comment` VALUES (14, 1, '2022-07-17 09:40:35', '2', 3);
INSERT INTO `tbl_comment` VALUES (15, 1, '2022-07-17 09:40:38', '3', 3);
INSERT INTO `tbl_comment` VALUES (16, 1, '2022-07-17 09:40:42', '4', 3);
INSERT INTO `tbl_comment` VALUES (17, 1, '2022-07-17 09:40:46', '5', 3);
INSERT INTO `tbl_comment` VALUES (19, 1, '2022-07-17 12:04:19', '你好', 9);
INSERT INTO `tbl_comment` VALUES (22, 1, '2022-07-17 12:55:57', '你好', 12);

-- ----------------------------
-- Table structure for tbl_goods
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods`;
CREATE TABLE `tbl_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` float(10, 2) NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` int(0) NULL DEFAULT NULL,
  `amount` int(0) NULL DEFAULT NULL,
  `photo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_goods
-- ----------------------------
INSERT INTO `tbl_goods` VALUES (1, '笔记本电脑', 888.00, '笔记本电脑，外观良好无破损，支持试用，先到先得笔记本电脑，外观良好无破损，支持试用，先到先得笔记本电脑，外观良好无破损，支持试用，先到先得', 1, 13, '/club/logo/笔记本.jpg');
INSERT INTO `tbl_goods` VALUES (2, '考研资料', 5.00, '全新考研资料，摆烂了不学了低价出', 1, 96, '/club/logo/考研资料.png');
INSERT INTO `tbl_goods` VALUES (3, '手机', 999.00, '女生自用，9.999999999999999成新', 1, 992, '/club/logo/手机.png');
INSERT INTO `tbl_goods` VALUES (4, '羊毛衫', 50.00, '小牌羊毛衫，仅试穿，送货上门', 1, 5, '/club/logo/羊毛衫.jpg');
INSERT INTO `tbl_goods` VALUES (5, '棉衣', 200.00, '我的棉衣我的棉衣我的棉衣我的棉衣我的棉衣我的棉衣我的棉衣我的棉衣我的棉衣我的棉衣', 2, 10, '/club/logo/棉衣.jpg');

-- ----------------------------
-- Table structure for tbl_join
-- ----------------------------
DROP TABLE IF EXISTS `tbl_join`;
CREATE TABLE `tbl_join`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `myid` int(0) NULL DEFAULT NULL,
  `aid` int(0) NULL DEFAULT NULL COMMENT '外键 java实现',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tbl_join
-- ----------------------------
INSERT INTO `tbl_join` VALUES (14, 1, 14);

-- ----------------------------
-- Table structure for tbl_map
-- ----------------------------
DROP TABLE IF EXISTS `tbl_map`;
CREATE TABLE `tbl_map`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `etime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_map
-- ----------------------------
INSERT INTO `tbl_map` VALUES (2, '体育馆检测点', '36.007578', '120.125819', '13:00', '18:00');
INSERT INTO `tbl_map` VALUES (3, '大学生活动中心检测点', '36.006239', '120.12338', '13:00', '18:00');
INSERT INTO `tbl_map` VALUES (4, '校医院检测点', '36.002763', '120.118443', '0:00', '24:00');

-- ----------------------------
-- Table structure for tbl_postings
-- ----------------------------
DROP TABLE IF EXISTS `tbl_postings`;
CREATE TABLE `tbl_postings`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL DEFAULT 0 COMMENT '用户id',
  `ptime` datetime(0) NOT NULL COMMENT '发帖时间',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `clicks` int(0) NOT NULL COMMENT '点击量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_tbl_postings_tbl_user`(`uid`) USING BTREE,
  CONSTRAINT `FK_tbl_postings_tbl_user` FOREIGN KEY (`uid`) REFERENCES `tbl_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发表的帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_postings
-- ----------------------------
INSERT INTO `tbl_postings` VALUES (2, 1, '2022-07-16 15:28:14', 'ahhahahahahahhahaahahhahahahaahahahhahahahahahhahaahahhahahahaahahhaahhahahahahahhahaahahhahahahaahahhaahhahahahahahhahaahahhahahahaahahhaahhahahahahahhahaahahhahahahaahahhaha', 107);
INSERT INTO `tbl_postings` VALUES (3, 1, '2022-07-16 15:29:50', '你没事吧你没事', 92);
INSERT INTO `tbl_postings` VALUES (4, 3, '2022-07-17 07:50:30', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试', 18);
INSERT INTO `tbl_postings` VALUES (7, 1, '2022-07-17 09:21:43', '111', 2);
INSERT INTO `tbl_postings` VALUES (8, 1, '2022-07-17 09:24:36', '44444', 4);
INSERT INTO `tbl_postings` VALUES (9, 1, '2022-07-17 10:35:32', '天气不错', 2);
INSERT INTO `tbl_postings` VALUES (10, 1, '2022-07-17 12:09:48', '大家好', 2);
INSERT INTO `tbl_postings` VALUES (12, 1, '2022-07-17 12:55:46', '你好', 4);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, 'admin', '123456', '/club/logo/屏幕截图 2022-07-12 151238.jpg');
INSERT INTO `tbl_user` VALUES (2, 'zs', '123456', '/club/logo/屏幕截图 2022-07-12 151238.jpg');
INSERT INTO `tbl_user` VALUES (3, 'ls', '123456', '/club/logo/屏幕截图 2022-07-12 151238.jpg');
INSERT INTO `tbl_user` VALUES (4, 'ww', '123456', '/club/logo/屏幕截图 2022-07-12 151238.jpg');
INSERT INTO `tbl_user` VALUES (5, 'zl', '123456', '/club/logo/屏幕截图 2022-07-12 151238.jpg');

-- ----------------------------
-- View structure for v_activity
-- ----------------------------
DROP VIEW IF EXISTS `v_activity`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_activity` AS select `tbl_activity`.`id` AS `id`,`tbl_activity`.`activity` AS `activity`,`tbl_activity`.`price` AS `price`,`tbl_activity`.`detail` AS `detail`,`tbl_activity`.`start` AS `start`,`tbl_activity`.`ctime` AS `ctime`,`tbl_activity`.`uid` AS `uid`,`tbl_activity`.`hot` AS `hot`,`tbl_activity`.`logo` AS `logo`,`tbl_user`.`username` AS `username` from (`tbl_activity` left join `tbl_user` on((`tbl_activity`.`uid` = `tbl_user`.`id`)));

-- ----------------------------
-- View structure for v_comment
-- ----------------------------
DROP VIEW IF EXISTS `v_comment`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_comment` AS select `tbl_comment`.`id` AS `id`,`tbl_comment`.`uid` AS `uid`,`tbl_comment`.`ptime` AS `ptime`,`tbl_comment`.`detail` AS `detail`,`tbl_comment`.`pid` AS `pid`,`tbl_user`.`username` AS `username`,`tbl_user`.`photo` AS `photo` from (`tbl_comment` left join `tbl_user` on((`tbl_comment`.`uid` = `tbl_user`.`id`)));

-- ----------------------------
-- View structure for v_goods
-- ----------------------------
DROP VIEW IF EXISTS `v_goods`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_goods` AS select `tbl_goods`.`id` AS `id`,`tbl_goods`.`goodsname` AS `goodsname`,`tbl_goods`.`price` AS `price`,`tbl_goods`.`introduction` AS `introduction`,`tbl_goods`.`uid` AS `uid`,`tbl_goods`.`amount` AS `amount`,`tbl_goods`.`photo` AS `photo`,`tbl_user`.`username` AS `username` from (`tbl_goods` left join `tbl_user` on((`tbl_goods`.`uid` = `tbl_user`.`id`)));

-- ----------------------------
-- View structure for v_postings
-- ----------------------------
DROP VIEW IF EXISTS `v_postings`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_postings` AS select `tbl_postings`.`id` AS `id`,`tbl_postings`.`uid` AS `uid`,`tbl_postings`.`ptime` AS `ptime`,`tbl_postings`.`detail` AS `detail`,`tbl_postings`.`clicks` AS `clicks`,`tbl_user`.`username` AS `username`,`tbl_user`.`photo` AS `photo` from (`tbl_postings` left join `tbl_user` on((`tbl_postings`.`uid` = `tbl_user`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
