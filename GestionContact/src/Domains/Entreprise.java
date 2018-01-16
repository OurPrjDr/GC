package Domains;

public class Entreprise extends Contact {
	private long numSiret;

	
	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String firstName, String lastname, String email,
            long numSiret) {
        super(firstName, lastname, email);
        this.numSiret = numSiret;
    }
    
	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}
	
}
