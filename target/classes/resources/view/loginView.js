'use strict';

var app = angular.module('air-ticket.login',['ngRoute','ajoslin.promise-tracker'])

app.config(['$routeProvider', function($routeProvider){
	$routeProvider.
		when('/login', {
			templateUrl: '/resources/view/loginView.html',
			controller: 'ViewCtrl'
		})
}])
