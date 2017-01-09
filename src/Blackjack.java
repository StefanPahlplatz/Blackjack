
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
		players[DEALER] = new Player("Dealer", 2, 0);
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
		
		printSituation();
	}
	
	private void printSituation() {
		System.out.println("***************************************************************");
		System.out.println("Dealer: " + players[DEALER].printHand(0));
		System.out.println("---------------------------------------------------------------");
		System.out.println(players[PLAYER].toString());
		System.out.println("***************************************************************");
	}
}
