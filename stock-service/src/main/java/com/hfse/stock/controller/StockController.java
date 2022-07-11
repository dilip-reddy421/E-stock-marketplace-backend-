package com.hfse.stock.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfse.stock.entity.Stock;
import com.hfse.stock.entity.StockDTO;
import com.hfse.stock.service.StockService;

@RestController
@RequestMapping("/api/v1.0/market/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@PostMapping("add/{companycode}")
	public Stock saveStockforCompany(@RequestBody Stock stock) {
		
		return stockService.saveStockforCompany(stock);
	}
	
	@GetMapping("get/{companyCode}/{startDate}/{endDate}")
	public StockDTO getStockInfo(@PathVariable String companyCode,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, 
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		
		return stockService.getstock(companyCode,startDate,endDate);
	}
	
	@GetMapping("latestStock/{companyCode}")
	public Double latestStockPrice(@PathVariable String companyCode) {
		
		return stockService.findLatestStockPriceForCompany(companyCode);
	}
	
	@GetMapping("latestStock/all")
	public Map<String,Double> lateststocksForAllCompanies() {
		
		return stockService.lateststocksForAllCompanies();
	}

}
