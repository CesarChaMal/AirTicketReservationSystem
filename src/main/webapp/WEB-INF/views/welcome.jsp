<%@ include file="common/header.jspf"%>
<body>
    <div class="container" align="center">
    	<p><font color="red">${errorMessage}</font></p>
    	<div class="jumbotron game-form" id="form">
	        <form action="/login" method="post" id="login">
				<fieldset class="form-group">
		            <table border="0">
		                <tr>
		                    <td colspan="2" align="center"><h2>XL Spaceship</h2></td>
		                </tr>
		                <tr>
		                    <td>User id:</td>
		                    <td><input name="user_id" class="form-control" required="required"/></td>
		                </tr>
		                <tr>
		                    <td>Full Name:</td>
		                    <td><input name="full_name" class="form-control" required="required"/></td>
		                </tr>
		                <tr>
		                    <td>Spaceship Protocol hostname:</td>
		                    <td><input name="hostname" class="form-control" required="required"/></td>
		                </tr>
		                <tr>
		                    <td>Spaceship Protocol port:</td>
		                    <td><input name="port" class="form-control" required="required"/></td>
		                </tr>
		                <tr>
		                    <td colspan="2" align="right"><input class="btn btn-success btn-md" type="submit" value="Create" /></td>
		                </tr>
		            </table>
				</fieldset>
	        </form>
        </div>
        <div class="jumbotron" id="newGame" style="display:none">
	        <p>User id: <font color="blue">${user_id}</font></p>
			<p>Full name: <font color="blue">${full_name}</font></p>
			<p>Rules: <font color="blue">${rules}</font></p>
			<p>Starting: <font color="blue">${starting}</font></p>
			<p>Game id: <font color="blue">${game_id}</font></p>
			<!-- <a href="/play/${game_id}/user/${starting}">Play</a> -->
			<a href="/play?game_id=${game_id}&user_id=${starting}" class="btn btn-success btn-lg">Play</a>
        </div>
    </div>
	<script>
		var status = "${status}";
		//alert(typeof status)
		if (status == "true"){
			document.getElementById("newGame").style.display = "block";
			document.getElementById("form").style.display = "none";
		}
	</script>
<%@ include file="common/footer.jspf"%>

