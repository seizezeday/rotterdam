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
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `idWeek` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idDeclaration`),
  KEY `FK_ejvjakyv5ikpl19nr1fkym857` (`idWeek`),
  CONSTRAINT `FK_ejvjakyv5ikpl19nr1fkym857` FOREIGN KEY (`idWeek`) REFERENCES `WEEK` (`idWeek`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-03 13:30:00