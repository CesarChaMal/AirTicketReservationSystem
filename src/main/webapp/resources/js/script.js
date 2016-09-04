$.fn.bootstrapBtn = $.fn.button.noConflict();

var domain = "";
//var domain = "http://localhost:8080/";
var windowWidth = $(window).width()/2;
var windowHeight = $(window).height()/2;	
var config;    	

$.getJSON('resources/json/config.json', function(data) {
	//console.log(data)
	config = data;
	//console.log(window.location.protocol);
	if (window.location.protocol == 'http:') {
		domain = window.location.protocol + '//' + config.webhost + ':' + config.webhostPortHttp + '/';
	} else if (window.location.protocol == 'https:') {
		domain = window.location.protocol + '//' + config.webhost + ':' + config.webhostPortHttps + '/';
	}
});
