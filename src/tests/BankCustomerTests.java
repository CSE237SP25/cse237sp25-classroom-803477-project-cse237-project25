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
	public void testCustomerBankAccountCreation() {
		BankCustomer testUser = new BankCustomer("testUser");
		BankAccount testBankAccount = testUser.openAccount("testing");
		
		assertEquals(testUser.getAccountList().size(), 1);
		assertEquals(testBankAccount.getCurrentBalance(), 0, 0.01);
	}

}
