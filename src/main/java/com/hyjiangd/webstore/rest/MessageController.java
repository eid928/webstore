package com.hyjiangd.webstore.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.hyjiangd.webstore.websocketmodel.MessageModel;

@Controller
public class MessageController {
	
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public MessageModel sendMessage(MessageModel message) throws InterruptedException {
		
		
		
		MessageModel messageModel = new MessageModel();
		messageModel.setFromLogin("System");
		messageModel.setMessage("!!!");
		return messageModel; 
	}
}
