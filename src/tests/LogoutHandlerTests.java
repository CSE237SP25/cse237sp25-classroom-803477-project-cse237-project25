package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import bankapp.BankAccount;
import bankapp.CreateAccount;

public class LogoutHandlerTests {
	
	@Test
	public void testAccountLogout() {
		CreateAccount newAccount = new CreateAccount();
		newAccount.setName("test");
		newAccount.setEmail("test@gmail.com");
		newAccount.setPhoneNumber("1112223333");
		
		List<Object> myProfile = new ArrayList<>();
		myProfile.add(newAccount.getName());
		myProfile.add(newAccount.getEmail());
		myProfile.add(newAccount.getPhoneNumber());
		Map<Integer, List<Object>> hashmap = new HashMap<>();
		hashmap.put(newAccount.getAccountNumber(), myProfile);
		
		BankAccount account = new BankAccount();
		account.deposit(20);
		
		account.logout(hashmap);
		
		assertEquals(0, account.getTransactionCount());
		assertEquals(account.getCurrentBalance(), 0.0, 0.005);
		assertTrue(hashmap.isEmpty());
	}
}
