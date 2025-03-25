package bankapp;

public class BankAccount {
	// Declaration of private instance variables
	private static int numberOfAccounts = 0;
	private int accountID;
	private double balance;
	private double minimumBalance;
	
	// Default constructor for BankCustomer
	public BankAccount() {
		this.accountID = numberOfAccounts++;
		this.balance = 0;
		this.minimumBalance = 0;
	}
	
	// Minimum balance constructor for BankCustomer
	public BankAccount(double minimumBalance) {
		this.accountID = numberOfAccounts++;
		this.balance = 0;
		this.minimumBalance = minimumBalance;
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
	}
	
	public void withdraw(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		if(this.balance - amount < minimumBalance) {
			throw new IllegalArgumentException();
		}
		
		if(this.balance >= amount) {
		this.balance -= amount;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	// Getter and setter methods for BankAccount
	public void setMinimumBalance(double minimumBalance) {
		if(minimumBalance < 0) 
			throw new IllegalArgumentException();
		this.minimumBalance = minimumBalance;
	}
	
	public double getMinimumBalance() {
		return minimumBalance;
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public int getAccountID() {
		return this.accountID;
	}
}
