package dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domains.Contact;
import domains.PhoneNumber;
import util.HibernateUtil;

public class DaoContact {
	
	/*private String url      = "jdbc:mysql://localhost/bdcontact";
	private String user     = "root";
	private String password = "root";
	Statement st;*/

	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
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
	
	public void addContact(String firstName, String lastName, String email, Set<PhoneNumber> phone) throws ClassNotFoundException {
		
		Contact c = new Contact(firstName, lastName, email); 
		
		//for(int i=0;i< phone.size();i++) {
			//System.out.println(phone.get(i).getIdPhoneNumber());
			//System.out.println(phone.get(i).getPhoneKind());

			////}
		

		try { 
 
			Transaction transaction = session.beginTransaction(); 
			System.out.println("session open ? " + session.isOpen());
			c.setPhones(phone);
			session.persist(c);
			System.out.println("session open ? " + session.isOpen());

 			
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
	
	public List<Contact> getAllContacts() {
		  Transaction transaction = session.beginTransaction(); 

		 Query qry = session.createQuery("from contact p");
		 
		 List l =qry.list();
		 System.out.println("Total Number Of Records : "+l.size());
		 Iterator it = l.iterator();
		 
		 while(it.hasNext())
		 {
			 Object o = (Object)it.next();
			 Contact p = (Contact)o;
			 
			 System.out.println("Product id : "+p.getIdContact());
			 System.out.println("  Name : "+p.getFirstName());
			 System.out.println("lasr  : "+p.getLastName());
			 System.out.println("----------------------");
		 }
		return l; 
		 
		
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
 
     public void updateContact(long id, String lastname,
             String firstName, String email) { 
 		try {
 			
			Transaction transaction = session.beginTransaction(); 
			Contact c = new Contact(); 
			c.setIdContact(id);
			c.setLastName(lastname);
			c.setFirstName(firstName);
			c.setEmail(email); 
			 			
			session.update(c); 
 			transaction.commit();			
 			
 			//st = this.newConnection().createStatement();
 			//st.executeUpdate( "UPDATE contact SET firstName = '"+nouvNom+"' ,  lastName = '"+ nouvPrenom +"',  email = '"+nouvEmail+"'  "
 	        //         + " WHERE idContact = " + id);
			//st.close();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.clear();

		}
    }

 
     public void searchContact(long id) {
  		try {
			Transaction transaction = session.beginTransaction(); 

  			Contact c = (Contact)session.load(Contact.class, id); 
  			transaction.commit();			


 			//st = this.newConnection().createStatement();
 			//st.executeUpdate( "SELECT * FROM contact "
 	        //         + " WHERE idContact = " + id);
			//st.close();
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.clear();

		}
  		
    }
     
    
     
}

