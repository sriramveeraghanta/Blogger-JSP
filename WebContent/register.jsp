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
<div class=" container" style="min-height: 84vh;">
<div class="row justify-content-center mt-4 mb-4">
	<div class="col-md-6 pt-4 pb-4">
		<form action="register" method="POST">
			<div class="form-group">
				<label>First Name</label>
				<input class="form-control" type="text" name="first_name" placeholder="Enter your First Name">
			</div>
			<div class="form-group">
				<label>Last Name</label>
				<input class="form-control" type="text" name="last_name" placeholder="Enter your Last Name">
			</div>
			<div class="form-group">
				<label>Email</label>
				<input class="form-control" type="email" name="email" placeholder="Enter your Email">
			</div>
			<div class="form-group">
				<label>Password</label>
				<input class="form-control" type="password" name="pass" placeholder="Enter your Password">
			</div>
			<div>
				<button class="btn btn-success mr-4" type="submit">Register</button>
				<button class="btn btn-danger" type="reset">Reset</button>
			</div>	
		</form>
	</div>
</div>
</div>
</jsp:body>
</t:genericpage>
