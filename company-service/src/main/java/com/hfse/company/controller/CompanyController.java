package com.hfse.company.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfse.company.client.StockServiceClient;
import com.hfse.company.entity.Company;
import com.hfse.company.response.CompanyStockDTO;
import com.hfse.company.service.CompanyService;
import com.hfse.company.utils.CompanyUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1.0/market/company/")
public class CompanyController {
	
	final org.slf4j.Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private StockServiceClient stockServiceClient;
	

	@PostMapping("register")
	public Company registerCompany(@RequestBody Company company) {
		
		log.info("registerCompany method called with request {}", company);
		Company response = companyService.registerCompany(company);
		log.info("Company registered successfully");
		return response;
	}
	
	@GetMapping("info/{companycode}")
	public CompanyStockDTO fetchCompany(@PathVariable String companycode) {
		log.info("fetchCompany method called with companycode {}", companycode);
		Company company = companyService.fetchCompany(companycode);
		//making call to stock service to get latest stock price  for company using feign client
		CompanyStockDTO companyStockDto = null;
		if(company != null) {
			Double stockPrice = stockServiceClient.latestStockPrice(company.getCompanyCode());
			companyStockDto = CompanyUtils.toCompanyStockDto(company, stockPrice);
		}
		log.info("fetchCompany method response {}", companyStockDto);
		return companyStockDto;
	}
	
	@GetMapping("getAll")
	public List<CompanyStockDTO> getAllCompanyDetails() {
		log.info("getAllCompanyDetails method called");
		List<Company> companyList = companyService.getAllCompanyDetails();
		//making call to stock service to get latest stock price  for all companies using feign client
		Map<String, Double> stockPriceMap = stockServiceClient.lateststocksForAllCompanies();
		List<CompanyStockDTO> companyStockDtoList = CompanyUtils.tocompanyStockDtoList(companyList, stockPriceMap);
		log.info("getAllCompanyDetails method returned with count {}", companyStockDtoList.size());

		return companyStockDtoList;
	}
	
	@DeleteMapping("delete/{companycode}")
	public String deleteCompany(@PathVariable String companycode) {
		log.info("deleteCompany method called with companycode {}", companycode);
		String response = companyService.deleteCompany(companycode);
		log.info("deleteCompany method response {}", response);
		return response;
	}
	
}
