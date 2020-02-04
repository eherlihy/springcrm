<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Customer List</title>
		<link type="text/css"
				rel="stylesheet"
				href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>
	<body>
		<div id = "wrapper">
			<div id= "header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id = "container">
			<div id = "content">
				<!-- button: Add Customer -->
				<input type="button" value="Add Customer"
						onclick="window.location.href='showFormForAdd'; return false;"
						class="add-button"/>
				<!-- search bar -->
				<form:form action="search" method="GET">
					Search customer: <input type="text" name="theSearch" />
					<input type="submit" value="Search" class="add-button"/>
				</form:form>
				<!-- add our html table here -->
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					<!-- loop over and print customers -->
					<c:forEach var ="tempCustomer" items="${customers}">
						
						<!-- construct update and delete links -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>
						
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>
						
						<tr>
							<td> ${tempCustomer.firstName} </td>
							<td> ${tempCustomer.lastName} </td>
							<td> ${tempCustomer.email} </td>
							<td> <a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>