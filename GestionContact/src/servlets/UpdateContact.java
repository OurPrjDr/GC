package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoContact;

public class UpdateContact extends HttpServlet {

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException{
       
        PrintWriter out = response.getWriter();
       
        long id = 0;

        String nouvPrenom= request.getParameter("nouvPrenom");
        String nouvNom = request.getParameter("nouvNom");
        String nouvEmail = request.getParameter("nouvEmail");
        DaoContact dao = new DaoContact();
       
        out.println("<html><body>");
        try {
            id = Long.parseLong(request.getParameter("id"));
            dao.updateContact(id, nouvPrenom, nouvNom, nouvEmail);
            out.println("On met à  jour le contact d'id "+
            id+" dans la base de donnees JDBC\n");   



        } catch (Exception e) {
            out.println("Id invalide !");   
        }
        out.println("</body></html>");
    }

}