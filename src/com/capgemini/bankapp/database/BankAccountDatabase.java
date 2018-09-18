package com.capgemini.bankapp.database;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.Customer;
import com.sun.accessibility.internal.resources.accessibility;

public class BankAccountDatabase {
	public static HashSet<BankAccount> accounts = new HashSet<BankAccount>();
	public static HashSet<Customer> details = new HashSet<>();
	 
	static {
		BankAccount a1=new BankAccount(12344,"savings",20000.00);
		 BankAccount a2=new BankAccount(12345,"current",25000.00);
		 BankAccount a3=new BankAccount(12346,"savings",30000.00);
		 BankAccount a4=new BankAccount(12347,"savings",20000.00);
		details.add(new Customer(123,"bindu","12","bindu@gmail.com","Hyderabad",LocalDate.of(2017, 05, 16), a1));
		 details.add(new Customer(4322,"swathi","ithaws","swathi@gmail.com","hyderabad",LocalDate.of(2018, 06, 6), a2));
		 details.add(new Customer(4323,"navya","ayvan","navya@gmail.com","banglore",LocalDate.of(2017, 02, 17),a3));
		 details.add(new Customer(4324,"sumana","anamus","sumana@gmail.com","chennai",LocalDate.of(2018, 02, 17),a4));
		 
		 accounts.add(a1);
		 accounts.add(a2);
		 accounts.add(a3);
		 accounts.add(a4);	
	}
	public BankAccountDatabase() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	public static  HashSet<BankAccount> getAllBankDetails() {
		
		
		 return accounts;
		 
	}
	
	 public static HashSet<Customer> getAllCustomerDetails(){
		
		 
		 
		 return details;
	 }
	 
	 
}
