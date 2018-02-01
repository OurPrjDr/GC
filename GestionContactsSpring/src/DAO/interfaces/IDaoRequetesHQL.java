package DAO.interfaces;

import java.util.Set;
import Domains.*;

public interface IDaoRequetesHQL {

	public Set<Contact> reqFromParam(String firstname, String lastname,
	            String email, String numSiret);
	public Set<Contact> reqCriteria(String firstname, String lastname,
	            String email, String numSiret);
	public Set<Contact> reqExample(String firstname, String lastname,
	            String email, String numSiret);
}