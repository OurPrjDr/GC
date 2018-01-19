package DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Util.HibernateUtil;

public class DAOContactGroup {
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	public void addContactInGroups(Set<ContactGroup> cg, Contact c) {
	    	
	    StringBuffer rq = new StringBuffer(128);
	    boolean first = true;
	    Set<ContactGroup> tmpSet = new HashSet<ContactGroup>();
	
	    rq.append("select c from ContactGroup c where ");
	    for (ContactGroup contactGroup : cg) {
	        if (first)
	            first = false;
	        else
	            rq.append(" or ");
	
	        rq.append("c.groupName = '");
	        rq.append(contactGroup.getGroupName());
	        rq.append("'");
	        tmpSet.add(contactGroup);
	    }
	
	    List<ContactGroup> l = session.createQuery(rq.toString()).list();
	
	    for (ContactGroup contactGroup : l) {
	        contactGroup.getContacts().add(c);
	        tmpSet.remove(contactGroup);
	    }
	
	    for (ContactGroup contactGroup : tmpSet) {
	        session.save(contactGroup);
	    }
	}
	
	public void deleteContactGroup(long idGroup) {
		Transaction transaction = session.beginTransaction(); 
		DAOContact dc = new DAOContact();
		ContactGroup cg = (ContactGroup)session.get(ContactGroup.class, idGroup);

		for(Contact c : cg.getContacts()) {
			c.getBooks().remove(cg);
			dc.updateContact(c);
		}
		
		session.delete(cg);
		transaction.commit();
	}
	
	public void updateContactGroup(ContactGroup cg) {
		session.beginTransaction();
		session.update(cg);
		session.getTransaction().commit();
	}
	
	public List<Contact> searchContactGroup(String cgName){
		session.beginTransaction();
		/*HQL*/
	}
	
}
