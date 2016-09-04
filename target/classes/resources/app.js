// Declare app level module which depends on views, and components
angular.module('dev-ops', [
	'ngRoute',
	'ngDialog',
	'dev-ops.view',
	'dev-ops.view1',
	'dev-ops.view2',
	'dev-ops.view3',
	'dev-ops.view4'
]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.otherwise({redirectTo: '/view'});
}]);
