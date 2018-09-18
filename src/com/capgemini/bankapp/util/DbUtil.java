package com.capgemini.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	static final String DB_URL="jdbc:mysql://localhost/bank";
	static final String USER_NAME="root";
	static final String PASSWORD="root";
	public static Connection getConnection()
	{
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			if(connection != null)
				System.out.println("--connected -- ");
			
			
			
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
			System.out.println("-- failed to connect --");
		}
		return connection;
	}

}
