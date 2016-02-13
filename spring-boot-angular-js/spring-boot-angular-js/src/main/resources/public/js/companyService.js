app.service('companyService', function($http) {
	delete $http.defaults.headers.common['X-Requested-With'];
	this.getData = function() {
		return $http({
			method : 'GET',
			url : 'http://localhost:8080/companies',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, DELETE',
				'Access-Control-Max-Age': '3600',
				'Access-Control-Allow-Headers': 'Content-Type, Accept, X-Requested-With, remember-me'
			}
		});
	}
});