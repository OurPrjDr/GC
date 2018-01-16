package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoContact;
import domains.PhoneNumber;

/**
 * Servlet implementation class NewContact
 */
public class NewContact extends HttpServlet {
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public NewContact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");

	        String phoneKind = "";
	        String phoneNumber = "";

	        
	        Set<PhoneNumber> phone =new HashSet<PhoneNumber>();

        	String[] nom = {"Mobile","House","Office"}; 

	        for(int i = 0; i < nom.length; i++) {
	        	if( ( !(phoneKind = request.getParameter("phone"+nom[i]+"Kind")).equals("")) && !((phoneNumber = request.getParameter("phone"+nom[i]+"Number")).equals(""))) {
		        	PhoneNumber p = new PhoneNumber(phoneKind,phoneNumber);
	 		        phone.add(p);
		        }
	        }    
	        DaoContact dao = new DaoContact();
	        
	        dao.addContact(firstName, lastName, email, phone);
	        
		} catch (Exception e) {
			System.out.println(e);
		}
 
	}

}
