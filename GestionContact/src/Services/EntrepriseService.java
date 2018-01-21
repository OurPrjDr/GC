package Services;

import java.util.List;
import java.util.Set;

import DAO.DaoEntreprise;
import Domains.Account;
import Domains.Address;
import Domains.Entreprise;
import Services.interfaces.IEntrepriseService;



public class EntrepriseService implements IEntrepriseService {
	
	DaoEntreprise dao;
	
	public EntrepriseService(DaoEntreprise dao){
		this.dao = dao;
	}
	
	public void deleteEntreprise(long id){
		try{
			dao.deleteEntreprise(id);
		} catch (Exception e){}
	}

	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret){
		try{
			dao.updateEntreprise(id, firstName, lastName, emailC, add, numSiret);
		} catch (Exception e){ }
	}

	public Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret){
		try{
			return dao.createEntreprise(firstName, lastName, email, add, numSiret);
		} catch (Exception e){
			return null;
		}
	}

	public Entreprise getEntreprise(long id){
		try{
			return dao.getEntreprise(id);
		} catch (Exception e){
			return null;
		}
	}

	public Set<Entreprise> getAllEntreprises() {
		try{
			return dao.getAllEntreprises();
		} catch (Exception e){
			return null;
		}
	}

}
