package com.hyjiangd.webstore.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private String id;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
			  fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_username")
	@JsonIgnoreProperties({"password", "enabled", "authority", "userDetail"})
	private User sellerUser;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
			  fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer_username")
	@JsonIgnoreProperties({"password", "enabled", "authority", "userDetail"})
	private User buyerUser;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "creation_time")
	private Date creationTime;
	
	public Order() {
		
	}

	public Order(String id, int total) {
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", sellerUser=" + sellerUser + ", buyerUser=" + buyerUser + ", status=" + status
				+ ", total=" + total + ", creationTime=" + creationTime + "]";
	}
}
