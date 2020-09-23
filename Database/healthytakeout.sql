/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : healthytakeout

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-18 19:11:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressno` int(11) NOT NULL AUTO_INCREMENT,
  `contactperson` varchar(30) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `addressdetail` varchar(255) DEFAULT NULL,
  `userno` int(10) DEFAULT NULL,
  PRIMARY KEY (`addressno`),
  KEY `FKuser` (`userno`),
  CONSTRAINT `FKuser` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '李', '0', '13569874562', '河南省开封市顺河区河南大学明伦校区11号楼', '100001');
INSERT INTO `address` VALUES ('2', '李', '0', '17655992365', '河南省开封市龙亭区河南大学金明校区', '100001');
INSERT INTO `address` VALUES ('3', '王', '1', '13266554789', '河南省开封市顺河区顺河学生公寓', '100002');
INSERT INTO `address` VALUES ('4', '王', '1', '16678546587', '河南省开封市龙亭区南苑学生公寓3号楼', '100002');
INSERT INTO `address` VALUES ('5', '王一涵', '1', '13654987457', '河南省开封市龙亭区鼎立国际E区3号楼', '100002');

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `fansid` int(11) NOT NULL AUTO_INCREMENT,
  `userno` int(11) DEFAULT NULL,
  `fansuserno` int(11) DEFAULT NULL,
  PRIMARY KEY (`fansid`),
  KEY `fkuserfan1` (`userno`),
  KEY `fkuserfan2` (`fansuserno`),
  CONSTRAINT `fkuserfan1` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`),
  CONSTRAINT `fkuserfan2` FOREIGN KEY (`fansuserno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO `fans` VALUES ('1', '100001', '100002');
INSERT INTO `fans` VALUES ('2', '100001', '100003');
INSERT INTO `fans` VALUES ('3', '100001', '100004');
INSERT INTO `fans` VALUES ('5', '100006', '100002');
INSERT INTO `fans` VALUES ('6', '100003', '100002');
INSERT INTO `fans` VALUES ('8', '100007', '100002');
INSERT INTO `fans` VALUES ('9', '100008', '100002');
INSERT INTO `fans` VALUES ('10', '100002', '100008');
INSERT INTO `fans` VALUES ('12', '100001', '100008');
INSERT INTO `fans` VALUES ('13', '100003', '100008');
INSERT INTO `fans` VALUES ('14', '100002', '100001');
INSERT INTO `fans` VALUES ('15', '100003', '100001');
INSERT INTO `fans` VALUES ('16', '100004', '100001');
INSERT INTO `fans` VALUES ('17', '100008', '100001');
INSERT INTO `fans` VALUES ('18', '100002', '100005');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `feedbackno` int(11) NOT NULL AUTO_INCREMENT,
  `feedbackdetail` varchar(255) DEFAULT NULL,
  `userno` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `contactdetail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`feedbackno`),
  KEY `fkuserno` (`userno`),
  CONSTRAINT `fkuserno` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '很好用', '100001', 'Tom', 'Tom@qq.com');

