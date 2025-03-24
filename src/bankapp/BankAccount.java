package bankapp;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		SimpleDateFormat localDateFormat = new SimpleDateFormat("h:mm:ss a");
        String time = localDateFormat.format(new Date());
		transactionHistory.add(new Transaction("Deposit", amount, time));
	}
	
	public void withdraw(double amount) {
		if (amount > balance)   {
			 throw new IllegalArgumentException("Insufficient funds.");
		} 
		if (amount < 0) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount.");
		}
		this.balance -= amount;
		SimpleDateFormat localDateFormat = new SimpleDateFormat("h:mm:ss a");
        String time = localDateFormat.format(new Date());
		transactionHistory.add(new Transaction("Withdrawal", amount, time));
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public double getFinalBalance(FixedDeposit fixedDeposit) {
		return this.balance+fixedDeposit.getFinalDeposit();
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
}
