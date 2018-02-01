package DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.interfaces.IDaoContact;
import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;
import Util.HibernateUtil;


public class DaoContact implements IDaoContact {
	
	public void createContact(Contact c) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(c);
		tx.commit();
		
		System.out.println("createContact reussi c="+c.getIdContact()); 
	}
		
	public Contact createContact(String firstname, String lastname, String emailC, Address add){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Contact contact = new Contact();
		contact.setEmail(emailC);
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setAddress(add);
		contact.setPhones(new HashSet<PhoneNumber>());
		contact.setBooks(new HashSet<ContactGroup>());
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.saveOrUpdate(add);
		session.save(contact);
		tx.commit();
		
		System.out.println("createContact reussi c="+contact.getIdContact()); 
		return contact;
	}
	
	public boolean updateContact(long id, String firstName, String lastName, String emailC, Address add){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			
			Contact contact = (Contact) session.load(Contact.class, id);
			if(firstName!= null && !firstName.isEmpty()) contact.setFirstName(firstName);
			if(lastName!= null && !lastName.isEmpty()) contact.setLastName(lastName);
			if(emailC!= null && !emailC.isEmpty()) contact.setEmail(emailC);
			if(add!= null) contact.setAddress(add);
			
			tx.commit();
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteContact(long id){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			
			Contact contact = (Contact) session.get(Contact.class, id);
			System.out.println("@@@@@@@@@@@"+contact.getIdContact());

			session.delete(contact);
			
			tx.commit();
			System.out.println("deletecontact reussit!!!");
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public Set<Contact> searchContact(String firstName, String lastName, String email, String numSiret) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 System.out.println("@@@@@@"+numSiret);
		 
         StringBuffer sb = new StringBuffer();
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
        	 System.out.println("IIIIIIIIIIIIIIIIIIIIIII"+nsiret);
             sb.append("select c from Entreprise as c ");
             sb.append(" where c.numSiret like '%" + nsiret + "%'");
         } 
         
         if  (firstName != null && firstName.length() > 0) {
             sb.append("select c from Contact as c");
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

         

        
         session.beginTransaction();
         
         List<Contact> l = session.createQuery(sb.toString()).list();
        
         
         session.flush();
         session.getTransaction().commit();
         
         setC.addAll(l);
         
         return setC;
    }
	
	public Contact getContactById(long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Contact c = new Contact();
		
		c = (Contact) session.get(Contact.class, id);
		return c;
	}

	public void addPhonesInContact(long idContact, PhoneNumber pn){
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Contact contact = (Contact) session.load(Contact.class, idContact);
		Set<PhoneNumber> phoneNumbers = contact.getPhones();
		phoneNumbers.add(pn);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		tx.commit();
	}
	
	
		
	public void addContactInGroup(long id_cont, long id_group){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id_cont);
		ContactGroup cg = (ContactGroup) session.load(ContactGroup.class, id_group);
		Set<ContactGroup> set = contact.getBooks();
		set.add(cg);
		contact.setBooks(set);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		tx.commit();
	}

	public Address getAddress(long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		return contact.getAddress();
		
	}
	
	public Set<Contact> getAllContacts() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<Contact> sc = new HashSet<Contact>();
      	try{
      		
      		 Transaction tx = session.getTransaction();
    		 if(!tx.isActive()) tx = session.beginTransaction();
	        
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
}
