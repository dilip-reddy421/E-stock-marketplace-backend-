package com.hfse.company.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfse.company.entity.Company;
import com.hfse.company.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public Company registerCompany(Company company) {
		 return companyRepository.save(company);
	}
	
	public Company fetchCompany(String companyCode) {
		return companyRepository.findByCompanyCode(companyCode);
	}
	
	public List<Company> getAllCompanyDetails() {
		return companyRepository.findAll();
	}
	
	public String deleteCompany(String companyCode) {
		Company company= companyRepository.findByCompanyCode(companyCode);
		if(company != null) {
			companyRepository.deleteById(company.getCompanyId());
			rabbitTemplate.convertAndSend("q.stock-delete", companyCode);;
			return "sucess";
		}
		else
		 return "failed";
	}

}
