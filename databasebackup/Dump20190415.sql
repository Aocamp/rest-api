-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: server_db
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `message_text` text NOT NULL,
  `message_date` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_id` (`room_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,86,'ААААаааАА','2019-03-13 15:22:22',55),(2,86,'лпочрчпгапш','2019-03-15 10:44:43',55),(3,86,'o','2019-03-15 05:51:30',55),(4,86,'шпшп','2019-03-15 12:41:43',55),(5,86,'аааа','2019-03-15 12:42:55',55),(6,86,'АаАААаААа','2019-03-15 12:45:13',55),(7,86,'12345','2019-03-15 12:48:24',55),(8,86,'очсщм','2019-03-15 12:48:40',55),(9,86,'под','2019-03-15 17:17:52',55),(10,86,'12335','2019-03-15 17:20:23',55),(11,86,'731','2019-03-15 17:22:39',55),(12,86,'831','2019-03-15 17:22:45',55),(13,86,'1124','2019-03-15 17:27:21',55),(14,86,'76','2019-03-15 17:27:29',55),(15,86,'1','2019-03-15 17:27:40',55),(16,86,'77','2019-03-15 17:29:41',55),(17,86,'vgfit','2019-04-05 05:44:48',55),(18,86,'qwe','2019-04-05 06:21:55',55),(19,86,'rrr','2019-04-05 06:25:50',55),(20,86,'to iijj','2019-04-05 06:34:52',55),(21,86,'nb enough','2019-04-05 06:35:08',55),(22,86,'yyyyy','2019-04-05 06:38:35',55),(23,86,'wwwwwww','2019-04-05 06:39:53',55),(24,86,'Hamilton','2019-04-05 06:47:51',55),(25,86,'happy','2019-04-05 06:51:32',55),(26,86,'Josh','2019-04-05 06:53:52',55),(27,86,'patch','2019-04-05 08:14:45',55),(28,86,'Karen','2019-04-05 08:15:03',55),(29,86,'we','2019-04-05 08:33:42',55),(30,86,'tonight','2019-04-05 08:37:16',55),(31,86,'kick','2019-04-05 09:03:59',55),(32,86,'boats','2019-04-05 09:38:56',55),(33,86,'vjvjvhv','2019-04-05 15:49:27',55),(34,86,'977','2019-04-05 15:49:33',55),(35,86,'first','2019-04-05 09:49:54',55),(36,86,'o','2019-04-08 06:48:23',55),(37,86,'t','2019-04-08 06:50:45',55),(38,86,'mmm','2019-04-08 07:00:07',55),(39,86,'ooi','2019-04-08 07:04:01',55),(40,86,'uuuuuu','2019-04-08 07:24:02',55),(41,86,'road','2019-04-09 08:27:55',55),(42,86,'оалаьа','2019-04-09 17:10:18',55);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(40) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_user_id` (`user_id`),
  CONSTRAINT `fk_room_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (86,'108890f5-3369-4a78-910b-1a69ba32e8c0',55);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `support_actions`
--

DROP TABLE IF EXISTS `support_actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `support_actions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `support_id` (`user_id`),
  CONSTRAINT `support_actions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `support_actions`
--

LOCK TABLES `support_actions` WRITE;
/*!40000 ALTER TABLE `support_actions` DISABLE KEYS */;
INSERT INTO `support_actions` VALUES (1,55);
/*!40000 ALTER TABLE `support_actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_login` varchar(40) NOT NULL,
  `support` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (55,'User1',1),(56,'UpdatedUser',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-15 10:10:09
