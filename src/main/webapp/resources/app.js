// Declare app level module which depends on views, and components
angular.module('air-ticket', [
	'ngRoute',
	'air-ticket.login',
	'air-ticket.list-fligths'
]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.otherwise({redirectTo: '/login'});
}]);
