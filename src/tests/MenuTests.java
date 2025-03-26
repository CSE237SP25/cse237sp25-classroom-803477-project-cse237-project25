package tests;

 MoneyReciever
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bankapp.BankAccount;
import bankapp.Menu;

public class MenuTests {
	@Test
	public void testUserDeposit() {
		// 1. Create object being tested
		Menu m = new Menu();
		// 2. Call method being tested
		m.processUserInput(25);
		// 3. Use assertions to verify correctness
		BankAccount account = m.getCurrentAccount();
		assertEquals(account.getBalance(), 25.0, 0.005);
	}

public class MenuTests {

 main
}
