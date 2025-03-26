package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

import bankapp.BankAccount;
import bankapp.Menu;

public class MenuLoginTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    private ByteArrayOutputStream testOut;

    @SuppressWarnings("unchecked")
    private HashMap<String, BankAccount> getAccountsMap() throws Exception {
        Field accountsField = Menu.class.getDeclaredField("accounts");
        accountsField.setAccessible(true);
        return (HashMap<String, BankAccount>) accountsField.get(null);
    }

    @BeforeEach
    public void resetAccountsMap() throws Exception {
        getAccountsMap().clear();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void setMenuScanner(InputStream inputStream) throws Exception {
        Field scannerField = Menu.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(null, new Scanner(inputStream));
    }

    private String runStaticMenuMethodWithInput(String inputData, String methodName) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(inputData.getBytes());
        System.setIn(testIn);

        try {
            setMenuScanner(testIn);

            Method method = Menu.class.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return "TEST FAILED: EXCEPTION THROWN - " + e.getMessage();
        }
        return testOut.toString();
    }

    @Test
    void testCreateAccount_Success() throws Exception {
        String input = "testUser\npassword123\n";
        String output = runStaticMenuMethodWithInput(input, "createAccount");

        assertTrue(output.contains("Account created successfully!"), "Output should confirm creation.");
        HashMap<String, BankAccount> accounts = getAccountsMap();
        assertTrue(accounts.containsKey("testUser"), "Accounts map should contain the new user.");
        assertNotNull(accounts.get("testUser"), "Account object should not be null.");
        assertEquals("testUser", accounts.get("testUser").getUsername());
        assertEquals("password123", accounts.get("testUser").getPassword());
    }

    @Test
    void testCreateAccount_DuplicateUsername() throws Exception {
        String input1 = "testUser\npassword123\n";
        runStaticMenuMethodWithInput(input1, "createAccount");

        String input2 = "testUser\notherpass\n";
        String output2 = runStaticMenuMethodWithInput(input2, "createAccount");

        assertTrue(output2.contains("Username already exists."), "Output should indicate duplicate.");
        assertEquals(1, getAccountsMap().size(), "Only one account should exist.");
    }

     @Test
    void testCreateAccount_EmptyUsername() throws Exception {
        String input = "\n";
        String output = runStaticMenuMethodWithInput(input, "createAccount");
        assertFalse(output.contains("Account created successfully!"), "Account should not be created.");
        assertTrue(getAccountsMap().isEmpty(), "Accounts map should be empty.");
    }

     @Test
    void testCreateAccount_EmptyPassword() throws Exception {
        String input = "testUser\n\n";
        String output = runStaticMenuMethodWithInput(input, "createAccount");
        assertFalse(output.contains("Account created successfully!"), "Account should not be created.");
        assertTrue(getAccountsMap().isEmpty(), "Accounts map should be empty.");
    }


    @Test
    void testLogin_Success() throws Exception {
    	String createInput = "loginUser\nloginPass\n";
    	runStaticMenuMethodWithInput(createInput, "createAccount");

    	String loginInput = "1\nloginUser\nloginPass\n\n";
    	String loginOutput = runStaticMenuMethodWithInput(loginInput, "login");

    	 assertTrue(loginOutput.contains("Login successful!"), "Output should confirm successful login.");
    }

    @Test
    void testLogin_WrongPassword() throws Exception {
        String createInput = "loginUser\nloginPass\n";
        runStaticMenuMethodWithInput(createInput, "createAccount");

        String loginInput = "1\nloginUser\nwrongPass\n3\n";
        String loginOutput = runStaticMenuMethodWithInput(loginInput, "login");

        assertTrue(loginOutput.contains("Invalid login credentials."), "Output should indicate failure.");
        assertFalse(loginOutput.contains("Login successful!"), "Output should not say login was successful.");
    }

    @Test
    void testLogin_UserNotFound() throws Exception {
        String loginInput = "1\nnonExistentUser\nanyPass\n3\n";
        String loginOutput = runStaticMenuMethodWithInput(loginInput, "login");

        assertTrue(loginOutput.contains("Invalid login credentials."), "Output should indicate failure (user not found).");
        assertFalse(loginOutput.contains("Login successful!"), "Output should not say login was successful.");
    }

    @Test
    void testForgotPassword_Success() throws Exception {
        String createInput = "resetUser\noldPass\n";
        runStaticMenuMethodWithInput(createInput, "createAccount");

        String forgotInput = "2\nresetUser\nnewPass\nnewPass\n3\n";
        String forgotOutput = runStaticMenuMethodWithInput(forgotInput, "login");

        assertTrue(forgotOutput.contains("Password updated successfully!"), "Output should confirm password update.");

        HashMap<String, BankAccount> accounts = getAccountsMap();
        assertTrue(accounts.containsKey("resetUser"), "User should still exist.");
        assertEquals("newPass", accounts.get("resetUser").getPassword(), "Password in map should be updated.");
    }

    @Test
    void testForgotPassword_PasswordMismatch() throws Exception {
        String createInput = "resetUser\noldPass\n";
        runStaticMenuMethodWithInput(createInput, "createAccount");

        String forgotInput = "2\nresetUser\nnewPass1\nnewPass2\n3\n";
        String forgotOutput = runStaticMenuMethodWithInput(forgotInput, "login");

        assertTrue(forgotOutput.contains("Passwords do not match."), "Output should indicate mismatch.");

        HashMap<String, BankAccount> accounts = getAccountsMap();
        assertEquals("oldPass", accounts.get("resetUser").getPassword(), "Password should remain unchanged.");
    }

     @Test
    void testForgotPassword_EmptyNewPassword() throws Exception {
        String createInput = "resetUser\noldPass\n";
        runStaticMenuMethodWithInput(createInput, "createAccount");

        String forgotInput = "2\nresetUser\n\n\n3\n";
        String forgotOutput = runStaticMenuMethodWithInput(forgotInput, "login");

        assertFalse(forgotOutput.contains("Password updated successfully!"), "Password should not be updated.");

        HashMap<String, BankAccount> accounts = getAccountsMap();
        assertEquals("oldPass", accounts.get("resetUser").getPassword(), "Password should remain unchanged.");
    }


    @Test
    void testForgotPassword_UserNotFound() throws Exception {
        String forgotInput = "2\nnonExistentUser\nnewPass\nnewPass\n3\n";
        String forgotOutput = runStaticMenuMethodWithInput(forgotInput, "login");

        assertTrue(forgotOutput.contains("Username does not exist."), "Output should indicate user not found.");
        assertFalse(forgotOutput.contains("Password updated successfully!"), "Output should not say password updated.");
    }
}