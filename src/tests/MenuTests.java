package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Banking.Menu;
import Banking.User;

public class MenuTests {
    
    @BeforeEach
    void setup() {
        this.menu = new Menu()
    }

    @Test
    void test_authentication() {
        User testUser = new User("Test","password",0);
        this.menu.datahandler.create_user(testUser);
        try {
            assertTrue(this.menu.authenticate_user_pass("Test","password"));
        }
        catch (Exception e) {
            this.menu.datahandler.delete_user("Test");
        }
        
    }

    @Test
    void test_user_creation() {
        boolean success = this.menu.create_user("Test","password",0);
        try {
            assertTrue(success);
            this.menu.datahandler.delete_user("Test");
        } catch (Exception e) {
            this.menu.datahandler.delete_user("Test");
        }
    }
}
