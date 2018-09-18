package com.capgemini.bankapp.client;

import java.sql.Connection;
import java.sql.SQLException;

import com.capgemini.bankapp.util.DbUtil;

public class CrudOperations {
	public static void main(String[] args) {
		try(Connection connection = DbUtil.getConnection();){
			if(connection != null)
				System.out.println("--connected -- ");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("--not connected -- ");
			
		}
	}

}
