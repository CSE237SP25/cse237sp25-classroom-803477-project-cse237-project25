package banking;
import banking.Database; //future class, handle reading from our files for persistence
import banking.Option;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    
    private Database dataHandler;
    private User activeUser;
    private List<Option> publicOptions;
    private List<Option> privateOptions;
    private Scanner keyboardInput;
    private boolean running;


    public Menu(Scanner keyboardInput, Database dataHandler) {
        this.dataHandler = dataHandler;
        this.keyboardInput = keyboardInput;
        this.activeUser = null;
        this.publicOptions = new ArrayList<>();
        publicOptions.add(new Option("Login to account", this::login));
        publicOptions.add(new Option("Create account", this::signUp));
        publicOptions.add(new Option("Exit",this::shutDown));
        this.privateOptions = new ArrayList<>();
        privateOptions.add(new Option("Check Balance",this::getBalance));
        privateOptions.add(new Option("Issue Charge",this::issueCharge));
        privateOptions.add(new Option("Deposit",this::deposit));
        privateOptions.add(new Option("Withdraw",this::withdraw));
        //privateOptions.add(new Option("Get statement",this.activeUser::getStatement));
        privateOptions.add(new Option("Logout",this::logOut));
        this.running = false;
    }
    
    public Database getDataHandler() {
    	return dataHandler;
    }

    public void run() {
        this.running = true;
        while (this.running) {
            printScopedMenu();
        }
    }

    public void shutDown() {
        this.running = false;
    }

    public void printScopedMenu() {
        if (this.activeUser != null) {
            this.printMenu(privateOptions);
        } else {
            this.printMenu(publicOptions);
        }
    }

    public void getBalance() {
        System.out.print("Your balance is currently : "+ activeUser.getBalance());
    }

    public void issueCharge() {
        System.out.print("Who would you like to charge (their user_id): ");
        int subject = keyboardInput.nextInt();
        System.out.print("Charge amount: ");
        double chargeAmount = keyboardInput.nextDouble();
        System.out.print("Charge description: ");
        String chargeDesc = keyboardInput.nextLine();
        activeUser.issueCharge(chargeAmount, chargeDesc); 
        // User class needs to implement charge targets , although maybe should be a database function since it requires authorization
        System.out.println("Charge issued.");
    }

    public void deposit() {
        System.out.println("How much would you like to deposit?");
        double amount = keyboardInput.nextDouble();
        activeUser.deposit(amount); // User class still needs to implement
        //System.out.println("Success! Your new balance is: " + activeUser.getBalance());
    }

    public void withdraw() {
        System.out.println("How much would you like to withdraw?");
        double amount = keyboardInput.nextDouble();
        if (activeUser.getBalance() >= amount) {
            //activeUser.withdraw(amount); // User class still needs to implement
            System.out.println("Successfully withdrew" + amount + "! Here is your cash: $$$");
        } else {
            System.out.println("There is insufficient balance in your account to cover the withdraw...");
        }
    }

    public void printMenu(List<Option> items) {
        int i = 1;
        for (Option item : items) {
            System.out.println(i + ". " + item.getOptionName());
            i++;
        }
        int userChoice = keyboardInput.nextInt();
        i = 1;
        for (Option item : items) {
            if (i == userChoice) {
                keyboardInput.nextLine(); // clears the newline from the item selection
                item.execute();
            }
            i++;
        }
    }

    private void login() {
        System.out.println("Enter username: ");
        String username = keyboardInput.nextLine();
    
        System.out.println("Enter password: ");
        String password = keyboardInput.nextLine();
    
        if (dataHandler.doesUserExist(username)) {
            if(authenticateUserPass(username, password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed: password hashes do not match");
            }
        } else {
            System.out.println("Login failed: user does not exist");
        }
    }

    public boolean authenticateUserPass(String username,String password) {
        User requestedAccount = dataHandler.getUserData(username);
        if (requestedAccount.getHashedPassword().equals(Menu.hashPassword(password))) {
            this.activeUser = requestedAccount;
            return true;
        }
        return false;
    }
    
    private void signUp() {
        System.out.println("Enter new username: ");
        String username = keyboardInput.nextLine();

        System.out.println("Enter new accountNumber: ");
        String accountNumber = keyboardInput.nextLine();
    
        System.out.println("Enter password: ");
        String password = keyboardInput.nextLine();

        System.out.println("Confirm password: ");
        String passwordConfirmation = keyboardInput.nextLine();

        if(!password.equals(passwordConfirmation)) {
            System.out.println("Passwords do not match, exiting...");
        } else {
            if (createUser(username,accountNumber, passwordConfirmation, 0)) {
                System.out.println("Account created successfully!");
            } else {
                System.out.println("Account already exists.");
            }
        }
    }

    public boolean createUser(String username, String accountNumber, String password, double balance) {
        if (!dataHandler.doesUserExist(username)) {
            User userToRegister = new User(username,accountNumber, Menu.hashPassword(password),balance);
            this.activeUser = dataHandler.createUser(userToRegister);
            return true;
        }
        return false;
    }

    public void logOut() {
        this.activeUser = null;
        System.out.println("Logged out Succesfully");
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            // Convert hash bytes to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            String hashedPassword = sb.toString();
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.");
        }
}

}