package login;

/**
 * Demo class showing how to use the login system.
 */
public class BankLoginDemo {
    public static void main(String[] args) {
        System.out.println("Welcome to the Banking System");
        System.out.println("Please login or create an account");
        
        // Create a login manager to handle user authentication
        LoginManager manager = new LoginManager();
        
        // Start the login/registration process
        User authenticatedUser = manager.start();
        
        // Check if login/registration was successful
        if (authenticatedUser != null) {
            System.out.println("\nWelcome, " + authenticatedUser.getUsername() + "!");
            System.out.println("You are now logged in to your account.");
            // Here you would connect to the main banking interface
            System.out.println("Banking functionality would be available here.");
        } else {
            System.out.println("Login/Registration canceled or failed.");
        }
        
        // Clean up resources
        manager.close();
        
        System.out.println("\nThank you for using our banking system!");
    }
}