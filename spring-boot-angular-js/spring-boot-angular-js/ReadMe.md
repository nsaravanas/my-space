###GitHub repository

	https://github.com/nsaravanas/ubuntu-eclipse/tree/master/spring-boot-angular-js/spring-boot-angular-js
	
###Heroku URL
	https://limitless-journey-45430.herokuapp.com/	
	
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
	
/companies returns List<Company>
	
> POST
	
/companies/{companyID} returns Company
	
> GET
	
/companies/{companyID} returns Company

###Technologies Used

	Java 8
	Spring Boot [Thymeleaf, REST, JPA]
	Mongo DB
	Angular JS
	
###Authentication

	JSON Web Token authentication is the best approach which I will implement, rather than storing the user information in cookies it stores the user information in encrypted way, also it can be scalable, this method of authentication is used by Facebook & Twitter.
	
###Service Redundancy
	Have used Mongo DB as it is document based no SQL which can be horizontally scalable, high available and provides excellent support by the offical team .