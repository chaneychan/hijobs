# Host: localhost  (Version: 5.5.9)
# Date: 2014-09-09 19:06:49
# Generator: MySQL-Front 5.3  (Build 4.156)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "applicant"
#

DROP TABLE IF EXISTS `applicant`;
CREATE TABLE `applicant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gender` int(10) NOT NULL DEFAULT '1' COMMENT '性别，1-男，2-女，3-保密等',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `nick` varchar(100) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(100) DEFAULT NULL COMMENT '用户头像(url)',
  `sig` varchar(100) DEFAULT NULL COMMENT '签名',
  `city` varchar(20) DEFAULT NULL COMMENT '所在城市',
  `user_id` bigint(20) DEFAULT NULL,
  `positions` varchar(100) DEFAULT NULL COMMENT '求职岗位，逗号分隔，取值数据字典',
  `intention` varchar(100) DEFAULT NULL COMMENT '意向工作地，取值数据字典',
  `exp` varchar(100) DEFAULT NULL COMMENT '工作年限，取值数据字典',
  `hukou` varchar(100) DEFAULT NULL COMMENT '户籍',
  `residence` varchar(255) DEFAULT NULL COMMENT '居住地',
  `grad_sch` varchar(100) DEFAULT NULL COMMENT '毕业学校',
  `status` int(10) DEFAULT '1' COMMENT '1-找工作，2-观望中',
  `nature` int(10) DEFAULT '1' COMMENT '工作性质1-全职，2-兼职',
  `exp_sal` varchar(100) DEFAULT NULL COMMENT '期望薪资，取值数据字典',
  `edu` varchar(100) DEFAULT NULL COMMENT '学历，取值数据字典',
  `skill_cert` varchar(100) DEFAULT NULL COMMENT '技能证书',
  `profile` varchar(100) DEFAULT NULL COMMENT '个人简介',
  `tags` varchar(100) DEFAULT NULL COMMENT '个性标签',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应聘者';

#
# Structure for table "areas"
#

DROP TABLE IF EXISTS `areas`;
CREATE TABLE `areas` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL,
  `area_name` varchar(50) NOT NULL,
  `zipcode` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3373 DEFAULT CHARSET=utf8 COMMENT='地区信息';

#
# Structure for table "attention"
#

DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注信息';

#
# Structure for table "company"
#

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL COMMENT '名字（昵称）',
  `mobile` varchar(100) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '1-未审核，2-审核中，3-审核通过',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `size` varchar(100) DEFAULT NULL COMMENT '公司规模-取值于数据字典',
  `nature` varchar(100) DEFAULT NULL COMMENT '公司性质-取值于数据字典',
  `industry` varchar(100) DEFAULT NULL COMMENT '公司行业-取值于数据字典',
  `profile` varchar(1000) DEFAULT NULL COMMENT '公司简介',
  `avatar` varchar(100) DEFAULT NULL COMMENT '公司头像(url)',
  `license_url` varchar(100) DEFAULT NULL COMMENT '营业执照url,逗号分隔',
  `latitude` double(10,6) DEFAULT NULL COMMENT '经度',
  `longitude` double(10,6) DEFAULT NULL COMMENT '纬度',
  `geohash` varchar(20) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注册企业';

#
# Structure for table "company_account"
#

DROP TABLE IF EXISTS `company_account`;
CREATE TABLE `company_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tel` varchar(100) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gmt_expire` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公司账号，包括子账号';

#
# Structure for table "group"
#

DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "interview"
#

DROP TABLE IF EXISTS `interview`;
CREATE TABLE `interview` (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `from_id` bigint(20) DEFAULT NULL COMMENT '邀请方',
  `to_id` bigint(20) DEFAULT NULL COMMENT '接收方',
  `status` tinyint(3) DEFAULT NULL COMMENT '1-待确认，2-确认，3-拒绝，4-过期',
  `address` varchar(255) DEFAULT NULL COMMENT '面试地址',
  `type` int(10) DEFAULT NULL COMMENT '1-hr发起，2-applicant发起',
  `job_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='面试记录';

#
# Structure for table "job"
#

DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `top` int(10) NOT NULL DEFAULT '0' COMMENT '是否置顶；-1：不置顶，100：置顶',
  `company_id` bigint(20) DEFAULT NULL,
  `hr_id` bigint(20) DEFAULT NULL,
  `latitude` double(10,5) DEFAULT NULL COMMENT '经度',
  `longitude` double(10,5) DEFAULT NULL COMMENT '纬度',
  `geohash` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位';

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `pwd` varchar(255) NOT NULL DEFAULT '' COMMENT '密码（md5）',
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `account_type` int(10) DEFAULT NULL COMMENT '1-company,2-applicant',
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dev_type` int(10) DEFAULT NULL COMMENT '设备类型 1-android，2-ios，3-wp，4-blackberry，-1-未知',
  `dev_id` varchar(100) DEFAULT NULL,
  `app_ver` varchar(100) DEFAULT NULL COMMENT '产品版本',
  `latitude` double(10,6) DEFAULT NULL COMMENT '经度',
  `longitude` double(10,6) DEFAULT NULL COMMENT '纬度',
  `geohash` varchar(20) DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL COMMENT '最近一次登录',
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Structure for table "user_group"
#

DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-非管理员，1-管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
