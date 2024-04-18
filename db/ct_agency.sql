-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ct_agency
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `company_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
INSERT INTO `agency` VALUES ('1','4TL',NULL,NULL,'123','123','123','1');
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` varchar(40) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` varchar(255) NOT NULL,
  `agency_id` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `date_updated` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_global_admin` int DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exporting_bill`
--

DROP TABLE IF EXISTS `exporting_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exporting_bill` (
  `id` varchar(40) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `date_Export` datetime DEFAULT NULL,
  `date_Created` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` enum('BOOKING','SHIPPING','CHECKED','CANCELLED','COMPELETED') DEFAULT NULL,
  `customer_id` varchar(40) DEFAULT NULL,
  `agency_id` varchar(40) DEFAULT NULL,
  `eCustomer_ID` varchar(12) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exporting_bill`
--

LOCK TABLES `exporting_bill` WRITE;
/*!40000 ALTER TABLE `exporting_bill` DISABLE KEYS */;
INSERT INTO `exporting_bill` VALUES ('1360acf6-b5b5-4af2-a97c-051598b587ba','DH-VLBK-4774',NULL,'2024-01-30 21:36:28',724671.46,'BOOKING','7ee502e3-660d-4e6a-a926-6e0c6cde0e5c',NULL,NULL,NULL,NULL,NULL,NULL),('16f15792-63f3-41de-88fc-eb914cd2305a','DH-MBBT-3517',NULL,'2024-01-30 16:23:34',439108.31,'BOOKING',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('28753365-5664-47a5-9dff-e9873c4e86ec','DH-KZHK-5643',NULL,'2024-01-30 19:55:35',439108.31,'BOOKING','532f1621-ed8e-4838-9c5a-c2a145b30375',NULL,NULL,NULL,NULL,NULL,NULL),('2a6fe3a6-713a-4cd3-9193-34e966bf43c4','DH-IYTF-6645',NULL,'2024-02-06 00:24:17',0,'BOOKING','4ed82c74-b208-4079-ad73-42bd16245fd5',NULL,NULL,NULL,NULL,NULL,NULL),('2e5baf4b-d927-4063-a14c-3667ef4814f1','DH-OVTQ-5247',NULL,'2024-02-06 20:06:44',10,'BOOKING',NULL,NULL,'KH-CXYV-9516',NULL,NULL,NULL,NULL),('2e813c3a-5cea-473c-a2ec-24264a1fd0a9','DH-FCRP-9608',NULL,'2024-01-30 19:55:13',878216.62,'BOOKING','96c28c6c-3145-4008-961a-6d65730f3713',NULL,NULL,NULL,NULL,NULL,NULL),('44841284-ef7f-409c-b78c-583abdbf927b','DH-BSYJ-3355',NULL,'2024-03-08 02:01:20',37767836.34,'BOOKING','4ed82c74-b208-4079-ad73-42bd16245fd5',NULL,NULL,NULL,NULL,NULL,NULL),('479a5195-2a58-42d4-979b-7f8d723c22db','DH-MDXD-6690',NULL,'2024-01-30 21:04:38',2579717.66,'BOOKING','7d1364f0-346f-436f-b576-faf91ce83052',NULL,NULL,NULL,NULL,NULL,NULL),('4e47438b-206a-4b17-8f40-fa83068fd9dc','DH-FMGK-2927',NULL,'2024-02-05 11:27:46',307055.58,'BOOKING','d1bfe669-aafc-40b6-99a3-fa107672f8ee',NULL,NULL,NULL,NULL,NULL,NULL),('50e021c9-9f36-4988-b102-a145a68cfa1b','DH-ZTNI-4303',NULL,'2024-01-30 21:37:22',724671.46,'BOOKING','67db8e2f-e15b-4d28-b52a-1d9cc288ffc8',NULL,NULL,NULL,NULL,NULL,NULL),('550ea5a3-29a3-4d9c-bac7-7683749918c5','DH-YYIG-1844',NULL,'2024-01-30 19:52:25',439108.31,'BOOKING','5ac903b7-9838-406d-b6e7-9d54fd88631c',NULL,NULL,NULL,NULL,NULL,NULL),('5fd44fe9-0f10-435c-a04e-ce839b0b628c','DH-SEGF-3600',NULL,'2024-01-30 21:59:28',1625158.1,'BOOKING','e5de526b-0eef-4bad-95d2-74694614b656',NULL,NULL,NULL,NULL,NULL,NULL),('65da8515-fc56-40c4-8fa5-72b479c92631','DH-VHPA-1334',NULL,'2024-02-06 00:42:05',1660299.57,'BOOKING','4ed82c74-b208-4079-ad73-42bd16245fd5',NULL,NULL,NULL,NULL,NULL,NULL),('a0cf5e66-bc1c-4bb2-9c30-80b146268b72','DH-JVRE-3425',NULL,'2024-01-30 22:04:59',439108.31,'BOOKING','205b0530-006a-402d-956d-7401aa787c1b',NULL,NULL,NULL,NULL,NULL,NULL),('a5a7aa72-92b1-43db-86d7-dc2e6b4682b7','DH-EIAJ-3345',NULL,'2024-02-06 16:22:25',10,'BOOKING',NULL,NULL,'KH-FEHM-1314',NULL,NULL,NULL,NULL),('a67d83ab-01a0-4f14-9107-50a19f125c63','DH-QPNM-8819',NULL,'2024-01-30 22:16:49',713536.45,'BOOKING','5abc19ad-464c-4df9-9474-075aef5c8fee',NULL,NULL,NULL,NULL,NULL,NULL),('aa06062a-0021-4f62-aab3-ab68586a4b88','DH-PGDQ-0441',NULL,'2024-01-30 20:00:06',713536.45,'BOOKING','3f474eab-7add-4f69-9510-a69ce7926f62',NULL,NULL,NULL,NULL,NULL,NULL),('adf84194-c7b7-4587-8e9f-9144e12b0188','DH-SILO-0931',NULL,'2024-02-05 21:17:25',614111.16,'BOOKING','ac01a8ec-d5d3-4de6-b5b7-62c8b9364591',NULL,NULL,NULL,NULL,NULL,NULL),('aec7379d-bedf-46da-97aa-aea61db7746f','DH-IDLE-8301',NULL,'2024-01-30 21:39:17',1174914.78,'BOOKING','c2baa63d-0f4c-4d3d-ac36-d6120d681434',NULL,NULL,NULL,NULL,NULL,NULL),('be09be55-237f-490f-bfb8-ceded84908a8','DH-WTBW-6609',NULL,'2024-02-06 16:24:30',10,'BOOKING',NULL,NULL,'KH-RMFO-6616',NULL,NULL,NULL,NULL),('be943c4a-def7-45c8-86cd-44f1b84b4462','DH-RMXV-7274',NULL,'2024-01-30 19:46:00',2502597.13,'BOOKING',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c040fcb3-079e-40ca-a384-c7d41920232f','DH-ZLSH-8913',NULL,'2024-01-30 20:57:24',2140609.35,'BOOKING','839e75e3-71a4-4d3d-b8f3-a3cd9fa950af',NULL,NULL,NULL,NULL,NULL,NULL),('c0b25b18-79ed-4884-9467-842ef4023380','DH-UNMO-7448',NULL,'2024-03-05 16:37:14',614111.16,'BOOKING','4ed82c74-b208-4079-ad73-42bd16245fd5',NULL,NULL,NULL,NULL,NULL,NULL),('c13114d0-0627-4dcd-9934-7326bd1c155e','DH-RBHD-0998',NULL,'2024-02-06 16:22:16',10,'BOOKING',NULL,NULL,'KH-ZRWT-9279',NULL,NULL,NULL,NULL),('c9f7066d-5ce2-4bb2-9dde-f48ab986c7fe','DH-TNHW-5780',NULL,'2024-01-30 21:24:56',450243.32,'BOOKING','c931ffc4-1efb-4da2-aa76-e7731412f505',NULL,NULL,NULL,NULL,NULL,NULL),('cb806df1-e40e-4f6b-a276-ae7580b1c94e','DH-GNTF-1597',NULL,'2024-03-04 20:36:27',0,'BOOKING','19c0c98b-75b6-48fc-9310-2ae0c4f829db',NULL,NULL,NULL,NULL,NULL,NULL),('e4844d56-71da-400c-8860-120c46b4ce42','DH-RWUR-0758',NULL,'2024-02-05 22:21:55',307055.58,'BOOKING','b58778ba-d948-4841-b221-0973681e754e',NULL,NULL,NULL,NULL,NULL,NULL),('fd495eef-4157-4eb3-ae6d-79881cde4d1e','DH-HAVP-9284','2024-03-08 12:52:08','2024-03-08 02:01:50',307055.58,'COMPELETED','4ed82c74-b208-4079-ad73-42bd16245fd5',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `exporting_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exporting_return_bill`
--

DROP TABLE IF EXISTS `exporting_return_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exporting_return_bill` (
  `id` varchar(255) NOT NULL,
  `agency_id` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `date_updated` datetime(6) DEFAULT NULL,
  `id_importingbill` varchar(255) DEFAULT NULL,
  `status` enum('COMPLETE','UNCOMPLETE') DEFAULT NULL,
  `supplier_id` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exporting_return_bill`
