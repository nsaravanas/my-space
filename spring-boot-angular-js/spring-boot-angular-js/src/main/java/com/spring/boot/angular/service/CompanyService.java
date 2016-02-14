package com.spring.boot.angular.service;

import java.util.List;

import com.spring.boot.angular.model.Company;

public interface CompanyService {

	List<Company> getAllCompanies();

	Company createCompany(Company company);

	Company updateCompany(Integer companyId, Company company);

	Company getCompany(Integer companyId);

}
