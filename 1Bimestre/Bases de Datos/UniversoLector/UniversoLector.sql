-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: universolector
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `autor`
--
DROP DATABASE IF EXISTS `universolector`;
CREATE DATABASE `universolector`;
USE `universolector`;
DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editorial`
--

DROP TABLE IF EXISTS `editorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editorial` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(100) NOT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editorial`
--

LOCK TABLES `editorial` WRITE;
/*!40000 ALTER TABLE `editorial` DISABLE KEYS */;
/*!40000 ALTER TABLE `editorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(13) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `anio_escritura` date DEFAULT NULL,
  `codigo_autor` int NOT NULL,
  `codigo_editorial` int NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  KEY `fk_libro_autor1_idx` (`codigo_autor`),
  KEY `fk_libro_editorial1_idx` (`codigo_editorial`),
  CONSTRAINT `fk_libro_autor1` FOREIGN KEY (`codigo_autor`) REFERENCES `autor` (`codigo`),
  CONSTRAINT `fk_libro_editorial1` FOREIGN KEY (`codigo_editorial`) REFERENCES `editorial` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_socio` int NOT NULL,
  `fecha` datetime NOT NULL,
  `fecha_devolucion` date NOT NULL,
  `fecha_tope` date NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Prestamo_Socio1_idx` (`codigo_socio`),
  CONSTRAINT `fk_Prestamo_Socio1` FOREIGN KEY (`codigo_socio`) REFERENCES `socio` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo_has_volumen`
--

DROP TABLE IF EXISTS `prestamo_has_volumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo_has_volumen` (
  `Prestamo_codigo` int NOT NULL,
  `volumen_codigo` int NOT NULL,
  PRIMARY KEY (`Prestamo_codigo`,`volumen_codigo`),
  KEY `fk_Prestamo_has_volumen_volumen1_idx` (`volumen_codigo`),
  KEY `fk_Prestamo_has_volumen_Prestamo1_idx` (`Prestamo_codigo`),
  CONSTRAINT `fk_Prestamo_has_volumen_Prestamo1` FOREIGN KEY (`Prestamo_codigo`) REFERENCES `prestamo` (`codigo`),
  CONSTRAINT `fk_Prestamo_has_volumen_volumen1` FOREIGN KEY (`volumen_codigo`) REFERENCES `volumen` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo_has_volumen`
--

LOCK TABLES `prestamo_has_volumen` WRITE;
/*!40000 ALTER TABLE `prestamo_has_volumen` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo_has_volumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `dni` int DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `nombres` varchar(100) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `Localidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` VALUES (1,3000000,'PATRICIA','JOHNSON','28 MySQL Boulevard','QLD'),(2,2988800,'LINDA','WILLIAMS','23 Workhaven Lane','Alberta'),(3,2500000,'BARBARA','JONES','Monroe 860','QLD'),(4,32980002,'LOIS','BUTLER','1688 Okara Way','Nothwest Border Prov'),(5,2313909,'ROBIN','HAYES','262 A Corua (La Corua) Parkway','Dhaka');
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefonoxsocio`
--

DROP TABLE IF EXISTS `telefonoxsocio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefonoxsocio` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nrotelefono` varchar(100) DEFAULT NULL,
  `codigo_socio` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_TelefonoxSocio_Socio_idx` (`codigo_socio`),
  CONSTRAINT `fk_TelefonoxSocio_Socio` FOREIGN KEY (`codigo_socio`) REFERENCES `socio` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefonoxsocio`
--

LOCK TABLES `telefonoxsocio` WRITE;
/*!40000 ALTER TABLE `telefonoxsocio` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefonoxsocio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volumen`
--

DROP TABLE IF EXISTS `volumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volumen` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `deteriorado` tinyint DEFAULT NULL,
  `libro_codigo` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_volumen_libro1_idx` (`libro_codigo`),
  CONSTRAINT `fk_volumen_libro1` FOREIGN KEY (`libro_codigo`) REFERENCES `libro` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volumen`
--

LOCK TABLES `volumen` WRITE;
/*!40000 ALTER TABLE `volumen` DISABLE KEYS */;
/*!40000 ALTER TABLE `volumen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-08  1:07:11
