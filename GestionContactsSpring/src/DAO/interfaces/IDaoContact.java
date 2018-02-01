package DAO.interfaces;

import java.util.Set;
import Domains.*;

public interface IDaoContact {
	
	public void createContact(Contact c);
	public Contact createContact(String firstname, String lastname, String emailC, Address add);
	public boolean updateContact(long id, String firstName, String lastName, String emailC, Address add);
	public boolean deleteContact(long id);
	public Set<Contact> searchContact(String firstName, String lastName, String email, String numSiret);
	public Contact getContactById(long id);
	public void addPhonesInContact(long idContact, PhoneNumber pn);			
	public void addContactInGroup(long id_cont, long id_group);
	public Address getAddress(long id);
	public Set<Contact> getAllContacts();
}
