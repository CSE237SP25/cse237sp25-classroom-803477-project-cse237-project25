package tests;




import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import bankapp.BankAccount;
import bankapp.BankCustomer;

public class BankCustomerTests {
	
	
	
	//testing bank customer account creation - testing that username is initialized appropriately
	@Test
	public void testBankCustomerAccountCreationUsername() {
		BankCustomer testUser = new BankCustomer("testUser");
		String username = testUser.getUsername();
		assertEquals(username, "testUser");

	}
	
	//checking that account list is empty for a new customer
	@Test
	public void testBankCustomerAccountCreationAccountList() {
		BankCustomer testUser = new BankCustomer("testUser");
		ArrayList<BankAccount> emptyAccountList = new ArrayList<BankAccount>();
		assertEquals(emptyAccountList, testUser.getAccountList());
		
	}
	
	@Test
	public void testRenameAccount() {
		BankCustomer testUser = new BankCustomer("testUser");
		testUser.renameAccount("newName"); 
		assertEquals("newName", testUser.getUsername());

	}

}
