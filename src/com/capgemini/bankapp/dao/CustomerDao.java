package com.capgemini.bankapp.dao;

import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.model.Customer;

public interface CustomerDao {
	public Customer authenticate(Customer customer) throws UserNotFoundException;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);

}
