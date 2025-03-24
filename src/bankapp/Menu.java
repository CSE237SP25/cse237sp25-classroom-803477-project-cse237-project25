package bankapp;

import java.util.Scanner;

public class Menu {
	private BankAccount theAccount;
	private FixedDeposit fixedDeposit;
    private Scanner keyboardInput;

    public Menu() {
        theAccount = new BankAccount();
        fixedDeposit = new FixedDeposit();
        keyboardInput = new Scanner(System.in);
    }
    
    public BankAccount getAccount() {
        return theAccount;
    }

    public void displayOptions() {
		 System.out.println("\n ~ Bank Menu ~");
         System.out.println("1. Deposit");
         System.out.println("2. Withdraw");
         System.out.println("3. View Transaction History");
         System.out.println("4. Check Current Balance");
         System.out.println("5. Fixed Deposit");
    }
    
    public int readIntFromPlayer() {
		System.out.println("Enter your choice: ");
		int userChoice = keyboardInput.nextInt(); 
		return userChoice;
	}
    
    public void processUserInput(int userChoice) {
    	switch (userChoice) {
	        case 1:
	            handleDeposit();
	            break;
	        case 2:
	            handleWithdrawal();
	            break;
	        // add more cases here like view transaction history, account balance, etc.
	        case 3:
	            // handle view transaction logic
	            break;
	        case 4:
	        	  // handle view account balance logic
	            break;
	        case 5:
	        	handleInterest();
	        	break;
	        default:
	            System.out.println("Invalid choice. Please enter a number between 1 and n.");
	    }
    }

    public void handleDeposit() {
    	System.out.println("Please enter deposit amount: ");
        double amountToDeposit = keyboardInput.nextDouble();
        processDeposit(amountToDeposit);
        System.out.println("You deposited $" + amountToDeposit + " into your account.");
    }
    
    public void processDeposit(double amount) {
        theAccount.deposit(amount);
    }
    
    public void handleWithdrawal() {
    	System.out.println("Please enter withdrawal amount: ");
        double amountToWithdraw = keyboardInput.nextDouble();
        processWithdrawal(amountToWithdraw);
        System.out.println("You withdrew $" + amountToWithdraw + " from your account.");
    }
    
    public void processWithdrawal(double amount) {
        theAccount.withdraw(amount);
    }

    public void handleInterest() {
    	fixedDeposit.printTerm();
    	int accountTerm = keyboardInput.nextInt();
    	double accountDeposit = fixedDeposit.getDeposit();
    	processInterest(accountTerm, accountDeposit);	
    	System.out.println("You put $" + accountDeposit + " in fixed deposit for " + accountTerm + " month.");
    }
    
    public void processInterest(int term, double deposit) {
    	fixedDeposit.processSelection(term, deposit);
    }
}
