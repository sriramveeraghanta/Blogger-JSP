<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="models.*" %>
    
<%
	User user = (User) session.getAttribute("user");
	if(user == null){
		response.sendRedirect("index.jsp");
	}
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>


	<h1>Profile</h1>
	
	<h2> ${user.getFirstName()} ${user.getLastName()}<br></h2>
	<h2> ${user.getEmail()}<br></h2>
	<br><br>
	
	<a href="Home">Home</a>
	
	<form action="post/create" method="POST">
		<textarea name="content"></textarea>
		<input type="file" name="image">
		<button type="submit">Create</button>
		<button type="reset">Clear</button>	
	</form>

	



</body>
</html>