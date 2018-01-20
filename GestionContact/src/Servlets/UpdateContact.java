package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoAddress;
import DAO.DaoContact;
import DAO.DaoContactGroup;
import DAO.DaoEntreprise;
import DAO.DaoPhoneNumber;
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
        


        String groupes[] = request.getParameterValues("groupes");

       
        
        DaoAddress daoAddress = new DaoAddress();
    	AddressService addressService = new AddressService(daoAddress);
    	addressService.updateAddress(Long.parseLong(idC), rue, ville, code, pays);
    	Address a = addressService.getAddressById(Long.parseLong(idC));
        
        DaoContact daoContact = new DaoContact();
        ContactService contactService = new ContactService(daoContact);

        
        if (numSiret != null && (!numSiret.equals(""))) {
        	DaoEntreprise daoEntreprise = new DaoEntreprise();
	           EntrepriseService entrepriseService = new EntrepriseService(daoEntreprise);
	           entrepriseService.updateEntreprise(Long.parseLong(idC), firstName, lastName, email,a , Long.parseLong(numSiret));
        }
        else{
        	contactService.updateContact(Long.parseLong(idC), firstName, lastName, email, a);
        }
        
        Contact c = contactService.getContactById(Long.parseLong(idC));
        
        Set<PhoneNumber> phones = c.getPhones();
        Iterator it = phones.iterator();
        
        DaoPhoneNumber daoPhoneNumber = new DaoPhoneNumber();
	    PhoneNumberService phoneNumberService = new PhoneNumberService(daoPhoneNumber);
        while(it.hasNext()){
        	PhoneNumber p = (PhoneNumber) it.next();
			Long id = p.getIdPhoneNumber();

        	if(p.getPhoneKind().equals("Mobile")){
        		if(p.getIdPhoneNumber()!=Long.parseLong(mobile)){
        			p.setIdPhoneNumber(Long.parseLong(mobile));
        			
        			phoneNumberService.updatePhoneNumber(id, p.getPhoneKind(), p.getPhoneNumber(), c);
					

        		}
        	}
        	if(p.getPhoneKind().equals("Maison")){
        		if(p.getIdPhoneNumber()!=Long.parseLong(maison)){
        			p.setIdPhoneNumber(Long.parseLong(maison));
        			phoneNumberService.updatePhoneNumber(id, p.getPhoneKind(), p.getPhoneNumber(), c);
        		}
        	}
        	if(p.getPhoneKind().equals("Bureau")){
        		if(p.getIdPhoneNumber()!=Long.parseLong(bureau)){
        			p.setIdPhoneNumber(Long.parseLong(bureau));
        			phoneNumberService.updatePhoneNumber(id, p.getPhoneKind(), p.getPhoneNumber(), c);
        		}
        	}
        }
        
      

       /* DaoContactGroup daoContactGroup = new DaoContactGroup();
        ContactGroupService contactGroupService = new ContactGroupService(daoContactGroup);
        contactGroupService.up
        Address adr = new Address(rue, ville, code, pays);
		*/
       /* Set<ContactGroup> cgroupe = new HashSet<ContactGroup>();

        if (groupes != null) {
            for (int i = 0; i < groupes.length; i++) {
                cgroupe.add(new ContactGroup(groupes[i], null));
            }
        }

        long nsiret = -1;
        if (numSiret != null && (!numSiret.equals(""))) {
            try {
                nsiret = Long.parseLong(numSiret);
            } catch (Exception e) {
                nsiret = 0;
            }
        }
        
        long id = -1;
        try {
            id = Long.parseLong(idC);
        } catch (Exception e) {}
        
        DaoContact dao = new DaoContact();
        dao.updateContact(id, firstName, lastName, email, adr, tels, cgroupe, nsiret);
        */
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil.jsp");
		//dispatcher.forward(request, response);
    }

}