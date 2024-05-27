package com.shiju.ssi.service;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.shiju.ssi.model.Order;

@Service
public class OrderService {
	
	

	@ServiceActivator(inputChannel =  "request-in-channel", outputChannel = "order-process-channel")
	public Message<Order> placeOrder(Message<Order> order){
		return order;
	}
	
	@ServiceActivator(inputChannel =  "order-process-channel", outputChannel = "order-reply-channel",
			poller =@Poller(fixedDelay = "100", maxMessagesPerPoll = "1"))
	public Message<Order> processOrder(Message<Order> order){
		 order.getPayload().setOrderStatus("Order successfully placed !!!");
		 return order;
	}
	
	@ServiceActivator(inputChannel = "order-reply-channel",
			poller =@Poller(fixedDelay = "100", maxMessagesPerPoll = "1"))
	public void replyOrder(Message<Order> order) {
		MessageChannel replyChannel = (MessageChannel)order.getHeaders().getReplyChannel();
		replyChannel.send(order);
	}
	
	
}
