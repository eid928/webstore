package com.hyjiangd.webstore.rest;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;
import com.hyjiangd.webstore.message.CrudMsg;
import com.hyjiangd.webstore.service.UserService;

@RestController
@Validated
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/userdetail")
	public UserDetail findLoginUserDetail() {
		
		UserDetail userDetail = userService.findLoginUserDetail();
		
		return userDetail;
	}
	
	@PostMapping("/register")
	public CrudMsg saveUser(@Valid @RequestBody User user) {
		
		System.out.println("in saveUser");
		userService.save(user);
		String msg = "歡迎， " + user.getUsername() + " !\n您已成功註冊會員。";
		return new CrudMsg(msg, new Date());
	}
	
	@PutMapping("/userdetail")
	public UserDetail updateLoginUserDetail(@Valid @RequestBody UserDetail userDetail) {
		
		System.out.println("in updateUserDetail");
		userService.updateLoginUserDetail(userDetail);
		
		return userService.findLoginUserDetail();
	}
	
	@PutMapping("/userdetail/updatepassword")
	public CrudMsg updateLoginPassword(@RequestBody 
							   		   @NotNull(message = "Not allowed to be blank")
							   		   @Size(min = 6, message = "密碼不得小於6個字元") String password) {
		
		System.out.println("in updatePassword");
		System.out.println("password: " + password);
		userService.updateLoginPassword(password);
		
		String msg = "您的密碼已成功變更";
		
		return new CrudMsg(msg, new Date());
	}
}
