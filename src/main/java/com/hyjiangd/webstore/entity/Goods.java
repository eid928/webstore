package com.hyjiangd.webstore.entity;

public class Goods {
	
	private int id;
	private User sellerUser;
	private String name;
	private long price;
	private String description;
	private int inventories;
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
