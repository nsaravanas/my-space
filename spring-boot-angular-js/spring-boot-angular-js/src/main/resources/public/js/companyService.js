angular.module('companyService', []).service('companyUtil', function($http) {
	delete $http.defaults.headers.common['X-Requested-With'];
	this.getData = function() {
		return $http({
			method : 'GET',
			url : 'http://localhost:8080/companies'
		});
	}
});