package login;
import java.util.Scanner;

/**
 * Manages user login and registration functionality.
 * This class provides an interface for users to login or create a new account.
 */
public class LoginManager {
    private Scanner scanner;
    private User currentUser;
    private UserManager userManager;
    
    /**
     * Creates a new LoginManager.
     */
    public LoginManager() {
        scanner = new Scanner(System.in);
        currentUser = null;
        userManager = UserManager.getInstance();
    }
    
    /**
     * Starts the login/registration process.
     * 
     * @return The authenticated user if login/registration is successful, null otherwise
     */
    public User start() {
        boolean running = true;
        
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    currentUser = loginUser();
                    if (currentUser != null) {
                        return currentUser;
                    }
                    break;
                case 2:
                    currentUser = registerUser();
                    if (currentUser != null) {
                        return currentUser;
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        return null;
    }
    
    /**
     * Displays the login/registration menu.
     */
    private void displayMenu() {
        System.out.println("\n===== BANKING SYSTEM =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.println("=========================");
    }
    
    /**
     * Handles the user login process.
     * 
     * @return The authenticated user if login is successful, null otherwise
     */
    private User loginUser() {
        System.out.println("\n----- LOGIN -----");
        String usernameText = getStringInput("Enter your username: ");
        String passwordText = getStringInput("Enter your password: ");
        
        User loggedInUser = userManager.login(usernameText, passwordText);
        if (loggedInUser != null) {
            System.out.println("Login successful!");
            return loggedInUser;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
    
    /**
     * Handles the user registration process.
     * 
     * @return The newly created user if registration is successful, null otherwise
     */
    private User registerUser() {
        System.out.println("\n----- REGISTER -----");
        System.out.println(Username.instance().getRequirements());
        
        String usernameText = "";
        boolean validUsername = false;
        
        while (!validUsername) {
            usernameText = getStringInput("Choose a username: ");
            
            // First check if username already exists
            if (userManager.usernameExists(usernameText)) {
                System.out.println("Username already exists. Please choose another one.");
                continue; // Skip the rest of the loop and prompt again
            }
            
            // Then check if the username is valid
            if (Username.instance().isValid(usernameText)) {
                validUsername = true;
            } else {
                System.out.println("Invalid username. " + Username.instance().getRequirements());
            }
        }
        
        System.out.println(Password.instance().getRequirements());
        
        String passwordText = "";
        boolean validPassword = false;
        
        while (!validPassword) {
            passwordText = getStringInput("Choose a password: ");
            if (Password.instance().isValid(passwordText)) {
                validPassword = true;
            } else {
                System.out.println("Invalid password.");
                System.out.println(Password.instance().getValidationErrors(passwordText));
            }
        }
        
        String confirmPassword = getStringInput("Confirm your password: ");
        if (!passwordText.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Registration failed.");
            return null;
        }
        
        User newUser = userManager.createAccount(usernameText, passwordText);
        if (newUser != null) {
            System.out.println("Registration successful!");
            return newUser;
        } else {
            System.out.println("Error registering user. Please try again.");
            return null;
        }
    }
    
    /**
     * Gets string input from the user.
     * 
     * @param prompt The prompt to display
     * @return The user's input
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /**
     * Gets integer input from the user.
     * 
     * @param prompt The prompt to display
     * @return The user's input as an integer
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}