/*创建角色表*/
DROP TABLE IF EXISTS `ballchen_t_role`;
CREATE TABLE `ballchen_t_role`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `ROLE_NAME` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `ROLE_CODE` VARCHAR(50) NOT NULL UNIQUE COMMENT '角色代码',
  `MARK` VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_ROLE_NAME(`ROLE_NAME`),
  KEY IDX_ROLE_CODE(`ROLE_CODE`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户信息表';

/*创建权限表*/
DROP TABLE  IF EXISTS `ballchen_t_authorization`;
CREATE TABLE `ballchen_t_authorization`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `AUTHORIZATION_NAME` LONGTEXT  NOT NULL COMMENT '权限名称',
  `AUTHORIZATION_CODE` LONGTEXT  NOT NULL  COMMENT '权限代码',
  `WORK_URL` LONGTEXT NOT NULL COMMENT '作用地址',
  `MARK` VARCHAR(500),
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限信息表';

/*创建权限-----角色表*/
DROP TABLE IF EXISTS `ballchen_t_role_authorization`;
CREATE TABLE `ballchen_t_role_authorization`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `AUTHORIZATION_ID` VARCHAR(50) NOT NULL  COMMENT '权限ID',
  `ROLE_ID` VARCHAR(50) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_AUTHORIZATION_ID(`AUTHORIZATION_ID`),
  KEY IDX_ROLE_ID(`ROLE_ID`),
  CONSTRAINT `FK_T_ROLE_AUTHORIZATION_T_AUTHORIZATION_ID` FOREIGN KEY (`AUTHORIZATION_ID`) REFERENCES `ballchen_t_authorization` (`ID`),
  CONSTRAINT `FK_T_ROLE_AUTHORIZATION_T_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `ballchen_t_role` (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限-----角色表';

/*创建账户表*/
DROP TABLE IF EXISTS `ballchen_t_account`;
CREATE TABLE `ballchen_t_account`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `ACCOUNT_NAME` VARCHAR(50) NOT NULL COMMENT '账户名称',
  `PASSWORD` VARCHAR(50) NOT NULL COMMENT '密码',
  `DENIED` BIT(1) DEFAULT b'0' COMMENT '禁用（0.false;1.true）',
  `MARK` VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_ACCOUNT_NAME(`ACCOUNT_NAME`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户表';

/*创建用户表基本表*/
DROP TABLE  IF EXISTS `ballchen_t_user_basic`;
CREATE TABLE `ballchen_t_user_basic`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `USER_NAME` VARCHAR(50) COMMENT '用户姓名',
  `NICK_NAME` VARCHAR(100) COMMENT '昵称',
  `SEX` BIT(1) DEFAULT b'0' COMMENT '性别0.女；1.男',
  `BIRTHDAY` TIMESTAMP COMMENT '生日',
  `EMAIL` VARCHAR(200) COMMENT '电子邮件',
  `ID_NUMBER` VARCHAR(30) UNIQUE COMMENT '身份证号码',
  `PHONE` VARCHAR(30) COMMENT '电话号码',
  `HOME_PHONE` VARCHAR(30) COMMENT '家庭电话',
  `DESCRIPTION` LONGTEXT COMMENT '个人描述',
  `ACCOUNT_ID` VARCHAR(50) NOT NULL COMMENT '账户ID',
  `MARK` VARCHAR(500) COMMENT '备注',
  `REAL_NAME_VALID` BIT(1) DEFAULT b'0' COMMENT '实名认证0.未认证；1.已认证',
  `DENIED` BIT(1) DEFAULT b'0' COMMENT '禁用（0.false;1.true）',
  `DENIED_REASON` VARCHAR(500) COMMENT '禁用原因',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_USER_NAME(`USER_NAME`),
  KEY IDX_NICK_NAME(`NICK_NAME`),
  KEY IDX_SEX(`SEX`),
  KEY IDX_BIRTHDAY(`BIRTHDAY`),
  KEY IDX_EMAIL(`EMAIL`),
  KEY IDX_ID_NUMBER(`ID_NUMBER`),
  KEY IDX_PHONE(`PHONE`),
  KEY IDX_HOME_PHONE(`HOME_PHONE`),
  KEY IDX_ACCOUNT_ID(`ACCOUNT_ID`),
  KEY IDX_REAL_NAME_VALID(`REAL_NAME_VALID`),
  KEY IDX_DENIED(`DENIED`),
  CONSTRAINT `FK_T_USER_BASIC_T_ACCOUNT_ID` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `ballchen_t_account` (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户基本表';

/*创建用户----角色表*/
DROP TABLE IF EXISTS `ballchen_t_user_basic_role`;
CREATE TABLE `ballchen_t_user_basic_role`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `USER_BASIC_ID` VARCHAR(50) NOT NULL COMMENT '用户ID',
  `ROLE_ID` VARCHAR(50) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_USER_BASIC_ID(`USER_BASIC_ID`),
  KEY IDX_ROLE_ID(`ROLE_ID`),
  CONSTRAINT `FK_T_USER_BASIC_ROLE_T_USER_BASIC_ID` FOREIGN KEY (`USER_BASIC_ID`) REFERENCES `ballchen_t_user_basic` (`ID`),
  CONSTRAINT `FK_T_USER_BASIC_ROLE_T_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `ballchen_t_role` (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户---角色表';

/*创建附件表*/
DROP TABLE IF EXISTS `ballchen_t_accessory`;
CREATE TABLE `ballchen_t_accessory`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `ACCESSORY_NAME` VARCHAR(50) NOT NULL COMMENT '附件名称',
  `SAVE_NAME` VARCHAR(200) NOT NULL COMMENT '保存名称',
  `FILE_NAME` VARCHAR(500) NOT NULL COMMENT '文件名',
  `URL` VARCHAR(500) NOT NULL COMMENT '附件路径',
  `EXT` VARCHAR(30) NOT NULL COMMENT '文件后缀名',
  `FILE_TYPE` VARCHAR(30) NOT NULL COMMENT '文件类型',
  `FILE_SIZE` BIGINT NOT NULL DEFAULT 0 COMMENT '文件大小',
  `FILE_SERVER_TYPE` VARCHAR(30) NOT NULL COMMENT '文件服务器类型',
  `MARK` VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_ACCESSORY_NAME(`ACCESSORY_NAME`),
  KEY IDX_EXT(`EXT`),
  KEY IDX_FILE_NAME(`FILE_NAME`),
  KEY IDX_SAVE_NAME(`SAVE_NAME`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET=utf8 COMMENT ='附件表';

/*创建附件用户关系表*/
DROP TABLE IF EXISTS `ballchen_t_user_basic_accessory`;
CREATE TABLE `ballchen_t_user_basic_accessory`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `USER_BASIC_ID` VARCHAR(50) NOT NULL COMMENT '用户ID',
  `ACCESSORY_ID` VARCHAR(50) NOT NULL COMMENT '附件ID',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_USER_BASIC_ID(`USER_BASIC_ID`),
  KEY IDX_ACCESSORY_ID(`ACCESSORY_ID`),
  CONSTRAINT `FK_T_USER_BASIC_ACCESSORY_T_USER_BASIC_ID` FOREIGN KEY (`USER_BASIC_ID`) REFERENCES `ballchen_t_user_basic` (`ID`),
  CONSTRAINT `FK_T_USER_BASIC_ACCESSORY_T_ACCESSORY_ID` FOREIGN KEY (`ACCESSORY_ID`) REFERENCES `ballchen_t_accessory` (`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT ='用户--附件表';