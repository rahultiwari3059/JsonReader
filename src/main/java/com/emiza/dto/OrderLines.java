package com.emiza.dto;



public class OrderLines {
	private String SKUID;
	private String Quantity;
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	private Offer Offer;
	public Offer getOffer() {
		return Offer;
	}
	public void setOffer(Offer offer) {
		this.Offer = offer;
	}
	public String getSkuid() {
		return SKUID;
	}
	public void setSkuid(String skuid) {
		this.SKUID = skuid;
	}
	

}
