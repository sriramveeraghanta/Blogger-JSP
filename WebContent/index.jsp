<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="layouts/header.jsp" %>  
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>
    </jsp:attribute>
    <jsp:body>
    	<h1>Social Spark</h1>
        <p>Hi I'm the heart of the message</p>
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
    </jsp:body>
</t:genericpage>
