package tests;

public class MenuTests {

    @Test
    public void testTransactionHistoryDisplay() {
        String simulatedInput = "2\n100\n3\n30\n5\n6\n"; // deposit, withdraw, view tx, exit

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
            assertTrue(output.contains("Transaction History"));
            assertTrue(output.contains("Deposited: $100.0"));
            assertTrue(output.contains("Withdrew: $30.0"));

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
