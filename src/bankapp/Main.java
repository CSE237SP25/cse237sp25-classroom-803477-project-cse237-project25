package bankapp;

public class Main {
	 public static void main(String[] args) {
		Menu menu = new Menu();
		while (true) {
			menu.displayOptions();
			int userChoice = menu.readIntFromPlayer();
	        menu.processUserInput(userChoice);
		}
	}
}
