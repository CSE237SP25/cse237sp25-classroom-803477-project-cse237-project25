package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import bankapp.BankAccount;
import bankapp.Menu;

public class MenuTests {
	
	@Test
	public void testInvalidChoice() {
		 Menu m = new Menu();
		 m.processUserInput(-1);
		 
		 BankAccount account = m.getAccount();
	     assertEquals(0, account.getCurrentBalance(), 0.005);
	}

    @Test
    public void testUserDeposit() {
        //1. Create objects to be tested
        Menu m = new Menu();
        
        //2. Call the method being tested
        m.processDeposit(25);
        
        //3. Use assertions to verify results
        BankAccount account = m.getAccount();
        assertEquals(25.0, account.getCurrentBalance(), 0.005);
    }
    
    @Test
    public void testWithdrawal() {
    	Menu m = new Menu();
    	
    	m.processDeposit(50);
        m.processWithdrawal(25);
        
        BankAccount account = m.getAccount();
        assertEquals(25.0, account.getCurrentBalance(), 0.005);
    }
}
