package ActionsTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import AccountActions.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount checkingAccount;
    
    @BeforeEach
    void setUp() {
    	// initialize our empty checking account
        checkingAccount = new BankAccount("Checking");
    }

    @Test
    void testInitialBalanceIsZero() {
    	// account starts at zero dollars
        assertEquals(0.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testDepositIncreasesBalance() {
    	// does the deposit put the right amount into the account?
        checkingAccount.deposit(100.0);
        assertEquals(100.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdrawReducesBalanceWhenSufficientFunds() {
        checkingAccount.deposit(200.0);
        boolean success = checkingAccount.withdraw(150.0);
        // you should be able to withdraw an amount less than the balance.
        assertTrue(success);
        assertEquals(50.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdrawFailsWhenInsufficientFunds() {
        checkingAccount.deposit(50.0);
        boolean success = checkingAccount.withdraw(100.0);
        assertFalse(success);
        // shouldn't be able to withdraw more than balance
        assertEquals(50.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testGetAccountType() {
    	// check our accountType (will be useful when savings implemented)
        assertEquals("Checking", checkingAccount.getAccountType());
    }
}
