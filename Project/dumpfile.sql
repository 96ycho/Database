-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: localhost    Database: dbproject
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Current Database: `dbproject`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dbproject` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `dbproject`;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `aname` varchar(30) NOT NULL,
  `rldate` varchar(15) DEFAULT NULL,
  `anumber` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`anumber`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES ('to','2017-07-08',1),('nothing','2017-11-13',2),('perfect','2017-11-17',3),('twicetagram','2017-10-30',4),('somthing','2017-10-23',5),('brother','2017-10-16',6),('love',NULL,7);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `mgrnumber` int(11) NOT NULL AUTO_INCREMENT,
  `mgrname` varchar(30) NOT NULL,
  `mid` varchar(30) NOT NULL,
  PRIMARY KEY (`mgrnumber`),
  UNIQUE KEY `mid` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'eunji','eunji'),(2,'manager1','manager1'),(3,'manager2','manager2');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `mname` varchar(30) NOT NULL,
  `mnumber` int(11) NOT NULL AUTO_INCREMENT,
  `playtime` varchar(10) DEFAULT NULL,
  `rldate` varchar(15) DEFAULT NULL,
  `snum` int(11) DEFAULT NULL,
  `anum` int(11) DEFAULT NULL,
  `mgrnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`mnumber`),
  KEY `snum` (`snum`),
  KEY `anum` (`anum`),
  KEY `mgrnum` (`mgrnum`),
  CONSTRAINT `music_ibfk_1` FOREIGN KEY (`snum`) REFERENCES `singer` (`snumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_ibfk_2` FOREIGN KEY (`anum`) REFERENCES `album` (`anumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `music_ibfk_3` FOREIGN KEY (`mgrnum`) REFERENCES `manager` (`mgrnumber`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES ('beautiful',1,'3:16','2017-11-13',1,2,1),('wannabe',2,'3:21','2017-07-08',1,1,1),('peekaboo',3,'3:45','2017-11-17',2,3,1),('likey',4,'3:34',NULL,3,4,1),('energetic',5,NULL,NULL,1,1,1),('springday',6,'3:50','2016-05-06',7,7,1),('dna',7,'4:02',NULL,7,7,1),('missyou',8,NULL,'2017-10-16',6,6,1),('mylady',9,NULL,NULL,6,6,1),('burnitup',10,'3:10','2017-07-08',1,1,1),('twilight',11,NULL,NULL,1,2,1),('signal',12,'3:48',NULL,3,4,1);
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `unum` int(11) NOT NULL,
  `pname` varchar(30) NOT NULL,
  `createdate` varchar(15) DEFAULT NULL,
  `totalnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`unum`,`pname`),
  CONSTRAINT `f_user` FOREIGN KEY (`unum`) REFERENCES `user` (`unumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (14,'eunjis','2017-12-06',2),(14,'wannaone','2017-12-06',5);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playon`
--

DROP TABLE IF EXISTS `playon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playon` (
  `mnum` int(11) NOT NULL,
  `unum` int(11) NOT NULL,
  `pname` varchar(30) NOT NULL,
  PRIMARY KEY (`mnum`,`unum`,`pname`),
  KEY `f_playlist` (`unum`,`pname`),
  CONSTRAINT `f_music` FOREIGN KEY (`mnum`) REFERENCES `music` (`mnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_playlist` FOREIGN KEY (`unum`, `pname`) REFERENCES `playlist` (`unum`, `pname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playon`
--

LOCK TABLES `playon` WRITE;
/*!40000 ALTER TABLE `playon` DISABLE KEYS */;
INSERT INTO `playon` VALUES (3,14,'eunjis'),(4,14,'eunjis'),(1,14,'wannaone'),(2,14,'wannaone'),(5,14,'wannaone'),(10,14,'wannaone'),(11,14,'wannaone');
/*!40000 ALTER TABLE `playon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `singer`
--

DROP TABLE IF EXISTS `singer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `singer` (
  `sname` varchar(30) NOT NULL,
  `debutdate` varchar(15) DEFAULT NULL,
  `snumber` int(11) NOT NULL AUTO_INCREMENT,
  `sex` int(11) DEFAULT NULL,
  `groupn` int(11) DEFAULT NULL,
  PRIMARY KEY (`snumber`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `singer`
--

LOCK TABLES `singer` WRITE;
/*!40000 ALTER TABLE `singer` DISABLE KEYS */;
INSERT INTO `singer` VALUES ('wannaone','2017-07-08',1,1,1),('redvelvet',NULL,2,2,1),('twice','2015-05-25',3,2,1),('urban','2011-03-23',4,3,1),('epikhigh','2007-08-08',5,1,1),('btob','2013-11-23',6,NULL,NULL),('bts',NULL,7,1,1);
/*!40000 ALTER TABLE `singer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `name` varchar(30) NOT NULL,
  `id` varchar(30) NOT NULL,
  `sex` char(1) DEFAULT NULL,
  `bdate` varchar(15) DEFAULT NULL,
  `rgtdate` varchar(15) DEFAULT NULL,
  `unumber` int(11) NOT NULL AUTO_INCREMENT,
  `mgrnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`unumber`),
  UNIQUE KEY `id` (`id`),
  KEY `mgrnum` (`mgrnum`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`mgrnum`) REFERENCES `manager` (`mgrnumber`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('user0','user0','F','1999-11-11','2017-12-06',1,1),('user1','user1','M',NULL,NULL,2,1),('data','data',NULL,'1000-01-01',NULL,3,1),('base','base',NULL,NULL,'1000-01-01',4,1),('heeseon','heeson','F','1996-12-10','2017-12-06',5,1),('gunju','gun','M','1996-07-08',NULL,6,1),('eunkyung','cane','F',NULL,'2017-12-06',9,NULL),('heeji','heejijang','F','1996-11-01','2017-12-06',10,NULL),('sungmin','park',NULL,NULL,'2017-12-06',11,NULL),('yoonjin','jini','F',NULL,'2017-12-06',12,NULL),('eunji','eunji','F','1996-10-02','2017-12-06',14,NULL),('eunji','asdf',NULL,NULL,'2017-12-06',15,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-06 23:10:04
