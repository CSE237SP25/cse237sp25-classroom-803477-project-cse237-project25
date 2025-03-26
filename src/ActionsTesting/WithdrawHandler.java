package ActionsTesting;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import AccountActions.WithdrawHandler;
import AccountActions.BankAccount;
import static org.junit.jupiter.api.Assertions.*;

class WithdrawHandlerTest {

    @Test
    void testSuccessfulWithdrawal() {
        // Simulate input: withdraw 50.0
        String input = "50.0\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BankAccount account = new BankAccount("Checking");
        account.deposit(100.0); // Preload funds

        WithdrawHandler.handle(account, scanner);

        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    void testFailedWithdrawalDueToInsufficientFunds() {
        // Simulate input: withdraw 200.0
        String input = "200.0\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        BankAccount account = new BankAccount("Checking");
        account.deposit(100.0); // Not enough funds
        WithdrawHandler.handle(account, scanner);
        // Balance should remain unchanged
        assertEquals(100.0, account.getBalance(), 0.001);
    }
}
