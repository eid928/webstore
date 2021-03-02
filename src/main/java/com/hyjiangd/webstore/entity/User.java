package com.hyjiangd.webstore.entity;

import java.util.List;

public class User {
	
	private String username;
	private String password;
	private int enabled;
	
	private UserDetail userDetail;
	
	private List<Goods> goodsList;
	private List<Order> sellerOrder;
	private List<Order> buyerOrder;
	
	public User() {
		
	}

	public User(String username, String password, int enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Order> getSellerOrder() {
		return sellerOrder;
	}

	public void setSellerOrder(List<Order> sellerOrder) {
		this.sellerOrder = sellerOrder;
	}

	public List<Order> getBuyerOrder() {
		return buyerOrder;
	}

	public void setBuyerOrder(List<Order> buyerOrder) {
		this.buyerOrder = buyerOrder;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", userDetail="
				+ userDetail + ", goodsList=" + goodsList + ", sellerOrder=" + sellerOrder + ", buyerOrder="
				+ buyerOrder + "]";
	}
}
