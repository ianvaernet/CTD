-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: spotify
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
-- Table structure for table `albums`
--
DROP DATABASE IF EXISTS `spotify`;
CREATE DATABASE `spotify`;
USE `spotify`;

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artistas_id` int NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `anio_publicacion` smallint NOT NULL,
  `imagen_portada` varchar(255) NOT NULL,
  `discografia_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_albums_artistas1_idx` (`artistas_id`),
  KEY `fk_albums_discografia1_idx` (`discografia_id`),
  CONSTRAINT `fk_albums_artistas1` FOREIGN KEY (`artistas_id`) REFERENCES `artistas` (`id`),
  CONSTRAINT `fk_albums_discografia1` FOREIGN KEY (`discografia_id`) REFERENCES `discografias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artistas`
--

DROP TABLE IF EXISTS `artistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artistas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artistas`
--

LOCK TABLES `artistas` WRITE;
/*!40000 ALTER TABLE `artistas` DISABLE KEYS */;
/*!40000 ALTER TABLE `artistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `canciones`
--

DROP TABLE IF EXISTS `canciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `album_id` int NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `duracion` time NOT NULL,
  `numero_reproducciones` int NOT NULL,
  `numero_likes` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_canciones_album_idx` (`album_id`),
  CONSTRAINT `fk_canciones_album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canciones`
--

LOCK TABLES `canciones` WRITE;
/*!40000 ALTER TABLE `canciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `canciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `canciones_playlists`
--

DROP TABLE IF EXISTS `canciones_playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canciones_playlists` (
  `canciones_id` int NOT NULL,
  `playlists_id` int NOT NULL,
  PRIMARY KEY (`canciones_id`,`playlists_id`),
  KEY `fk_canciones_has_playlists_playlists1_idx` (`playlists_id`),
  KEY `fk_canciones_has_playlists_canciones1_idx` (`canciones_id`),
  CONSTRAINT `fk_canciones_has_playlists_canciones1` FOREIGN KEY (`canciones_id`) REFERENCES `canciones` (`id`),
  CONSTRAINT `fk_canciones_has_playlists_playlists1` FOREIGN KEY (`playlists_id`) REFERENCES `playlists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canciones_playlists`
--

LOCK TABLES `canciones_playlists` WRITE;
/*!40000 ALTER TABLE `canciones_playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `canciones_playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discografias`
--

DROP TABLE IF EXISTS `discografias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discografias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discografias`
--

LOCK TABLES `discografias` WRITE;
/*!40000 ALTER TABLE `discografias` DISABLE KEYS */;
/*!40000 ALTER TABLE `discografias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos_canciones`
--

DROP TABLE IF EXISTS `generos_canciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos_canciones` (
  `genero_id` int NOT NULL,
  `canciones_id` int NOT NULL,
  PRIMARY KEY (`genero_id`,`canciones_id`),
  KEY `fk_genero_has_canciones_canciones1_idx` (`canciones_id`),
  KEY `fk_genero_has_canciones_genero1_idx` (`genero_id`),
  CONSTRAINT `fk_genero_has_canciones_canciones1` FOREIGN KEY (`canciones_id`) REFERENCES `canciones` (`id`),
  CONSTRAINT `fk_genero_has_canciones_genero1` FOREIGN KEY (`genero_id`) REFERENCES `generos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos_canciones`
--

LOCK TABLES `generos_canciones` WRITE;
/*!40000 ALTER TABLE `generos_canciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `generos_canciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `monto` float NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `suscripcion_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pagos_suscripcion1_idx` (`suscripcion_id`),
  CONSTRAINT `fk_pagos_suscripcion1` FOREIGN KEY (`suscripcion_id`) REFERENCES `suscripciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_tarjeta`
--

DROP TABLE IF EXISTS `pagos_tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_tarjeta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(45) NOT NULL,
  `ultimos_digitos` varchar(4) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `suscripcion_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pago_tarjeta_suscripcion1_idx` (`suscripcion_id`),
  CONSTRAINT `fk_pago_tarjeta_suscripcion1` FOREIGN KEY (`suscripcion_id`) REFERENCES `suscripciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_tarjeta`
--

LOCK TABLES `pagos_tarjeta` WRITE;
/*!40000 ALTER TABLE `pagos_tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos_tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_transferencia`
--

DROP TABLE IF EXISTS `pagos_transferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_transferencia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cbu` bigint DEFAULT NULL,
  `alias` varchar(100) DEFAULT NULL,
  `suscripcion_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pago_transferencia_suscripcion1_idx` (`suscripcion_id`),
  CONSTRAINT `fk_pago_transferencia_suscripcion1` FOREIGN KEY (`suscripcion_id`) REFERENCES `suscripciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_transferencia`
--

LOCK TABLES `pagos_transferencia` WRITE;
/*!40000 ALTER TABLE `pagos_transferencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos_transferencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwords`
--

DROP TABLE IF EXISTS `passwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passwords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(150) NOT NULL,
  `fecha_modificacion` date NOT NULL,
  `usuarios_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`password`),
  KEY `fk_passwords_usuarios1_idx` (`usuarios_id`),
  CONSTRAINT `fk_passwords_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwords`
--

LOCK TABLES `passwords` WRITE;
/*!40000 ALTER TABLE `passwords` DISABLE KEYS */;
/*!40000 ALTER TABLE `passwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `numero_canciones` int DEFAULT NULL,
  `fecha_creacion` date NOT NULL,
  `usuarios_id` int NOT NULL,
  `estado` enum('activa','eliminada') NOT NULL,
  `fecha_eliminacion` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_playlists_usuarios1_idx` (`usuarios_id`),
  CONSTRAINT `fk_playlists_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripciones`
--

DROP TABLE IF EXISTS `suscripciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('estandar','premium') NOT NULL,
  `usuarios_id` int NOT NULL,
  `forma_pago` enum('tarjeta','transferencia') NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_renovacion` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_suscripcion_usuarios1_idx` (`usuarios_id`),
  CONSTRAINT `fk_suscripcion_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripciones`
--

LOCK TABLES `suscripciones` WRITE;
/*!40000 ALTER TABLE `suscripciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `suscripciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `codigo_postal` varchar(45) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-08 12:30:02
