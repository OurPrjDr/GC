package Services;

import java.util.List;

import DAO.DaoContactGroup;
import Domains.Account;
import Domains.ContactGroup;
import Services.interfaces.IContactGroupService;



public class ContactGroupService implements IContactGroupService {

	private DaoContactGroup dao;
	
	public ContactGroupService(DaoContactGroup dao){
		this.dao = dao;
	}
	
	public ContactGroup createContactGroup(String nameGroupe){
		try{
			return dao.createContactGroup(nameGroupe);
		} catch (Exception e){
			return null;
		}
	}
	
	public boolean updateContactGroup(long id, String nameGroupe){
		try{
			return dao.updateContactGroup(id, nameGroupe);
		} catch (Exception e){
			return false;
		}
	}
	
	public List<ContactGroup> findAll(){
		try{
			return dao.findAll();
		} catch (Exception e){
			return null;
		}
	}
	
	public void deleteContactGroup(long id){
		try{
			dao.deleteContactGroup(id);
		} catch (Exception e){}
	}
}
