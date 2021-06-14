```sql
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL COMMENT '用户姓名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `sex` int(2) DEFAULT NULL COMMENT '性别 1:男 2:女 3:未知',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日 yyyy-MM-dd',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



CREATE TABLE `t_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `spec` varchar(255) DEFAULT NULL COMMENT '规格',
  `total_stock` decimal(10,2) NOT NULL COMMENT '总库存',
  `sales_unit` varchar(10) NOT NULL COMMENT '销售单位',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `type` int(5) DEFAULT NULL COMMENT '商品类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';



CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(20) NOT NULL COMMENT '订单号',
  `order_type` int(4) NOT NULL COMMENT '订单类型 1：正常订单 2：退单',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `sku_id` bigint(20) NOT NULL COMMENT '商品id',
  `amount` decimal(14,2) NOT NULL COMMENT '订单金额',
  `order_status` int(4) NOT NULL COMMENT '订单状态：1:待支付  2：已支付  3：已取消',
  `order_at` datetime NOT NULL COMMENT '下单时间',
  `pay_at` datetime DEFAULT NULL COMMENT '支付时间',
  `paid_amt` decimal(14,2) NOT NULL COMMENT '实际支付金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
```

