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
				console.log('Reset In');
				$scope.companyForm.$setUntouched();
				$scope.companyForm.$setPristine();
				$scope.comId = '';
				console.log('Reset Out');
			};

			$scope.save = function(company) {
				console.log('Controller JSON ' + JSON.stringify(company));
				companyService.addCompany(company).then(function(dataResponse) {
					$scope.company = dataResponse.data;
					console.log('Company added.')
					$scope.company = {};
					$scope.companyForm.$setUntouched();
					$scope.companyForm.$setPristine();
				});

			};

			$scope.getCompany = function(comId) {
				companyService.getCompany(comId).then(function(dataResponse) {
					$scope.company = dataResponse.data;
					$scope.comId = '';
				});
			};

		} ]);