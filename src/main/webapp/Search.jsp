<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="core.User, java.util.*"%>

    <!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<link rel="stylesheet" type="text/css" href="assets/styles.css" />
</head>
<body>

<table style = "border-color:white;">
    <tr>
	<form method="POST" action='Search' name="search">

				<td class="labels" style = "border-color:white;">Nickname:</td>
				<td style = "border-color:white;"><input type="text" name="snickname"  style="width:120px;"
				value=<%= request.getAttribute("snickname")!=null?request.getAttribute("snickname"):"" %>
				></td>
				<td class="labels" style = "border-color:white;">PhoneNumber:</td>
				<td style = "border-color:white;"><input type="text" name="sphone"  style="width:120px;"
				value=<%= request.getAttribute("sphone")!=null?request.getAttribute("sphone"):"" %>
				></td>
				<td align="center" style = "border-color:white;">
				<!--
				<%if (request.getAttribute("LoggedUsername")!=null){%>
				<input type="hidden" name="LoggedUsername" value=<%=request.getAttribute("LoggedUsername")%>>
				<%}%>
-->
					<input type="submit" name="btnSearch" value="Search">
				</td>

	</form>
	<form method="POST" action='Search' name "add">
	<td style = "border-color:white;">
	<!--
				<%if (request.getAttribute("LoggedUsername")!=null){%>
				<input type="hidden" name="LoggedUsername" value=<%=request.getAttribute("LoggedUsername")%>>
				<%}%>
		-->
	<input type="hidden" name="snickname" value=<%=request.getAttribute("snickname")%>>
	<input type="hidden" name="sphone" value=<%=request.getAttribute("sphone")%>>
	<input type="submit" name="btnAdd" value="Add new User">
	</td>
	</form>

	</tr>
</table>
<table>
 <tr>
 <th class="labels" style = "align:left;width:120px">Nickname</TH>
 <th class="labels" style = "align:left;width:120px">Full UserName</TH>
 <th class="labels" style = "align:left;width:120px">Phone Number</TH>
 <th class="labels" style = "align:left;width:120px">Email</TH>
 <th ></TH>
 <th ></TH>
 </tr>

 <%
     if (request.getAttribute("Model") != null ){
     ArrayList <User> userList= (ArrayList <User>)request.getAttribute("Model");

     Iterator i = userList.iterator();
     while (i.hasNext())

     {
       User user = (User)i.next();
  %>

 <tr>
 <td >
 <%=user.nickname%>
 </td>

 <td >
 <%=user.name+" "+user.surname%>
 </td>

 <td >
 <%=user.phone%>
 </td>

 <td >
 <%=user.email%>
 </td>

  <td >
    <form method="POST" action='Search' name="edit">
    <input type="hidden" name="nickname" value=<%=user.nickname%>>
    <!--
				<%if (request.getAttribute("LoggedUsername")!=null){%>
				<input type="hidden" name="LoggedUsername" value=<%=request.getAttribute("LoggedUsername")%>>
				<%}%>
				-->
    	<input type="hidden" name="snickname" value=<%=request.getAttribute("snickname")%>>
    	<input type="hidden" name="sphone" value=<%=request.getAttribute("sphone")%>>
    <input type="submit" name="btnEdit" value="Edit">
    </form>
  </td>

 <td >
    <form method="POST" action='Search' name="delete">
    <input type="hidden" name="nickname" value=<%=user.nickname%>>
    <!--
				<%if (request.getAttribute("LoggedUsername")!=null){%>
				<input type="hidden" name="LoggedUsername" value=<%=request.getAttribute("LoggedUsername")%>>
				<%}%>
				-->
    	<input type="hidden" name="snickname" value=<%=request.getAttribute("snickname")%>>
    	<input type="hidden" name="sphone" value=<%=request.getAttribute("sphone")%>>
    <input type="submit" name="btnDelete" value="Delete">
    </form>
  </td>
<%}}%>



</body>
</html>