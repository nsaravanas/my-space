package com.spring.boot.angular.model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {

	@Id
	private int companyID;

	private String name;
	private Address address;
	private String[] email;
	private String[] phoneNumber;
	private String[] owners;

	public Company() {
		super();
	}

	public Company(int companyID, String name, Address address, String[] email, String[] phoneNumber, String[] owners) {
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

	public String[] getEmail() {
		return email;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public String[] getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String[] phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String[] getOwners() {
		return owners;
	}

	public void setOwners(String[] owners) {
		this.owners = owners;
	}

	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", name=" + name + ", address=" + address + ", email="
				+ Arrays.toString(email) + ", phoneNumber=" + Arrays.toString(phoneNumber) + ", owners="
				+ Arrays.toString(owners) + "]";
	}

}
