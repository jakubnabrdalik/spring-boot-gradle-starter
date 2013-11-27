	var scotchApp = angular.module('scotchApp', ['ngResource'])

	scotchApp.config(function($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl : 'pages/myEntities.html',
				controller  : 'MyEntitiesController'
			})
	});

    scotchApp.controller('MyEntitiesController', function($scope, $http) {
        $scope.entities = []
        var futureResponse = $http.get('/some/mine');
        futureResponse.success(function (data, status, headers, config) {
            $log.warn("Received data: " + data)
            $scope.entities = data.entities;
        });
        futureResponse.error(function (data, status, headers, config) {
            throw new Error('Something went wrong...; status ' + status + ' data: ' + data);
        });
    });

    scotchApp.controller('NewEntityFormController', function($scope, $http, $log) {
        $scope.submit = function() {
            $scope.errorMessage = ""
            var newEntity = $scope.newEntity
            var params = $.param({amount: newEntity.someAmount});
            $log.warn("Params to be sent: " + params)
            $http.post('/some/add', params, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded, charset=UTF-8'}
            }).success(function (data, status, headers, config) {
                $log.warn("Success. Status: " + status)
                if(data.wasCreated) {
                    $scope.entities.push(data.entity)
                    $scope.newEntity = {}
                } else {
                    $scope.errorMessage = "Sorry, but no!"
                }
            }).error(function (data, status, headers, config) {
                $log.warn("We have an error. Status: " + status)
                $scope.errorMessage = "Sorry, but no!"
            });
        };
    });
