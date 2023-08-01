<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
</head>
<body>

		<c:if test="${existinguser != null}">
			<form action="updateUserinfo" method="post">
		</c:if>
		<c:if test="${existinguser == null}">
			<form action="register" method="post">
		</c:if>
		
		<c:if test="${existinguser != null}">
			<input type="hidden" name="id" value="<c:out value='${existinguser.userid}' />" />
		</c:if>
				
		<fieldset class="form-group">
			<label>Name</label> <input type="text"   value="${existinguser.name}"  class="form-control" name="name" required="required">
		</fieldset>
		
		<fieldset class="form-group">
			<label>Password</label> <input type="text"  class="form-control" name="password" required="required" value="${existinguser.password}">
		</fieldset>

		<fieldset class="form-group">
			<label>Phoneno</label> <input type="text"  class="form-control" name="phoneno" minlength="10" required="required"  value="${existinguser.phoneno}" >
		</fieldset>
	
		<c:if test="${existinguser == null}">
			<fieldset class="form-group">
				IsAdmin : <input type="checkbox" value="1" name="IsAdmincheck" ><br/>
				<button type="submit" class="btn btn-success">Save</button>
			</fieldset>
		</c:if>
		
		<c:if test="${existinguser != null}">
			<button type="submit" class="btn btn-success">Update</button>
		</c:if>
	</form>
</body>
</html>