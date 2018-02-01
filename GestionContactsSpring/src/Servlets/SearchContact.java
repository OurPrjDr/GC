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
import Util.MyUtil;

public class SearchContact extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3248888220436838302L;

	public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException{
       
		MyUtil my = MyUtil.getInstance();
        
        String numSiret = my.testNullAndSet(request.getParameter("numSiret"));

        String lastName = my.testNullAndSet(request.getParameter("lastName"));
        String firstName = my.testNullAndSet(request.getParameter("firstName"));
        String email = my.testNullAndSet(request.getParameter("email"));

        String rue = my.testNullAndSet(request.getParameter("street"));
        String ville = my.testNullAndSet(request.getParameter("city"));
        String code = my.testNullAndSet(request.getParameter("zip"));
        String pays = my.testNullAndSet(request.getParameter("country"));

        String mobile = my.testNullAndSet(request.getParameter("telMobile"));
        String maison = my.testNullAndSet(request.getParameter("telMaison"));
        String bureau = my.testNullAndSet(request.getParameter("telBureau"));


      
         String typeSearch = request.getParameter("typesearch");
         
        


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
          out.println( "<html><head><script src='./design/js/bootstrap.js'></script><link href='./design/css/bootstrap.min.css' rel='stylesheet'></head><body>" );
          
          //OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
          
          Set<Contact> res = null;
  		  ApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
  		  if (typeSearch!=null){
	          if (typeSearch.compareTo("Simple") == 0) {
	              /* Recherche simple */
	      		
		        ContactService contactService = (ContactService) context.getBean("contactService");
	
	              res = contactService.searchContact(firstName, lastName, email,numSiret);
	              
	          } else if (typeSearch.compareTo("Param") == 0) {
	              /* Recherche avec requetes HQL */
	        	  IDaoRequetesHQL daoReq = (IDaoRequetesHQL)context.getBean("DaoRequetesHQL");
	              res = daoReq.reqFromParam(firstName, lastName, email, numSiret);
	          } else if (typeSearch.compareTo("Example") == 0) {
	              /* Recherche avec requetes HQL */
	        	  IDaoRequetesHQL daoReq = (IDaoRequetesHQL)context.getBean("DaoRequetesHQL");
	              res = daoReq.reqExample(firstName, lastName, email, numSiret);
	          } else if (typeSearch.compareTo("Criteria") == 0) {
	              /* Recherche avec requetes HQL */
	        	  IDaoRequetesHQL daoReq = (IDaoRequetesHQL)context.getBean("DaoRequetesHQL");
	              res = daoReq.reqCriteria(firstName, lastName, email,  numSiret);
	          } else {
	              out.write("<h1>Il faudrait penser à sélectionner le type de recherche</h1>\n");
	              return;
	          }
  		  }
  		  else request.setAttribute("message", "One field is empty...");
         
          if (res == null || res.size() == 0) {
              out.println("<h3 class='text-on-pannel text-primary'>Aucun resultat.</h3>");
          } else {
              out.println("<h3 class='text-on-pannel text-primary'><strong class='text-uppercase'> Resultat de la recherche </strong></h3>");
              out.println("<table class='table table-hover'>");
              out.println("<thead>");
              out.println("<tr>");
              out.println("<th class='sortable-column'>FirstName</th>");
              out.println("<th class='sortable-column'>LastName</th>");
              out.println("<th class='sortable-column'>Email</th>");
              out.println("<th class='sortable-column'>Numero SIRET</th>");
              out.println("<th class='sortable-column'>Afficher le contact</th>");
              out.println("<th class='sortable-column'>Supprimer le contact</th>");

              out.println("</tr>");
              out.println("</thead>");
              String entreprise = "false";
              for (Contact c : res) {
            	  if((c instanceof Entreprise))
            		 entreprise="true";
            		 else 
            			 entreprise="false";
            		 
                  out.println("<tr><td>"
                          + c.getLastName()
                          + "</td><td>"
                          + c.getFirstName()
                          + "</td><td>"
                          + c.getEmail()
                          + "</td><td>"
                          + ((c instanceof Entreprise) ? ((Entreprise) c).getNumSiret() : "") + "</td>"
           	              + "<td><a  href='updateContact.jsp?idContact=" + c.getIdContact() +"&entreprise="+entreprise+"'><input class='btn btn-primary' type='button' value='Detail/Edit'/></a>"
          	              + "</td>"
           	              + "<td><a  href='removeContact.jsp?idContact=" + c.getIdContact() +"'><input class='btn btn-primary' type='button' value='Delete'/></a>"

                           + "</tr>");
              }
              out.println("</table>");
          }
          out.println("</body></html>"); 
    }
}