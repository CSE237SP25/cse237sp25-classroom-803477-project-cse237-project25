package bankapp;

import java.util.Scanner;

public class Menu {
	  private BankAccount theAccount;

	    public Menu(){
	        theAccount = new BankAccount();
	    }

	    //display methods do not need to be tested
	    public void displayOptions(){
	        System.out.println("Please select an option:");
	        System.out.println("1. Deposit");
	        System.out.println("2. Withdraw");
	    }

	    public int getUserChoice(){
	        try (Scanner keyboardInput = new Scanner(System.in)) {
				return keyboardInput.nextInt();
			}
	    }

	    //methods that require user input don't need to be tested
	    public double getUserAmount(){
	        try (Scanner keyboardInput = new Scanner(System.in)) {
				return keyboardInput.nextDouble();
			}
	    }

	    //can and should be tested
	    public void processUserInput(int choice, double amount){
	        if (choice == 1) {
	            theAccount.deposit(amount);
	            System.out.println("Deposited: " + amount);
	        } else if (choice == 2) {
	            try {
	                theAccount.withdraw(amount);
	                System.out.println("Withdrew: " + amount);
	            } catch (IllegalArgumentException e) {
	                System.out.println("Invalid withdrawal amount.");
	            }
	        } else {
	            System.out.println("Invalid option.");
	        }
	        
	    }

	    public BankAccount getAccount(){
	        return theAccount;
	    }


}
