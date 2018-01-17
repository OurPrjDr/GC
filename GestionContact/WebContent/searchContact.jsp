<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Recherche Contact</title>
    </head>
    <body>
    
    
	<%@page import="java.util.Set"%>
	<%@page import="java.util.HashSet"%>
    
    <%
    Set<String> groupes = new HashSet<String>();
 	%>
        <form method="post" action="SearchContact">
        	
        	<div class='row cells2'>
				<div class='cell colspan2'>
					<h3>Type de recherche HQL</h3>
				</div>
			</div>

			<div class='row cells8'>
				<div class='cell colspan2'>
					<label class="input-control radio">
					    <input type="radio" name="typesearch" value="Simple">
					    <span class="check"></span>
					    <span class="caption">Simple</span>
					</label>
				</div>
				<div class='cell colspan2'>
					<label class="input-control radio">
					    <input type="radio" name="typesearch" value="Param">
					    <span class="check"></span>
					    <span class="caption">Param</span>
					</label>
				</div>
				<div class='cell colspan2'>
					<label class="input-control radio">
					    <input type="radio" name="typesearch" value="Example">
					    <span class="check"></span>
					    <span class="caption">Example</span>
					</label>
				</div>
				
				<div class='cell colspan2'>
					<label class="input-control radio">
					    <input type="radio" name="typesearch" value="Criteria">
					    <span class="check"></span>
					    <span class="caption">Criteria</span>
					</label>
				</div>
			</div>
				            
			<div class="container">
	 
			  	<label><b>Numero Siret</b></label>
			    <input type="text" placeholder="Enter numero siret" name="numSiret" />
			    <br>
			    <label><b>Fist Name</b></label>
			    <input type="text" placeholder="Enter first name" name="firstName" />
			    <br>
			    <label><b>Last Name</b></label>
			    <input type="text" placeholder="Enter last name" name="lastName" />
			        <br>
			    
			    <label><b>Email</b></label>
			    <input type="text" placeholder="Enter email" name="email" />
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
						    <legend>Mobile Phones</legend>
				 		        
						        <label><b>Phone Kind</b></label>
						        <input type="text" placeholder="Enter Mobile Phone kind" name="phoneMobileKind"   />  <br>
						        
						        <label><b>Phone Number</b></label>
						        <input type="text" placeholder="Enter Mobile Phone number" name="phoneMobileNumber"   />  <br>
						        
				        </fieldset>
				        <fieldset>
						    <legend>House Phones</legend>
				 		        
						        <label><b>Phone Kind</b></label>
						        <input type="text" placeholder="Enter House Phone kind" name="phoneHouseKind"   />  <br>
						        
						        <label><b>Phone Number</b></label>
						        <input type="text" placeholder="Enter House Phone number" name="phoneHouseNumber"   />  <br>
						        
				        </fieldset>
				        <fieldset>
						    <legend>Office Phones</legend>
				 		        
						        <label><b>Phone Kind</b></label>
						        <input type="text" placeholder="Enter Office Phone kind" name="phoneOfficeKind"   />  <br>
						        
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
	    
			    <input type="submit" value="Rechercher le contact"/>     
			    <input type="reset" value="Annuler"/> 
			</div>
                  
      </form>
   </body>
</html>




