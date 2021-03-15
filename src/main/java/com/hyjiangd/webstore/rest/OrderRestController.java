package com.hyjiangd.webstore.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.Order;
import com.hyjiangd.webstore.service.OrderService;

@RestController
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("orders/asbuyer")
	public List<Order> findByLoginUserAsBuyer() {
		
		return orderService.findByLoginUserAsBuyer();
	}
	
	@GetMapping("orders/asseller")
	public List<Order> findByLoginUserAsSeller() {
		
		return orderService.findByLoginUserAsSeller();
	}
	
	@PostMapping("orders")
	public String summitOrder(@RequestBody List<Map<String, Integer>> cart) {
		
		orderService.summitOrder(cart);
		return "已提交訂單";
	}
}
