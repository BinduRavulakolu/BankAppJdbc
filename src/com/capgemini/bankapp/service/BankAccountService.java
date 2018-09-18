package com.capgemini.bankapp.service;

import com.capgemini.bankapp.exceptions.NegativeBalanceException;
import com.capgemini.bankapp.exceptions.UserNotFoundException;

public interface BankAccountService {
	public double getBalance(long accountId) throws UserNotFoundException;
	public double withdraw(long accountId, double amount) throws UserNotFoundException, NegativeBalanceException;
	public double deposit(long accountId, double amount) throws UserNotFoundException;
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws NegativeBalanceException, UserNotFoundException;


}
