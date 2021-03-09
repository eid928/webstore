package com.hyjiangd.webstore.rest;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;
import com.hyjiangd.webstore.service.UserService;

@RestController
@Validated
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{username}")
	public User findByUsername(@PathVariable String username) {
		
		User user = userService.findByUsername(username);
		
		return user;
	}
	
	@PostMapping("/users")
	public User saveUser(@Valid @RequestBody User user) {
		
		System.out.println("in saveUser");
		String username = user.getUsername();
		userService.save(user);
		
		
		return userService.findByUsername(username);
	}
	
	@PutMapping("/userdetails/{username}")
	public User updateUserDetail(@PathVariable String username, @Valid @RequestBody UserDetail userDetail) {
		
		System.out.println("in updateUserDetail");
		userService.updateUserDetail(username, userDetail);
		
		return userService.findByUsername(username);
	}
	
	@PutMapping("/users/{username}")
	public User updatePassword(@PathVariable String username, 
							   @RequestBody 
							   @NotNull(message = "請輸入密碼")
							   @Size(min = 6, message = "密碼不得小於六個字元") String password) {
		
		System.out.println("in updatePassword");
		System.out.println("password: " + password);
		userService.updatePassword(username, password);
		
		return userService.findByUsername(username);
	}
}
