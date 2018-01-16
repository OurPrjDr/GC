package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoContact;
import Domains.Address;
import Domains.ContactGroup;
import Domains.PhoneNumber;

public class UpdateContact extends HttpServlet {

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException{
       
    	String idC = request.getParameter("id");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");

        String rue = request.getParameter("street");
        String ville = request.getParameter("city");
        String code = request.getParameter("zip");
        String pays = request.getParameter("country");

        String mobile = request.getParameter("telMobile");
        String maison = request.getParameter("telMaison");
        String bureau = request.getParameter("telBureau");

        String numSiret = request.getParameter("numSiret");

        String groupes[] = request.getParameterValues("groupes");

        Set<PhoneNumber> tels = new HashSet<PhoneNumber>();
        tels.add(new PhoneNumber("Mobile", mobile));
        tels.add(new PhoneNumber("Maison", maison));
        tels.add(new PhoneNumber("Bureau", bureau));

        Address adr = new Address(rue, ville, code, pays);

        Set<ContactGroup> cgroupe = new HashSet<ContactGroup>();

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
        dao.updateContact(id, firstName, lastName, email, adr, tels,
                cgroupe, nsiret);
    }

}