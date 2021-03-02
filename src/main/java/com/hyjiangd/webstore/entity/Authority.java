package com.hyjiangd.webstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "authorities")
@JsonIgnoreProperties({"username", "user"})
public class Authority {
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "authority")
	private String authority;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;
	
	public Authority() {
		
	}

	public Authority(String authority) {
		this.setAuthority(authority);
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authorities [authority=" + authority + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
