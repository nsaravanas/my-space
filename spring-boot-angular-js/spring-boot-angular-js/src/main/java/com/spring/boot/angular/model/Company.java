package com.spring.boot.angular.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {

	@Id
	@NotNull
	private int companyID;

	@NotNull
	private String name;
	@NotNull
	private Address address;

	private List<String> email;

	private List<String> phoneNumber;

	@NotNull
	private List<String> owners;

	public Company() {
		super();
	}

	public Company(int companyID, String name, Address address, List<String> email, List<String> phoneNumber,
			List<String> owners) {
		super();
		this.companyID = companyID;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.owners = owners;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
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

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public List<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getOwners() {
		return owners;
	}

	public void setOwners(List<String> owners) {
		this.owners = owners;
	}

	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", owners=" + owners + "]";
	}
}
