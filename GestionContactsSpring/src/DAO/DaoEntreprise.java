package DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import DAO.interfaces.IDaoEntreprise;
import Util.HibernateUtil;

import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.Entreprise;



public class DaoEntreprise implements IDaoEntreprise {
	
	public Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Entreprise entre = new Entreprise();
		entre.setFirstName(firstName);
		entre.setLastName(lastName);
		entre.setEmail(email);
		entre.setNumSiret(numSiret);
		entre.setAddress(add);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.saveOrUpdate(add);
		session.save(entre);
		tx.commit();
		
		return entre;
		
	}

	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Entreprise entre = (Entreprise) session.load(Entreprise.class, id);
		if(firstName!= null && !firstName.isEmpty()) entre.setFirstName(firstName);
		if(lastName!= null && !lastName.isEmpty()) entre.setLastName(lastName);
		if(emailC!= null && !emailC.isEmpty()) entre.setEmail(emailC);
		if(add!= null) entre.setAddress(add);
		entre.setNumSiret(numSiret);
		
		tx.commit();
	}

	public void deleteEntreprise(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
		session.delete(entreprise);
		tx.commit();
	}

	public Entreprise getEntreprise(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Entreprise entreprise = (Entreprise) session.get(Entreprise.class, id);
		tx.commit();
		return entreprise;
	}
	public Set<Entreprise> getAllEntreprises() {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Set<Entreprise> sc = new HashSet<Entreprise>();
	      	try{
	      		
	      		 Transaction tx = session.getTransaction();
	    		 if(!tx.isActive()) tx = session.beginTransaction();
		        
		         Query query = session.createQuery("from Entreprise");
		         //query.addScalar("id", Hibernate.INT).setResultTransformer(Transformers.aliasToBean(Contact.class));
		        // query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		         List<Entreprise> data = query.list();
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
