package com.hyjiangd.webstore.service;

import java.util.List;
import java.util.Map;

import com.hyjiangd.webstore.entity.Order;

public interface OrderService {
	
	public List<Order> findByLoginUserAsBuyer();
	public List<Order> findByLoginUserAsSeller();
	public void summitOrder(List<Map<String, Integer>> cart);
}
