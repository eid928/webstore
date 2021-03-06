package com.hyjiangd.webstore.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;
import com.hyjiangd.webstore.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{username}")
	public User findByUsername(@PathVariable String username) {
		
		User user = userService.findByUsername(username);
		
		if (user == null) {
			throw new RuntimeException("Username is not found: " + username);
		}
		
		return user;
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User user) {
		
		System.out.println("in saveUser");
		String username = user.getUsername();
		UserDetail userDetail = user.getUserDetail();
		userDetail.setUser(user);
		userService.save(user);
		
		return userService.findByUsername(username);
	}
}
