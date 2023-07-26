
# Java Spring Project

This is a simple backend repository made with java spring boot 

* Used Thymeleaf for view and still updating

* For database example you can use this section : 

CREATE DATABASE  IF NOT EXISTS `employee_directory`;

USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--


INSERT INTO `employee` VALUES 

	(1,'Leslie','Andrews','example.com'),
	(2,'Emma','Baumgarten','example.com'),
	(3,'Avani','Gupta','example.com'),
	(4,'Yuri','Petrov','example.com'),
	(5,'Juan','Vega','example.com');


* Also don't forget to change database authorization in application.properties file (username,password etc.)




