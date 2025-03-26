package AccountActions;

import java.util.Scanner;

/**
 * Class for letting user deposit an amount into their newly created account
 * User deposits amount into their account.
 */

public class DepositHandler {
    public static void handle(BankAccount account, Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        //very simple deposit function.
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }
}
