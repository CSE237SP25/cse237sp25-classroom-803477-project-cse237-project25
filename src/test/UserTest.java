package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import login.User;
import login.UserManager;

class UserTest {
    
    private static final String TEST_USERNAME = "SkylerWhite";
    private static final String TEST_PASSWORD = "Myhusbandiswalterwhiteyo1!";
    private static final String TEST_FILE_PATH = "data/testusers.txt";
    private static final String PROD_FILE_PATH = "data/users.txt";
    
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
        // This way the application will read/write to test file while thinking it's using the production file
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
    void testUserConstruction() {
        // Create a new user with the TEST_USERNAME and password
        User testUser = new User(TEST_USERNAME, TEST_PASSWORD);
        
        // Check username was set correctly
        assertEquals(TEST_USERNAME, testUser.getUsername(), "Username should be set correctly");
        
        // User should save successfully
        assertTrue(testUser.saveUser(), "New user should save successfully");
    }
    
    @Test
    void testDuplicateUserSave() {
        // Create and save a user with TEST_USERNAME
        User firstUser = new User(TEST_USERNAME, TEST_PASSWORD);
        firstUser.saveUser();
        
        // Create a duplicate user with the same username
        User duplicateUser = new User(TEST_USERNAME, TEST_PASSWORD);
        
        // Attempt to save the duplicate should fail
        assertFalse(duplicateUser.saveUser(), "Saving duplicate username should fail");
    }
    
    @Test
    void testPasswordValidation() {
        // Create a new user
        User testUser = new User("testUser3", TEST_PASSWORD);
        
        // Test password validation
        assertTrue(testUser.validatePassword(TEST_PASSWORD), "Correct password should validate");
        assertFalse(testUser.validatePassword("wrongpassword"), "Wrong password should not validate");
    }
    
    @Test
    void testGetUserFilePath() {
        // Test that the user file path is not null or empty
        String filePath = User.getUserFilePath();
        assertNotNull(filePath, "User file path should not be null");
        assertFalse(filePath.isEmpty(), "User file path should not be empty");
        assertTrue(filePath.contains("data/users.txt"), "File path should point to data/users.txt");
    }
    
    @Test
    void testUserFileInitialization() {
        // Call the initialization method
        User.initializeUserFile();
        
        // Check if the directory exists
        File directory = new File("data");
        assertTrue(directory.exists(), "Data directory should exist");
        assertTrue(directory.isDirectory(), "Data path should be a directory");
        
        // Check if the file exists
        File userFile = new File(User.getUserFilePath());
        assertTrue(userFile.exists(), "User file should exist");
    }
    
    @Test
    void testLoadExistingUser() {
        // First create and save a user with TEST_USERNAME
        User originalUser = new User(TEST_USERNAME, TEST_PASSWORD);
        originalUser.saveUser();
        
        // Now try to login with that user
        UserManager manager = UserManager.getInstance();
        User loggedInUser = manager.login(TEST_USERNAME, TEST_PASSWORD);
        
        assertNotNull(loggedInUser, "User should be able to log in");
        assertEquals(TEST_USERNAME, loggedInUser.getUsername(), "Username should match");
        
        // Test that user can validate the original password
        assertTrue(loggedInUser.validatePassword(TEST_PASSWORD), "Loaded user should validate original password");
    }
    
    @Test
    void testHashPassword() {
        // Test that the same password gives consistent validation results
        User user1 = new User("testuser5", TEST_PASSWORD);
        User user2 = new User("testuser6", TEST_PASSWORD);
        
        // Both users should validate the same password
        assertTrue(user1.validatePassword(TEST_PASSWORD), "User 1 should validate password");
        assertTrue(user2.validatePassword(TEST_PASSWORD), "User 2 should validate password");
        
        // Different passwords should not validate
        assertFalse(user1.validatePassword("DifferentPassword1!"), "Different password should not validate");
    }
    
    @Test
    void testSaveAndRetrieveUser() {
        // Create and save a user with TEST_USERNAME
        User originalUser = new User(TEST_USERNAME, TEST_PASSWORD);
        boolean saveResult = originalUser.saveUser();
        assertTrue(saveResult, "User should save successfully");
        
        // Retrieve all users and check existence
        UserManager manager = UserManager.getInstance();
        boolean exists = manager.usernameExists(TEST_USERNAME);
        assertTrue(exists, "Username should exist after saving");
    }
}