package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import bankapp.Menu;
import bankapp.BankAccount;
import bankapp.Transaction;
import java.util.List;
import java.awt.*;

public class TransactionTests {

	@Test
	public void testTransactionCreateDeposit() {
		BankAccount account = new BankAccount();
		account.deposit(100);
		List<Transaction> transactionHistory = account.getTransactionHistory();
		assertEquals(1, transactionHistory.size());
	}
}
