<%@ page errorPage="/error.jsp" language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,DAO.DaoEntreprise,Domains.Contact,java.util.List,Services.ContactService,Domains.PhoneNumber,Domains.Account"%>  
<%@page import="Services.EntrepriseService"%>
<%@page import="java.util.Set"%>
<%@page import="Domains.Entreprise"%>
<%@ include file="navbar.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='./design/js/bootstrap.js'></script>
<link href='./design/css/bootstrap.min.css' rel='stylesheet'>

<title>Insert title here</title>
</head>
<body>
 <ul class="user">
    			<li>
    				<%
    				    String str = null;
    				    str = (String)request.getAttribute("account");
    				    Account account = (Account) request.getSession().getAttribute("account");
    				    
                        %>
	     				
   						<dl>
   							<dt>
	    						<a>Bienvenu,<b><font color="red"><%=account.getLogin() %></font></b></a>
	    						<a href="<%=path %>/Servlets/SignOut"><font color="#CDC9C9">Exit</font></a><!-- 绝对路径 -->
	    						
	    						<!-- 
	    						<a href="lyons/control/HandleExit"><font color="#CDC9C9">退出</font></a><!-- 相对路径 -->
	    						 -->
   							</dt>
   						</dl>
    			</li>
    		</ul>
    		
</body>
<%  
	DaoContact daoContact = new DaoContact();
	ContactService contactService = new ContactService(daoContact);
	Set<Contact> contactList = contactService.getAllContacts(); 
	
	DaoEntreprise daoEntreprise = new DaoEntreprise();
	EntrepriseService entrepriseService = new EntrepriseService(daoEntreprise);
	Set<Entreprise> entrepriseList = entrepriseService.getAllEntreprises();
	
	Iterator it = null;
	
	if(entrepriseList.size()!=0){
		it = entrepriseList.iterator();
	}else{
		it = contactList.iterator();
	}
	
	
	
	

%>
<table class="table table-hover" >
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
	          <td><% if (c.getAddress() != null) { %><%=c.getAddress().getStreet() %><% } %></td>
	          <td><% if (c.getAddress() != null) { %><%=c.getAddress().getCity() %><% } %></td>
	          <td><% if (c.getAddress() != null) { %><%=c.getAddress().getZip() %><% } %></td>
	          <td><% if (c.getAddress() != null) { %><%=c.getAddress().getCountry() %><% } %></td>
	         
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

