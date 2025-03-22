package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bankapp.CreateAccount;
import bankapp.BankAccount;

import java.util.HashMap;
import java.util.Scanner;
import java.io.ByteArrayInputStream;

public class CreateAccountTests {
    private HashMap<String, String> userDatabase;
    private Scanner scanner;
    private CreateAccount createAccount;

    @Before
    public void setUp() {
        userDatabase = new HashMap<>();
    }

    @Test
    public void testCreateNewAccount() {
        String input = "username123\npassword123\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        createAccount = new CreateAccount(userDatabase, scanner);

        BankAccount account = createAccount.authenticateUser();
        assertNotNull(account);
        assertTrue(userDatabase.containsKey("username123"));
        assertEquals("password123", userDatabase.get("username123"));
    }

    @Test
    public void testLoginSuccess() {
        userDatabase.put("username123", "password123");
        String input = "username123\npassword123\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        createAccount = new CreateAccount(userDatabase, scanner);

        BankAccount account = createAccount.authenticateUser();
        assertNotNull(account);
    }

    @Test
    public void testLoginFailureAndRetry() {
        userDatabase.put("username123", "password123");
        String input = "username123\nwrongpassword\nusername123\npassword123\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        createAccount = new CreateAccount(userDatabase, scanner);

        BankAccount account = createAccount.authenticateUser();
        assertNotNull(account);
    }

    @Test
    public void testCreateAccountWithExistingUsername() {
        userDatabase.put("username123", "password123");
        String input = "username123\npassword123\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        createAccount = new CreateAccount(userDatabase, scanner);

        BankAccount account = createAccount.authenticateUser();
        assertNotNull(account);
        assertEquals("password123", userDatabase.get("username123"));
    }
}
