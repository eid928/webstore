package com.hyjiangd.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.UserDao;
import com.hyjiangd.webstore.entity.User;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}

	@Override
	public void save(User user) {
		
		userDao.save(user);
	}
}
