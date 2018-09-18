package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.capgemini.bankapp.client.DatabaseUtil;
import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.database.BankAccountDatabase;
import com.capgemini.bankapp.exceptions.NegativeBalanceException;
import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.model.BankAccount;

public class BankAccountDaoImpl implements BankAccountDao {

	@Override
	public double getBalance(long accountId) {
		String query="select balance from bankaccounts where account_id=?";
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);){
			statement.setDouble(1, accountId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				System.out.println(result.getDouble(1));
				return result.getDouble(1);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double updateBalance(long accountId, double newBalance) {
		String query="UPDATE bankaccounts SET balance=? WHERE  account_id=?";
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);){
			
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			int result = statement.executeUpdate();
			if (result == 1) {
				return getBalance(accountId);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

}
