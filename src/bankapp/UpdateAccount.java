package bankapp;

import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.regex.*;
import java.util.Scanner;

public class UpdateAccount {

    private Map<Integer, List<Object>> accounts;

    public UpdateAccount(Map<Integer, List<Object>> accounts) {

        this.accounts = accounts;
    }

    public void displayOptions(){
        System.out.println("What Information do you want to update?: ");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email");
        System.out.println("4. Exit");
    }

    public void validAccountNumber(int accountNumber){
        if(!accounts.containsKey(accountNumber)){
            throw new IllegalArgumentException("Account number does not exist, please retry with a valid account number");
        }
    }

    public Map<Integer, List<Object>> updateName(int accountNumber, String name) {
        validAccountNumber(accountNumber);
        this.accounts.get(accountNumber).set(0, name);
        return this.accounts;
    }

    public Map<Integer, List<Object>> updatePhoneNumber(int accountNumber, String phoneNumber) {
        validAccountNumber(accountNumber);
        String phoneRegex = "^(\\+\\d{1,3}\\s?)?(\\(\\d{3}\\)|\\d{3})[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            this.accounts.get(accountNumber).set(1, phoneNumber);
        } else {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        return this.accounts;
    }

    public Map<Integer, List<Object>> updateEmail(int accountNumber, String email) {
        validAccountNumber(accountNumber);
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            this.accounts.get(accountNumber).set(2, email);
        } else{
            throw new IllegalArgumentException("Invalid email format.");
        }
        return this.accounts;
    }
}
