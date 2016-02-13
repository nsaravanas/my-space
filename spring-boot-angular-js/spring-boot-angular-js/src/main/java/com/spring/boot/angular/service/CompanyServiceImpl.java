package com.spring.boot.angular.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.angular.model.Company;
import com.spring.boot.angular.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public List<Company> getAllCompanies() {
		return this.companyRepository.findAll();
	}

	@Override
	public boolean createCompany(Company company) {
		this.companyRepository.save(company);
		return true;
	}

	@Override
	public boolean updateCompany(Integer companyId, Company company) {
		this.companyRepository.save(company);
		return true;
	}

	@Override
	public Company getCompany(Integer companyId) {
		return this.companyRepository.findOne(companyId);
	}

}
