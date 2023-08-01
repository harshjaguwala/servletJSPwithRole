<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
	<br/>
	<a href="<%=request.getContextPath()%>/Logout" class="btn btn-success">Logout</a><br />
	<c:if test="${not empty getOneUser}">
		<h1>
			<center>Show User</center>
		</h1>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Phoneno</th>
					<!-- <th>Action</th> -->
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${getOneUser.name}</td>
					<td>${getOneUser.phoneno}</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty listofuserinfo}">
		<h1>
			<center>Show All Users</center>
		</h1>
		<a href="<%=request.getContextPath()%>/ShowRegisterPage" class="btn btn-primary">Add User</a><br />
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Phoneno</th>
					<th>RoleName</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="listofuserinfo" items="${listofuserinfo}">
					<c:if
						test="${(listofuserinfo.roleName == 'Admin') || (listofuserinfo.roleName == 'User')}">
						<tr>
							<td>${listofuserinfo.name}</td>
							<td>${listofuserinfo.phoneno}</td>
							<td>${listofuserinfo.roleName}</td>
							<td><a
								href="editUserinfo?id=<c:out value='${listofuserinfo.userid}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="DeleteUserinfo?id=<c:out value='${listofuserinfo.userid}' />">Delete</a></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>