/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : Kitchen

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 27/08/2019 19:19:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for buyfood
-- ----------------------------
DROP TABLE IF EXISTS `buyfood`;
CREATE TABLE `buyfood` (
  `buy_order_id` varchar(255) NOT NULL,
  `food_id` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`buy_order_id`,`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyfood
-- ----------------------------
BEGIN;
INSERT INTO `buyfood` VALUES ('1566715631262374737', '1566614095562954682', 11, 2);
INSERT INTO `buyfood` VALUES ('1566715631262374737', '1566614701048127859', 22, 2);
INSERT INTO `buyfood` VALUES ('1566716038862934944', '1566614095562954682', 11, 2);
INSERT INTO `buyfood` VALUES ('1566716038862934944', '1566614701048127859', 22, 2);
COMMIT;

-- ----------------------------
-- Table structure for foodinfo
-- ----------------------------
DROP TABLE IF EXISTS `foodinfo`;
CREATE TABLE `foodinfo` (
  `food_id` varchar(1024) NOT NULL,
  `food_des` varchar(1024) NOT NULL,
  `food_price` int(11) NOT NULL DEFAULT '0',
  `food_num` int(11) NOT NULL DEFAULT '0',
  `food_name` varchar(1024) NOT NULL,
  `food_type_name_of_food_info` varchar(1024) NOT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodinfo
-- ----------------------------
BEGIN;
INSERT INTO `foodinfo` VALUES ('1566614095562954682', 'xxx', 11, 22, '猪肉', '肉类');
INSERT INTO `foodinfo` VALUES ('1566614701048127859', 'xxxxx', 11, 22, 'xx', '肉类');
COMMIT;

-- ----------------------------
-- Table structure for foodorder
-- ----------------------------
DROP TABLE IF EXISTS `foodorder`;
CREATE TABLE `foodorder` (
  `order_id` varchar(1024) NOT NULL,
  `send_time` date NOT NULL,
  `send_address` varchar(1024) NOT NULL,
  `user_tel` varchar(1024) NOT NULL,
  `order_status` int(11) NOT NULL DEFAULT '0',
  `user_id` varchar(1024) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodorder
-- ----------------------------
BEGIN;
INSERT INTO `foodorder` VALUES ('1566694232575788567', '2019-08-26', 'xxx', 'xxx', 1, '1566604396192902572');
INSERT INTO `foodorder` VALUES ('1566700354645812346', '2019-08-28', 'xxx', '1xxxxx', 2, '1566604396192902572');
INSERT INTO `foodorder` VALUES ('1566700445283663624', '2019-08-26', 'xxxx', '1xxxxxx', 1, '1566604396192902572');
INSERT INTO `foodorder` VALUES ('1566796064649621273', '2019-08-27', 'xxx', '1xxxxx', 0, '1566604396192902572');
INSERT INTO `foodorder` VALUES ('1566796264660908600', '2019-08-28', 'xxx', '1xxxxx', 0, '1566604396192902572');
INSERT INTO `foodorder` VALUES ('1566798981310246694', '2019-08-27', 'xxx', '1xxxx', 0, '1566604396192902572');
INSERT INTO `foodorder` VALUES ('1566834625068270858', '2019-08-27', '1xxx', 'xxx', 0, '1');
COMMIT;

-- ----------------------------
-- Table structure for foodtype
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `food_type_id` varchar(1024) NOT NULL,
  `food_type_name` varchar(1024) NOT NULL,
  `food_type_des` varchar(1024) NOT NULL,
  PRIMARY KEY (`food_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
BEGIN;
INSERT INTO `foodtype` VALUES ('1566610600951701614', '肉类', '好吃不油腻嗷');
COMMIT;

-- ----------------------------
-- Table structure for myuser
-- ----------------------------
DROP TABLE IF EXISTS `myuser`;
CREATE TABLE `myuser` (
  `user_id` varchar(1024) NOT NULL,
  `user_name` varchar(1024) NOT NULL,
  `user_tel` varchar(1024) NOT NULL,
  `user_email` varchar(1024) NOT NULL,
  `user_contact` varchar(1024) DEFAULT NULL,
  `sex` varchar(1024) NOT NULL,
  `pwd` varchar(1024) NOT NULL,
  `city` varchar(1024) NOT NULL,
  `registerDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myuser
-- ----------------------------
BEGIN;
INSERT INTO `myuser` VALUES ('1566560282490893582', 'zwj', 'xxxxxx', '1233989@163.com', '暂无', '男', '123456', 'wuhan', '2019-08-23 19:38:02');
INSERT INTO `myuser` VALUES ('1566604396192902572', 'wzw', 'xxxxx', 'xxx@qq.com', '暂无', '男', '123456', 'shanghai', '2019-08-24 07:53:16');
COMMIT;

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `op_id` varchar(1024) NOT NULL,
  `op_name` varchar(1024) NOT NULL,
  `op_level` int(11) NOT NULL,
  `op_pwd` varchar(1024) NOT NULL,
  PRIMARY KEY (`op_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operator
-- ----------------------------
BEGIN;
INSERT INTO `operator` VALUES ('1', 'wz', 1, 'wzjly1314');
COMMIT;

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `order_id` varchar(255) NOT NULL,
  `food_id` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `discount` double(11,0) NOT NULL,
  PRIMARY KEY (`order_id`,`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
BEGIN;
INSERT INTO `orderdetail` VALUES ('1566694232575788567', '1566614095562954682', 11, 121, 1);
INSERT INTO `orderdetail` VALUES ('1566700354645812346', '1566614095562954682', 11, 121, 1);
INSERT INTO `orderdetail` VALUES ('1566700354645812346', '1566614701048127859', 22, 242, 1);
INSERT INTO `orderdetail` VALUES ('1566700445283663624', '1566614095562954682', 11, 121, 0);
INSERT INTO `orderdetail` VALUES ('1566796064649621273', '1566614095562954682', 22, 242, 1);
INSERT INTO `orderdetail` VALUES ('1566796064649621273', '1566614701048127859', 11, 121, 1);
INSERT INTO `orderdetail` VALUES ('1566796264660908600', '1566614095562954682', 11, 121, 1);
INSERT INTO `orderdetail` VALUES ('1566798981310246694', '1566614095562954682', 11, 121, 1);
INSERT INTO `orderdetail` VALUES ('1566834625068270858', '1566614095562954682', 1, 11, 1);
COMMIT;

-- ----------------------------
-- Table structure for recipe
-- ----------------------------
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe` (
  `recipeId` varchar(1024) NOT NULL,
  `recipe_name` varchar(1024) NOT NULL,
  `contri_usr` varchar(1024) NOT NULL,
  `recipe_des` varchar(1024) NOT NULL,
  `recipe_score` varchar(1024) NOT NULL DEFAULT '0',
  `recipe_coll` int(11) NOT NULL DEFAULT '0',
  `recipe_brow` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`recipeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipe
-- ----------------------------
BEGIN;
INSERT INTO `recipe` VALUES ('1566799194372454792', '猪肉炒饭', 'wz', 'xxx很好吃', '5', 1, 21);
COMMIT;

-- ----------------------------
-- Table structure for recipebrow
-- ----------------------------
DROP TABLE IF EXISTS `recipebrow`;
CREATE TABLE `recipebrow` (
  `brow_id` varchar(1024) NOT NULL,
  `recipe_Id` varchar(1024) DEFAULT NULL,
  `brow_user_id` varchar(1024) DEFAULT NULL,
  `is_brow` tinyint(1) NOT NULL,
  PRIMARY KEY (`brow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipebrow
-- ----------------------------
BEGIN;
INSERT INTO `recipebrow` VALUES ('15668726543811461911', '1566799194372454792', '1', 1);
INSERT INTO `recipebrow` VALUES ('15668729137587549811', '1566799194372454792', '1', 1);
INSERT INTO `recipebrow` VALUES ('1566893397136209764', '1566799194372454792', '1566604396192902572', 1);
COMMIT;

-- ----------------------------
-- Table structure for recipecoll
-- ----------------------------
DROP TABLE IF EXISTS `recipecoll`;
CREATE TABLE `recipecoll` (
  `coll_id` varchar(1024) NOT NULL,
  `recipe_Id` varchar(1024) DEFAULT NULL,
  `coll_user_id` varchar(1024) DEFAULT NULL,
  `is_coll` tinyint(1) NOT NULL,
  PRIMARY KEY (`coll_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipecoll
-- ----------------------------
BEGIN;
INSERT INTO `recipecoll` VALUES ('1566885038774398468', '1566799194372454792', '1', 1);
INSERT INTO `recipecoll` VALUES ('1566900580732701069', '1566799194372454792', '1566604396192902572', 1);
COMMIT;

-- ----------------------------
-- Table structure for recipecomment
-- ----------------------------
DROP TABLE IF EXISTS `recipecomment`;
CREATE TABLE `recipecomment` (
  `recipe_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `comment_content` varchar(1024) NOT NULL,
  `browse_sig` int(11) NOT NULL DEFAULT '0',
  `coll_sig` int(11) NOT NULL DEFAULT '0',
  `comment_score` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`recipe_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipecomment
-- ----------------------------
BEGIN;
INSERT INTO `recipecomment` VALUES ('1566799194372454792', '1', 'xsxsx', 1, 1, 5);
INSERT INTO `recipecomment` VALUES ('1566799194372454792', '1566604396192902572', 'xxx', 1, 1, 6);
COMMIT;

-- ----------------------------
-- Table structure for recipematerials
-- ----------------------------
DROP TABLE IF EXISTS `recipematerials`;
CREATE TABLE `recipematerials` (
  `recipe_id` varchar(255) NOT NULL,
  `food_id` varchar(255) NOT NULL,
  `num_of_food` int(11) NOT NULL,
  `work_address` varchar(1024) NOT NULL,
  PRIMARY KEY (`recipe_id`,`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipematerials
-- ----------------------------
BEGIN;
INSERT INTO `recipematerials` VALUES ('1566799194372454792', '1566614095562954682', 1, '斤');
COMMIT;

-- ----------------------------
-- Table structure for recipestep
-- ----------------------------
DROP TABLE IF EXISTS `recipestep`;
CREATE TABLE `recipestep` (
  `recipe_id` varchar(255) NOT NULL,
  `step_id` int(11) NOT NULL DEFAULT '1',
  `step_des` varchar(1024) NOT NULL,
  PRIMARY KEY (`recipe_id`,`step_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recipestep
-- ----------------------------
BEGIN;
INSERT INTO `recipestep` VALUES ('1566799194372454792', 0, '放点水');
INSERT INTO `recipestep` VALUES ('1566799194372454792', 1, '再放点猪肉');
INSERT INTO `recipestep` VALUES ('1566799194372454792', 2, '等水沸腾之后,加入炒饭，进行翻炒一分钟，最后出锅');
INSERT INTO `recipestep` VALUES ('1566799194372454792', 3, 'shihi');
INSERT INTO `recipestep` VALUES ('1566799194372454792', 4, 'xxx');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
