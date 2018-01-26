<%@ page errorPage="/error.jsp" language="java" import="java.util.Iterator" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="DAO.DaoContact,DAO.DaoEntreprise,Domains.Contact,java.util.List,Services.ContactService,Domains.PhoneNumber,Domains.Account"%>  
<%@page import="Services.EntrepriseService"%>
<%@page import="java.util.Set"%>
<%@page import="Domains.Entreprise"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
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
	    						<a>Bienvenue, <b><font color="red"><%=account.getLogin() %></font></b></a>
	    						<a href="<%=path %>/Servlets/SignOut"><font color="#CDC9C9">Déconnexion</font></a>
	    						
	    						
	    						 -->
   							</dt>
   						</dl>
    			</li>
    		</ul>
    		
</body>
<%  
    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    ContactService contactService = (ContactService) context.getBean("contactService");
	Set<Contact> contactList = contactService.getAllContacts(); 
	
    EntrepriseService entrepriseService = (EntrepriseService) context.getBean("entrepriseService");;

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
          <th>Prénom</th>
          <th>Nom</th>
          <th>Email</th>
          <th>Rue</th>
          <th>Ville</th>
          <th>Code postal</th>
          <th>Pays</th>
          <th>Editer</th>
          <th>Supprimer</th>
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
	          	  <a href="updateContact.jsp?idContact=<%=c.getIdContact() %>" ><input class="btn btn-primary" type="button" name="Edit" id="Edit" value="Edit" /></a>
	          	
	          </td>
	          <td>
	          	  <a href="removeContact.jsp?idContact=<%=c.getIdContact() %>"><input class="btn btn-danger" type="button" name="Delete" id="Deletet" value="Delete" /></a>
			  </td>
			 
           <tr>
	    <% } %>
	                        
</table>


</head>
</html>

