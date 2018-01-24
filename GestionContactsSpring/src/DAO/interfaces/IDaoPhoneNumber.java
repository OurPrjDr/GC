package DAO.interfaces;

import Domains.Contact;
import Domains.PhoneNumber;

public interface IDaoPhoneNumber {
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact);
	public PhoneNumber getPhoneNumberById(long id);
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact) ;
	public void deletePhoneNumber(long id);
}
