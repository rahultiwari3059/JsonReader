package com.emiza.dto;

public class AddressInfo {
private BillingAddress BillingAddress;
private ShippingAddress ShippingAddress;
public BillingAddress getBillingaddress() {
	return BillingAddress;
}
public void setBillingaddress(BillingAddress billingaddress) {
	this.BillingAddress = billingaddress;
}
public ShippingAddress getShippingadress() {
	return ShippingAddress;
}
public void setShippingadress(ShippingAddress shippingadress) {
	this.ShippingAddress = shippingadress;
}
}
