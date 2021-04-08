package com.hyjiangd.webstore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "goods")
public class Goods implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "seller_username")
	@JsonIgnoreProperties({"password", "enabled", "authority", "userDetail"})
	private User user;
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "price")
	@NotNull
	@Min(value = 10, message = "單價不得小於10元")
	@Max(value = 999999, message = "單價不得大於999999元")
	private int price;
	
	@Column(name = "description")
	@NotBlank
	private String description;
	
	@Column(name = "inventories")
	@NotNull
	@Min(value = 0, message = "庫存不得小於0")
	@Max(value = 999999, message = "庫存不得大於999999")
	private int inventories;
	
	@Column(name = "image")
	@NotBlank
	private String image;
	
	@Column(name = "last_update_time")
	private Date lastUpdateTime;
	
	public Goods() {
		
	}

	public Goods(String name, int price, String description, int inventories, String image) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.inventories = inventories;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInventories() {
		return inventories;
	}

	public void setInventories(int inventories) {
		this.inventories = inventories;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", inventories=" + inventories + ", image=" + image + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
