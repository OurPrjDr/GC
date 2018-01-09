package domains;

import java.util.List;

public class ContactGroup {
	private long idContactGroup; 
	private String groupName;
	private List<Contact> contacts;

	
	public ContactGroup() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ContactGroup(String groupName, List<Contact> contacts) {
		super();
		this.groupName = groupName;
		this.contacts = contacts;
	}


	public long getIdContactGroup() {
		return idContactGroup;
	}


	public void setIdContactGroup(long idContactGroup) {
		this.idContactGroup = idContactGroup;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

 
	
 
}
