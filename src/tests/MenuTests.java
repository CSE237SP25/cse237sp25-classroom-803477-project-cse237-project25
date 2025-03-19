package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import bankapp.Menu;
import bankapp.BankAccount;

import java.awt.*;

public class MenuTests {
	 @Test
	    public void testUserDeposit(){
	        Menu menu = new Menu();
	        menu.processUserInput(1, 25.0); // 1 represents deposit
	        BankAccount account = menu.getAccount();
	        assertEquals(25.0, account.getCurrentBalance(), 0.005);
	    }

	    @Test
	    public void testUserWithdraw(){
	        Menu menu = new Menu();
	        menu.processUserInput(1, 50.0); // Deposit first
	        menu.processUserInput(2, 25.0); // Withdraw
	        BankAccount account = menu.getAccount();
	        assertEquals(25.0, account.getCurrentBalance(), 0.005);
	    }
	    
	    @Test
	    public void testInvalidChoice() {
	        Menu menu = new Menu();
	        menu.processUserInput(3, 20.0); // Invalid choice
	        BankAccount account = menu.getAccount();
	        assertEquals(0.0, account.getCurrentBalance(), 0.005); // Balance should remain unchanged
	    }

}
