package Servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import DAO.interfaces.IDaoAddress;
import DAO.interfaces.IDaoContact;
import DAO.interfaces.IDaoContactGroup;
import DAO.interfaces.IDaoEntreprise;
import DAO.interfaces.IDaoPhoneNumber;
import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;
import Services.AddressService;
import Services.ContactGroupService;
import Services.ContactService;
import Services.EntrepriseService;
import Services.PhoneNumberService;

/**
 * Servlet implementation class NewContact
 */
public class NewContact extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8276414383412909812L;

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
	        String numSiret = request.getParameter("numSiret");
	        
	        String street = request.getParameter("street");
	        String city = request.getParameter("city");
	        String zip = request.getParameter("zip");
	        String country = request.getParameter("country");
	        
	        String new_group = request.getParameter("new_group");
	        	
         
	        boolean okFirstName = firstName!=null && firstName.length()>0;
			boolean okLastName = lastName!=null && lastName.length()>0;
			boolean okEmail = email!=null && email.length()>5 && email.contains("@") && email.contains(".");
	        
			boolean okStreet = street!=null && street.length()>0;
			boolean okZip = zip!=null && zip.length()>0;
			boolean okCity = city!=null && city.length()>0;
			boolean okCountry = country!=null && country.length()>0;
			
 
			

			Account account = (Account) request.getSession().getAttribute("account");
			
			ApplicationContext context =
		                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		        
			if(okFirstName && okLastName && okEmail && okStreet && okZip && okCity && okCountry){
				
				System.out.println("Address");
				AddressService addressService = (AddressService) context.getBean("addressService");

	        	Address address = addressService.createAddress(street, city, zip, country);
		        	
		        ContactService contactService = (ContactService) context.getBean("contactService");

		        
		        Contact c = null;
		        if (numSiret != null && (!numSiret.equals(""))) {
		        	System.out.println("numSiret");
		            EntrepriseService entrepriseService = (EntrepriseService) context.getBean("entrepriseService");;

		        	c = entrepriseService.createEntreprise(firstName, lastName, email, address, Long.parseLong(numSiret));
		        }else{
		        	c = contactService.createContact(firstName, lastName, email, address);
		        }
		        
		        System.out.println("Address");
		        PhoneNumberService phoneNumberService = (PhoneNumberService) context.getBean("phoneNumberService");
		        
		        String phone = "";
		        Set<PhoneNumber> phones =new HashSet<PhoneNumber>();
	        	String[] nom = {"Mobile","House","Office"}; 
	        	System.out.println("PhoneNumber");
		        for(int i = 0; i < nom.length; i++) {
		        	if( !((phone = request.getParameter("phone"+nom[i]+"Number")).equals(""))) {
			        	
		        		PhoneNumber p = phoneNumberService.createPhoneNumber(nom[i], phone, c);
		 		        phones.add(p);
			        }
		        }  
		        
		        System.out.println("ContactGroup");
		        ContactGroupService contactGroupService = (ContactGroupService) context.getBean("contactGroupService");

		        String groupes[] = request.getParameterValues("groupes");
		        
		        ContactGroup cg = null;
		        if (groupes != null) {
		            for (int i = 0; i < groupes.length; i++) {
		            	cg = contactGroupService.createContactGroup(groupes[i]);
		            	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
		            }
		        }
 
 				boolean okgroup = new_group!=null && new_group.length()>0;
				if(okgroup) {
					cg = contactGroupService.createContactGroup(new_group);
	            	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
				}
				
				/* 
			     * Exemples ajoutés grace aux beans                   
			     */
			 
				//daoContact = (IDaoContact) context.getBean("DaoContact");
		        //contactService = new ContactService(daoContact);
		        //contactService.createContact((Contact) ctx.getBean("idContact1"));
		        
				//daoContact = (IDaoContact) ctx.getBean("DaoContact");
		        //contactService = new ContactService(daoContact);
		        //contactService.createContact((Contact) ctx.getBean("idEntreprise1"));
		        
		     
		        /*
		         * Fin Exemple 
		         */
		        
		        //request.setAttribute("success", true);
				//request.setAttribute("message", "Contact created !");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil.jsp");
				dispatcher.forward(request, response);
	        
			}else{
				
				
				//request.setAttribute("success", false);
				//request.setAttribute("message", "Error with one field...");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/addContact.jsp");
				dispatcher.forward(request, response);
				
			}

	     
	        
			
		} catch (Exception e) {
			System.out.println(e);
		}
 
	}

}
