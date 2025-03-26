package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BillPayment {
    private Bank bank;
    private List<String> paymentHistory;
    
    public BillPayment(Bank bank) {
        if (bank == null) {
            throw new IllegalArgumentException("Bank cannot be null");
        }
        this.bank = bank;
        this.paymentHistory = new ArrayList<>();
    }
    
    public void payBill(BankAccount account, String billProvider, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Bank account cannot be null");
        }
        
        if (billProvider == null || billProvider.trim().isEmpty()) {
            throw new IllegalArgumentException("Bill provider cannot be empty");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Bill payment amount must be positive");
        }
        
        try {
            account.withdraw(amount);
            String paymentRecord = "Paid " + billProvider + " - $" + String.format("%.2f", amount);
            paymentHistory.add(paymentRecord);
            System.out.println("Bill paid successfully: " + paymentRecord);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Insufficient funds to pay bill");
        }
    }
    
    public boolean isValidBillProvider(String billProvider) {
        return billProvider != null && !billProvider.trim().isEmpty();
    }
    
    public List<String> getPaymentHistory() {
        return new ArrayList<>(paymentHistory);
    }
}
