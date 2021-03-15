USE `webstore`;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` INT(11) NOT NULL auto_increment,
  `orderid` int(11) NOT NULL,
  `goodsid` int(11) NOT NULL,
  `quantity` int default 1,
  `subtotal` int DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_ORDERS_ORDERDETAILS` (`orderid`), 
  
  CONSTRAINT `FK_ORDERS_ORDERDETAILS`
  FOREIGN KEY (`orderid`)
  REFERENCES `orders` (`id`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION, 
  
  KEY `FK_GOODS_ORDERDETAILS` (`goodsid`), 
  
  CONSTRAINT `FK_GOODS_ORDERDETAILS`
  FOREIGN KEY (`goodsid`)
  REFERENCES `goods` (`id`)
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


INSERT INTO `order_details` (orderid, goodsid, quantity, subtotal)
VALUES 
(1, 1, 1, 2000);