package bankapp;

import java.util.List;
import java.util.Map;

public class UpdateAccount {

    private Map<Integer, List<Object>> accounts;

    public UpdateAccount(CreateAccount createdAccounts) {
        this.accounts = createdAccounts.getAccounts();
    }

    public boolean validAccountNumber(int accountNumber) {
        return this.accounts.containsKey(accountNumber);
    }

    public void updateName(int accountNumber, String name) {
        if (!validAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number.");
        }
        this.accounts.get(accountNumber).set(0, name);
    }

    public void updatePhoneNumber(int accountNumber, String phoneNumber) {
        if (!validAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number.");
        }
        this.accounts.get(accountNumber).set(1, phoneNumber);
    }

    public void updateEmail(int accountNumber, String email) {
        if (!validAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number.");
        }
        this.accounts.get(accountNumber).set(2, email);
    }
}
