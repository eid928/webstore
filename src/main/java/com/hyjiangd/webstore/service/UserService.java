package com.hyjiangd.webstore.service;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;

public interface UserService {
	
	public User findByUsername(String username);
	public void save(User user);
	public void updateUserDetail(String username, UserDetail userDetail);
	public void updatePassword(String username, String password);
}
