app.controller('companyController', [ '$scope', 'companyService',
		function($scope, companyService) {

			$scope.allCompanies = [];

			$scope.refresh = function() {
				companyService.getAllCompanies().then(function(dataResponse) {
					$scope.allCompanies = dataResponse.data;
				});
			};

			$scope.company = {};

			$scope.reset = function() {
				$scope.companyForm.$setUntouched();
				$scope.companyForm.$setPristine();
				$scope.comId = '';
			};

			$scope.save = function(company) {
				companyService.addCompany(company).then(function(dataResponse) {
					$scope.company = dataResponse.data;
					$scope.company = {};
					$scope.companyForm.$setUntouched();
					$scope.companyForm.$setPristine();
				});

			};

			$scope.getCompany = function(comId) {
				companyService.getCompany(comId).then(function(dataResponse) {
					$scope.company = dataResponse.data;
					$scope.comId = '';
					$scope.companyForm.$touched = true;
					$scope.companyForm.$valid = true;
				});
			};

		} ]);