<%@ page errorPage="/error.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
				<h4><%=message %></h4> 
			
			<% if(success==null || !success){ %>
				</div>
			<% } else { %>
				</div>
			<% }%>
		<%}%>
		<%if(sign.equals("up")) {%>
			<fieldset style="margin-top: 5%; margin-bottom: 5%;">
	   			<legend style="color: #5826AB80;">Sign up</legend>
	  		</fieldset>
			<input type="hidden" name="SignInOrUp" value="up" />
			Login : <input type="text" class="form-control" name="login" /><br>
			Password : <input type="text" class="form-control" name="password" /><br> 
			Repeated password : <input type="text" class="form-control" name="secondPassword"><br> 
			
			<br/> 
			<button class="btn btn-info" type="submit">Sign up</button>
			<button class="btn" type="reset">Reset</button>
		<%}else if(sign.equals("in")) {%>
		
		
		
		   <fieldset style="margin-top: 5%; margin-bottom: 5%;">
    			<legend style="color: #5826AB80;">Sign in</legend>
   			</fieldset>
			<input type="hidden" name="SignInOrUp" value="in" />
			Login : <input type="text" class="form-control" name="login" /><br>
			Password : <input type="text" class="form-control" name="password" /><br> 
			
			<br/> 
			<button class="btn btn-info" type="submit">Sign in</button>
			<button class="btn" type="reset">Reset</button>
		<% } %>
	</form>

</body>
</html>