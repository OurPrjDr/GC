<%@ page errorPage="/error.jsp" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<html>
    <head>
        <title>Recherche Contact</title>
        <script src='./design/js/bootstrap.js'></script>
		<link href='./design/css/bootstrap.min.css' rel='stylesheet'>
    </head>
    <body>
    
    
	<%@page import="java.util.Set"%>
	<%@page import="java.util.HashSet"%>
    
    <%
    
    if(request.getParameter("message")!=null) {
    		String msg = request.getParameter("message");%>				
    	<div class="btn btn-danger btn-block"><%=msg %></div> 
    	
    	<%}%>
<%
    Set<String> groupes = new HashSet<String>();
 	%>
        <form method="post" action="SearchContact">
        	<div class="container">
        	 
				<div  >
					<h3>Type de recherche HQL</h3>
				</div>
			 

		 	
				 
					<label class="btn btn-primary active">
					    <input  type="radio" name="typesearch" value="Simple">
					    <span class="check"></span>
					    <span class="caption">Simple</span>
					</label>
				 
				 
					<label class="btn btn-primary active">
					    <input   type="radio" name="typesearch" value="Param">
					    <span class="check"></span>
					    <span class="caption">Param</span>
					</label>
				 
				 
					<label class="btn btn-primary active">
					    <input   type="radio" name="typesearch" value="Example">
					    <span class="check"></span>
					    <span class="caption">Example</span>
					</label>
				 
				
				 
					<label class="btn btn-primary active">
					    <input  type="radio" name="typesearch" value="Criteria">
					    <span class="check"></span>
					    <span class="caption">Criteria</span>
					</label>
				 <br/>
			 
				            
		
	 
			  	<label><b>Numero Siret</b></label>
			    <input  class="form-control" type="text" placeholder="Enter numero siret" name="numSiret" />
			    <br>
			    <label><b>First Name</b></label>
			    <input  class="form-control"  type="text" placeholder="Enter first name" name="firstName" />
			    <br>
			    <label><b>Last Name</b></label>
			    <input  class="form-control" type="text" placeholder="Enter last name" name="lastName" />
			        <br>
			    
			    <label><b>Email</b></label>
			    <input  class="form-control" type="text" placeholder="Enter email" name="email" />
			        <br>
			
				<fieldset>
			    	<legend>Groupes</legend>
			    	
					<select  class="form-control" id='groupesSelect' name='groupes' multiple='multiple'
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
	    
			    <input class="btn btn-primary" type="submit" value="Rechercher le contact"/>     
			    <input class="btn btn-secondary" type="reset" value="Annuler"/> 
			</div>
                  
      </form>
   </body>
</html>




