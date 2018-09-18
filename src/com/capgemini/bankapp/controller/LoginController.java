package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.service.CustomerService;
import com.capgemini.bankapp.service.impl.CustomerServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	Customer customer;
	private CustomerService service;

	public LoginController() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new CustomerServiceImpl();
		context = config.getServletContext();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String custId = request.getParameter("custId");
		String password = request.getParameter("password");

		context.setAttribute("service", service);
		Customer customer = new Customer(Integer.parseInt(custId), null, password, null, null, LocalDate.now(), null);

		try {
			customer = service.authenticate(customer);
			request.setAttribute("success", true);
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			System.out.println(customer);
			response.sendRedirect("account");
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			context.log(e.toString());
			request.setAttribute("success", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
