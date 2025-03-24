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
        myDatabase.createUser("Test", "password", 0);
        assertEquals(true, myDatabase.doesUserExist("Test"));
    }

    @Test
    void testGetUser() {
        User curUser = myDatabase.createUser("Test", "password", 0);
        assertEquals(curUser, myDatabase.getUserData("Test"));
    }

    @Test
    void testDeleteUser() {
        User curUser = myDatabase.createUser("Test", "password", 0);
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
