package com.hfse.company.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STOCK-SERVICE")
public interface StockServiceClient {
	
	@GetMapping("/api/v1.0/market/stock/latestStock/{companyCode}")
	public Double latestStockPrice(@PathVariable("companyCode") String companyCode);
	
	@GetMapping("/api/v1.0/market/stock/latestStock/all")
	public Map<String,Double> lateststocksForAllCompanies();

}
