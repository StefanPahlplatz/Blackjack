import java.util.Scanner;

/*
 * Blackjack. Hierin wordt één spelletje Blackjack gespeeld.
 */
public class Blackjack {
	private static final int DEALER = 0;
	private static final int PLAYER = 1;
	
	private Player[] players;
	private Deck deck;
	
	public Blackjack(Player player) {
		// Initialize variables
		players = new Player[2];
		players[DEALER] = new Player("Dealer", 1, 0);
		players[PLAYER] = player;
		this.deck = new Deck();
		
		// Draw 2 cards for each player
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].getHands().length; j++) {
				players[i].getHands()[j].dealCard(deck.drawCard());
				
				if (i != DEALER)
					players[i].getHands()[j].dealCard(deck.drawCard());
			}
		}
		
		play();
	}
	
	private void play() {
		int handsPassed = 0;
		
		// While not all hands are passed
		do {
			printSituation();
			
			// Loop through all hands
			for (int hand = 0; hand < players[PLAYER].amountOfHands(); hand++) {
				// If the hand is not passed
				if (!players[PLAYER].getHands()[hand].isPassed()) {

					// Process the user input
					switch (getAction(hand)) {
					// Pass
					case "p":
						players[PLAYER].getHands()[hand].pass();
						break;
					
					// Deal
					case "d":
						players[PLAYER].getHands()[hand].dealCard(deck.drawCard());
						break;

					// Double bet + deal
					case "2":
						if (players[PLAYER].getHands()[hand].betAmount() * 2 < players[PLAYER].getMoney())
							players[PLAYER].getHands()[hand].doubleBet();
						players[PLAYER].getHands()[hand].dealCard(deck.drawCard());
						System.out.print("New situation: ");
						System.out.println(players[PLAYER].printHand(hand));
						break;
					default:
						throw new IllegalArgumentException();
					}
				} else {
					handsPassed++;
				}
			}
		} while (handsPassed < players[PLAYER].getHands().length);
		
		printSituation();
		
		System.out.println(players[DEALER].toString());
		System.out.print("Press enter to make the dealer play...");
		try { System.in.read(); } catch (Exception ex) {};
		System.out.println("ts");
	}
	
	private String getAction(int hand) {
		String qAction = players[PLAYER].getName() + ", what do you want to do with hand " + (hand + 1) + "?: ";
		String[] validActions = { "p", "d", "2" };
		return Input.getString(new Scanner(System.in), qAction, validActions);
	}
	
	private void printSituation() {
		System.out.println("\n***************************************************************");
		System.out.println("Dealer: " + players[DEALER].printCardsInHand(0));
		System.out.println("---------------------------------------------------------------");
		System.out.println(players[PLAYER].toString());
		System.out.println("***************************************************************\n");
	}
}
