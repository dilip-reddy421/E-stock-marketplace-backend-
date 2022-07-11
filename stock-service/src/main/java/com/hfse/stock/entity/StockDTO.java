package com.hfse.stock.entity;

import java.util.List;

public class StockDTO {
	
	private List<Stock> stockList;
	
	private StockPriceAggregation stockPriceAggregation;

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public StockPriceAggregation getStockPriceAggregation() {
		return stockPriceAggregation;
	}

	public void setStockPriceAggregation(StockPriceAggregation stockPriceAggregation) {
		this.stockPriceAggregation = stockPriceAggregation;
	}
	
	
	

}
