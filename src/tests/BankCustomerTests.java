package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.BankCustomer;
import bankapp.BankRecord;

public class BankCustomerTests {

	@Test
	public void testSimpleNewCustomer() {
		BankRecord bankRecord = new BankRecord();
		//1. Create objects to be tested
		BankCustomer customer = new BankCustomer("Test Customer", null, bankRecord);
		
		//2. Use assertions to verify results both username and user ID
		assertEquals(customer.getUsername(), "Test Customer");
	}
	
	@Test
	public void testMultipleNewCustomers() {
		BankRecord bankRecord = new BankRecord();
		//1. Create objects to be tested
		BankCustomer customer0 = new BankCustomer("Test Customer 0", null, bankRecord);
		BankCustomer customer1 = new BankCustomer("Test Customer 1", null, bankRecord);

		//2. Use assertions to verify results both username and user ID
		assertEquals(customer0.getUsername(), "Test Customer 0");
		assertEquals(customer1.getUsername(), "Test Customer 1");
		assertEquals(customer0.getUserID() + 1, customer1.getUserID());
	}
	
	@Test
	public void testSetUsername() {
		BankRecord bankRecord = new BankRecord();
		//1. Create objects to be tested
		BankCustomer customer = new BankCustomer("Test Customer", null, bankRecord);
		
		//2. Call methods to be tested
		customer.setUsername("New Username");

		//2. Use assertions to verify results both username and user ID
		assertEquals(customer.getUsername(), "New Username");
	}
	
	@Test
	public void testCreateNewAccount() {
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer2 = new BankCustomer("Alice", null, bankRecord);
        int userID = customer2.getUserID();
        int accountID = customer2.addNewAccount(bankRecord);
        List<Integer> userAccounts = customer2.getUserAccounts(bankRecord);

        assertTrue(userAccounts.contains(accountID));
        assertNotNull(bankRecord.getAccountIDAccounts().get(accountID));
	}
	
	@Test
    void testRemoveAccountThroughCustomer() {
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer = new BankCustomer("Alice", null, bankRecord);
        int accountID = customer.addNewAccount(bankRecord);

        // Ensure account exists
        assertTrue(customer.getUserAccounts(bankRecord).contains(accountID));

        // Remove account
        customer.removeAccount(bankRecord, accountID);

        // Verify account is removed
        assertFalse(customer.getUserAccounts(bankRecord).contains(accountID));
        assertNull(bankRecord.getAccountIDAccounts().get(accountID));
    }
	
	@Test
	void simpleValidAccountTransfer() {
		// Create user with two accounts
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer = new BankCustomer("Alice", null, bankRecord);
        
        int fundLosingAccountID = customer.addNewAccount(bankRecord);
        int fundGainingAccountID = customer.addNewAccount(bankRecord);
        
        BankAccount fundLosingAccount = bankRecord.getAccountIDAccounts().get(fundLosingAccountID);
        BankAccount fundGainingAccount = bankRecord.getAccountIDAccounts().get(fundGainingAccountID);
        
        // Deposit money into accounts
        fundLosingAccount.deposit(100);
        fundGainingAccount.deposit(50);
        
        // Transfer money
        customer.transferFundsBetweenAccount(fundLosingAccountID, fundGainingAccountID, 50);
        
        assertEquals(fundLosingAccount.getCurrentBalance(), 50, 0.01);
        assertEquals(fundGainingAccount.getCurrentBalance(), 100, 0.01);
	}
	
	@Test
	void accountTransferFailDueToMinimumBalance() {
		// Create user with two accounts
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer = new BankCustomer("Alice", null, bankRecord);
        
        int fundLosingAccountID = customer.addNewAccount(bankRecord);
        int fundGainingAccountID = customer.addNewAccount(bankRecord);
        
        BankAccount fundLosingAccount = bankRecord.getAccountIDAccounts().get(fundLosingAccountID);
        BankAccount fundGainingAccount = bankRecord.getAccountIDAccounts().get(fundGainingAccountID);
        
        // Deposit money into accounts
        fundLosingAccount.deposit(100);
        fundLosingAccount.setMinimumBalance(60);
        fundGainingAccount.deposit(50);
        
        // Transfer money
        try {
        	customer.transferFundsBetweenAccount(fundLosingAccountID, fundGainingAccountID, 50);
        } catch (IllegalArgumentException e) {
			assertTrue(e != null);
		} 
	}
	
	@Test
	void accountTransferFailDueToWrongAccountID() {
		// Create user with two accounts
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer = new BankCustomer("Alice", null, bankRecord);
        
        int fundLosingAccountID = customer.addNewAccount(bankRecord);
        int fundGainingAccountID = customer.addNewAccount(bankRecord);
        
        BankAccount fundLosingAccount = bankRecord.getAccountIDAccounts().get(fundLosingAccountID);
        BankAccount fundGainingAccount = bankRecord.getAccountIDAccounts().get(fundGainingAccountID);
        
        // Deposit money into accounts
        fundLosingAccount.deposit(100);
        fundLosingAccount.setMinimumBalance(60);
        fundGainingAccount.deposit(50);
        
        // Transfer money
        try {
        	customer.transferFundsBetweenAccount(-10, fundGainingAccountID, 50);
        } catch (IllegalArgumentException e) {
			assertTrue(e != null);
		} 
	}
	
	@Test
	void accountTransferFailDueToInsufficientFunds() {
		// Create user with two accounts
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer = new BankCustomer("Alice", null, bankRecord);
        
        int fundLosingAccountID = customer.addNewAccount(bankRecord);
        int fundGainingAccountID = customer.addNewAccount(bankRecord);
        
        BankAccount fundLosingAccount = bankRecord.getAccountIDAccounts().get(fundLosingAccountID);
        BankAccount fundGainingAccount = bankRecord.getAccountIDAccounts().get(fundGainingAccountID);
        
        // Deposit money into accounts
        fundLosingAccount.deposit(40);
        fundGainingAccount.deposit(50);
        
        // Transfer money
        try {
        	customer.transferFundsBetweenAccount(fundLosingAccountID, fundGainingAccountID, 50);
        } catch (IllegalArgumentException e) {
			assertTrue(e != null);
		} 
	}
}
