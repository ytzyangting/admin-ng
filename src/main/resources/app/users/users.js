'use strict';

angular.module('myApp.users', ['ngRoute', 'ui.bootstrap'])

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
		query: {
			method: "GET",
			isArray: false
		},
		update: {
			method: "PUT"
		}
	});
}])

.factory('UserService', ['$uibModal', 'User', function($uibModal, User) {
	var service = {};
	var users = {};
	
	service.page = 1;
	service.totalItems = 0;
	service.users = [];
	
	service.getUsers = function(success) {
		User.query({page: service.page - 1}, function(response) {
			service.totalItems = response.totalElements;
			service.users = response.content;
			success();
		});
	};
	
	service.getUser = function(userId) {
		return User.get({id: userId});
	};
	
	service.newUser = function() {
		return new User({
			role: "USER",
			enabled: true
		});
	};
	
	service.saveUser = function(user, success, failure) {
		if (user.id) {
			User.update(user, success, failure);
		} else {
			User.save(user, success, failure);
		}
	};
	
	service.deleteUser = function(user, success, failure) {
		var modal = $uibModal.open({
			templateUrl: 'components/modal-confirmation.html',
			controller: 'UserDeleteCtrl',
			resolve: {
				item: function() {
					return user;
				}
			}
		});
		modal.result.then(function(item) {
			User.delete({
				id: item.id
			}, function(res) {
				success();
			});
		});
	};
	
	return service;
}])

.controller('UsersCtrl', ['$scope', 'UserService', function($scope, UserService) {
	$scope.currentPage = UserService.page;
	$scope.maxSize = 5;
	
	initUsers();
	loadUsers();
	
	function initUsers() {
		$scope.totalItems = UserService.totalItems;
		$scope.users = UserService.users;
	}
	
	function loadUsers() {
		UserService.getUsers(initUsers);
	}
	
	$scope.pageChanged = function() {
		UserService.page = $scope.currentPage;
		loadUsers();
	};
	
}])

.controller('UserCtrl', ['$scope', '$location', '$routeParams', 'UserService', function($scope, $location, $routeParams, UserService) {
	if ($routeParams.userId) {
		$scope.user = UserService.getUser($routeParams.userId);
	} else {
		$scope.user = UserService.newUser();
	}
	
	$scope.cancel = function() {
		$location.path('/ui/users');
	};
	
	$scope.saveUser = function() {
		UserService.saveUser($scope.user, success, failure);
		
		function success(response) {
			$location.path('/ui/users');
		}

		function failure(response) {
			angular.forEach(response.data.fields, function(message, field) {
				$scope.userForm[field].$setValidity(message, false);
			});
		}
	};
	
	$scope.deleteUser = function() {
		UserService.deleteUser($scope.user);
	};
	
	$scope.hasError = function(name) {
		return $scope.userForm[name].$invalid;
	};
	
	$scope.errorMessage = function(name) {
		var result = [];
		angular.forEach($scope.userForm[name].$error, function (key, value) {
			result.push(value);
		});
		
		return result.join(", ");
	};
	
}])

.controller('UserDeleteCtrl', ['$scope', '$uibModalInstance', 'item', function($scope, $uibModalInstance, item) {
	$scope.item = item;
	$scope.dialog = {
		title: 'Delete user',
		message: 'Are you sure you want to permanently delete ' + item.name + '?',
		optionYes: 'Yes, delete',
		optionNo: 'Cancel'
	}
	$scope.ok = function() {
		$uibModalInstance.close($scope.item);
	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}]);