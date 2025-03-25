package login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for handling password validation and security operations.
 * Includes methods to check if passwords meet security requirements.
 */
public class Password {
    
    // Password validation constants
    private static final int MIN_LENGTH = 8;
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;':,./<>?";
    
    // Non-static instance to be used by other classes
    private static final Password INSTANCE = new Password();
    
    /**
     * Private constructor to enforce singleton pattern.
     * This class is meant to be used through its instance() method.
     */
    private Password() {
        // Private constructor
    }
    
    /**
     * Get the singleton instance of this class.
     * 
     * @return The Password validator instance
     */
    public static Password instance() {
        return INSTANCE;
    }
    
    /**
     * Validates if a password meets all security requirements:
     * - Minimum length (8 characters)
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     * - Contains at least one digit
     * - Contains at least one special character
     * 
     * @param passwordText The password to validate
     * @return true if password meets all requirements, false otherwise
     */
    public boolean isValid(String passwordText) {
        return hasMinimumLength(passwordText) &&
               hasUpperCase(passwordText) &&
               hasLowerCase(passwordText) &&
               hasDigit(passwordText) &&
               hasSpecialChar(passwordText);
    }
    
    /**
     * Checks if password meets minimum length requirement.
     * 
     * @param passwordText The password to check
     * @return true if password is long enough, false otherwise
     */
    private boolean hasMinimumLength(String passwordText) {
        return passwordText != null && passwordText.length() >= MIN_LENGTH;
    }
    
    /**
     * Checks if password contains at least one uppercase letter.
     * 
     * @param passwordText The password to check
     * @return true if password contains uppercase, false otherwise
     */
    private boolean hasUpperCase(String passwordText) {
        if (passwordText == null) return false;
        
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(passwordText);
        
        return matcher.find();
    }
    
    /**
     * Checks if password contains at least one lowercase letter.
     * 
     * @param passwordText The password to check
     * @return true if password contains lowercase, false otherwise
     */
    private boolean hasLowerCase(String passwordText) {
        if (passwordText == null) return false;
        
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(passwordText);
        
        return matcher.find();
    }
    
    /**
     * Checks if password contains at least one digit.
     * 
     * @param passwordText The password to check
     * @return true if password contains digit, false otherwise
     */
    private boolean hasDigit(String passwordText) {
        if (passwordText == null) return false;
        
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(passwordText);
        
        return matcher.find();
    }
    
    /**
     * Checks if password contains at least one special character.
     * 
     * @param passwordText The password to check
     * @return true if password contains special character, false otherwise
     */
    private boolean hasSpecialChar(String passwordText) {
        if (passwordText == null) return false;
        
        Pattern pattern = Pattern.compile("[" + Pattern.quote(SPECIAL_CHARS) + "]");
        Matcher matcher = pattern.matcher(passwordText);
        
        return matcher.find();
    }
    
    /**
     * Returns a string describing password requirements.
     * 
     * @return Password requirement description
     */
    public String getRequirements() {
        return "Password must contain at least " + MIN_LENGTH + " characters, including:\n" +
               "- At least one uppercase letter (A-Z)\n" +
               "- At least one lowercase letter (a-z)\n" +
               "- At least one digit (0-9)\n" +
               "- At least one special character (" + SPECIAL_CHARS + ")";
    }
    
    /**
     * Gets detailed validation errors for a password.
     * 
     * @param passwordText The password to check
     * @return String containing all validation errors, or empty string if valid
     */
    public String getValidationErrors(String passwordText) {
        StringBuilder errors = new StringBuilder();
        
        if (!hasMinimumLength(passwordText)) {
            errors.append("- Password must be at least ").append(MIN_LENGTH).append(" characters long\n");
        }
        
        if (!hasUpperCase(passwordText)) {
            errors.append("- Password must contain at least one uppercase letter\n");
        }
        
        if (!hasLowerCase(passwordText)) {
            errors.append("- Password must contain at least one lowercase letter\n");
        }
        
        if (!hasDigit(passwordText)) {
            errors.append("- Password must contain at least one digit\n");
        }
        
        if (!hasSpecialChar(passwordText)) {
            errors.append("- Password must contain at least one special character\n");
        }
        
        return errors.toString();
    }
}