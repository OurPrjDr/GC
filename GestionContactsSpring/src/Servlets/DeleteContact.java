package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import DAO.interfaces.IDaoContact;
import Services.ContactService;

/**
 * Servlet implementation class DeleteContact
 */
public class DeleteContact extends HttpServlet {
        
	/**
	 * 
	 */
	private static final long serialVersionUID = -929100826134971219L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
				
		try {
	       
	        long idContact = Long.parseLong(request.getParameter("idContact"));

	        ApplicationContext ctx =
	                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		    IDaoContact daoContact = (IDaoContact) ctx.getBean("DaoContact");
	        ContactService contactService = new ContactService(daoContact);
	        contactService.deleteContact(idContact);
	        	       
	        response.sendRedirect("accueil.jsp");
	            
	     
		} catch (Exception e) {
			System.out.println(e);
		}
 
	}

}