-- ----------------------------
-- Table structure for focus
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus` (
  `focusid` int(11) NOT NULL AUTO_INCREMENT,
  `userno` int(11) DEFAULT NULL,
  `focususerno` int(11) DEFAULT NULL,
  PRIMARY KEY (`focusid`),
  KEY `fkuserfocus1` (`userno`),
  KEY `fkuserfocus2` (`focususerno`),
  CONSTRAINT `fkuserfocus1` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`),
  CONSTRAINT `fkuserfocus2` FOREIGN KEY (`focususerno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of focus
-- ----------------------------
INSERT INTO `focus` VALUES ('3', '100002', '100001');
INSERT INTO `focus` VALUES ('4', '100002', '100005');
INSERT INTO `focus` VALUES ('5', '100002', '100006');
INSERT INTO `focus` VALUES ('6', '100002', '100003');
INSERT INTO `focus` VALUES ('8', '100002', '100007');
INSERT INTO `focus` VALUES ('9', '100002', '100008');
INSERT INTO `focus` VALUES ('10', '100008', '100002');
INSERT INTO `focus` VALUES ('12', '100008', '100001');
INSERT INTO `focus` VALUES ('13', '100008', '100003');
INSERT INTO `focus` VALUES ('14', '100001', '100002');
INSERT INTO `focus` VALUES ('15', '100001', '100003');
INSERT INTO `focus` VALUES ('16', '100001', '100004');
INSERT INTO `focus` VALUES ('17', '100001', '100008');
INSERT INTO `focus` VALUES ('18', '100005', '100002');

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `foodno` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(30) DEFAULT NULL,
  `foodname` varchar(30) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `calories` float(10,2) DEFAULT NULL,
  `supplier` varchar(30) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  `restaurantid` int(11) DEFAULT NULL,
  PRIMARY KEY (`foodno`),
  KEY `restaurantid` (`restaurantid`),
  CONSTRAINT `restaurantid` FOREIGN KEY (`restaurantid`) REFERENCES `restaurant` (`restaurantid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000019 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('10000001', '主食类', '寿司', '20.50', '221.35', '成都龙山传奇食品有限公司', '美味寿司', '1005');
INSERT INTO `food` VALUES ('10000002', '饮品类', '柠檬水', '5.00', '10.00', '北京草原鑫茂食品有限公司', '柠檬水', '1001');
INSERT INTO `food` VALUES ('10000003', '菜品类', '炒土豆丝', '10.00', '203.00', '巨鹿县鲜佰胜农业科技有限公司', '炒土豆丝', '1003');
INSERT INTO `food` VALUES ('10000004', '饮品类', '可乐', '3.00', '301.00', '百事公司', '冰爽饮料', '1003');
INSERT INTO `food` VALUES ('10000005', '主食类', '雪碧（500ml）', '3.56', '688.00', '可口可乐有限公司', '透心凉心飞扬！！！', '1003');
INSERT INTO `food` VALUES ('10000006', '菜品类', '麻辣肉丝', '45.00', '603.60', '天方蔬菜批发市场', '非常好吃的麻辣肉丝', '1003');
INSERT INTO `food` VALUES ('10000007', '菜品类', '麻婆豆腐', '9.00', '252.00', '霸州市源鑫食品有限公司', '重庆麻婆豆腐色香味好', '1003');
INSERT INTO `food` VALUES ('10000008', '菜品类', '辣子鸡', '38.00', '650.00', '茂南农产品批发市场', '非常辣的东西', '1003');
INSERT INTO `food` VALUES ('10000009', '菜品类', '干拌凉粉', '7.00', '300.00', '河南汇晔食品有限公司', '好吃的干拌凉粉', '1003');
INSERT INTO `food` VALUES ('10000010', '菜品类', '红烧肉', '48.00', '760.00', '北京恒祺伟业食品有限公司', '好吃不腻', '1003');
INSERT INTO `food` VALUES ('10000011', '菜品类', '红烧鸭头', '46.00', '520.00', '乐陵市畅腾食品有限公司', '好吃的红烧鸭头', '1003');
INSERT INTO `food` VALUES ('10000012', '菜品类', '青椒肉片', '28.00', '590.00', '寿光有机蔬菜基地', '重庆特色口味的青椒肉片', '1003');
INSERT INTO `food` VALUES ('10000013', '菜品类', '干煸四季豆', '18.00', '230.00', '泉州健趣商贸有限公司', '新鲜四季豆制作而成的干煸四季豆', '1003');
INSERT INTO `food` VALUES ('10000014', '菜品类', '回锅肉', '29.00', '696.00', '河南大唐宜品商贸有限公司', '绝对新鲜食材新鲜食材', '1003');
INSERT INTO `food` VALUES ('10000015', '菜品类', '腊肠', '30.00', '630.00', '上海味淳食品有限公司', '湖南风味腊肠', '1003');
INSERT INTO `food` VALUES ('10000016', '主食类', '日式拉面', '36.00', '420.00', '山东腾禹食品有限公司', '极具特色的日式拉面', '1005');
INSERT INTO `food` VALUES ('10000017', '主食类', '日式咖喱', '25.00', '460.00', '武汉市东西湖金鸥味业食品厂', '融合了印度风味的日式经典', '1005');
INSERT INTO `food` VALUES ('10000018', '主食类', '日式蛋包饭', '19.00', '300.00', '潍坊旭泽食品有限公司', '美味蛋包饭', '1005');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `no` int(11) NOT NULL,
  `musername` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'manager1', '111111');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `senderuserno` int(11) DEFAULT NULL,
  `sendername` varchar(30) DEFAULT NULL,
  `receiveruserno` int(11) DEFAULT NULL,
  `messagecontent` varchar(255) DEFAULT NULL,
  `sendtime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`messageid`),
  KEY `fkmsuno` (`senderuserno`),
  KEY `fkmruno` (`receiveruserno`),
  CONSTRAINT `fkmruno` FOREIGN KEY (`receiveruserno`) REFERENCES `user` (`userno`),
  CONSTRAINT `fkmsuno` FOREIGN KEY (`senderuserno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '100001', 'Tom', '100002', '你好，很高兴认识你', '2020-03-16 22:25:40');
