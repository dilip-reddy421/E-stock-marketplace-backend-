package com.hfse.company.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hfse.company.entity.Company;
import com.hfse.company.response.CompanyStockDTO;;

public class CompanyUtils {
	
	public static CompanyStockDTO toCompanyStockDto(Company company, Double stockPrice) {
		CompanyStockDTO CompanyStockDTO = new CompanyStockDTO();
		CompanyStockDTO.setCompanyCode(company.getCompanyCode());
		CompanyStockDTO.setCompanyName(company.getCompanyName());
		CompanyStockDTO.setCompanyCeo(company.getCompanyCeo());
		CompanyStockDTO.setCompanyWebsite(company.getCompanyWebsite());
		CompanyStockDTO.setStockExchange(company.getStockExchange());
		CompanyStockDTO.setCompanyTurnOver(company.getCompanyTurnOver());
		CompanyStockDTO.setLatestStockPrice(stockPrice);
		
		return CompanyStockDTO;
	}
	
	public static List<CompanyStockDTO> tocompanyStockDtoList(List<Company> companyList, 
			Map<String, Double> stockPriceMap) {
		
		List<CompanyStockDTO> companyStockDtoList = new ArrayList<>();
		for(Company company : companyList) {
			Double stockPrice = stockPriceMap.get(company.getCompanyCode());
			CompanyStockDTO companyStockDTO = toCompanyStockDto(company, stockPrice);
			companyStockDtoList.add(companyStockDTO);
		}
		
		return companyStockDtoList;
		
	}

}
