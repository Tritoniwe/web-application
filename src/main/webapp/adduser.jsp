<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="core.User,java.util.*"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
         "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Adding and Edition Users</title>
    <link rel="stylesheet" type="text/css" href="assets/styles.css" />
    <style>
    td {
        background-color: #CCCCCC;
    }
    </style>
</head>
<body>
<%User user = (User)request.getAttribute("User");%>
    <form method="POST" action='Edit' name="Add/Edit">
        <table >
            <tr >
                <td class="labels">Nickname:</td>
                <td><input type="text" name="nickname" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.nickname:"" %>
                <% if (request.getAttribute("Edit")!= null){ out.print(" disabled ");%> >
                <input type="hidden" name="nickname" value=<%=user.nickname%>
                <%}%>
                ></td>
            </tr>
            <tr>
                <td class="labels">Password:</td>
                <td><input type="password" name="password" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.password:""%>></td>
            </tr>

            <tr>
                <td class="labels">User Role:</td>
                <td>
                    <select name="userrole">
                    <option <% if (user==null||user.userRole==null){ out.print("selected");}%> disabled>Choose User Role</option>
                    <option <% if (user!=null&&user.userRole!=null&&user.userRole.equals("Admin")){ out.print("selected ");}%> value="Admin">Admin</option>
                    <option <% if (user!=null&&user.userRole!=null&&user.userRole.equals("User")) {out.print("selected ");}%>value="User">User</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td class="labels">Name:</td>
                <td><input type="text" name="name" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.name:""%>></td>
            </tr>
            <tr>
                <td class="labels">Surname:</td>
                <td><input type="text" name="surname" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.surname:""%>></td>
            </tr>
            <tr>
                <td class="labels">Sex:</td>
                <td>
                    <input type="radio" name="sex"
                    <% if (user!=null&&user.sex!=null&&user.sex.equals("male")){ out.print("checked ");}%> value="male"> Male
                    <input type="radio" name="sex"
                    <% if (user!=null&&user.sex!=null&&user.sex.equals("female")){ out.print("checked ");}%>value="female"> Female
               </td>
            </tr>
            <tr>
                <td class="labels">Email:</td>
                <td><input type="text" name="email" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.email:""%>></td>
            </tr>
            <tr>
                <td class="labels">Phone:</td>
                <td><input type="text" name="phone" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.phone:""%>></td>
            </tr>
            <tr>
                <td class="labels">Adresss:</td>
                <td><input type="text" name="address" maxlength="20" style="width:120px;"
                value=<%= user!=null?user.address:""%>></td>
            </tr>
            <tr>
            <td colspan="2" align="right">
				<!--
				<%if (request.getAttribute("LoggedUsername")!=null){%>
				<input type="hidden" name="LoggedUsername" value=<%=request.getAttribute("LoggedUsername")%>>
				<%}%>
				-->
                	<input type="hidden" name="snickname" value=<%=request.getAttribute("snickname")%>>
                	<input type="hidden" name="sphone" value=<%=request.getAttribute("sphone")%>>
                	<input type="hidden" name="btnSearch" value="aa">
                    <input type="submit"
                    name=<%=request.getAttribute("Edit")!= null?"btnEditUser":"btnAddUser"%>
                    value=<%=request.getAttribute("Edit")!= null?"Save":"Add User"%>>
                    <input type="submit" name="btnBack" value= "Back">

            </td>
            </tr>
        </table>
    </form>
    <form class="errors" >
        <% if (request.getAttribute("ErrorModel") != null ) {%>
            Fields listed bellow can't be empty: <br>
            <%for (String str: (ArrayList<String>)request.getAttribute("ErrorModel")){%>
		    <%= str %> <br>
	        <%}}%>
        </form>
</body>
</html>