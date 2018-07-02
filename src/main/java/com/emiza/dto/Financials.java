package com.emiza.dto;

public class Financials {
	
	private String PRICE;
	private String MRP;
	private String GST;
	private String VAT;
	public String getPrice() {
		return PRICE;
	}
	public void setPrice(String price) {
		this.PRICE = price;
	}
	public String getMrp() {
		return MRP;
	}
	public void setMrp(String mrp) {
		this.MRP = mrp;
	}
	public String getGst() {
		return GST;
	}
	public void setGst(String gst) {
		this.GST = gst;
	}
	public String getVat() {
		return VAT;
	}
	public void setVat(String vat) {
		this.VAT = vat;
	}
}
