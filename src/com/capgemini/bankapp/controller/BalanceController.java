package com.capgemini.bankapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;


@WebServlet("/balance")
public class BalanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//       BankAccountDao bankAccountDao;
       BankAccountService bankAccountService;
       public ServletContext context;
    
    public BalanceController() {
        super();
        
    }
@Override
public void init(ServletConfig config) throws ServletException {
	context=config.getServletContext();
	bankAccountService = new BankAccountServiceImpl();
	context.setAttribute("bank", bankAccountService);
}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		Customer cust = (Customer) session.getAttribute("customer");
//		context.setAttribute("BankAccountDao", bankAccountDao);
		 
//		long accountid = Long.parseLong(request.getParameter("accountId"));
		
		try {
			double balance;
			
			balance = bankAccountService.getBalance(cust.getAccount().getAccountId());
			System.out.println("hanga"+balance);
			request.setAttribute("balance", balance);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher=request.getRequestDispatcher("balance.jsp");
		dispatcher.forward(request, response);
	}

}
