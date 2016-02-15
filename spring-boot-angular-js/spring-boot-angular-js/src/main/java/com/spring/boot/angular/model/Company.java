package com.spring.boot.angular.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {

	@Id
	@NotNull
	private Integer companyID;

	@NotNull
	private String name;

	@NotNull
	private Address address;

	private String email;

	private Integer phoneNumber;

	@NotNull
	private List<String> owners;

	public Company() {
		super();
	}

	public Company(Integer companyID, String name, Address address, String email, Integer phoneNumber,
			List<String> owners) {
		super();
		this.companyID = companyID;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.owners = owners;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getOwners() {
		return owners;
	}

	public void setOwners(List<String> owners) {
		this.owners = owners;
	}

}
