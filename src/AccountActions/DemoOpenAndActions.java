package AccountActions;
import java.util.Scanner;

/**
 * Basic usage of the open account and account actions systems.
 */
public class DemoOpenAndActions {
    public static void main(String[] args) {
    	// open up scanner
        Scanner scanner = new Scanner(System.in);
        
        // open new account
        BankAccount account = PromptHandler.openAccount(scanner);
        //prompt user for actions
        PromptHandler.commandLoop(account, scanner);
        //finish up.
        scanner.close();
    }
}
