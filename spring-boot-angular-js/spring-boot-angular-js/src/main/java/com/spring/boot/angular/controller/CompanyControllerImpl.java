package com.spring.boot.angular.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.angular.model.Company;
import com.spring.boot.angular.service.CompanyService;

@RestController
public class CompanyControllerImpl implements CompanyController {

	@Autowired
	private CompanyService companyService;

	@Override
	@RequestMapping(value = "/companies", method = RequestMethod.PUT)
	public Company createCompany(@RequestBody @Valid Company company) {
		return this.companyService.createCompany(company);
	}

	@Override
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public List<Company> listAllCompanies() {
		return this.companyService.getAllCompanies();
	}

	@Override
	@RequestMapping(value = "/companies/{companyID}", method = RequestMethod.POST)
	public Company updateCompany(@PathVariable Integer companyID, @Valid @RequestBody Company company) {
		return this.companyService.updateCompany(companyID, company);
	}

	@Override
	@RequestMapping(value = "/companies/{companyID}", method = RequestMethod.GET)
	public Company getCompany(@PathVariable Integer companyID) {
		return this.companyService.getCompany(companyID);
	}

}
