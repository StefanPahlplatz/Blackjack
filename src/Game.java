import java.util.Scanner;

public class Game {
	private Player player;
	private Scanner sc;
	
	public Game() {
		sc = new Scanner(System.in);
	}
	
	public void run() {
		initializeUser();
		
		Deck deck = new Deck();
	}
	
	private void initializeUser() {
		// Welcome the user
		System.out.println("Welcome to the iCasino! Let's play blackjack!");
		System.out.println("\nCommands:\n----------");
		System.out.println("p = pass\nd = turn\n2 = double bet");
		
		// Create a player with the correct name
		System.out.print("\nWhat is your name?: ");
		String name = Input.getString(sc);
		System.out.printf("Welcome %1s, Your starting budget is %2d", name, 1000);
		
		// Get the amount of games he wants to play
		System.out.print("\nHow many games do you want to play (1-5)?: ");
		int amountOfGames = Input.getInt(sc, 1, 5);
		
		// Get the amount per game
		System.out.printf("How big is your bet per game (1-%1d)?: ", 1000 / amountOfGames);
		int budgetPerGame = Input.getInt(sc, 1, 1000 / amountOfGames);
		System.out.println("\n***************************************************************");
		
		player = new Player(name, amountOfGames, budgetPerGame);
	}
}
