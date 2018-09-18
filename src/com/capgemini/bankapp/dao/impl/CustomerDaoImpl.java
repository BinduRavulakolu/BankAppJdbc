package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.bankapp.client.DatabaseUtil;
import com.capgemini.bankapp.dao.CustomerDao;
import com.capgemini.bankapp.exceptions.UserNotFoundException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer authenticate(Customer customer) throws UserNotFoundException {
		String query = "SELECT * FROM customers inner join bankaccounts on customers.customer_id = bankaccounts.customer_id WHERE customers.customer_id=? AND customers.customer_password=?";
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getPassword());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				customer.setCustomerName(result.getString(2));
				customer.setPassword(result.getString(3));
				customer.setEmail(result.getString(4));
				customer.setAddress(result.getString(5));
				customer.setDateOfBirth(result.getDate(6).toLocalDate());
				BankAccount bankAccount = new BankAccount(result.getInt(8), result.getString(9), result.getDouble(10));
				customer.setAccount(bankAccount);
				return customer;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer updateProfile(Customer customer) {
		String query = "UPDATE customers SET customer_address = ?,customer_dob= ?,customer_email=?,customer_name=? WHERE customer_id = ? ";
		String query2 = "SELECT * FROM accounts WHERE customer_id = ?";

		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				PreparedStatement statement2 = connection.prepareStatement(query2)) {

			statement.setString(1, customer.getAddress());
			statement.setDate(2, Date.valueOf(customer.getDateOfBirth()));
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getCustomerName());
			statement.setInt(5, customer.getCustomerId());

			if (statement.executeUpdate() != 0) {
//				statement2.setInt(1, customer.getCustomerId());
//				try (ResultSet result = statement2.executeQuery()) {
//					if (result.next()) {
//						customer.setCustomerName(result.getString(2));
//						customer.setEmail(result.getString(4));
//						customer.setAddress(result.getString(5));
//						customer.setDateOfBirth(result.getDate(6).toLocalDate());
//						return customer;
//					}
//				}
				return getCustomer(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		String query = "update customers set customers.customer_password=? WHERE customers.customer_id=?";
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, newPassword);
			statement.setLong(2, customer.getCustomerId());
			if (statement.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Customer getCustomer(Customer customer) {
		String query = "SELECT * FROM customers inner join bankaccounts on customers.customer_id = bankaccounts.customer_id WHERE customers.customer_id=?";
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, customer.getCustomerId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				customer.setCustomerName(result.getString(2));
				customer.setPassword(result.getString(3));
				customer.setEmail(result.getString(4));
				customer.setAddress(result.getString(5));
				customer.setDateOfBirth(result.getDate(6).toLocalDate());
				BankAccount bankAccount = new BankAccount(result.getInt(8), result.getString(9), result.getDouble(10));
				customer.setAccount(bankAccount);
				return customer;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
