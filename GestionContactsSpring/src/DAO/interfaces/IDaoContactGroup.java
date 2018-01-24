package DAO.interfaces;

import java.util.List;
import Domains.ContactGroup;

public interface IDaoContactGroup {
	public ContactGroup createContactGroup(String groupName);
	public boolean updateContactGroup(long id,String groupName);
	public ContactGroup getContactGroupById(long id);
	public boolean deleteContactGroup(long id);
	public void deleteContactInGroup(long idGroup, long idContact);
	public List<ContactGroup> findAll();
}
