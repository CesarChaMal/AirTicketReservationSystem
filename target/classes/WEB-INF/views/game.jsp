<%@ include file="common/header.jspf"%>
	<script>
		var grid_p1 = ${grid_p1}
		//console.log(grid_p1)
        for (var i in grid_p1) {
			//console.log(i + ': ' + grid_p1[i])
        } 
        //console.log(grid.grid)
        grid_p1 = grid_p1.grid;

		var salvo_p1 = ${salvo_p1}
		//console.log(salvo_p1)
        for (var i in salvo_p1) {
			//console.log(i + ': ' + salvo_p1[i])
        } 
        //console.log(salvo_p1.salvoShots)
        var salvoShots_p1 = salvo_p1.salvoShots;

		var player_id_p1 = "${player_id_p1}";



		var grid_p2 = ${grid_p2}
		//console.log(grid_p2)
        for (var i in grid_p2) {
			//console.log(i + ': ' + grid_p2[i])
        } 
        //console.log(grid.grid)
        grid_p2 = grid_p2.grid;

		var salvo_p2 = ${salvo_p2}
		//console.log(salvo_p2)
        for (var i in salvo_p2) {
			//console.log(i + ': ' + salvo_p2[i])
        } 
        //console.log(salvo_p2.salvoShots)
        var salvoShots_p2 = salvo_p2.salvoShots;

		var player_id_p2 = "${player_id_p2}";

	</script>

	<body>
		<br>
<!--
	 	<script src="<c:url value="/resources/js/build/three.min.js" />"></script>
