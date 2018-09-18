package com.capgemini.bankapp.service.impl;

import java.util.HashSet;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapp.exceptions.NegativeBalanceException;
import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {
               BankAccountDao bankAccountDao;
               
               
	public BankAccountServiceImpl() {
				super();
				// TODO Auto-generated constructor stub
				bankAccountDao = new BankAccountDaoImpl();
			}

	@Override
	public double getBalance(long accountId) throws UserNotFoundException{
		return bankAccountDao.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws NegativeBalanceException, UserNotFoundException{
		
		return (bankAccountDao.updateBalance(accountId,(bankAccountDao.getBalance(accountId)-amount)));
	}

	@Override
	public double deposit(long accountId, double amount) throws UserNotFoundException{
		return (bankAccountDao.updateBalance(accountId,(bankAccountDao.getBalance(accountId)+amount)));
		
	
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws NegativeBalanceException, UserNotFoundException {
	if(withdraw(fromAcc,amount)!=-1)
			{
		deposit(toAcc,amount);
		return true;
			}
		return false;
	}

}
