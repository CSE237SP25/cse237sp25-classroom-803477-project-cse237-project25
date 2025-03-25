package login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Class that represents a user in the system.
 */
public class User {
    private String username;
    private String passwordHash;
    // Store the file in a data directory to make it more organized
    private static final String USER_FILE = "data/users.txt";
    private boolean isExistingUser;
    
    // Add a static initializer to ensure the file and directory exist when the class is loaded
    static {
        initializeUserFile();
    }
    
    /**
     * Initializes the user file and ensures the directory exists.
     */
    public static void initializeUserFile() {
        File file = new File(USER_FILE);
        File directory = file.getParentFile();
        
        // Create the directory if it doesn't exist
        if (directory != null && !directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Created directory: " + directory.getAbsolutePath());
            } else {
                System.err.println("Failed to create directory: " + directory.getAbsolutePath());
            }
        }
        
        // Create the file if it doesn't exist
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("Created user file: " + file.getAbsolutePath());
                } else {
                    System.err.println("Failed to create user file: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.err.println("Error creating user file: " + e.getMessage());
            }
        }
    }
    
    /**
     * Creates a new user with the specified username and password.
     * The password is stored as a hash, not in plain text.
     * 
     * @param usernameText The user's username
     * @param passwordText The user's password
     */
    public User(String usernameText, String passwordText) {
        this.username = usernameText;
        this.passwordHash = hashPassword(passwordText);
        this.isExistingUser = false;
    }
    
    /**
     * Constructor for loading an existing user from stored data.
     * Uses a flag parameter to distinguish from the other constructor.
     * 
     * @param usernameText The user's username
     * @param passwordHashText The user's already hashed password
     * @param isExisting Flag to indicate this is an existing user
     */
    private User(String usernameText, String passwordHashText, boolean isExisting) {
        this.username = usernameText;
        this.passwordHash = passwordHashText;
        this.isExistingUser = isExisting;
    }
    
    /**
     * Factory method to create a user instance from stored data.
     * 
     * @param usernameText The username from stored data
     * @param passwordHashText The password hash from stored data
     * @return A User instance representing an existing user
     */
    public static User loadExistingUser(String usernameText, String passwordHashText) {
        return new User(usernameText, passwordHashText, true);
    }
    
    /**
     * Gets the path to the user file.
     * 
     * @return The file path
     */
    public static String getUserFilePath() {
        return USER_FILE;
    }
    
    /**
     * Gets the username.
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Creates a hash of the provided password.
     * 
     * @param passwordText The password to hash
     * @return The hashed password
     */
    private String hashPassword(String passwordText) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    passwordText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            // Fallback to simple hashing if SHA-256 isn't available
            return String.valueOf(passwordText.hashCode());
        }
    }
    
    /**
     * Validates if the provided password matches the stored password hash.
     * 
     * @param passwordText The password to validate
     * @return true if password matches, false otherwise
     */
    public boolean validatePassword(String passwordText) {
        String hashedInput = hashPassword(passwordText);
        return passwordHash.equals(hashedInput);
    }
    
    /**
     * Saves the user to the data file.
     * 
     * @return true if save was successful, false otherwise
     */
    public boolean saveUser() {
        try {
            // Ensure file exists
            initializeUserFile();
            
            File file = new File(USER_FILE);
            
            // Check if user already exists
            UserManager userManager = UserManager.getInstance();
            if (userManager.usernameExists(this.username)) {
                System.out.println("User already exists: " + this.username);
                return false;
            }
            
            // Add new user
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(username + "," + passwordHash);
                writer.newLine();
                System.out.println("User saved successfully: " + this.username);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }
}