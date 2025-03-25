package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bankapp.BankCustomer;
import bankapp.BankRecord;
import bankapp.Menu;

public class MenuTests {
	
	@Test
	public void testSignUpNewCustomer() {
		// 1. Set up objects being tested
		Menu menu = new Menu();
		BankRecord bankRecord = new BankRecord();
		String username = "TestUser";
		String password = "TestPassword1";
 
		
		//2. Call method under test
        BankCustomer newCustomer = new BankCustomer(username, password, bankRecord);
        
        
        //3. Use assertions to verify results
        assertEquals(username, newCustomer.getUsername());			
	}
	
	@Test
	public void testMultipleSignUps() {
		//1. Set up objects being tested
		
		Menu menu = new Menu ();
		BankRecord bankRecord = new BankRecord();
		String username1 = "UserName1";
		String username2 = "UserName2";
		String password1 = "Password1";
		String password2 = "Password2";
		
		//2. Call method under test
        BankCustomer customer1 = new BankCustomer(username1, password1, bankRecord);
        BankCustomer customer2 = new BankCustomer(username2, password2, bankRecord);
        
        //3. Use assertions to verify results
        assertEquals(username1, customer1.getUsername());
        assertEquals(username2, customer2.getUsername());
	}
}