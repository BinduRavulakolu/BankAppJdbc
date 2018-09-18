package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.CustomerService;

@WebServlet("/account")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  public ServletContext context;
    public AccountController() {
        super();
        
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	context = config.getServletContext();
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

}
	