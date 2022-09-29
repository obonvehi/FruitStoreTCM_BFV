package com.tcm.Fruites.application.dto;

import com.tcm.Fruites.domain.Order;
import com.tcm.Fruites.utilities.exceptions.InvalidParamException;

public class OrderDTO {
	
	private String date;
	private String orderID;
	
	public OrderDTO(Order order) throws Exception {
		if(order==null) throw new InvalidParamException();
		this.orderID=order.getId();
		this.date=order.getDate().toString();
	
	}
	public String getDate() throws InvalidParamException {
		if(date==null||date.equals("")) throw new InvalidParamException();
		return date;
	}

	public String getId() throws InvalidParamException {
		if(orderID==null||orderID.equals("")) throw new InvalidParamException();
		return orderID;
	}

}
