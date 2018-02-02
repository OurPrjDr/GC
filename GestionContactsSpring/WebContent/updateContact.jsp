<%@ page errorPage="/error.jsp" language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,DAO.DaoEntreprise,Domains.Contact,Domains.PhoneNumber,Domains.ContactGroup,Domains.Entreprise,Services.ContactService,Services.EntrepriseService"%>  
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

<script src='./design/js/bootstrap.js'></script>
<link href='./design/css/bootstrap.min.css' rel='stylesheet'></head>
    </head>
    
    
    <%
    	Long idContact = Long.parseLong(request.getParameter("idContact"));
    	String entreprise = request.getParameter("entreprise");
    	ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    	Contact contact = null;
    	Entreprise entre = null;
    	Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
    	if(entreprise.equals("true")){
    		
    	    EntrepriseService entrepriseService = (EntrepriseService) context.getBean("entrepriseService");
    	    entre = entrepriseService.getEntreprise(idContact);
    	    ContactService contactService = (ContactService) context.getBean("contactService");
        	contact = contactService.getContactById(idContact); 
    	    phones = entre.getPhones(); 
    	    
    	}else{
        	ContactService contactService = (ContactService) context.getBean("contactService");
        	contact = contactService.getContactById(idContact); 
        	phones = contact.getPhones(); 
    	}
    	
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
		Boolean famille = false;
		
		
		Set<ContactGroup> groups = contact.getBooks(); 
		
		Iterator itp = groups.iterator();
		

    %>
    <body>
   	 <div class="container">
             <h3 class="text-on-pannel text-primary"><strong class="text-uppercase"> Update Contact </strong></h3>
    
        <form action="UpdateContact" method="post" >
        	<input class="form-control" type="hidden" name="idContact" value=<%=contact.getIdContact()%> ><br /> 
        	<%if(entreprise.equals("true")) {%>
            	Num Siret: <input  class="form-control"  type="text" name="numSiret"  value=<%=entre.getNumSiret() %> readonly /><br /> 
           	<%}else{ %>
           		Num Siret: <input  class="form-control"  type="text" name="numSiret"  readonly /><br /> 
           	<%} %>
            FirstName: <input  class="form-control"  type="text" name="prenom" value=<%=contact.getFirstName() %> /><br /> 
            LastName: <input   class="form-control"  type="text" name="nom" value=<%=contact.getLastName() %> /><br /> 
            Email: <input  class="form-control"  type="text" name="email" value=<%=contact.getEmail() %> /><br />
			<fieldset>
    		<legend>Address</legend>
			Street: <input  class="form-control"  type="text" name="rue" value=<%=contact.getAddress().getStreet() %> ><br /> 
			City: <input  class="form-control"  type="text" name="ville" value=<%=contact.getAddress().getCity() %> ><br /> 
			Zip: <input  class="form-control"  type="number" name="code" value=<%=contact.getAddress().getZip() %> ><br /> 
			
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
			 
		</div>
         </form>
         </div>
          <div class="container">
         <a href="accueil.jsp"><input  class="btn btn-primary" type="button" value="Annuler"/></a>
         </div>
		<!--  <form action="NewContactGroup" method="post" > 
			  </form>
		-->
		
		
    </body>
</html>