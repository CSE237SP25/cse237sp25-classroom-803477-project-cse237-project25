package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bankapp.FixedDeposit;

public class FixedDepositTests {
		
	@Test
	public void testFixedDeposit() {
		//1. Set up
		FixedDeposit cdAccount = new FixedDeposit();
		
		//2. Call methods
		cdAccount.processSelection(1, 10000);
		double finalDeposit=cdAccount.getFinalDeposit();
		
		//3. Verify results
		assertEquals(finalDeposit, 13097.4, 0.05);
	}
}