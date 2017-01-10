import java.util.Random;

public class Deck {
	private static final int SUITS = 4;
	private static final int FACES = 13;
	private static final int CARDS_IN_DECK = 52;
	private static final int TOTAL_CARDS = 312;
	
	private Card[] cards;
	private Random random;
	
	/**
	 * Default constructor.
	 */
	public Deck() {
		// Initialize variables
		cards = new Card[TOTAL_CARDS];
		random = new Random();
		
		// Fill array with cards
		for (int deck = 0; deck < 6; deck++) {
			for (int suit = 0; suit < SUITS; suit++) {
				for (int face = 0; face < FACES; face++) {
					cards[deck * CARDS_IN_DECK +  suit * FACES + face] = new Card(suit, face);
				}
			}	
		}
  	}
	
	/**
	 * Draws one card from the deck and sets this card to null after returning it.
	 * @return a card from the deck.
	 */
	public Card drawCard() {
		Card retCard;
		do {
			int index = random.nextInt(TOTAL_CARDS);
			retCard = cards[index];
			cards[index] = null;
		} while (retCard == null);
		return retCard;
	}
}
