<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="core.User, java.util.*"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
         "http://www.w3.org/TR/html4/loose.dtd">
<%if (session.getAttribute("USER")==null){%>
<jsp:forward page="/Login.jsp"/>
<%}%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/styles.css" />
</head>
<body>

<table style = "border-color:white;">
    <tr>
	<form method="POST" action='<%=request.getContextPath()%>/actions/Search' name="search">

				<td class="labels" style = "border-color:white;">Nickname:</td>
				<td style = "border-color:white;"><input type="text" name="snickname"  style="width:120px;"
				value=<%= session.getAttribute("snickname")!=null?session.getAttribute("snickname"):"" %>
				></td>
				<td class="labels" style = "border-color:white;">PhoneNumber:</td>
				<td style = "border-color:white;"><input type="text" name="sphone"  style="width:120px;"
				value=<%= session.getAttribute("sphone")!=null?session.getAttribute("sphone"):"" %>
				></td>
				<td align="center" style = "border-color:white;">
					<input type="submit" name="btnSearch" value="Search">
				</td>

	</form>
	<form method="POST" action='<%=request.getContextPath()%>/actions/Search' name "add">
	<td style = "border-color:white;">
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
    <form method="POST" action='<%=request.getContextPath()%>/actions/Search' name="edit">
    <input type="hidden" name="nickname" value=<%=user.nickname%>>
    <input type="submit" name="btnEdit" value="Edit">
    </form>
  </td>

 <td >
    <form method="POST" action='<%=request.getContextPath()%>/actions/Search' name="delete">
    <input type="hidden" name="nickname" value=<%=user.nickname%>>
    <input type="submit" name="btnDelete" value="Delete">
    </form>
  </td>
<%}}%>

<form method="GET" action='<%=request.getContextPath()%>/Logout' name="Logout">
<input type="submit" name="btnLogout" value="Logout">
</form>

</body>
</html>