-->
	 	<script src="/webjars/three.js/r77/three.min.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/libs/tween.min.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/controls/TrackballControls.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/renderers/CSS3DRenderer.js" />"></script>
	 	<script src="<c:url value="/resources/js/examples/js/controls/OrbitControls.js" />"></script>

		<a href="javascript:history.back()" style="color:black">Go Back</a>

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

		<div id="container1"></div>
		<div id="container2"></div>
        
		<div id="info">
	    	<p><font color="red">${errorMessage}</font></p>
	    	<p><font color="black">${game_id}</font></p>
			<button id="player1">${player_id_p1}</button>
			<button id="player2">${player_id_p2}</button>
		</div>

		<script>

		if ( 
			(typeof grid_p1 != "undefined") && (typeof salvo_p1 != "undefined") &&
			(typeof grid_p2 != "undefined") && (typeof salvo_p2 != "undefined")
		 ){

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
			for (var y =  0; y < grid_p1.length; y++) {
				for (var x = 0; x < grid_p1[y].length; x++) {

					for ( var j = 0; j < salvoShots_p1.length; j++ ) {
						//console.log("x :" + salvoShots[j].x)
						//console.log("y :" + salvoShots[j].y)
						var shotX = salvoShots_p1[j].x;
						var shotY = salvoShots_p1[j].y;
						var status = salvoShots_p1[j].status;

						if ((y==shotX) && (x==shotY) && (status == 'MISS')){
							grid_p1[y][x] = '-';
							break;
						}
						if ( (y==shotX) && (x==shotY) && ((status == 'HIT') || (status == 'KILL')) ){
							grid_p1[y][x] = 'X';
							break;
						}
					}
					var coordinates = x + "," + y;
					//myJSONtext += "'*' , ' ', ' '," + i + ", " + j + ",";
					myJSONtext += "'" + grid_p1[y][x] + "', ' ', '" + coordinates +"'," + x + ", " + y + ",";
					//myJSONtext += "'" + grid[i][j] + "', ' ', ' '," + i + ", " + j + ",";
				}
			}
			myJSONtext = myJSONtext.substring(0, myJSONtext.lastIndexOf(","));
			myJSONtext += "];"
			//alert(myJSONtext)
			//console.log(myJSONtext)
			var table1 = eval(myJSONtext);


			myJSONtext = "[";
			for (var y =  0; y < grid_p2.length; y++) {
				for (var x = 0; x < grid_p2[y].length; x++) {

					for ( var j = 0; j < salvoShots_p2.length; j++ ) {
						//console.log("x :" + salvoShots[j].x)
						//console.log("y :" + salvoShots[j].y)
						var shotX = salvoShots_p2[j].x;
						var shotY = salvoShots_p2[j].y;
						var status = salvoShots_p2[j].status;

						if ((y==shotX) && (x==shotY) && (status == 'MISS')){
							grid_p2[y][x] = '-';
							break;
						}
						if ( (y==shotX) && (x==shotY) && ((status == 'HIT') || (status == 'KILL')) ){
							grid_p2[y][x] = 'X';
							break;
						}
					}
					var coordinates = x + "," + y;
					//myJSONtext += "'*' , ' ', ' '," + i + ", " + j + ",";
					myJSONtext += "'" + grid_p2[y][x] + "', ' ', '" + coordinates +"'," + x + ", " + y + ",";
					//myJSONtext += "'" + grid[i][j] + "', ' ', ' '," + i + ", " + j + ",";
				}
			}
			myJSONtext = myJSONtext.substring(0, myJSONtext.lastIndexOf(","));
			myJSONtext += "];"
			//alert(myJSONtext)
			//console.log(myJSONtext)
			var table2 = eval(myJSONtext);
			
			var camera1, scene1, renderer1;
			var camera2, scene2, renderer2;
			
			var controls1;
			var controls2;

			var objects1 = [];
			var objects2 = [];
			var targets = { table1: [], table2: [] };

			init();
			animate1();
			animate2();


			function init() {

				//camera = new THREE.PerspectiveCamera( 100, window.innerWidth / window.innerHeight, 1, 10000 );
				camera1 = new THREE.PerspectiveCamera( 100, window.innerWidth / window.innerHeight, 0.1, 10000 );
				//camera.position.z = 5000;
				camera1.position.z = 2000;

				scene1 = new THREE.Scene();

				// table

				//console.log(table.length)
				//var x = 7;
				//var y = 2;
				var x = 15;
				var y = 29;
				var gridRows = grid_p1.length;
				var gridColumns = grid_p1[0].length;
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


				for ( var i = 0; i < table1.length; i += 5 ) {
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
					if (table1[ i ] != ' ' && table1[ i ] != '-') {
						element.style.backgroundColor = 'rgba(127,127,127,127)';
					}
					
					// light green bullets
					if (table1[ i ] == '-') {
						element.style.backgroundColor = 'rgba(128,255,0,127)';
					}
					
					// red bullets
					if (table1[ i ] == 'X') {
						element.style.backgroundColor = 'rgba(255,51,51,127)';
					}
					
					var number = document.createElement( 'div' );
					number.className = 'number';
					//number.textContent = (i/5) + 1;
					number.textContent = '';
					element.appendChild( number );

					var symbol = document.createElement( 'div' );
					symbol.className = 'symbol';
					symbol.textContent = table1[ i ];
					element.appendChild( symbol );

					var details = document.createElement( 'div' );
					details.className = 'details';
					details.innerHTML = table1[ i + 1 ] + '<br>' + table1[ i + 2 ];
					element.appendChild( details );

					var object = new THREE.CSS3DObject( element );
					object.position.x = Math.random() * 4000 - 2000;
					object.position.y = Math.random() * 4000 - 2000;
					object.position.z = Math.random() * 4000 - 2000;
					scene1.add( object );

					objects1.push( object );

					var object = new THREE.Object3D();
					object.position.x = ( table1[ i + 3 ] * 140 ) - 1330;
					object.position.y = - ( table1[ i + 4 ] * 180 ) + 990;

					targets.table1.push( object );

				}


				camera2 = new THREE.PerspectiveCamera( 100, window.innerWidth / window.innerHeight, 0.1, 10000 );
				//camera.position.z = 5000;
				camera2.position.z = 2000;

				scene2 = new THREE.Scene();

				// table 2
				var gridRows = grid_p2.length;
				var gridColumns = grid_p2[0].length;
				var addX = 5 * gridRows;
				var addY = 5 * gridColumns;

				var x1 = x*5;
				var x2 = (x*5)+5;
				var y1 = y*addY;
				var y2 = (y*addY)+5;
				for ( var i = 0; i < table2.length; i += 5 ) {
					var element = document.createElement( 'div' );
					element.className = 'element';
					// blue grid
					element.style.backgroundColor = 'rgba(0,0,255,127)';
					// green spaceships
					if (table2[ i ] != ' ' && table2[ i ] != '-') {
						element.style.backgroundColor = 'rgba(127,127,127,127)';
					}
					
					// light green bullets
					if (table2[ i ] == '-') {
						element.style.backgroundColor = 'rgba(128,255,0,127)';
					}
					
					// red bullets
					if (table2[ i ] == 'X') {
						element.style.backgroundColor = 'rgba(255,51,51,127)';
					}
					
					var number = document.createElement( 'div' );
					number.className = 'number';
					//number.textContent = (i/5) + 1;
					number.textContent = '';
					element.appendChild( number );

					var symbol = document.createElement( 'div' );
					symbol.className = 'symbol';
					symbol.textContent = table2[ i ];
					element.appendChild( symbol );

					var details = document.createElement( 'div' );
					details.className = 'details';
					details.innerHTML = table2[ i + 1 ] + '<br>' + table2[ i + 2 ];
					element.appendChild( details );

					var object = new THREE.CSS3DObject( element );
					object.position.x = Math.random() * 4000 - 2000;
					object.position.y = Math.random() * 4000 - 2000;
					object.position.z = Math.random() * 4000 - 2000;
					scene2.add( object );

					objects2.push( object );

					var object = new THREE.Object3D();
					object.position.x = ( table2[ i + 3 ] * 140 ) - 1330;
					object.position.y = - ( table2[ i + 4 ] * 180 ) + 990;

					targets.table2.push( object );

				}



				renderer1 = new THREE.CSS3DRenderer();
				renderer1.setSize( window.innerWidth, window.innerHeight );
				//renderer.setSize( 500, 500 );
				renderer1.domElement.style.position = 'absolute';
				document.getElementById( 'container1' ).appendChild( renderer1.domElement );

				renderer2 = new THREE.CSS3DRenderer();
				renderer2.setSize( window.innerWidth, window.innerHeight );
				//renderer.setSize( 500, 500 );
				renderer2.domElement.style.position = 'absolute';
				document.getElementById( 'container2' ).appendChild( renderer2.domElement );

				// controls1 = new THREE.TrackballControls( camera, renderer.domElement );
				// controls1.rotateSpeed = 0.5;
				// controls1.minDistance = 500;
				// controls1.maxDistance = 6000;
				// controls1.addEventListener( 'change', render );

				controls1 = new THREE.OrbitControls( camera1, renderer1.domElement );
				// controls.rotateSpeed = 0.5;
				controls1.minDistance = 500;
				controls1.maxDistance = 6000;
				controls1.addEventListener( 'change', render1 );

				controls2 = new THREE.OrbitControls( camera2, renderer2.domElement );
				// controls.rotateSpeed = 0.5;
				controls2.minDistance = 500;
				controls2.maxDistance = 6000;
				controls2.addEventListener( 'change', render2 );
				//controls2.addEventListener( 'change', render1 );

				/*
				var button = document.getElementById( 'table' );
				button.addEventListener( 'click', function ( event ) {

					transform( targets.table, 2000 );

				}, false );
				*/

				// animation efect
				//transform( targets.table, 2000 );

				//without animation
				//transform( targets.table1, 0 );


				var button = document.getElementById( 'player1' );
				button.addEventListener( 'click', function ( event ) {

					document.getElementById( 'container2' ).style.display = "none";
					document.getElementById( 'player1' ).style.backgroundColor = 'rgba(0, 0, 255, .50)';
					document.getElementById( 'player2' ).style.background = 'transparent';
					transform( targets.table1, objects1, render1, 2000 );
					document.getElementById( 'container1' ).style.display = "block";

				}, false );

				var button = document.getElementById( 'player2' );
				button.addEventListener( 'click', function ( event ) {

					document.getElementById( 'container1' ).style.display = "none";
					document.getElementById( 'player2' ).style.backgroundColor = 'rgba(0, 0, 255, .50)';
					document.getElementById( 'player1' ).style.background = 'transparent';
					transform( targets.table2, objects2, render2, 2000 );
					document.getElementById( 'container2' ).style.display = "block";

				}, false );

				transform( targets.table1, objects1, render1, 2000 );
				transform( targets.table2, objects2, render2, 2000 );
				document.getElementById( 'player2' ).style.backgroundColor = 'rgba(0, 0, 255, .50)';

				window.addEventListener( 'resize', onWindowResize1, false );
				window.addEventListener( 'resize', onWindowResize2, false );

			}

			function transform( targets, objects, render, duration) {
				//alert(targets)

				TWEEN.removeAll();

				for ( var i = 0; i < objects.length; i ++ ) {

					var object = objects[ i ];
					var target = targets[ i ];
					//console.log(target)

					new TWEEN.Tween( object.position )
						.to( { x: target.position.x, y: target.position.y, z: target.position.z }, Math.random() * duration + duration )
						.easing( TWEEN.Easing.Exponential.InOut )
						.start();

					new TWEEN.Tween( object.rotation )
						.to( { x: target.rotation.x, y: target.rotation.y, z: target.rotation.z }, Math.random() * duration + duration )
						.easing( TWEEN.Easing.Exponential.InOut )
						.start();

				}

				new TWEEN.Tween( this )
					.to( {}, duration * 2 )
					.onUpdate( render )
					.start();

			}

			function onWindowResize1() {

				camera1.aspect = window.innerWidth / window.innerHeight;
				camera1.updateProjectionMatrix();

				renderer1.setSize( window.innerWidth, window.innerHeight );

				render1();

			}

			function onWindowResize2() {

				camera2.aspect = window.innerWidth / window.innerHeight;
				camera2.updateProjectionMatrix();

				renderer2.setSize( window.innerWidth, window.innerHeight );

				render2();

			}

			function animate1() {

				requestAnimationFrame( animate1 );

				TWEEN.update();

				controls1.update();

			}

			function animate2() {

				requestAnimationFrame( animate2 );

				TWEEN.update();

				controls2.update();

			}

			function render1() {
				renderer1.render( scene1, camera1 );

			}

			function render2() {
				renderer2.render( scene2, camera2 );
			}
		}

	</script>
<%@ include file="common/footer.jspf"%>
