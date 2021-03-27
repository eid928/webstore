package com.hyjiangd.webstore.service;

import java.util.Date;
import java.util.HashMap;
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
import com.hyjiangd.webstore.message.CrudMsg;

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
	public Order findByOrderId(int orderId) {
		
		Order order = orderDao.findByOrderId(orderId);
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!loginUsername.equals(order.getBuyerUser().getUsername()) && !loginUsername.equals(order.getSellerUser().getUsername())) {
			throw new RuntimeException("您並非此訂單的關係人");
		}
		
		return order;
	}

	@Override
	@Transactional
	public List<OrderDetail> findOrderDetailsByOrderId(int orderId) {

		Order order = orderDao.findByOrderId(orderId);
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!loginUsername.equals(order.getBuyerUser().getUsername()) && !loginUsername.equals(order.getSellerUser().getUsername())) {
			throw new RuntimeException("您並非此訂單的關係人");
		}
		
		return orderDao.findOrderDetailsByOrderId(orderId);
	}

	@Override
	@Transactional
	public CrudMsg summitOrder(List<Map<String, Integer>> cart) { // Map<String, Integer>為購物車內一種商品的購買訊息，{"goodsId"= ??, "quantity"= ??}
		
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		String message = "";
		Map<String, Order> orders = new HashMap<>();
		// {賣家帳號: 訂單}
		// 購物車內不同賣家的商品將自動分成不同訂單
		
		for (Map<String, Integer> item : cart) {
			
			Goods goodsOfTheItem = GoodsDao.findById(item.get("goodsId"));
			String sellerOfTheItem = goodsOfTheItem.getUser().getUsername();
			int inventories = goodsOfTheItem.getInventories();
			int subtotal = goodsOfTheItem.getPrice() * item.get("quantity");
			
			if (loginUsername.equals(sellerOfTheItem)) { //若購物車中有屬於買家自己上架的商品，則略過
				
				message += "※商品：「" + goodsOfTheItem.getName() + "」為您上架的商品，無法成立訂單\n";
				continue;
				
			} else if (item.get("quantity") <= 0) {
				
				message += "※商品：「" + goodsOfTheItem.getName() + "」之數量異常(<=0)，無法成立訂單\n";
				continue;
				
			} else if (item.get("quantity") > goodsOfTheItem.getInventories()) {
				
				message += "※商品：「" + goodsOfTheItem.getName() + "」之數量超過庫存，無法成立訂單\n";
				continue;
				
			} else if (!orders.containsKey(sellerOfTheItem)) { // 若此賣家尚未建立order，則建立order以及orderDetail
				
				System.out.println("item: " + item);
				
				Order order = new Order();
				order.setSellerUser(goodsOfTheItem.getUser());
				order.setBuyerUser(UserDao.findUserDetailByUsername(loginUsername).getUser());
				order.setTotal(subtotal);
				order.setStatus(0);
				order.setCreationTime(new Date());
				orderDao.saveOrder(order);
				
				orders.put(sellerOfTheItem, order);
				
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setGoods(goodsOfTheItem);
				orderDetail.setOrder(order);
				orderDetail.setQuantity(item.get("quantity"));
				orderDetail.setSubtotal(subtotal);
				
				goodsOfTheItem.setInventories(inventories - item.get("quantity"));
				GoodsDao.update(sellerOfTheItem, goodsOfTheItem);
				
				orderDao.saveOrderDetail(orderDetail);
				message += "※商品：「" + goodsOfTheItem.getName() + "」已成功加入訂單:" + order.getId() + "\n"
						 + " →原庫存:" + inventories + "\n"
						 + " →購買:" + item.get("quantity") + "\n"
						 + " →剩餘庫存:" + (inventories - item.get("quantity")) + "\n";
				
			} else { // 若此賣家已建立order，則建立orderDetail並連接至現有的order
				
				System.out.println("item: " + item);
				
				Order order = orders.get(sellerOfTheItem);
				order.setTotal(order.getTotal() + subtotal);
				
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setGoods(goodsOfTheItem);
				orderDetail.setOrder(order);
				orderDetail.setQuantity(item.get("quantity"));
				orderDetail.setSubtotal(subtotal);
				
				goodsOfTheItem.setInventories(inventories-item.get("quantity"));
				GoodsDao.update(sellerOfTheItem, goodsOfTheItem);
				
				orderDao.saveOrderDetail(orderDetail);
				message += "※商品：「" + goodsOfTheItem.getName() + "」已成功加入訂單:" + order.getId() + "\n"
						 + " →原庫存:" + inventories + "\n"
						 + " →購買:" + item.get("quantity") + "\n"
						 + " →剩餘庫存:" + (inventories - item.get("quantity")) + "\n";
			}
		}
		return new CrudMsg(message, new Date());
	}
}
