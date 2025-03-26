package AccountActions;

import java.util.Scanner;

/**
 * Class for letting user withdraw an amount from their newly created account
 * User withdraws amount if possible, stops if not possible.
 */


public class WithdrawHandler {
    public static void handle(BankAccount account, Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        // allow user to withdraw amount if funds allot for it.
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } 
        // user with insufficient funds can't withdraw.
        else {
            System.out.println("Insufficient funds. Current balance: " + account.getBalance());
        }
    }
}
