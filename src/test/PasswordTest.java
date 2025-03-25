package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import login.Password;

class PasswordTest {
    
    @Test
    void testValidPassword() {
        // Valid password with all requirements met
        assertTrue(Password.instance().isValid("SaulGoodman1!"), "Valid password should be accepted");
        assertTrue(Password.instance().isValid("Abcd1234!@#$"), "Valid password should be accepted");
    }
    
    @Test
    void testInvalidPassword_TooShort() {
        // Password too short
        assertFalse(Password.instance().isValid("Ab1!"), "Password shorter than min length should be rejected");
    }
    
    @Test
    void testInvalidPassword_NoUpperCase() {
        // Password without uppercase letter
        assertFalse(Password.instance().isValid("saulgoodman1!"), "Password without uppercase should be rejected");
    }
    
    @Test
    void testInvalidPassword_NoLowerCase() {
        // Password without lowercase letter
        assertFalse(Password.instance().isValid("SAULGOODMAN1!"), "Password without lowercase should be rejected");
    }
    
    @Test
    void testInvalidPassword_NoDigit() {
        // Password without digit
        assertFalse(Password.instance().isValid("SaulGoodman!"), "Password without digit should be rejected");
    }
    
    @Test
    void testInvalidPassword_NoSpecialChar() {
        // Password without special character
        assertFalse(Password.instance().isValid("SaulGoodman1"), "Password without special character should be rejected");
    }
    
    @Test
    void testInvalidPassword_Null() {
        // Null password
        assertFalse(Password.instance().isValid(null), "Null password should be rejected");
    }
    
    @Test
    void testGetRequirements() {
        // Check if requirements string is not empty
        String requirements = Password.instance().getRequirements();
        assertNotNull(requirements, "Requirements string should not be null");
        assertFalse(requirements.isEmpty(), "Requirements string should not be empty");
        
        // Verify requirements contain key terms
        assertTrue(requirements.contains("uppercase"), "Requirements should mention uppercase");
        assertTrue(requirements.contains("lowercase"), "Requirements should mention lowercase");
        assertTrue(requirements.contains("digit"), "Requirements should mention digits");
        assertTrue(requirements.contains("special"), "Requirements should mention special characters");
    }
    
    @Test
    void testGetValidationErrors_Valid() {
        // Valid password should have no errors
        String errors = Password.instance().getValidationErrors("SaulGoodman1!");
        assertTrue(errors.isEmpty(), "Valid password should have no errors");
    }
    
    @Test
    void testGetValidationErrors_Invalid() {
        // Invalid password should have specific errors
        String shortPassword = "Pw1!";
        String errors = Password.instance().getValidationErrors(shortPassword);
        assertFalse(errors.isEmpty(), "Invalid password should have errors");
        
        // Check for specific error message
        assertTrue(errors.contains("at least"), "Error should mention minimum length");
    }
    
    @Test
    void testGetValidationErrors_MultipleIssues() {
        // Password with multiple issues
        String weakPassword = "password";  // No uppercase, no digit, no special char
        String errors = Password.instance().getValidationErrors(weakPassword);
        
        // Check that all issues are reported
        assertTrue(errors.contains("uppercase"), "Should report missing uppercase");
        assertTrue(errors.contains("digit"), "Should report missing digit");
        assertTrue(errors.contains("special"), "Should report missing special character");
    }
    
    @Test
    void testSingleton() {
        // Verify that the same instance is returned each time
        Password instance1 = Password.instance();
        Password instance2 = Password.instance();
        assertSame(instance1, instance2, "Password.instance() should always return the same instance");
    }
}