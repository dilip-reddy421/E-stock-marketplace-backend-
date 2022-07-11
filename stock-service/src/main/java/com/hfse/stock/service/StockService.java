package com.hfse.stock.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.hfse.stock.entity.Stock;
import com.hfse.stock.entity.StockDTO;
import com.hfse.stock.entity.StockPriceAggregation;
import com.hfse.stock.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Stock saveStockforCompany(Stock stock) {
		
		stock.setCreatedDate(LocalDateTime.now());
		return stockRepository.save(stock);
	}
	
	public StockDTO getstock(String companyCode, LocalDateTime startDate, LocalDateTime endDate){
		StockDTO StockDTO = null;
		
		List<Stock> stockList = stockRepository.findByCompanyCodeAndCreatedDateBetween(companyCode, startDate, endDate);
		if(stockList != null && !stockList.isEmpty()) {
			StockDTO = new StockDTO();
			StockPriceAggregation aggregate = aggregateData(companyCode, startDate, endDate);
			StockDTO.setStockList(stockList);
			StockDTO.setStockPriceAggregation(aggregate);
		}
		return StockDTO;
	}
	
	public StockPriceAggregation aggregateData(String companyCode, LocalDateTime startDate, LocalDateTime endDate) {
		
		GroupOperation groupforAggregation = Aggregation.group("companyCode")
				  .max("stockPrice").as("maxStockPrice")
				  .min("stockPrice").as("minStockPrice")
				  .avg("stockPrice").as("avgStockPrice");
		MatchOperation filterStock = Aggregation.match(new Criteria("companyCode").is(companyCode).
				andOperator(Criteria.where("createdDate").gte(startDate), Criteria.where("createdDate").lte(endDate)));

		Aggregation aggregation = Aggregation.newAggregation(
				filterStock, groupforAggregation);
		AggregationResults<StockPriceAggregation> result = mongoTemplate.aggregate(
				  aggregation, "stockinfo", StockPriceAggregation.class);
		
		List<StockPriceAggregation> stockPriceAggregationList = result.getMappedResults();
		System.out.println("aggregate data"+ stockPriceAggregationList );
		if(!stockPriceAggregationList.isEmpty()) {
			return stockPriceAggregationList.get(0);
		}
		else 
			return null;

	}
	
	public Double findLatestStockPriceForCompany(String companyCode) {
		
          Stock stock  = stockRepository.findFirstByCompanyCodeOrderByCreatedDateDesc(companyCode);
          if(stock!= null) {
        	  return stock.getStockPrice();
          }
          else
        	  return null;
		
	}
	
	public Map<String,Double> lateststocksForAllCompanies() 
	{
		Map<String,Double> LatestStockPriceMap = new HashMap<>();

		SortOperation sortByCreatedDateDesc = Aggregation.sort(Sort.by(Direction.DESC, "createdDate"));
		
		GroupOperation groupByCompanyCode = Aggregation.group("companyCode").
				first("stockPrice").as("stockPrice");
		
		ProjectionOperation projectStage = Aggregation.project("stockPrice").
				andExpression("_id").as("companyCode");
		
		Aggregation aggregation = Aggregation.newAggregation(
				sortByCreatedDateDesc, groupByCompanyCode, projectStage);
		AggregationResults<Stock> result = mongoTemplate.aggregate(
				  aggregation, "stockinfo", Stock.class);
		
		List<Stock> stockPriceAggregationList = result.getMappedResults();
		if(!stockPriceAggregationList.isEmpty()) {
			LatestStockPriceMap = stockPriceAggregationList.stream().
			collect(Collectors.toMap(Stock::getCompanyCode, Stock::getStockPrice));
		}
		System.out.println("aggregate data"+ stockPriceAggregationList );
		System.out.println("StockPriceMap data"+ LatestStockPriceMap );

		
		return LatestStockPriceMap;
	}
	
	@RabbitListener(queues = "q.stock-delete")
	public void deleleStock(String companyCode) {
		System.out.println("companyId to delete"+ companyCode.toString() );
		Long stocksDeleted = stockRepository.deleteStockByCompanyCode(companyCode);
		System.out.println("stocks deleted for company"+ stocksDeleted );
		
	}
	
	

}
