package bankapp;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            menu.displayMainMenu();
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    menu.createAccount();
                    break;
                case 2:
                    menu.login();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            
            if (menu.getCurrentAccount() != null) {
                boolean loggedIn = true;
                while (loggedIn) {
                    menu.displayLoggedInMenu();
                    choice = scanner.nextInt();
                    
                    switch (choice) {
                        case 1:
                            System.out.print("Enter deposit amount: ");
                            double amount = scanner.nextDouble();
                            menu.processUserInput(amount);
                            break;
                        case 2:
                            menu.transferMoney();
                            break;
                        case 3:
                            menu.checkBalance();
                            break;
                        case 4:
                            menu.logout();
                            loggedIn = false;
                            break;
                        case 5:
                            menu.changePassword();
                            break;
                        case 6:
                            menu.changeUsername();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            }
        }
        
        System.out.println("Thank you for using our banking application!");
        scanner.close();
    }
} 