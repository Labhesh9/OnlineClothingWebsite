-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: mysql_db
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `contentHTML` longtext,
  `contentMarkdown` longtext,
  `statusId` varchar(255) DEFAULT NULL,
  `categoryId` int DEFAULT NULL,
  `agetypesId` int DEFAULT NULL,
  `view` int DEFAULT NULL,
  `madeby` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `brandId` int DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  KEY `brandId` (`brandId`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`brandId`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (4,'Regular Fit Round-neck T-shirt','','',NULL,6,1,1,'','Cotton',1,'2023-08-12 17:27:46','2023-08-13 09:23:51'),(5,'Relaxed Fit Printed T-shirt','','',NULL,6,1,1,'','Cotton',1,'2023-08-12 18:52:57','2023-08-13 09:24:23'),(7,'Relaxed Fit Printed T-shirt','','',NULL,2,1,1,'','Cotton',1,'2023-08-16 09:55:33','2023-08-16 09:55:33'),(8,'Oversized print-motif top','','',NULL,11,6,1,'','Cotton',1,'2023-08-16 10:07:19','2023-08-16 10:07:19'),(9,'Long-sleeved jersey top','','',NULL,11,6,1,'','Cotton',1,'2023-08-16 10:12:28','2023-08-16 10:12:28'),(36,'Embroidered baseball jacket','','',NULL,8,4,1,'','Cotton',1,'2023-08-22 21:07:59','2023-08-22 21:07:59'),(37,'Printed hoodie','','',NULL,5,4,1,'','Cotton',1,'2023-08-23 03:31:13','2023-08-23 03:31:13'),(38,'Sports shorts','','',NULL,5,4,1,'','Cotton',1,'2023-08-23 03:34:19','2023-08-23 03:34:19'),(39,'DryMove™ Sports shorts','','',NULL,7,5,1,'','Cotton',1,'2023-08-23 03:40:27','2023-08-23 03:40:27'),(40,'Knitted cotton all-in-one suit','','',NULL,8,3,1,'','Cotton',1,'2023-08-23 03:50:36','2023-08-23 03:50:36'),(41,'2-piece cotton set','','',NULL,8,3,1,'','Cotton',1,'2023-08-23 03:57:05','2023-08-23 03:57:05'),(42,'2-piece sweatshirt set','','',NULL,16,3,1,'','Cotton',1,'2023-08-23 04:54:09','2023-08-23 04:54:09');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-23 14:25:30
