package tests;

import bankapp.BankAccount;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

import bankapp.Menu;

public class MenuTests {

	private Menu m;


	@Before
	public void setUp() {
		m = new Menu();
	}

	@Test
	public void testCheckRegisterMenuSelection() {
		boolean actual = m.checkRegisterMenuSelection("register");
		assertEquals(true, actual);

		actual = m.checkRegisterMenuSelection("login");
		assertEquals(false, actual);

	}

	@Test
	public void testCheckLoginMenuSelection() {
		boolean actual = m.checkLoginMenuSelection("login");
		assertEquals(true, actual);

		actual = m.checkLoginMenuSelection("register");
		assertEquals(false, actual);

	}

	@Test
	public void testCheckYes() {
		boolean actual = m.checkYes("yes");
		assertEquals(true, actual);

		actual = m.checkYes("no");
		assertEquals(false, actual);


	}

	@Test
	public void testCheckNo() {
		boolean actual = m.checkNo("no");
		assertEquals(true, actual);

		actual = m.checkNo("yes");
		assertEquals(false, actual);

	}

	@Test
	public void testCheckIncorrectUserMenuInput() {
		boolean actual = m.checkIncorrectUserMenuInput("this is clearly wrong");
		assertEquals(true, actual);

		actual = m.checkIncorrectUserMenuInput("register");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserMenuInput("login");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserMenuInput("reGistER");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserMenuInput("LOGIN");
		assertEquals(false, actual);
	}

	@Test
	public void testCheckIncorrectUserBooleanInput() {
		boolean actual = m.checkIncorrectUserBooleanInput("this is clearly wrong");
		assertEquals(true, actual);

		actual = m.checkIncorrectUserBooleanInput("yes");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserBooleanInput("no");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserBooleanInput("yES");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserBooleanInput("NO");
		assertEquals(false, actual);
	}

	public void testWithdraw(){
		m.withdraw(25.0);
		assertEquals(m.getCurrentBalance(), 75.00, 0.005);
	}

	public void testWithdrawInsufficiently(){
		try {
			m.deposit(200);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	public void testWithdrawNegativeAmount(){
		try {
			m.withdraw(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

}
