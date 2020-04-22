<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Social Spark</h1>

	<fieldset>
		<legend>Login</legend>
		<form action="login" method="POST">
			<input type="email" name="email" placeholder="Enter your Email">
			<br><br>
			<input type="password" name="pass" placeholder="Enter your Password">
			<br><br>
			<button type="submit">Login</button>
			<button type="reset">Reset</button>
			
			<tag:if test="${loginError}">
					<br>
					<span> incorrect email or password </span>	
			</tag:if>
			
			
			
		</form>
	</fieldset>

</body>
</html>