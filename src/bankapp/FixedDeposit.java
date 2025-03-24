package bankapp;

import java.util.Scanner;

public class FixedDeposit {
	
	private double deposit;
	private double interestRate;
	private int month;
	private Scanner keyboardInput;

	public FixedDeposit() {
		this.deposit = 0;
		this.interestRate = 0;
		this.month = 0;
		keyboardInput = new Scanner(System.in);
	}

	public void printTerm() {
		System.out.println("Select By Term:");
		System.out.println("1. 7 months");
		System.out.println("2. 10 months");
		System.out.println("3. 13 months");
	}

	public int getTerm() {
		System.out.print("Please select a term: ");
		return keyboardInput.nextInt();	
	}

	public double getDeposit() {
		System.out.print("Please enter a deposit amount: $");
		return keyboardInput.nextDouble();
	}

	public void processSelection(int userTerm, double userDeposit) {
		if (userTerm == 1) {
			month = 7;
			interestRate = 0.0393;
		} else if (userTerm == 2) {
			month = 10;
			interestRate = 0.0340;
		} else if (userTerm == 3) {
			month = 13;
			interestRate = 0.0272;
		} else {
			System.out.println("Invalid selection. Using default values.");
			month = 0;
			interestRate = 0;
		}
		
		deposit = userDeposit * Math.pow((1 + interestRate), month);
		//System.out.printf("Final amount after %d months: $%.2f\n", month, deposit);
	}

	public double getFinalDeposit() {
		return deposit;
	}

	public void closeScanner() {
		keyboardInput.close();
	}
}