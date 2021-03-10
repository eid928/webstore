package com.hyjiangd.webstore.dao;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;

public interface UserDao {
	
	public UserDetail findUserDetailByUsername(String username);
	public void save(User user);
	public void updateUserDetail(String username, UserDetail userDetail);
	public void updatePassword(String username, String password);
}
