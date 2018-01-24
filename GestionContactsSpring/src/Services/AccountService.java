package Services;

import DAO.DaoAccount;
import DAO.interfaces.IDaoAccount;
import Domains.Account;
import Services.interfaces.IAccountService;



public class AccountService implements IAccountService {
	
	private IDaoAccount dao;
	
	public AccountService(IDaoAccount dao){
		this.dao = dao;
	}
	
	public Account createAccount(String login, String pwd){
		try{
			return dao.createAccount(login, pwd);
		} catch (Exception e){
			return null;
		}
	}
	
	public void updateAccount(long id, String pwd){
		try{
			dao.updateContact(id, pwd);
		} catch (Exception e){	}
	}
	
	public void deleteAccount(long id){
		try{
			dao.deleteAccount(id);
		} catch (Exception e){}
	}

	public boolean containsLogin(String login) {
		try{
			return dao.containsLogin(login);
		} catch (Exception e){
			return false;
		}
	}

	public Account checkConnection(String login, String password) {
		try{
			return dao.checkConnection(login, password);
		} catch (Exception e){
			return null;
		}
	}

	public long getAccountId(String login) {
		try{
			return dao.findAccountIdByLogin(login);
		} catch (Exception e){
			return 0;
		}
	}
	
}
