package AccountActions;
public class BankAccount {
    private String accountType;
    private double accountBalance;
    
    /**
     * Class for initializing the checking account and its methods
     * Includes several getter methods and our account actions.
     */
    
    //simple initialization constructor
    public BankAccount(String accountType) {
        this.accountType = accountType;
        this.accountBalance = 0.0;
    }

    public double getBalance() {
    	//simple getter function
        return accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public boolean withdraw(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
            //successful withdrawal
            return true;
        }
        // user can't withdraw that amount.
        return false;
    }

    public String getAccountType() {
        return accountType;
        //should be just checking for now
    }
}
