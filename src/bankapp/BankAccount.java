package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

	private double balance;
	private List<Transaction> transactionHistory;
	
	public BankAccount() {
		this.balance = 0;
		this.transactionHistory = new ArrayList<>();
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
		Transaction deposit = new Transaction("deposit", amount);
		transactionHistory.add(deposit);
	}
	
	public void withdraw(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		if(amount > this.balance) {
			throw new IllegalArgumentException();
		}
		this.balance -= amount;
		Transaction withdraw = new Transaction("withdraw", amount);
		transactionHistory.add(withdraw);
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public List<Transaction> getTransactionHistory(){
		return transactionHistory;
	}
}
