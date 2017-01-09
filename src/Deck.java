
public class Deck {
	private static final char[] suits = {'♠', '♥', '♦', '♣'};
	private static final String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private Card[] cards;
	
	public Deck() {
		// Initialize array
		cards = new Card[312];
		
		// Fill array with cards
		for (int k = 0; k < 6; k++) {
			for (int i = 0; i < suits.length; i++) {
				for (int j = 0; j < faces.length; j++) {
					cards[k * 52 +  i * 13 + j] = new Card(suits[i], faces[j]);
				}
			}	
		}
  	}
}
