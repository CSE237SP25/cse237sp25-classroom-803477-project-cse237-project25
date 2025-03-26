package bankapp;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, BankAccount> accounts;
    
    public Bank() {
        accounts = new HashMap<>();
    }
    
    public void createAccount(String username, String password, String accountNumber) {
        if (accounts.containsKey(username)) {
            throw new IllegalStateException("Username already exists");
        }
        accounts.put(username, new BankAccount(username, password, accountNumber));
    }
    
    public BankAccount login(String username, String password) {
        BankAccount account = accounts.get(username);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (!account.validatePassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return account;
    }
    
    public BankAccount getAccount(String username) {
        return accounts.get(username);
    }
    
    public boolean accountExists(String username) {
        return accounts.containsKey(username);
    }
}