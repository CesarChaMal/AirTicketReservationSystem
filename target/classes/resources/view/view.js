'use strict';

var app = angular.module('dev-ops.view',['ngRoute','ajoslin.promise-tracker'])
//var app = angular.module('dev-ops.view',['ngRoute','ngDialog', 'ngResource'])

/*
app.config(['$routeProvider', function($routeProvider){
	$routeProvider.
		when('/view', {
			templateUrl: 'view/view.html',
			controller: 'ViewJqueryCtrl'
		})
}])
*/

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
	$routeProvider.
		when('/view', {
			templateUrl: 'view/view.html',
			controller: 'ViewCtrl'
		})
}])
