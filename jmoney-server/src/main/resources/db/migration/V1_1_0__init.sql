#DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` bigint PRIMARY KEY,
  `reason` varchar(200) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `created_at` DATETIME DEFAULT NULL,

  KEY `index_reason` (`reason`),
  KEY `index_paymentDate` (`payment_date`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

#DROP TABLE IF EXISTS `juser`;
CREATE TABLE `juser` (

  `id` bigint PRIMARY KEY,
  `username` varchar(60) DEFAULT NULL,
  `created_at` DATETIME DEFAULT NULL,

  KEY `index_username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

#DROP TABLE IF EXISTS `jar`;
CREATE TABLE `jar` (

  `id` bigint PRIMARY KEY,
  `name` varchar(60) DEFAULT NULL,
  `created_at` DATETIME DEFAULT NULL,

  KEY `index_name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;

#DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` bigint PRIMARY KEY,
  `amount` decimal(19,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `income_date` date DEFAULT NULL,
  `userid` bigint NOT NULL,
  `created_at` DATETIME DEFAULT NULL,

  KEY `index_incomedate` (`income_date`),
  KEY `index_amount` (`amount`),
  FOREIGN KEY (userid) REFERENCES juser (id)
) ENGINE=MyISAM DEFAULT CHARSET=UTF8MB4;
