# Host: wydy.shop:2697  (Version 5.7.16)
# Date: 2018-06-02 14:51:33
# Generator: MySQL-Front 6.0  (Build 1.163)

# Host: shu.wydy.shop:9325  (Version 5.7.16)
# Date: 2018-01-15 11:13:02
# Generator: MySQL-Front 6.0  (Build 1.163)


#
# Structure for table "userinfo"
#

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) DEFAULT NULL COMMENT '唯一Id',
  `userName` varchar(255) DEFAULT NULL COMMENT '登录名',
  `passWord` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '密码随机盐',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickName` varchar(255) DEFAULT NULL COMMENT '昵称',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) DEFAULT NULL COMMENT '用户Id',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updateTime',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#
# Structure for table "spring_session"
#

DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session` (
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SESSION_ID`),
  KEY `SPRING_SESSION_IX1` (`LAST_ACCESS_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "spring_session_attributes"
#

DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes` (
  `SESSION_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_ID`,`ATTRIBUTE_NAME`),
  KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_ID`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_ID`) REFERENCES `spring_session` (`SESSION_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
