package com.spring.boot.angular.service;

import java.util.List;

import com.spring.boot.angular.model.Company;

public interface CompanyService {

	List<Company> getAllCompanies();

	boolean createCompany(Company company);

	boolean updateCompany(Integer companyId, Company company);

	Company getCompany(Integer companyId);

}
