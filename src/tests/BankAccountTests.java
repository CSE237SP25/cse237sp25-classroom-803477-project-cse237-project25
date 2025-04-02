package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import bankapp.BankAccount;

public class BankAccountTests {

    @Test
    public void testSimpleDeposit() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        assertEquals(25.0, account.getCurrentBalance(), 0.005);
    }

    @Test
    public void testNegativeDeposit() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-25));
    }

    @Test
    public void testSimpleWithdrawal() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        account.withdraw(5);
        assertEquals(20.0, account.getCurrentBalance(), 0.005);
    }

    @Test
    public void testNegativeWithdrawal() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5.25));
    }

    @Test
    public void testOverdrawingWithdrawal() {
        BankAccount account = new BankAccount();
        account.deposit(25);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(500));
    }

    @Test
    public void testInitialCreditBalance() {
        BankAccount account = new BankAccount();
        assertEquals(0, account.getCreditBalance(), 0.001);
    }

    @Test
    public void testBorrowCredit() {
        BankAccount account = new BankAccount();
        account.borrowCredit(200);
        assertEquals(200, account.getCreditBalance(), 0.001);
    }

    @Test
    public void testBorrowNegativeCredit() {
        BankAccount account = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> account.borrowCredit(-100));
    }

    @Test
    public void testRepayCredit() {
        BankAccount account = new BankAccount();
        account.borrowCredit(300);
        account.repayCredit(100);
        assertEquals(200, account.getCreditBalance(), 0.001);
    }

    @Test
    public void testRepayNegativeCredit() {
        BankAccount account = new BankAccount();
        account.borrowCredit(300);
        assertThrows(IllegalArgumentException.class, () -> account.repayCredit(-50));
    }

    @Test
    public void testRepayMoreThanCredit() {
        BankAccount account = new BankAccount();
        account.borrowCredit(150);
        assertThrows(IllegalArgumentException.class, () -> account.repayCredit(200));
    }
}