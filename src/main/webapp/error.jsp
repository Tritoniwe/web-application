<html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
         "http://www.w3.org/TR/html4/loose.dtd">


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="assets/styles.css" />
</head>
<body>

	<text class="errors" >
	Unexpected error during processing your request.<br>
	Error:
	<%= request.getAttribute("Error")%>
	</text>
</body>
</html>