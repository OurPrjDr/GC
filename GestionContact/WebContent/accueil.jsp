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
 </ul> 
</body>

<%@page import="dao.DaoContact,domains.Contact,java.util.List"%>  


<%  
	DaoContact dao = new DaoContact();
	List<Contact> contactList = dao.getAllContacts();  
%>  



<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>FisrtName</th>
          <th>LastName</th>
          <th>Email</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
        
       
       <tr>
	       <% for(int i= 0; i < contactList.size();i++ )  { %>
	          <td><%=contactList.get(i).getFirstName() %></td>
	          <td><%=contactList.get(i).getLastName() %></td>
	          <td><%=contactList.get(i).getEmail() %></td>
	          <td>
	           </td>
	          <td>
	           </td>
	       <% } %>
	              
      </tr>
          
</table>

    
</html>