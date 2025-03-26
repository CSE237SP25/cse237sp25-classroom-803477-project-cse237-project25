package bankapp;

public class Main {

	public static void main(String[] args) {
		// TODO prompt the user to create a bank customer user
		
		// TODO prompt the user to create a bank account
		
		// TODO print the current status (user created and account created + account balance)
		
		// TODO prompt the user to create the first deposit
		
		Menu mainMenu = new Menu();
		mainMenu.createNewBankCustomerUserDisplay();
		while(true) {
			mainMenu.displayOptions();
		}
		
	}
}
