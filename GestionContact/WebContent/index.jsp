<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
</head>

<body>

<%
String login = request.getParameter("login");
String password = request.getParameter("password");
login = login==null?"":login;
password = password==null?"":password;

if(login.equals(password) && (!login.equals("")) ) {
	response.sendRedirect("accueil.jsp");
    //out.println("Hello Mr. User "+login);   
} else {
%>
   
<form method="post" action="index.jsp">
    <table>
        <th>
            Voici un premier exemple
        </th>
        <tr><td><input type="text" name="login"/></td></tr>
        <tr><td><input type="password" name="password"/></td></tr>
        <tr>
          <td colspan="2">
           <input type="submit" value="submit"/>
           <input type="reset" value="Reset"/>
          </td>
       </tr>
    </table>       
</form>

<%
}
%>


</body>

</html>