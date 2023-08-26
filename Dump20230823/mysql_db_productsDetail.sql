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
-- Table structure for table `productsDetail`
--

DROP TABLE IF EXISTS `productsDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productsDetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productId` int DEFAULT NULL,
  `description` longtext,
  `nameDetail` varchar(255) DEFAULT NULL,
  `imageproduct` varchar(255) DEFAULT NULL,
  `originalPrice` bigint DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  CONSTRAINT `productsDetail_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productsDetail`
--

LOCK TABLES `productsDetail` WRITE;
/*!40000 ALTER TABLE `productsDetail` DISABLE KEYS */;
INSERT INTO `productsDetail` VALUES (3,4,'Regular-fit T-shirt in soft cotton jersey with a round, rib-trimmed neckline and a straight hem.','','1691907607040.jpg',399,NULL,NULL),(4,5,'Relaxed-fit T-shirt in soft cotton jersey with a print motif. Rib-trimmed neckline, dropped shoulders and a straight-cut hem.','','1691910895504.jpg',799,NULL,NULL),(5,7,'Relaxed-fit T-shirt in soft cotton jersey with a print motif, round,','Relaxed Fit Printed T-shirt','1692179849701.jpg',799,NULL,NULL),(6,8,'Oversized top in cotton jersey with a print motif on the front. Round neckline, low dropped shoulders and a straight-cut hem with a slit at each side.','Oversized print-motif top','1692180494550.jpg',1399,NULL,NULL),(7,9,'Fitted top in soft jersey with a square neckline and long sleeves.','Oversized print-motif top','1692180494550.jpg',1399,NULL,NULL),(17,36,'Loose-fit baseball jacket in woven fabric with embroidered motifs. Small, ribbed collar, dropped shoulders, press-studs down the front, welt front pockets and ribbing at the cuffs and hem.','Embroidered baseball jacket','1692738470332.jpg',2299,NULL,NULL),(18,37,'Loose-fit hoodie in printed sweatshirt fabric with a soft brushed inside. Lined hood with a wrapover front, dropped shoulders, long sleeves and ribbing at the cuffs and hem.','Printed hoodie','1692761470515.jpg',1499,NULL,NULL),(19,38,'Shorts in fast-drying functional fabric with good air permeability to wick away moisture and help keep you dry and cool while exercising. Elastication and a concealed drawstring at the waist, pockets in the side seams, and a slit in each side of the hem.','Sports shorts','1692761645496.jpg',999,NULL,NULL),(20,39,'Sports shorts in stretchy DryMove™ functional fabric that helps to wick away moisture from your skin, keeping you comfortably dry while you move. Regular fit with an elasticated, drawstring waist, zipped side pockets and gently rounded hems.','DryMove™ Sports shorts','1692762025437.jpg',1999,NULL,NULL),(21,40,'All-in-one suit knitted in soft cotton with buttons down the front. Hood with ear-shaped appliqués, open, rib-trimmed front pockets and ribbing around the hood, cuffs and hem.','Knitted cotton all-in-one suit','1692762626421.jpg',1999,NULL,NULL),(22,41,'Set with a long-sleeved bodysuit and a pair of dungarees. Bodysuit in soft cotton jersey with press-studs on the shoulders and at the crotch. Dungarees in cotton sweatshirt fabric with an all-over print, straps that cross at the back and have adjustable buttoning, elastication at the back of the waist and patch front pockets. Concealed press-studs at the crotch and down the legs.','2-piece cotton set','1692763023641.jpg',1499,NULL,NULL),(23,42,'Comfortable set with a top and pair of joggers in sweatshirt fabric with an all-over print and a soft brushed inside. Top with dropped shoulders, long sleeves and a press-stud on one shoulder (except in sizes 2–4Y). Ribbing around the neckline, cuffs and hem. Joggers with an elasticated, drawstring waist and ribbed hems.','2-piece sweatshirt set','1692766447447.jpg',1399,NULL,NULL);
/*!40000 ALTER TABLE `productsDetail` ENABLE KEYS */;
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
