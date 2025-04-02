package bankapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		transactionHistory.add(new Transaction("Deposit", amount, getCurrentTime()));
	}
	
	public void withdraw(double amount) {
		if (amount > balance)   {
			 throw new IllegalArgumentException("Insufficient funds.");
		} 
		if (amount < 0) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount.");
		}
		this.balance -= amount;
		transactionHistory.add(new Transaction("Withdrawal", amount, getCurrentTime()));
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public String getCurrentTime() {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("h:mm:ss a");
        return localDateFormat.format(new Date());
	}
	
	public double getFinalBalance(FixedDeposit fixedDeposit) {
		transactionHistory.add(new Transaction("Fixed Deposit", fixedDeposit.getFinalDeposit(), getCurrentTime()));
		return this.balance + fixedDeposit.getFinalDeposit();
	}

	
	public int getTransactionCount() {
	    return transactionHistory.size();
	}
	
	public void viewTransactionHistory() {
		if (getTransactionCount() == 0) {
			System.out.println("No recent transaction history");
		}
		else {
			System.out.println("Your Recent Transaction History:");
			for (Transaction t: transactionHistory) {
				t.showTransaction();
			}
		}
	}

	public void transferTo(BankAccount recipient, double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Transfer amount must be positive.");
		}
		if (this.balance < amount) {
			throw new IllegalArgumentException("Insufficient funds.");
		}

		this.withdraw(amount);
		recipient.deposit(amount);

		this.transactionHistory.add(new Transaction("Transfer Sent", amount, getCurrentTime()));
		recipient.transactionHistory.add(new Transaction("Transfer Received", amount, getCurrentTime()));
	}
	
	public void logout(Map<Integer, List<Object>> accounts) {
		accounts.clear();
		transactionHistory.clear();
		balance = 0.0;
		System.out.println("You have successfully logged out");
	}
}
