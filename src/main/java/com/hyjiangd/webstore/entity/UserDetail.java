package com.hyjiangd.webstore.entity;

public class UserDetail {
	
	private int id;
	private String name;
	private String email;
	private String address;
	private String phone;
	
	public UserDetail() {
		
	}

	public UserDetail(String name, String email, String address, String phone) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phone="
				+ phone + "]";
	}
}
