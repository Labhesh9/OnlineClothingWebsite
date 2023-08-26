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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nameCategory` varchar(255) DEFAULT NULL,
  `agetypesId` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2,'T-SHIRTS & TANK TOPS',1,'',NULL,NULL),(3,'MEN\'S SHIRTS',1,'',NULL,NULL),(4,'Trousers',1,'',NULL,NULL),(5,'Hoodies & Sweatshirts',1,'',NULL,NULL),(6,'Jeans',1,'',NULL,NULL),(7,'Shorts',1,'',NULL,NULL),(8,'Knitwear',1,'',NULL,NULL),(9,'Swimwear',1,'',NULL,NULL),(10,'Sportswear',1,'',NULL,NULL),(11,'Tops & T-shirts',6,'',NULL,NULL),(12,'Dresses',6,'',NULL,NULL),(13,'Shirts & Blouses',6,'',NULL,NULL),(14,'Trousers',6,'',NULL,NULL),(15,'Jeans',6,'',NULL,NULL),(16,'Shorts',6,'',NULL,NULL),(17,'Skirts',6,'',NULL,NULL),(18,'Nightwear',6,'',NULL,NULL),(19,'Loungewear',6,'',NULL,NULL),(20,'Jumpsuits',6,'',NULL,NULL),(21,'Jumpsuits',6,'',NULL,NULL),(22,'NEWBORN BABY CLOTHES',NULL,'Our newborn baby clothes are perfect for keeping little ones comfortable-as-can-be. Whether you\'re searching for gifts or refreshing on everyday essentials, we have various options to get new parents started out. ',NULL,NULL),(23,'BOYS’ OUTERWEAR 2-8Y',NULL,'Our boys’ outerwear is adventure friendly and designed to keep him warm and dry. When it comes to practical jackets and coats, our edit serves him waterproof jackets, puffer jackets, fleece jackets and cozy gilets.',NULL,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-23 14:25:29
