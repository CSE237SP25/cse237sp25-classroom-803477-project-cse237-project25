package bankapp;

import java.util.ArrayList;

public class BankCustomer {

	String username;
	ArrayList<BankAccount> accountList;
	
	
	public BankCustomer(String username) {
		this.username = username;
		accountList = new ArrayList<BankAccount>();
	}
	
	public boolean openAccount(String accountName) {
		return false;
	}
	
	public boolean transferMoney(BankAccount a, BankAccount b) {
		return false;
	}
	
	public ArrayList<BankAccount> getAccountList(){
		return accountList;
	}
	
	public boolean renameAccount(String newAccountName) {
		return false;
	}
}
