package domains;

public class Address {
	private long idAdress; 
	private String street; 
	private String city; 
	private String zip; 
	private String country; 
 	private Contact contact; 
 	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(long idAdress, String street, String city, String zip, String country) {
		super();
		this.idAdress = idAdress;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}

	public long getIdAdress() {
		return idAdress;
	}

	public void setIdAdress(long idAdress) {
		this.idAdress = idAdress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	

	
}