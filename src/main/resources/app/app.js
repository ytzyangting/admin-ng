'use strict';

angular.module('myApp', [
  'ngRoute',
  'ngResource',
  'myApp.users',
  'myApp.view1',
  'myApp.view2',
  'myApp.version'
])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.otherwise({redirectTo: '/view1'});
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
