package com.hfse.company.response;

public class CompanyStockDTO {
	
	private String companyCode;
	private String companyName;
	private String companyCeo;
	private Double companyTurnOver;
	private String companyWebsite;
	private String stockExchange;
	private Double latestStockPrice;
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public Double getCompanyTurnOver() {
		return companyTurnOver;
	}
	public void setCompanyTurnOver(Double companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public Double getLatestStockPrice() {
		return latestStockPrice;
	}
	public void setLatestStockPrice(Double latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}
	

}
