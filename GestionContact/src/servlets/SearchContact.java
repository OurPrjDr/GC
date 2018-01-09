package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoContact;

public class SearchContact extends HttpServlet {

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException{
       
        PrintWriter out = response.getWriter();
       
        long id = 0;
        out.println("<html><body>");
        try {
            id = Long.parseLong(request.getParameter("id"));
            DaoContact dao = new DaoContact();
            dao.searchContact(id);
           
            out.println("On cherche le contact d'id "+id+
                    " dans la base de donnees JDBC\n");   
        } catch (Exception e) {
            out.println("Id invalide !");   
        }
        out.println("</body></html>");
    }
}