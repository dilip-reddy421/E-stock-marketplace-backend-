package com.hfse.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hfse.company.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{
	
	public Company findByCompanyCode(String companyCode);

}
