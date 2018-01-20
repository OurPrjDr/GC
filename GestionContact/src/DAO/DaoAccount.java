package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;

import Domains.Account;



public class DaoAccount {
	
	
	
	public Account createAccount(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Account acc = new Account();
		acc.setLogin(login);
		acc.setPwd(password);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(acc);
		tx.commit();
		System.out.println("createAccount réussi");
		return acc;
		
	}
	
	public void deleteAccount(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Account acc = (Account) session.load(Account.class, id);
		DaoContact contactDAO = new DaoContact();
		
		//On relance la session car deleteContactByCreator l'a fermé 
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(acc);
		tx.commit();
		System.out.println("deleteAccount réussi");
	}

	public void updateContact(long id, String pwd) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Account acc = (Account) session.load(Account.class, id);
		acc.setPwd(pwd);
		tx.commit();
		System.out.println("updateAccount réussi");
	}

	public boolean containsLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		tx.commit();
		System.out.println("containsLogin réussi");
		return acc!=null;
	}

	public Account checkConnection(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		String query = "from Account as account where account.login = :login and account.pwd = :password";
		Account acc = (Account) session.createQuery(query).setString("login", login).setString("password", password).uniqueResult();
		tx.commit();
		System.out.println("checkConnection réussi");
		//retourne null si pas de compte trouvé avec le login et le password
		return acc;
	}

	public long findAccountIdByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		tx.commit();
		System.out.println("findAccountIdByLogin réussi");
		return acc.getId();
	}
	

}
