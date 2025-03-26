package bankapp;
import java.util.*;
import java.util.Scanner;


public class Menu {
	private BankAccount theAccount;
	private FixedDeposit fixedDeposit;
    private CreateAccount newAccount;
    private UpdateAccount updateAccount;
    private Map<Integer, List<Object>> accounts;

    private Scanner keyboardInput;

    public Menu() {
        theAccount = new BankAccount();
        fixedDeposit = new FixedDeposit();
        keyboardInput = new Scanner(System.in);
        this.accounts = new HashMap<>();
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
         System.out.println("6. Transfer Between Accounts");
         System.out.println("7. Create Account");
         System.out.println("8. Update Account Information");
    }
    
    public int readIntFromPlayer() {
		System.out.println("Enter your choice: ");
		int userChoice = keyboardInput.nextInt();
        keyboardInput.nextLine();
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
	        case 3:
	            handleViewTransaction();
	            break;
	        case 4:
	        	handleTotalBalance();
	            break;
	        case 5:
	        	handleInterest();
	        	break;
            case 6:
                handleTransfer();
                break;
            case 7:
                handleCreateAccount();
                break;
            case 8:
                handleUpdateAccount();
                break;
            default:
	            System.out.println("Invalid choice. Please enter a number between 1 and n.");
	    }
    }

    public void handleCreateAccount() {
        newAccount = new CreateAccount();
        System.out.println("Please Enter your name: ");
        String name = keyboardInput.nextLine();
        System.out.println("Please Enter your email: ");
        String email = keyboardInput.nextLine();
        System.out.println("Please Enter your phone number: ");
        String phoneNumber = keyboardInput.nextLine();

        try{
            newAccount.setName(name);
            newAccount.setEmail(email);
            newAccount.setPhoneNumber(phoneNumber);
            storeAccountInfo(name, email, phoneNumber);
        } catch (java.lang.Exception e) {
            System.out.println("Could not create account because: " + e.getMessage());
        }

    }

    public void storeAccountInfo(String name, String email, String phoneNumber) {
        int accountNumber = (int) (Math.random() * 900_000_000) + 100_000_000;
        List<Object> accountDetails = new ArrayList<>();
        accountDetails.add(name);
        accountDetails.add(phoneNumber);
        accountDetails.add(email);
        this.accounts.put(accountNumber, accountDetails);
        System.out.println("Your account has been created.");
        displayAccountDetails(accountNumber);
    }


    public void handleUpdateAccount() {
        updateAccount = new UpdateAccount(accounts);
        System.out.println("Please enter your account number: ");
        int accountNumber = keyboardInput.nextInt();
        keyboardInput.nextLine();

        while(true){
            try{
                updateAccount.validAccountNumber(accountNumber);
            } catch (java.lang.Exception e) {
                System.out.println( e.getMessage());
                break;
            }
            updateAccount.displayOptions();
            System.out.println("Enter your choice: ");
            int choice = keyboardInput.nextInt();
            keyboardInput.nextLine();
            if(choice == 1){
                handleUpdateName(accountNumber);
            } else if(choice == 2){
                handleUpdatePhoneNumber(accountNumber);
            } else if (choice == 3){
                handleUpdateEmail(accountNumber);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    public void handleUpdateName(int accountNumber){
        System.out.println("Please enter your updated name: ");
        String name = keyboardInput.nextLine();
        try{
            this.accounts = updateAccount.updateName(accountNumber, name);
            System.out.println("Please Find Below You Updated Information.");
            displayAccountDetails(accountNumber);
            System.out.println();
        } catch(java.lang.Exception e) {
            System.out.println( e.getMessage());
            System.out.println();
        }
    }

    public void handleUpdatePhoneNumber(int accountNumber){
        System.out.println("Please enter your new phone number: ");
        String phoneNumber = keyboardInput.nextLine();
        try{
            this.accounts = updateAccount.updatePhoneNumber(accountNumber, phoneNumber);
            System.out.println("Please Find Below You Updated Information.");
            displayAccountDetails(accountNumber);
            System.out.println();
        } catch(java.lang.Exception e) {
            System.out.println( e.getMessage());
            System.out.println();
        }
    }

    public void handleUpdateEmail(int accountNumber){
        System.out.println("Please enter your new email address");
        String email = keyboardInput.nextLine();
        try{
            this.accounts = updateAccount.updateEmail(accountNumber, email);
            System.out.println("Please Find Below You Updated Information.");
            displayAccountDetails(accountNumber);
            System.out.println();
        } catch(java.lang.Exception e) {
            System.out.println( e.getMessage());
            System.out.println();
        }
    }

    public void displayAccountDetails(int accountNumber){
            System.out.println();
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Name: " + this.accounts.get(accountNumber).get(0));
            System.out.println("Phone Number: " + this.accounts.get(accountNumber).get(1));
            System.out.println("Email: " + this.accounts.get(accountNumber).get(2));
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
    
    public void handleViewTransaction() {
    	theAccount.viewTransactionHistory();
    }

    public void handleInterest() {
    	fixedDeposit.printTerm();
    	int accountTerm = keyboardInput.nextInt();
    	double accountDeposit = fixedDeposit.getDeposit();
    	double accountCD = processInterest(accountTerm, accountDeposit);	
    	System.out.println("You will eventually get $" + accountCD + " from the fixed deposit");
    }
    
    public double processInterest(int term, double deposit) {
    	fixedDeposit.processSelection(term, deposit);
    	return theAccount.getFinalBalance(fixedDeposit);
    }
    
    public void handleTotalBalance() {
    	FixedDeposit fd = new FixedDeposit();
    	double accountFinalDeposit = theAccount.getFinalBalance(fd);
    	System.out.println("Your final balance is $"+ accountFinalDeposit +".");
    }
    
    public void processTotalBalance(FixedDeposit fd, double regularDeposit) {
    	theAccount.getFinalBalance(fd);
    }
    
    public void handleTransfer() {
        System.out.println("Enter amount to transfer: ");
        double amountToTransfer = keyboardInput.nextDouble();

        BankAccount recipientAccount = new BankAccount();

        try {
            theAccount.transferTo(recipientAccount, amountToTransfer);
            System.out.println("Transferred $" + amountToTransfer + " to the recipient account.");
            System.out.println("Your new balance: $" + theAccount.getCurrentBalance());
            System.out.println("Recipient balance: $" + recipientAccount.getCurrentBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }
}
