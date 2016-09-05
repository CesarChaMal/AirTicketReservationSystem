app.controller('ViewCtrl', ['$scope', '$rootScope', '$http', '$routeParams', '$location', '$timeout', '$interval', '$httpParamSerializerJQLike', 'promiseTracker', function($scope, $rootScope, $http, $routeParams, $location, $timeout, $interval, $httpParamSerializerJQLike, promiseTracker){
   	
//	console.log('Angular Event Delegation test')

	$scope.checkEventDelegation = function() {
		console.log('Angular Event Delegation test')
		alert('Angular Event Delegation test')
	}

	$scope.submit = function() {
		console.log('submit ViewCtrl')
  		var username = $("[name='name']");
 	    username = jQuery(username);
 	    username = username.val();
		console.log(username)

 	    var password = $("[name='password']");
		password = jQuery(password);
		password = password.val();
		console.log(password)

		var data = {
				'username' : username,
				'password' : password
		};

		var config = {
				method: 'POST',
			  	url: domain + 'airticket/auth',
//			  	data: $httpParamSerializerJQLike(data),
			  	data: data,
//			  	headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				headers: {'Content-Type': 'application/json'}
		}

		var promise = $http(config).success(function(data, status, headers, config) {
//			console.log(data)
//			console.log(status)
//			console.log(headers)
//			console.log(config)

			if (status == 200) {

				if (data){
					console.log(data)
					console.log(data.ok)
					if (data.ok == "true"){
						console.log("login succesful")

						var flights = [
			              	{'name' : 'test1',
			              	'destination' : 'nowhere'},
			              	{'name' : 'test2',
			              	'destination' : 'nowhere'}
						];
			
						$rootScope.flights = flights;
						$rootScope.app = 'Air Ticket Reservation System';
						$rootScope.LOGGEDIN_USER = name;
						$location.url('/list-fligths');
					} else if (data.ok == "false"){
						console.log("wrong login")
						$scope.errorMessage = 'Invalid Credentials!!';
						$location.url('/login');
					}
				}
				$scope.messages = 'Your request has been sent!';
			} else {
				$scope.messages = 'Oops, we received your request, but there was an error processing it.';
			}
			console.log($scope.messages);
		})
		.error(function(data, status, headers, config) {
			$scope.loadingTracker = data;
			$scope.messages = 'There was a network error. Try again later.';
			console.log($scope.messages);
			console.error(data);
		})
		.finally(function() {
			// Hide status messages after three seconds.
			$timeout(function() {
				$scope.messages = null;
			}, 3000);
		});

	}

	//Create a new tracker
	//$scope.loadingTracker = promiseTracker();
//	$scope.loadingTracker = promiseTracker({ activationDelay: 500, minDuration: 500 });

	//console.log(domain)

/*
	$http.get('json/config.json').success(function(data){
		$scope.config = data;
		console.log(data)
	});

	$http.get('json/elements.json').success(function(data){
		$scope.elements = data;
		console.log(data)
	});
*/

	//console.log($scope.submit());
/*	
 	var promiseInterval;
    $scope.stop = function() {
      	$interval.cancel(promiseInterval);
    };

 	$scope.$on('$destroy', function() {
    	$scope.stop();
    })    
*/
	//paiting the elements after the view is loaded
	//first aproch works using regular timeout
	//setTimeout(function(){ $scope.paintElements($scope.elements) }, config.timeout);
	//second aproch works using angular timeout
	//$timeout(function () { $scope.paintElements($scope.elements) }, config.timeout);

	//best approch using promise tracker to let the user know we are loading the elements
//	$scope.paintLoadedElements = function() {
//		$scope.stop();
//		var promiseTimeout = $timeout(function () { $scope.paintElements($scope.elements) }, config.timeout);
//		promiseInterval = $interval(function () { $scope.paintElements($scope.elements) }, config.interval);
//		$scope.loadingTracker.addPromise(promiseTimeout);
//	};

}]);
