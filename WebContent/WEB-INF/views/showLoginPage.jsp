<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<form action="Afterlogin" method="post">
		<fieldset class="form-group">
			<label>User Name</label> <input type="text" class="form-control"
				name="name" required="required">
		</fieldset>

		<fieldset class="form-group">
			<label>Password</label> <input type="text" class="form-control"
				name="password" required="required">
		</fieldset>
		
		<button type="submit" class="btn btn-success">Login</button>
		<a href="<%=request.getContextPath()%>/ShowRegisterPage" class="btn btn-primary">Sign Up</a><br />
	</form>
</body>
</html>