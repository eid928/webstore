package com.hyjiangd.webstore.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.hyjiangd.webstore.entity.ChatMessage;
import com.hyjiangd.webstore.service.ChatMessageService;

@RestController
public class MessageController {
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@MessageMapping("/chat/{toWho}")
	@SendTo({"/topic/{toWho}"})
	public ChatMessage sendMessage(@DestinationVariable String toWho, ChatMessage chatMessage) throws InterruptedException {
		
		chatMessage.setSendTime(new Date());
		chatMessageService.save(chatMessage);
		
		return chatMessage; 
	}
}
