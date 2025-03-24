package bankapp;

public class Transaction {
	
	private String type;
	private double amount;
	private String time;
	
	public Transaction(String type, double amount, String time) {
		this.type = type;
		this.amount = amount;
		this.time = time;
	}
	
	public void showTransaction() {
		System.out.println(type + " $" + amount + " at " + time);
	}
}