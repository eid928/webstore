package com.hyjiangd.webstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "goods")
@JsonIgnoreProperties("id")
public class Goods {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "seller_username")
	private User sellerUser;
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "price")
	@NotBlank
	private long price;
	
	@Column(name = "description")
	@NotBlank
	private String description;
	
	@Column(name = "inventories")
	@NotBlank
	private int inventories;
	
	@Column(name = "image")
	@NotBlank
	private String image;
	
	public Goods() {
		
	}

	public Goods(String name, long price, String description, int inventories, String image) {
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

	public User getSellerUser() {
		return sellerUser;
	}

	public void setSellerUser(User sellerUser) {
		this.sellerUser = sellerUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
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

	@Override
	public String toString() {
		return "Goods [id=" + id + ", sellerUser=" + sellerUser + ", name=" + name + ", price=" + price
				+ ", description=" + description + ", inventories=" + inventories + ", image=" + image + "]";
	}
}
