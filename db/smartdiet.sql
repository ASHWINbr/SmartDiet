# MySQL-Front 5.1  (Build 1.5)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: smartdiet
# ------------------------------------------------------
# Server version 5.0.24a-community-nt

DROP DATABASE IF EXISTS `smartdiet`;
CREATE DATABASE `smartdiet` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `smartdiet`;

#
# Source for table diet
#

CREATE TABLE `diet` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(255) default NULL,
  `height` varchar(255) default NULL,
  `weight` varchar(255) default NULL,
  `age` varchar(255) default NULL,
  `gender` varchar(255) default NULL,
  `bmi` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `weight_to_be` varchar(255) default NULL,
  `plan` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Dumping data for table diet
#
LOCK TABLES `diet` WRITE;
/*!40000 ALTER TABLE `diet` DISABLE KEYS */;

/*!40000 ALTER TABLE `diet` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table user
#

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Dumping data for table user
#
LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
