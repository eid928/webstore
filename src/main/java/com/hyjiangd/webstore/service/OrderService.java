package com.hyjiangd.webstore.service;

import java.util.List;
import java.util.Map;

import com.hyjiangd.webstore.entity.Order;
import com.hyjiangd.webstore.entity.OrderDetail;

public interface OrderService {
	
	public List<Order> findByLoginUserAsBuyer();
	public List<Order> findByLoginUserAsSeller();
	public Order findByOrderId(int orderId);
	public List<OrderDetail> findOrderDetailsByOrderId(int orderId);
	public String summitOrder(List<Map<String, Integer>> cart);
}
