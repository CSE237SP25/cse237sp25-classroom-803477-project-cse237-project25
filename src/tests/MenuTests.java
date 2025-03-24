package tests;

import bankapp.Menu;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MenuTests {

	@Test
	public void testInvalidMenuSelection() {
		String simulatedInput = "5\n0\n2\n";
        InputStream originalSystemIn = System.in; //save original System.in
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            Menu testMenu = new Menu();
            int selection = testMenu.getUserMenuInput(3);
            
           //assert that the getUserMenuInput eventually selects the valid input, 2
            assertEquals(2, selection);
        } finally {
            System.setIn(originalSystemIn); //restore System.in
        }
	}
	
	@Test
	public void testValidMenuSelection() {
		String simulatedInput = "1\n"; //try selection 1
        InputStream originalSystemIn = System.in; //save original System.in
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            Menu testMenu = new Menu();
            testMenu.displayOptions(); //option 1 will be selected (Create account)
            
            //ensure createAccount() was called by asserting that there is 1 account in the account list
            assertEquals(1, testMenu.getAccountList().size());
        } finally {
            System.setIn(originalSystemIn); //restore System.in
        }
	}

}
