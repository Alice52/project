## schema

1. seckill.goods

```sql
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
  `is_deleted` tinyint(1) DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

2. seckill.order

```sql
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
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '订单的创建时间',
  `pay_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP NULL COMMENT '支付时间',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

3. seckill.seckill_goods

```sql
DROP TABLE IF EXISTS `seckill.seckill_goods`;
CREATE TABLE `seckill.seckill_goods` (
  `id` varchar(36) NOT NULL COMMENT '秒杀的商品表',
  `goods_id` varchar(36) DEFAULT NULL COMMENT '商品Id',
  `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

4. seckill.seckill_stock

```sql
DROP TABLE IF EXISTS `seckill.seckill_stock`;
CREATE TABLE `seckill.seckill_stock`(
  `id` varchar(36) NOT NULL,
  `seckill_goods_id` nvarchar(36) NOT NULL COMMENT '秒杀商品 ID',
  `seckill_goods_count` int(11) DEFAULT 0 COMMENT '秒杀商品库存',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT false
)
```

5. seckill.seckill_order

```sql
DROP TABLE IF EXISTS `seckill.seckill_order`;
CREATE TABLE `seckill.seckill_order` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `order_id` varchar(36) DEFAULT NULL COMMENT '订单ID',
  `goods_id` varchar(36) DEFAULT NULL COMMENT '商品ID',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

6. seckill.user

```sql
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

-- DROP TRIGGER IF EXISTS ` seckill.user.updated_date_trigger`;
-- DELIMITER ;;
-- CREATE TRIGGER ` seckill.user.updated_date_trigger` BEFORE UPDATE ON `seckill.user` FOR EACH ROW SET NEW.updated_date=CURRENT_TIMESTAMP(6);
-- ;;
-- DELIMITER ;
```

7. seckill.password

```sql
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
```

8. message relavent
   // TODO:
