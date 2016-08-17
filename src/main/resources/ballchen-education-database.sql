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

/*创建分类表*/
DROP TABLE IF EXISTS `ballchen_t_category`;
CREATE TABLE `ballchen_t_category`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `CATEGORY_NAME` VARCHAR(50) NOT NULL COMMENT '分类名称',
  `CATEGORY_TYPE` VARCHAR(50) COMMENT '分类类型',
  `SEQUENCE` BIGINT DEFAULT '0' COMMENT '排序',
  `PARENT_ID` VARCHAR(50) COMMENT '父级ID',
  `USER_BASIC_ID` VARCHAR(50) COMMENT '所属用户ID',
  `ACCESSORY_ID` VARCHAR(50) COMMENT '附件ID',
  `MARK` VARCHAR(500) COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_CATEGORY_NAME(`CATEGORY_NAME`),
  KEY IDX_CATEGORY_TYPE(`CATEGORY_TYPE`),
  KEY IDX_PARENT_ID(`PARENT_ID`),
  KEY IDX_USER_BASIC_ID(`USER_BASIC_ID`),
  KEY IDX_ACCESSORY_ID(`ACCESSORY_ID`),
  CONSTRAINT `FK_T_CATEGORY_T_USER_BASIC_ID` FOREIGN KEY (`USER_BASIC_ID`) REFERENCES `ballchen_t_user_basic` (`ID`),
  CONSTRAINT `FK_T_CATEGORY_T_ACCESSORY_ID` FOREIGN KEY (`ACCESSORY_ID`) REFERENCES `ballchen_t_accessory` (`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT =' 分类表';


/*创建课程表*/
DROP TABLE IF EXISTS `ballchen_t_course`;
CREATE TABLE `ballchen_t_course`(
  `ID` VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `COURSE_NAME` VARCHAR(200) NOT NULL COMMENT '课程名称',
  `COURSE_TYPE` VARCHAR(30) NOT NULL COMMENT '课程类型（一对一，班课）',
  `PRICE` DECIMAL(10,2) DEFAULT '0.00' COMMENT '价格',
  `DENIED` BIT(1) DEFAULT b'1' COMMENT '禁用（0.false;1.true）',
  `RECOMMEND` BIT(1) DEFAULT b'0' COMMENT '推荐（0.false;1.true）',
  `CATEGORY_ID` VARCHAR(50) NOT NULL COMMENT '课程分类ID',
  `DESCRIPTION` LONGTEXT COMMENT '描述',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_COURSE_NAME(`COURSE_NAME`),
  KEY IDX_COURSE_TYPE(`COURSE_TYPE`),
  KEY IDX_PRICE(`PRICE`),
  CONSTRAINT `FK_T_COURSE_T_CATEGORY_ID` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `ballchen_t_category` (`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT '课程表';

/*创建课程--用户中间表*/
DROP TABLE IF EXISTS `ballchen_t_course_user_basic`;
CREATE TABLE `ballchen_t_course_user_basic`(
  `ID`  VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `COURSE_ID` VARCHAR(50) NOT NULL COMMENT '课程ID',
  `USER_BASIC_ID` VARCHAR(50) NOT NULL COMMENT '用户ID',
  PRIMARY KEY(`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_COURSE_ID(`COURSE_ID`),
  KEY IDX_USER_BASIC_ID(`USER_BASIC_ID`),
  CONSTRAINT `Fk_T_COURSE_USER_BASIC_T_COURSE_ID` FOREIGN KEY (`COURSE_ID`) REFERENCES `ballchen_t_course`(`ID`),
  CONSTRAINT `Fk_T_COURSE_USER_BASIC_T_USER_BASIC_ID` FOREIGN KEY (`USER_BASIC_ID`) REFERENCES `ballchen_t_user_basic`(`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT '课程--用户中间表';

/*创建课程--附件中间表*/
DROP TABLE IF EXISTS `ballchen_t_course_accessory`;
CREATE TABLE `ballchen_t_course_accessory`(
  `ID`  VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `COURSE_ID` VARCHAR(50) NOT NULL COMMENT '课程ID',
  `ACCESSORY_ID` VARCHAR(50) NOT NULL COMMENT '附件ID',
  PRIMARY KEY(`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_COURSE_ID(`COURSE_ID`),
  KEY IDX_USER_BASIC_ID(`ACCESSORY_ID`),
  CONSTRAINT `FK_T_COURSE_ACCESSORY_T_COURSE_ID` FOREIGN KEY (`COURSE_ID`) REFERENCES `ballchen_t_course`(`ID`),
  CONSTRAINT `Fk_T_COURSE_ACCESSORY_T_ACCESSORY_ID` FOREIGN KEY (`ACCESSORY_ID`) REFERENCES `ballchen_t_accessory`(`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT '课程--附件中间表';
/*创建课程章节表*/
DROP TABLE IF EXISTS `ballchen_t_course_chapter`;
CREATE TABLE `ballchen_t_course_chapter` (
  `ID`  VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME` TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `CHAPTER_NAME` VARCHAR(200) NOT NULL COMMENT '章节名称',
  `CHAPTER_TYPE` VARCHAR(50) NOT NULL COMMENT '章节类型（在线直播，线下，视频课，练习题，一对一，其它）',
  `PLAN_BEGIN_DATE` DATE COMMENT '计划开始日期',
  `PLAN_BEGIN_TIME` TIME COMMENT '计划开始时间',
  `PLAN_END_DATE` DATE COMMENT '计划结束日期',
  `PLAN_END_TIME` TIME COMMENT '计划结束时间',
  `REAL_BEGIN_DATE` DATE COMMENT '实际开始日期',
  `REAL_BEGIN_TIME` DATE COMMENT '实际开始时间',
  `REAL_END_DATE` DATE COMMENT '实际结束日期',
  `REAL_END_TIME` DATE COMMENT '实际结束时间',
  `COURSE_ID` VARCHAR(50) NOT NULL COMMENT '课程ID',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_CHAPTER_NAME(`CHAPTER_NAME`),
  KEY IDX_CHAPTER_TYPE(`CHAPTER_TYPE`),
  KEY IDX_PLAN_BEGIN_TIME(`PLAN_BEGIN_TIME`),
  KEY IDX_PLAN_BEGIN_DATE(`PLAN_BEGIN_DATE`),
  KEY IDX_PLAN_END_TIME(`PLAN_END_TIME`),
  KEY IDX_PLAN_END_DATE(`PLAN_END_DATE`),
  KEY IDX_REAL_BEGIN_TIME(`REAL_BEGIN_TIME`),
  KEY IDX_REAL_BEGIN_DATE(`REAL_BEGIN_DATE`),
  KEY IDX_REAL_END_TIME(`REAL_END_TIME`),
  KEY IDX_REAL_END_DATE(`REAL_END_DATE`),
  CONSTRAINT `FK_T_COURSE_CHAPTER_T_COURSE_ID` FOREIGN KEY (`COURSE_ID`) REFERENCES ballchen_t_course(`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT '课程章节表';

/*创建课程章节附件表*/
DROP TABLE IF EXISTS `ballchen_t_course_chapter_accessory`;
CREATE TABLE `ballchen_t_course_chapter_accessory` (
  `ID`  VARCHAR(50) NOT NULL COMMENT '主键ID',
  `CREATE_TIME`   TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `COURSE_CHAPTER_ID`     VARCHAR(50) NOT NULL COMMENT '课程章节ID',
  `ACCESSORY_ID` VARCHAR(50) NOT NULL COMMENT '附件ID',
  PRIMARY KEY (`ID`),
  KEY IDX_CREATE_TIME(`CREATE_TIME`),
  KEY IDX_COURSE_CHAPTER_ID(`COURSE_CHAPTER_ID`),
  KEY IDX_ACCESSORY_ID(`ACCESSORY_ID`),
  CONSTRAINT `FK_T_COURSE_CHAPTER_ACCESSORY_T_COURSE_CHAPTER_ID` FOREIGN KEY (`COURSE_CHAPTER_ID`) REFERENCES `ballchen_t_course_chapter` (`ID`),
  CONSTRAINT `Fk_T_COURSE_CHAPTER_ACCESSORY_T_ACCESSORY_ID` FOREIGN KEY (`ACCESSORY_ID`) REFERENCES `ballchen_t_accessory` (`ID`)
)ENGINE =InnoDB AUTO_INCREMENT=2 CHARSET =utf8 COMMENT '课程章节--附件表';

/*初始化用户*/
INSERT INTO `ballchen_t_user_basic` VALUES ('0236bce1-101c-4436-a8bc-b38330dc20d2', '2016-06-27 13:52:20', '白凤', '白凤', '', '2016-06-28 00:00:00', 'organizationtwo@qq.com', '370911199404196939', '13256189693', '13256189693', '<p>神秘优雅的白衣美男子，轻功举世无双，能够借助鸟类或羽毛飞翔，时常驾驭着白色的巨鸟在天空飞过。天赋异禀，具有控制与指挥鸟类的能力，被称为“百鸟之王”。[1]<a>&nbsp;</a></p><p>年少时曾为韩国大将军<a target=\"_blank\" href=\"http://baike.baidu.com/view/9412479.htm\">姬无夜</a>“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/866370/17171521.htm\">夜幕</a>”组织“百鸟”杀手团的杀手之一。后叛逃，加入以<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2105000/9571051.htm\">卫庄</a>为首领的杀手组织“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/37005/8143709.htm\">流沙</a>”，与<a target=\"_blank\" href=\"http://baike.baidu.com/view/1846320.htm\">赤练</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/view/2220076.htm\">苍狼王</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/view/2177838.htm\">无双鬼</a>并称为“四天王”，居首位。</p>', '7757804a-8473-4194-8270-90b86bcf6692', '白凤', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('2e53f132-028b-4ddd-92d1-980a737a3e8e', '2016-06-27 11:59:51', '王富贵', '王权富贵', '', '2016-06-09 00:00:00', 'organizationone@qq.com', '210802198604254098', '13256189693', '13256189693', '<p>驼色短发，金丝眼镜，有钱的花花公子，男主的死对头，道家兵人王权富贵的转世。讨厌是他的名字和妖怪，因为无论获得多么伟大的成就，只要这个成就化作前缀落在后面的“王富贵”都显得愚蠢透顶，和再世续缘关系只能易姓不易名。<br></p>', '15380fe9-22b1-417c-a7dc-734c48efa3cd', '王权富贵', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('2e542a77-7351-49fd-8f27-bc0404d17595', '2016-06-27 14:04:21', '白月初', '二货道士', '', '2016-06-27 00:00:00', 'organizationthree@qq.com', '450311199107123538', '13256189693', '13256189693', '<p>道界最强之人东方月初的转世，一气道盟严格控制之人。财迷+吃货，史上最无节操正太道士，与涂山苏苏有婚约。除了拥有涂山红红的妖力之 外，自身也有着非比寻常的力量。现与涂山苏苏进行红娘任务。<br></p>', 'bae685ed-0563-4abd-9e42-e7467b98354f', '白月初', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('3bba6113-8768-49ed-b4b1-650ebde1c1d7', '2016-06-27 12:02:37', '高月', '月儿', '\0', '2016-06-06 00:00:00', 'dsfaewf@qq.com', '410422198902288924', '13256189693', '13256189693', '<p><a target=\"_blank\" href=\"http://baike.baidu.com/subview/400026/8005560.htm\">燕太子丹</a>之女，原是燕国公主，封号“高月”。天真无邪，温柔婉约，在燕国灭亡后和普通百姓一样过着平常生活，但言谈举止间却仍有一股与生俱来的高贵气质。[1]<a>&nbsp;</a>&nbsp;原为<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>女弟子，精通药理，是墨家统领<a target=\"_blank\" href=\"http://baike.baidu.com/subview/968913/10862917.htm\">端木蓉</a>的得力帮手。没有学过武功，却在阴阳巫术方面也有着非比寻常的天赋。后被阴阳家右护法<a target=\"_blank\" href=\"http://baike.baidu.com/view/121800.htm\">月神</a>抓走，一度被消去记忆（后期在斗争中逐渐恢复），带入<a target=\"_blank\" href=\"http://baike.baidu.com/subview/54508/11140653.htm\">阴阳家</a>，被告知真名姓姬，名如，字千泷，拥有延续千年姬姓最尊贵家族的血脉。之后进入<a target=\"_blank\" href=\"http://baike.baidu.com/subview/3480383/9156841.htm\">蜃楼</a>，是解开“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/9767636/15854145.htm\">苍龙七宿</a>”的关键要素之一。<br></p>', '37fa72e4-80d5-440e-b1af-9193b9092cb1', '高月', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('426f49ae-b169-4875-82a1-0b8fbd4915b3', '2016-06-27 13:56:23', '雪女', '赵舞', '\0', '2016-06-20 00:00:00', 'teacherthree@qq.com', '511902198012276047', '13256189693', '13256189693', '<p><a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>统领之一，出身赵国。原是燕地最秀美清丽的舞姬，笑傲王侯。一曲《<a target=\"_blank\" href=\"http://baike.baidu.com/subview/24286/9975231.htm\">白雪</a>》据说能够让最铁石心肠的人落泪。绝技“<a target=\"_blank\" href=\"http://baike.baidu.com/view/4245839.htm\">凌波飞燕</a>”被<a target=\"_blank\" href=\"http://baike.baidu.com/view/3356792.htm\">雁春君</a>称为是“燕国都城的传说”。与燕国乐师<a target=\"_blank\" href=\"http://baike.baidu.com/view/122230.htm\">高渐离</a>是一对令人羡慕的神仙眷侣。虽然因经历过一段不为人知的过去而立誓“终此一生，不会再嫁”，没有与<a target=\"_blank\" href=\"http://baike.baidu.com/subview/122230/7640956.htm\">高渐离</a>结为夫妻，但情深意真，生死相随。<br></p>', '9af4c94d-d344-4808-bf2d-a74bbc073e05', '雪女', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('594c81fc-724b-4cd0-9c71-698c19653e2b', '2016-06-27 11:57:45', '涂山红红', '红红', '\0', '2016-06-18 00:00:00', 'organizationone@qq.com', '410506198704113440', '13256189693', '13256189693', '<p>绝缘仙狐，原妖盟盟主，涂山第一人，涂山“最强战力”。力量型狐妖，也是狐妖之王。温柔体贴又霸气冷漠。有着如日中天强大妖力，金色长发，碧绿瞳孔，标志性的红色蝴蝶结金色铃铛。东方月初深爱着她，因为与东方月初签订再世续缘，以与他相识相知的一点一滴记忆和全部妖力为代价，丧失了全部妖力，而从御姐体型变为萝莉形态。<br></p>', '0c1a2fc8-a4e3-4796-a257-843031c1b8a3', '涂山狐妖', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('5af76e1d-32cf-407f-ac7e-bed2a371b7a6', '2016-06-27 12:00:56', '涂山苏苏', '苏苏', '\0', '2016-05-31 00:00:00', 'black@163.com', '13073219791220236X', '13256189693', '13256189693', '<p>与白月初有婚约，但是自己不想嫁人，想要成为真正的狐妖。原推测为涂山红红的转世，实为<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E6%B6%82%E5%B1%B1%E7%BA%A2%E7%BA%A2\">涂山红红</a>本人，因为失去妖力而从女神变成萝莉，也因为以记忆作为转世续缘的代价而遗忘了一切。双手可以免疫一切法宝，连没有实体的黑狐都能抓住。外表稚嫩无比（因此被雅雅说成妖力，美貌，智慧一点也没有），但仔细看可以看出以后风华绝代的雏形。<br></p>', '36e35467-fc19-425e-b664-2076de93ee4b', '涂山苏苏', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('637e5de7-79cb-4b69-9239-ae0ac3791d10', '2016-06-27 14:02:16', '红仙界', '红仙界', '\0', '2016-06-15 00:00:00', 'teacherthree@qq.com', '', '13256189693', '13256189693', '<p>进入需要安检，传说中的“红仙界”即为此地。<img src=\"http://c.hiphotos.baidu.com/baike/s%3D220/sign=96cba1b8fcedab6470724ac2c737af81/1c950a7b02087bf43815889af5d3572c10dfcfcc.jpg\" alt=\"\"></p><p>代表人物：涂山红红﹑涂山雅雅﹑涂山容容﹑涂山苏苏﹑白月初﹑颜如玉</p>', 'aa459d77-8c4d-4f6d-8b3c-4b4aa6b27c05', '红仙界', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('64ac9152-4fcd-428d-ada3-dad63b231170', '2016-06-27 14:14:22', '一气道盟', '一气道盟', '', '2016-06-22 00:00:00', 'teacherthree@qq.com', '411401197803067143', '13256189693', '13256189693', '<p>传说在一千多年以前，所有修道之人成立了一个前所未有的强大联盟就叫一气道盟。位于人类区。这个道盟一直存在至今，长盛不衰，内里人才济济。其中一又以王、李、张三家最强也最为富有。</p><p>百年前的盟主为<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E4%B8%9C%E6%96%B9%E6%9C%88%E5%88%9D\">东方月初</a>，现盟主为王富贵的老爸。</p><p>代表人物：王权富贵﹑东方月初﹑王富贵﹑白裘恩</p><p>（注：白月初原为一气道盟成员，后因一气道盟百年前的协议故归于涂山）</p>', 'd6b3ccee-b076-4133-b29e-b4d792b5306f', '一气道盟', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('7f81fdf1-f574-40aa-bafa-f2851b8b4cf8', '2016-06-27 13:51:23', '高渐离', '小高', '', '2016-06-30 00:00:00', 'black@163.com', '370501199005277235', '13256189693', '13256189693', '<p>燕国琴师，气质忧郁高雅，容颜俊美。原在燕国酒肆、<a target=\"_blank\" href=\"http://baike.baidu.com/view/5283480.htm\">妃雪阁</a>等多地弹琴，较有名气。后成为<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>统领，被墨家众人称作“小高”，武功在六国灭亡后仅存的墨家成员中仅次于<a target=\"_blank\" href=\"http://baike.baidu.com/subview/1096245/7542147.htm\">墨家巨子</a><a target=\"_blank\" href=\"http://baike.baidu.com/subview/1379987/18182774.htm\">燕丹</a>，地位上也和<a target=\"_blank\" href=\"http://baike.baidu.com/view/2828199.htm\">班大师</a>一样仅次于巨子。</p><p>高渐离与<a target=\"_blank\" href=\"http://baike.baidu.com/view/3162370.htm\">旷修</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/17670/6050337.htm\">荆轲</a>二人互为知己，尤其敬重大哥<a target=\"_blank\" href=\"http://baike.baidu.com/subview/17670/6050337.htm\">荆轲</a>，易水河畔与荆轲相和的一首“<a target=\"_blank\" href=\"http://baike.baidu.com/view/1379728.htm\">风萧萧兮易水寒</a>，壮士一去兮不复还”流传千古。[1]<a>&nbsp;</a>&nbsp;高渐离和<a target=\"_blank\" href=\"http://baike.baidu.com/subview/88527/7542049.htm\">雪女</a>生死相随，感情深厚，其击筑曲目“阳春”与雪女的箫声“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/24286/9975231.htm\">白雪</a>”合称“阳春白雪”，在燕国乃至天下皆堪称一绝。</p>', '7232da68-29f2-44f9-a4e6-4965ef5935e7', '小高', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('941df5a9-fffe-4692-818d-38fb4f5ea65e', '2016-06-27 14:03:21', '星魂', '星魂', '', '2016-06-15 00:00:00', 'organizationtwo@qq.com', '450621198408182333', '13256189693', '13256189693', '<p><a target=\"_blank\" href=\"http://baike.baidu.com/subview/54508/11140653.htm\">阴阳家</a>左护法，小小年纪便与<a target=\"_blank\" href=\"http://baike.baidu.com/subview/121800/5063870.htm\">月神</a>并列为秦国两大护国法师之一的天才少年。无论在武学修为上还是<a target=\"_blank\" href=\"http://baike.baidu.com/subview/400255/16839920.htm\">阴阳术</a>方面都已达到了常人穷尽一生努力都无法到达的高度。拥有极端可怕的杀伤力，实力凌驾于阴阳家五大长老之上。稚气未脱，却有着犀利的眼神以及超乎常人的洞察力，仿佛能洞穿人心。<br></p>', 'ad1afcbf-48be-4bf5-9ab5-f9a52eb7d42a', '星魂', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('9e120d50-7951-4fe8-91f3-2dfe370823d3', '2016-06-27 11:54:30', '流沙', '术以知奸,以刑止刑', '', '2016-06-01 00:00:00', 'liusha@qq.com', '37152319930926001X', '13256189693', '13256189693', '<p>刺客组织，由<a target=\"_blank\" href=\"http://baike.so.com/doc/2718855-2870174.html\">韩非子</a>生前创立，已达“术以知奸，以刑止刑”的目的，韩非死后，其旧日同窗卫庄一直在调查其死因。由于组织内杀手武艺高超，聚散流沙几乎什么人都杀，只要雇主给得起钱，王侯百姓无一幸免。<br></p>', '03910867-7c81-441f-a034-e2a4c2eb5fa7', '刺客组织', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('a3ff7fea-cee4-49c1-8ba5-c973580a4f30', '2016-06-27 13:47:56', '班大师', '班大师', '', '2016-06-15 00:00:00', 'dsfaewf@qq.com', '45010119750224429X', '13256189693', '13256189693', '<p>墨子后裔，老者。<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>统领中地位最高者之一，<a target=\"_blank\" href=\"http://baike.baidu.com/view/8804409.htm\">非攻机关术</a>专家，以善于发明各种机关而闻名。木匠出身，由于幼年的一次意外导致失去一条手臂，从此开始钻研和制造各种机关。看起来是个糊涂的老头儿，实际上在<a target=\"_blank\" href=\"http://baike.baidu.com/view/172951.htm\">机关术</a>上有着天人一般的精湛技艺。<br></p>', '67b153f9-6ff4-461d-9106-2f9c6cd647a2', '班大师', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('aad465b4-b847-42d0-a0c5-1edb3a43e0b3', '2016-06-27 14:17:23', '盖聂', '剑圣', '', '2016-06-17 00:00:00', 'organizationthree@qq.com', '429006197801173478', '13256189693', '13256189693', '<p>气质从容淡定，处事冷静。师出<a target=\"_blank\" href=\"http://baike.baidu.com/view/1228923.htm\">鬼谷</a>，剑术出神入化，被称为<a target=\"_blank\" href=\"http://baike.baidu.com/view/113333.htm\">秦国</a>最强的剑客，在江湖上享有“<a target=\"_blank\" href=\"http://baike.baidu.com/view/264128.htm\">剑圣</a>”的名号，但是却意外地从秦国叛逃，带着故人<a target=\"_blank\" href=\"http://baike.baidu.com/subview/17670/6050337.htm\">荆轲</a>之子<a target=\"_blank\" href=\"http://baike.baidu.com/view/1090918.htm\">荆天明</a>，亡命天涯。佩名剑“<a target=\"_blank\" href=\"http://baike.baidu.com/view/1504590.htm\">渊虹</a>”。渊虹剑折断后改用木剑。<br></p>', 'd853678b-c020-4568-9a3d-79667a19b326', '剑圣', '', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('ae655b22-43f9-4b2f-accd-c996a9aa23f1', '2016-06-27 12:05:20', '天明', '天明', '', '2016-06-08 00:00:00', 'organizationone@qq.com', '150623198108213670', '13256189693', '13256189693', '<p>父亲是<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>绝顶剑客<a target=\"_blank\" href=\"http://baike.baidu.com/subview/17670/6050337.htm\">荆轲</a>，母亲是倾国倾城的<a target=\"_blank\" href=\"http://baike.baidu.com/subview/1199186/10017111.htm\">丽姬</a>。为人古灵精怪却又有些不知天高地厚，但十分重义气。他迷离的身世牵动整片大地的风云变幻，但自己并不知情。[1]<a>&nbsp;</a>&nbsp;因身世原因而被<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2389/5397435.htm\">秦始皇</a>追杀，流落街头。后被“剑圣”<a target=\"_blank\" href=\"http://baike.baidu.com/subview/937924/7789628.htm\">盖聂</a>找到，对盖聂十分敬仰，渐生父子之情，跟随盖聂学习剑术。在逃亡的过程中，天明陆续结识了众多江湖高人，并相继与年纪相仿的<a target=\"_blank\" href=\"http://baike.baidu.com/view/1717216.htm\">项少羽</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/965905/5964000.htm\">高月</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/206655/7605858.htm\">石兰</a>成为好友，也见证了世间最后一片净土<a target=\"_blank\" href=\"http://baike.baidu.com/view/3298847.htm\">墨家机关城</a>的毁灭。机缘巧合之下他成为<a target=\"_blank\" href=\"http://baike.baidu.com/subview/1096245/7542147.htm\">墨家巨子</a>。磨砺中天明渐渐成长，危难时刻，他甚至挺身而出，凭借自己弱小的肩膀保护着朋友们的性命。然而这一切事件的背后似乎都有着一只看不见的手在操控，江湖神秘势力<a target=\"_blank\" href=\"http://baike.baidu.com/subview/54508/11140653.htm\">阴阳家</a>的存在渐渐浮出水面。处于阴谋核心的天明，由此层层剥开了自己身世以及所有阴谋背后的惊天秘密。<br></p>', '4d159845-6ae3-4527-bf13-a01d83a9e068', '天明', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('bf2bafcf-e72e-4469-90f9-bb86bfd8ec7c', '2016-06-27 14:12:23', '涂山容容', '容容', '\0', '2016-06-14 00:00:00', 'organizationthree@qq.com', '410184198201082141', '13256189693', '13256189693', '<p>千面妖狐涂山容容，算无遗策的涂山二当家，有千面妖容的称号，被称为涂山的“最强智囊”（目前的最强战力是<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E6%B6%82%E5%B1%B1%E9%9B%85%E9%9B%85\">涂山雅雅</a>）<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E6%B6%82%E5%B1%B1%E9%9B%85%E9%9B%85\">涂山雅雅</a>的妹妹，颜如玉的师父。涂山管账，随身携带算盘。<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E6%B6%82%E5%B1%B1%E7%BA%A2%E7%BA%A2\">涂山红红</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E6%B6%82%E5%B1%B1%E9%9B%85%E9%9B%85\">涂山雅雅</a>的妹妹，<a target=\"_blank\" href=\"http://baike.baidu.com/item/%E6%B6%82%E5%B1%B1%E8%8B%8F%E8%8B%8F\">涂山苏苏</a>的姐姐。<br></p>', 'cb4c706c-9e65-44c3-be06-4c88acabe15d', '涂山容容', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('bfd3d160-9d43-43e1-9340-7fce2450daf1', '2016-06-27 13:55:14', '石兰', '虞姬', '\0', '2016-07-02 00:00:00', 'organizationtwo@qq.com', '331004198808164403', '13256189693', '13256189693', '<p>表面上是<a target=\"_blank\" href=\"http://baike.baidu.com/subview/3155333/7945551.htm\">庖丁</a>在<a target=\"_blank\" href=\"http://baike.baidu.com/subview/721713/14999948.htm\">桑海</a>的“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/479172/16556047.htm\">有间客栈</a>”中打杂的小伙计，外表柔弱，唯唯诺诺，总是无害的、默默关注着周遭所发生的一切。其真实身份是<a target=\"_blank\" href=\"http://baike.baidu.com/subview/376309/16744217.htm\">蜀山</a>的虞渊护卫之一，本名<a target=\"_blank\" href=\"http://baike.baidu.com/view/9395545.htm\">小虞</a>，背负着神秘而重要的任务，女扮男装在<a target=\"_blank\" href=\"http://baike.baidu.com/subview/721713/14999948.htm\">桑海</a>的“有间客栈”中做小伙计，其实是为了打探<a target=\"_blank\" href=\"http://baike.baidu.com/subview/3480383/9156841.htm\">蜃楼</a>的情报而来。有时化身为月下的夜行人，如精灵一般身轻如燕，身手矫捷，有一双冰冷的眼眸。<br></p>', '85d3d77c-db99-4637-a1d2-6efbf9196670', '石兰', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('c5791374-399a-4f76-bd0d-8e075c0bfc25', '2016-06-27 14:07:50', '墨家', '墨家', '', '2016-06-14 00:00:00', 'dsfaewf@qq.com', '140601199001163014', '13256189693', '13256189693', '<p>墨家是中国<a target=\"_blank\" href=\"http://baike.baidu.com/view/26914.htm\">东周</a>时期的<a target=\"_blank\" href=\"http://baike.baidu.com/view/3330.htm\">哲学</a>派别，诸子百家之一，与孔子所代表的儒家、老子所代表的道家共同构成了中国古代三大哲学体系，法家代表韩非子称其和儒家为“世之显学”，而儒家代表孟子也曾说“天下之言，不归杨（杨朱，道家代表人物）则归墨（墨子）”等语，证明了墨家思想曾经在中国的辉煌。杨墨本是硬币的两个面，故杨墨互补，然古往今来人们都以孟子“距杨墨”的一般思维方式评价杨墨，这是有失公允的[1]<a>&nbsp;</a>&nbsp;。</p><p>墨家约产生于<a target=\"_blank\" href=\"http://baike.baidu.com/subview/20236/5138173.htm\">战国</a>时期。创始人为<a target=\"_blank\" href=\"http://baike.baidu.com/view/46556.htm\">墨翟</a>（墨子）。墨家是一个纪律严密的学术团体，其首领称“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/821352/10256971.htm\">巨子</a>”，其成员到各国为官必须推行墨家主张，所得俸禄亦须向团体奉献。<a target=\"_blank\" href=\"http://baike.baidu.com/view/188287.htm\">墨家学派</a>有前后期之分：前期思想主要涉及社会政治、伦理及认识论问题，关注现世战乱；后期墨家在逻辑学方面有重要贡献，开始向科学研究领域靠拢。</p><p>墨家的主要思想主张是：人与人之间平等的相爱（兼爱），反对侵略战争（<a target=\"_blank\" href=\"http://baike.baidu.com/subview/448362/10824028.htm\">非攻</a>），推崇节约、反对铺张浪费（节用），重视继承前人的文化财富（<a target=\"_blank\" href=\"http://baike.baidu.com/view/5943471.htm\">明鬼</a>），掌握自然规律（天志）等。</p><p>因为墨家思想独有的政治属性，兼之西汉<a target=\"_blank\" href=\"http://baike.baidu.com/subview/17163/11853616.htm\">汉武帝</a>“罢黜百家，独尊儒术”的官学勾结政策，墨家不断遭到打压，并逐渐失去了存身的现实基础，墨家思想在中国逐渐灭绝；直到清末民初，学者们才从故纸堆中重新挖出墨家，并发现其进步性。近年来经过一些新墨者的努力，墨家学说中的一些有益观点开始进入人们的视野。</p>', 'bb20ef9d-a596-4aa3-8b67-f689df080690', '墨家', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('c69e713e-9a3a-43fc-a417-f07747022dcc', '2016-06-27 13:53:32', '月神', '月神', '', '2016-06-14 00:00:00', 'teacherthree@qq.com', '321324198102234449', '13256189693', '13256189693', '<p>姬姓，阴阳家右护法，秦国两大<a target=\"_blank\" href=\"http://baike.baidu.com/view/413182.htm\">护国法师</a>之一，<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2389/5397435.htm\">秦始皇</a>最信任的阴阳家大巫。精通占星，具有预知能力，同时还有控制他人精神和未知的强大破坏力。辅佐秦始皇左右，行踪言辞都很神秘，用纱布遮住的双眼仿佛隐藏了什么阴谋。<br></p>', '7a7095ec-07d6-407d-aadb-19dca0b1beae', '月神', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('c7f6640a-d0a9-4004-b232-142590580336', '2016-06-27 12:04:03', '赤练', '赤练', '\0', '2016-06-23 00:00:00', 'dsfaewf@qq.com', '230403198807032549', '13256189693', '13256189693', '<p>原为韩国公主，封号“<a target=\"_blank\" href=\"http://baike.baidu.com/view/194766.htm\">红莲</a>”。后成为“<a target=\"_blank\" href=\"http://baike.baidu.com/view/3380407.htm\">聚散流沙</a>”四天王之一。妩媚妖娆，精通各类毒术，善于控制各类毒蛇，而她的性感往往比毒药更加危险。她的<a target=\"_blank\" href=\"http://baike.baidu.com/view/4932743.htm\">火魅术</a>可以迷惑看见她双瞳的人，使之产生幻觉。<br></p>', '46cca829-cd11-4ce1-bbb9-f50e16f11201', '赤练', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('d20d2186-c65d-40f7-ade0-4a590f7ffda1', '2016-06-27 12:36:52', '盗跖', '盗王之王', '', '2016-06-01 00:00:00', 'black@163.com', '610929197508102292', '13256189693', '13256189693', '<p><a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>统领之一，天下第一神偷，号称“盗王之王”，身材纤瘦，擅长飞檐走壁，轻功卓越，绝技是“<a target=\"_blank\" href=\"http://baike.baidu.com/view/9198534.htm\">电光神行步</a>”。虽然生性油滑，嘴巴不老实，看似不太可靠，但骨子里也有着认真、执着的一面，危难时刻绝不临阵脱逃。讨厌<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2176/5070682.htm\">孔子</a>以及<a target=\"_blank\" href=\"http://baike.baidu.com/subview/51931/17163404.htm\">儒家</a>的那些大道理，但不排斥儒家中同为反秦势力的人。<br></p>', '60d7b4a6-4703-4497-b24c-9428fd545685', '盗跖', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('f052e12a-533b-41d9-9eda-77ac28c8cf14', '2016-06-27 14:10:48', '涂山雅雅', '雅雅大姐', '\0', '2016-06-23 00:00:00', 'organizationtwo@qq.com', '520321198809269765', '13256189693', '13256189693', '<p>幼年似乎对东方月初有好感，但年幼时没有了解涂山红红的心结，无法理解东方月初离开涂山，加入一气道盟的目的，对其生怨，因此对于东方月初转世，认为多死几次好呢。曾经几次发起挑战，屡战屡败，随身携带的法宝无尽酒壶被东方月初提上了“雅”。年幼时法力依然很强，曾经单人打倒一气道盟的众人。涂山红红决战之前将涂山托付给她，并叮嘱她“招式上不要太模仿姐姐的打法，要多用冰系法术”，于是500年来只修寒气，先法力却还是涂山第二，九尾全开时几乎无人能敌（除了红红）</p><p>雅雅自小喜欢红色，一直身穿一身红衣，在与南国公主的较量中，涂山红红的意识出现，告诉涂山雅雅“你还是没有听姐姐的话，红色不适合你”，于是改穿蓝色。</p>', 'bf65cde7-93f5-43af-9fdb-eb0506524fa3', '涂山雅雅', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('f2778542-027d-4e62-b757-3766dd2bd119', '2016-06-27 13:50:08', '卫庄', '卫庄', '', '2016-06-02 00:00:00', 'dsfaewf@qq.com', '445381198910195239', '13256189693', '13256189693', '<p>韩国人，自小就因不明原因在韩国王宫中生活。<a target=\"_blank\" href=\"http://baike.baidu.com/subview/152/17160757.htm\">纵横家</a>，<a target=\"_blank\" href=\"http://baike.baidu.com/subview/1228923/17213319.htm\">鬼谷</a>横剑术传人，浑身充满邪气与霸气，武功深不可测。最大的目标就是击败师哥<a target=\"_blank\" href=\"http://baike.baidu.com/subview/937924/7789628.htm\">盖聂</a>，继承鬼谷绝学，为此他一度与秦朝合作。后在韩国故人<a target=\"_blank\" href=\"http://baike.baidu.com/subview/9442/5565691.htm\">张良</a>的劝解下，与盖聂、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/2748/14869969.htm\">墨家</a>等反秦势力达成暂时的利益合作关系。</p><p>卫庄同时也是“<a target=\"_blank\" href=\"http://baike.baidu.com/subview/37005/8143709.htm\">流沙</a>”组织的现任最高首领。卫庄一直在暗中调查昔日好友——“流沙”创立者<a target=\"_blank\" href=\"http://baike.baidu.com/subview/25728/9572794.htm\">韩非</a>的死因。</p>', '68589822-8d32-4772-97e3-df3cbeacfc1f', '卫庄', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('f2d84530-61c0-4a9f-a0b4-eca5a1854435', '2016-06-27 12:06:50', '项羽', '霸王', '', '2016-06-01 00:00:00', 'teacherthree@qq.com', '341221199102161271', '13256189693', '13256189693', '<p>楚将<a target=\"_blank\" href=\"http://baike.baidu.com/view/400029.htm\">项燕</a>之孙，被秦国用重金悬赏的楚国项氏一族少主。天赋异禀，有千斤拔鼎之神力。智勇双全，年纪虽小，临阵决敌却已有大将之风，为人仗义。后在<a target=\"_blank\" href=\"http://baike.baidu.com/subview/721713/14999948.htm\">桑海</a>与<a target=\"_blank\" href=\"http://baike.baidu.com/subview/376309/16744217.htm\">蜀山</a>少女<a target=\"_blank\" href=\"http://baike.baidu.com/subview/206655/7605858.htm\">石兰</a>互生好感。<br></p>', '4feabd56-38cf-41a3-99e3-54c27e693965', '少羽', '\0', '\0', null);
INSERT INTO `ballchen_t_user_basic` VALUES ('fde170cb-3a98-4626-93f0-d816b178e205', '2016-06-27 13:57:42', '大司命', '大司命', '\0', '2016-06-23 00:00:00', 'black@163.com', '41040319770928416X', '13256189693', '13256189693', '<p>掌管五行派系中的火部，与<a target=\"_blank\" href=\"http://baike.baidu.com/subview/81210/5073958.htm\">云中君</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/41360/7348634.htm\">少司命</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/107284/12670395.htm\">湘君</a>、<a target=\"_blank\" href=\"http://baike.baidu.com/subview/107296/12021322.htm\">湘夫人</a>并列<a target=\"_blank\" href=\"http://baike.baidu.com/subview/54508/11140653.htm\">阴阳家</a>五大长老之一。</p><p>追杀叛逆或异己分子的死亡使者之一，外表妖艳动人，手段阴辣狠毒，擅长使用幻术迷惑敌人，曾成功用“<a target=\"_blank\" href=\"http://baike.baidu.com/view/9173733.htm\">六魂恐咒</a>”杀死燕国太子<a target=\"_blank\" href=\"http://baike.baidu.com/subview/1379987/18182774.htm\">燕丹</a>。</p>', 'a2dcad34-b922-4e25-9c70-155a1b06b96a', '大司命', '\0', '\0', null);
/*初始化附件*/
INSERT INTO `ballchen_t_accessory` VALUES ('461c6d3a-be2a-4b66-a64b-1449d2dac7e5', '2016-08-17 11:43:45', '9922720e0cf3d7ca0237cd7ff31fbe096a63a9f2', 'a3163074-6f13-44bd-a7e9-96ecbf2c944e', '9922720e0cf3d7ca0237cd7ff31fbe096a63a9f2', 'a3163074-6f13-44bd-a7e9-96ecbf2c944e.jpg', '.jpg', 'HEADPICTURE', '325000', 'QINIU', null);
INSERT INTO `ballchen_t_accessory` VALUES ('9e7826d0-6043-4d69-a5e7-78a82346a1ae', '2016-08-17 11:44:51', 'd000baa1cd11728ba1d76038cffcc3cec3fd2c36', 'a9760346-b5a1-442e-8ee3-3e30a1fcd25d', 'd000baa1cd11728ba1d76038cffcc3cec3fd2c36', 'a9760346-b5a1-442e-8ee3-3e30a1fcd25d.jpg', '.jpg', 'HEADPICTURE', '58029', 'QINIU', null);
INSERT INTO `ballchen_t_accessory` VALUES ('db51c629-a900-4857-b183-4d7a7a7c26bd', '2016-08-17 11:05:44', 't01662d77e4f9a769cf', 'e6a86b59-b245-433a-bd2f-4b0731b5c2ea', 't01662d77e4f9a769cf', 'e6a86b59-b245-433a-bd2f-4b0731b5c2ea.jpg', '.jpg', 'HEADPICTURE', '8107', 'QINIU', null);
INSERT INTO `ballchen_t_accessory` VALUES ('e45e8a2b-e159-4f62-a492-303a3a7497dc', '2016-08-17 11:39:07', '01300000167882122106610082987', 'd916f4eb-11b4-45f8-9b0a-7bb462e6c4d8', '01300000167882122106610082987', 'd916f4eb-11b4-45f8-9b0a-7bb462e6c4d8.jpg', '.jpg', 'HEADPICTURE', '18388', 'QINIU', null);
INSERT INTO `ballchen_t_accessory` VALUES ('f9187f67-020a-41de-8247-94f42475b5e9', '2016-08-17 11:40:50', '1914_byGCOL44LM5O59Yoyl4M_square', '6a2047c3-4bb8-4b44-a59d-f688f090ec1b', '1914_byGCOL44LM5O59Yoyl4M_square', '6a2047c3-4bb8-4b44-a59d-f688f090ec1b.png', '.png', 'HEADPICTURE', '76629', 'QINIU', null);
INSERT INTO `ballchen_t_accessory` VALUES ('fdad6627-0a66-4d29-aa91-d8a599465a98', '2016-08-17 11:40:01', '20160529114310_eZsch.thumb.224_0', '2d6a86b1-7c86-4560-8a30-4e103c02c415', '20160529114310_eZsch.thumb.224_0', '2d6a86b1-7c86-4560-8a30-4e103c02c415.jpeg', '.jpeg', 'HEADPICTURE', '17859', 'QINIU', null);
/*初始化用户--附件*/
INSERT INTO `ballchen_t_user_basic_accessory` VALUES ('29dadb00-97c7-4c2d-b875-cb09c0168d05', '2016-08-17 11:43:45', 'c5791374-399a-4f76-bd0d-8e075c0bfc25', '461c6d3a-be2a-4b66-a64b-1449d2dac7e5');
INSERT INTO `ballchen_t_user_basic_accessory` VALUES ('2f6bdb97-7b5a-47d7-96fe-16242adbaf96', '2016-08-17 11:40:01', 'bf2bafcf-e72e-4469-90f9-bb86bfd8ec7c', 'fdad6627-0a66-4d29-aa91-d8a599465a98');
INSERT INTO `ballchen_t_user_basic_accessory` VALUES ('3b4e2544-8ad5-4c7e-83b0-eb9dc0216d0b', '2016-08-17 11:05:44', 'aad465b4-b847-42d0-a0c5-1edb3a43e0b3', 'db51c629-a900-4857-b183-4d7a7a7c26bd');
INSERT INTO `ballchen_t_user_basic_accessory` VALUES ('3dfddf27-2995-41f7-8194-ca37022845e7', '2016-08-17 11:39:07', '64ac9152-4fcd-428d-ada3-dad63b231170', 'e45e8a2b-e159-4f62-a492-303a3a7497dc');
INSERT INTO `ballchen_t_user_basic_accessory` VALUES ('e2d1512e-5cf7-411d-9528-540caf5056fc', '2016-08-17 11:44:51', '2e542a77-7351-49fd-8f27-bc0404d17595', '9e7826d0-6043-4d69-a5e7-78a82346a1ae');
INSERT INTO `ballchen_t_user_basic_accessory` VALUES ('f07c00e3-ad4a-4a45-9738-911be351fb69', '2016-08-17 11:40:50', 'f052e12a-533b-41d9-9eda-77ac28c8cf14', 'f9187f67-020a-41de-8247-94f42475b5e9');
