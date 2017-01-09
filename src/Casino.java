import java.util.Scanner;

public class Casino {
	
	public void run() {
		Player player = initializeUser();
		Blackjack blackjack;
		
		do {
			blackjack = new Blackjack(player);
			blackjack.play();
		} while (Input.getYesNo(new Scanner(System.in), "Do you want to play again? (yes/no): "));
		System.out.println("\n\nThank you for playing");	
	}
	
	private Player initializeUser() {
		Scanner sc = new Scanner(System.in);
		
		// Welcome the user
		System.out.println("Welcome to the iCasino! Let's play blackjack!");
		System.out.println("\nCommands:\n----------");
		System.out.println("p = pass\nd = turn\n2 = double bet");
		
		// Create a player with the correct name
		System.out.print("\nWhat is your name?: ");
		String name = Input.getString(sc);
		System.out.printf("Welcome %1s, Your starting budget is %2d\n", name, 1000);
		
		// Get the amount of games he wants to play
		String qAmountOfGames = "How many games do you want to play (1-5)?: ";
		int amountOfGames = Input.getInt(sc, 1, 5, qAmountOfGames);
		
		// Get the amount per game
		String qBudgetPerGame = String.format("How big is your bet per game (1-%1d)?: ", 1000 / amountOfGames);
		int budgetPerGame = Input.getInt(sc, 1, 1000 / amountOfGames, qBudgetPerGame);
		System.out.println();
		
		// Create the player
		return new Player(name, amountOfGames, budgetPerGame);
	}
}
