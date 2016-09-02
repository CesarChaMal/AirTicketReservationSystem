<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<H1>Welcome ${LOGGEDIN_USER}</H1>

	<table class="table table-striped">
		<caption>Your Flights are</caption>
		<thead>
			<th>Description</th>
			<th>Destination</th>
			<th>Actions</th>
		</thead>
		<tbody>
			<c:forEach items="${flights}" var="flight">
				<tr>
					<td>${flight.name}</td>
					<td>${flight.destination}</td>
					<td>&nbsp;&nbsp;<a class="btn btn-danger" href="/delete-flight.do?todo=${flight.name}&destination=${flight.destination}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<font color="red">${errorMessage}</font>
	</p>
	<a class="btn btn-success" href="/add-flight.do">Add New Flight</a>
</div>

<%@ include file="common/footer.jspf"%>
