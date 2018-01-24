package Services.interfaces;

import java.util.List;
import java.util.Set;

import Domains.Account;
import Domains.Address;
import Domains.Entreprise;


public interface IEntrepriseService {
	
	void deleteEntreprise(long id);

	void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret);

	Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret);

	Entreprise getEntreprise(long id);
	
	Set<Entreprise> getAllEntreprises();
}