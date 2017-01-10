import java.util.Scanner;

public class Blackjack {
	private static final int DEALER = 0;
	private static final int PLAYER = 1;
	
	private Player[] players;
	private Deck deck;
	
	/**
	 * Initialize a game of plackjack.
	 * @param player a fully initialized player.
	 */
	public Blackjack(Player player) {
		// Initialize variables
		players = new Player[2];
		players[DEALER] = new Player("Dealer", 1, 0);
		players[PLAYER] = player;
		this.deck = new Deck();
		
		// Draw 2 cards for each player
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].getHands().length; j++) {
				// Draw one card for everyone
				players[i].getHands()[j].dealCard(deck.drawCard());
				
				// Draw another card if the it's not the dealer
				if (i != DEALER)
					players[i].getHands()[j].dealCard(deck.drawCard());
			}
		}
	}
	
	/**
	 * Starts one game of blackjack
	 */
	public void play() {
		playUserHand();
		
		playDealerHand();
		
		calculateResult();
	}
	
	/**
	 * Calculates the results after the player and the dealer played.
	 */
	private void calculateResult() {
		boolean dealerLost = players[DEALER].getHands()[0].isDead();
		int dealerPoints = players[DEALER].getHands()[0].getTotal();
		String message = "null";
		String name = players[PLAYER].getName();
		
		// Show the dealer's status
		if (dealerLost)
			System.out.println("The dealer lost!");
		else
			System.out.println("The dealer passed and ended with " + dealerPoints + " points.");
			
		
		// Calculate result for each hand
		for (int i = 0; i < players[PLAYER].getHands().length; i++) {
			int myPoints = players[PLAYER].getHands()[i].getTotal();
			int betAmount = players[PLAYER].getHands()[i].betAmount();
			
			// Lost
			if ((myPoints < dealerPoints && !dealerLost) || players[PLAYER].getHands()[i].isDead()) {
				message = String.format("%1$s, you lost hand %2$d with a bet of %3$d", name, i + 1, betAmount);
			// Blackjack
			} else if (myPoints == 21) {
				message = String.format("%1$s, you win hand %2$d with a bet of %3$d", name, i + 1, betAmount);
				players[PLAYER].addMoney(betAmount * 2.5);
			// Draw
			} else if (myPoints == dealerPoints && myPoints <= 21) {
				message = String.format("%1$s, hand %2$d is a draw, you get %3$s back", name, i + 1, betAmount);
				players[PLAYER].addMoney(betAmount);
			// Won
			} else if (myPoints > dealerPoints && myPoints <= 21) {
				message = String.format("%1$s, you win hand %2$d with a bet of %3$d", name, i + 1, betAmount);
				players[PLAYER].addMoney(betAmount * 2);
			// Dealer lost
			} else if (myPoints <= 21 && dealerLost) {
				message = String.format("%1$s, you win hand %2$d with a bet of %3$d", name, i + 1, betAmount);
				players[PLAYER].addMoney(betAmount * 2);
			}
			
			// Print the status
			System.out.println(message);
		}
		
		// Show the players budget
		System.out.println("Your total budget is now " + players[PLAYER].getMoney());
	}
	
	/**
	 * Play the dealers hand
	 */
	private void playDealerHand() {
		// Print the dealers cards
		System.out.println("Dealer: " + players[DEALER].printCardsInHand(0));
		
		// Sleep for 100ms so that the enter key in not pressed immediately
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Print wait message
		System.out.print("Press enter to make the dealer play...");

		// Wait for the user to press enter		
		try { 
			System.in.read(); 
		} catch (Exception ex) { };
		
		// Deal cards while the total is <= 17
		while (players[DEALER].getHands()[0].getTotal() <= 17) {
			// Deal card
			dealHand(DEALER, 0);
			
			// Print the dealers cards
			System.out.println("Dealer: " + players[DEALER].printCardsInHand(0));
		}
	}
	
	/**
	 * Let the user play
	 */
	private void playUserHand() {
		// Print the current cards
		printSituation();
		
		// Keep track of the amount of passed hands
		int handsPassed = 0;
		
		// While not all hands are passed
		while (handsPassed < players[PLAYER].getHands().length) {			
			// Loop through all hands
			for (int handIndex = 0; handIndex < players[PLAYER].amountOfHands(); handIndex++) {
				Hand currentHand = players[PLAYER].getHands()[handIndex];
				
				// If the hand is not passed
				if (!currentHand.isPassed()) {
					// Get the user action
					String action = getAction(handIndex);

					// Preform the action
					if (action.equals("p")) {
						passHand(handIndex);
					} else if (action.equals("d")) {
						dealHand(PLAYER, handIndex);
					} else if (action.equals("2")) {
						doubleHand(handIndex);
					}
					
				} else {
					// If the hand is passed increment handsPassed
					handsPassed++;
				}
			}
			
			if (handsPassed < players[PLAYER].getHands().length) {
				printSituation();
			}
		}
	}
		
	/**
	 * Pass the given hand
	 * @param handIndex
	 */
	private void passHand(int handIndex) {
		players[PLAYER].getHands()[handIndex].pass();
	}
		
	/**
	 * Deals a card to the given player and hand.
	 * @param player which player to deal to.
	 * @param handIndex which hand to deal to.
	 */
	private void dealHand(int player, int handIndex) {
		players[player].getHands()[handIndex].dealCard(deck.drawCard());
	}
	
	/**
	 * Doubles the bet of the hand if the money of the player allows it, otherwise just draw a card.
	 * @param handIndex Index of the hand.
	 */
	private void doubleHand(int handIndex) {
		if (players[PLAYER].getHands()[handIndex].betAmount() * 2 < players[PLAYER].getMoney())
			players[PLAYER].getHands()[handIndex].doubleBet();
		players[PLAYER].getHands()[handIndex].dealCard(deck.drawCard());
		System.out.print("New situation: ");
		System.out.println(players[PLAYER].printHand(handIndex));
	}

	/**
	 * Gets the users choice of action.
	 * @param hand integer to show which hand we're on. 
	 * @return "p", "d" or "2".
	 */
	private String getAction(int hand) {
		String qAction = players[PLAYER].getName() + ", what do you want to do with hand " + (hand + 1) + "?: ";
		String[] validActions = { "p", "d", "2" };
		return Input.getString(new Scanner(System.in), qAction, validActions);
	}
	
	/**
	 * Prints the current situation
	 */
	private void printSituation() {
		System.out.println("\n***************************************************************");
		System.out.println("Dealer: " + players[DEALER].printCardsInHand(0));
		System.out.println("---------------------------------------------------------------");
		System.out.println(players[PLAYER].toString());
		System.out.println("***************************************************************\n");
	}
	
	/**
	 * Resets the players.
	 */
	public void reset() {
		for (Player player : players) {
			player.reset();
		}
	}
}
