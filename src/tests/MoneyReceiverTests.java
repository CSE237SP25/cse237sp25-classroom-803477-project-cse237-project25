package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import bankapp.Bank;
import bankapp.BankAccount;
import bankapp.MoneyReceiver;

public class MoneyReceiverTests {
    @Test
    public void testSuccessfulMoneyReceive() {
        Bank bank = new Bank();
        bank.createAccount("sender", "senderpass", "123456");
        bank.createAccount("recipient", "recipientpass", "789012");
        
        BankAccount senderAccount = bank.getAccount("sender");
        BankAccount recipientAccount = bank.getAccount("recipient");
        senderAccount.deposit(500.00);
        recipientAccount.deposit(100.00);
        
        MoneyReceiver moneyReceiver = new MoneyReceiver(bank);
        
        moneyReceiver.receiveMoney(recipientAccount, "sender", 200.00);
        
        assertEquals(300.00, senderAccount.getBalance(), 0.005);
        assertEquals(300.00, recipientAccount.getBalance(), 0.005);
    }
}