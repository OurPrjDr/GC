package Services.interfaces;

import Domains.Account;



public interface IAccountService {

	Account createAccount(String login, String pwd);

	void updateAccount(long id, String pwd);

	void deleteAccount(long id);

	boolean containsLogin(String login);

	Account checkConnection(String login, String password);

	long getAccountId(String login);

}