INSERT INTO `message` VALUES ('2', '100002', 'Lisa', '100001', '嗯嗯，你好', '2020-03-16 22:26:20');
INSERT INTO `message` VALUES ('3', '100001', 'Tom', '100002', '有时间可以一起健身减肥啊', '2020-03-16 22:26:57');
INSERT INTO `message` VALUES ('4', '100002', 'Lisa', '100001', '哈哈哈', '2020-03-16 23:59:46');

-- ----------------------------
-- Table structure for orderdata
-- ----------------------------
DROP TABLE IF EXISTS `orderdata`;
CREATE TABLE `orderdata` (
  `orderid` int(15) NOT NULL AUTO_INCREMENT,
  `userno` int(10) DEFAULT NULL,
  `restaurantid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `restaurantname` varchar(50) DEFAULT NULL,
  `orderdetails` varchar(255) DEFAULT NULL,
  `totalprice` float(10,2) DEFAULT NULL,
  `starttime` varchar(30) DEFAULT NULL,
  `orderstatus` varchar(30) DEFAULT NULL,
  `addressno` int(11) DEFAULT NULL,
  `orderaddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `fkorderuser` (`userno`),
  KEY `fkorderaddress` (`addressno`),
  KEY `fkordershop` (`restaurantid`),
  CONSTRAINT `fkorderaddress` FOREIGN KEY (`addressno`) REFERENCES `address` (`addressno`),
  CONSTRAINT `fkordershop` FOREIGN KEY (`restaurantid`) REFERENCES `restaurant` (`restaurantid`),
  CONSTRAINT `fkorderuser` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000011 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of orderdata
-- ----------------------------
INSERT INTO `orderdata` VALUES ('1000000003', '100002', '1003', 'Lisa', '重庆特色菜', '麻辣肉丝*3、', '135.00', '2020-03-09 23:52:06', '已完成', '5', '王一涵 13654987457 河南省开封市龙亭区鼎立国际E区3号楼');
INSERT INTO `orderdata` VALUES ('1000000004', '100002', '1003', 'Lisa', '重庆特色菜', '麻辣肉丝*3、', '135.00', '2020-03-09 23:52:11', '已完成', '5', '王一涵 13654987457 河南省开封市龙亭区鼎立国际E区3号楼');
INSERT INTO `orderdata` VALUES ('1000000005', '100001', '1003', 'Tom', '重庆特色菜', '麻辣肉丝*1、', '45.00', '2020-03-11 17:10:29', '已完成', '1', '李 13569874562 河南省开封市顺河区河南大学明伦校区11号楼');
INSERT INTO `orderdata` VALUES ('1000000006', '100001', '1001', 'Tom', '海鲜遇上面', '柠檬水*4、', '20.00', '2020-03-09 23:56:36', '已完成', '2', '李 17655992365 河南省开封市龙亭区河南大学金明校区');
INSERT INTO `orderdata` VALUES ('1000000007', '100001', '1005', 'Tom', '吉野日式料理', '寿司*1、日式拉面*1、日式咖喱*1、日式蛋包饭*1、', '100.50', '2020-03-09 23:56:50', '已完成', '1', '李 13569874562 河南省开封市顺河区河南大学明伦校区11号楼');
INSERT INTO `orderdata` VALUES ('1000000008', '100001', '1005', 'Tom', '吉野日式料理', '寿司*1、日式拉面*1、日式咖喱*1、', '81.50', '2020-03-11 00:31:51', '已完成', '2', '李 17655992365 河南省开封市龙亭区河南大学金明校区');
INSERT INTO `orderdata` VALUES ('1000000009', '100001', '1003', 'Tom', '重庆特色菜', '麻辣肉丝*1、', '45.00', '2020-03-11 00:27:43', '已完成', '2', '李 17655992365 河南省开封市龙亭区河南大学金明校区');
INSERT INTO `orderdata` VALUES ('1000000010', '100001', '1005', 'Tom', '吉野日式料理', '日式拉面*1、', '36.00', '2020-03-11 00:26:12', '已完成', '2', '李 17655992365 河南省开封市龙亭区河南大学金明校区');

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `restaurantid` int(11) NOT NULL AUTO_INCREMENT,
  `restaurantname` varchar(50) DEFAULT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `restaurantaddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`restaurantid`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
INSERT INTO `restaurant` VALUES ('1001', '海鲜遇上面', '主营海鲜', '星光天地3楼104号');
INSERT INTO `restaurant` VALUES ('1002', '食螺记螺狮粉', '主营螺狮粉', '开元广场2楼67号');
INSERT INTO `restaurant` VALUES ('1003', '重庆特色菜', '各种重庆地方特色菜', '文星路300号');
INSERT INTO `restaurant` VALUES ('1004', '雨花西餐厅', '西式菜品', '人民南路3005号');
INSERT INTO `restaurant` VALUES ('1005', '吉野日式料理', '日式菜品，三文鱼刺身、寿司、日式蛋包饭、日式拉面', '人民南路1002号');
INSERT INTO `restaurant` VALUES ('1006', '百粥坊（粥·便当）', '招牌金丝黄瓜粥、桂圆红枣粥、红枣山药粥等', '顺城路东段菜市场里50米路东（润真蛋糕房楼下）');
INSERT INTO `restaurant` VALUES ('1007', '芝士先生（星光天地店）', '全国连锁西式快餐，芝士焗饭专卖店', '黄河路北段星光天地B馆3层C-A号');
INSERT INTO `restaurant` VALUES ('1008', '吉祥馄饨·烧麦（汉兴店）', '上海吉祥汉兴店第一家品牌加盟店，以最优质的餐品为您服务！', '汉兴路95号');
INSERT INTO `restaurant` VALUES ('1009', '志诚优果（晋安路店）', '不好吃，不新鲜，无条件退换货', '金明大道890号');
INSERT INTO `restaurant` VALUES ('1010', '叫份水果捞（河大店）', '我与春风皆过客，你携秋水度星河', '示范区东陈新街17号');

-- ----------------------------
-- Table structure for shopcomment
-- ----------------------------
DROP TABLE IF EXISTS `shopcomment`;
CREATE TABLE `shopcomment` (
  `shopcommentno` int(11) NOT NULL AUTO_INCREMENT,
  `restaurantid` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `commenttime` varchar(30) DEFAULT NULL,
  `userno` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shopcommentno`),
  KEY `fkscrestaurant` (`restaurantid`),
  KEY `fkscuser` (`userno`),
  CONSTRAINT `fkscrestaurant` FOREIGN KEY (`restaurantid`) REFERENCES `restaurant` (`restaurantid`),
  CONSTRAINT `fkscuser` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcomment
