package com.hyjiangd.webstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyjiangd.webstore.entity.Order;
import com.hyjiangd.webstore.entity.OrderDetail;

@Repository
public class OrderDaoImp implements OrderDao{
	
	@Autowired
	private EntityManager entityManger;
	
	@Override
	public List<Order> findByUserAsBuyer(String username) {
		
		Session session = entityManger.unwrap(Session.class);
		Query<Order> query = session.createQuery("from Order where buyerUser.username = :username order by creationTime desc", Order.class);
		query.setParameter("username", username);
		List<Order> orders = query.getResultList();
		
		return orders;
	}

	@Override
	public List<Order> findByUserAsSeller(String username) {
		
		Session session = entityManger.unwrap(Session.class);
		Query<Order> query = session.createQuery("from Order where sellerUser.username = :username order by creationTime desc", Order.class);
		query.setParameter("username", username);
		List<Order> orders = query.getResultList();
		
		return orders;
	}

	@Override
	public Order findByOrderId(int orderId) {
		
		Session session = entityManger.unwrap(Session.class);
		
		return session.get(Order.class, orderId);
	}

	@Override
	public List<OrderDetail> findOrderDetailsByOrderId(int orderId) {
		
		Session session = entityManger.unwrap(Session.class);
		Query<OrderDetail> query = session.createQuery("from OrderDetail where order.id = :orderId", OrderDetail.class);
		query.setParameter("orderId", orderId);
		List<OrderDetail> orderDetails = query.getResultList();
		
		return orderDetails;
	}

	@Override
	public void saveOrder(Order order) {
		
		Session session = entityManger.unwrap(Session.class);
		session.save(order);
	}

	@Override
	public void saveOrderDetail(OrderDetail orderDetail) {
		
		Session session = entityManger.unwrap(Session.class);
		session.save(orderDetail);
	}
}
