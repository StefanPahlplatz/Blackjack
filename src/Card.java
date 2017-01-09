public class Card {
	private final char suit;
	private final String face;
	
	public Card(char suit, String face) {
		this.suit = suit;
		this.face = face;
	}
	
	@Override
	public String toString() {
		return suit + face;
	}
}
