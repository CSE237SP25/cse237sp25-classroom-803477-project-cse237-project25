package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

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
		account.deposit(25);
		account.withdraw(10);
		assertEquals(account.getCurrentBalance(), 15.0, 0.005);
	}
	
	@Test
	public void testNegativeWithdraw() {
	    	BankAccount account = new BankAccount();
	  	account.deposit(25);
	    	try {
	        	account.withdraw(-25);
	        	fail();
	    	} catch (IllegalArgumentException e){
	        	assertTrue(e != null);
	    	}
	}
	
	@Test
	public void testIllegalWithdraw() {
	    	BankAccount account = new BankAccount();
	    	account.deposit(25);
	    	try {
	        	account.withdraw(50);
	        	fail();
	    	} catch (IllegalArgumentException e){
	        	assertTrue(e != null);
	    	}
	}
}
