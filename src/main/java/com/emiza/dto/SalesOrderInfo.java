package com.emiza.dto;

import java.util.List;

public class SalesOrderInfo {
	
	private Order Order;
	public Order getOrder() {
		return Order;
	}
	public void setOrder(Order order) {
		this.Order = order;
	}
	private List<OrderLines> OrderLines;
	private Buyer Buyer;
	private Seller Seller;
	
	public List<OrderLines> getOrderlines() {
		return OrderLines;
	}
	public void setOrderlines(List<OrderLines> orderlines) {
		this.OrderLines = orderlines;
	}
	public Buyer getBuyer() {
		return Buyer;
	}
	public void setBuyer(Buyer buyer) {
		Buyer = buyer;
	}
	public Seller getSeller() {
		return Seller;
	}
	public void setSeller(Seller seller) {
		Seller = seller;
	}

	
	

}
