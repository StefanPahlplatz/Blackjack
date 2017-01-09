public class Card {
	private static final char[] suits = {'♠', '♥', '♦', '♣'};
	private static final String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	private final int suit;
	private final int face;
	
	public Card(int suit, int face) {
		this.suit = suit;
		this.face = face;
	}
	
	public int getValue() {
		if (face <= 8)
			return face + 2;
		else if (face < 13)
			return 10;
		else if (face == 13)
			return 1;
		return -1;
	}
	
	@Override
	public String toString() {
		return suits[suit] + faces[face];
	}
}
