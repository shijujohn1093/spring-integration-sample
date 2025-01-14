package com.shiju.ssi.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import com.shiju.ssi.model.Order;


@MessagingGateway
public interface OrderGateway {
	
	@Gateway(requestChannel = "request-in-channel")
	public Message<Order> placeOrder(Order  order);

}
	