package tests;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.UpdateAccount;

import java.util.*;

public class UpdateAccountTest {

    private Map<Integer, List<Object>> accounts;
    private UpdateAccount updateAccount;

    @BeforeEach
    public void setUp() {
        accounts = new HashMap<>();
        // Initialize dummy account with: name, phone number, email
        accounts.put(123456789, new ArrayList<>(Arrays.asList("John Doe", "123-456-7890", "john@example.com")));
        updateAccount = new UpdateAccount(accounts);
    }

    @Test
    public void testUpdateName_Valid() {
        updateAccount.updateName(123456789, "Jane Smith");
        assertEquals("Jane Smith", accounts.get(123456789).get(0));
    }

    @Test
    public void testUpdatePhoneNumber_Valid() {
        updateAccount.updatePhoneNumber(123456789, "(314) 555-7890");
        assertEquals("(314) 555-7890", accounts.get(123456789).get(1));
    }

    @Test
    public void testUpdatePhoneNumber_Invalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateAccount.updatePhoneNumber(123456789, "invalid-number");
        });
        assertEquals("Invalid phone number format.", exception.getMessage());
    }

    @Test
    public void testUpdateEmail_Valid() {
        updateAccount.updateEmail(123456789, "jane.smith@example.org");
        assertEquals("jane.smith@example.org", accounts.get(123456789).get(2));
    }

    @Test
    public void testUpdateEmail_Invalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateAccount.updateEmail(123456789, "bad-email");
        });
        assertEquals("Invalid email format.", exception.getMessage());
    }

    @Test
    public void testUpdateInvalidAccountNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            updateAccount.updateName(999999999, "Someone");
        });
        assertEquals("Account number does not exist, please retry with a valid account number", exception.getMessage());
    }
}
