package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Domains.Address;
import Util.HibernateUtil;

public class DAOAddress {
	Session session = HibernateUtil.getSessionFactory().openSession();

	public void addAddress(Address a) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(a);
		transaction.commit();
	}
	
	/*
	public void deleteAddress(long idAddress) {
		Transaction transaction = session.beginTransaction(); 

		Address a = (Address)session.load(Address.class, idAddress); 			 		
		session.delete(a); 
		transaction.commit();
	}*/
	
	public void deleteAddress(Address a) {
		Transaction transaction = session.beginTransaction(); 
		session.delete(a); 
		transaction.commit();
	}
	
	public void updateAddress(long id, String street, String city, String zip, String country) {
		Transaction transaction = session.beginTransaction();

        Address a = (Address) session.get(Address.class, id);

        if (a != null) {
            if (street != null)
            	a.setStreet(street);
            if (city != null)
                a.setCity(city);
            if (zip != null)
                a.setZip(zip);
            if (country != null)
            	a.setCountry(country);
        }
		session.update(a);
		transaction.commit();
	}
	
	public Address search(String a) {
		/*HQL*/
	}
	
}
