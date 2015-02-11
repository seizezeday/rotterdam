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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Day`
--

LOCK TABLES `Day` WRITE;
/*!40000 ALTER TABLE `Day` DISABLE KEYS */;
INSERT INTO `Day` VALUES (57,'2015-01-26',8),(58,'2015-01-27',8),(59,'2015-01-28',8),(60,'2015-01-29',8),(61,'2015-01-30',8),(62,'2015-01-31',8),(63,'2015-02-01',8),(64,'2015-01-19',9),(65,'2015-01-20',9),(66,'2015-01-21',9),(67,'2015-01-22',9),(68,'2015-01-23',9),(69,'2015-01-24',9),(70,'2015-01-25',9),(71,'2015-01-26',10),(72,'2015-01-27',10),(73,'2015-01-28',10),(74,'2015-01-29',10),(75,'2015-01-30',10),(76,'2015-01-31',10),(77,'2015-02-01',10),(78,'2015-02-02',12),(79,'2015-02-03',12),(80,'2015-02-04',12),(81,'2015-02-05',12),(82,'2015-02-06',12),(83,'2015-02-07',12),(84,'2015-02-08',12),(85,'2015-02-09',13),(86,'2015-02-10',13),(87,'2015-02-11',13),(88,'2015-02-12',13),(89,'2015-02-13',13),(90,'2015-02-14',13),(91,'2015-02-15',13);
/*!40000 ALTER TABLE `Day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Declaration`
--

DROP TABLE IF EXISTS `Declaration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Declaration` (
  `idDeclaration` bigint(20) NOT NULL AUTO_INCREMENT,
  `costType` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL,
  `idDay` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idDeclaration`),
  KEY `FK_ejvjakyv5ikpl19nr1fkym857` (`idDay`),
  CONSTRAINT `fk_Declaration_1` FOREIGN KEY (`idDay`) REFERENCES `Day` (`idDay`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Declaration`
--

LOCK TABLES `Declaration` WRITE;
/*!40000 ALTER TABLE `Declaration` DISABLE KEYS */;
INSERT INTO `Declaration` VALUES (87,'0',15,NULL,78),(88,'3',78,NULL,78),(89,'1',26,NULL,78),(90,'2',14,NULL,78),(91,'0',78,NULL,78),(92,'0',56,NULL,79);
/*!40000 ALTER TABLE `Declaration` ENABLE KEYS */;
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
  `overTime` double DEFAULT '0',
  `idUser` int(11) DEFAULT NULL,
  `periodType` varchar(45) DEFAULT NULL,
  `calculated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idPeriod`),
  KEY `fk_PERIOD_1_idx` (`idUser`),
  CONSTRAINT `fk_PERIOD_1` FOREIGN KEY (`idUser`) REFERENCES `USER` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERIOD`
--

LOCK TABLES `PERIOD` WRITE;
/*!40000 ALTER TABLE `PERIOD` DISABLE KEYS */;
INSERT INTO `PERIOD` VALUES (5,'2015-01-05','2015-02-01',0,8,'FOURWEEK',1),(6,'2015-01-05','2015-02-01',0,9,'FOURWEEK',0),(7,'2014-12-08','2014-12-28',123,8,'FOURWEEK',1),(8,'2015-02-02','2015-03-01',0,8,'FOURWEEK',0);
/*!40000 ALTER TABLE `PERIOD` ENABLE KEYS */;
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
INSERT INTO `SESSION` VALUES ('d5ha4hl1e84ognvpk5gu2scn30',8,'2015-02-09 19:22:49','2015-02-11 08:03:35');
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
  `timeForTime` double DEFAULT '0',
  `regNum` varchar(45) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (8,'superuser','superuser',NULL,'superuser@mail.com','wVpSEFCGEBoGLdSUrt3uDTZyuo0xirXB',434,'123456','Driver'),(9,'Vasya','pupkin',NULL,'pupkin@mail.ru','J2SLm4cwpfmp8RKo8MqeEw==',0,NULL,'Driver');
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WEEK`
--

LOCK TABLES `WEEK` WRITE;
/*!40000 ALTER TABLE `WEEK` DISABLE KEYS */;
INSERT INTO `WEEK` VALUES (8,'2015-01-26','2015-02-01','12:00:00','12:00:00','12:00:00','12:00:00','12:00:00',NULL,NULL,5,0,1),(9,'2015-01-19','2015-01-25','12:00:00','12:00:00','21:00:00','21:00:00','02:00:00','00:00:00','00:00:00',5,0,0),(10,'2015-01-26','2015-02-01','04:00:00','04:00:00','04:00:00','04:00:00','04:00:00','00:00:00','00:00:00',6,0,0),(11,'2014-12-15','2014-12-21','12:00:00','21:00:00','12:00:00','12:00:00','12:00:00',NULL,NULL,7,0,0),(12,'2015-02-02','2015-02-08','11:00:00','12:00:00','12:00:00','12:00:00','12:00:00',NULL,NULL,8,0,0),(13,'2015-02-09','2015-02-15','12:00:00','12:00:00','12:00:00','12:00:00','12:00:00',NULL,NULL,8,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORKHOURS`
--

LOCK TABLES `WORKHOURS` WRITE;
/*!40000 ALTER TABLE `WORKHOURS` DISABLE KEYS */;
INSERT INTO `WORKHOURS` VALUES (159,'07:00:00','20:00:00',90,'Werkdag',71,NULL),(160,'06:00:00','22:00:00',120,'Werkdag',72,NULL),(161,'07:00:00','20:00:00',90,'Werkdag',73,NULL),(162,'16:00:00','20:00:00',0,'Werkdag',74,NULL),(163,'00:00:00','00:00:00',0,'Weekenddag',75,NULL),(164,'00:00:00','00:00:00',0,'Weekenddag',76,NULL),(165,'00:00:00','00:00:00',0,'Weekenddag',77,NULL),(188,'17:00:00','19:00:00',0,'Werkdag',67,NULL),(192,'18:00:00','19:00:00',0,'Werkdag',70,NULL),(207,'00:00:00','00:00:00',0,'Tijd_voor_tijd',65,NULL),(208,'00:00:00','00:00:00',0,'Werkdag',64,NULL),(209,'00:00:00','00:00:00',0,'Werkdag',66,NULL),(210,'18:00:00','21:00:00',0,'Werkdag',69,NULL),(211,'19:00:00','22:00:00',0,'Werkdag',68,NULL),(220,'09:05:00','21:50:00',90,'Werkdag',59,NULL),(223,'10:00:00','21:00:00',90,'Werkdag',63,NULL),(224,'09:55:00','22:50:00',90,'Werkdag',62,NULL),(225,'09:50:00','21:00:00',90,'Werkdag',60,NULL),(226,'10:55:00','23:55:00',90,'Werkdag',61,NULL),(230,'00:00:00','00:00:00',0,'Werkdag',80,NULL),(231,'00:00:00','00:00:00',0,'Werkdag',81,NULL),(232,'00:00:00','00:00:00',0,'Werkdag',82,NULL),(233,'00:00:00','00:00:00',0,'Werkdag',83,NULL),(234,'00:00:00','00:00:00',0,'Werkdag',84,NULL),(237,'00:00:00','00:00:00',0,'Werkdag',79,NULL),(243,'11:00:00','13:55:00',91,'Werkdag',57,NULL),(244,'14:00:00','16:55:00',30,'Werkdag',57,NULL),(257,'09:10:00','16:50:00',90,'Werkdag',58,NULL),(258,'17:00:00','22:50:00',30,'Werkdag',58,NULL),(259,'01:10:00','12:55:00',91,'Werkdag',78,NULL),(261,'00:00:00','00:00:00',0,'Werkdag',86,NULL),(264,'00:00:00','00:00:00',0,'Werkdag',89,NULL),(265,'00:00:00','00:00:00',0,'Werkdag',90,NULL),(266,'00:00:00','00:00:00',0,'Werkdag',91,NULL),(269,'00:05:00','21:00:00',150,'Werkdag',85,NULL),(275,'09:02:00','23:59:00',120,'Werkdag',87,NULL),(276,'00:00:00','09:02:00',60,'Werkdag',88,NULL);
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

-- Dump completed on 2015-02-11 10:05:30
