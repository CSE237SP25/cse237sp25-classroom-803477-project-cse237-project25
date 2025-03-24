package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Banking.Transaction;

public class TransactionTests {

    @Test
    void testTransactionCreation() {
        Transaction t = new Transaction(50.0, "Amazon gift purchase");
        assertEquals(50.0, t.getAmount());
        assertEquals("Amazon gift purchase", t.getDescription());
    }
}