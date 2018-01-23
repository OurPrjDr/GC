<%@ page errorPage="/error.jsp" language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,DAO.DaoEntreprise,Domains.Contact,Domains.PhoneNumber,Domains.ContactGroup,Domains.Entreprise,Services.ContactService"%>  
<%@page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

<script src='./design/js/bootstrap.js'></script>
<link href='./design/css/bootstrap.min.css' rel='stylesheet'></head>
    </head>
    <%
    
    	String msg = (String)request.getAttribute("update");
    	if(msg == null){%>
    		<h1>Resgitre...</h1>
    	<%}else{%>
    		<h1><%=msg %></h1>
    	<%} %>
    
    <%
    	Long idContact = Long.parseLong(request.getParameter("idContact"));
    	DaoContact dao = new DaoContact();
    	ContactService contactService = new ContactService(dao);
		Contact contact = contactService.getContactById(idContact); 

		Set<PhoneNumber> phones = contact.getPhones(); 
		Iterator it = phones.iterator();
		
		String telMobile = "";
		String telMaison = "";
		String telBureau = "";
		
		
		while(it.hasNext()) { 
			PhoneNumber p = (PhoneNumber)it.next();
			if(p.getPhoneKind().equals("Mobile")){
				telMobile = p.getPhoneNumber();
			}
			if(p.getPhoneKind().equals("House")){
				telMaison = p.getPhoneNumber();
			}
			if(p.getPhoneKind().equals("Office")){
				telBureau = p.getPhoneNumber();
			}
		}
		
		Boolean amis = false;
		Boolean collegues = false;
		Boolean  famille = false;
		
		Set<ContactGroup> groups = contact.getBooks(); 
		Iterator itp = groups.iterator();
		

    %>
    <body>
    <div class="container">
             <h3 class="text-on-pannel text-primary"><strong class="text-uppercase"> Update Contact </strong></h3>
    
        <form action="UpdateContact" method="post" >
        	Id du contact à modifier: 
        	<input class="form-control" type="text" name="idContact" value=<%=contact.getIdContact()%> readonly /><br /> 
            Num Siret: <input  class="form-control"  type="text" name="numSiret"  readonly/><br /> 
            FirstName: <input  class="form-control"  type="text" name="prenom" value=<%=contact.getFirstName() %> /><br /> 
            LastName: <input   class="form-control"  type="text" name="nom" value=<%=contact.getLastName() %> /><br /> 
            Email: <input  class="form-control"  type="text" name="email" value=<%=contact.getEmail() %> /><br />
			<fieldset>
    		<legend>Address</legend>
			Street: <input  class="form-control"  type="text" name="rue" value=<%=contact.getAddress().getStreet() %> ><br /> 
			City: <input  class="form-control"  type="text" name="ville" value=<%=contact.getAddress().getCity() %> ><br /> 
			Zip: <input  class="form-control"  type="text" name="code" value=<%=contact.getAddress().getZip() %> ><br /> 
			
			Country: <input  class="form-control"  type="text" name="pays" value=<%=contact.getAddress().getCountry() %> ><br /> 
			</fieldset>
			
			<fieldset>
    		<legend>Phones</legend>	
			Mobile Phone: <input  class="form-control"  type="text" name="telMobile" value=<%=telMobile %> ><br /> 
		
			House Phone: <input  class="form-control"  type="text" name="telMaison" value=<%=telMaison %> ><br /> 
		
			Office Phone: <input  class="form-control"  type="text" name="telBureau" value=<%=telBureau %> ><br /> 
			</fieldset>
			
			<%  while(itp.hasNext()) {  
				ContactGroup g = (ContactGroup) itp.next();
			%>
			 	<%=g.getGroupName() %>: <input type="checkbox" name="<%=g.getGroupName()%>" value="<%=g.getGroupName()%>" checked="checked" /><br />
		   
			<% } %>
			 
			<a href="#new" class="btn" onclick="addGroup()">Autre Groupe</a>
			
			<div class="form-group" id="new" style="display:none">
	  			<input  class="form-control"  type="text" placeholder="Enter new groupe" name="new_group"   /> 
	  		</div> 
			 
		
		<script type='text/javascript'>
			 
			function addGroup() {
			    var x = document.getElementById("new");
			    if (x.style.display === "none") {
			        x.style.display = "block";
			    } else {
			        x.style.display = "none";
			    }
			} 
		</script>
		
		 <div class="form-group"> 
			<input class="btn btn-primary" type="submit" value="Mettre-a-jour le contact"/>
			 <a href="accueil.jsp"><input  class="btn btn-secondary" type="reset" value="Annuler"/></a>
		</div>
         </form>
         </div>
         
		<!--  <form action="NewContactGroup" method="post" > 
			  </form>
		-->
		
		 
		
            	
      
        
    </body>
</html>