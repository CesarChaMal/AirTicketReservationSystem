
//Directives
app.directive(
	"bnDelegate",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			var config = attributes.bnDelegate.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"mouseover.bnDelegate",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "mouseover.bnDelegate" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive(
	"bnHover",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			var config = attributes.bnHover.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"hover.bnHover",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "hover.bnHover" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive(
	"bnOver",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			var config = attributes.bnOver.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"mouseover.bnOver",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "mouseover.bnOver" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive(
	"bnOut",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			var config = attributes.bnOut.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"mouseout.bnOut",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "mouseout.bnOut" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive(
	"bnEnter",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			var config = attributes.bnEnter.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"mouseenter.bnEnter",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "mouseenter.bnEnter" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive(
	"bnLeave",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			var config = attributes.bnLeave.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"mouseleave.bnLeave",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "mouseleave.bnLeave" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive(
	"bnClick",
	function( $parse ) {
		function link( $scope, element, attributes ) {
			//console.log($scope)
			//console.log(element)
			//console.log(attributes)
			var config = attributes.bnClick.split( "|" );
			if ( config.length === 1 ) {
				var selector = "a";
				var expression = config[ 0 ];
			} else {
				var selector = config[ 0 ];
				var expression = config[ 1 ];
			}
			var expressionHandler = $parse( expression );
			element.on(
				"click.bnClick",
				selector,
				function( event ) {
					event.preventDefault();
					var localScope = $(event.target).scope();
					localScope.$apply(
						function() {
							expressionHandler( localScope );
						}
					);
				}
			);
			$scope.$on(
				"$destroy",
				function( event ) {
					element.off( "click.bnClick" );
				}
			);
		}
		return({
			link: link,
			restrict: "A"
		});
	}
);

app.directive('afterRender', ['$timeout', function ($timeout) {
    var def = {
        restrict: 'A',
        terminal: true,
        transclude: false,
        link: function (scope, element, attrs) {
        	$timeout(function(){scope.$eval(attrs.afterRender)}, 0); //Calling a scoped method
        }
    };
    return def;
}]);

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])

