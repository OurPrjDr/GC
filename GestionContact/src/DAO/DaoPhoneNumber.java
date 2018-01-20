package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Util.HibernateUtil;

import Domains.Contact;
import Domains.PhoneNumber;


public class DaoPhoneNumber {
	
	public DaoPhoneNumber(){ }
	
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PhoneNumber phoneNum = new PhoneNumber();
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		phoneNum.setContact(contact);
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(phoneNum);
		tx.commit();
		return phoneNum;
	}

	public PhoneNumber getPhoneNumberById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		PhoneNumber phoneNumber = (PhoneNumber) session.load(PhoneNumber.class, id);
		return phoneNumber;
	}
	
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact) {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		PhoneNumber phoneNum = (PhoneNumber) session.get(PhoneNumber.class, id);
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		
		/*try {
			if(phoneKind!=null && !phoneKind.equals("")) phoneNum.setPhoneKind(phoneKind);
			if(phoneNumber!=null && !phoneNumber.equals("")) phoneNum.setPhoneNumber(phoneNumber);
			System.out.println("@@@@@@@@@@try");
		} catch (Exception e){
			System.out.println("@@@@@@@@@@catch");

			phoneNum = new PhoneNumber();
			phoneNum.setContact(contact);
			phoneNum.setPhoneKind(phoneKind);
			phoneNum.setPhoneNumber(phoneNumber);
		}*/
		

		//session.update(phoneNum);
		session.saveOrUpdate(phoneNum);
		System.out.println("update reussit");
		tx.commit();
		
	}

	public void deletePhoneNumber(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		PhoneNumber phoneNum = (PhoneNumber) session.load(PhoneNumber.class, id);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(phoneNum);
		tx.commit();
	}

}
