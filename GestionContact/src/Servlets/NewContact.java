package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoContact;
import Domains.Address;
import Domains.ContactGroup;
import Domains.PhoneNumber;

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
	        
	        String street = request.getParameter("street");
	        String city = request.getParameter("city");
	        String zip = request.getParameter("zip");
	        String country = request.getParameter("country");

	        Address address = new Address(street, city, zip, country);
	        
	        String phoneKind = "";
	        String phoneNumber = "";

	        
	        Set<PhoneNumber> phones =new HashSet<PhoneNumber>();

        	String[] nom = {"Mobile","House","Office"}; 

	        for(int i = 0; i < nom.length; i++) {
	        	if( ( !(phoneKind = request.getParameter("phone"+nom[i]+"Kind")).equals("")) && !((phoneNumber = request.getParameter("phone"+nom[i]+"Number")).equals(""))) {
		        	PhoneNumber p = new PhoneNumber(phoneKind,phoneNumber);
	 		        phones.add(p);
		        }
	        }    
	        
	        String groupes[] = request.getParameterValues("groupes");
	        
	        Set<ContactGroup> cgroupe = new HashSet<ContactGroup>();

	        if (groupes != null) {
	            for (int i = 0; i < groupes.length; i++) {
	                cgroupe.add(new ContactGroup(groupes[i], null));
	            }
	        }
	        
	        String numSiret = request.getParameter("numSiret");
	        
	        long nsiret = -1;
	        if (numSiret != null && (!numSiret.equals(""))) {
	            try {
	                nsiret = Long.parseLong(numSiret);
	            } catch (Exception e) {
	                nsiret = 0;
	            }
	        }
	        
	        DaoContact dao = new DaoContact();
	        
	        dao.addContact(firstName, lastName, email, address, phones, cgroupe, nsiret);
	        
			PrintWriter out = response.getWriter();
	        
	        response.setContentType( "text/html" );
	        out.println( "<html><body>" );
	        response.sendRedirect("accueil.jsp");
	        out.println("</body></html>"); 

		} catch (Exception e) {
			System.out.println(e);
		}
 
	}

}
