package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import banking.Database;
import banking.User;

public class DatabaseTest {
    private Database myDatabase;

    @BeforeEach
    public void setUp() {
        myDatabase = new Database();
    }

    @Test
    void testCheckNonexistingUser() {
        assertEquals(false, myDatabase.doesUserExist("Test"));
    }

    @Test
    void testCreateUser() {
    	User testUser = new User("Test", "123", "password", 0);
        myDatabase.createUser(testUser);
        assertEquals(true, myDatabase.doesUserExist("Test"));
    }

    @Test
    void testGetUser() {
    	User testUser = new User("Test","123", "password", 0);
        User curUser = myDatabase.createUser(testUser);
        assertEquals(curUser, myDatabase.getUserData("Test"));
    }

    @Test
    void testDeleteUser() throws Exception {
    	User testUser = new User("Test", "123", "password", 0);
        User curUser = myDatabase.createUser(testUser);
        myDatabase.deleteUser("Test");
        assertEquals(false, myDatabase.doesUserExist("Test"));
    }

    @Test
    void testDeleteNonExistingUser() {
        assertThrows(RuntimeException.class, () -> {
            myDatabase.deleteUser("UnknownUser");
        });
    }
}
