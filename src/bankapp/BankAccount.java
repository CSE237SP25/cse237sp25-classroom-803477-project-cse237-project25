package bankapp;

public class BankAccount {
 MoneyReciever
	private double balance;
	private String username;
	private String accountNumber;
	private String password;

	public BankAccount(String username, String password, String accountNumber) {
		this.balance = 0;
	    this.username = username;
	    this.password = password;
	    this.accountNumber = accountNumber;
	}

	public void deposit(double amount) {
		if (amount < 0) {


	private double balance;
	
	public BankAccount() {
		this.balance = 0;
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
 main
			throw new IllegalArgumentException();
		}
		this.balance += amount;
	}
 MoneyReciever

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}
		if (amount > balance) {
			throw new IllegalStateException("Insufficient funds");
		}
		this.balance -= amount;
	}

	public double getBalance() {
		return this.balance;
	}
	
	public String getUsername() {
        return username;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
    
    public void transfer(BankAccount recipient, double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Transfer amount cannot be negative");
        }
        if(amount > balance) {
            throw new IllegalStateException("Insufficient funds for transfer");
        }
        this.withdraw(amount);
        recipient.deposit(amount);
    }

	
	public double getCurrentBalance() {
		return this.balance;
	}
 main
}
