package com.hyjiangd.webstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"goodsList"})
public class User implements Serializable {
	
	@Id
	@Column(name = "username")
	@NotNull(message = "請輸入帳號")
	@Size(min = 4, message = "帳號不得小於四個字元")
	private String username;
	
	@NotNull(message = "is required")
	@Column(name = "password")
	@NotNull(message = "請輸入密碼")
	@Size(min = 6, message = "密碼不得小於六個字元")
	private String password;
	
	@Column(name = "enabled")
	@NotNull(message = "enabled could not be null")
	private int enabled;
	
	@OneToOne(cascade = CascadeType.ALL, 
			  mappedBy = "user")
	@NotNull(message = "authority could not be null")
	private Authority authority;
	
	@OneToOne(cascade = CascadeType.ALL,
			  mappedBy = "user", 
			  fetch = FetchType.LAZY)
	@NotNull(message = "userdetail could not be null")
	@Valid
	private UserDetail userDetail;
	
	@OneToMany(cascade = CascadeType.ALL, 
			   fetch = FetchType.LAZY, 
			   mappedBy = "user")
	private List<Goods> goodsList;
	
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}
