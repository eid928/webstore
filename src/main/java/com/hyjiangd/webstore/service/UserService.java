package com.hyjiangd.webstore.service;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;

public interface UserService {
	
	public UserDetail findLoginUserDetail();
	public void save(User user);
	public void updateLoginUserDetail(UserDetail userDetail);
	public void updateLoginPassword(String password);
}
