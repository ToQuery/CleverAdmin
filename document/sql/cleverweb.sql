/*
navicat mysql data transfer

source server         : mysql
source server version : 50618
source host           : localhost:3306
source database       : cleverweb

target server type    : mysql
target server version : 50618
file encoding         : 65001

date: 2016-04-12 01:07:33
*/

set foreign_key_checks=0;

-- ----------------------------
-- table structure for db_fhdb
-- ----------------------------
drop table if exists `db_fhdb`;
create table `db_fhdb` (
  `fhdb_id` varchar(100) not null,
  `username` varchar(50) default null comment '操作用户',
  `backup_time` varchar(32) default null comment '备份时间',
  `tablename` varchar(50) default null comment '表名',
  `sqlpath` varchar(300) default null comment '存储位置',
  `type` int(1) not null comment '类型',
  `dbsize` varchar(10) default null comment '文件大小',
  `bz` varchar(255) default null comment '备注',
  primary key (`fhdb_id`)
) engine=innodb default charset=utf8;

CREATE TABLE `tb_sys_area` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `parent_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `short_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '区域编码',
  `type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='区域表';


CREATE TABLE `tb_sys_dict` (
  `dict_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '数据值',
  `label` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `type` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) COLLATE utf8_bin DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`dict_id`),
  KEY `tb_sys_dict_value` (`value`),
  KEY `tb_sys_dict_label` (`label`),
  KEY `tb_sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- records of db_fhdb
-- ----------------------------

-- ----------------------------
-- table structure for db_timingbackup
-- ----------------------------
drop table if exists `db_timingbackup`;
create table `db_timingbackup` (
  `timingbackup_id` varchar(100) not null,
  `jobname` varchar(50) default null comment '任务名称',
  `create_time` varchar(32) default null comment '创建时间',
  `tablename` varchar(50) default null comment '表名',
  `status` int(1) not null comment '类型',
  `fhtime` varchar(30) default null comment '时间规则',
  `timeexplain` varchar(100) default null comment '规则说明',
  `bz` varchar(255) default null comment '备注',
  primary key (`timingbackup_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of db_timingbackup
-- ----------------------------
insert into `db_timingbackup` values ('311e06c34a5e4518a86d5d30799f9b55', 'sys_app_user_515762', '2016-04-11 17:04:55', 'sys_app_user', '2', '1/2 * * ? * *', '每个月的 每周 每天 每小时执行一次', '备份任务');

