package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import DAO.interfaces.IDaoContact;
import DAO.interfaces.IDaoRequetesHQL;
import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.Entreprise;
import Domains.PhoneNumber;
import Services.ContactService;

public class SearchContact extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3248888220436838302L;

	public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException{
       
    	  String typeSearch = request.getParameter("typesearch");
          
          String numSiret = request.getParameter("numSiret");

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

          PrintWriter out = response.getWriter();
        
          response.setContentType("text/html");
          out.println( "<html><body>" );
          
          //OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
          
          Set<Contact> res = null;
  		  ApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());

          if (typeSearch.compareTo("Simple") == 0) {
              /* Recherche simple */
      		Account account = (Account) request.getSession().getAttribute("account");
      		
	        ContactService contactService = (ContactService) context.getBean("contactService");

              res = contactService.searchContact(firstName, lastName, email, adr, tels,cgroupe, numSiret,account);
          } else if (typeSearch.compareTo("Param") == 0) {
              /* Recherche avec requetes HQL */
        	  IDaoRequetesHQL daoReq = (IDaoRequetesHQL)context.getBean("DAORequetesHQL");
              res = daoReq.reqFromParam(firstName, lastName, email, adr, tels,
                      cgroupe, numSiret);
          } else if (typeSearch.compareTo("Example") == 0) {
              /* Recherche avec requetes HQL */
        	  IDaoRequetesHQL daoReq = (IDaoRequetesHQL)context.getBean("DAORequetesHQL");
              res = daoReq.reqExample(firstName, lastName, email, adr, tels,
                      cgroupe, numSiret);
          } else if (typeSearch.compareTo("Criteria") == 0) {
              /* Recherche avec requetes HQL */
        	  IDaoRequetesHQL daoReq = (IDaoRequetesHQL)context.getBean("DAORequetesHQL");
              res = daoReq.reqCriteria(firstName, lastName, email, adr, tels,
                      cgroupe, numSiret);
          } else {
              out.write("<h1>Il faudrait penser à sélectionner le type de recherche</h1>\n");
              return;
          }


          if (res == null || res.size() == 0) {
              out.println("<h1>Aucun résultat.</h1>");
          } else {
              out.println("<h1>Résultats de la recherche</h1>");
              out.println("<table class='table striped hovered cell-hovered border bordered'>");
              out.println("<thead>");
              out.println("<tr>");
              out.println("<th class='sortable-column'>FirstName</th>");
              out.println("<th class='sortable-column'>LastName</th>");
              out.println("<th class='sortable-column'>Email</th>");
              out.println("<th class='sortable-column'>Numero SIRET</th>");
              out.println("<th class='sortable-column'>Afficher le contact</th>");
              out.println("<th class='sortable-column'>Editer le contact</th>");
              out.println("<th class='sortable-column'>Supprimer le contact</th>");

              out.println("</tr>");
              out.println("</thead>");

              for (Contact c : res) {
                  out.println("<tr><td>"
                          + c.getLastName()
                          + "</td><td>"
                          + c.getFirstName()
                          + "</td><td>"
                          + c.getEmail()
                          + "</td><td>"
                          + ((c instanceof Entreprise) ? ((Entreprise) c).getNumSiret() : "") + "</td>"
                          + "<td><a href='search.jsp?id=" + c.getIdContact() +"'/>"
          	     	      + "</td>"
          	              + "<td><a href='update.jsp?id=" + c.getIdContact() +"'/>"
          	              + "</td>"
          	              + "<td><a href='delete.jsp?id=" + c.getIdContact() +"'/>"
                          + "</tr>");
              }
              out.println("</table>");
          }
          out.println("</body></html>"); 
    }
}