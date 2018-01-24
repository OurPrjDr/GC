package DAO.interfaces;

import java.util.Set;

import Domains.Address;
import Domains.Entreprise;

public interface IDaoEntreprise {
		
	public Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret);
	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret);
	public void deleteEntreprise(long id);
	public Entreprise getEntreprise(long id);
	public Set<Entreprise> getAllEntreprises();
}
