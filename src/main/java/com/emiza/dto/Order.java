package com.emiza.dto;

public class Order {
	private String Id;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getStatusDateTime() {
		return StatusDateTime;
	}
	public void setStatusDateTime(String statusDateTime) {
		StatusDateTime = statusDateTime;
	}
	private String Status;
	private String StatusDateTime;
	private Financials Financials;
	private String WarehouseId;
	public String getWarehouseId() {
		return WarehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		WarehouseId = warehouseId;
	}

	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	public String getStatusdatetime() {
		return StatusDateTime;
	}
	public void setStatusdatetime(String statusdatetime) {
		this.StatusDateTime = statusdatetime;
	}
	public Financials getFinancials() {
		return Financials;
	}
	public void setFinancials(Financials financials) {
		this.Financials = financials;
	}

}
