package com.hyjiangd.webstore.dao;

import com.hyjiangd.webstore.entity.User;

public interface UserDao {
	
	public User findByUsername(String username);
}
