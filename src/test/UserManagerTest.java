package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import login.User;
import login.UserManager;

class UserManagerTest {
    
    private static final String TEST_USERNAME = "WalterWhite";
    private static final String TEST_PASSWORD = "Iamthedanger1!";
    private static final String TEST_FILE_PATH = "data/testusers.txt";
    private static final String PROD_FILE_PATH = "data/users.txt";
    private UserManager userManager;
    
    @BeforeEach
    void setUp() throws Exception {
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
        
        // Get the singleton instance
        userManager = UserManager.getInstance();
        
        // Initialize the user file
        User.initializeUserFile();
    }
    
    @AfterEach
    void tearDown() throws Exception {
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
    
    @Test
    void testSingleton() {
        // Verify that the same instance is returned each time
        UserManager instance1 = UserManager.getInstance();
        UserManager instance2 = UserManager.getInstance();
        assertSame(instance1, instance2, "getInstance() should always return the same instance");
    }
    
    @Test
    void testCreateAccount() {
        // Create a new account
        User newUser = userManager.createAccount(TEST_USERNAME, TEST_PASSWORD);
        
        // Verify account was created
        assertNotNull(newUser, "Account should be created successfully");
        assertEquals(TEST_USERNAME, newUser.getUsername(), "Username should match");
        
        // Verify user exists in system
        assertTrue(userManager.usernameExists(TEST_USERNAME), "Username should exist after account creation");
    }
    
    @Test
    void testCreateDuplicateAccount() {
        // Create first account
        userManager.createAccount(TEST_USERNAME, TEST_PASSWORD);
        
        // Try to create duplicate account
        User duplicateUser = userManager.createAccount(TEST_USERNAME, "DifferentPassword1!");
        assertNull(duplicateUser, "Duplicate account should not be created");
    }
    
    @Test
    void testCreateAccountWithInvalidCredentials() {
        // Try to create account with invalid username (too short)
        User invalidUsernameUser = userManager.createAccount("abc", TEST_PASSWORD);
        assertNull(invalidUsernameUser, "Account with invalid username should not be created");
        
        // Try to create account with invalid password (no special char)
        User invalidPasswordUser = userManager.createAccount("validuser", "Password123");
        assertNull(invalidPasswordUser, "Account with invalid password should not be created");
    }
    
    @Test
    void testLogin() {
        // Create a test account
        userManager.createAccount(TEST_USERNAME, TEST_PASSWORD);
        
        // Test successful login
        User loggedInUser = userManager.login(TEST_USERNAME, TEST_PASSWORD);
        assertNotNull(loggedInUser, "Login should succeed with correct credentials");
        assertEquals(TEST_USERNAME, loggedInUser.getUsername(), "Logged in username should match");
        
        // Test failed login - wrong password
        User failedWrongPassword = userManager.login(TEST_USERNAME, "WrongPassword1!");
        assertNull(failedWrongPassword, "Login should fail with incorrect password");
        
        // Test failed login - non-existent user
        User failedNonExistent = userManager.login("NonExistentUser", TEST_PASSWORD);
        assertNull(failedNonExistent, "Login should fail with non-existent username");
    }
    
    @Test
    void testLoadAllUsers() {
        // Create test accounts
        userManager.createAccount("user1", "Password1!");
        userManager.createAccount("user2", "Password2!");
        
        // Load all users
        List<User> users = userManager.loadAllUsers();
        
        // Check if both users were loaded
        assertEquals(2, users.size(), "Should have loaded two users");
        
        // Verify usernames
        boolean foundUser1 = false;
        boolean foundUser2 = false;
        
        for (User user : users) {
            if (user.getUsername().equals("user1")) {
                foundUser1 = true;
            } else if (user.getUsername().equals("user2")) {
                foundUser2 = true;
            }
        }
        
        assertTrue(foundUser1, "User1 should be found in loaded users");
        assertTrue(foundUser2, "User2 should be found in loaded users");
    }
    
    @Test
    void testUsernameExists() {
        // Create a test account
        userManager.createAccount(TEST_USERNAME, TEST_PASSWORD);
        
        // Test username exists
        assertTrue(userManager.usernameExists(TEST_USERNAME), "Username should exist");
        
        // Test case insensitivity (if your implementation is case-insensitive)
        assertTrue(userManager.usernameExists(TEST_USERNAME.toLowerCase()), 
                "Username check should be case-insensitive");
        
        // Test non-existent username
        assertFalse(userManager.usernameExists("nonexistentuser"), 
                "Non-existent username should not exist");
    }
    
    @Test
    void testEmptyUserList() {
        // Test with empty user file
        List<User> users = userManager.loadAllUsers();
        
        // List should be empty but not null
        assertNotNull(users, "User list should not be null");
        assertTrue(users.isEmpty(), "User list should be empty");
    }
}