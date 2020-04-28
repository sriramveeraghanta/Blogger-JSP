<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="layouts/header.jsp" %>  
    </jsp:attribute>
    <jsp:attribute name="footer">
		<%@ include file="layouts/footer.jsp" %>  
    </jsp:attribute>

	<jsp:body>
		<div class=" container">
<div class="row justify-content-center">
	<div class="col-md-6 pt-4 align-items-center">
		<h3 class="text-center">
			Write Your Way Into the World</h3>
	</div>
	<div class="col-md-6">
		<form action="login" method="POST">
			<tag:if test="${loginError}">
				<div class="alert alert-danger" role="alert">
					Incorrect Email Or Password
				</div>	
			</tag:if>
			<div class="form-group">
				<label>Email</label>
				<input class="form-control" type="email" name="email" placeholder="Enter your Email">
			</div>
			<div class="form-group">
				<label>Password</label>
				<input class="form-control" type="password" name="pass" placeholder="Enter your Password">
			</div>
			<div>
				<button class="btn btn-success mr-4" type="submit">Login</button>
				<button class="btn btn-danger" type="reset">Reset</button>
			</div>	
		</form>
	</div>
</div>
</div>
</jsp:body>
</t:genericpage>
