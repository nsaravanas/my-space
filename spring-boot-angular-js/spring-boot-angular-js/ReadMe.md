This documentation provides the brief description about the appilcation, technologies, REST APIs to be used

In this application you can add/update/get and getall companies and details

###GitHub repository
[GitHub Link][1]
	
###Heroku URL
[Heroku Link][2]	
	
###Sample JSON

	{
		"companyID":3,
		"name":"TCS",
		"address":{
			"no":"123",
			"street":"Rajiv",
			"city":"Chennai",
			"country":"India",
			"zipCode":600026
		},
		"email":"some@tcs.in",
		"phoneNumber":1234567890,
		"owners":["Tata","GRD"]
	}

###REST APIs
	
> GET
	
	/companies 
	returns List<Company>
	
> POST
	
	/companies/{companyID}
	returns Company
	
> GET
	
	/companies/{companyID}
	returns Company

###Technologies Used
1. `Java 8`
2. `Spring Boot 1.3.0 [Thymeleaf, REST, JPA]`
3. `Mongo DB 3.0.9 Database-as-a-service`
4. `Angular JS 1.4.5`
	
###Authentication
	JSON Web Token authentication is the best approach which I will implement, rather than storing the user information in cookies it stores the user information in encrypted way, also it can be scalable, this method of authentication is used by Facebook & Twitter.
	
###Service Redundancy
	Have used Mongo DB as it is document based no SQL which can be horizontally scalable, high available and provides excellent support by the offical team .

  [1]: https://github.com/nsaravanas/ubuntu-eclipse/tree/master/spring-boot-angular-js/spring-boot-angular-js
  [2]: https://limitless-journey-45430.herokuapp.com/	