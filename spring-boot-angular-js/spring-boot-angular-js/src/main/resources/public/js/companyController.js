app.controller('companyController', [ '$scope', 'companyService',
		function($scope, companyService) {
			
			$scope.allCompanies = null;
			
			companyService.getAllCompanies().then(function(dataResponse) {
				$scope.allCompanies = dataResponse.data;
			});				
			
			$scope.newCompany = null;
			
			$scope.save = function(newCompany){				
				console.log('Controller JSON '+JSON.stringify(newCompany));
				companyService.addCompany(newCompany).then(function(dataResponse) {
					$scope.newCompany = dataResponse.data;
				});
			};
			
			$scope.updatedCompany = null;
			
			companyService.updateCompany($scope.updatedCompany).then(function(dataResponse) {
				$scope.updatedCompany = dataResponse.data;
			});	
			
			$scope.company = null;
			
			$scope.getCompany = function (companyID){
				companyService.getCompany(companyID).then(function(dataResponse) {
					$scope.company = dataResponse.data;					
				});
			};	
			
		} ]);