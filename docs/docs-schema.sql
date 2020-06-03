/*
Navicat MySQL Data Transfer

Source Server         : Ubuntu
Source Server Version : 50729
Source Host           : 1***:3306
Source Database       : project

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-06-01 23:32:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill.goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill.goods`;
CREATE TABLE `seckill.goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
  `goods_detail` longtext COMMENT '商品的详情介绍',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存, -1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.goods
-- ----------------------------
INSERT INTO `seckill.goods` VALUES ('1', 'iphonex', '手机', null, '苹果手机天下第一', '10000.00', '100');
INSERT INTO `seckill.goods` VALUES ('2', 'mate30', '华为', null, '华为天下第二', '8000.00', '200');

-- ----------------------------
-- Table structure for seckill.order
-- ----------------------------
DROP TABLE IF EXISTS `seckill.order`;
CREATE TABLE `seckill.order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收获地址ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc , 2android , 3ios',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态, 0新建未支付, 1已支付, 2已发货, 3已收货, 4已退款, 5已完成',
  `create_date` datetime DEFAULT NULL COMMENT '订单的创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.order
-- ----------------------------

-- ----------------------------
-- Table structure for seckill.seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_goods`;
CREATE TABLE `seckill.seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品Id',
  `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.seckill_goods
-- ----------------------------
INSERT INTO `seckill.seckill_goods` VALUES ('1', '1', '0.01', '4', '2017-11-05 15:18:00', '2017-11-13 14:00:18');
INSERT INTO `seckill.seckill_goods` VALUES ('2', '2', '0.01', '9', '2017-11-12 14:00:14', '2017-11-13 14:00:24');

-- ----------------------------
-- Table structure for seckill.seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_order`;
CREATE TABLE `seckill.seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.seckill_order
-- ----------------------------


-- ----------------------------
-- Table structure for seckill.user
-- ----------------------------
DROP TABLE IF EXISTS `seckill.user`;
CREATE TABLE `seckill.user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for seckill.user
-- ----------------------------
