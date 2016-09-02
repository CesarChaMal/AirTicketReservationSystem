<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<H1>Welcome ${LOGGEDIN_USER}</H1>

	<table class="table table-striped">
		<%-- <caption><spring:message code="app"/></caption> --%>
		<caption>${app}</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Actions</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${flights}" var="f">
				<tr>
					<td>${f.name}</td>
					<td>${f.destination}</td>
					<td>&nbsp;&nbsp;<a class="btn btn-danger" href="/delete-flight.do?flight=${f.name}&destination=${f.destination}">Delete</a></td>
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
