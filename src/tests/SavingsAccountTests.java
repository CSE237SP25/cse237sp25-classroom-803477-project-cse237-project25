package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import bankapp.SavingsAccount;

public class SavingsAccountTests {

	@Test
	public void testOpenSavingsAccount() {
		// Create a savings account with an initial deposit of $500
		SavingsAccount account = new SavingsAccount(101, 500.0);

		// Verify initial balance
		assertEquals(500.0, account.getBalance(), 0.005);
	}

	@Test
	public void testDeposit() {
		SavingsAccount account = new SavingsAccount(101, 100.0);
		account.deposit(50.0);
		assertEquals(150.0, account.getBalance(), 0.005);
	}

	@Test
	public void testNegativeDeposit() {
		SavingsAccount account = new SavingsAccount(101, 100.0);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			account.deposit(-20.0);
		});
		assertEquals("Invalid deposit amount.", exception.getMessage());
	}

	@Test
	public void testWithdrawValid() {
		SavingsAccount account = new SavingsAccount(101, 200.0);
		assertTrue(account.withdraw(50.0));
		assertEquals(150.0, account.getBalance(), 0.005);
	}

	@Test
	public void testWithdrawInsufficientFunds() {
		SavingsAccount account = new SavingsAccount(101, 50.0);
		assertFalse(account.withdraw(100.0)); // Should fail
		assertEquals(50.0, account.getBalance(), 0.005);
	}

	@Test
	public void testTransferValid() {
		SavingsAccount sender = new SavingsAccount(101, 500.0);
		SavingsAccount receiver = new SavingsAccount(102, 300.0);

		assertTrue(sender.transfer(receiver, 200.0));
		assertEquals(300.0, sender.getBalance(), 0.005);
		assertEquals(500.0, receiver.getBalance(), 0.005);
	}

	@Test
	public void testTransferInsufficientFunds() {
		SavingsAccount sender = new SavingsAccount(101, 100.0);
		SavingsAccount receiver = new SavingsAccount(102, 300.0);

		assertFalse(sender.transfer(receiver, 500.0)); // Should fail
		assertEquals(100.0, sender.getBalance(), 0.005);
		assertEquals(300.0, receiver.getBalance(), 0.005);
	}

	@Test
	public void testCalculateInterest() {
		SavingsAccount account = new SavingsAccount(101, 1000.0);
		account.calculateInterest();
		assertEquals(1020.0, account.getBalance(), 0.005); // Assuming 2% interest
	}
}
