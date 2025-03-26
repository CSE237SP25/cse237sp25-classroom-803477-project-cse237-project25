package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

public class TransactionTests {
	
	@Test
	public void testEmptyTransaction() {
		BankAccount account = new BankAccount();
		assertEquals(0, account.getTransactionCount());
	}
	
	@Test
	public void testTransactionCount() {
		BankAccount account = new BankAccount();
		account.deposit(25);
		account.deposit(50);
		assertEquals(2, account.getTransactionCount());
	}
	
	@Test
	public void testBothTransactions() {
		BankAccount account = new BankAccount();
		account.deposit(25);
		account.withdraw(20);
		account.withdraw(5);
		assertEquals(3, account.getTransactionCount());
	}
}