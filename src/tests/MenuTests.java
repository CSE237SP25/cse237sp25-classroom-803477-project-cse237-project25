package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import banking.Menu;
import banking.User;

public class MenuTests {
    
    @BeforeEach
    void setup() {
        this.menu = new Menu()
    }

    @Test
    void testAuthentication() {
        User testUser = new User("Test","password",0);
        this.menu.dataHandler.createUser(testUser);
        try {
            assertTrue(this.menu.authenticateUserPass("Test","password"));
        }
        catch (Exception e) {
            this.menu.dataHandler.deleteUser("Test");
        }
        
    }

    @Test
    void testUserCreation() {
        boolean success = this.menu.createUser("Test","password",0);
        try {
            assertTrue(success);
            this.menu.dataHandler.deleteUser("Test");
        } catch (Exception e) {
            this.menu.dataHandler.deleteUser("Test");
        }
    }
}
