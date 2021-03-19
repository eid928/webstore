package com.hyjiangd.webstore.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.entity.UserDetail;
import com.hyjiangd.webstore.exception.AlreadyExistException;
import com.hyjiangd.webstore.exception.NotFoundException;

@Repository
public class UserDaoImp implements UserDao{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public UserDetail findUserDetailByUsername(String username) {
		
		Session session = entityManager.unwrap(Session.class);
		
		User theUser = session.get(User.class, username);
		
		if (theUser == null) {
			throw new NotFoundException("Username is not found: " + username);
		}
		
		return theUser.getUserDetail();
	}

	@Override
	public void save(User user) {
		
		Session session = entityManager.unwrap(Session.class);
		try {
			session.save(user);
		} catch (Exception e) {
			throw new AlreadyExistException("此帳號: " + user.getUsername() + " 已經被註冊。");
		}
	}
	
	@Override
	public void updateUserDetail(String username, UserDetail userDetail) {
		
		Session session = entityManager.unwrap(Session.class);
		User dbUser = session.get(User.class, username);
		if (dbUser == null) {
			throw new NotFoundException("Username is not found: " + username);
		}
		dbUser.getUserDetail().setName(userDetail.getName());
		dbUser.getUserDetail().setAddress(userDetail.getAddress());
		dbUser.getUserDetail().setEmail(userDetail.getEmail());
		dbUser.getUserDetail().setPhone(userDetail.getPhone());
		
		session.saveOrUpdate(dbUser);
	}

	@Override
	public void updatePassword(String username, String password) {
		
		Session session = entityManager.unwrap(Session.class);
		User dbUser = session.get(User.class, username);
		if (dbUser == null) {
			throw new NotFoundException("Username is not found: " + username);
		}
		
		dbUser.setPassword(password);
		session.saveOrUpdate(dbUser);
	}
}
