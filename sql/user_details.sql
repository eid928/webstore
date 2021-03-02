USE `webstore`;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_USERS_USERNAME` (`username`), 
  
  CONSTRAINT `FK_USERS`
  FOREIGN KEY (`username`)
  REFERENCES `users` (`username`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


INSERT INTO `user_details` (username, name, email, address, phone)
VALUES 
('eid928','江和穎-1','eid928@gmail.com', '台北市信義區基隆路二段151-7號五樓', '0903101841'),
('hyjiang','江和穎-2','hyjiang@gmail.com','台北市信義區基隆路二段151-7號五樓', '0926128748');
