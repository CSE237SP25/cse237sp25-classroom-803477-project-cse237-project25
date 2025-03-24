package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.FixedDeposit;

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
	public void testSimpleWithdraw() {
		BankAccount account = new BankAccount();
		account.deposit(25.0);
		account.withdraw(10.0);
		assertEquals(account.getCurrentBalance(), 15.0, 0.005);
	}
	
	@Test
	public void testNegativeWithdraw() {
		BankAccount account = new BankAccount();
		try {
			account.withdraw(-5);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test
	public void testInsufficientWithdraw() {
		BankAccount account = new BankAccount();
		account.deposit(10);
		try {
			account.withdraw(15);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	@Test
	public void testFinalBalance() {
		// Step 1: Set up the account and deposit
        BankAccount account = new BankAccount();
        account.deposit(1000); 
        // Step 2: Create and process a fixed deposit
        FixedDeposit fd = new FixedDeposit();
        fd.processSelection(1, 2000); 
        double expectedFD = 2000 * Math.pow(1 + 0.0393, 7);
        double expectedTotal = 1000 + expectedFD;
        double actualTotal = account.getFinalBalance(fd);
        // Step 3: Assert
        assertEquals(expectedTotal, actualTotal, 0.01); 
	}

}
