package bankapp;

import java.time.LocalDateTime;

class Transaction {
    private LocalDateTime currentTime;
    private double amount;
    private String transactionType;

    public Transaction(String transactionType, double amount) {
        this.currentTime = LocalDateTime.now();
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String toString() {
        return currentTime + " - " + transactionType + ": $" + amount;
    }
}