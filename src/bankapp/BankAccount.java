package bankapp;

public class BankAccount {

	private double balance;
	
	public BankAccount() {
		this.balance = 0;
	}
	/**
     	Deposits the given amount into the account.
     	@param amount must be non-negative.
     	*/
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
}
