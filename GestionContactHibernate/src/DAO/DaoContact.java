package DAO;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.transform.Transformers;

import com.sun.javafx.collections.MappingChange.Map;

import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.Entreprise;
import Domains.PhoneNumber;
import Util.HibernateUtil;

public class DaoContact {
	
	/*private String url      = "jdbc:mysql://localhost/bdcontact";
	private String user     = "root";
	private String password = "root";
	Statement st;*/

	Session session = HibernateUtil.getSessionFactory().openSession();
	Set<Contact> sc = new HashSet<Contact>();

	//Session session = HibernateUtil.getSessionFactory().openSession();

	
	/*java.sql.Connection newConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection conn = DriverManager.getConnection(url, user, password);
		    return conn;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	public void addContact(String firstName, String lastName, String email, Address address, Set<PhoneNumber> phones, Set<ContactGroup> groups, 
			long numSiret) throws ClassNotFoundException {
		
		Contact c = new Contact(firstName, lastName, email); 
		
		//for(int i=0;i< phone.size();i++) {
			//System.out.println(phone.get(i).getIdPhoneNumber());
			//System.out.println(phone.get(i).getPhoneKind());

			////}
		

		try { 
 
			Transaction transaction = session.beginTransaction(); 
			
		    if (numSiret > -1) {
		    	c = new Entreprise(firstName, lastName, email, numSiret);
		    } else {
		        c = new Contact(firstName, lastName, email);
		    }
		      
			c.setAddress(address);
			c.setPhones(phones);
			c.setBooks(groups);
			//session.save(c);
 			session.persist(c);
			transaction.commit();
			
 		
		//	st = this.newConnection().createStatement();
		//	st.executeUpdate("INSERT INTO contact " + "VALUES ('"+id+"', '"+firstName+"', '"+lastName+"', '"+email+"')");

		
 		//	st.close();

				
		} catch (Exception e) {
			e.printStackTrace();
		}/*finally {
			session.clear();
		}*/

		
	}
	
	public Set<Contact> getAllContacts() {
			
		   //session = HibernateUtil.getSessionFactory().openSession();
	      	try{Transaction tx = null;
	      
	         tx = session.beginTransaction();
	        
	         Query query = session.createQuery("from Contact");
	         //query.addScalar("id", Hibernate.INT).setResultTransformer(Transformers.aliasToBean(Contact.class));
	        // query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	         List<Contact> data = query.list();
	         sc.addAll(data);
	      
	         tx.commit();
	      	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	         finally {
	 			session.clear();

	 		}
		        
	         return sc;
	}
	
	public void deleteContact(long idContact) {
		
		try {
			Transaction transaction = session.beginTransaction(); 

  			Contact c = (Contact)session.load(Contact.class, idContact); 			 		
 			session.delete(c); 
 			transaction.commit();			
 
 			
 			//st = this.newConnection().createStatement();
			//st.executeUpdate("DELETE FROM contact WHERE idContact=" + idContact);
		    //st.close();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.clear();

		}
		
	}
 
     public void updateContact(long id, String firstName, String lastName, String email, Address address, Set<PhoneNumber> phones, Set<ContactGroup> groups, 
		long numSiret) { 
         session.beginTransaction();

         Contact c = (Contact) session.get(Contact.class, id);

         if (c != null) {
             if (firstName != null)
                 c.setFirstName(firstName);
             if (lastName != null)
                 c.setLastName(lastName);
             if (email != null)
                 c.setEmail(email);

             if (address != null) {
                 c.getAddress().setCity(address.getCity());
                 c.getAddress().setCountry(address.getCountry());
                 c.getAddress().setStreet(address.getStreet());
                 c.getAddress().setZip(address.getZip());
             }

             if (phones != null) {
                 for (PhoneNumber phn : c.getPhones()) {
                     session.delete(phn);
                 }
                 
                 c.setPhones(phones);
                 session.flush();
             }

             if (groups != null) {
                 ///Changer de groupes
             }
         }
 		
 		session.getTransaction().commit();

    }

     public Contact searchParId(long idContact){
    	 Contact c = new Contact();
    	 try {
 			Transaction transaction = session.beginTransaction(); 

   			c = (Contact)session.get(Contact.class, idContact); 
   			
  			transaction.commit();			
  			//System.out.println("das"+c.getFirstName());
  			
  			//st = this.newConnection().createStatement();
 			//st.executeUpdate("DELETE FROM contact WHERE idContact=" + idContact);
 		    //st.close();
 	
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} finally {
 			session.clear();

 		}
    	 return c;
     }
 
     public Set<Contact> searchContact(String firstName, String lastName, String email, Address address, Set<PhoneNumber> phones, Set<ContactGroup> groups, 
 			String numSiret) {
    	
         StringBuffer sb = new StringBuffer(128);
         Set<Contact> setC = new HashSet<Contact>();
         
         long nsiret = -1;
         
         if ((numSiret != null) && (!numSiret.equals(""))) {
             try {
                 nsiret = Long.parseLong(numSiret);
             } catch (Exception e) {
                 nsiret = 0;
             }
         }

         if (nsiret > -1) {
             sb.append("select c from Entreprise as c inner join PhoneNumber phn");
             sb.append(" where c.numSiret like '%" + numSiret + "%'");
         } else {
             sb.append("select c from Contact as c, PhoneNumber as phn");
             if (firstName != null && firstName.length() > 0) {
                 sb.append(" where c.firstName like '%" + firstName + "%'");
             } else {
                 sb.append(" where c.firstName like '%'");
             }
         }
         
         
         if (lastName != null && lastName.length() > 0) {
             sb.append(" and c.lastName like '%" + lastName + "%'");
         } else {
             sb.append(" and c.lastName like '%'");
         }

         if (email != null && email.length() > 0) {
             sb.append(" and c.email like '%" + email + "%'");
         } else {
             sb.append( "and c.email like '%'");
         }

         if (address!= null) {
             if (address.getCity().length()>0) {
                 sb.append(" and c.address.city like '%" + address.getCity() + "%'");
             } else {
                 sb.append(" and c.address.city like '%'");
             }
             
             if (address.getCountry().length()>0) {
                 sb.append(" and c.address.country like '%" + address.getCountry() + "%'");
             } else {
                 sb.append(" and c.address.country like '%'");
             }
             
             if (address.getStreet().length()>0) {
                 sb.append(" and c.address.street like '%" + address.getStreet() + "%'");
             } else {
                 sb.append(" and c.address.street like '%'");
             }
             
             if (address.getZip().length()>0) {
                 sb.append(" and c.address.zip like '%" + address.getZip() + "%'");
             } else {
                 sb.append(" and c.address.zip like '%'");
             }
         }

         // Rajouter les tels
         // Rajouter les groupes
         
         session.beginTransaction();
         
         List<Contact> l = session.createQuery(sb.toString()).list();
         session.getTransaction().commit();
         
         setC.addAll(l);
         
         return setC;
    }
}    
        


