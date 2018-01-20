package Services;

import DAO.DaoPhoneNumber;
import Domains.Contact;
import Domains.PhoneNumber;
import Services.interfaces.IPhoneNumberService;




public class PhoneNumberService implements IPhoneNumberService {
	
private DaoPhoneNumber dao;
	
	public PhoneNumberService(DaoPhoneNumber dao){
		this.dao = dao;
	}
	
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact){
		try{
			return dao.createPhoneNumber(phoneKind, phoneNumber, contact);
		} catch (Exception e){ 
			return null;
		}
	}
	
	
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact){
		try{
			dao.updatePhoneNumber(id, phoneKind, phoneNumber, contact);
		} catch (Exception e){ }
	}
	
	public void deletePhoneNumber(long id){
		try{
			dao.deletePhoneNumber(id);
		} catch (Exception e){ }
	}

}
