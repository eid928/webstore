package com.hyjiangd.webstore.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.UserDao;
import com.hyjiangd.webstore.entity.Authority;
import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetail findLoginUserDetail() {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Now login: " + username);
		
		return userDao.findUserDetailByUsername(username);
	}

	@Override
	public void save(User user) {
		
		UserDetail userDetail = user.getUserDetail();
		userDetail.setUser(user);
		
		Authority authority = user.getAuthority();
		authority.setUsername(user.getUsername());
		authority.setAuthority("ROLE_CUSTOMER");
		
		String password = user.getPassword();
		String hashedPassword = "{bcrypt}" + BCrypt.hashpw(password, BCrypt.gensalt(10));
		System.out.println(hashedPassword);
		user.setPassword(hashedPassword);
		user.setEnabled(1);
		
		userDao.save(user);
	}

	@Override
	public void updateLoginUserDetail(UserDetail userDetail) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Now login: " + username);
		
		userDao.updateUserDetail(username, userDetail);
	}

	@Override
	public void updateLoginPassword(String password) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Now login: " + username);
		
		System.out.println("new password: " + password);
		
		String hashedPassword = "{bcrypt}" + BCrypt.hashpw(password, BCrypt.gensalt(10));
		System.out.println("hashedPassword: " + hashedPassword);
		
		userDao.updatePassword(username, hashedPassword);
	}
}
