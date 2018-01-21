<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Contact</title>
</head>
<body>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>

<%
    Set<String> groupes = new HashSet<String>();
%>
 
 <form method="post" action="NewContact">
  <div class="container">
 
  	<label><b>Numero Siret</b></label>
    <input type="text" placeholder="Enter numero siret" name="numSiret" />
    <br>
    <label><b>Fist Name</b></label>
    <input type="text" placeholder="Enter first name" name="firstName" required/>
    <br>
    <label><b>Last Name</b></label>
    <input type="text" placeholder="Enter last name" name="lastName" required/>
        <br>
    
    <label><b>Email</b></label>
    <input type="text" placeholder="Enter email" name="email" required/>
        <br>
    
 
    
     <fieldset>
    <legend>Address</legend>
 
        <label><b>Street</b></label>
    		<input type="text" placeholder="Enter street" name="street"  />
        <br>
         <label><b>City</b></label>
    		<input type="text" placeholder="Enter city" name="city" />
        <br>
                 <label><b>Zip</b></label>
    		<input type="text" placeholder="Enter zip" name="zip"  />
        <br>
                 <label><b>Country</b></label>
    		<input type="text" placeholder="Enter country" name="country"  />
        <br>
    </fieldset>
    
    
    <fieldset>
    <legend>Phones</legend>
        <fieldset>
		    <legend >Mobile Phones</legend>
 		        
		        
		        <label><b>Phone Number</b></label>
		        <input type="text" placeholder="Enter Mobile Phone number" name="phoneMobileNumber"   />  <br>
		        
        </fieldset>
                <fieldset>
		    <legend>House Phones</legend>
 		        
		    
		        
		        <label><b>Phone Number</b></label>
		        <input type="text" placeholder="Enter House Phone number" name="phoneHouseNumber"   />  <br>
		        
        </fieldset>
                <fieldset>
		    <legend>Office Phones</legend>
 		        
		        
		        
		        <label><b>Phone Number</b></label>
		        <input type="text" placeholder="Enter Office Phone number" name="phoneOfficeNumber"   />  <br>
		        
        </fieldset>
         
    </fieldset> 

	<fieldset>
    	<legend>Groupes</legend>
    	
		<select id='groupesSelect' name='groupes' multiple='multiple'
			style='width: 100%'>
			<option value='Amis'>Amis</option>
			<option value='Collegues'>Collegues</option>
			<option value='Famille'>Famille</option>
			<%
			for (String nomDeGroupe : groupes) {
			%>    
			<option value='<%=nomDeGroupe%>'><%=nomDeGroupe%></option>
			<%  
			}
			%>
		</select>
			
		<script type='text/javascript'>
			$('#groupesSelect').select2({
				/**/
				tags : true,
				tokenSeparators : [ ',', ' ', ':',
						';', '\\', '/' ],
				placeholder : 'Entrer des groupes associés au contact'
			});
		</script>
    </fieldset>
    
    <input type="submit" value="Ajouter le contact"/>     
    <input type="reset" value="Annuler"/> 
   </div>
  </form> 
 
</body>
</html>