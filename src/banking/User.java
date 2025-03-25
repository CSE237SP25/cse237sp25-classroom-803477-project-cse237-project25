package banking;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

public class User {

    private String username;
    private String hashedPassword;
    private double balance;
    private List<Transaction> transactionHistory;
    private String accountNumber;
    
    public User(String username, String accountNumber, String hashedPassword, double balance) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.transactionHistory = new ArrayList<>();
    }
    public Object getHashedPassword() {
        return hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public double getBalance() {
        return balance;
    }
    //to check if two users are the same
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Double.compare(user.balance, balance) == 0 &&
               username.equals(user.username) &&
               hashedPassword.equals(user.hashedPassword);
    }

    //override user hashcode
    @Override
    public int hashCode() {
        return Objects.hash(username, hashedPassword, balance);
    }
    
    //Issue a charge
    public void issueCharge(double amount, String description) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction(-amount, "Charge: " + description));
        } 
        else if (amount <= 0) {
            System.out.println("Charge amount invalid.");
        } 
        else if (balance < amount) {
            System.out.println("Insufficient balance.");
        } 
        else {
            System.out.println("An error occurred. Please try again later.");
        }
    }

    //Request statement
    public void printStatement() {
        System.out.println("\n--- Account Statement ---");
        for (Transaction t : transactionHistory) {
            System.out.printf("[%s] %s: $%.2f\n", t.getDate(), t.getDescription(), t.getAmount());
        }
        System.out.printf("Current Balance: $%.2f\n", balance);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
    
}
