create database IF NOT EXISTS bwl CHARACTER set utf8;
CREATE TABLE bwl.`notes` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '随记id',
  `userNo` varchar(19) NOT NULL COMMENT '用户编号',
  `topic` varchar(20) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `noteNo` varchar(32) NOT NULL COMMENT '笔记编号',
  `version` int(5) NOT NULL COMMENT '笔记版本号',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `noteno_unique` (`noteNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

CREATE TABLE bwl.`tip` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `delFlag` tinyint(1) DEFAULT NULL,
  `userNo` varchar(19) NOT NULL,
  `tipNo` varchar(32) NOT NULL,
  `topic` varchar(20) DEFAULT NULL,
  `content` varchar(20) DEFAULT NULL,
  `timestampstr` varchar(15) NOT NULL,
  `tipCycle` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `version` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipno_unique` (`tipNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

CREATE TABLE bwl.`user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(20) NOT NULL COMMENT '昵称',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `mobileNo` varchar(11) NOT NULL COMMENT '手机号',
  `status` tinyint(1) NOT NULL COMMENT '用户状态',
  `imageUrl` text COMMENT '用户头像',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `delFlag` tinyint(1) NOT NULL COMMENT '逻辑删除',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `userType` tinyint(1) NOT NULL COMMENT '用户类型',
  `userNo` varchar(19) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobileno_unique` (`mobileNo`) USING BTREE COMMENT '手机号唯一',
  UNIQUE KEY `userno_unique` (`userNo`) USING BTREE COMMENT '用户编号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
