package bankapp;

 MoneyReciever
import java.util.Scanner;

public class Menu {
    private Bank theBank;
    private BankAccount currentAccount;
    private Scanner keyboardInput;
    
    public Menu() {
        theBank = new Bank();
        keyboardInput = new Scanner(System.in);
        currentAccount = null;
    }
    
    public void displayMainMenu() {
        System.out.println("\n=== Banking Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    
    public void displayLoggedInMenu() {
        System.out.println("\n=== Account Menu ===");
        System.out.println("1. Deposit");
        System.out.println("2. Transfer Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Logout");
        System.out.println("5. Change Password");
        System.out.println("6. Change Username");
        System.out.print("Enter your choice: ");
    }
    
    public void createAccount() {
        System.out.print("Enter username: ");
        String username = keyboardInput.next();
        System.out.print("Enter password: ");
        String password = keyboardInput.next();
        System.out.print("Enter account number: ");
        String accountNumber = keyboardInput.next();
        
        try {
            theBank.createAccount(username, password, accountNumber);
            System.out.println("Account created successfully!");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void login() {
        System.out.print("Enter username: ");
        String username = keyboardInput.next();
        System.out.print("Enter password: ");
        String password = keyboardInput.next();
        
        try {
            currentAccount = theBank.login(username, password);
            System.out.println("Login successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void processUserInput(double amount) {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        currentAccount.deposit(amount);
    }
    
    public void transferMoney() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        
        System.out.print("Enter recipient username: ");
        String recipientUsername = keyboardInput.next();
        System.out.print("Enter transfer amount: ");
        double amount = keyboardInput.nextDouble();
        
        BankAccount recipient = theBank.getAccount(recipientUsername);
        if (recipient == null) {
            System.out.println("Recipient account not found!");
            return;
        }
        
        try {
            currentAccount.transfer(recipient, amount);
            System.out.println("Transfer successful!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void checkBalance() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.println("Current balance: $" + currentAccount.getBalance());
    }
    
    public void changePassword() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter current password: ");
        String currentPwd = keyboardInput.next();

        if (!currentAccount.validatePassword(currentPwd)) {
            System.out.println("Incorrect password.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPwd = keyboardInput.next();
        currentAccount.setPassword(newPwd);
        System.out.println("Password changed successfully!");
    }
    
    public void changeUsername() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter current password: ");
        String currentPwd = keyboardInput.next();

        if (!currentAccount.validatePassword(currentPwd)) {
            System.out.println("Incorrect password.");
            return;
        }

        System.out.print("Enter new username: ");
        String newUsername = keyboardInput.next();

        BankAccount existing = theBank.getAccount(newUsername);
        if (existing != null) {
            System.out.println("Username already taken.");
            return;
        }

        String oldUsername = currentAccount.getUsername();
        currentAccount.setUsername(newUsername);
        theBank.updateUsername(oldUsername, currentAccount);

        System.out.println("Username changed successfully!");
    }
    
    public void logout() {
        currentAccount = null;
        System.out.println("Logged out successfully!");
    }
    
    public BankAccount getCurrentAccount() {
        return currentAccount;
    }
}

public class Menu {

}
 main
