package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import login.LoginManager;
import login.User;
import login.UserManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class LoginManagerTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    private static final String TEST_FILE_PATH = "data/testusers.txt";
    private static final String PROD_FILE_PATH = "data/users.txt";
    
    @BeforeEach
    void setUp() throws Exception {
        // Redirect stdout for testing console output
        System.setOut(new PrintStream(outContent));
        
        // Create backup of production file if it exists
        File prodFile = new File(PROD_FILE_PATH);
        if (prodFile.exists()) {
            try {
                Files.copy(prodFile.toPath(), Paths.get(PROD_FILE_PATH + ".bak"));
            } catch (IOException e) {
                System.err.println("Failed to backup production file: " + e.getMessage());
            }
        }
        
        // Delete both files to start clean
        if (prodFile.exists()) {
            prodFile.delete();
        }
        
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        
        // Create symbolic link or copy the test file to production path
        try {
            // Create the test file first
            testFile.getParentFile().mkdirs();
            testFile.createNewFile();
            
            // Create a hard link or copy
            Files.copy(testFile.toPath(), prodFile.toPath());
        } catch (IOException e) {
            System.err.println("Failed to link test file: " + e.getMessage());
            fail("Test setup failed");
        }
        
        // Initialize the user file
        User.initializeUserFile();
        
        // Create a test user
        User testUser = new User("existinguser", "Password1!");
        testUser.saveUser();
        
        // Force UserManager to reload users from the new file
        UserManager.getInstance().loadAllUsers();
    }
    
    @AfterEach
    void tearDown() throws Exception {
        // Restore stdout and stdin
        System.setOut(originalOut);
        System.setIn(originalIn);
        
        // Clean up test file
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        
        // Clean up production file used in test
        File prodFile = new File(PROD_FILE_PATH);
        if (prodFile.exists()) {
            prodFile.delete();
        }
        
        // Restore production file if backup exists
        File backupFile = new File(PROD_FILE_PATH + ".bak");
        if (backupFile.exists()) {
            try {
                Files.copy(backupFile.toPath(), Paths.get(PROD_FILE_PATH));
                backupFile.delete();
            } catch (IOException e) {
                System.err.println("Failed to restore production file: " + e.getMessage());
            }
        }
    }
    
    // Helper method to simulate user input
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    
    @Test
    void testSuccessfulLogin() {
        // Simulate user selecting login option, entering credentials
        String input = "1\nexistinguser\nPassword1!\n";
        provideInput(input);
        
        // Create login manager and start login process
        LoginManager manager = new LoginManager();
        User authenticatedUser = manager.start();
        
        // Check if login was successful
        assertNotNull(authenticatedUser, "Login should be successful");
        assertEquals("existinguser", authenticatedUser.getUsername(), "Username should match");
        
        manager.close();
    }
    
    @Test
    void testFailedLogin() {
        // Simulate user selecting login option, entering wrong credentials, and exiting
        String input = "1\nexistinguser\nwrongpassword\n0\n";
        provideInput(input);
        
        // Create login manager and start login process
        LoginManager manager = new LoginManager();
        User authenticatedUser = manager.start();
        
        // Check if login failed
        assertNull(authenticatedUser, "Login should fail with incorrect password");
        assertTrue(outContent.toString().contains("Invalid username or password"), 
                  "Output should contain login error message");
        
        manager.close();
    }
    
    @Test
    void testSuccessfulRegistration() {
        // Simulate user selecting register option and entering valid credentials
        String input = "2\nnewuser\nPassword1!\nPassword1!\n";
        provideInput(input);
        
        // Create login manager and start registration process
        LoginManager manager = new LoginManager();
        User registeredUser = manager.start();
        
        // Check if registration was successful
        assertNotNull(registeredUser, "Registration should be successful");
        assertEquals("newuser", registeredUser.getUsername(), "Username should match");
        assertTrue(outContent.toString().contains("Registration successful"), 
                  "Output should confirm successful registration");
        
        manager.close();
    }
    
    @Test
    void testRegistrationWithMismatchedPasswords() {
        // Simulate user entering mismatched passwords during registration and then exiting
        String input = "2\nuniquename\nPassword1!\nDifferentPassword1!\n0\n";
        provideInput(input);
        
        // Create login manager and start registration process
        LoginManager manager = new LoginManager();
        User registeredUser = manager.start();
        
        // Check if registration failed
        assertNull(registeredUser, "Registration should fail with mismatched passwords");
        assertTrue(outContent.toString().contains("Passwords do not match"), 
                  "Output should indicate passwords don't match");
        
        manager.close();
    }
    
    @Test
    void testRegistrationWithInvalidUsername() {
        // Provide enough input lines for the entire registration flow plus exit
        // After invalid username, it will likely prompt for username again, so provide valid one
        // Then provide a password, confirmation, and eventually exit
        String input = "2\n123\nvaliduser\nPassword1!\nPassword1!\n0\n";
        provideInput(input);
        
        // Create login manager and start registration process
        LoginManager manager = new LoginManager();
        User registeredUser = manager.start();
        
        // We're only checking if the error message appears, not the final result
        String output = outContent.toString();
        assertTrue(output.contains("Invalid username") || 
                  output.contains("must start with a letter") || 
                  output.contains("Username requirements"), 
                  "Output should indicate username validation issue");
        
        manager.close();
    }
    
    @Test
    void testRegistrationWithInvalidPassword() {
        // Provide enough input for the entire registration flow
        // After invalid password, it will likely prompt for password again
        String input = "2\nvaliduser\nweakpw\nPassword1!\nPassword1!\n0\n";
        provideInput(input);
        
        // Create login manager and start registration process
        LoginManager manager = new LoginManager();
        User registeredUser = manager.start();
        
        // We're checking for error message, not final result
        String output = outContent.toString();
        assertTrue(output.contains("Invalid password") || 
                  output.contains("Password must") ||
                  output.contains("Password requirements"), 
                  "Output should indicate password validation issue");
        
        manager.close();
    }
    
    @Test
    void testDuplicateUsernameRegistration() {
        // Simulate user trying to register with existing username
        // Then providing a new username, valid password, confirmation, and exit
        // Format: option 2 (register), existinguser, newValidUser, validPassword, same password to confirm
        String input = "2\nexistinguser\nnewuser123\nPassword1!\nPassword1!\n";
        provideInput(input);
        
        // Create login manager and start registration process
        LoginManager manager = new LoginManager();
        User registeredUser = manager.start();
        
        // The registration should succeed with the second username
        assertNotNull(registeredUser, "Registration should complete with the alternative username");
        assertEquals("newuser123", registeredUser.getUsername(), "Username should match the second provided username");
        
        // Verify that the error message about duplicate username was shown
        String output = outContent.toString();
        boolean hasErrorMessage = 
            output.contains("already exists") || 
            output.contains("Username already taken") ||
            output.contains("Username exists") ||
            output.contains("choose another") ||
            output.contains("username is not available") ||
            output.contains("username is taken");
        
        assertTrue(hasErrorMessage, "Output should indicate the first username was already in use");
        
        manager.close();
    }
    
    @Test
    void testInvalidMenuOption() {
        // Simulate user entering invalid menu option then exiting
        String input = "5\n0\n";
        provideInput(input);
        
        // Create login manager and start process
        LoginManager manager = new LoginManager();
        User result = manager.start();
        
        // Check if process exited correctly
        assertNull(result, "Process should exit with null result");
        assertTrue(outContent.toString().contains("Invalid option"), 
                  "Output should indicate invalid option");
        
        manager.close();
    }
    
    @Test
    void testExitOption() {
        // Simulate user directly selecting exit option
        String input = "0\n";
        provideInput(input);
        
        // Create login manager and start process
        LoginManager manager = new LoginManager();
        User result = manager.start();
        
        // Check if process exited correctly
        assertNull(result, "Process should exit with null result");
        
        manager.close();
    }
    
    @Test
    void testInvalidInputHandling() {
        // Simulate non-numeric input for menu selection, then exit
        String input = "abc\n0\n";
        provideInput(input);
        
        // Create login manager and start process
        LoginManager manager = new LoginManager();
        User result = manager.start();
        
        // Check if invalid input was handled
        assertNull(result, "Process should exit with null result");
        assertTrue(outContent.toString().contains("valid number"), 
                  "Output should prompt for valid number");
        
        manager.close();
    }
}