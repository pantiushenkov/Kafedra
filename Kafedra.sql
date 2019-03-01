CREATE DATABASE  IF NOT EXISTS `Kafedra` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `Kafedra`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: Kafedra
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `idDepartment` int(6) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Phone` char(10) NOT NULL,
  PRIMARY KEY (`idDepartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES (1,'Математики','3820938203'),(2,'Історія','435646466'),(3,'Філософія','033764823'),(4,'Фізики','3234555');
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Management`
--

DROP TABLE IF EXISTS `Management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Management` (
  `idTeacher` int(11) NOT NULL,
  `idScientificWork` int(11) NOT NULL,
  `Start` date DEFAULT NULL,
  `End` date DEFAULT NULL,
  PRIMARY KEY (`idTeacher`,`idScientificWork`),
  KEY `idScientificWork_idx` (`idScientificWork`),
  CONSTRAINT `idScientificWork` FOREIGN KEY (`idScientificWork`) REFERENCES `scientificwork` (`idscientificwork`) ON UPDATE CASCADE,
  CONSTRAINT `idTeacher` FOREIGN KEY (`idTeacher`) REFERENCES `teacher` (`idteacher`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Management`
--

LOCK TABLES `Management` WRITE;
/*!40000 ALTER TABLE `Management` DISABLE KEYS */;
INSERT INTO `Management` VALUES (2,4,'2015-12-12','2015-05-03');
/*!40000 ALTER TABLE `Management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ScientificWork`
--

DROP TABLE IF EXISTS `ScientificWork`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ScientificWork` (
  `idScientificWork` int(11) NOT NULL,
  `idDepartment` int(6) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Manager` varchar(20) DEFAULT NULL,
  `Customer` varchar(20) DEFAULT NULL,
  `Start` date DEFAULT NULL,
  `End` date DEFAULT NULL,
  PRIMARY KEY (`idScientificWork`),
  KEY `idDepartment_idx` (`idDepartment`),
  CONSTRAINT `idDepartment` FOREIGN KEY (`idDepartment`) REFERENCES `department` (`iddepartment`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ScientificWork`
--

LOCK TABLES `ScientificWork` WRITE;
/*!40000 ALTER TABLE `ScientificWork` DISABLE KEYS */;
INSERT INTO `ScientificWork` VALUES (4,1,'PubNub', 'Кривенко', 'Єфремова', '2018-12-12', '2018-12-12');
/*!40000 ALTER TABLE `ScientificWork` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teacher`
--

DROP TABLE IF EXISTS `Teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teacher` (
  `idTeacher` int(11) NOT NULL,
  `idDepartment` int(11) NOT NULL,
  `Position` varchar(20) NOT NULL,
  `Title` varchar(30) NOT NULL,
  `Surname` varchar(20) NOT NULL,
  `StartDate` date NOT NULL,
  `Phone` char(10) DEFAULT NULL,
  `Sex` char(1) DEFAULT NULL,
  PRIMARY KEY (`idTeacher`),
  KEY `idDepartment_idx` (`idDepartment`),
  CONSTRAINT `idDepartmen` FOREIGN KEY (`idDepartment`) REFERENCES `department` (`iddepartment`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES (2,2,'Викладач','Доцент','Петренко','2015-01-12','0994453423','ч');
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-01 17:55:46
