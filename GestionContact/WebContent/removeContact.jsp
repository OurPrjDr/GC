<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remove Contact</title>
</head>
<body>

 <form method="post" action="DeleteContact">
  <div class="container">
    <label><b>Id Contact</b></label>
    <input type="text" placeholder="Enter Id" name="idContact" required/>
    <br>

    <input type="submit" value="Supprimer le contact"/>     
    <input type="reset" value="Annuler"/> 
   </div>
  </form> 


</body>
</html>