package bankapp;

import java.util.*;
import java.util.regex.*;

public class CreateAccount {

    private String email;
    private String phoneNumber;
    private int accountNumber;
    private String name;
    private Map<Integer, List<Object>> accounts;

    public CreateAccount() {
        this.accounts = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+\\d{1,3}\\s?)?(\\(\\d{3}\\)|\\d{3})[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }

    public String getName(){
        return this.name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getEmail(){
        return this.email;
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }
}
