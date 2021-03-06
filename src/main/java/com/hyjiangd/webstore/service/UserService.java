package com.hyjiangd.webstore.service;

import com.hyjiangd.webstore.entity.User;

public interface UserService {
	
	public User findByUsername(String username);
	public void save(User user);
}