--

LOCK TABLES `exporting_return_bill` WRITE;
/*!40000 ALTER TABLE `exporting_return_bill` DISABLE KEYS */;
INSERT INTO `exporting_return_bill` VALUES ('36b53d2c-74ec-424f-84cc-fe6b4c0c50e0',NULL,'ER-SDOP-2620','2024-03-07 18:55:14.717000','2024-03-08 01:55:44.469978','36b53d2c-74ec-424f-84cc-fe6b4c0c50e0','COMPLETE','1cf02014-6f3d-44fb-8983-c00fd9cf558c',7500000),('38046733-d703-43d0-b120-0e4acad32818',NULL,'ER-AVSW-4732','2024-03-07 18:54:09.899000','2024-03-08 01:54:20.989730','38046733-d703-43d0-b120-0e4acad32818','COMPLETE','1cf02014-6f3d-44fb-8983-c00fd9cf558c',123467),('3c6205ca-4526-4feb-9348-50cbce198a31',NULL,'ER-YNAT-9871','2024-03-08 05:26:44.662000','2024-03-08 12:27:02.966626','3c6205ca-4526-4feb-9348-50cbce198a31','COMPLETE','1cf02014-6f3d-44fb-8983-c00fd9cf558c',20),('f1b5d12b-dc97-4a35-a64d-2229ca0b775a',NULL,'ER-JMBF-1904',NULL,'2024-03-06 07:20:41.630000','f1b5d12b-dc97-4a35-a64d-2229ca0b775a','COMPLETE',NULL,88888),('fe724580-7728-475b-98ee-c7b534448310',NULL,'ER-NFCD-6642',NULL,'2024-03-07 16:20:53.928000','fe724580-7728-475b-98ee-c7b534448310','UNCOMPLETE','1cf02014-6f3d-44fb-8983-c00fd9cf558c',9);
/*!40000 ALTER TABLE `exporting_return_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exporting_return_transaction`
--

DROP TABLE IF EXISTS `exporting_return_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exporting_return_transaction` (
  `id` varchar(255) NOT NULL,
  `amount` int DEFAULT NULL,
  `billReturn` varchar(255) DEFAULT NULL,
  `Product_ID` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exporting_return_transaction`
--

LOCK TABLES `exporting_return_transaction` WRITE;
/*!40000 ALTER TABLE `exporting_return_transaction` DISABLE KEYS */;
INSERT INTO `exporting_return_transaction` VALUES ('1f1c0a1a-c0b9-45f1-aed5-49e67ae32eb0',5000000,'36b53d2c-74ec-424f-84cc-fe6b4c0c50e0','05ccd690-84c1-4f1b-a2a2-42da37c6e372',50,NULL,NULL),('223b001f-7acb-42fb-b85d-30bc208a07e1',12,'fe724580-7728-475b-98ee-c7b534448310','05ccd690-84c1-4f1b-a2a2-42da37c6e372',3,NULL,NULL),('46491670-5834-4850-95a5-a2ed7d2b0927',222000,'f1b5d12b-dc97-4a35-a64d-2229ca0b775a','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1000,NULL,NULL),('89e972e6-b47c-4cda-beee-f6b8f49c89a5',46666,'3c6205ca-4526-4feb-9348-50cbce198a31','05ccd690-84c1-4f1b-a2a2-42da37c6e372',2,NULL,NULL),('c59d4767-1607-43ce-8213-c5f9936552fb',15129,'38046733-d703-43d0-b120-0e4acad32818','05ccd690-84c1-4f1b-a2a2-42da37c6e372',123,NULL,NULL);
/*!40000 ALTER TABLE `exporting_return_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exporting_transaction`
--

DROP TABLE IF EXISTS `exporting_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exporting_transaction` (
  `id` varchar(255) NOT NULL,
  `bill_ID` varchar(40) DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_ID` varchar(40) DEFAULT NULL,
  `size` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exporting_transaction`
--

LOCK TABLES `exporting_transaction` WRITE;
/*!40000 ALTER TABLE `exporting_transaction` DISABLE KEYS */;
INSERT INTO `exporting_transaction` VALUES ('01abbc7d-3958-4702-bf65-a3927d60dced','a67d83ab-01a0-4f14-9107-50a19f125c63',274428,1,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('01cdc425-cf8e-441d-87b8-d1d11c051b30','479a5195-2a58-42d4-979b-7f8d723c22db',1756433,4,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('08be51f2-e42b-411d-aa56-e1d46ab741b7','479a5195-2a58-42d4-979b-7f8d723c22db',823284,3,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('0ac678ff-c0f8-4474-ab89-aad07c5cf0c8','65da8515-fc56-40c4-8fa5-72b479c92631',1353243,3,'06555c6c-b5e4-4827-8c70-fe1068c399de','55c97861-b95c-4fa2-8892-6d446751dc6c','123524cc-1681-4d1b-a53a-77d287e78ff0'),('0fe6a784-c771-4d9c-81e3-7c5cb1ff491b','50e021c9-9f36-4988-b102-a145a68cfa1b',450243,1,'022907d1-d7b1-482c-9ed2-62a289ccceee',NULL,NULL),('19292c5c-cfc6-4638-8444-d244c1e32688','aec7379d-bedf-46da-97aa-aea61db7746f',274428,1,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('1d2d504e-eb81-4697-a4c8-df5d7244819b','550ea5a3-29a3-4d9c-bac7-7683749918c5',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('1dc8ee21-a162-4bec-bf5b-c53f9f77baed','fd495eef-4157-4eb3-ae6d-79881cde4d1e',307055,1,'05ccd690-84c1-4f1b-a2a2-42da37c6e372',NULL,NULL),('20d8dd21-063f-4455-8d6f-56bec0025d17','be943c4a-def7-45c8-86cd-44f1b84b4462',307055,1,NULL,NULL,NULL),('34643132-770d-40ce-83b0-857197cd7240','1360acf6-b5b5-4af2-a97c-051598b587ba',450243,1,'022907d1-d7b1-482c-9ed2-62a289ccceee',NULL,NULL),('3530fc39-ef72-4388-af90-e564863ed008','2e813c3a-5cea-473c-a2ec-24264a1fd0a9',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('3b328602-372c-4722-8161-338269b6fafb','28753365-5664-47a5-9dff-e9873c4e86ec',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('606753c1-a3c2-4d41-b589-a2c3439cf07a','1360acf6-b5b5-4af2-a97c-051598b587ba',274428,1,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('61fd344f-77b4-4b50-8e80-8d708805f6f1','aa06062a-0021-4f62-aab3-ab68586a4b88',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('683637a5-8190-4152-8e7c-d8d96844f8c7','a67d83ab-01a0-4f14-9107-50a19f125c63',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('68528779-dba8-485f-9713-0915ab76b181','adf84194-c7b7-4587-8e9f-9144e12b0188',614111,2,'05ccd690-84c1-4f1b-a2a2-42da37c6e372','82962bc4-5dca-440c-8268-d2c7ee279584','91d8b0aa-193b-4f2e-bbe2-42d7bb1ab29c'),('6caab127-13a4-4ef4-abc4-e34c48c5d260','50e021c9-9f36-4988-b102-a145a68cfa1b',274428,1,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('6eb6a689-90d8-480c-9aab-45c56acc7368','c040fcb3-079e-40ca-a384-c7d41920232f',1317324,3,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('7168b1de-d366-447b-abeb-f68b729a8ef9','5fd44fe9-0f10-435c-a04e-ce839b0b628c',274428,1,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('942d4a1a-720e-4cff-9c95-44a04856a734','4e47438b-206a-4b17-8f40-fa83068fd9dc',307055,1,'05ccd690-84c1-4f1b-a2a2-42da37c6e372','82962bc4-5dca-440c-8268-d2c7ee279584','91d8b0aa-193b-4f2e-bbe2-42d7bb1ab29c'),('94cc0767-71ec-452e-9921-21093515238c','aa06062a-0021-4f62-aab3-ab68586a4b88',274428,1,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('a45b4c0d-f91a-4dc3-839c-bef85ea6151d','44841284-ef7f-409c-b78c-583abdbf927b',37767836,123,'05ccd690-84c1-4f1b-a2a2-42da37c6e372',NULL,NULL),('ab67f121-0093-49dc-a334-a4b86411e4c4','5fd44fe9-0f10-435c-a04e-ce839b0b628c',1350729,3,'022907d1-d7b1-482c-9ed2-62a289ccceee',NULL,NULL),('b0f5b9e5-6950-42b7-943e-b4c5b7cccb68','c9f7066d-5ce2-4bb2-9dde-f48ab986c7fe',450243,1,'022907d1-d7b1-482c-9ed2-62a289ccceee',NULL,NULL),('b29f47f2-372b-4016-9388-654ee40fe386','2e813c3a-5cea-473c-a2ec-24264a1fd0a9',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('ba4802a0-8372-40c6-b44a-f131cf214e30','aec7379d-bedf-46da-97aa-aea61db7746f',900486,2,'022907d1-d7b1-482c-9ed2-62a289ccceee',NULL,NULL),('ba788bf5-dbdb-49c3-9e9b-c52e4cda2340','c040fcb3-079e-40ca-a384-c7d41920232f',823284,3,'00474ba4-da19-43a6-b980-9e2b439e992e',NULL,NULL),('c6605516-15c7-4bbc-ba53-78192f545ec9','a0cf5e66-bc1c-4bb2-9c30-80b146268b72',439108,1,'00e85d12-4828-4c6c-967f-2c84c339e4d8',NULL,NULL),('d3494017-8197-40e1-9cab-97f919b75e8e','16f15792-63f3-41de-88fc-eb914cd2305a',439108,1,NULL,NULL,NULL),('ddfe23e3-78c2-4432-ab1e-acef6cb0173d','2a6fe3a6-713a-4cd3-9193-34e966bf43c4',902162,2,NULL,'55c97861-b95c-4fa2-8892-6d446751dc6c','123524cc-1681-4d1b-a53a-77d287e78ff0'),('edad8e42-05f8-413a-a6e9-ad536903b448','be943c4a-def7-45c8-86cd-44f1b84b4462',439108,1,NULL,NULL,NULL),('f20cb6d8-63c9-4ac9-8e4b-4649fe9df547','be943c4a-def7-45c8-86cd-44f1b84b4462',1756433,4,NULL,NULL,NULL),('f33a6bdb-843f-4f2c-8414-c348ffcd8a2c','c0b25b18-79ed-4884-9467-842ef4023380',614111,2,'05ccd690-84c1-4f1b-a2a2-42da37c6e372',NULL,NULL);
/*!40000 ALTER TABLE `exporting_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importing`
--

DROP TABLE IF EXISTS `importing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `importing` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `agency_id` varchar(255) DEFAULT NULL,
  `supplier_id` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` enum('COMPLETE','UNCOMPLETE') DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importing`
--

LOCK TABLES `importing` WRITE;
/*!40000 ALTER TABLE `importing` DISABLE KEYS */;
INSERT INTO `importing` VALUES ('36b53d2c-74ec-424f-84cc-fe6b4c0c50e0','IP-UJJL-6063',NULL,NULL,7500000,'UNCOMPLETE',NULL,'2024-03-04 07:47:22'),('38046733-d703-43d0-b120-0e4acad32818','IP-VFQQ-3235',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',123467,'UNCOMPLETE',NULL,'2024-03-06 07:39:00'),('3c6205ca-4526-4feb-9348-50cbce198a31','IP-BIRB-4675',NULL,NULL,20,'UNCOMPLETE',NULL,'2024-03-30 02:57:00'),('426d71b9-775f-4ae7-b54a-ff4494b290df','IP-NWNW-2864',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',7,'UNCOMPLETE',NULL,NULL),('4a0e1c07-5be1-487a-b643-a6c88d9aecec','IP-XRNW-2601',NULL,NULL,5,'UNCOMPLETE',NULL,'2024-03-05 17:00:00'),('53fdd3c1-e045-428e-a92a-d9d1dc80d733','IP-JVLZ-7745',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',4350000,NULL,'2024-03-05 16:33:35','2024-03-05 09:32:11'),('5519d5ea-3734-49de-9592-37b04a01e571','IP-NTCE-2639',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',5000000,NULL,'2024-03-04 15:05:23','2024-03-04 08:04:54'),('5d44b1f5-8833-4f83-88c0-cd1530771e95','IP-YQGG-8924',NULL,NULL,6,'UNCOMPLETE',NULL,NULL),('67cbe271-9fe5-4842-8335-60f183694f53','IP-EIBZ-3618',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',11,'UNCOMPLETE',NULL,'2024-03-05 17:00:00'),('6835f6d5-64f4-4d02-99c1-fba38fd706fa','IP-OIFZ-4783',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',2000000,'UNCOMPLETE',NULL,'2024-03-01 17:00:00'),('6f113baf-1650-4bc8-bee2-b574de51f356','IP-YVQR-1839',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',10,'UNCOMPLETE',NULL,'2024-03-05 17:00:00'),('78d117c8-374d-4cd2-aca5-fd2a10081906','IP-KPCR-7887',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',4,'UNCOMPLETE',NULL,'2024-03-04 17:00:00'),('8b5630bd-aa98-4fde-8c71-ee0bee4a8970','IP-XEOS-4612',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',11,'UNCOMPLETE',NULL,'2024-03-05 17:00:00'),('920a5e13-1973-4258-88f0-bef4df719ad0','IP-LGQW-4919',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',100000,'UNCOMPLETE',NULL,'2024-03-05 22:37:00'),('af0f6d89-ed32-4292-9e19-0f50eb44eedb','IP-ISTJ-0994',NULL,NULL,2,'UNCOMPLETE',NULL,'2024-03-03 17:00:00'),('bf8f514b-5b67-46cf-b074-f06361e3f702','IP-FRLF-3193',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',941774099,'UNCOMPLETE',NULL,'2024-03-01 17:00:00'),('c0d54df5-79ea-4d96-993d-d43252e995d6','IP-DTKR-2382',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',21,'UNCOMPLETE',NULL,'2024-03-06 09:27:00'),('c34650d7-c088-4060-ad45-17ca592742f4','IP-VUZK-8666',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',2,'UNCOMPLETE',NULL,'2024-03-06 17:00:00'),('c37555c6-2c45-4a5b-9b56-16719f6f5a8f','IP-IYGZ-1625',NULL,NULL,999999,'UNCOMPLETE',NULL,'2024-03-29 17:00:00'),('e54281c7-9319-4b82-9094-8ac2d7b07cf2','IP-KKRP-2556',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',11,'UNCOMPLETE',NULL,'2024-03-05 17:00:00'),('e5e6674d-d4cf-4b52-b6b3-c79c4a96a440','IP-IYFR-1986',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',1000999,'UNCOMPLETE',NULL,'2024-03-04 07:58:01'),('eb685d23-a89d-4796-8c58-f0ece89ab15e','IP-IOVQ-7845',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',5400000,'COMPLETE','2024-03-02 13:07:46','2024-03-02 06:07:39'),('f1b5d12b-dc97-4a35-a64d-2229ca0b775a','IP-CWHX-1957',NULL,NULL,88888,'UNCOMPLETE',NULL,'2024-03-29 17:00:00'),('f432465a-0288-4370-ae37-b75408701ce4','IP-HZHA-7640',NULL,NULL,1000000,'UNCOMPLETE',NULL,NULL),('fc6ea057-512e-44d4-ba5b-4a5da2debe0f','IP-QMEQ-2114',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',1000000,'UNCOMPLETE','2024-03-02 13:07:39','2024-03-02 06:07:30'),('fe724580-7728-475b-98ee-c7b534448310','IP-HPBW-5936',NULL,'1cf02014-6f3d-44fb-8983-c00fd9cf558c',9,'COMPLETE','2024-03-07 23:20:54','2024-03-07 16:20:17'),('fe79c00f-4d60-4302-b3a2-732055cd01d4','IP-DXCA-6184',NULL,NULL,333333,'UNCOMPLETE',NULL,NULL),('ffd5d9b3-0c9b-4f6e-a094-3f9f5fe27be0','IP-LMOT-1543',NULL,'1ea80e10-de07-4fe4-b4fe-aac17563f4ec',3,'UNCOMPLETE',NULL,'2024-03-04 08:42:40');
/*!40000 ALTER TABLE `importing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importing_return_bill`
--

DROP TABLE IF EXISTS `importing_return_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `importing_return_bill` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `agency_id` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `date_Created` datetime(6) DEFAULT NULL,
  `date_Export` datetime(6) DEFAULT NULL,
  `eCustomer_ID` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `exporting_Id` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` enum('BOOKING','SHIPPING','CHECKED','CANCELLED','COMPELETED') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importing_return_bill`
--

LOCK TABLES `importing_return_bill` WRITE;
/*!40000 ALTER TABLE `importing_return_bill` DISABLE KEYS */;
INSERT INTO `importing_return_bill` VALUES ('fd495eef-4157-4eb3-ae6d-79881cde4d1e',NULL,NULL,'DH-HAVP-9284','4ed82c74-b208-4079-ad73-42bd16245fd5','2024-03-08 02:01:50.000000','2024-03-08 03:01:26.284065',NULL,NULL,'fd495eef-4157-4eb3-ae6d-79881cde4d1e',NULL,NULL,614111,'SHIPPING');
/*!40000 ALTER TABLE `importing_return_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importing_return_transaction`
--

DROP TABLE IF EXISTS `importing_return_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `importing_return_transaction` (
  `id` varchar(255) NOT NULL,
  `amount` int DEFAULT NULL,
  `bill_ID` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `Product_ID` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importing_return_transaction`
--

LOCK TABLES `importing_return_transaction` WRITE;
/*!40000 ALTER TABLE `importing_return_transaction` DISABLE KEYS */;
INSERT INTO `importing_return_transaction` VALUES ('1dc8ee21-a162-4bec-bf5b-c53f9f77baed',14738664,'fd495eef-4157-4eb3-ae6d-79881cde4d1e',NULL,'05ccd690-84c1-4f1b-a2a2-42da37c6e372',4,NULL);
/*!40000 ALTER TABLE `importing_return_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importing_transaction`
--

DROP TABLE IF EXISTS `importing_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `importing_transaction` (
  `id` varchar(255) NOT NULL,
  `importing_ID` varchar(255) DEFAULT NULL,
  `product_ID` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importing_transaction`
--

LOCK TABLES `importing_transaction` WRITE;
/*!40000 ALTER TABLE `importing_transaction` DISABLE KEYS */;
INSERT INTO `importing_transaction` VALUES ('046046dc-0a85-41d9-a383-a57da9cd207e','426d71b9-775f-4ae7-b54a-ff4494b290df','05ccd690-84c1-4f1b-a2a2-42da37c6e372',67,88888,5955496),('0790a476-fd14-4bf2-98d7-d7fc3d535308','67cbe271-9fe5-4842-8335-60f183694f53','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1000,12312321323,12312321323000),('1f1c0a1a-c0b9-45f1-aed5-49e67ae32eb0','36b53d2c-74ec-424f-84cc-fe6b4c0c50e0','05ccd690-84c1-4f1b-a2a2-42da37c6e372',50,100000,5000000),('223b001f-7acb-42fb-b85d-30bc208a07e1','fe724580-7728-475b-98ee-c7b534448310','05ccd690-84c1-4f1b-a2a2-42da37c6e372',3,4,12),('26569241-9d40-44fb-acc8-45fb5230bacb','ffd5d9b3-0c9b-4f6e-a094-3f9f5fe27be0','05ccd690-84c1-4f1b-a2a2-42da37c6e372',3,3,9),('2df3bc24-610e-475d-b17d-0e6b7559f028','eb685d23-a89d-4796-8c58-f0ece89ab15e','05ccd690-84c1-4f1b-a2a2-42da37c6e372',17,599000,10183000),('3993b30b-4f68-46f3-8443-c183c04025c9','eb685d23-a89d-4796-8c58-f0ece89ab15e','259cae4d-35bd-4737-a183-c6cb2f220ffc',8,700000,5600000),('40f209d6-bc4b-4f50-9a7c-8f1a2c263592','8b5630bd-aa98-4fde-8c71-ee0bee4a8970','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1000,12312321323,12312321323000),('46491670-5834-4850-95a5-a2ed7d2b0927','f1b5d12b-dc97-4a35-a64d-2229ca0b775a','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1000,222,222000),('477ef84f-2565-4ee7-8449-2a5d6ba315e6','fe79c00f-4d60-4302-b3a2-732055cd01d4','05ccd690-84c1-4f1b-a2a2-42da37c6e372',333,777,258741),('64f7dd6a-b591-4dc5-b825-53895f4d3121','c37555c6-2c45-4a5b-9b56-16719f6f5a8f','05ccd690-84c1-4f1b-a2a2-42da37c6e372',77,50000,3850000),('67d44ac9-b764-4134-9e75-f628d06f7701','fc6ea057-512e-44d4-ba5b-4a5da2debe0f','2a7e306a-beb5-493c-b52c-b909468d61ee',100,2000000,200000000),('69fc4bbe-f4ae-4e77-9ac9-c89a027e1bf4','bf8f514b-5b67-46cf-b074-f06361e3f702','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1002,100000,100200000),('6b8c998f-8f3f-4367-aca1-cfa03c4c3171','c0d54df5-79ea-4d96-993d-d43252e995d6','05ccd690-84c1-4f1b-a2a2-42da37c6e372',21,100,2100),('71872a7f-d418-4932-83c3-09390bb83af8','920a5e13-1973-4258-88f0-bef4df719ad0','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1,1000,1000),('89e972e6-b47c-4cda-beee-f6b8f49c89a5','3c6205ca-4526-4feb-9348-50cbce198a31','05ccd690-84c1-4f1b-a2a2-42da37c6e372',2,23333,46666),('9555d55d-891a-4d08-9b68-bc9fe0593b0e','e5e6674d-d4cf-4b52-b6b3-c79c4a96a440','05ccd690-84c1-4f1b-a2a2-42da37c6e372',99,250000,24750000),('993a705e-96b6-48d5-b3da-0cedd5a112c2','6835f6d5-64f4-4d02-99c1-fba38fd706fa','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1000,100000,100000000),('99b1a70a-766c-4175-a89d-f50d39d86b59','c34650d7-c088-4060-ad45-17ca592742f4','05ccd690-84c1-4f1b-a2a2-42da37c6e372',2,1,2),('9d3f38ad-2899-4998-8d56-c75c391d7b00','eb685d23-a89d-4796-8c58-f0ece89ab15e','201dffd5-bc08-499c-b345-8dd21a344bdb',27,168000,4536000),('a28e1130-a47b-4276-82f6-84805917b566','5d44b1f5-8833-4f83-88c0-cd1530771e95','05ccd690-84c1-4f1b-a2a2-42da37c6e372',111,222,24642),('a6223001-f678-4946-a795-3c0382c03aec','78d117c8-374d-4cd2-aca5-fd2a10081906','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1,2,2),('c59d4767-1607-43ce-8213-c5f9936552fb','38046733-d703-43d0-b120-0e4acad32818','05ccd690-84c1-4f1b-a2a2-42da37c6e372',123,123,15129),('cc2a952d-e6f0-4295-b213-431797564e91','eb685d23-a89d-4796-8c58-f0ece89ab15e','42885482-eacc-4c5c-936b-24ddcdd0c810',3,500000,1500000),('d40b382d-c7a4-4f4d-b231-6002bb9148ba','5519d5ea-3734-49de-9592-37b04a01e571','259cae4d-35bd-4737-a183-c6cb2f220ffc',100,500000,50000000),('e2f4ac89-66c0-4de6-8235-27feb7f55552','53fdd3c1-e045-428e-a92a-d9d1dc80d733','51cf31b2-c2f1-4b74-a090-92194a07233b',65,750000,48750000),('e6b826a9-9022-4e25-8051-8f3b41879d7b','e54281c7-9319-4b82-9094-8ac2d7b07cf2','05ccd690-84c1-4f1b-a2a2-42da37c6e372',1000,12312321323,12312321323000),('f67f21bc-2d56-4d2c-9710-42e4ecc9505d','bf8f514b-5b67-46cf-b074-f06361e3f702','1a96ec87-888c-45cf-88fa-b415692d7915',1000,100000,100000000);
/*!40000 ALTER TABLE `importing_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` varchar(255) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `beneficiary` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `status` enum('COMPLETE','UNCOMPLETE') DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `id_type_payment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('68409ae6-5d43-4ecf-ad6a-7e1ebe1233b5','PM-AYTF-0749',300450,'Lê Nguyễn ','2024-02-17 18:45:39','2024-02-24 22:07:42','UNCOMPLETE','Thiếu nợ','3'),('79c116ff-5942-4a29-9d4c-a71721f95bb0','PM-AXWC-4329',624000,'Nguyễn Hoàng Thịnh','2024-02-17 00:44:04','2024-02-24 22:08:23','COMPLETE','Tiền chi hàng ngày','4'),('9a01ba05-fc47-4fd8-9aff-5f340b9a7fdb','PM-SGDD-9391',245600,'Quân Mắm Tôm','2024-02-16 22:07:43','2024-02-24 22:07:27','UNCOMPLETE','Phiếu tiền chi trả tháng','4');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_transaction`
--

DROP TABLE IF EXISTS `payment_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_transaction` (
  `id` varchar(255) NOT NULL,
  `id_payment` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_transaction`
--

LOCK TABLES `payment_transaction` WRITE;
/*!40000 ALTER TABLE `payment_transaction` DISABLE KEYS */;
INSERT INTO `payment_transaction` VALUES ('016e4deb-a190-4eae-81fb-643095ba627d','79c116ff-5942-4a29-9d4c-a71721f95bb0',2,345000,690000),('682d212d-2306-400b-ab9a-12296d35bee0','68409ae6-5d43-4ecf-ad6a-7e1ebe1233b5',8,1000000,8000000),('75c81bc0-3df5-4eef-9edf-097b44b34f5b','68409ae6-5d43-4ecf-ad6a-7e1ebe1233b5',8,50000,400000),('9814b94f-9535-471a-9e9a-549e520925eb','e1f655d3-4e44-4c30-bf74-2e990dcbab40',NULL,25.75,51.5),('9a861fc5-e5d9-4fda-836c-8937fbcbc1b7','79c116ff-5942-4a29-9d4c-a71721f95bb0',7,66000,462000),('abcd5bfd-0d7e-4191-97b1-13e655d573c3','68409ae6-5d43-4ecf-ad6a-7e1ebe1233b5',0,23000,23000),('cf76a680-e1d3-41d0-9f05-366451804331','68409ae6-5d43-4ecf-ad6a-7e1ebe1233b5',0,44000,88000),('d21dfc6f-70e3-4aa7-b700-abaf9b4bc52c','e1f655d3-4e44-4c30-bf74-2e990dcbab40',NULL,25.75,51.5);
/*!40000 ALTER TABLE `payment_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `Code` varchar(255) DEFAULT NULL,
  `company_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `specification` enum('LOW','NORMAL','HIGH','EXTRA') DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id` varchar(255) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `id_type_receipt` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` enum('COMPLETE','UNCOMPLETE') DEFAULT NULL,
  `date_Updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES ('21f725ff-4c11-49bc-869b-eb85815f29e0','RC-ECWW-7860','7',600000,'2024-02-17 22:45:36','Tiền Thu gom','COMPLETE','2024-02-24 21:50:37.003688'),('33fdc412-c3f9-4993-81de-0a9d0c5c68e5','RC-DOUB-7244','6',100000,'2024-02-09 23:21:31','This is a sample receipt','UNCOMPLETE','2024-02-17 22:57:33.277320'),('34c6210f-3dad-4c14-8fc9-4aa99e6d32f8','RC-ADFZ-2859','6',990000,'2024-02-09 17:43:10','This is a sample receipt','UNCOMPLETE','2024-02-17 22:57:50.359414');
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_transaction`
--

DROP TABLE IF EXISTS `receipt_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt_transaction` (
  `id` varchar(255) NOT NULL,
  `id_receipt` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_transaction`
--

LOCK TABLES `receipt_transaction` WRITE;
/*!40000 ALTER TABLE `receipt_transaction` DISABLE KEYS */;
INSERT INTO `receipt_transaction` VALUES ('0b5baea3-4e47-4718-ad27-b9bb65e31b1e',NULL,NULL,50,50),('3e059ca5-cdce-48a8-a08a-65a8377cb72b',NULL,NULL,25,50),('66dfb01c-2038-487d-8a78-89b7044c7c03','33fdc412-c3f9-4993-81de-0a9d0c5c68e5',0,25,50),('8df99039-4768-4678-a365-6f7a1c07b5f1',NULL,NULL,25,50),('95c438f6-511a-47a7-9544-b5e443a7b0f5',NULL,NULL,50,50),('9a39f4ef-d7d2-40c4-aa48-3b2f8989d907','33fdc412-c3f9-4993-81de-0a9d0c5c68e5',0,50,50),('9fce5ee5-b5fe-4ca0-8e8c-a54e33e34e58',NULL,4,1000000,4000000),('bea89359-d39c-445d-9ba2-b76538b702c7','21f725ff-4c11-49bc-869b-eb85815f29e0',2,76000,152000),('e4e6196d-17ef-4c78-b4dc-53eb0a6f1cfe',NULL,2,88500,177000);
/*!40000 ALTER TABLE `receipt_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_payment_receipt`
--

DROP TABLE IF EXISTS `type_payment_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_payment_receipt` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` enum('PAYMENT','RECEIPT') DEFAULT NULL,
  `date_Created` datetime DEFAULT NULL,
  `date_Updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_payment_receipt`
--

LOCK TABLES `type_payment_receipt` WRITE;
/*!40000 ALTER TABLE `type_payment_receipt` DISABLE KEYS */;
INSERT INTO `type_payment_receipt` VALUES ('1','Khác','Phụ Phí khác','PAYMENT',NULL,NULL),('2','Chi tiền điện','Phí kinh doanh bán hàng','PAYMENT',NULL,NULL),('3','Chi cộng tác viên','Phí dịch vụ ','PAYMENT',NULL,NULL),('4','Chi thuế kinh doanh','Phí kinh doanh bán hàng','PAYMENT',NULL,NULL),('5','Chi dụng cụ bán hàng','Phí kinh doanh bán quần áo','PAYMENT',NULL,NULL),('6','Tiền nhận','Phí doanh thu','RECEIPT',NULL,NULL),('7','Tiền lời tháng','Phí lợi nhuận tháng','RECEIPT',NULL,NULL);
/*!40000 ALTER TABLE `type_payment_receipt` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17 18:52:40
