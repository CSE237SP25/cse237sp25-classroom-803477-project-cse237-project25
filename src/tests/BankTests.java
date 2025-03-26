package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.Bank;
import bankapp.BankAccount;

public class BankTests {
    
    @Test
    public void testCreateAccount() {
        Bank bank = new Bank();
        bank.createAccount("user1", "pass1", "ACC001");
        assertTrue(bank.accountExists("user1"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void testCreateDuplicateAccount() {
        Bank bank = new Bank();
        bank.createAccount("user1", "pass1", "ACC001");
        bank.createAccount("user1", "pass2", "ACC002");
    }
    
    @Test
    public void testSuccessfulLogin() {
        Bank bank = new Bank();
        bank.createAccount("user1", "pass1", "ACC001");
        BankAccount account = bank.login("user1", "pass1");
        assertNotNull(account);
        assertEquals("user1", account.getUsername());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLoginWithWrongPassword() {
        Bank bank = new Bank();
        bank.createAccount("user1", "pass1", "ACC001");
        bank.login("user1", "wrongpass");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLoginWithNonExistentAccount() {
        Bank bank = new Bank();
        bank.login("nonexistent", "pass1");
    }
} 