-- ----------------------------
INSERT INTO `shopcomment` VALUES ('1', '1003', '这家店的食材很好', '2020-03-14 15:39:42', '100001', 'Tom');
INSERT INTO `shopcomment` VALUES ('2', '1003', 'OKOK', '2020-03-14 17:55:23', '100001', 'Tom');
INSERT INTO `shopcomment` VALUES ('3', '1003', 'haha', '2020-03-14 18:02:25', '100001', 'Tom');
INSERT INTO `shopcomment` VALUES ('4', '1003', '啊', '2020-03-14 18:04:43', '100001', 'Tom');
INSERT INTO `shopcomment` VALUES ('5', '1003', '啊', '2020-03-14 18:07:17', '100001', 'Tom');

-- ----------------------------
-- Table structure for trend
-- ----------------------------
DROP TABLE IF EXISTS `trend`;
CREATE TABLE `trend` (
  `trendno` int(11) NOT NULL AUTO_INCREMENT,
  `userno` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `trendtitle` varchar(100) DEFAULT NULL,
  `trendcontent` varchar(255) DEFAULT NULL,
  `releasetime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`trendno`),
  KEY `fktrenduser` (`userno`),
  CONSTRAINT `fktrenduser` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trend
-- ----------------------------
INSERT INTO `trend` VALUES ('1', '100001', 'Tom', '第一条', '大家好，这是我的第一天动态', '2020-03-14 22:30:57');
INSERT INTO `trend` VALUES ('2', '100002', 'Lisa', '第二条', '我是Lisa，欢迎大家关注我', '2020-03-14 22:32:03');
INSERT INTO `trend` VALUES ('3', '100001', 'Tom', '123', '123123123123123', '2020-03-15 13:52:39');
INSERT INTO `trend` VALUES ('4', '100001', 'Tom', '牛油果健康早餐', '早餐吃一个牛油果很健康，减肥！！！', '2020-03-15 13:56:07');
INSERT INTO `trend` VALUES ('5', '100001', 'Tom', 'test', '第一\n        很好\n        这是段落', '2020-03-15 15:16:11');

-- ----------------------------
-- Table structure for trendcomment
-- ----------------------------
DROP TABLE IF EXISTS `trendcomment`;
CREATE TABLE `trendcomment` (
  `trendcommentno` int(11) NOT NULL AUTO_INCREMENT,
  `trendno` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `commenttime` varchar(30) DEFAULT NULL,
  `userno` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`trendcommentno`),
  KEY `fktct` (`trendno`),
  KEY `fktu` (`userno`),
  CONSTRAINT `fktct` FOREIGN KEY (`trendno`) REFERENCES `trend` (`trendno`),
  CONSTRAINT `fktu` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trendcomment
-- ----------------------------
INSERT INTO `trendcomment` VALUES ('1', '4', '真的吗', '2020-03-14 15:39:42', '100001', 'Tom');
INSERT INTO `trendcomment` VALUES ('2', '4', '确实', '2020-03-15 14:56:35', '100001', 'Tom');
INSERT INTO `trendcomment` VALUES ('3', '2', '已关注', '2020-03-15 14:57:52', '100001', 'Tom');
INSERT INTO `trendcomment` VALUES ('5', '4', '好的', '2020-03-15 15:40:10', '100001', 'Tom');
INSERT INTO `trendcomment` VALUES ('6', '5', '评论测试', '2020-03-16 14:13:44', '100001', 'Tom');
INSERT INTO `trendcomment` VALUES ('7', '4', '我是lisa', '2020-03-16 22:11:13', '100002', 'Lisa');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userno` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `height` float(10,1) DEFAULT NULL,
  `weight` float(10,1) DEFAULT NULL,
  `preference` varchar(10) DEFAULT NULL,
  `targetweight` float(10,0) DEFAULT NULL,
  PRIMARY KEY (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=100013 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('100001', 'Tom', '123456', '0', '20', '176.0', '90.0', '甜', '70');
INSERT INTO `user` VALUES ('100002', 'Lisa', '123456', '1', '20', '163.0', '49.0', '酸', '49');
INSERT INTO `user` VALUES ('100003', 'Jim', '111111', '0', '22', '179.0', '71.5', '辣', '66');
INSERT INTO `user` VALUES ('100004', 'Alice', '123456', '1', '25', '158.0', '50.0', '辣', '45');
INSERT INTO `user` VALUES ('100005', 'Alan', '123456', '0', '24', '178.0', '67.0', '甜', '68');
INSERT INTO `user` VALUES ('100006', 'Barry', '123456', '0', '21', '180.0', '80.0', '甜', '70');
INSERT INTO `user` VALUES ('100007', '123', '123456', '0', '231', '321.0', '312.0', '甜', '321');
INSERT INTO `user` VALUES ('100008', 'test1', '123456', '1', '11', '22.0', '33.0', '辣', '44');
INSERT INTO `user` VALUES ('100009', '12', '21', '0', '321', '321.0', '231.0', '甜', '213');
INSERT INTO `user` VALUES ('100010', '123', '21', '0', '321', '321.0', '231.0', '甜', '213');
INSERT INTO `user` VALUES ('100011', '1234', '21', '1', '321', '321.0', '231.0', '甜', '213');
INSERT INTO `user` VALUES ('100012', '1', '2', '1', '1', '2.0', '1.0', '酸', '1');
