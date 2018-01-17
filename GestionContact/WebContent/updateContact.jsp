<%@ page language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,Domains.Contact,Domains.PhoneNumber,Domains.ContactGroup"%>  
<%@page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Mise-a-jour Contact</title>
    </head>
    <%
    	Long idContact = Long.parseLong(request.getParameter("idContact"));
    	DaoContact dao = new DaoContact();
		Contact contact = dao.searchParId(idContact); 
		
		Set<PhoneNumber> phones = contact.getPhones(); 
		Iterator it = phones.iterator();
		
		Set<ContactGroup> groups = contact.getBooks(); 
		out.println("---------->>>>"+groups.size());
		Iterator itp = groups.iterator();
		
		
    %>
    <body>
        <form method="post" action="UpdateContact">
        	Id du contact à modifier: <input type="text" name="idContact" value=<%=contact.getIdContact()%> readonly /><br /> 
            Num Siret: <input type="text" name="numSiret" value=<%=contact.getFirstName()%> /><br /> 
            FirstName: <input type="text" name="prenom" value=<%=contact.getFirstName() %> /><br /> 
            LastName: <input type="text" name="nom" value=<%=contact.getLastName() %> /><br /> 
            Email: <input type="text" name="email" value=<%=contact.getEmail() %> /><br />
			Street: <input type="text" name="rue" value=<%=contact.getAddress().getStreet() %> ><br /> 
			City: <input type="text" name="ville" value=<%=contact.getAddress().getCity() %> ><br /> 
			Zip: <input type="text" name="code" value=<%=contact.getAddress().getZip() %> ><br /> 
			
			Country: <input type="text" name="pays" value=<%=contact.getAddress().getCountry() %> ><br /> 
			<%if (phones.size()!=0){ %>
				<% while(it.hasNext()) { PhoneNumber p = (PhoneNumber)it.next(); %>
					<%if(p.getPhoneKind().equals("Mobile")){ %>
						Mobile Phone: <input type="text" name="telMobile" value=<%=p.getPhoneNumber() %> ><br /> 
					<%} %>
					<%if(p.getPhoneKind().equals("House")){ %>
						House Phone: <input type="text" name="telMaison" value=<%=p.getPhoneNumber() %> ><br /> 
					<%} %>
					<%if(p.getPhoneKind().equals("Office")){ %>
						Office Phone: <input type="text" name="telBureau" value=<%=p.getPhoneNumber() %> ><br /> 
					<%} %>
				<%} %>
			<% }else{ %>
			
				Mobile Phone: <input type="text" name="telMobile" /><br /> 
				House Phone: <input type="text" name="telMaison" /><br /> 
				Office Phone: <input type="text" name="telBureau" /><br /> 
				
			<% }%>	
			
			
			<% while(itp.hasNext()) { ContactGroup g = (ContactGroup)itp.next(); %>
				<%if(g.getGroupName().equals("Amis")){ %>
					Friends: <input type="checkbox" name="amis" value="amis" checked> />Amis<br /> 
				<%}else{ %>
					Friends: <input type="checkbox" name="amis" value="amis" > Amis<br /> 
				<% }%>
					
				<%if(g.getGroupName().equals("Collegues")){ %>
					Colleagues: <input type="checkbox" name="collegues" value="collegues" checked >Collegues<br />
				<% }else{%>
					Colleagues: <input type="checkbox" name="collegues" value="collegues"  >Collegues<br />
				<%} %>
				<%if(g.getGroupName().equals("Famille")){ %>
					Family: <input type="checkbox" name="famille" value="famille" checked >Famille<br />
				<% } else{%>	
					Family: <input type="checkbox" name="famille" value="famille"  >Famille<br />
					
				<% } %>
			<% } %>	
			<input type="button" name="New group" value="nouveauGroupe" /><br />
		      
            <input type="submit" value="Mettre-a-jour le contact"/>
            <input type="reset" value="Annuler"/>
        </form>
    </body>
</html>