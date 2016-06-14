'use strict';

angular.module('myApp.dashboard', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/ui', {
    templateUrl: 'dashboard/dashboard.html',
    controller: 'DashboardCtrl'
  });
}])

.controller('DashboardCtrl', [function() {

}]);