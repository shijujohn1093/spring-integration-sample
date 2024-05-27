package com.shiju.ssi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

	private int orderId;
	private String itemName;
	private double amount;
	private String orderStatus;
	
}