-- ----------------------------
-- table structure for oa_department
-- ----------------------------
drop table if exists `oa_department`;
create table `oa_department` (
  `department_id` varchar(100) not null,
  `name` varchar(30) default null comment '名称',
  `name_en` varchar(50) default null comment '英文',
  `bianma` varchar(50) default null comment '编码',
  `parent_id` varchar(100) default null comment '上级id',
  `bz` varchar(255) default null comment '备注',
  `headman` varchar(30) default null comment '负责人',
  `tel` varchar(50) default null comment '电话',
  `functions` varchar(255) default null comment '部门职能',
  `address` varchar(255) default null comment '地址',
  primary key (`department_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of oa_department
-- ----------------------------
insert into `oa_department` values ('0956d8c279274fca92f4091f2a69a9ad', '销售会计', 'xiaokuai', '05896', 'd41af567914a409893d011aa53eda797', '', '', '', '', '');
insert into `oa_department` values ('3e7227e11dc14b4d9e863dd1a1fcedf6', '成本会计', 'chengb', '03656', 'd41af567914a409893d011aa53eda797', '', '', '', '', '');
insert into `oa_department` values ('5cccdb7c432449d8b853c52880058140', 'b公司', 'b', '002', '0', '冶铁', '李四', '112', '冶铁', '河北');
insert into `oa_department` values ('83a25761c618457cae2fa1211bd8696d', '销售b组', 'xiaob', '002365', 'cbbc84eddde947ba8af7d509e430eb70', '', '李四', '', '', '');
insert into `oa_department` values ('8f8b045470f342fdbc4c312ab881d62b', '销售a组', 'xiaoa', '0326', 'cbbc84eddde947ba8af7d509e430eb70', '', '张三', '0201212', '', '');
insert into `oa_department` values ('a0982dea52554225ab682cd4b421de47', '1队', 'yidui', '02563', '8f8b045470f342fdbc4c312ab881d62b', '', '小王', '12356989', '', '');
insert into `oa_department` values ('a6c6695217ba4a4dbfe9f7e9d2c06730', 'a公司', 'a', '001', '0', '挖煤', '张三', '110', '洼煤矿', '山西');
insert into `oa_department` values ('cbbc84eddde947ba8af7d509e430eb70', '销售部', 'xiaoshoubu', '00201', '5cccdb7c432449d8b853c52880058140', '推销商品', '小明', '11236', '推销商品', '909办公室');
insert into `oa_department` values ('d41af567914a409893d011aa53eda797', '财务部', 'caiwubu', '00101', 'a6c6695217ba4a4dbfe9f7e9d2c06730', '负责发工资', '王武', '11236', '管理财务', '308办公室');

-- ----------------------------
-- table structure for sys_app_user
-- ----------------------------
drop table if exists `sys_app_user`;
create table `sys_app_user` (
  `user_id` varchar(100) not null,
  `username` varchar(255) default null,
  `password` varchar(255) default null,
  `name` varchar(255) default null,
  `rights` varchar(255) default null,
  `role_id` varchar(100) default null,
  `last_login` varchar(255) default null,
  `ip` varchar(100) default null,
  `status` varchar(32) default null,
  `bz` varchar(255) default null,
  `phone` varchar(100) default null,
  `sfid` varchar(100) default null,
  `start_time` varchar(100) default null,
  `end_time` varchar(100) default null,
  `years` int(10) default null,
  `number` varchar(100) default null,
  `email` varchar(32) default null,
  primary key (`user_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of sys_app_user
-- ----------------------------
insert into `sys_app_user` values ('1e89e6504be349a68c025976b3ecc1d1', 'a1', '698d51a19d8a121ce581499d7b701668', '会员甲', '', '115b386ff04f4352b060dffcd2b5d1da', '', '', '1', '121', '1212', '1212', '2015-12-02', '2015-12-25', '2', '111', '313596790@qq.com');
insert into `sys_app_user` values ('ead1f56708e4409c8d071e0a699e5633', 'a2', 'bcbe3365e6ac95ea2c0343a2395834dd', '会员乙', '', '1b67fc82ce89457a8347ae53e43a347e', '', '', '0', '', '', '', '2015-12-01', '2015-12-24', '1', '121', '978336446@qq.com');

-- ----------------------------
-- table structure for sys_createcode
-- ----------------------------
drop table if exists `sys_createcode`;
create table `sys_createcode` (
  `createcode_id` varchar(100) not null,
  `packagename` varchar(50) default null comment '包名',
  `objectname` varchar(50) default null comment '类名',
  `tablename` varchar(50) default null comment '表名',
  `fieldlist` varchar(5000) default null comment '属性集',
  `createtime` varchar(100) default null comment '创建时间',
  `title` varchar(255) default null comment '描述',
  primary key (`createcode_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of sys_createcode
-- ----------------------------
insert into `sys_createcode` values ('002ea762e3e242a7a10ea5ca633701d8', 'system', 'buttonrights', 'sys_,fh,buttonrights', 'name,fh,string,fh,名称,fh,是,fh,无,fh,255q313596790', '2016-01-16 23:20:36', '按钮权限');
insert into `sys_createcode` values ('49d985e081ed44e6b34ba1b8c5466e39', 'fhdb', 'timingbackup', 'db_,fh,timingbackup', 'jobname,fh,string,fh,任务名称,fh,否,fh,无,fh,50q313596790create_time,fh,date,fh,创建时间,fh,否,fh,无,fh,32q313596790tablename,fh,string,fh,表名,fh,是,fh,无,fh,50q313596790type,fh,integer,fh,类型,fh,否,fh,无,fh,1q313596790fhtime,fh,string,fh,时间规则,fh,是,fh,无,fh,30q313596790timeexplain,fh,string,fh,规则说明,fh,是,fh,无,fh,100q313596790bz,fh,string,fh,备注,fh,是,fh,无,fh,255q313596790', '2016-04-09 11:53:38', '定时备份');
insert into `sys_createcode` values ('bf35ab8b2d064bf7928a04bba5e5a6dd', 'system', 'fhsms', 'sys_,fh,fhsms', 'content,fh,string,fh,内容,fh,是,fh,无,fh,1000q313596790type,fh,string,fh,类型,fh,否,fh,无,fh,5q313596790to_username,fh,string,fh,收信人,fh,是,fh,无,fh,255q313596790from_username,fh,string,fh,发信人,fh,是,fh,无,fh,255q313596790send_time,fh,string,fh,发信时间,fh,是,fh,无,fh,100q313596790status,fh,string,fh,状态,fh,否,fh,无,fh,5q313596790sanme_id,fh,string,fh,共同id,fh,是,fh,无,fh,100q313596790', '2016-03-27 21:39:45', '站内信');
insert into `sys_createcode` values ('c7586f931fd44c61beccd3248774c68c', 'system', 'department', 'sys_,fh,department', 'name,fh,string,fh,名称,fh,是,fh,无,fh,30q313596790name_en,fh,string,fh,英文,fh,是,fh,无,fh,50q313596790bianma,fh,string,fh,编码,fh,是,fh,无,fh,50q313596790parent_id,fh,string,fh,上级id,fh,否,fh,无,fh,100q313596790bz,fh,string,fh,备注,fh,是,fh,无,fh,255q313596790headman,fh,string,fh,负责人,fh,是,fh,无,fh,30q313596790tel,fh,string,fh,电话,fh,是,fh,无,fh,50q313596790functions,fh,string,fh,部门职能,fh,是,fh,无,fh,255q313596790address,fh,string,fh,地址,fh,是,fh,无,fh,255q313596790', '2015-12-20 01:49:25', '组织机构');
insert into `sys_createcode` values ('c937e21208914e5b8fb1202c685bbf2f', 'fhdb', 'fhdb', 'db_,fh,fhdb', 'username,fh,string,fh,操作用户,fh,否,fh,无,fh,50q313596790backup_time,fh,date,fh,备份时间,fh,否,fh,无,fh,32q313596790tablename,fh,string,fh,表名,fh,是,fh,无,fh,50q313596790sqlpath,fh,string,fh,存储位置,fh,否,fh,无,fh,300q313596790type,fh,integer,fh,类型,fh,是,fh,无,fh,1q313596790dbsize,fh,string,fh,文件大小,fh,否,fh,无,fh,10q313596790bz,fh,string,fh,备注,fh,否,fh,无,fh,255q313596790', '2016-03-30 13:46:54', '数据库管理');
insert into `sys_createcode` values ('d514dbd2474d4b6c8b6ab9904cc9cc7c', 'new', 'news', 'tb_,fh,news', 'title,fh,string,fh,标题,fh,是,fh,无,fh,255q313596790', '2016-01-25 16:38:14', '新闻管理');
insert into `sys_createcode` values ('dbd7b8330d774dcabd184eca8668a295', 'system', 'fhsms', 'sys_,fh,fhsms', 'content,fh,string,fh,内容,fh,是,fh,无,fh,1000q313596790type,fh,string,fh,类型,fh,否,fh,无,fh,5q313596790to_username,fh,string,fh,收信人,fh,是,fh,无,fh,255q313596790from_username,fh,string,fh,发信人,fh,是,fh,无,fh,255q313596790send_time,fh,string,fh,发信时间,fh,是,fh,无,fh,100q313596790status,fh,string,fh,状态,fh,否,fh,无,fh,5q313596790sanme_id,fh,string,fh,共同id,fh,是,fh,无,fh,100q313596790', '2016-01-23 01:44:15', '站内信');
insert into `sys_createcode` values ('fe239f8742194481a5b56f90cad71520', 'system', 'fhbutton', 'sys_,fh,fhbutton', 'name,fh,string,fh,名称,fh,是,fh,无,fh,30q313596790qx_name,fh,string,fh,权限标识,fh,是,fh,无,fh,50q313596790bz,fh,string,fh,备注,fh,是,fh,无,fh,255q313596790', '2016-01-15 18:38:40', '按钮管理');

-- ----------------------------
-- table structure for sys_dictionaries
-- ----------------------------
drop table if exists `sys_dictionaries`;
create table `sys_dictionaries` (
  `dictionaries_id` varchar(100) not null,
  `name` varchar(30) default null comment '名称',
  `name_en` varchar(50) default null comment '英文',
  `bianma` varchar(50) default null comment '编码',
  `order_by` int(11) not null comment '排序',
  `parent_id` varchar(100) default null comment '上级id',
  `bz` varchar(255) default null comment '备注',
  `tbsname` varchar(100) default null comment '排查表',
  primary key (`dictionaries_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of sys_dictionaries
-- ----------------------------
insert into `sys_dictionaries` values ('096e4ec8986149d994b09e604504e38d', '黄浦区', 'huangpu', '0030201', '1', 'f1ea30ddef1340609c35c88fb2919bee', '黄埔', '');
insert into `sys_dictionaries` values ('12a62a3e5bed44bba0412b7e6b733c93', '北京', 'beijing', '00301', '1', 'be4a8c5182c744d28282a5345783a77f', '北京', '');
insert into `sys_dictionaries` values ('507fa87a49104c7c8cdb52fdb297da12', '宣武区', 'xuanwuqu', '0030101', '1', '12a62a3e5bed44bba0412b7e6b733c93', '宣武区', '');
insert into `sys_dictionaries` values ('8994f5995f474e2dba6cfbcdfe5ea07a', '语文', 'yuwen', '00201', '1', 'fce20eb06d7b4b4d8f200eda623f725c', '语文', '');
insert into `sys_dictionaries` values ('8ea7c44af25f48b993a14f791c8d689f', '分类', 'fenlei', '001', '1', '0', '分类', '');
insert into `sys_dictionaries` values ('be4a8c5182c744d28282a5345783a77f', '地区', 'diqu', '003', '3', '0', '地区', '');
insert into `sys_dictionaries` values ('d428594b0494476aa7338d9061e23ae3', '红色', 'red', '00101', '1', '8ea7c44af25f48b993a14f791c8d689f', '红色', '');
insert into `sys_dictionaries` values ('de9afadfbed0428fa343704d6acce2c4', '绿色', 'green', '00102', '2', '8ea7c44af25f48b993a14f791c8d689f', '绿色', '');
insert into `sys_dictionaries` values ('f1ea30ddef1340609c35c88fb2919bee', '上海', 'shanghai', '00302', '2', 'be4a8c5182c744d28282a5345783a77f', '上海', '');
insert into `sys_dictionaries` values ('fce20eb06d7b4b4d8f200eda623f725c', '课程', 'kecheng', '002', '2', '0', '课程', '');

-- ----------------------------
-- table structure for tb_sys_button
-- ----------------------------
drop table if exists `tb_sys_button`;
create table `tb_sys_button` (
  `button_id` varchar(100) not null,
  `button_name` varchar(30) default null comment '名称',
  `qx_name` varchar(50) default null comment '权限标识',
  `remark` varchar(255) default null comment '备注',
  primary key (`button_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of tb_sys_button
-- ----------------------------
insert into `tb_sys_button` values ('3542adfbda73410c976e185ffe50ad06', '导出excel', 'toexcel', '导出excel');
insert into `tb_sys_button` values ('46992ea280ba4b72b29dedb0d4bc0106', '发邮件', 'email', '发送电子邮件');
insert into `tb_sys_button` values ('4efa162fce8340f0bd2dcd3b11d327ec', '导入excel', 'fromexcel', '导入excel到系统用户');
insert into `tb_sys_button` values ('cc51b694d5344d28a9aa13c84b7166cd', '发短信', 'sms', '发送短信');
insert into `tb_sys_button` values ('da7fd386de0b49ce809984f5919022b8', '站内信', 'fhsms', '发送站内信');

-- ----------------------------
-- table structure for tb_sys_message
-- ----------------------------
drop table if exists `tb_sys_message`;
create table `tb_sys_message` (
  `message_id` varchar(32) not null,
  `content` varchar(1000) default null comment '内容',
  `type` varchar(5) default null comment '类型',
  `receive_id` varchar(255) default null comment '收信人',
  `send_id` varchar(255) default null comment '发信人',
  `send_time` varchar(100) default null comment '发信时间',
  `status` varchar(5) default null comment '状态',
  `sanme_id` varchar(100) default null,
  primary key (`message_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of sys_fhsms
-- ----------------------------
insert into tb_sys_message values ('05879f5868824f35932ee9f2062adc03', '你好', '2', 'admin', 'san', '2016-01-25 14:05:31', '1', 'b311e893228f42d5a05dbe16917fd16f');
insert into tb_sys_message values ('2635dd035c6f4bb5a091abdd784bd899', '你好', '2', 'san', 'admin', '2016-01-25 14:05:02', '2', '1b7637306683460f89174c2b025862b5');
insert into tb_sys_message values ('52378ccd4e2d4fe08994d1652af87c68', '你好', '1', 'admin', 'san', '2016-01-25 16:26:44', '1', '920b20dafdfb4c09b560884eb277c51d');
insert into tb_sys_message values ('77ed13f9c49a4c4bb460c41b8580dd36', 'gggg', '2', 'admin', 'san', '2016-01-24 21:22:43', '2', 'dd9ee339576e48c5b046b94fa1901d00');
insert into tb_sys_message values ('98a6869f942042a1a037d9d9f01cb50f', '你好', '1', 'admin', 'san', '2016-01-25 14:05:02', '2', '1b7637306683460f89174c2b025862b5');
insert into tb_sys_message values ('9e00295529014b6e8a27019cbccb3da1', '柔柔弱弱', '1', 'admin', 'san', '2016-01-24 21:22:57', '1', 'a29603d613ea4e54b5678033c1bf70a6');
insert into tb_sys_message values ('d3aedeb430f640359bff86cd657a8f59', '你好', '1', 'admin', 'san', '2016-01-24 21:22:12', '1', 'f022fbdce3d845aba927edb698beb90b');
insert into tb_sys_message values ('e5376b1bd54b489cb7f2203632bd74ec', '管理员好', '2', 'admin', 'san', '2016-01-25 14:06:13', '2', 'b347b2034faf43c79b54be4627f3bd2b');
insert into tb_sys_message values ('e613ac0fcc454f32895a70b747bf4fb5', '你也好', '2', 'admin', 'san', '2016-01-25 16:27:54', '2', 'ce8dc3b15afb40f28090f8b8e13f078d');
insert into tb_sys_message values ('f25e00cfafe741a3a05e3839b66dc7aa', '你好', '2', 'san', 'admin', '2016-01-25 16:26:44', '1', '920b20dafdfb4c09b560884eb277c51d');

-- ----------------------------
-- table structure for tb_sys_menu
-- ----------------------------
drop table if exists `tb_sys_menu`;
create table `tb_sys_menu` (
  `menu_id` int(11) not null,
  `menu_name` varchar(255) default null,
  `menu_url` varchar(255) default null,
  `parent_id` varchar(100) default null,
  `menu_order` varchar(100) default null,
  `menu_icon` varchar(60) default null,
  `menu_type` varchar(10) default null,
  `menu_state` int(1) default null,
  primary key (`menu_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of tb_sys_menu
-- ----------------------------
insert into `tb_sys_menu` values ('1', '系统管理', '#', '0', '1', 'menu-icon fa fa-desktop blue', '2', '1');
insert into `tb_sys_menu` values ('2', '权限管理', '#', '1', '1', 'menu-icon fa fa-lock black', '1', '1');
insert into `tb_sys_menu` values ('6', '信息管理', '#', '0', '5', 'menu-icon fa fa-credit-card green', '2', '1');
insert into `tb_sys_menu` values ('7', '图片管理', '#', '6', '1', 'menu-icon fa fa-folder-o pink', '2', '1');
insert into `tb_sys_menu` values ('8', '性能监控', 'druid/index.html', '9', '8', 'menu-icon fa fa-tachometer red', '1', '1');
insert into `tb_sys_menu` values ('9', '系统工具', '#', '0', '3', 'menu-icon fa fa-cog black', '2', '1');
insert into `tb_sys_menu` values ('10', '接口测试', 'tool/interfacetest.do', '9', '2', 'menu-icon fa fa-exchange green', '1', '1');
insert into `tb_sys_menu` values ('11', '发送邮件', 'tool/gosendemail.do', '9', '3', 'menu-icon fa fa-envelope-o green', '1', '1');
insert into `tb_sys_menu` values ('12', '置二维码', 'tool/gotwodimensioncode.do', '9', '4', 'menu-icon fa fa-barcode green', '1', '1');
insert into `tb_sys_menu` values ('14', '地图工具', 'tool/map.do', '9', '6', 'menu-icon fa fa-globe black', '1', '1');
insert into `tb_sys_menu` values ('15', '微信管理', '#', '0', '4', 'menu-icon fa fa-comments purple', '2', '1');
insert into `tb_sys_menu` values ('16', '文本回复', 'textmsg/list.do', '15', '2', 'menu-icon fa fa-comment green', '2', '1');
insert into `tb_sys_menu` values ('17', '应用命令', 'command/list.do', '15', '4', 'menu-icon fa fa-comment grey', '2', '1');
insert into `tb_sys_menu` values ('18', '图文回复', 'imgmsg/list.do', '15', '3', 'menu-icon fa fa-comment pink', '2', '1');
insert into `tb_sys_menu` values ('19', '关注回复', 'textmsg/gosubscribe.do', '15', '1', 'menu-icon fa fa-comment orange', '2', '1');
insert into `tb_sys_menu` values ('20', '在线管理', 'onlinemanager/list.do', '1', '5', 'menu-icon fa fa-laptop green', '1', '1');
insert into `tb_sys_menu` values ('21', '打印测试', 'tool/printtest.do', '9', '7', 'menu-icon fa fa-hdd-o grey', '1', '1');
insert into `tb_sys_menu` values ('22', '一级菜单', '#', '0', '10', 'menu-icon fa fa-fire orange', '2', '1');
insert into `tb_sys_menu` values ('23', '二级菜单', '#', '22', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('24', '三级菜单', '#', '23', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('30', '四级菜单', '#', '24', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('31', '五级菜单1', '#', '30', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('32', '五级菜单2', '#', '30', '2', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('33', '六级菜单', '#', '31', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('34', '六级菜单2', 'login_default.do', '31', '2', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('35', '四级菜单2', 'login_default.do', '24', '2', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('36', '角色(基础权限)', 'role.do', '2', '1', 'menu-icon fa fa-key orange', '1', '1');
insert into `tb_sys_menu` values ('37', '按钮权限', 'buttonrights/list.do', '2', '2', 'menu-icon fa fa-key green', '1', '1');
insert into `tb_sys_menu` values ('38', '菜单管理', 'menu/listallmenu.do', '1', '3', 'menu-icon fa fa-folder-open-o brown', '1', '1');
insert into `tb_sys_menu` values ('39', '按钮管理', 'fhbutton/list.do', '1', '2', 'menu-icon fa fa-download orange', '1', '1');
insert into `tb_sys_menu` values ('40', '用户管理', '#', '0', '2', 'menu-icon fa fa-users blue', '2', '1');
insert into `tb_sys_menu` values ('41', '系统用户', 'user/listusers.do', '40', '1', 'menu-icon fa fa-users green', '1', '1');
insert into `tb_sys_menu` values ('42', '会员管理', 'happuser/listusers.do', '40', '2', 'menu-icon fa fa-users orange', '1', '1');
insert into `tb_sys_menu` values ('43', '数据字典', 'dictionaries/listalldict.do?dictionaries_id=0', '1', '4', 'menu-icon fa fa-book purple', '1', '1');
insert into `tb_sys_menu` values ('44', '代码生成器', 'createcode/list.do', '9', '0', 'menu-icon fa fa-cogs brown', '1', '1');
insert into `tb_sys_menu` values ('45', '七级菜单1', '#', '33', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('46', '七级菜单2', '#', '33', '2', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('47', '八级菜单', 'login_default.do', '45', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('48', '图表报表', ' tool/fusionchartsdemo.do', '9', '5', 'menu-icon fa fa-bar-chart-o black', '1', '1');
insert into `tb_sys_menu` values ('50', '站内信', 'fhsms/list.do', '6', '2', 'menu-icon fa fa-envelope green', '1', '1');
insert into `tb_sys_menu` values ('51', '图片列表', 'pictures/list.do', '7', '1', 'menu-icon fa fa-folder-open-o green', '1', '1');
insert into `tb_sys_menu` values ('52', '图片爬虫', 'pictures/goimagecrawler.do', '7', '2', 'menu-icon fa fa-cloud-download green', '1', '1');
insert into `tb_sys_menu` values ('53', '表单构建器', 'tool/goformbuilder.do', '9', '1', 'menu-icon fa fa-leaf black', '1', '1');
insert into `tb_sys_menu` values ('54', '数据库管理', '#', '0', '9', 'menu-icon fa fa-hdd-o blue', '2', '1');
insert into `tb_sys_menu` values ('55', '数据库备份', 'brdb/listalltable.do', '54', '1', 'menu-icon fa fa-cloud-upload blue', '1', '1');
insert into `tb_sys_menu` values ('56', '数据库还原', 'brdb/list.do', '54', '3', 'menu-icon fa fa-cloud-download blue', '1', '1');
insert into `tb_sys_menu` values ('57', '备份定时器', 'timingbackup/list.do', '54', '2', 'menu-icon fa fa-tachometer blue', '1', '1');
insert into `tb_sys_menu` values ('58', 'sql编辑器', 'sqledit/view.do', '54', '4', 'menu-icon fa fa-pencil-square-o blue', '1', '1');
insert into `tb_sys_menu` values ('59', 'oa办公', '#', '0', '6', 'menu-icon fa fa-laptop pink', '2', '1');
insert into `tb_sys_menu` values ('60', '组织机构', 'department/listalldepartment.do?department_id=0', '59', '1', 'menu-icon fa fa-users green', '1', '1');

-- ----------------------------
-- table structure for sys_role
-- ----------------------------
drop table if exists `tb_sys_role`;
create table `tb_sys_role` (
  `role_id` varchar(100) not null,
  `role_name` varchar(100) default null,
  `rights` varchar(255) default null,
  `parent_id` varchar(100) default null,
  `add_qx` varchar(255) default null,
  `del_qx` varchar(255) default null,
  `edit_qx` varchar(255) default null,
  `cha_qx` varchar(255) default null,
  primary key (`role_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of sys_role
-- ----------------------------
insert into `tb_sys_role` values ('1', '系统管理组', '2305280058220076998', '0', '1', '1', '1', '1');
insert into `tb_sys_role` values ('115b386ff04f4352b060dffcd2b5d1da', '中级会员', '498', '2', '0', '0', '0', '0');
insert into `tb_sys_role` values ('1b67fc82ce89457a8347ae53e43a347e', '初级会员', '498', '2', '0', '0', '0', '0');
insert into `tb_sys_role` values ('2', '会员组', '498', '0', '0', '0', '0', '1');
insert into `tb_sys_role` values ('3264c8e83d0248bb9e3ea6195b4c0216', '一级管理员', '4611123067433770950', '1', '2244102192095174', '2251798773489606', '1125898866646982', '560135202614009798');
insert into `tb_sys_role` values ('46294b31a71c4600801724a6eb06bb26', '职位组', '', '0', '0', '0', '0', '0');
insert into `tb_sys_role` values ('5466347ac07044cb8d82990ec7f3a90e', '主管', '', '46294b31a71c4600801724a6eb06bb26', '0', '0', '0', '0');
insert into `tb_sys_role` values ('68f8e4a39efe47c7bb869e9d15ab925d', '二级管理员', '2305280058220076998', '1', '0', '0', '2251798773489606', '0');
insert into `tb_sys_role` values ('856849f422774ad390a4e564054d8cc8', '经理', '', '46294b31a71c4600801724a6eb06bb26', '0', '0', '0', '0');
insert into `tb_sys_role` values ('8b70a7e67f2841e7aaba8a4d92e5ff6f', '高级会员', '498', '2', '0', '0', '0', '0');
insert into `tb_sys_role` values ('c21cecf84048434b93383182b1d98cba', '组长', '', '46294b31a71c4600801724a6eb06bb26', '0', '0', '0', '0');
insert into `tb_sys_role` values ('d449195cd8e7491080688c58e11452eb', '总监', '', '46294b31a71c4600801724a6eb06bb26', '0', '0', '0', '0');
insert into `tb_sys_role` values ('de9de2f006e145a29d52dfadda295353', '三级管理员', '2305280058220076998', '1', '0', '0', '0', '0');

-- ----------------------------
-- table structure for tb_sys_role_button
-- ----------------------------
drop table if exists `tb_sys_role_button`;
create table `tb_sys_role_button` (
  `rb_id` varchar(100) not null,
  `role_id` varchar(100) default null,
  `button_id` varchar(100) default null,
  primary key (`rb_id`),
  key `角色表外键` (`role_id`) using btree,
  key `button` (`button_id`),
  constraint `button_fk` foreign key (`button_id`) references `tb_sys_button` (`button_id`) on delete cascade on update cascade,
  constraint `role_fk` foreign key (`role_id`) references `tb_sys_role` (`role_id`) on delete cascade on update cascade
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of tb_sys_role_button
-- ----------------------------
insert into `tb_sys_role_button` values ('14b5c28ea6ae4508b57d2d272ab3d5f1', '3264c8e83d0248bb9e3ea6195b4c0216', '46992ea280ba4b72b29dedb0d4bc0106');
insert into `tb_sys_role_button` values ('1743733f366240c693c4295b527d1b0e', 'de9de2f006e145a29d52dfadda295353', '4efa162fce8340f0bd2dcd3b11d327ec');
insert into `tb_sys_role_button` values ('3768e60edd1c4b5c9f1dd861188ae2f9', '3264c8e83d0248bb9e3ea6195b4c0216', 'cc51b694d5344d28a9aa13c84b7166cd');
insert into `tb_sys_role_button` values ('8231c216fb514b4188e4162e629c6237', '3264c8e83d0248bb9e3ea6195b4c0216', '4efa162fce8340f0bd2dcd3b11d327ec');
insert into `tb_sys_role_button` values ('9412d1d05162464c83658c7f89ab03f0', '68f8e4a39efe47c7bb869e9d15ab925d', '3542adfbda73410c976e185ffe50ad06');
insert into `tb_sys_role_button` values ('96567633dd3548c9b75d28f430adf5a3', '3264c8e83d0248bb9e3ea6195b4c0216', 'da7fd386de0b49ce809984f5919022b8');
insert into `tb_sys_role_button` values ('a1478f27c852459fa9cad04b642f4fb7', 'de9de2f006e145a29d52dfadda295353', '3542adfbda73410c976e185ffe50ad06');
insert into `tb_sys_role_button` values ('ba6696b8761044618e44c7e02c9ba89e', '68f8e4a39efe47c7bb869e9d15ab925d', 'cc51b694d5344d28a9aa13c84b7166cd');
insert into `tb_sys_role_button` values ('f0329033d0914faf8ea6e9ff252cc5e6', '68f8e4a39efe47c7bb869e9d15ab925d', '46992ea280ba4b72b29dedb0d4bc0106');
insert into `tb_sys_role_button` values ('f627982cc9d4479dbc03af726dc6ac58', 'de9de2f006e145a29d52dfadda295353', 'cc51b694d5344d28a9aa13c84b7166cd');

-- ----------------------------
-- table structure for tb_sys_user
-- ----------------------------
drop table if exists `tb_sys_user`;
create table `tb_sys_user` (
  `user_id` varchar(100) not null COMMENT '用户id',
  `user_name` varchar(255) default null ,
  `password` varchar(255) default null,
  `nick_name` varchar(255) default null,
  `rights` varchar(255) default null,
  `role_id` varchar(100) default null,
  `last_login` varchar(255) default null,
  `last_ip` varchar(100) default null,
  `status` varchar(32) default null,
  `bz` varchar(255) default null,
  `skin` varchar(100) default null,
  `email` varchar(32) default null,
  `index_num` varchar(100) default null,
  `phone` varchar(32) default null,
  `last_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最后使用时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  primary key (`user_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of sys_user
-- ----------------------------
insert into `tb_sys_user` values ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'fh', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2016-04-11 17:25:53', '127.0.0.1', '0', 'admin', 'default', 'qq313596790@main.com', '001', '18788888888','2016-12-24','2016-12-24');
insert into `tb_sys_user` values ('69177258a06e4927b4639ab1684c3320', 'san', '47c4a8dc64ac2f0bb46bbd8813b037c9718f9349', '三', '', '3264c8e83d0248bb9e3ea6195b4c0216', '2016-04-07 21:14:09', '127.0.0.1', '0', '111', 'default', '978336446@qq.com', '333', '13562202556','2016-12-24','2016-12-24');
insert into `tb_sys_user` values ('9991f4d7782a4ccfb8a65bd96ea7aafa', 'lisi', '2612ade71c1e48cd7150b5f4df152faa699cedfe', '李四', '', '3264c8e83d0248bb9e3ea6195b4c0216', '2016-03-21 17:41:57', '127.0.0.1', '0', '小李', 'default', '313596790@qq.com', '1102', '13566233663','2016-12-24','2016-12-24');
insert into `tb_sys_user` values ('e29149962e944589bb7da23ad18ddeed', 'zhangsan', 'c2da1419caf053885c492e10ebde421581cdc03f', '张三', '', '3264c8e83d0248bb9e3ea6195b4c0216', '', '', '0', '小张', 'default', 'zhangsan@www.com', '1101', '2147483647','2016-12-24','2016-12-24');

-- ----------------------------
-- table structure for tb_pictures
-- ----------------------------
drop table if exists `tb_pictures`;
create table `tb_pictures` (
  `pictures_id` varchar(100) not null,
  `title` varchar(255) default null comment '标题',
  `name` varchar(255) default null comment '文件名',
  `path` varchar(255) default null comment '路径',
  `createtime` varchar(100) default null comment '创建时间',
  `master_id` varchar(255) default null comment '属于',
  `bz` varchar(255) default null comment '备注',
  primary key (`pictures_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of tb_pictures
-- ----------------------------

-- ----------------------------
-- table structure for weixin_command
-- ----------------------------
drop table if exists `weixin_command`;
create table `weixin_command` (
  `command_id` varchar(100) not null,
  `keyword` varchar(255) default null comment '关键词',
  `commandcode` varchar(255) default null comment '应用路径',
  `createtime` varchar(255) default null comment '创建时间',
  `status` int(1) not null comment '状态',
  `bz` varchar(255) default null comment '备注',
  primary key (`command_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of weixin_command
-- ----------------------------
insert into `weixin_command` values ('2636750f6978451b8330874c9be042c2', '锁定服务器', 'rundll32.exe user32.dll,lockworkstation', '2015-05-10 21:25:06', '1', '锁定计算机');
insert into `weixin_command` values ('46217c6d44354010823241ef484f7214', '打开浏览器', 'c:/program files/internet explorer/iexplore.exe', '2015-05-09 02:43:02', '1', '打开浏览器操作');
insert into `weixin_command` values ('576adcecce504bf3bb34c6b4da79a177', '关闭浏览器', 'taskkill /f /im iexplore.exe', '2015-05-09 02:36:48', '2', '关闭浏览器操作');
insert into `weixin_command` values ('854a157c6d99499493f4cc303674c01f', '关闭qq', 'taskkill /f /im qq.exe', '2015-05-10 21:25:46', '1', '关闭qq');
insert into `weixin_command` values ('ab3a8c6310ca4dc8b803ecc547e55ae7', '打开qq', 'd:/soft/qq/qq/bin/qq.exe', '2015-05-10 21:25:25', '1', '打开qq');

-- ----------------------------
-- table structure for weixin_imgmsg
-- ----------------------------
drop table if exists `weixin_imgmsg`;
create table `weixin_imgmsg` (
  `imgmsg_id` varchar(100) not null,
  `keyword` varchar(255) default null comment '关键词',
  `createtime` varchar(100) default null comment '创建时间',
  `status` int(11) not null comment '状态',
  `bz` varchar(255) default null comment '备注',
  `title1` varchar(255) default null comment '标题1',
  `description1` varchar(255) default null comment '描述1',
  `imgurl1` varchar(255) default null comment '图片地址1',
  `tourl1` varchar(255) default null comment '超链接1',
  `title2` varchar(255) default null comment '标题2',
  `description2` varchar(255) default null comment '描述2',
  `imgurl2` varchar(255) default null comment '图片地址2',
  `tourl2` varchar(255) default null comment '超链接2',
  `title3` varchar(255) default null comment '标题3',
  `description3` varchar(255) default null comment '描述3',
  `imgurl3` varchar(255) default null comment '图片地址3',
  `tourl3` varchar(255) default null comment '超链接3',
  `title4` varchar(255) default null comment '标题4',
  `description4` varchar(255) default null comment '描述4',
  `imgurl4` varchar(255) default null comment '图片地址4',
  `tourl4` varchar(255) default null comment '超链接4',
  `title5` varchar(255) default null comment '标题5',
  `description5` varchar(255) default null comment '描述5',
  `imgurl5` varchar(255) default null comment '图片地址5',
  `tourl5` varchar(255) default null comment '超链接5',
  `title6` varchar(255) default null comment '标题6',
  `description6` varchar(255) default null comment '描述6',
  `imgurl6` varchar(255) default null comment '图片地址6',
  `tourl6` varchar(255) default null comment '超链接6',
  `title7` varchar(255) default null comment '标题7',
  `description7` varchar(255) default null comment '描述7',
  `imgurl7` varchar(255) default null comment '图片地址7',
  `tourl7` varchar(255) default null comment '超链接7',
  `title8` varchar(255) default null comment '标题8',
  `description8` varchar(255) default null comment '描述8',
  `imgurl8` varchar(255) default null comment '图片地址8',
  `tourl8` varchar(255) default null comment '超链接8',
  primary key (`imgmsg_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of weixin_imgmsg
-- ----------------------------
insert into `weixin_imgmsg` values ('380b2cb1f4954315b0e20618f7b5bd8f', '首页', '2015-05-10 20:51:09', '1', '图文回复', '图文回复标题', '图文回复描述', 'http://a.hiphotos.baidu.com/image/h%3d360/sign=c6c7e73ebc389b5027ffe654b535e5f1/a686c9177f3e6709392bb8df3ec79f3df8dc55e3.jpg', 'www.baidu.com', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');

-- ----------------------------
-- table structure for weixin_textmsg
-- ----------------------------
drop table if exists `weixin_textmsg`;
create table `weixin_textmsg` (
  `textmsg_id` varchar(100) not null,
  `keyword` varchar(255) default null comment '关键词',
  `content` varchar(255) default null comment '内容',
  `createtime` varchar(100) default null comment '创建时间',
  `status` int(2) default null comment '状态',
  `bz` varchar(255) default null comment '备注',
  primary key (`textmsg_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- records of weixin_textmsg
-- ----------------------------
insert into `weixin_textmsg` values ('695cd74779734231928a253107ab0eeb', '吃饭', '吃了噢噢噢噢', '2015-05-10 22:52:27', '1', '文本回复');
insert into `weixin_textmsg` values ('d4738af7aea74a6ca1a5fb25a98f9acb', '关注', '这里是关注后回复的内容', '2015-05-11 02:12:36', '1', '关注回复');
