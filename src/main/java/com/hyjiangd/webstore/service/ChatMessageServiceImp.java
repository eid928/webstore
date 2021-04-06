package com.hyjiangd.webstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyjiangd.webstore.dao.ChatMessageDao;
import com.hyjiangd.webstore.entity.ChatMessage;
import com.hyjiangd.webstore.message.CrudMsg;

@Service
public class ChatMessageServiceImp implements ChatMessageService{
	
	@Autowired
	private ChatMessageDao chatMessageDao;
	
	@Override
	@Transactional
	public List<ChatMessage> getChatHistory(String fromUsername, String toUsername) {
		
		return chatMessageDao.getChatHistory(fromUsername, toUsername);
	}

	@Override
	@Transactional
	public CrudMsg save(ChatMessage message) {
		
		return chatMessageDao.save(message);
	}
}
