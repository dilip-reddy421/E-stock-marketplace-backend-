package com.hfse.stock.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;;

@Document(collection = "stockinfo")
public class Stock {
	
	@Id
	private String Id;
	private Double stockPrice;
	private String companyCode;
	private LocalDateTime createdDate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "Stock [Id=" + Id + ", stockPrice=" + stockPrice + ", companyCode=" + companyCode + ", createdDate="
				+ createdDate + "]";
	}
	
	

}
