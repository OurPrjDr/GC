package Services.interfaces;

import java.util.List;
import java.util.Set;

import Domains.Account;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;



public interface IContactService {

	Contact createContact(String firstname, String lastname, String emailC, Address add);

	boolean updateContact(long id, String firstName, String lastName, String emailC, Address add);

	boolean deleteContact(long id);

	Contact getContactById(long id);

	void addPhonesInContact(long idContact, PhoneNumber pn);
	
	Set<Contact> searchContact(String firstName, String lastName, String email, Address address, Set<PhoneNumber> phones, Set<ContactGroup> groups, 
 			String numSiret, Account acc);

	void addContactInGroup(long id_cont, long id_group);	
	
	Address getAddress(long id);
	
	Set<Contact> getAllContacts();
}