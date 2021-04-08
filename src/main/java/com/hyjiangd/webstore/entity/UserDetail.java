package com.hyjiangd.webstore.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_details")
@JsonIgnoreProperties({"id", "user"})
public class UserDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;
	
	@Column(name = "name")
	@NotNull(message = "請輸入姓名")
	@Size(min = 1, message = "請輸入姓名")
	private String name;
	
	@Column(name = "email")
	@NotNull(message = "請輸入電子信箱")
	@Size(min = 1, message = "請輸入電子信箱")
	private String email;
	
	@Column(name = "address")
	@NotNull(message = "請輸入地址")
	@Size(min = 1, message = "請輸入地址")
	private String address;
	
	@Column(name = "phone")
	@NotNull(message = "請輸入電話")
	@Size(min = 1, message = "請輸入電話")
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phone="
				+ phone + "]";
	}
}
