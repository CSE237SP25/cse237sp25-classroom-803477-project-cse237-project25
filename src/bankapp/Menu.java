package bankapp;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static HashMap<String, BankAccount> accounts = new HashMap<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to the Banking App!");
		boolean running = true;

		while (running) {
			System.out.println("\n1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			int choice = -1;
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
			} finally {
				scanner.nextLine();
			}

			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				login();
				break;
			case 3:
				running = false;
				break;
			default:
				if (choice != -1) {
					System.out.println("Invalid option.");
				}
			}
		}
		System.out.println("Thank you for using the app!");
		scanner.close();
	}

	private static void createAccount() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine().trim();

		if (username.isEmpty()) {
			System.out.println("Username cannot be empty.");
			return;
		}

		if (accounts.containsKey(username)) {
			System.out.println("Username already exists.");
			return;
		}

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		if (password.isEmpty()) {
			System.out.println("Password cannot be empty.");
			return;
		}

		BankAccount newAccount = new BankAccount();
		newAccount.setUsername(username);
		newAccount.setPassword(password);
		accounts.put(username, newAccount);

		System.out.println("Account created successfully!");
	}

	private static void login() {
		boolean loginMenuActive = true;
		while (loginMenuActive) {
			System.out.println("\n1. Login");
			System.out.println("2. Forgot Password");
			System.out.println("3. Back to Main Menu");
			System.out.print("Choose an option: ");

			int option = -1;
			try {
				option = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
			} finally {
				scanner.nextLine();
			}

			switch (option) {
			case 1:
				System.out.print("Username: ");
				String username = scanner.nextLine();
				System.out.print("Password: ");
				String password = scanner.nextLine();

				BankAccount acc = accounts.get(username);
				if (acc != null && acc.validatePassword(password)) {
					System.out.println("Login successful!");
					accountMenu(acc);
					loginMenuActive = false;
				} else {
					System.out.println("Invalid login credentials. Please try again.");
				}
				break;
			case 2:
				forgotPassword();
				break;
			case 3:
				System.out.println("Returning to main menu...");
				loginMenuActive = false;
				break;
			default:
				if (option != -1) {
					System.out.println("Invalid option. Please try again.");
				}
			}
		}
	}

	private static void forgotPassword() {
		 System.out.print("Enter username: ");
         String username = scanner.nextLine();
         if (!accounts.containsKey(username)) {
             System.out.println("Username does not exist.");
             return;
         }

         System.out.print("Enter new password: ");
         String newPassword = scanner.nextLine();

         if (newPassword.isEmpty()) {
             System.out.println("Password cannot be empty.");
             return;
        }

         System.out.print("Confirm new Password: ");
         String confirmPassword = scanner.nextLine();

         if (!newPassword.equals(confirmPassword)) {
             System.out.println("Passwords do not match.");
             return;
         }

         BankAccount acc = accounts.get(username);
         acc.setPassword(newPassword);
         System.out.println("Password updated successfully!");
     }

     private static void accountMenu(BankAccount acc) {
         boolean loggedIn = true;
         while (loggedIn) {
             System.out.println("\nWelcome, " + acc.getUsername());
             System.out.println("(Account menu - press Enter to 'logout' for now)");
             scanner.nextLine();
             loggedIn = false;
         }
         System.out.println("Logged out.");
     }
}
