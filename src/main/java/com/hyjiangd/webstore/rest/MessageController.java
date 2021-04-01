package com.hyjiangd.webstore.rest;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.hyjiangd.webstore.websocketmodel.MessageModel;

@Controller
public class MessageController {
	
	
	@MessageMapping("/chat/{fromWho}/{toWho}")
	@SendTo({"/topic/{toWho}", "/topic/{fromWho}"})
	public MessageModel sendMessage(@DestinationVariable String toWho, @DestinationVariable String fromWho, MessageModel message) throws InterruptedException {
		
		MessageModel messageModel = new MessageModel();
		messageModel.setFromLogin(message.getFromLogin());
		messageModel.setMessage(message.getMessage() + "!!!");
		return messageModel; 
	}
}
