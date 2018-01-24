<%@ page errorPage="/error.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='./design/js/bootstrap.js'></script>
<link href='./design/css/bootstrap.min.css' rel='stylesheet'>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String sign = (String) request.getParameter("SignInOrUp");
	if(sign!=null && sign.equals("up")){
	%>
		<title>Sign up</title>
	<%		
	} else if (sign!=null && sign.equals("in")){
	%>
		<title>Sign in</title>
	<% } %>
</head>
<body> 
<div class="container">
  <div class="panel panel-primary">
    <div class="panel-body">
	<form method=post action="SignServlet">
		<%
		String message = ((String)request.getAttribute("message"));
		Boolean success = ((Boolean)request.getAttribute("success"));
		if(message != null){
			if(success==null || !success){ %>
			<% } else { %>
				<div class="alert alert-dismissable alert-success">
			<% }%>
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<div class="btn btn-danger btn-block"><%=message %></div> 
			
			<% if(success==null || !success){ %>
				</div>
			<% } else { %>
				</div>
			<% }%>
		<%}%>
		<%if(sign.equals("up")) {%>
       <h3 class="text-on-pannel text-primary"><strong class="text-uppercase"> Sign Up </strong></h3>
 
			<input type="hidden" name="SignInOrUp" value="up" />
			Login : <input type="text" class="form-control" name="login" />
			Password : <input type="password" class="form-control" name="password" /> 
			Repeat password : <input type="password" class="form-control" name="secondPassword"> 
			
			<br/> 
			<button class="btn btn-primary" type="submit">Sign up</button>
			<button class="btn btn-secondary" type="reset">Reset</button>
		<%}else if(sign.equals("in")) {%>
		
		    <h3 class="text-on-pannel text-primary"><strong class="text-uppercase">Sign In</strong></h3>
		 	
		   
			<input type="hidden" name="SignInOrUp" value="in" />
			Login : <input type="text" class="form-control" name="login" />
			Password : <input type="password" class="form-control" name="password" /> 
			
			<br/> 
			<button class="btn btn-primary" type="submit">Sign in</button>
			<button class="btn btn-secondary" type="reset">Reset</button>
		<% } %>
	</form>
</div>
</div>
</div>
</body>
</html>