package com.hyjiangd.webstore.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.GoodsDao;
import com.hyjiangd.webstore.dao.OrderDao;
import com.hyjiangd.webstore.dao.UserDao;
import com.hyjiangd.webstore.entity.Goods;
import com.hyjiangd.webstore.entity.Order;
import com.hyjiangd.webstore.entity.OrderDetail;

@Service
public class OrderServiceImp implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private GoodsDao GoodsDao;
	
	@Autowired
	private UserDao UserDao;
	
	@Override
	@Transactional
	public List<Order> findByLoginUserAsBuyer() {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Now Login: " + loginUsername);
		List<Order> orders = orderDao.findByUserAsBuyer(loginUsername);
		
		return orders;
	}

	@Override
	@Transactional
	public List<Order> findByLoginUserAsSeller() {
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Now Login: " + loginUsername);
		List<Order> orders = orderDao.findByUserAsSeller(loginUsername);
		
		return orders;
	}

	@Override
	@Transactional
	public void summitOrder(List<Map<String, Integer>> cart) {
		
		Order order = new Order();
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
		order.setBuyerUser(UserDao.findUserDetailByUsername(loginUsername).getUser());
		order.setStatus(0);
		order.setCreationTime(new Date());
		
		int total = 0;
		
		for (Map<String, Integer> item : cart) {
			Goods goods = GoodsDao.findById(item.get("goodsId"));
			int subtotal = goods.getPrice() * item.get("quantity");
			total += subtotal;
			order.setSellerUser(goods.getUser());
			orderDao.saveOrder(order);
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setGoods(goods);
			orderDetail.setQuantity(item.get("quantity"));
			orderDetail.setSubtotal(subtotal);
			orderDao.saveOrderDetail(orderDetail);
		}
		
		order.setTotal(total);
		orderDao.saveOrder(order);
	}
}
