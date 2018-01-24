package DAO.interfaces;

import Domains.Account;

public interface IDaoAccount {
	
	public Account createAccount(String login, String password);	
	public void deleteAccount(long id);
	public void updateContact(long id, String pwd);
	public boolean containsLogin(String login);
	public Account checkConnection(String login, String password);
	public long findAccountIdByLogin(String login);
}
