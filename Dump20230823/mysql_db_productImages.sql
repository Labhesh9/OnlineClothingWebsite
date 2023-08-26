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
-- Table structure for table `productImages`
--

DROP TABLE IF EXISTS `productImages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productImages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `caption` varchar(255) DEFAULT NULL,
  `productDetailId` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productDetailId` (`productDetailId`),
  CONSTRAINT `productImages_ibfk_1` FOREIGN KEY (`productDetailId`) REFERENCES `productsDetail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productImages`
--

LOCK TABLES `productImages` WRITE;
/*!40000 ALTER TABLE `productImages` DISABLE KEYS */;
INSERT INTO `productImages` VALUES (23,'Regular Fit Round-neck T-shirt',3,'1691907607040.jpg',NULL,NULL),(24,'Regular Fit Round-neck T-shirt',3,'1691910730524.jpg',NULL,NULL),(25,'Regular Fit Round-neck T-shirt',3,'1691910789669.jpg',NULL,NULL),(26,'Regular Fit Round-neck T-shirt',3,'1691910833140.jpg',NULL,NULL),(27,'Relaxed Fit Printed T-shirt',4,'1691910895504.jpg',NULL,NULL),(28,'Relaxed Fit Printed T-shirt',4,'1691911127672.jpg',NULL,NULL),(29,'Relaxed Fit Printed T-shirt',4,'1691911169012.jpg',NULL,NULL),(30,'Relaxed Fit Printed T-shirt',4,'1691911200010.jpg',NULL,NULL),(31,'Relaxed Fit Printed T-shirt',5,'1692179849701.jpg',NULL,NULL),(32,'Relaxed Fit Printed T-shirt',5,'1692180131352.jpg',NULL,NULL),(33,'Relaxed Fit Printed T-shirt',5,'1692180158969.jpg',NULL,NULL),(34,'Relaxed Fit Printed T-shirt',5,'1692180191026.jpg',NULL,NULL),(35,'Oversized print-motif top',6,'1692180494550.jpg',NULL,NULL),(36,'Oversized print-motif top',6,'1692180592949.jpg',NULL,NULL),(37,'Oversized print-motif top',6,'1692180626659.jpg',NULL,NULL),(38,'Oversized print-motif top',6,'1692180653610.jpg',NULL,NULL),(39,'Oversized print-motif top',7,'1692180908478.jpg',NULL,NULL),(40,'Oversized print-motif top',7,'1692180908478.jpg',NULL,NULL),(41,'Oversized print-motif top',7,'1692181008696.jpg',NULL,NULL),(42,'Oversized print-motif top',7,'1692181048859.jpg',NULL,NULL),(43,'Oversized print-motif top',7,'1692181076429.jpgg',NULL,NULL);
/*!40000 ALTER TABLE `productImages` ENABLE KEYS */;
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
