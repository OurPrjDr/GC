package Domains;

import java.util.List;
import java.util.Set;

public class ContactGroup {
	private long idContactGroup; 
	private String groupName;
	private Set<Contact> contacts;

	private long version;
	

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}


	public ContactGroup() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ContactGroup(String groupName, Set<Contact> contacts) {
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


	public Set<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

 
	
 
}
