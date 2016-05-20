DROP TABLE IF EXISTS `T_MENU_INFO`;
CREATE TABLE `T_MENU_INFO` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menu_name` varchar(35) NOT NULL COMMENT '菜单名称',
  `url` varchar(500) DEFAULT NULL COMMENT '路径',
  `visiable` bit(1) DEFAULT b'0' COMMENT '可见性0.可见；1.不可见',
  `sequence` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '顺序',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父菜单ID',
  `deletioned` bit(1) DEFAULT b'0' COMMENT '已删除0：未删除；1：已删除',
  `mark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_sequence` (`sequence`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_visiable` (`visiable`),
  KEY `idx_deletioned` (`deletioned`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='菜单信息表';
