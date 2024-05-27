package com.shiju.ssi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shiju.ssi.gateway.OrderGateway;
import com.shiju.ssi.model.Order;

@RestController
public class OrderController {
	
	@Autowired
	private final OrderGateway orderGateway;

	
	
	
	public OrderController(OrderGateway orderGateway) {
		super();
		this.orderGateway = orderGateway;
	}




	@PostMapping("/placeOrder")
	public Order placeOrder(@RequestBody Order order) {
		Message<Order> replyMesssage = orderGateway.placeOrder(order);
		return replyMesssage.getPayload();
	}
}
