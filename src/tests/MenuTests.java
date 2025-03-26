package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

public class MenuTests {
@Test
    public void testBalanceHistoryOptionDisplaysCorrectly() {
        String simulatedInput = "2\n50\n3\n20\n4\n5\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        try {
            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            BankAccount account = new BankAccount();
            Menu.showMainMenu(account);

            String output = testOut.toString();

            assertTrue(output.contains("Balance History"));
            assertTrue(output.contains("Step 0: $0.0"));
            assertTrue(output.contains("Step 1: $50.0"));
            assertTrue(output.contains("Step 2: $30.0"));

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
