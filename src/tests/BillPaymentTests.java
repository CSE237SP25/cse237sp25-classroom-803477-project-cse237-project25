package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import bankapp.Bank;
import bankapp.BankAccount;
import bankapp.BillPayment;

public class BillPaymentTests {
    @Test
    public void testSuccessfulBillPayment() {
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        billPayment.payBill(testAccount, "Electric Company", 100.00);
        
        assertEquals(400.00, testAccount.getBalance(), 0.005);
    }
}