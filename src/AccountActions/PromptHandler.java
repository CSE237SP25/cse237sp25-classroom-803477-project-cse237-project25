package AccountActions;

import java.util.Scanner;

/**
 * Class for prompting user for inputs
 * Includes a method to get the user to open an account,
 * and another method to show user possible actions and execute them.
 */


public class PromptHandler {

    public static BankAccount openAccount(Scanner scanner) {
        System.out.println("Would you like to open a checking account? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
        
        // user opens checking account successfully
        if (response.equals("yes")) {
            System.out.println("Opening a checking account...");
            return new BankAccount("Checking");
        } else {
        	//user fails to open account.
            System.out.println("No account created. Goodbye!");
            return null;
        }
    }

    public static void commandLoop(BankAccount account, Scanner scanner) {
        if (account == null) return;

        String command;
        do {
        	// look for these commands from the user.
            System.out.println("\nEnter a command (deposit, withdraw, done): ");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
            // cases defined by user input, handler works on each instance.
                case "deposit":
                    DepositHandler.handle(account, scanner);
                    break;
                case "withdraw":
                    WithdrawHandler.handle(account, scanner);
                    break;
                case "done":
                    System.out.println("Thank you for banking with us, have a great day!");
                    break;
                default:
                    System.out.println("Command not in list of commands.");
            }

        } while (!command.equalsIgnoreCase("done"));
    }
}
