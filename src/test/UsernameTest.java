package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import login.Username;

class UsernameTest {
    
    @Test
    void testValidUsername() {
        // Valid usernames
        assertTrue(Username.instance().isValid("Mike"), "Valid username should be accepted");
        assertTrue(Username.instance().isValid("NegraArroyoLane308"), "Valid username should be accepted");
        assertTrue(Username.instance().isValid("walter_white"), "Valid username should be accepted");
        assertTrue(Username.instance().isValid("a123456789"), "Valid username should be accepted");
    }
    
    @Test
    void testInvalidUsername_TooShort() {
        // Username too short
        assertFalse(Username.instance().isValid("gus"), "Username shorter than min length should be rejected");
    }
    
    @Test
    void testInvalidUsername_TooLong() {
        // Username too long
        assertFalse(Username.instance().isValid("abcdefghijklmnopqrstuvwxyz"), 
                   "Username longer than max length should be rejected");
    }
    
    @Test
    void testInvalidUsername_StartingWithNonLetter() {
        // Username starting with a number
        assertFalse(Username.instance().isValid("308NegraArroyoLane"), "Username starting with number should be rejected");
        // Username starting with underscore
        assertFalse(Username.instance().isValid("_SkylarWhiteYo"), "Username starting with underscore should be rejected");
    }
    
    @Test
    void testInvalidUsername_InvalidCharacters() {
        // Username with special characters
        assertFalse(Username.instance().isValid("gus@fring"), "Username with special characters should be rejected");
        assertFalse(Username.instance().isValid("gus fring"), "Username with spaces should be rejected");
    }
    
    @Test
    void testInvalidUsername_Null() {
        // Null username
        assertFalse(Username.instance().isValid(null), "Null username should be rejected");
    }
    
    @Test
    void testGetRequirements() {
        // Check if requirements string is not empty
        String requirements = Username.instance().getRequirements();
        assertNotNull(requirements, "Requirements string should not be null");
        assertFalse(requirements.isEmpty(), "Requirements string should not be empty");
        
        // Verify requirements mention key constraints
        assertTrue(requirements.contains("letter"), "Requirements should mention starting with a letter");
        assertTrue(requirements.contains("between"), "Requirements should mention length constraints");
    }
    
    @Test
    void testGetValidationErrors_Valid() {
        // Valid username should have no errors
        String errors = Username.instance().getValidationErrors("validuser");
        assertTrue(errors.isEmpty(), "Valid username should have no errors");
    }
    
    @Test
    void testGetValidationErrors_Invalid() {
        // Invalid username should have errors
        String errors = Username.instance().getValidationErrors("123");
        assertFalse(errors.isEmpty(), "Invalid username should have errors");
        
        // Check for specific error message
        assertTrue(errors.contains("start with a letter"), "Error should mention starting with a letter");
    }
    
    @Test
    void testGetValidationErrors_MultipleIssues() {
        // Username with multiple issues (too short and starts with a number)
        String errors = Username.instance().getValidationErrors("1a");
        
        // Check that all issues are reported
        assertTrue(errors.contains("start with a letter"), "Should report not starting with a letter");
        assertTrue(errors.contains("between"), "Should report length requirement");
    }
    
    @Test
    void testSingleton() {
        // Verify that the same instance is returned each time
        Username instance1 = Username.instance();
        Username instance2 = Username.instance();
        assertSame(instance1, instance2, "Username.instance() should always return the same instance");
    }
}