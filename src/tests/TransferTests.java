package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;

public class TransferTests {
    
    @Test
    public void testSuccessfulTransfer() {
        BankAccount sender = new BankAccount("user1", "pass1", "ACC001");
        BankAccount recipient = new BankAccount("user2", "pass2", "ACC002");
        
        sender.deposit(100.0);
        sender.transfer(recipient, 50.0);
        
        assertEquals(50.0, sender.getBalance(), 0.001);
        assertEquals(50.0, recipient.getBalance(), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTransferNegativeAmount() {
        BankAccount sender = new BankAccount("user1", "pass1", "ACC001");
        BankAccount recipient = new BankAccount("user2", "pass2", "ACC002");
        
        sender.deposit(100.0);
        sender.transfer(recipient, -50.0);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testTransferInsufficientFunds() {
        BankAccount sender = new BankAccount("user1", "pass1", "ACC001");
        BankAccount recipient = new BankAccount("user2", "pass2", "ACC002");
        
        sender.deposit(100.0);
        sender.transfer(recipient, 150.0);
    }
} 