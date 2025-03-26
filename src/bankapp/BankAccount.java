package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

	private double balance;
	private List<Double> balanceHistory;
	private boolean isFrozen;
	
	public BankAccount() {
		this.balanceHistory = new ArrayList<>();
		this.balance = 0;
		this.isFrozen = false;
		balanceHistory.add(this.balance);
	}
	
	public void deposit(double amount) {
		if(!this.isFrozen) {
			if(amount < 0) {
				throw new IllegalArgumentException();
			}
			this.balance += amount;
			balanceHistory.add(this.balance);
		}
		
	}

	public void withdraw(double amount){
		if(!this.isFrozen) {
			if(amount < 0 || amount > this.balance){
		        throw new IllegalArgumentException();
		    }
		    this.balance -= amount;
		    balanceHistory.add(this.balance);
		}
	}
	
	public void freeze() {
		this.isFrozen = true;
	}
	
	public void unfreeze() {
		this.isFrozen = false;
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}

       public List<Double> getBalanceHistory() {
		return new ArrayList<>(balanceHistory);
        }
	
	public boolean getFrozenStatus() {
		return this.isFrozen;
	}
}
