package com.hyjiangd.webstore.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hyjiangd.webstore.entity.User;

@Repository
public class UserDaoImp implements UserDao{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public User findByUsername(String username) {
		
		Session session = entityManager.unwrap(Session.class);
		
		User theUser = session.get(User.class, username);
		System.out.println(theUser);
		System.out.println(theUser.getAuthority());
		System.out.println(theUser.getUserDetail());
		
		return theUser;
	}
}
