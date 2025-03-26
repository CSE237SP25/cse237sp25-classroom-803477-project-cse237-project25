package bankapp;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

	private Scanner scanner;
	private HashMap<String, User> users;  // Store registered users
	private User currentUser;             // Track the logged-in user
	private double balance;
	public Menu() {
		this.scanner = new Scanner(System.in);
		this.users = new HashMap<>();
		this.currentUser = null;
		this.balance= 100;
	}

	public void runStartingConfiguration() {
		this.displayWelcomeMessage();

		while (true) {
			this.displayMenuOptions();

			String menuChoice = this.handleUserMenuInput();
			this.handleUserMenuSelection(menuChoice);
		}
	}

	public void handleUserMenuSelection(String menuChoice) {
		if (menuChoice.equalsIgnoreCase("register") && currentUser == null) {
			this.registerUser();
		} else if (menuChoice.equalsIgnoreCase("login") && currentUser == null) {
			this.userLogin();
		} else if (menuChoice.equalsIgnoreCase("logout") && currentUser != null) {
			this.logout();
		} else {
			System.out.println("Invalid selection or action not allowed.");
		}
	}

	public boolean checkRegisterMenuSelection(String input) {
		return input.equalsIgnoreCase("register");
	}

	public boolean checkLoginMenuSelection(String input) {
		return input.equalsIgnoreCase("login");
	}

	public void userLogin() {
		this.displayLoginOptions();
		if (this.checkYes(this.handleUserBooleanInput())) {
			String username = promptUsername();
			if (!users.containsKey(username)) {
				System.out.println("User not found. Try again.");
				return;
			}

			String password = promptPassword();
			validateAndLoginUser(username, password);
		} else {
			this.runStartingConfiguration();
		}
	}

	public void registerUser() {
		this.displayRegisterOptions();
		if (this.checkYes(this.handleUserBooleanInput())) {
			String username = promptUsername();

			if (users.containsKey(username)) {
				System.out.println("Username already exists. Try again.");
				return;
			}

			String password = promptPassword();
			createUser(username, password);
		} else {
			this.runStartingConfiguration();
		}
	}

	public void logout() {
		if (currentUser != null) {
			System.out.println("Logged out: " + currentUser.getUsername());
			currentUser = null;
		} else {
			System.out.println("No user is currently logged in.");
		}
	}

	private String promptUsername() {
		System.out.println("Enter Username: ");
		return this.scanner.nextLine();
	}

	private String promptPassword() {
		System.out.println("Enter Password: ");
		return this.scanner.nextLine();
	}

	private void validateAndLoginUser(String username, String password) {
		User user = users.get(username);
		if (user.validatePassword(password)) {
			currentUser = user;
			System.out.println("Logged in successfully as: " + username);
		} else {
			System.out.println("Incorrect password.");
		}
	}

	private void createUser(String username, String password) {
		User newUser = new User(username, password);
		users.put(username, newUser);
		currentUser = newUser;
		System.out.println("Created and logged into new account!");
	}

	public boolean checkYes(String input) {
		return input.equalsIgnoreCase("yes");
	}

	public boolean checkNo(String input) {
		return input.equalsIgnoreCase("no");
	}

	public String handleUserMenuInput() {
		String input = this.scanner.nextLine().toLowerCase();

		while (this.checkIncorrectUserMenuInput(input)) {
			System.out.println("Please enter a correct menu selection.");
			input = this.scanner.nextLine().toLowerCase();
		}

		return input;
	}

	public boolean checkIncorrectUserMenuInput(String input) {
		String lowerInput = input.toLowerCase();

		if (currentUser == null) {
			return !(lowerInput.equals("register") || lowerInput.equals("login"));
		} else {
			return !lowerInput.equals("logout");
		}
	}

	public String handleUserBooleanInput() {
		String input = this.scanner.nextLine().toLowerCase();

		while (this.checkIncorrectUserBooleanInput(input)) {
			System.out.println("Please enter a correct menu selection.");
			input = this.scanner.nextLine().toLowerCase();
		}

		return input;
	}

	public boolean checkIncorrectUserBooleanInput(String input) {
		String lowerInput = input.toLowerCase();

		return !(lowerInput.equals("yes") || lowerInput.equals("no"));
	}

	public void displayWelcomeMessage() {
		System.out.println("Welcome to the bank.");
	}

	public void displayMenuOptions() {
		System.out.println("\nMENU OPTIONS:");
		if (currentUser == null) {
			System.out.println("> REGISTER");
			System.out.println("> LOGIN");
		} else {
			System.out.println("> LOGOUT");
		}
	}

	public void withdraw(double amount) {
		if(amount > this.balance || amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance -= amount;
		System.out.println("Withdrawl completed!")
	}

	public double getCurrentBalance() {
		return this.balance;
	}
}

	public void displayLoginOptions() {
		System.out.println("Are you sure you want to login? If not, you will be taken to the main menu.");
		System.out.println("> YES");
		System.out.println("> NO");
	}

	public void displayRegisterOptions() {
		System.out.println("Are you sure you want to register? If not, you will be taken to the main menu.");
		System.out.println("> YES");
		System.out.println("> NO");
	}
}
