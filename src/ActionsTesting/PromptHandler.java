package ActionsTesting;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import AccountActions.BankAccount;
import AccountActions.PromptHandler;

import static org.junit.jupiter.api.Assertions.*;

class PromptHandlerTest {

    @Test
    void testOpenAccountYes() {
        String input = "yes\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BankAccount account = PromptHandler.openAccount(scanner);
        assertNotNull(account);
        assertEquals("Checking", account.getAccountType());
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    void testOpenAccountNo() {
        String input = "no\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        BankAccount account = PromptHandler.openAccount(scanner);
        assertNull(account);
    }

    @Test
    void testCommandLoopDepositWithdrawDone() {
        // Simulates: deposit 100 → withdraw 30 → done
        String input = String.join("\n",
            "deposit", "100",
            "withdraw", "30",
            "done"
        ) + "\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        BankAccount account = new BankAccount("Checking");

        PromptHandler.commandLoop(account, scanner);

        assertEquals(70.0, account.getBalance(), 0.001);
    }
}
