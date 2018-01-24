<%@ page errorPage="/error.jsp" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src='./design/js/bootstrap.js'></script>
<link href='./design/css/bootstrap.min.css' rel='stylesheet'></head>
<body>
<% 
   Long idContact = null;
   if(request.getParameter("idContact")!=null){
		idContact = Long.parseLong(request.getParameter("idContact"));
	}%>
 <form method="post" action="DeleteContact">
  <div class="container">
         <h3 class="text-on-pannel text-primary"><strong class="text-uppercase"> Delete Contact </strong></h3>
  
    <div class="form-group"> 
    	<label><b>Id Contact</b></label>
    	<input class="from-control" type="text" placeholder="Enter Id" value=<%=idContact %> name="idContact" required/>
    </div>

    <input class="btn btn-primary" type="submit" value="Supprimer le contact"/>     
    <input class="btn btn-secondary" type="reset" value="Annuler"/> 
  </div>
  </form> 


</body>
</html>