<%@ page errorPage="/error.jsp" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src='./design/js/bootstrap.js'></script>
<link href='./design/css/bootstrap.min.css' rel='stylesheet'></head>
<body>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>

<%
    Set<String> groupes = new HashSet<String>();
%>
 
 <form method="post" action="NewContact">
  <div class="container">
  			<br>
            <h3 class="text-on-pannel text-primary"><strong class="text-uppercase"> Add New Contact </strong></h3>
  			<br>
 <label for="checkSiret">
    <input type="checkbox" id="checkSiret" onclick="ShowHideSiret(this)" />
    Contact Entreprise ?
</label>
<hr />
<div id="divsiret" class="form-group" style="display: none">
  	<label><b>Numero Siret</b></label>
    <input class="form-control"  type="text" placeholder="Enter numero siret" name="numSiret" />
</div>

  <div class="form-group">
    <label><b>Fist Name</b></label>
    <input class="form-control" type="text" placeholder="Enter first name" name="firstName" required/>
  </div>
    <div class="form-group">
    <label><b>Last Name</b></label>
    <input class="form-control" type="text" placeholder="Enter last name" name="lastName" required/>
      </div>  
      <div class="form-group">
    <label><b>Email</b></label>
    <input class="form-control" type="email" placeholder="Enter email" name="email" required/>
        
    </div>
 
    
   <fieldset>
    <legend>Address</legend>
   <div class="form-group">
        <label><b>Street</b></label>
    		<input class="form-control" type="text" placeholder="Enter street" name="street"  />
     </div>  
       <div class="form-group"> 
         <label><b>City</b></label>
    		
    		<input class="form-control" type="text" placeholder="Enter city" name="city" />
          </div>
            <div class="form-group">
                 <label><b>Zip</b></label>
    		<input class="form-control" type="text" placeholder="Enter zip" name="zip"  />
        </div>
          <div class="form-group">
                 <label><b>Country</b></label>
    		<input class="form-control" type="text" placeholder="Enter country" name="country"  />
        </div>
    </fieldset>
    
    
    <fieldset>
    <legend>Phones</legend>
        <fieldset>
          <div class="form-group">
		      <label><b>Mobile Phone</b></label>
 		        
 		        
 		        <input class="form-control" type="text" placeholder="Enter Mobile Phone number" name="phoneMobileNumber"   />  
		      </div>  
        </fieldset>
                <fieldset>
                  <div class="form-group">
		      <label><b>House Phone</b></label>
 		        
		    
		        
 		        <input class="form-control" type="text" placeholder="Enter House Phone number" name="phoneHouseNumber"   />  
		        </div>
        </fieldset>
                <fieldset>
		      <label><b>Office Phone</b></label>
 		        
		        
		        
 		        <input class="form-control" type="text" placeholder="Enter Office Phone number" name="phoneOfficeNumber"   />  
		        
        </fieldset>
         
    </fieldset> 

	<fieldset>
    	<legend>Groupes</legend>
    	
		<select class="form-control" id='groupesSelect' name='groupes' multiple='multiple'
			style='width: 100%'>
			<option value='Collegues'>Collegues</option>
			<option value='Amis'>Amis</option>
			<option value='Famille'>Famille</option>
			<%
			for (String nomDeGroupe : groupes) {
			%>    
			<option value='<%=nomDeGroupe%>'><%=nomDeGroupe%></option>
			<%  
			}
			%>
		</select>
			
		<a href="#new" class="btn" onclick="addGroup()">Autres Groupe</a>
		<br>
		<div id="new" style="display:none">
  		<input class="form-control" type="text" placeholder="Enter new groupe" name="new_group"   /> 
  		</div> 
		<br>
		
		<script type='text/javascript'>
			$('#groupesSelect').select2({
				/**/
				tags : true,
				tokenSeparators : [ ',', ' ', ':',
						';', '\\', '/' ],
				placeholder : 'Entrer des groupes associés au contact'
			});
			function addGroup() {
			    var x = document.getElementById("new");
			    if (x.style.display === "none") {
			        x.style.display = "block";
			    } else {
			        x.style.display = "none";
			    }
			} 

		     function ShowHideSiret(checkSiret) {
		         var divsiret = document.getElementById("divsiret");
		         divsiret.style.display = checkSiret.checked ? "block" : "none";
		     }
		     
		</script>
    </fieldset>
    
    <input class="btn btn-primary" type="submit" value="Ajouter le contact"/>     
    <input class="btn btn-secondary" type="reset" value="Annuler"/> 
   </div>
  </form> 
 
</body>
</html>