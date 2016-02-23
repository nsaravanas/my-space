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
	
> PUT
	
	/companies/{companyID}
	returns Company
	
> GET
	
	/companies/{companyID}
	returns Company

###cURL

> GET all companies

	curl https://limitless-journey-45430.herokuapp.com/companies/ -X GET

> GET a company

	curl https://limitless-journey-45430.herokuapp.com/companies/{companyID} -X GET

> Save/Update a company

	curl https://limitless-journey-45430.herokuapp.com/companies/ -X PUT -d '{"companyID":7,"name":"TCS","address":{"no":"123",""Chennai","country":"India","zipCode":600026},"email":"some@tcs.in","phoneNumber":1234567890,"owners":["Tata","GRD"]}' -H 'Content-Type:application/json'

###Technologies Used
1.	Java 8
2.	Spring Boot 1.3.0 [Thymeleaf, REST, JPA]
3.	Mongo DB 3.0.9 Database-as-a-service
4.	Angular JS 1.4.5
	
###Authentication
JSON Web Token authentication would be the best approach, as it does not store user details in client's cookies, 
encrypted user information in JSON is stored in Local Storage, decrypted using the private key and also it can be scalable.
This type of authentication is used in Social Networks like FaceBook, Twitter
	
###Service Redundancy
In terms of Service Redundancy, using a Horizontally scalable database with high availability using replications would be the good choice.
Mongo DB provides both high scalability using Mongos (Shared cluster) and high availablity using RS ( Replication Set), also it is a No-SQL DB with excellent support by the official team and has good documentation.    

  [1]: https://github.com/nsaravanas/ubuntu-eclipse/tree/master/spring-boot-angular-js/spring-boot-angular-js
  [2]: https://limitless-journey-45430.herokuapp.com/	