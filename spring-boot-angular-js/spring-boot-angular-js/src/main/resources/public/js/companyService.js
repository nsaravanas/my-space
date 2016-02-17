app.service('companyService', function($http) {
	delete $http.defaults.headers.common['X-Requested-With'];

	this.getAllCompanies = function() {
		return $http({
			method : 'GET',
			url : '/companies'
		});
	};

	this.addCompany = function(company) {
		return $http({
			method : 'PUT',
			url : '/companies',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : company
		});
	};

	this.updateCompany = function(companyID) {
		return $http({
			method : 'POST',
			url : '/companies/' + companyID
		});
	};

	this.getCompany = function(companyID) {
		return $http({
			method : 'GET',
			url : '/companies/' + companyID
		});
	};
});