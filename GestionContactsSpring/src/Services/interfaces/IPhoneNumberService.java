package Services.interfaces;

import Domains.Contact;
import Domains.PhoneNumber;



public interface IPhoneNumberService {

	PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact);

	void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact);

	void deletePhoneNumber(long id);

}