<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">

	<script>
		var grid = ${grid}
		//console.log(grid)
        for (var i in grid) {
			//console.log(i + ': ' + grid[i])
			//alert(grid[i])
        } 
        //console.log(grid.grid)
        grid = grid.grid;

		var salvo = ${salvo}
		//console.log(salvo)
        for (var i in salvo) {
			//console.log(i + ': ' + salvo[i])
			//alert(grid[i])
        } 
        //console.log(salvo.salvoShots)
        var salvoShots = salvo.salvoShots;

		var player_id = "${player_id}";
	</script>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<!-- 
		<meta http-equiv="refresh" content="20">
		-->
        <title>XL SpaceShip</title>
	
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />
	</head>
    
	<body>
		<br>
	
	 	<script src="<c:url value="/resources/js/build/three.min.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/libs/tween.min.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/controls/TrackballControls.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/renderers/CSS3DRenderer.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/controls/OrbitControls.js" />"></script>

    	<p><font color="red">${errorMessage}</font></p>

		<div id="container"></div>

		<div id="info">
	    	<p><font color="red">${errorMessage}</font></p>
	    	<p><font color="black">${game_id}</font></p>
			<div class='my-legend'>
				<div class='legend-scale'>
				  <ul class='legend-labels'>
				    <li>
				        <span class="color" style='background:rgba(0,0,255,127);'></span>
				        <span class="legend">Grid</span>
				        <span class="color" style='background:rgba(127,127,127,127);'></span>
				        <span class="legend">Spaceship</span>
				        <span class="color" style='background:rgba(128,255,0,127);'></span>
				        <span class="legend">Miss</span>
				        <span class="color" style='background:rgba(255,51,51,127);'></span>
				        <span class="legend">Hit</span>
				    </li>
				  </ul>
				</div>
			</div>
			<button>${player_id}</button>
		</div>

		<script>

		if ( (typeof grid != "undefined") && (typeof salvo != "undefined") ){

			//paint only the grid with the spaceships
			// myJSONtext = "[";
			// for (var y =  0; y < grid.length; y++) {
			// 	for (var x = 0; x < grid[y].length; x++) {
			// 		//myJSONtext += "'*' , ' ', ' '," + i + ", " + j + ",";
			// 		var coordinates = x + "," + y;
			// 		myJSONtext += "'" + grid[y][x] + "', ' ', '" + coordinates +"'," + x + ", " + y + ",";
			// 		//myJSONtext += "'" + grid[i][j] + "', ' ', ' '," + i + ", " + j + ",";
			// 	}
			// }
			// myJSONtext = myJSONtext.substring(0, myJSONtext.lastIndexOf(","));
			// myJSONtext += "];"

			//paint the grid with the spaceships and the shots missed
			myJSONtext = "[";
			for (var y =  0; y < grid.length; y++) {
				for (var x = 0; x < grid[y].length; x++) {

					for ( var j = 0; j < salvoShots.length; j++ ) {
						//console.log("x :" + salvoShots[j].x)
						//console.log("y :" + salvoShots[j].y)
						var shotX = salvoShots[j].x;
						var shotY = salvoShots[j].y;
						var status = salvoShots[j].status;

						if ((y==shotX) && (x==shotY) && (status == 'MISS')){
							grid[y][x] = '-';
							break;
						}
						if ( (y==shotX) && (x==shotY) && ((status == 'HIT') || (status == 'KILL')) ){
							grid[y][x] = 'X';
							break;
						}
					}
					var coordinates = x + "," + y;
					//myJSONtext += "'*' , ' ', ' '," + i + ", " + j + ",";
					myJSONtext += "'" + grid[y][x] + "', ' ', '" + coordinates +"'," + x + ", " + y + ",";
					//myJSONtext += "'" + grid[i][j] + "', ' ', ' '," + i + ", " + j + ",";
				}
			}
			myJSONtext = myJSONtext.substring(0, myJSONtext.lastIndexOf(","));
			myJSONtext += "];"
			//alert(myJSONtext)
			//console.log(myJSONtext)
			var table = eval(myJSONtext);
			var camera, scene, renderer;
			
			var controls;

			var objects = [];
			var targets = { table: [] };

			init(table, 'container');
			animate();


			function init(table, container) {

				//camera = new THREE.PerspectiveCamera( 100, window.innerWidth / window.innerHeight, 1, 10000 );
				camera = new THREE.PerspectiveCamera( 100, window.innerWidth / window.innerHeight, 0.1, 10000 );
				//camera.position.z = 5000;
				camera.position.z = 2000;

				scene = new THREE.Scene();

				// table

				//console.log(table.length)
				//var x = 7;
				//var y = 2;
				var x = 15;
				var y = 29;
				var gridRows = grid.length;
				var gridColumns = grid[0].length;
				var addX = 5 * gridRows;
				var addY = 5 * gridColumns;

				// for 16 x 16 grid
				// var x1 = (x*5);
				// var x2 = (x*5)+5;
				// var y1 = y*80;
				// var y2 = (y*80)+5;
				
				var x1 = x*5;
				var x2 = (x*5)+5;
				var y1 = y*addY;
				var y2 = (y*addY)+5;
				//console.log(gridColumns)
				//salvo shots
				// for ( var j = 0; j < salvoShots.length; j++ ) {
				// 	console.log("x :" + salvoShots[j].x)
				// 	console.log("y :" + salvoShots[j].y)
				// }

				//salvo shots coordinates
				// for ( var j = 0; j < salvoShots.length; j++ ) {
				// 	//console.log("x :" + salvoShots[j].x)
				// 	//console.log("y :" + salvoShots[j].y)
				// 	x = salvoShots[j].x;
				// 	y = salvoShots[j].y;
				// 	x1 = x*5;
				// 	x2 = (x*5)+5;
				// 	y1 = y*addY;
				// 	y2 = (y*addY)+5;
				// 	if (j == (x1+y1) && j <=(x2+y2)){
				// 		//element.style.backgroundColor = 'rgba(0,255,255,255)';
				// 	}	
				// }


				for ( var i = 0; i < table.length; i += 5 ) {
					var element = document.createElement( 'div' );
					element.className = 'element';
					// blue grid
					element.style.backgroundColor = 'rgba(0,0,255,127)';
					//element.style.backgroundColor = 'rgba(0,127,127,127)';
					//element.style.backgroundColor = 'rgba(0,127,127,' + ( Math.random() * 0.5 + 0.25 ) + ')';
					// if (i == (x1+y1) && i <=(x2+y2)){
					// 	element.style.backgroundColor = 'rgba(0,255,255,255)';
					// }

					//var x = table[ i + 1 ];
					//var y = table[ i + 2 ];
					//console.log(x)
					//console.log(y)
					//var x1 = (x*5);
					//var x2 = (x*5)+5;
					//var y1 = y*80;
					//var y2 = (y*80)+5;

					//if (table[ i ] != ' ' && (i == (x1+y1) && i <=(x2+y2)) ) {
					//if ( (i == (x1+y1) && i <=(x2+y2)) ) {
						//element.style.backgroundColor = 'rgba(0,127,127,127)';
					 	//if (i == (x1+y1) && i <=(x2+y2)){
							//element.style.backgroundColor = 'rgba(0,127,127,127)';
						//}
					//}

					// green spaceships
					if (table[ i ] != ' ' && table[ i ] != '-') {
						element.style.backgroundColor = 'rgba(127,127,127,127)';
					}
					
					// yellow bullets
					if (table[ i ] == '-') {
						element.style.backgroundColor = 'rgba(128,255,0,127)';
					}
					
					// red bullets
					if (table[ i ] == 'X') {
						element.style.backgroundColor = 'rgba(255,51,51,127)';
					}
					
					var number = document.createElement( 'div' );
					number.className = 'number';
					//number.textContent = (i/5) + 1;
					number.textContent = '';
					element.appendChild( number );

					var symbol = document.createElement( 'div' );
					symbol.className = 'symbol';
					symbol.textContent = table[ i ];
					element.appendChild( symbol );

					var details = document.createElement( 'div' );
					details.className = 'details';
					details.innerHTML = table[ i + 1 ] + '<br>' + table[ i + 2 ];
					element.appendChild( details );

					var object = new THREE.CSS3DObject( element );
					object.position.x = Math.random() * 4000 - 2000;
					object.position.y = Math.random() * 4000 - 2000;
					object.position.z = Math.random() * 4000 - 2000;
					scene.add( object );

					objects.push( object );

					var object = new THREE.Object3D();
					object.position.x = ( table[ i + 3 ] * 140 ) - 1330;
					object.position.y = - ( table[ i + 4 ] * 180 ) + 990;

					targets.table.push( object );

				}

				renderer = new THREE.CSS3DRenderer();
				//renderer = new THREE.WebGLRenderer();
				renderer.setSize( window.innerWidth, window.innerHeight );
				//renderer.setSize( 500, 500 );
				renderer.domElement.style.position = 'absolute';
				document.getElementById( container ).appendChild( renderer.domElement );

				// controls = new THREE.TrackballControls( camera, renderer.domElement );
				// controls.rotateSpeed = 0.5;
				// controls.minDistance = 500;
				// controls.maxDistance = 6000;
				// controls.addEventListener( 'change', render );

				controls = new THREE.OrbitControls( camera, renderer.domElement );
				// controls.rotateSpeed = 0.5;
				controls.minDistance = 500;
				controls.maxDistance = 6000;
				controls.addEventListener( 'change', render );

				/*
				var button = document.getElementById( 'table' );
				button.addEventListener( 'click', function ( event ) {

					transform( targets.table, 2000 );

				}, false );
				*/

				// animation efect
				//transform( targets.table, 2000 );

				//without animation
				transform( targets.table, 0 );

				//

				window.addEventListener( 'resize', onWindowResize, false );

			}


			function transform( targets, duration ) {

				TWEEN.removeAll();

				for ( var i = 0; i < objects.length; i ++ ) {
					var object = objects[ i ];
					var target = targets[ i ];
					console.log(target)

					new TWEEN.Tween( object.position )
						.to( { x: target.position.x, y: target.position.y, z: target.position.z }, Math.random() * duration + duration )
						//.easing( TWEEN.Easing.Exponential.InOut )
						.start();

					new TWEEN.Tween( object.rotation )
						.to( { x: target.rotation.x, y: target.rotation.y, z: target.rotation.z }, Math.random() * duration + duration )
						//.easing( TWEEN.Easing.Exponential.InOut )
						.start();

				}

				new TWEEN.Tween( this )
					.to( {}, duration * 2 )
					.onUpdate( render )
					.start();

			}

			function onWindowResize() {

				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();

				renderer.setSize( window.innerWidth, window.innerHeight );

				render();

			}

			function animate() {

				requestAnimationFrame( animate );

				TWEEN.update();

				controls.update();

			}

			function render() {

				renderer.render( scene, camera );

			}
		}

	</script>
</body>
</html>