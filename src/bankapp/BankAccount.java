package bankapp;

public class BankAccount {

	private double balance;
    private List<String> transactionHistory;
	
	public BankAccount() {
		this.balance = 0;
        this.transactionHistory = new ArrayList<>();
	}
	
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
        transactionHistory.add("Deposited: $" + amount);
	}

	public void withdraw(double amount){
	        if(amount < 0 || amount > this.balance()){
	        	throw new IllegalArgumentException();
	        }
	        this.balance -= amount; 
        	transactionHistory.add("Withdrew: $" + amount);       
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}


    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}
