package com.hfse.stock.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hfse.stock.entity.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
	
	public List<Stock> findByCompanyCode(String companyCode);
	
	public List<Stock> findByCompanyCodeAndCreatedDateBetween(String companyCode, 
			LocalDateTime startDate, LocalDateTime endDate);
	
	public Stock findFirstByCompanyCodeOrderByCreatedDateDesc(String companycode);
	
	public List<Stock> deleteByCompanyCode(String companyCode);
	
	public Long deleteStockByCompanyCode(String companyCode);

}
