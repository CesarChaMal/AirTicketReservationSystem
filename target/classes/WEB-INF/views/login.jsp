<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p><font color="red">${errorMessage}</font></p>
	<form action="/login.do" method="POST">
	
		<!-- <div class="col-xs-4"> -->
		<div class="col-xs-4 col-lg-4 col-sm-4">
			<div class="input-group input-group-lg">
			  <span class="input-group-addon" id="name">Name :</span>
			  <input name="name" type="text" class="form-control" placeholder="Username" aria-describedby="name">
			</div>
	
			<div class="input-group input-group-lg">
			  <span class="input-group-addon" id="password">Password :</span>
			  <input name="password" type="password" class="form-control" placeholder="Password" aria-describedby="password">
			</div>
		</div>
  		
  		
  		
  		<!--
		<div class="col-xs-4">
			<label for="name" class="label label-success">Name</label> 
			<input name="name" id="name" type="text" class="form-control form-control-sm" /> 
			<label for="password" class="label label-success">Password :</label> 
			<input name="password" id="password" type="password" class="form-control form-control-sm" /> 
			<BR />
		</div>
		-->
		
		<!-- <input type="submit" class="btn btn-success" value="Submit" /> -->
		<input type="submit" class="btn btn-lg btn-default" value="Submit" />
	</form>
</div>



<%@ include file="common/footer.jspf"%>
