package bankapp;

public class SavingsAccount {
	private static int nextAccountNumber = 1000; // Simulated unique account number generator
	private int accountNumber;
	private double balance;
	private double interestRate; // Example: 2% interest rate
	private int userId;

	// Constructor to open a savings account
	public SavingsAccount(int userId, double initialDeposit) {
		this.accountNumber = nextAccountNumber++;
		this.userId = userId;
		this.balance = initialDeposit;
		this.interestRate = 0.02; // Example: 2% interest
		FileHandler.updateFile(this.accountNumber, this.userId, this.balance); // Update file with initial deposit
	}

	// Get account details
	public void getAccountDetails() {
		System.out.println("Savings Account #" + accountNumber);
		System.out.println("User ID: " + userId);
		System.out.println("Balance: $" + balance);
		System.out.println("Interest Rate: " + (interestRate * 100) + "%");
	}

	// Deposit function
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println("Deposited $" + amount + ". New Balance: $" + balance);
		} else {
			System.out.println("Invalid deposit amount.");
		}
	}

	// Withdraw function (optional: enforce minimum balance rule)
	public boolean withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			System.out.println("Withdrew $" + amount + ". New Balance: $" + balance);
			return true;
		} else {
			System.out.println("Insufficient funds or invalid withdrawal amount.");
			return false;
		}
	}

	// Transfer function (simplified, assumes another SavingsAccount object)
	public boolean transfer(SavingsAccount targetAccount, double amount) {
		if (withdraw(amount)) {
			targetAccount.deposit(amount);
			System.out.println("Transferred $" + amount + " to Account #" + targetAccount.accountNumber);
			return true;
		}
		return false;
	}

	// Interest calculation function
	public void calculateInterest() {
		double interest = balance * interestRate;
		balance += interest;
		System.out.println("Interest applied: $" + interest + ". New Balance: $" + balance);
	}

	// Getters (optional, if needed in other classes)
	public int getAccountNumber() { return accountNumber; }
	public double getBalance() { return balance; }
	public int getUserId() { return userId; }
}

