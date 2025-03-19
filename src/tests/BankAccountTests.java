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
	public void testSimpleWithdrawal(){
		BankAccount account = new BankAccount();

		account.deposit(30);

		account.withdraw(25);

		assertEquals(account.getCurrentBalance(), 5.0, 0.005);
	 }

	 @Test
	 public void testWithdrawalBounds(){
		BankAccount account = new BankAccount();

		try{
			account.withdraw(10);
			fail();
		} catch (IllegalArgumentException e){
			assertTrue(e != null);
		}
	 }

	@Test
	public void testNegativeWithdrawal() {

		BankAccount account = new BankAccount();

		try {
			account.withdraw(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}
	
	 @Test
	    public void testInvalidWithdraw(){
	        BankAccount account = new BankAccount();
	        try {
	        	account.withdraw(10);
	            fail();
	        } catch (IllegalArgumentException e) {
	            assertTrue(e != null);
	        }
	    }

}
