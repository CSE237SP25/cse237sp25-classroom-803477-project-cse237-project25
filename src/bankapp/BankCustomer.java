package bankapp;

import java.util.ArrayList;

public class BankCustomer {
	// Create variable corresponding to number of bank customer (will be used for user id)
	static int numberOfBankCustomers = 0;
	
	// Other private variable declarations
	private String username;
	private String password;
	// UserID will be unique across BankCustomers
	private int userID;
	// Other private instance variables
	private BankRecord bankRecord;
	
	// Default constructor for BankCustomer
	public BankCustomer(String username, String password, BankRecord bankRecord) {
		this.username = username;
		this.password = password;
		this.userID = numberOfBankCustomers++;
		this.bankRecord = bankRecord;
		bankRecord.addUser(userID, this);
	}
	
	public boolean isPasswordCorrect(String enteredPassword) {
		if (this.password.equals(enteredPassword)) {
            return true;
        }
		else {
			return false;
		}
	}
	
	public int addNewAccount(BankRecord bankRecord) {
        BankAccount newAccount = new BankAccount();
        bankRecord.addAccount(this.userID, newAccount.getAccountID(), newAccount);
        return newAccount.getAccountID();
	}
	
	public void removeAccount(BankRecord bankRecord, int accountID) {
	    bankRecord.deleteAccount(this.userID, accountID);
	}
	
	public void transferFundsBetweenAccount(int fundLosingAccountID, int fundGainingAccountID, double amountToTransfer) {
		if (!this.bankRecord.getUserIDAccountIDs().get(this.userID).contains(fundLosingAccountID)) {
			throw new IllegalArgumentException("Fund losing account is not owned by user");
		}
		if (!this.bankRecord.getUserIDAccountIDs().get(this.userID).contains(fundGainingAccountID)) {
			throw new IllegalArgumentException("Fund gaining account is not owned by user");
		}
		BankAccount fundLosingAccount = this.bankRecord.getAccountIDAccounts().get(fundLosingAccountID);
		BankAccount fundGainingAccount = this.bankRecord.getAccountIDAccounts().get(fundGainingAccountID);
		
		// Should not have to double check if it is possible because the logic will be covered by withdraw and deposit
		fundLosingAccount.withdraw(amountToTransfer);
		fundGainingAccount.deposit(amountToTransfer);
	}
	
	// Getter and setter methods for BankCustomer
	public String getUsername() {
		return this.username;
	}
		
	public void setUsername(String u) {
		this.username = u;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public  ArrayList<Integer> getUserAccounts(BankRecord bankRecord) {
		ArrayList<Integer> accountIDs = bankRecord.getUserIDAccountIDs().get(this.userID);
		return accountIDs;
	}
}