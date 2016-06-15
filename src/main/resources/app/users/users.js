'use strict';

angular.module('myApp.users', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider
  	.when('/ui/users', {
  		templateUrl: 'users/users.html',
  		controller: 'UsersCtrl'
  	})
  	.when('/ui/user/:userId?', {
  		templateUrl: 'users/user.html',
  		controller: 'UserCtrl'
  	});
}])

.factory('User', ['$resource', function($resource) {
	return $resource('/api/users/:id', { id: '@id' }, {
		update: {
			method: "PUT"
		}
	});
}])

.controller('UsersCtrl', ['$scope', 'User', function($scope, User) {
	
	User.query(function(response) {
		$scope.users = response ? response : [];
	});
	
}])

.controller('UserCtrl', ['$scope', '$location', '$parse', '$routeParams', 'User', function($scope, $location, $parse, $routeParams, User) {
	
	if ($routeParams.userId) {
		$scope.user = User.get({
			id: $routeParams.userId
		});
	} else {
		$scope.user = new User({
			role: "USER",
			enabled: true
		});
	}
	
	$scope.cancel = function() {
		$location.path('/ui/users');
	};
	
	$scope.saveUser = function() {
		if ($routeParams.userId) {
			User.update($scope.user, success, failure);
		} else {
			User.save($scope.user, success, failure);
		}

		function success(response) {
			$location.path('/ui/users');
		}

		function failure(response) {
			console.log('failure', response);
			
			angular.forEach(response.data.fields, function(message, field) {
				$scope.userForm.$setValidity(field, false, $scope.userForm);
				var serverMessage = $parse('userForm.' + field + '.$error.message');
				serverMessage.assign($scope, message);
			});
		}
	};
	
	$scope.deleteUser = function(user) {
		
	};
	
}]);