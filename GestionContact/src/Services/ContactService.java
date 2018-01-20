package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import DAO.DaoContact;
import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;
import Services.interfaces.IContactService;



public class ContactService implements IContactService {
	
	private DaoContact dao;
	
	public ContactService(DaoContact dao){
		this.dao = dao;
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
	
	public Set<Contact> searchContact(String firstName, String lastName, String email, Address address, Set<PhoneNumber> phones, Set<ContactGroup> groups, 
 			String numSiret, Account acc){
		try{
			return dao.searchContact(firstName, lastName, email, address, phones, groups, numSiret, acc);
			
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
