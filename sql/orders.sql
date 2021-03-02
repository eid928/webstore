USE `webstore`;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(20) NOT NULL,
  `seller_username` varchar(50) NOT NULL,
  `buyer_username` varchar(50) NOT NULL,
  `status` int default 0 comment '0: 待結帳, 1: 出貨中, 2: 完成訂單',
  `total` float DEFAULT NULL,
  `creation_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_USERS_USERNAME_SELLER` (`seller_username`), 
  
  CONSTRAINT `FK_USERS_ORDERS_SELLER`
  FOREIGN KEY (`seller_username`)
  REFERENCES `users` (`username`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION, 
  
  KEY `FK_USERS_USERNAME_BUYER` (`buyer_username`), 
  
  CONSTRAINT `FK_USERS_ORDERS_BUYER`
  FOREIGN KEY (`buyer_username`)
  REFERENCES `users` (`username`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


INSERT INTO `orders` (id, seller_username, buyer_username, total)
VALUES 
('202103011455', 'hyjiang', 'eid928', 2000);
