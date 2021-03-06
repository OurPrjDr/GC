package Services;

import java.util.Set;

import DAO.interfaces.IDaoContact;
import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;
import Services.interfaces.IContactService;

public class ContactService implements IContactService {
	
	private IDaoContact dao;
	
	public ContactService(IDaoContact dao){
		this.dao = dao;
	}
	
	public void createContact(Contact c) {
		try{
			dao.createContact(c);
		} catch (Exception e){}
	}
	
	public Contact createContact(String firstname, String lastname, String emailC, Address add){
		try{
			return dao.createContact(firstname, lastname, emailC, add);
		} catch (Exception e){
			return null;
		}
	}
	
	public boolean updateContact(long id, String firstname, String lastname, String emailC, Address add){
		try{
			return dao.updateContact(id, firstname, lastname, emailC, add);
		} catch (Exception e){
			return false;
		}
	}
	
	public boolean deleteContact(long id){
		try{
			return dao.deleteContact(id);
		} catch (Exception e){
			return false;
		}
	}
	
	public Set<Contact> searchContact(String firstName, String lastName, String email,  String numSiret){
		try{
			return dao.searchContact(firstName, lastName, email, numSiret);
			
		} catch (Exception e){
			return null;
		}
	}

	public Contact getContactById(long id) {
		try{
			return dao.getContactById(id);
		} catch (Exception e){
			return null;
		}
	}

	public void addPhonesInContact(long idContact, PhoneNumber pn) {
		try{
			dao.addPhonesInContact(idContact, pn);
		} catch (Exception e){}
	}

	public void addContactInGroup(long id_cont, long id_group) {
		try{
			dao.addContactInGroup(id_cont, id_group);
		} catch (Exception e){}
	}

	public Address getAddress(long id){
		try{
			return dao.getAddress(id);
		} catch (Exception e){
			return null;
		}
	}


	public Set<Contact> getAllContacts() {
		try{
			return dao.getAllContacts();
		} catch (Exception e){
			return null;
		}
	}

}
