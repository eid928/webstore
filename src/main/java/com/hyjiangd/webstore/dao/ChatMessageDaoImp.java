package com.hyjiangd.webstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyjiangd.webstore.entity.ChatMessage;
import com.hyjiangd.webstore.entity.User;
import com.hyjiangd.webstore.message.CrudMsg;

@Repository
public class ChatMessageDaoImp implements ChatMessageDao{
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ChatMessage> getChatHistory(String fromUsername, String toUsername) {
		
		Session session = entityManager.unwrap(Session.class);
		Query<ChatMessage> query = session.createQuery("from ChatMessage where "
				+ "(fromUser.username = :fromUsername AND toUser.username = :toUsername)"
				+ "OR "
				+ "(fromUser.username = :toUsername AND toUser.username = :fromUsername)"
				+ "order by sendTime asc", ChatMessage.class);
		
		query.setParameter("fromUsername", fromUsername);
		query.setParameter("toUsername", toUsername);
		List<ChatMessage> list = query.getResultList();
		
		return list;
	}

	@Override
	public CrudMsg save(ChatMessage message) {
		
		Session session = entityManager.unwrap(Session.class);
		User fromUser = session.get(User.class, message.getFromUser().getUsername());
		User toUser = session.get(User.class, message.getToUser().getUsername());
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		session.save(message);
		
		return new CrudMsg("messageSave", new Date());
	}
}
