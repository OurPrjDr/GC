package Services;

import DAO.DaoAddress;
import Domains.Address;
import Services.interfaces.IAddressService;


public class AddressService implements IAddressService {
	
	private DaoAddress dao;
	
	public AddressService(DaoAddress dao){
		this.dao = dao;
	}
	
	
	public Address createAddress(String street, String city, String zip, String country){
		try{
			return dao.createAddress(street, city, zip, country);
		} catch (Exception e){
			return null;
		}
	}
	
	public boolean updateAddress(long id, String street, String city, String zip, String country){
		try{
			return dao.updateAddress(id, street, city, zip, country);
		} catch (Exception e){
			return false;
		}
	}
	
	public void deleteAddress(long id){
		try{
			dao.deleteAddress(id);
		} catch (Exception e){}
	}
	
	public Address getAddressById(long id){
		try{
			return dao.getAddressById(id);
		} catch (Exception e){
			return null;
		}
	}

}
