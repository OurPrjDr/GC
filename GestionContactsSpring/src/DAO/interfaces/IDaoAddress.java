package DAO.interfaces;

import Domains.Address;

public interface IDaoAddress {

	public Address createAddress(String street, String city, String zip, String country);
	public boolean updateAddress(long id, String street, String city, String zip, String country);
	public void deleteAddress(long id);
	public Address getAddressById(long id);
}
