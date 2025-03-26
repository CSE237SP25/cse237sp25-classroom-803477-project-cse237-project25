package ActionsTesting;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import AccountActions.DepositHandler;
import AccountActions.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class DepositHandlerTest {

    @Test
    void testHandleDeposit() {
        // Simulate user input of "150.75"
        String input = "150.75\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        BankAccount account = new BankAccount("Savings");
        DepositHandler.handle(account, scanner);

        assertEquals(150.75, account.getBalance(), 0.001);
    }
}
