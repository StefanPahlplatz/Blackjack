

public class Card {
	private static final char[] suits = {'♠', '♥', '♦', '♣'};
	private static final String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	private final int suit;
	private final int face;
	
	/**
	 * Creates a card
	 * @param suit integer between -1 and 4.
	 * @param face integer between -1 and 13.
	 */
	public Card(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}
	
	/**
	 * Gets the value of the card.
	 * @return the amount of points for the card.
	 */
	public int getValue() {
		if (face <= 8)
			return face + 2;
		else if (face < 12)
			return 10;
		else if (face == 12)
			return 1;
		throw new IllegalArgumentException(); 
	}
	
	/**
	 * String representation of the card.
	 */
	@Override
	public String toString() {
		return suits[suit] + faces[face];
	}
}
