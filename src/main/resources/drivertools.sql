CREATE DATABASE  IF NOT EXISTS `rotterdam` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rotterdam`;
-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: rotterdam
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.14.04.1

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
-- Table structure for table `PERIOD`
--

DROP TABLE IF EXISTS `PERIOD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERIOD` (
  `idPeriod` bigint(20) NOT NULL AUTO_INCREMENT,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `timeForTime` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `periodType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPeriod`),
  KEY `fk_PERIOD_1_idx` (`idUser`),
  CONSTRAINT `fk_PERIOD_1` FOREIGN KEY (`idUser`) REFERENCES `USER` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERIOD`
--

LOCK TABLES `PERIOD` WRITE;
/*!40000 ALTER TABLE `PERIOD` DISABLE KEYS */;
INSERT INTO `PERIOD` VALUES (1,'2015-01-19','2015-02-16',0,5,'FOURWEEK'),(2,'2015-02-17','2015-03-16',0,5,'FOURWEEK');
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
INSERT INTO `SESSION` VALUES ('1tma47sikqh03nesdipoc0pjb8',5,'2015-01-17 13:24:39','2015-01-17 13:24:39'),('27u1opk3nk78h6r0ltq69j5jqv',5,'2015-01-17 13:16:40','2015-01-17 13:16:40'),('29rgsbetls8i78gf0s8nhbhtnr',5,'2015-01-18 13:42:55','2015-01-18 13:42:55'),('3a44200cfpf3ui9k49v9at6ttl',5,'2015-01-17 13:06:13','2015-01-17 13:06:13'),('70m2u1djf4fdbki1ckg4jp09ce',5,'2015-01-13 13:13:03','2015-01-13 13:13:03'),('af130437q8q3q94bu0dovhq0sl',5,'2015-01-17 13:47:22','2015-01-17 13:47:22'),('e5idr9bbhvvmsgos5mj6okqv26',5,'2015-01-19 08:51:05','2015-01-19 08:51:05'),('f1uemjh2ip4b852va2bfsianj5',5,'2015-01-18 11:58:31','2015-01-18 11:58:31'),('fbcv2r1mj9ni679b96251v0llu',5,'2015-01-17 13:44:11','2015-01-17 13:44:11'),('g32vup655qkf4ntnqhr16bblir',5,'2015-01-17 13:32:39','2015-01-17 13:32:39'),('g5n0fqc1hu6u5lfl7lo6lndavl',5,'2015-01-18 15:15:57','2015-01-18 15:15:57'),('jhueagnvqn2c67sssdph6m8mun',5,'2015-01-17 13:46:25','2015-01-17 13:46:25'),('k2lc8u8770bjbgopjg33okf0q1',5,'2015-01-17 14:50:01','2015-01-17 14:50:01'),('k6j4bcqrvu3035e5jpjs86mqpr',5,'2015-01-18 19:18:35','2015-01-18 19:18:35'),('k9rirp2c4oshqpuoscssr3od1h',5,'2015-01-17 14:12:39','2015-01-17 14:12:39'),('kl6t3uc0h6d0ijsl2o29k3i3nc',5,'2015-01-18 13:38:21','2015-01-18 13:38:21'),('m8sa66uodum10gnqmavd85vkrg',5,'2015-01-17 13:17:51','2015-01-17 13:17:51'),('msi75foh0oh33g0svhjvpnr6hd',5,'2015-01-17 13:15:12','2015-01-17 13:15:12'),('ndldrhplsp9hekru103bb90oqi',5,'2015-01-17 13:29:04','2015-01-17 13:29:04'),('pr6gg6j9ko6d16qgbicpvutagd',5,'2015-01-17 14:04:10','2015-01-17 14:04:10'),('umfil2r0rho78kdb080u9csbm1',5,'2015-01-17 13:52:20','2015-01-17 13:52:20'),('up90nhhh040bpjf62sc0t3g9sf',5,'2015-01-17 13:55:29','2015-01-17 13:55:29');
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
  `timeForPay` int(11) DEFAULT NULL,
  `idUserRole` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idUserCategory_idx_idx` (`idUserRole`),
  CONSTRAINT `idUseRole_idx` FOREIGN KEY (`idUserRole`) REFERENCES `USERROLE` (`idUserRole`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (4,'root','root',NULL,'root','pook',NULL,1),(5,'superuser','superuser',NULL,'superuser@mail.com','wVpSEFCGEBoGLdSUrt3uDTZyuo0xirXB',NULL,3);
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
  PRIMARY KEY (`idWeek`),
  KEY `fk_WEEK_1_idx` (`idPeriod`),
  CONSTRAINT `fk_WEEK_1` FOREIGN KEY (`idPeriod`) REFERENCES `PERIOD` (`idPeriod`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WEEK`
--

LOCK TABLES `WEEK` WRITE;
/*!40000 ALTER TABLE `WEEK` DISABLE KEYS */;
/*!40000 ALTER TABLE `WEEK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WORKHOURS`
--

DROP TABLE IF EXISTS `WORKHOURS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WORKHOURS` (
  `idWorkHours` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `startWorkingTime` time NOT NULL,
  `endWorkingTime` time NOT NULL,
  `restTime` int(11) NOT NULL,
  `rideType` varchar(255) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idWeek` bigint(20) NOT NULL,
  PRIMARY KEY (`idWorkHours`),
  UNIQUE KEY `idWorkHours_UNIQUE` (`idWorkHours`),
  KEY `fk_workhours_user` (`idUser`),
  KEY `fk_WORKHOURS_1_idx` (`idWeek`),
  CONSTRAINT `fk_WORKHOURS_1` FOREIGN KEY (`idWeek`) REFERENCES `WEEK` (`idWeek`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_workhours_user` FOREIGN KEY (`idUser`) REFERENCES `USER` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORKHOURS`
--

LOCK TABLES `WORKHOURS` WRITE;
/*!40000 ALTER TABLE `WORKHOURS` DISABLE KEYS */;
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

-- Dump completed on 2015-01-19 13:44:19