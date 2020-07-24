SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill.goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill.goods`;
CREATE TABLE `seckill.goods` (
  `id` varchar(36) NOT NULL COMMENT '商品ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
  `goods_detail` longtext COMMENT '商品的详情介绍',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存, -1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.goods
-- ----------------------------

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
  `create_date` datetime DEFAULT NULL COMMENT '订单的创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.order
-- ----------------------------

-- ----------------------------
-- Table structure for seckill.seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_goods`;
CREATE TABLE `seckill.seckill_goods` (
  `id` varchar(36) NOT NULL COMMENT '秒杀的商品表',
  `goods_id` varchar(36) DEFAULT NULL COMMENT '商品Id',
  `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for seckill.seckill_goods
-- ----------------------------

-- ----------------------------
-- Table structure for seckill.seckill_stock
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_stock`;
CREATE TABLE `seckill.seckill_stock`(
    `id` varchar(36) NOT NULL,
    `seckill_goods_id` nvarchar(36) NOT NULL COMMENT '秒杀商品 ID',
    `seckill_goods_count` int(11) DEFAULT 0 COMMENT '秒杀商品库存',
)

-- ----------------------------
-- Records of seckill.seckill_stock
-- ----------------------------

-- ----------------------------
-- Table structure for seckill.seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill.seckill_order`;
CREATE TABLE `seckill.seckill_order` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `order_id` varchar(36) DEFAULT NULL COMMENT '订单ID',
  `goods_id` varchar(36) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `registerDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seckill.user
-- ----------------------------
INSERT INTO `seckill.user` VALUES ('4e9f65ed-4255-4715-b8ef-e736bdede106', 'zack', '49ba59abbe56e057', 'ecab869d72e0413bbbf83789432fdded', '2020-06-08 21:31:19');

-- ----------------------------
-- Records of seckill.goods
-- ----------------------------
INSERT INTO `seckill.goods` VALUES ('1', 'iphonex', '手机', null, '苹果手机天下第一', '10000.00', '100');
INSERT INTO `seckill.goods` VALUES ('2', 'mate30', '华为', null, '华为天下第二', '8000.00', '200');

-- ----------------------------
-- Records of seckill.seckill_goods
-- ----------------------------
INSERT INTO `seckill.seckill_goods` VALUES ('1', '1', '0.01', '4', '2017-11-05 15:18:00', '2017-11-13 14:00:18');
INSERT INTO `seckill.seckill_goods` VALUES ('2', '2', '0.01', '9', '2017-11-12 14:00:14', '2017-11-13 14:00:24');