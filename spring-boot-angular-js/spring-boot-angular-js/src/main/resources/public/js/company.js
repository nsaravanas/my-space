angular.module('company', [ 'companyService' ]).controller('companyController',
		[ '$scope', 'companyUtil', function($scope, companyUtil) {
			$scope.companies = null;
			companyUtil.getData().then(function(dataResponse) {
				$scope.companies = dataResponse.data;
			});
		} ]);