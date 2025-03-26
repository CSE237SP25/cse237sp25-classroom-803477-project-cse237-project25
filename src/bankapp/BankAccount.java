package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

	private double balance;
	
	private String username;
	private String password;
	
	public BankAccount() {
		this.username = "";
		this.password = "";
		this.balance = 0;
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
	}

	public void withdraw(double amount){
	        if(amount < 0 || amount > this.balance){
	        	throw new IllegalArgumentException();
	        }
	        this.balance -= amount;        
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public void setUsername(String u) {
		this.username = u;
	}
	
	public void setPassword(String p) {
		this.password = p;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean validatePassword(String inputPassword) {
		return inputPassword != null && inputPassword.equals(this.password);
	}
}
