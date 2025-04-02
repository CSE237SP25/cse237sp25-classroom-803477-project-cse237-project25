package bankapp;

public class BankAccount {
    private double balance;
    private double creditBalance;

    public BankAccount() {
        this.balance = 0;
        this.creditBalance = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
    }

    public double getCurrentBalance() {
        return balance;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void borrowCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Borrow amount cannot be negative");
        }
        this.creditBalance += amount;
    }

    public void repayCredit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        if (amount > this.creditBalance) {
            throw new IllegalArgumentException("Cannot repay more than the current credit balance");
        }
        this.creditBalance -= amount;
    }

    /* Withdraw method
       Removes user-specified amount from balance, validates
       desired withdrawal amount with ensureValid 
    */
    public void withdraw(double withdrawalAmt) {
        if (ensureValid(withdrawalAmt)) {
            this.balance -= withdrawalAmt;
        }
    }

    /* 
       Verifies user's withdrawal amount is positive and not 
       greater than the amount in the user's balance. Throws
       IllegalArgumentException if either of the above cases
       is not true.
    */
    public boolean ensureValid(double withdrawalAmt) {
        if (withdrawalAmt > getCurrentBalance()) {
            throw new IllegalArgumentException("Attempting to withdraw sum greater than balance");
        } else if (withdrawalAmt <= 0) {
            throw new IllegalArgumentException("Must withdraw a positive sum of money");            
        } else {
            return true;
        }
    }
}