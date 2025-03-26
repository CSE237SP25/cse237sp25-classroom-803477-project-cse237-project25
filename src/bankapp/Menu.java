package bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private BankCustomer currentCustomer;
	ArrayList<BankAccount> accountList;

	BankAccount currentAccount; 
	Scanner keyboardInput;


	private int numPrimaryMenuItems = 3;

	//we should think about/ask about the "has-a" framework and whether these instance variables are ok

	public Menu() {
		accountList = new ArrayList<BankAccount>();
		keyboardInput = new Scanner(System.in);
	}

	//Gabriela
	public BankCustomer createCustomerUser(String username) {
		//set as currentCustomer
		//set accountList instance variable from currentCustomer
		currentCustomer = new BankCustomer(username);
		return currentCustomer;
	}

	public void createNewBankCustomerUserDisplay() {
		System.out.println("To create a customer account, enter an account name:");
		String usernameInput = getUserStringInput();
		this.createCustomerUser(usernameInput);
	}

	//Gabriela
	public void createBankAccount(String accountName) {
		// TODO take in a string and call bank account constructor
		// TODO print account successfully created
		//System.out.println("What do you want to name this account?");
		//String accountName = getUserStringInput();
		BankAccount account = currentCustomer.openAccount(accountName);
		currentAccount = account;
		accountList = currentCustomer.getAccountList();
		System.out.println("Account succesfully created");

	}


	//Gabriela
	public void displayAccountList() {
		//prints out account names and balances
		//using method made in BankAccount class to print out name+balance of bank account
		//no unit tests, string output
		for(BankAccount account : accountList) {
			account.displayBankAccount();
			System.out.println();
		}
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
		int userSelection = getUserMenuInput(numPrimaryMenuItems); //made a parameter for how many menu options there are. Not sure how to not hard code it or if there's a better way

		processMenuSelection(userSelection);
	}

	//Claire
	public void processMenuSelection(int userSelection) {
		switch (userSelection) {
		case 1: //Create account
			System.out.println("Enter account name:");
			String accountName = getUserStringInput();
			createBankAccount(accountName);
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

		System.out.println("Menu Options:"
				+ "\n1. Deposit"
				+ "\n2. Withdraw"
				+ "\n3. Rename");
		System.out.println("\nPlease press a number key to indicate your selection.");
		int userSelection = getUserMenuInput(numPrimaryMenuItems); 

	}	
	
	
	public void processAccountSelection(int userSelection) {
		switch (userSelection) {
		case 1: //deposit
			System.out.println("Enter amount to deposit (must greater than 0:");
			double depositAmount = getUserDoubleInput(); 
			currentAccount.deposit(depositAmount); 
			break;

		case 2: //withdraw
			System.out.println("Enter amount to deposit (must greater than 0:");
			double withdrawlAmount = getUserDoubleInput(); 
			currentAccount.deposit(withdrawlAmount);
			break;

		case 3: //rename
			System.out.println("Enter a new account name (must be at least one 1 character and be a unique name");
			String rename = getUserStringInput();
			currentCustomer.renameAccount(rename); 
			break;
		}
	}



	public int getUserMenuInput(int numMenuItems) {
		//Scanner keyboardInput = new Scanner(System.in);
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
		//Scanner keyboardInput = new Scanner(System.in);
		double userInput = keyboardInput.nextDouble();
		return userInput;
	}

	public double getUserDoubleInput() {
		//Scanner keyboardInput = new Scanner(System.in);
		double userInput = keyboardInput.nextDouble();
		return userInput;
	}

	//can and should test methods that process user input

	public void processUserInput(double amount) {
		//can handle deposits and withdrawls
	}

	public String getUserStringInput() {

		String userInput = keyboardInput.next();
		return userInput;

	}

	public BankAccount getAccount() {
		return currentAccount;
	}

	public ArrayList<BankAccount> getAccountList(){
		return accountList;
	}


}