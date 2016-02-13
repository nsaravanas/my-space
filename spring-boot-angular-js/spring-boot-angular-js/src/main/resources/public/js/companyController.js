app.controller('companyController', [ '$scope', 'companyService',
		function($scope, companyService) {
			$scope.companies = null;
			companyService.getData().then(function(dataResponse) {
				$scope.companies = dataResponse.data;
			});
		} ]);