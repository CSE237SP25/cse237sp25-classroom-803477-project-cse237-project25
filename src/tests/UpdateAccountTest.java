package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.CreateAccount;
import bankapp.UpdateAccount;

import java.util.List;
import java.util.Map;

public class UpdateAccountTest {

    private CreateAccount createAccount;
    private UpdateAccount updateAccount;
    private int accountNumber;

    @BeforeEach
    public void setUp() {
        // 1. Create objects to be tested
        createAccount = new CreateAccount();
        updateAccount = new UpdateAccount();

        // 2. Create a test account
        createAccount.setName("John Doe");
        createAccount.setEmail("john.doe@example.com");
        createAccount.setPhoneNumber("+1 123-456-7890");
        createAccount.setAccountNumber();
        createAccount.storeAccountInfo();

        // 3. Retrieve account number
        Map<Integer, List<Object>> accounts = createAccount.getAccounts();
        accountNumber = createAccount.getAccountNumber();
    }

    @Test
    public void testValidAccountNumber() {
        // 2. Call method being tested
        boolean exists = updateAccount.validAccountNumber(accountNumber);

        // 3. Verify result
        assertTrue(exists);
    }

    @Test
    public void testInvalidAccountNumber() {
        assertTrue(!updateAccount.validAccountNumber(999999999));
    }

    @Test
    public void testUpdateName() {
        // 2. Call method being tested
        updateAccount.updateName(accountNumber, "Jane Doe");

        // 3. Verify result
        List<Object> accountDetails = updateAccount.accounts.get(accountNumber);
        assertEquals("Jane Doe", accountDetails.get(0));
    }

    @Test
    public void testUpdatePhoneNumber() {
        // 2. Call method being tested
        updateAccount.updatePhoneNumber(accountNumber, "+1 987-654-3210");

        // 3. Verify result
        List<Object> accountDetails = updateAccount.accounts.get(accountNumber);
        assertEquals("+1 987-654-3210", accountDetails.get(1));
    }

    @Test
    public void testUpdateEmail() {
        // 2. Call method being tested
        updateAccount.updateEmail(accountNumber, "jane.doe@example.com");

        // 3. Verify result
        List<Object> accountDetails = updateAccount.accounts.get(accountNumber);
        assertEquals("jane.doe@example.com", accountDetails.get(2));
    }

    @Test
    public void testUpdateNonExistentAccount() {
        try {
            updateAccount.updateName(999999999, "Random User");
            fail();
        } catch (NullPointerException e) {
            assertTrue(e != null);
        }
    }
}
