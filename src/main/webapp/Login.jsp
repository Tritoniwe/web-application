<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html >

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login and Registration</title>
<link rel="stylesheet" type="text/css" href="assets/styles.css" />
<style>
td {
    background-color: #CCCCCC;
}
</style>
</head>

<body>
	
	<form method="POST" action='Login' name="login">
		<table >
			<tr >
				<td class="labels">Nickname:</td>
				<td><input type="text" name="nickname" maxlength="20" style="width:120px;"></td>
			</tr>
			<tr>
				<td class="labels">Password:</td>
				<td><input type="password" name="password" maxlength="20" style="width:120px;"></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" name="btnLogin" value="Login"> 
				</td>
			</tr>
		</table>
	</form>

	<form class="errors" >
	<% if (request.getAttribute("Error") != null ) {%>
		<%= request.getAttribute("Error") %>
	<%}%> 
	</form>
	
</body>
</html>