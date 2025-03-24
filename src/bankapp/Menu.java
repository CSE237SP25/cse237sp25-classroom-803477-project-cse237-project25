package bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private BankCustomer currentCustomer;
	ArrayList<BankAccount> accountList;
	BankAccount currentAccount; 
	//we should think about/ask about the "has-a" framework and whether these instance variables are ok
	
	public Menu() {
		accountList = new ArrayList<BankAccount>();
	}
	
	//Gabriela
	public BankCustomer createCustomerUser(String username) {
		//set as currentCustomer
		//set accountList instance variable from currentCustomer
		currentCustomer = new BankCustomer(username);
		return currentCustomer;
	}
	
	//Gabriela
	public void createBankAccount() {
		// TODO take in a string and call bank account constructor
		// TODO print account successfully created
		
	}
	
	//Gabriela
	public void displayAccountList() {
		//prints out account names and balances
	}
	
	//Claire
	public void displayOptions() {
		// TODO print options like press 1
		//handle invalid value case - handled in getUserMenuInput() ? we can change that though
		
		//create account, view account list, modify account (deposit, withdraw, rename)
		
		//also do if statements or switch statements that executes relevant methods according to the menuChoice passed in
		System.out.println("Menu Options:"
				+ "\n1. Create account"
				+ "\n2. View account list"
				+ "\n3. Modify an account (deposit, withdraw, or rename)");
		System.out.println("\nPlease press a number key to indicate your selection.");
		int userSelection = getUserMenuInput(3); //made a parameter for how many menu options there are. Not sure how to not hard code it or if there's a better way
		
		switch (userSelection) {
			case 1: //Create account
				createBankAccount();
				break;
				
			case 2: //View account list
				displayAccountList();
				break;
				
			case 3: //Modify an account (deposit, withdraw, or rename)
				displayAccountModificationOptions();
				break;
		}
	}

	//Melena
	public void displayAccountModificationOptions() {
		// TODO print options for account
		//  the number that the user inputed
		//handle invalid value case
		
		//set currentAccount instance variable to the account selected by the user
		
		
		//TODO print options for account modifications
		// deposit, withdraw, rename conditionals
		// TODO execute relevant methods according to the selection
	}
	
	
	
	
	public int getUserMenuInput(int numMenuItems) {
		Scanner keyboardInput = new Scanner(System.in);
		int userInput = keyboardInput.nextInt();
		while(userInput < 1 || userInput > numMenuItems) {
			System.out.println("Not a valid input. Please select an option 1 through " + numMenuItems + " on your keyboard.");
			userInput = keyboardInput.nextInt();
		}
		return userInput;
	}
	
	
	//TODO maybe make a getAccountList() getter? or alternative. but make sure testValidMenuSelection works in MenuTests.java
	
	//deal with later
	//methosd that req user inptu don't need to be tested
	public double getUserInputDouble() {
		Scanner keyboardInput = new Scanner(System.in);
		double userInput = keyboardInput.nextDouble();
		return userInput;
	}
	
	//can and should test methods that process user input
	
	public void processUserInput(double amount) {
		//can handle deposits and withdrawls
	}
	
	public void processUserInput(String amount) {
		//currentAccount.deposit(amount);
	}
	
	public BankAccount getAccount() {
		return currentAccount;
	}
	

}