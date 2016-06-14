'use strict';

angular.module('myApp', [
  'ngRoute',
  'ngResource',
  'myApp.dashboard',
  'myApp.users',
  'myApp.view1',
  'myApp.view2',
])

.config(['$locationProvider', function($locationProvider) {
	$locationProvider.html5Mode(true);
}])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.otherwise({redirectTo: '/ui'});
}])

.controller('MenuCtrl', ['$scope', function($scope) {
	$scope.item = 1;
	
	$scope.selectItem = function(i) {
		$scope.item = i;
	};

	$scope.isSelected = function(i) {
		return $scope.item === i;
	};
	
}]);
