package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import Util.HibernateUtil;

import Domains.Account;
import Domains.Address;
import Domains.Entreprise;



public class DaoEntreprise {
	
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

		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
		tx.commit();
		return entreprise;
	}
	
	
}
