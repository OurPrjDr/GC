package Servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import DAO.interfaces.IDaoContact;
import DAO.interfaces.IDaoContactGroup;
import Domains.Contact;
import Domains.ContactGroup;
import Services.ContactGroupService;
import Services.ContactService;

/**
 * Servlet implementation class NewContactGroup
 */
public class NewContactGroup extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 917276677818727780L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public NewContactGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	String idC = request.getParameter("idContact");

		String amis = request.getParameter("amis");
		String collegues = request.getParameter("collegues");
		String famille = request.getParameter("famille");
		
		String temps = "";
		
		String type = "";
		if(amis!=null){
			type="Amis";
		}
		if(collegues!=null){
			type="Collegues";
		}
		if(famille!=null){
			type="Famille";
		}
		 
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ContactService contactService = (ContactService) context.getBean("contactService");

		Contact c = contactService.getContactById(Long.parseLong(idC));
		
        Set<ContactGroup> cgroupe = c.getBooks();
        Iterator itg =cgroupe.iterator();
        
        ContactGroupService contactGroupService = (ContactGroupService) context.getBean("contactGroupService");

        ContactGroup cg = null;
        
        if(cgroupe.size() == 0){
        	if(amis!=null){
        		temps = "ContactGroup [ AMIS ] Updated !";
        		cg = contactGroupService.createContactGroup(amis);
		    	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
        	}
        	if(collegues!=null){
        		temps = "ContactGroup [ COLLEGUES ] Updated !";
        		cg = contactGroupService.createContactGroup(collegues);
		    	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
        	}
        	if(famille!=null){
        		temps = "ContactGroup [ FAMILLE ] Updated !";
        		cg = contactGroupService.createContactGroup(famille);
		    	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
        	}
        }
        
      
        
        while(itg.hasNext()){	
        	ContactGroup g = (ContactGroup) itg.next();
        	Long id = g.getIdContactGroup();
        	if(type.equals("Amis")){
        		temps = "ContactGroup [ AMIS ] Updated !";
        	}
        	if(type.equals("Collegues")){
        		temps = "ContactGroup [ COLLEGUES ] Updated !";
        	}
        	if(type.equals("Famille")){
        		temps = "ContactGroup [ FAMILLE ] Updated !";
        	}
        	contactGroupService.updateContactGroup(id, type);
        	
     	}  
        request.setAttribute("update", temps);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateContact.jsp");
        dispatcher.forward(request, response);
	       
	    	
	            
		
	}

}
