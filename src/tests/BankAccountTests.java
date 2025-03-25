package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.BankCustomer;
import bankapp.BankRecord;

public class BankAccountTests {

	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount();
		
		//2. Call the method being tested
		account.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		BankAccount account = new BankAccount();

		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test
	public void testSimpleWithdrawal() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount();
				
		//2. Call the method being tested
		account.deposit(25);
		account.withdraw(5);
				
		//3. Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 20.0, 0.005);
	}
	
	@Test
	public void testNegativeWithdrawal() {
		//1. Create object to be tested
		BankAccount account = new BankAccount();

		try {
			account.withdraw(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	
	}
	
	@Test
	public void testOverWithdrawal() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount();
		
		account.deposit(25);
				
		try {
			account.withdraw(50);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	
	@Test
	public void testUniqueAccountID() {
		BankRecord bankRecord = new BankRecord();
        BankCustomer customer = new BankCustomer("Alex", null, bankRecord);
        int userID = customer.getUserID();

        // Create multiple accounts
        int accountID1 = customer.addNewAccount(bankRecord);
        int accountID2 = customer.addNewAccount(bankRecord);
        int accountID3 = customer.addNewAccount(bankRecord);

        // Collect account IDs in a Set (ensures uniqueness)
        Set<Integer> accountIDs = new HashSet<>();
        accountIDs.add(accountID1);
        accountIDs.add(accountID2);
        accountIDs.add(accountID3);

        // Verify that all account IDs are unique
        assertEquals(3, accountIDs.size());

        // Ensure the created account IDs are correctly stored
        List<Integer> userAccounts = customer.getUserAccounts(bankRecord);
        assertTrue(userAccounts.contains(accountID1));
        assertTrue(userAccounts.contains(accountID2));
        assertTrue(userAccounts.contains(accountID3));
	}
	
	@Test
	public void testSetMinimumBalance() {
		BankAccount account = new BankAccount(100);
		
		account.setMinimumBalance(50);
		
		assertEquals(50.0, account.getMinimumBalance(), 0.005);
	}
	
	@Test
	public void testNegativeMinimumBalance() {
		BankAccount account = new BankAccount(100);

		try {
			account.withdraw(-100);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test 
	public void testWithdrawBelowMinimumBalance(){
		BankAccount account = new BankAccount(50);
		account.deposit(100);
		
		try {
			account.withdraw(60);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test 
	public void testWithdrawAboveMinimumBalance(){
		BankAccount account = new BankAccount(50);
		account.deposit(100);
		
		account.withdraw(40);
		
		assertEquals(60.0, account.getCurrentBalance(), 0.005);
	}
	
	@Test 
	public void testWithdrawExactAmountToReachMinimumBalance(){
		BankAccount account = new BankAccount(50);
		account.deposit(100);
		
		account.withdraw(50);
		
		assertEquals(50.0, account.getCurrentBalance(), 0.005);
	}
	
}
