package com.hyjiangd.webstore.entity;

import java.util.Date;
import java.util.List;

public class Order {
	
	private String id;
	private User sellerUser;
	private User buyerUser;
	private int status;
	private long total;
	private Date creationTime;
	private List<OrderDetail> orderDetails;
	
	public Order() {
		
	}

	public Order(String id, long total) {
		this.id = id;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSellerUser() {
		return sellerUser;
	}

	public void setSellerUser(User sellerUser) {
		this.sellerUser = sellerUser;
	}

	public User getBuyerUser() {
		return buyerUser;
	}

	public void setBuyerUser(User buyerUser) {
		this.buyerUser = buyerUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", sellerUser=" + sellerUser + ", buyerUser=" + buyerUser + ", status=" + status
				+ ", total=" + total + ", creationTime=" + creationTime + ", orderDetails=" + orderDetails + "]";
	}
}
