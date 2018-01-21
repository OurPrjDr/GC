<%@ page language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,DAO.DaoEntreprise,Domains.Contact,Domains.PhoneNumber,Domains.ContactGroup,Domains.Entreprise,Services.ContactService"%>  
<%@page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Mise-a-jour Contact</title>
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
		while(itp.hasNext()) { 
			ContactGroup g = (ContactGroup) itp.next();
			if(g.getGroupName().equals("Amis")){
				amis = true;
			}
			if(g.getGroupName().equals("Collegues")){
				collegues = true;
			}
			if(g.getGroupName().equals("Famille")){
				famille = true;
			}
		}

    %>
    <body>
        <form action="UpdateContact" method="post" >
        	Id du contact à modifier: <input type="text" name="idContact" value=<%=contact.getIdContact()%> readonly /><br /> 
            Num Siret: <input type="text" name="numSiret"  /><br /> 
            FirstName: <input type="text" name="prenom" value=<%=contact.getFirstName() %> /><br /> 
            LastName: <input type="text" name="nom" value=<%=contact.getLastName() %> /><br /> 
            Email: <input type="text" name="email" value=<%=contact.getEmail() %> /><br />
			Street: <input type="text" name="rue" value=<%=contact.getAddress().getStreet() %> ><br /> 
			City: <input type="text" name="ville" value=<%=contact.getAddress().getCity() %> ><br /> 
			Zip: <input type="text" name="code" value=<%=contact.getAddress().getZip() %> ><br /> 
			
			Country: <input type="text" name="pays" value=<%=contact.getAddress().getCountry() %> ><br /> 
			
					
			Mobile Phone: <input type="text" name="telMobile" value=<%=telMobile %> ><br /> 
		
			House Phone: <input type="text" name="telMaison" value=<%=telMaison %> ><br /> 
		
			Office Phone: <input type="text" name="telBureau" value=<%=telBureau %> ><br /> 
			
			<%if (amis){ %>
				Friends: <input type="checkbox" name="amis" value="Amis" checked="checked" /><br />
			<%}else{ %>
				Friends: <input type="checkbox" name="amis" value="Amis" ><br /> 
			<%} %>
			<%if (collegues){ %>
				Colleagues: <input type="checkbox" name="collegues" value="Collegues" checked="checked" /><br />
			<%}else{ %>
				Colleagues: <input type="checkbox" name="collegues" value="Collegues"  /><br />
			<%} %>
			<%if (famille){ %>
				Family: <input type="checkbox" name="famille" value="Famille" checked="checked" /><br />
			<%}else{ %>
				Family: <input type="checkbox" name="famille" value="Famille" /><br />
			<%} %>
				
			
			<input type="submit" value="Mettre-a-jour le contact"/>
         </form>
         
		<!--  <form action="NewContactGroup" method="post" > 
			  </form>
		-->
		
		<input type="button" name="New group" value="nouveauGroupe" />
		
		
		
            	
       <a href="accueil.jsp"><input type="reset" value="Annuler"/></a>

        
    </body>
</html>