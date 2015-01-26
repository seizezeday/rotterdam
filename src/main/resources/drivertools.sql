CREATE DATABASE  IF NOT EXISTS `rotterdam` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rotterdam`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: rotterdam
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Day`
--

DROP TABLE IF EXISTS `Day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Day` (
  `idDay` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `idWeek` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idDay`),
  KEY `fk_Day_1_idx` (`idWeek`),
  CONSTRAINT `fk_Day_1` FOREIGN KEY (`idWeek`) REFERENCES `WEEK` (`idWeek`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Day`
--

LOCK TABLES `Day` WRITE;
/*!40000 ALTER TABLE `Day` DISABLE KEYS */;
INSERT INTO `Day` VALUES (22,'2015-01-19',3),(23,'2015-01-20',3),(24,'2015-01-21',3),(25,'2015-01-22',3),(26,'2015-01-23',3),(27,'2015-01-24',3),(28,'2015-01-25',3),(29,'2015-01-05',4),(30,'2015-01-06',4),(31,'2015-01-07',4),(32,'2015-01-08',4),(33,'2015-01-09',4),(34,'2015-01-10',4),(35,'2015-01-11',4),(36,'2015-01-12',5),(37,'2015-01-13',5),(38,'2015-01-14',5),(39,'2015-01-15',5),(40,'2015-01-16',5),(41,'2015-01-17',5),(42,'2015-01-18',5),(43,'2015-01-26',6),(44,'2015-01-27',6),(45,'2015-01-28',6),(46,'2015-01-29',6),(47,'2015-01-30',6),(48,'2015-01-31',6),(49,'2015-02-01',6),(50,'2015-01-19',7),(51,'2015-01-20',7),(52,'2015-01-21',7),(53,'2015-01-22',7),(54,'2015-01-23',7),(55,'2015-01-24',7),(56,'2015-01-25',7);
/*!40000 ALTER TABLE `Day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERIOD`
--

DROP TABLE IF EXISTS `PERIOD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERIOD` (
  `idPeriod` bigint(20) NOT NULL AUTO_INCREMENT,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `timeForTime` double DEFAULT '0',
  `idUser` int(11) DEFAULT NULL,
  `periodType` varchar(45) DEFAULT NULL,
  `calculated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idPeriod`),
  KEY `fk_PERIOD_1_idx` (`idUser`),
  CONSTRAINT `fk_PERIOD_1` FOREIGN KEY (`idUser`) REFERENCES `USER` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERIOD`
--

LOCK TABLES `PERIOD` WRITE;
/*!40000 ALTER TABLE `PERIOD` DISABLE KEYS */;
INSERT INTO `PERIOD` VALUES (2,'2015-01-05','2015-02-01',20,5,'FOURWEEK',1),(3,'2015-02-02','2015-03-01',0,5,'FOURWEEK',0),(4,'2015-01-05','2015-02-01',0,7,'FOURWEEK',0);
/*!40000 ALTER TABLE `PERIOD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RULETYPE`
--

DROP TABLE IF EXISTS `RULETYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RULETYPE` (
  `idRuleType` int(11) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idRuleType`),
  UNIQUE KEY `idRuleType_UNIQUE` (`idRuleType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RULETYPE`
--

LOCK TABLES `RULETYPE` WRITE;
/*!40000 ALTER TABLE `RULETYPE` DISABLE KEYS */;
INSERT INTO `RULETYPE` VALUES (1,'Driver',1);
/*!40000 ALTER TABLE `RULETYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RoleRule`
--

DROP TABLE IF EXISTS `RoleRule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoleRule` (
  `idRoleRule` int(11) NOT NULL AUTO_INCREMENT,
  `idRuleType` int(11) DEFAULT NULL,
  `idUserRole` int(11) DEFAULT NULL,
  `enabled` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRoleRule`),
  UNIQUE KEY `idCategoryRule_UNIQUE` (`idRoleRule`),
  KEY `idUserCategoty_idx` (`idUserRole`),
  KEY `idRuleType_idx` (`idRuleType`),
  CONSTRAINT `idRuleType` FOREIGN KEY (`idRuleType`) REFERENCES `RULETYPE` (`idRuleType`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `idUserCategoty` FOREIGN KEY (`idUserRole`) REFERENCES `USERROLE` (`idUserRole`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RoleRule`
--

LOCK TABLES `RoleRule` WRITE;
/*!40000 ALTER TABLE `RoleRule` DISABLE KEYS */;
INSERT INTO `RoleRule` VALUES (1,1,1,NULL);
/*!40000 ALTER TABLE `RoleRule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SESSION`
--

DROP TABLE IF EXISTS `SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SESSION` (
  `sessionId` varchar(100) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `lastAccessedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`sessionId`),
  UNIQUE KEY `sessionId_UNIQUE` (`sessionId`),
  KEY `FK_s0375xfmso948x6ouaih6vtn4` (`idUser`),
  CONSTRAINT `FK_s0375xfmso948x6ouaih6vtn4` FOREIGN KEY (`idUser`) REFERENCES `USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SESSION`
--

LOCK TABLES `SESSION` WRITE;
/*!40000 ALTER TABLE `SESSION` DISABLE KEYS */;
INSERT INTO `SESSION` VALUES ('171tdtki9uo40nel85oi27062c',5,'2015-01-21 12:06:53','2015-01-21 12:06:53'),('18t04fdrta2qq3rvqsdur4r31b',5,'2015-01-23 17:12:31','2015-01-23 17:12:31'),('1tma47sikqh03nesdipoc0pjb8',5,'2015-01-17 13:24:39','2015-01-17 13:24:39'),('27u1opk3nk78h6r0ltq69j5jqv',5,'2015-01-17 13:16:40','2015-01-17 13:16:40'),('29rgsbetls8i78gf0s8nhbhtnr',5,'2015-01-18 13:42:55','2015-01-18 13:42:55'),('2lb838umgcocd2bg4alpbiofaa',5,'2015-01-24 15:06:43','2015-01-24 15:06:43'),('2oo539bk0djqkruabtjvd7upcc',5,'2015-01-19 11:51:01','2015-01-19 11:51:01'),('33jkeu93k341hlrerksrdl14t5',5,'2015-01-20 17:39:36','2015-01-20 17:39:36'),('3a44200cfpf3ui9k49v9at6ttl',5,'2015-01-17 13:06:13','2015-01-17 13:06:13'),('3jmn6cqohb0ebke6e2g4t65v7g',5,'2015-01-24 14:38:38','2015-01-24 14:38:38'),('3msne6sdtvq4dcmkr7vefik65',5,'2015-01-21 12:54:18','2015-01-21 12:54:18'),('5e2l6f64rcapmek9m9rbd8fmih',5,'2015-01-24 15:34:51','2015-01-24 15:34:51'),('70m2u1djf4fdbki1ckg4jp09ce',5,'2015-01-13 13:13:03','2015-01-13 13:13:03'),('72mljco7jth1vf3208hge3h7kp',5,'2015-01-24 15:36:00','2015-01-24 15:36:00'),('7ajehrel9g3rmp7rdugg3s1ea1',5,'2015-01-22 10:01:08','2015-01-22 10:01:08'),('7df4r8eq6b9gcsqsejo6458drf',5,'2015-01-22 18:08:16','2015-01-22 18:08:16'),('7v8fl2u71fortl2im81nl25mi0',5,'2015-01-26 09:44:47','2015-01-26 09:44:47'),('8lc0c63hr9f0bq3r2e9dlfc919',5,'2015-01-22 18:07:39','2015-01-22 18:07:39'),('8pk20dpbp64ngb837lfa78piek',5,'2015-01-22 19:29:48','2015-01-22 19:29:48'),('9kmfkmkfe2h6aoduar6eiungii',5,'2015-01-24 15:21:26','2015-01-24 15:21:26'),('af130437q8q3q94bu0dovhq0sl',5,'2015-01-17 13:47:22','2015-01-17 13:47:22'),('c9548i1vnnhl1336h88gq3p3ha',5,'2015-01-22 13:36:59','2015-01-22 13:36:59'),('crc57v80qhajdkjcilbl70epu0',5,'2015-01-22 18:07:23','2015-01-22 18:07:23'),('dqairsfp7tnbkad4s99q4atd45',5,'2015-01-20 13:58:28','2015-01-20 13:58:28'),('drflar2209acufv4ju6ku946pe',5,'2015-01-21 10:34:57','2015-01-21 10:34:57'),('e5idr9bbhvvmsgos5mj6okqv26',5,'2015-01-19 08:51:05','2015-01-19 08:51:05'),('f1uemjh2ip4b852va2bfsianj5',5,'2015-01-18 11:58:31','2015-01-18 11:58:31'),('fbcv2r1mj9ni679b96251v0llu',5,'2015-01-17 13:44:11','2015-01-17 13:44:11'),('flqhrv5js6ikd07ju7qmpohork',5,'2015-01-20 15:00:27','2015-01-20 15:00:27'),('g32vup655qkf4ntnqhr16bblir',5,'2015-01-17 13:32:39','2015-01-17 13:32:39'),('g5n0fqc1hu6u5lfl7lo6lndavl',5,'2015-01-18 15:15:57','2015-01-18 15:15:57'),('gofsjhl5cadquqmv0rc9nmvjj0',5,'2015-01-21 10:57:27','2015-01-21 10:57:27'),('hjiga2q962phabhj78uoiouiq5',5,'2015-01-21 12:31:36','2015-01-21 12:31:36'),('i2igkv58g69rng1okvor5m20jp',5,'2015-01-20 15:10:26','2015-01-20 15:10:26'),('jhueagnvqn2c67sssdph6m8mun',5,'2015-01-17 13:46:25','2015-01-17 13:46:25'),('jt6823oei8bqbna686f4nsm0d5',5,'2015-01-23 10:32:25','2015-01-23 10:32:25'),('k2lc8u8770bjbgopjg33okf0q1',5,'2015-01-17 14:50:01','2015-01-17 14:50:01'),('k6j4bcqrvu3035e5jpjs86mqpr',5,'2015-01-18 19:18:35','2015-01-18 19:18:35'),('k9rirp2c4oshqpuoscssr3od1h',5,'2015-01-17 14:12:39','2015-01-17 14:12:39'),('ka9ab4n4fmulreh1c2fvupsbkc',5,'2015-01-21 12:28:18','2015-01-21 12:28:18'),('ki9nalbuj5o5krqpqippsf7j2',5,'2015-01-21 10:21:23','2015-01-21 10:21:23'),('kl6t3uc0h6d0ijsl2o29k3i3nc',5,'2015-01-18 13:38:21','2015-01-18 13:38:21'),('m8hnkbnsq0qkpqmk6mjandbc23',5,'2015-01-20 14:50:25','2015-01-20 14:50:25'),('m8sa66uodum10gnqmavd85vkrg',5,'2015-01-17 13:17:51','2015-01-17 13:17:51'),('mdi82oq7o3s58qj5jfbjme24e0',5,'2015-01-24 15:28:25','2015-01-24 15:28:25'),('msi75foh0oh33g0svhjvpnr6hd',5,'2015-01-17 13:15:12','2015-01-17 13:15:12'),('n269orjooal2bqnnsnknptco3',5,'2015-01-24 15:30:30','2015-01-24 15:30:30'),('nc758vi94eqa6jar0ubpejqirk',5,'2015-01-20 17:45:40','2015-01-20 17:45:40'),('ndldrhplsp9hekru103bb90oqi',5,'2015-01-17 13:29:04','2015-01-17 13:29:04'),('nlhfsn97pqbu4ofv2aekv0lldb',5,'2015-01-22 10:33:59','2015-01-22 10:33:59'),('nulfu8js68j5khl2107bpngnip',5,'2015-01-24 13:45:43','2015-01-24 13:45:43'),('oek6gsttihq7l37976jitl5p7k',5,'2015-01-21 12:32:27','2015-01-21 12:32:27'),('p21ulglf8vs3j3h2sk8h9n4ovm',5,'2015-01-20 17:47:48','2015-01-20 17:47:48'),('ppce3q6frr0svnue6jqr37qaq5',5,'2015-01-20 15:18:51','2015-01-20 15:18:51'),('pr6gg6j9ko6d16qgbicpvutagd',5,'2015-01-17 14:04:10','2015-01-17 14:04:10'),('q670qgoid8iau0b75j2hbqqtbo',5,'2015-01-22 18:07:15','2015-01-22 18:07:15'),('s6sggmhaqidfb1nol85nbm1vfc',5,'2015-01-21 10:13:38','2015-01-21 10:13:38'),('t8i83qgtlg4httr2blr48g5g93',5,'2015-01-21 20:19:33','2015-01-21 20:19:33'),('tejcj3nhh37vps9of9fsl4ht43',5,'2015-01-21 11:00:59','2015-01-21 11:00:59'),('ttqroot53p6sv0cbp0alf04sr8',5,'2015-01-21 10:53:24','2015-01-21 10:53:24'),('tubc53eai9fe9a6tdnu6v3td5n',5,'2015-01-20 14:02:17','2015-01-20 14:02:17'),('tvomrjk0sglfsrco5g712o33ld',7,'2015-01-25 14:05:14','2015-01-25 14:05:14'),('u0lvalmd6t0a2k3k1gfgv7ibj0',5,'2015-01-21 10:23:14','2015-01-21 10:23:14'),('u27840g6eeujqi7dkg9ns3cpe3',5,'2015-01-20 17:36:36','2015-01-20 17:36:36'),('uki1c9tgpv71qtn39g89dlsprf',5,'2015-01-20 17:43:47','2015-01-20 17:43:47'),('umfil2r0rho78kdb080u9csbm1',5,'2015-01-17 13:52:20','2015-01-17 13:52:20'),('up90nhhh040bpjf62sc0t3g9sf',5,'2015-01-17 13:55:29','2015-01-17 13:55:29'),('utvrp4ki2ek242ds13ccekqjcb',5,'2015-01-22 13:52:43','2015-01-22 13:52:43');
/*!40000 ALTER TABLE `SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` text,
  `surname` text,
  `zipcode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `timeForPay` double DEFAULT '0',
  `idUserRole` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idUserCategory_idx_idx` (`idUserRole`),
  CONSTRAINT `idUseRole_idx` FOREIGN KEY (`idUserRole`) REFERENCES `USERROLE` (`idUserRole`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (4,'root','root',NULL,'root','pook',0,1),(5,'superuser','superuser',NULL,'superuser@mail.com','wVpSEFCGEBoGLdSUrt3uDTZyuo0xirXB',37,3),(6,'test','test',NULL,'test@mail.com','VqtoansNblo=',0,3),(7,'test2','test2',NULL,'test2@mail.com','VVRRGqzQvc0=',0,3);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERROLE`
--

DROP TABLE IF EXISTS `USERROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERROLE` (
  `idUserRole` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`idUserRole`),
  UNIQUE KEY `idUserCategory_UNIQUE` (`idUserRole`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERROLE`
--

LOCK TABLES `USERROLE` WRITE;
/*!40000 ALTER TABLE `USERROLE` DISABLE KEYS */;
INSERT INTO `USERROLE` VALUES (1,'Admin'),(2,'Moderator'),(3,'Driver'),(4,'Unpaid');
/*!40000 ALTER TABLE `USERROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WEEK`
--

DROP TABLE IF EXISTS `WEEK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WEEK` (
  `idWeek` bigint(20) NOT NULL AUTO_INCREMENT,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `promiseMondayTime` time DEFAULT NULL,
  `promiseTuesdayTime` time DEFAULT NULL,
  `promiseWednesdayTime` time DEFAULT NULL,
  `promiseThursdayTime` time DEFAULT NULL,
  `promiseFridayTime` time DEFAULT NULL,
  `promiseSaturdayTime` time DEFAULT NULL,
  `promiseSundayTime` time DEFAULT NULL,
  `idPeriod` bigint(20) DEFAULT NULL,
  `showCompensation` tinyint(1) DEFAULT '1',
  `saturdayCompensation` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idWeek`),
  KEY `fk_WEEK_1_idx` (`idPeriod`),
  CONSTRAINT `fk_WEEK_1` FOREIGN KEY (`idPeriod`) REFERENCES `PERIOD` (`idPeriod`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WEEK`
--

LOCK TABLES `WEEK` WRITE;
/*!40000 ALTER TABLE `WEEK` DISABLE KEYS */;
INSERT INTO `WEEK` VALUES (3,'2015-01-19','2015-01-25','14:00:00','11:00:00','12:00:00','13:00:00','13:00:00','15:00:00','16:00:00',2,1,0),(4,'2015-01-05','2015-01-11','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00',2,1,0),(5,'2015-01-12','2015-01-18','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00',2,1,0),(6,'2015-01-26','2015-02-01','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00',2,1,1),(7,'2015-01-19','2015-01-25','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00','08:00:00',4,1,0);
/*!40000 ALTER TABLE `WEEK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WORKHOURS`
--

DROP TABLE IF EXISTS `WORKHOURS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WORKHOURS` (
  `idWorkHours` bigint(20) NOT NULL AUTO_INCREMENT,
  `startWorkingTime` time NOT NULL,
  `endWorkingTime` time NOT NULL,
  `restTime` int(11) NOT NULL,
  `rideType` varchar(255) NOT NULL,
  `idDay` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idWorkHours`),
  UNIQUE KEY `idWorkHours_UNIQUE` (`idWorkHours`),
  KEY `fk_WORKHOURS_1_idx` (`idDay`),
  CONSTRAINT `fk_WORKHOURS_1` FOREIGN KEY (`idDay`) REFERENCES `Day` (`idDay`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORKHOURS`
--

LOCK TABLES `WORKHOURS` WRITE;
/*!40000 ALTER TABLE `WORKHOURS` DISABLE KEYS */;
INSERT INTO `WORKHOURS` VALUES (44,'11:00:00','23:00:00',10,'WORK',29,NULL),(45,'10:00:00','22:00:00',10,'WORK',30,NULL),(46,'09:00:00','21:00:00',10,'WORK',31,NULL),(47,'08:00:00','20:00:00',10,'WORK',32,NULL),(48,'07:00:00','19:00:00',10,'WORK',33,NULL),(49,'06:00:00','18:00:00',10,'WORK',34,NULL),(50,'05:00:00','17:00:00',10,'WORK',35,NULL),(51,'10:00:00','22:00:00',10,'WORK',36,NULL),(52,'10:00:00','22:00:00',10,'WORK',37,NULL),(53,'10:00:00','22:00:00',10,'WORK',38,NULL),(54,'10:00:00','22:00:00',10,'WORK',39,NULL),(55,'10:00:00','22:00:00',10,'WORK',40,NULL),(56,'10:00:00','22:00:00',10,'WORK',41,NULL),(57,'10:00:00','22:00:00',10,'WORK',42,NULL),(65,'10:00:00','22:00:00',10,'WORK',43,NULL),(66,'10:00:00','22:00:00',10,'WORK',44,NULL),(67,'10:00:00','22:00:00',10,'WORK',45,NULL),(68,'10:00:00','22:00:00',10,'WORK',46,NULL),(69,'10:00:00','22:00:00',10,'WORK',47,NULL),(70,'10:00:00','22:00:00',10,'WORK',48,NULL),(71,'10:00:00','22:00:00',10,'WORK',49,NULL),(107,'00:00:00','00:00:00',0,'WEEKEND',23,NULL),(108,'18:00:00','19:00:00',10,'WORK',24,NULL),(109,'02:08:00','20:00:00',10,'WORK',25,NULL),(113,'00:00:00','00:00:00',0,'WEEKEND',26,NULL),(115,'11:00:00','18:00:00',30,'WORK',22,NULL),(116,'14:00:00','17:00:00',10,'WORK',27,NULL),(117,'14:00:00','18:00:00',10,'WORK',28,NULL),(119,'00:00:00','00:00:00',0,'WORK',51,NULL),(120,'00:00:00','00:00:00',0,'WORK',52,NULL),(121,'00:00:00','00:00:00',0,'WORK',53,NULL),(122,'00:00:00','00:00:00',0,'WORK',54,NULL),(123,'00:00:00','00:00:00',0,'WORK',55,NULL),(124,'00:00:00','00:00:00',0,'WORK',56,NULL),(135,'17:05:00','19:05:00',10,'WORK',50,NULL);
/*!40000 ALTER TABLE `WORKHOURS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-26 12:57:46