<%@ page language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,Domains.Contact,java.util.List,Services.ContactService,Domains.PhoneNumber"%>  
<%@page import="java.util.Set"%>
<%@page import="Domains.Entreprise"%>
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
  <!-- <li><a href="updateContact.jsp">Editer un contact</a></li>-->
  <!-- <li><a href="removeContact.jsp">Supprimer contact</a></li>-->
  
 </ul> 
</body>
<%  
	DaoContact daoContact = new DaoContact();
	ContactService contactService = new ContactService(daoContact);
	Set<Contact> contactList = contactService.getAllContacts(); 
	Iterator it = contactList.iterator();
	
	
	
%>  
<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
       	  <th>Id</th>
          <th>FisrtName</th>
          <th>LastName</th>
          <th>Email</th>
          <th>Street</th>
          <th>City</th>
          <th>Zip</th>
          <th>Country</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
        
       
       
	   <%  while(it.hasNext()){
		   Contact c = (Contact)it.next();%>
	         <tr> 
	          <td><%=c.getIdContact() %></td>
	          <td><%=c.getFirstName() %></td>
	          <td><%=c.getLastName() %></td>
	          <td><%=c.getEmail() %></td>
	          <td><%=c.getAddress().getStreet() %></td>
	          <td><%=c.getAddress().getCity() %></td>
	          <td><%=c.getAddress().getZip() %></td>
	          <td><%=c.getAddress().getStreet() %></td>
	         
	          <td>
	          	  <a href="updateContact.jsp?idContact=<%=c.getIdContact() %>" ><input type="button" name="Edit" id="Edit" value="Edit" /></a>
	          	
	          </td>
	          <td>
	          	  <a href="removeContact.jsp?idContact=<%=c.getIdContact() %>"><input type="button" name="Delete" id="Deletet" value="Delete" /></a>
			  </td>
			 
           <tr>
	    <% } %>
	                        
</table>


</head>
</html>

