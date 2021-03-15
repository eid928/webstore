package com.hyjiangd.webstore.dao;

import java.util.List;

import com.hyjiangd.webstore.entity.Order;
import com.hyjiangd.webstore.entity.OrderDetail;

public interface OrderDao {
	
	public List<Order> findByUserAsBuyer(String username);
	public List<Order> findByUserAsSeller(String username);
	public void saveOrder(Order order);
	public void saveOrderDetail(OrderDetail orderDetail);
}
