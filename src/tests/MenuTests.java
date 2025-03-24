package tests;

import bankapp.Menu;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MenuTests {
	
	@Test
	public void testCustomerBankAccountCreation() {
		
		Menu testMenu = new Menu();
		testMenu.createCustomerUser("testUser");
		testMenu.createBankAccount("name");
		assertEquals(1, testMenu.getAccountList().size());
		
	}

}
