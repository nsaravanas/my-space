package com.spring.boot.angular.model;

import javax.validation.constraints.NotNull;

public class Address {

	private String no;

	@NotNull
	private String street;

	@NotNull
	private String city;

	@NotNull
	private String country;

	@NotNull
	private int zipCode;

	public Address() {
		super();
	}

	public Address(String no, String street, String city, String country, int zipCode) {
		super();
		this.no = no;
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

}
