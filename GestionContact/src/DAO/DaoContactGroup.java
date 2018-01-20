package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Domains.Account;
import Domains.ContactGroup;
import Util.HibernateUtil;



public class DaoContactGroup {
	
	public ContactGroup createContactGroup(String groupName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		ContactGroup cG = new ContactGroup();
		cG.setGroupName(groupName);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(cG);
		tx.commit();
		
		System.out.println("createContactGroup réussi");
		return cG;
		
	}

	public boolean updateContactGroup(long id,String groupName) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			ContactGroup cG = (ContactGroup) session.load(ContactGroup.class, id);
			if(groupName!=null && !groupName.isEmpty()) cG.setGroupName(groupName);
			tx.commit();
			return true;
		} catch(Exception e){
			return false;
		}
	}

	public ContactGroup getContactGroupById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		ContactGroup contact = (ContactGroup) session.load(ContactGroup.class, id);
		return contact;
	}

	public boolean deleteContactGroup(long id) {
		try{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
	
			ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, id);
			tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			session.delete(contactGroup);
			tx.commit();
			return true;
		} catch(Exception e){
			return false;
		}
	}

	public void deleteContactInGroup(long idGroup, long idContact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, idGroup);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(contactGroup);
		tx.commit();
		System.out.println("deletePhoneNumber réussi");
	}

	public List<ContactGroup> findAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<ContactGroup> results = session.createQuery("select contactGroup from ContactGroup contactGroup").list();
		List<ContactGroup> listContact = new ArrayList<ContactGroup>();
		if(results!=null){
			for(ContactGroup cg: results){
				listContact.add(cg);
			}
		}
		tx.commit();
		return listContact;
	}

}
