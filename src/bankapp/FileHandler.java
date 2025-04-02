package bankapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    private static final String FILE_PATH = "customers.txt";

/* Save customers to file (CSV format)-- DON'T USE - assumes savings accounts stored in a list
    public static void saveAccounts(List<SavingsAccount> SavingsAccounts) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (SavingsAccount SavingsAccount : loadSavingsAccounts()) {
                String line = String.format("%s,%s,%.2f%n",
                    customer.getName(),
                    customer.getAccountId(),
                    customer.getBalance());
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
    }
 */   
    
    //Updates file for each account
    public static void updateFile(int accountNumber, int userId, double balance) {
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            String line = String.format("%d, %d, %.2f%n", accountNumber, userId, balance);
            writer.write(line);
        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }

    // Load saving accounts from file
    public static List<SavingsAccount> loadSavingsAccounts() {
        List<SavingsAccount> SavingsAccounts = new ArrayList<>();
        File accounts = new File(FILE_PATH);
        if (!accounts.exists()) return SavingsAccounts;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int accountNumber = Integer.parseInt(parts[0]);
                    int userId = Integer.parseInt(parts[1]);
                    double balance = Double.parseDouble(parts[1]);
                    SavingsAccounts.add(new SavingsAccount(userId, balance));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
        }
        return SavingsAccounts;
    }
}
