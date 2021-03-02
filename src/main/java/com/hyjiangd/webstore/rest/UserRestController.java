package com.hyjiangd.webstore.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.dao.UserDao;
import com.hyjiangd.webstore.entity.User;

@RestController
public class UserRestController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users/{username}")
	public User findByUsername(@PathVariable String username) {
		
		
		User user = userDao.findByUsername(username);
		
		return user;
	}
}
