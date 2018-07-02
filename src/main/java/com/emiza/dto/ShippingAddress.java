package com.emiza.dto;

public class ShippingAddress {
	private String Name ;
	private String Address;
	private String AddressStreet ;
	private String City;
	private String Zip;
	private String State;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getAddressStreet() {
		return AddressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		AddressStreet = addressStreet;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}

}
