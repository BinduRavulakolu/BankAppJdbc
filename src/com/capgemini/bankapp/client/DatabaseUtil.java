package com.capgemini.bankapp.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

	public static Connection getConnection() {
		String dburl = "jdbc:mysql://localhost:3306/bank";
		String username = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dburl, username, password);
			return connection;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
