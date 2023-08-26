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
-- Table structure for table `orderProducts`
--

DROP TABLE IF EXISTS `orderProducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderProducts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `statusId` varchar(255) DEFAULT NULL,
  `voucherId` int DEFAULT NULL,
  `addressUserId` int DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `isPaymentOnline` int DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` datetime DEFAULT NULL,
  `codeOrder` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `addressUserId` (`addressUserId`),
  CONSTRAINT `orderProducts_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `orderProducts_ibfk_2` FOREIGN KEY (`addressUserId`) REFERENCES `addressUser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderProducts`
--

LOCK TABLES `orderProducts` WRITE;
/*!40000 ALTER TABLE `orderProducts` DISABLE KEYS */;
INSERT INTO `orderProducts` VALUES (92,1,'1',1,1,'',1,'2023-08-21 17:27:39',NULL,'77490e73-1683-4cb8-baa3-053f36fe2350'),(93,1,'1',1,1,'',1,'2023-08-21 18:26:34',NULL,'34ab3537-6d7f-45d8-a217-371617be3c5a'),(94,1,'1',1,1,'',1,'2023-08-21 18:29:28',NULL,'f4600f74-cb88-437f-a082-624478028044'),(95,1,'1',1,1,'',1,'2023-08-22 07:06:23',NULL,'08450670-38c2-4dd2-9b8f-3a23633c3c4d'),(96,1,NULL,0,1,NULL,0,'2023-08-22 11:45:34',NULL,'6977f36d-6ccc-4a91-9ffb-c6eeeea7139c'),(97,1,'1',1,1,'',1,'2023-08-22 11:48:06',NULL,'df9642a7-08e6-43ba-9a30-e56fe2f458bf'),(98,1,'1',1,1,'',1,'2023-08-23 04:59:45',NULL,'83751b34-cbc1-4cca-ac76-7a1ab2311741'),(99,1,'1',1,1,'',1,'2023-08-23 05:00:19',NULL,'9615ad59-4501-44ba-b2d6-8ff75b734a3c'),(100,1,'1',1,1,'',1,'2023-08-23 05:00:41',NULL,'642132cf-4d4e-4017-a7de-c61428df5937'),(101,1,'1',1,1,'',1,'2023-08-23 05:01:28',NULL,'bde95f97-539c-4e50-9105-b6c7dad16e4f');
/*!40000 ALTER TABLE `orderProducts` ENABLE KEYS */;
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
