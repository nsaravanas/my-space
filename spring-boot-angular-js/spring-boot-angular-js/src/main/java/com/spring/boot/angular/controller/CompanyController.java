package com.spring.boot.angular.controller;

import java.util.List;

import com.spring.boot.angular.model.Company;

public interface CompanyController {

	List<Company> listAllCompanies();

	boolean createCompany(Company company);

	boolean updateCompany(Integer companyId, Company company);

	Company getCompany(Integer companyId);

}
