package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import bankapp.Customer;

public class CustomerTests {
	
	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		//2. Call the method being tested
		customer.getaccount().deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(customer.getaccount().getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		Customer customer = new Customer("Billy", "Bob", "Joe", "Billy@gmail.com",
				"3147289341", "1 Brookings Dr, St. Louis, MO 63130");
		
		try {
			customer.getaccount().deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

}
