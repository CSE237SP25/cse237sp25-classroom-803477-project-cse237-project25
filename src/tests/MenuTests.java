package tests;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import banking.Database;
import banking.Menu;
import banking.User;

public class MenuTests {
    
    private Menu menu;
    MessageDigest md;

	@BeforeEach
    void setup() throws Exception {
        Scanner keyboardInput = new Scanner(System.in);
        Database dataHandler = new Database();
        this.menu = new Menu(keyboardInput,dataHandler);
        
        md = MessageDigest.getInstance("SHA-256");
    }

    @Test
    void testAuthentication() throws Exception {
    	
        String password = "password";
        byte[] hashBytes = md.digest(password.getBytes()); // Use UTF-8 for consistency

        // Convert hash bytes to hex string
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        String hashedPassword = sb.toString();

        User testUser = new User("Test", "123",hashedPassword,0);
        
        this.menu.getDataHandler().createUser(testUser);
        try {
            assertTrue(this.menu.authenticateUserPass("Test","password"));
        }
        catch (Exception e) {
            this.menu.getDataHandler().deleteUser("Test");
        }
        
    }

    @Test
    void testUserCreation() throws Exception {
    	
        boolean success = this.menu.createUser("Test","123","password",0);
        try {
            assertTrue(success);
            this.menu.getDataHandler().deleteUser("Test");
        } catch (Exception e) {
            this.menu.getDataHandler().deleteUser("Test");
        }
    }
}
