package login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class to handle operations involving collections of users
 * and cross-user operations.
 */
public class UserManager {
    private static final UserManager INSTANCE = new UserManager();
    
    /**
     * Private constructor for singleton
     */
    private UserManager() {
        // Private constructor
    }
    
    /**
     * Gets the singleton instance of UserManager.
     * 
     * @return The UserManager instance
     */
    public static UserManager getInstance() {
        return INSTANCE;
    }
    
    /**
     * Checks if a username already exists in the system.
     * 
     * @param usernameText The username to check
     * @return true if username exists, false otherwise
     */
    public boolean usernameExists(String usernameText) {
        List<User> users = loadAllUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(usernameText)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Loads all users from the data file.
     * 
     * @return List of all users
     */
    public List<User> loadAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(User.getUserFilePath());
        
        // Ensure file exists
        User.initializeUserFile();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String usernameText = parts[0];
                    String passwordHashText = parts[1];
                    users.add(User.loadExistingUser(usernameText, passwordHashText));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        
        return users;
    }
    
    /**
     * Authenticates a user login attempt.
     * 
     * @param usernameText The username to authenticate
     * @param passwordText The password to authenticate
     * @return The authenticated User object if successful, null otherwise
     */
    public User login(String usernameText, String passwordText) {
        List<User> users = loadAllUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(usernameText) && user.validatePassword(passwordText)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Creates a new user account if it passes all validation.
     * 
     * @param usernameText The username for the new account
     * @param passwordText The password for the new account
     * @return The new User object if creation was successful, null otherwise
     */
    public User createAccount(String usernameText, String passwordText) {
        // Validate username and password
        if (!Username.instance().isValid(usernameText)) {
            return null;
        }
        
        if (!Password.instance().isValid(passwordText)) {
            return null;
        }
        
        // Check if username already exists - keeping this check here for extra safety
        if (usernameExists(usernameText)) {
            System.out.println("Username already exists: " + usernameText);
            return null;
        }
        
        // Create and save new user
        User newUser = new User(usernameText, passwordText);
        if (newUser.saveUser()) {
            return newUser;
        }
        
        return null;
    }
}