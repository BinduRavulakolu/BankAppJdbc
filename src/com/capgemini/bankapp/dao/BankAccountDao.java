package com.capgemini.bankapp.dao;

import com.capgemini.bankapp.exceptions.NegativeBalanceException;
import com.capgemini.bankapp.exceptions.UserNotFoundException;

public interface BankAccountDao {
	public double getBalance(long accountId);
	public double updateBalance(long accountId, double newBalance);
	
}
