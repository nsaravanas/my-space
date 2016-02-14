app.service('companyService', function($http) {
	delete $http.defaults.headers.common['X-Requested-With'];	
	
	this.getAllCompanies = function() {
		return $http({
			method : 'GET',
			url : '/companies'
		});
	};
	
	this.addCompany = function(company) {
		console.log('Service JSON '+company);
		return $http({
			method : 'PUT',
			url : '/companies',
			headers: {'Content-Type': 'application/json'},
			data : company
		});
	};
	
	this.updateCompany = function(companyID) {
		return $http({
			method : 'POST',
			url : '/companies/'+companyID
		});
	};
	
	this.getCompany = function(companyID) {		
		return $http({
			method : 'GET',
			url : '/companies/'+companyID
		});
	};	
});

/*
 * headers: { 'Access-Control-Allow-Origin': '*',
 * 'Access-Control-Allow-Credentials': 'true', 'Access-Control-Allow-Methods':
 * 'POST, GET, OPTIONS, DELETE', 'Access-Control-Max-Age': '3600',
 * 'Access-Control-Allow-Headers': 'Content-Type, Accept, X-Requested-With,
 * remember-me' }
 */