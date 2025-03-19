package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.CreateAccount;

import java.util.List;
import java.util.Map;

public class CreateAccountTest {

    private CreateAccount account;

    @BeforeEach
    public void setUp() {
        // 1. Create object to be tested
        account = new CreateAccount();
    }

    @Test
    public void testName(){
        account.setName("Alfred");
        assertEquals("Alfred", account.getName());
    }

    @Test
    public void testValidEmail() {
        account.setEmail("test@example.com");
        assertEquals("test@example.com", account.getEmail());
    }

    @Test
    public void testInvalidEmail() {
        try {
            account.setEmail("invalid-email");
            fail(); // Should not reach here
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testValidPhoneNumber() {
        account.setPhoneNumber("+1 123-456-7890");
        assertEquals("+1 123-456-7890", account.getPhoneNumber());
    }

    @Test
    public void testInvalidPhoneNumber() {
        try {
            account.setPhoneNumber("12345");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testStoreAccountInfoSuccess() {
        account.setName("John Doe");
        account.setEmail("john.doe@example.com");
        account.setPhoneNumber("+1 123-456-7890");

        // 2. Call the method being tested
        account.storeAccountInfo();

        // 3. Use assertions to verify results
        Map<Integer, List<Object>> accounts = account.getAccounts();
        assertEquals(1, accounts.size());

        List<Object> accountDetails = accounts.values().iterator().next();
        assertEquals("John Doe", accountDetails.get(0));
        assertEquals("+1 123-456-7890", accountDetails.get(1));
        assertEquals("john.doe@example.com", accountDetails.get(2));
    }

    @Test
    public void testStoreAccountInfoFailure() {
        try {
            account.storeAccountInfo();
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e != null);
        }
    }
}
