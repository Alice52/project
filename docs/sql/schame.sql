SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill.goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill.goods`;
CREATE TABLE `seckill.goods` (
  `id` varchar(36) NOT NULL COMMENT '商品ID',
  `name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
  `detail` longtext COMMENT '商品的详情介绍',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `stock` int(11) DEFAULT '0' COMMENT '商品库存, -1表示没有限制',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.goods
-- ----------------------------
INSERT INTO `seckill.goods` VALUES ('0d0de9fdd94711ea93d70242ac110006', 'iphonex', '手机', null, '苹果手机天下第一', '5400.00', '5000', '2020-08-08 15:17:07', '2020-08-08 15:22:21', '0');
INSERT INTO `seckill.goods` VALUES ('2d8608f1d94711ea93d70242ac110006', 'mate30', '手机', null, '华为天下第二', '5200.00', '3000', '2020-08-08 15:17:21', '2020-08-08 15:22:23', '0');

-- ----------------------------
-- Table structure for seckill.order
-- ----------------------------
DROP TABLE IF EXISTS `seckill.order`;
CREATE TABLE `seckill.order` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `goods_id` varchar(36) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` varchar(36) DEFAULT NULL COMMENT '收获地址ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc , 2android , 3ios',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态, 0新建未支付, 1已支付, 2已发货, 3已收货, 4已退款, 5已完成',
  `pay_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `source` varchar(36) DEFAULT NULL COMMENT 'pc, h5',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for seckill.password
-- ----------------------------
DROP TABLE IF EXISTS `seckill.password`;
CREATE TABLE `seckill.password` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `salt` varchar(36) NOT NULL,
  `password` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for seckill.seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_goods`;
CREATE TABLE `seckill.seckill_goods` (
  `id` varchar(36) NOT NULL COMMENT '秒杀的商品表',
  `goods_id` varchar(36) DEFAULT NULL COMMENT '商品Id',
  `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.seckill_goods
-- ----------------------------
INSERT INTO `seckill.seckill_goods` VALUES ('92f61330d94711ea93d70242ac110006', '0d0de9fdd94711ea93d70242ac110006', '0.01', '2020-08-08 15:20:16', '2020-08-08 15:20:16', '2020-08-08 15:20:16', '2020-08-08 15:20:16', '0');
INSERT INTO `seckill.seckill_goods` VALUES ('a4301748d94711ea93d70242ac110006', '2d8608f1d94711ea93d70242ac110006', '0.02', '2020-08-08 15:20:38', '2020-08-08 15:20:38', '2020-08-08 15:20:38', '2020-08-08 15:21:28', '0');

-- ----------------------------
-- Table structure for seckill.seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_order`;
CREATE TABLE `seckill.seckill_order` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `order_id` varchar(36) DEFAULT NULL COMMENT '订单ID',
  `seckill_goods_id` varchar(36) DEFAULT NULL COMMENT '商品ID',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0',
  `seckill_price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for seckill.seckill_stock
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_stock`;
CREATE TABLE `seckill.seckill_stock` (
  `id` varchar(36) NOT NULL,
  `seckill_goods_id` varchar(36) CHARACTER SET utf8 NOT NULL COMMENT '秒杀商品 ID',
  `seckill_goods_stock` int(11) DEFAULT '0' COMMENT '秒杀商品库存',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.seckill_stock
-- ----------------------------
INSERT INTO `seckill.seckill_stock` VALUES ('cfb190b1d94711ea93d70242ac110006', '92f61330d94711ea93d70242ac110006', '500', '2020-08-08 15:21:55', '2020-08-08 15:21:55', '0');
INSERT INTO `seckill.seckill_stock` VALUES ('da71038fd94711ea93d70242ac110006', 'a4301748d94711ea93d70242ac110006', '300', '2020-08-08 15:22:12', '2020-08-08 15:22:12', '0');

-- ----------------------------
-- Table structure for seckill.stock_log
-- ----------------------------
DROP TABLE IF EXISTS `seckill.stock_log`;
CREATE TABLE `seckill.stock_log` (
  `id` varchar(36) NOT NULL,
  `goods_id` varchar(36) NOT NULL COMMENT '商品ID',
  `seckill_goods_id` varchar(36) DEFAULT NULL COMMENT '商品ID',
  `amount` int(11) DEFAULT '0' COMMENT '商品数量',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for seckill.user
-- ----------------------------
DROP TABLE IF EXISTS `seckill.user`;
CREATE TABLE `seckill.user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `registered_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(50) DEFAULT NULL,
  `phone` decimal(13,0) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT '0',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
