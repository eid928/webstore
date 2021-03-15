USE `webstore`;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_username` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` int(11) not null,
  `description` varchar(200) DEFAULT NULL,
  `inventories` int DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL, 
  
  PRIMARY KEY (`id`),
  
  KEY `FK_USERS_USERNAME` (`seller_username`), 
  
  CONSTRAINT `FK_USERS_GOODS`
  FOREIGN KEY (`seller_username`)
  REFERENCES `users` (`username`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


INSERT INTO `goods` (seller_username, name, price, description, inventories, image)
VALUES 
('hyjiang', '測試商品', 2000, '測試描述', 10, 'image.jpg');
