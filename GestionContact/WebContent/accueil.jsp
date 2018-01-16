<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <ul>
  <li><a href="addContact.jsp">Créer un nouveau contact</a></li>
  <li><a href="searchContact.jsp">Chercher un contact</a></li>
  <li><a href="updateContact.jsp">Editer un contact</a></li>
  <li><a href="removeContact.jsp">Supprimer contact</a></li>
  
 </ul> 
</body>

<%@page import="DAO.DaoContact,Domains.Contact,java.util.List"%>  
<%@page import="java.util.Set"%>
<%@page import="Domains.Entreprise"%>

<% 
/*DaoContact dao = new DaoContact();
	Set<Contact> contactSet = dao.getAllContacts();  
	
	if (contactSet.size() == 0) {
        out.println("<h3> Votre liste de contacts est vide </h3>");
    } else {*/
%>  

<!-- <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>FisrtName</th>
          <th>LastName</th>
          <th>Email</th>
          <th>Numero Siret</th>th>
          <th>Fiche du contact</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>-->
        
       <%
	   /* for (Contact c : contactSet) {
	            out.println("<tr>"
	                    + "<td>"
	                    + c.getFirstName()
	                    + "</td>"
	                    + "<td>"
	                    + c.getLastName()
	                    + "</td>"
	                    + "<td>"
	                    + c.getEmail()
	                    + "</td>"
	                    + "<td>"
	                    + ((c instanceof Entreprise) ? ((Entreprise) c).getNumSiret() : "") 
	                    + "</td>"
	                    + "<td><a href='search.jsp?id=" + c.getIdContact() +"'/>"
	     	            + "</td>"
	                    + "<td><a href='update.jsp?id=" + c.getIdContact() +"'/>"
	                    + "</td>"
	                    + "<td><a href='delete.jsp?id=" + c.getIdContact() +"'/>"
	    	            + "</td>"
	                    + "</tr>");
	        }*/
	%>

</table> 
<%
/*    }*/
%> 
    
</html>