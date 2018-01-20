package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoAccount;
import Domains.Account;
import Services.AccountService;

/**
 * Servlet implementation class SignServlet
 */
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoAccount daoAccount = new DaoAccount();
		AccountService accountService = new AccountService(daoAccount);
		String signInOrUp = request.getParameter("SignInOrUp");
		boolean bon = false;
		if(signInOrUp.equals("up")){
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String secondPassword = request.getParameter("secondPassword");
			if(login!=null && !login.isEmpty() && password!=null && !password.isEmpty() 
					&& secondPassword!=null && !secondPassword.isEmpty()){ 
				if(!password.equals(secondPassword)){
					request.setAttribute("message", "Not the same passwords...");
				} else if(accountService.containsLogin(login)){
					request.setAttribute("message", "Login already exists...");
				} else {
					
					Account account = accountService.createAccount(login, password);
					if(account==null) request.setAttribute("message", "Sorry. An error occured during the account creation...");
					else {
						request.getSession().setAttribute("account", account);
						request.setAttribute("message", "Welcome "+login+" !");
						request.setAttribute("success", true);
						bon = true;
					}
				}
			}else {
				request.setAttribute("message", "One field is empty...");
			}
		}else if(signInOrUp!=null && signInOrUp.equals("in")){ // Connexion
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			if(login!=null && !login.isEmpty() && password!=null && !password.isEmpty()){ // Champs correctement remplis
				if(!accountService.containsLogin(login)){
					request.setAttribute("message", "Unknown login...");
				} else {
					
					Account account = accountService.checkConnection(login, password);
					if(account==null) request.setAttribute("message", "Bad password...");
					else {
						request.getSession().setAttribute("account", account);
						request.setAttribute("message", "Welcome "+login+" !");
						bon = true;

					}
				}
				
			} else {
				request.setAttribute("message", "One field is empty...");
			}
		}
		if(bon) response.sendRedirect("accueil.jsp");
		else request.getRequestDispatcher("sign.jsp").forward(request, response);
	}

}
