package Services.interfaces;

import Domains.Address;


public interface IAddressService {

	Address createAddress(String street, String city, String zip, String country);

	boolean updateAddress(long id, String street, String city, String zip, String country);

	void deleteAddress(long id);

	Address getAddressById(long id);

}