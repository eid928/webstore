USE `webstore`;

--
-- Table structure for table `chatmessage`
--

DROP TABLE IF EXISTS `chatmessage`;
CREATE TABLE `chatmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_username` varchar(50) NOT NULL,
  `to_username` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `send_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_FROM_USERNAME` (`from_username`), 
  
  CONSTRAINT `FK_FROM_USERNAME`
  FOREIGN KEY (`from_username`)
  REFERENCES `users` (`username`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION, 
  
  KEY `FK_TO_USERNAME` (`to_username`), 
  
  CONSTRAINT `FK_TO_USERNAME`
  FOREIGN KEY (`to_username`)
  REFERENCES `users` (`username`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;