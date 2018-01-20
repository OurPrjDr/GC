package Services.interfaces;

import java.util.List;

import Domains.Account;
import Domains.ContactGroup;


public interface IContactGroupService {

	ContactGroup createContactGroup(String nameGroupe);

	boolean updateContactGroup(long id, String nameGroupe);

	List<ContactGroup> findAll();

	void deleteContactGroup(long id);

}