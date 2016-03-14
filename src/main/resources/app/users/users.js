'use strict';

angular.module('myApp.users', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider
  	.when('/users', {
  		templateUrl: 'users/users.html',
  		controller: 'UsersCtrl'
  	})
  	.when('/user/:userId?', {
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
	
	if ($routeParams.userId == null) {
		$scope.user = new User({
			name: null,
			username: null,
			role: "USER",
			enabled: true
		});
	} else {
		$scope.user = User.get({
			id: $routeParams.userId
		});
	}
	
	$scope.cancel = function() {
		$location.path('users');
	};
	
	$scope.createUser = function(user) {
		$scope.userForm.$setValidity('username', false, $scope.userForm);
		var serverMessage = $parse('userForm.username.$error.message');
		serverMessage.assign($scope, 'This is a error message.');
		//user.$save(function(res) {
		//	$location.path('users');
		//});
	};
	
	$scope.updateUser = function(user) {
		
	};
	
	$scope.deleteUser = function(user) {
		
	};
	
}]);