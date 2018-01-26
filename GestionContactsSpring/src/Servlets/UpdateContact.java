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

import DAO.interfaces.IDaoAddress;
import DAO.interfaces.IDaoContact;
import DAO.interfaces.IDaoContactGroup;
import DAO.interfaces.IDaoEntreprise;
import DAO.interfaces.IDaoPhoneNumber;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;
import Services.AddressService;
import Services.ContactGroupService;
import Services.ContactService;
import Services.EntrepriseService;
import Services.PhoneNumberService;

public class UpdateContact extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4511813501459933892L;

	public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException{
       
    	String numSiret = request.getParameter("numSiret");
    	
    	String idC = request.getParameter("idContact");
        String lastName = request.getParameter("nom");
        String firstName = request.getParameter("prenom");
        String email = request.getParameter("email");

        String rue = request.getParameter("rue");
        String ville = request.getParameter("ville");
        String code = request.getParameter("code");
        String pays = request.getParameter("pays");

        String mobile = request.getParameter("telMobile");
        String maison = request.getParameter("telMaison");
        String bureau = request.getParameter("telBureau");
        
        String amis = request.getParameter("amis");
        String collegues = request.getParameter("collegues");
        String famille = request.getParameter("famille");
        
        String new_group = request.getParameter("new_group");


        //update address
        
        ApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        
		AddressService addressService = (AddressService) context.getBean("addressService");

    	addressService.updateAddress(Long.parseLong(idC), rue, ville, code, pays);
    	Address a = addressService.getAddressById(Long.parseLong(idC));
        
    	//update contact || entreprise
        ContactService contactService = (ContactService) context.getBean("contactService");

        Contact c = contactService.getContactById(Long.parseLong(idC));

        if (numSiret != null && (!numSiret.equals(""))) {
            EntrepriseService entrepriseService = (EntrepriseService) context.getBean("entrepriseService");;

            entrepriseService.updateEntreprise(Long.parseLong(idC), firstName, lastName, email,a , Long.parseLong(numSiret));
        }
        else{
        	contactService.updateContact(Long.parseLong(idC), firstName, lastName, email, a);
        }
        
        //update phoneNumbre
        Set<PhoneNumber> phones = c.getPhones();
        Iterator it = phones.iterator();
        
        Boolean okMobile = false;
        Boolean okHouse = false;
        Boolean okOffice = false;

        PhoneNumberService phoneNumberService = (PhoneNumberService) context.getBean("phoneNumberService");

        
        while(it.hasNext()){
        	
        	PhoneNumber p = (PhoneNumber) it.next();
			Long id = p.getIdPhoneNumber();
        	if(p.getPhoneKind().equals("Mobile") && mobile!=null && !(mobile.equals(""))){
        		if(p.getIdPhoneNumber()!=Long.parseLong(mobile)){
        			okMobile = true;
        			p.setIdPhoneNumber(Long.parseLong(mobile));
        			phoneNumberService.updatePhoneNumber(id, p.getPhoneKind(), mobile, c);
					

        		}
        	}
        	if(p.getPhoneKind().equals("House") && maison!=null && !(maison.equals(""))){
        		if(p.getIdPhoneNumber()!=Long.parseLong(maison)){
        			okHouse = true;
        			p.setIdPhoneNumber(Long.parseLong(maison));
        			phoneNumberService.updatePhoneNumber(id, p.getPhoneKind(), maison, c);
        		}
        	}
        	if(p.getPhoneKind().equals("Office") && bureau!=null && !(bureau.equals(""))){
        		if(p.getIdPhoneNumber()!=Long.parseLong(bureau)){
        			okOffice = true;
        			p.setIdPhoneNumber(Long.parseLong(bureau));
        			phoneNumberService.updatePhoneNumber(id, p.getPhoneKind(), bureau, c);
        		}
        	}
        }
        if(!(okMobile) && (mobile!=null)){
        	phoneNumberService.createPhoneNumber("Mobile", mobile, c);
        }
        if(!(okHouse) && (maison!=null)){
        	phoneNumberService.createPhoneNumber("House", maison, c);
        }
        if(!(okOffice) && (bureau!=null)){
        	phoneNumberService.createPhoneNumber("Office", bureau, c);
        }
        
        //ContactGroup update
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
		
        Set<ContactGroup> cgroupe = c.getBooks();
        Iterator itg =cgroupe.iterator();
        
        ContactGroupService contactGroupService = (ContactGroupService) context.getBean("contactGroupService");

        ContactGroup cg = null;
        
        if(cgroupe.size() == 0){
        	if(amis!=null){
        		cg = contactGroupService.createContactGroup(amis);
		    	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
        	}
        	if(collegues!=null){
        		cg = contactGroupService.createContactGroup(collegues);
		    	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
        	}
        	if(famille!=null){
        		cg = contactGroupService.createContactGroup(famille);
		    	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
        	}
        }
        

        while(itg.hasNext()){	
        	ContactGroup g = (ContactGroup) itg.next();
        	Long id = g.getIdContactGroup();
        	contactGroupService.updateContactGroup(id, type);
        	
     	}  
        
 		if(new_group!=null && new_group.length()>0) {
			cg = contactGroupService.createContactGroup(new_group);
        	contactService.addContactInGroup(c.getIdContact(), cg.getIdContactGroup());
		}
        request.setAttribute("update", "Contact updated !");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/successUpdate.jsp");
		dispatcher.forward(request, response);
    }

}