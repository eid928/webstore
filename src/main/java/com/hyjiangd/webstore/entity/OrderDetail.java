package com.hyjiangd.webstore.entity;

public class OrderDetail {
	
	private int id;
	private Goods goods;
	private int quantity;
	private long subtotal;
	
	public OrderDetail() {
		
	}

	public OrderDetail(int quantity, long subtotal) {
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(long subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", goods=" + goods + ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
}
