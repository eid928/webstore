package com.hyjiangd.webstore.dao;

import java.util.List;

import com.hyjiangd.webstore.entity.ChatMessage;
import com.hyjiangd.webstore.message.CrudMsg;


public interface ChatMessageDao {
	
	public List<ChatMessage> getChatHistory(String fromUsername, String toUsername);
	public CrudMsg save(ChatMessage message);
}
