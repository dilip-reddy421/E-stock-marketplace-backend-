package com.hfse.stock.entity;

public class StockPriceAggregation {
	
	private Double minStockPrice;
	private Double maxStockPrice;
	private Double avgStockPrice;
	public Double getMinStockPrice() {
		return minStockPrice;
	}
	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}
	public Double getMaxStockPrice() {
		return maxStockPrice;
	}
	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}
	public Double getAvgStockPrice() {
		return avgStockPrice;
	}
	public void setAvgStockPrice(Double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
	@Override
	public String toString() {
		return "StockPriceAggregation [minStockPrice=" + minStockPrice + ", maxStockPrice=" + maxStockPrice
				+ ", avgStockPrice=" + avgStockPrice + "]";
	}
	
	
	

}
