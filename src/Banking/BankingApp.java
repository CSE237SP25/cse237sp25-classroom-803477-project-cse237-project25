import banking.Menu;
import banking.User;
import java.util.Scanner;

public class BankingApp {
    private static Scanner scanner = new Scanner(System.in);
    private Menu menu;

    public BankingApp() {
        this.menu = new Menu();
    }

    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        app.run();
    }

    private void run() {
        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (menu.authenticateUserPass(username, password)) {
                System.out.println("Login successful!");
                transactionMenu();
            } else {
                System.out.println("Login failed. Try again.\n");
            }
        }
    }

    private void transactionMenu() {
        User activeUser = menu.getActiveUser();//TODO: add a user getter from menu!
        while (true) {
            System.out.println("\n--- Transaction Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Issue a Charge");
            System.out.println("5. Request Statement");
            System.out.println("6. Logout");

            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.printf("Balance: $%.2f\n", activeUser.getBalance());
                    break;
                case 2:
                    System.out.print("Amount to deposit: ");
                    double depAmount = Double.parseDouble(scanner.nextLine());
                    activeUser.deposit(depAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Amount to withdraw: ");
                    double withAmount = Double.parseDouble(scanner.nextLine());
                    if (activeUser.withdraw(withAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.print("Charge amount: ");
                    double chargeAmount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Charge description: ");
                    String chargeDesc = scanner.nextLine();
                    activeUser.issueCharge(chargeAmount, chargeDesc); //Charge the user yourself for now
                    System.out.println("Charge issued.");
                    break;
                case 5:
                    activeUser.printStatement();
                    break;
                case 6:
                    menu.logout();//TODO
                    System.out.println("Logged out.\n");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}