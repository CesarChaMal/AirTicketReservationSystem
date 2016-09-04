'use strict';

var app = angular.module('air-ticket.list-fligths',['ngRoute','ajoslin.promise-tracker'])

app.config(['$routeProvider', function($routeProvider){
	$routeProvider.
		when('/list-fligths', {
			templateUrl: '/resources/view/listFlightsView.html',
			controller: 'ViewCtrl'
		})
}])
