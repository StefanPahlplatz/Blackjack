
public class Player {
	private final String name;
	private double money;
	private int betPerGame;
	private Hand[] hands;
	
	/**
	 * Creates a player.
	 * @param name of the player.
	 * @param amountOfGames that the user wants to  play at once.
	 * @param betPerGame amount.
	 */
	public Player(String name, int amountOfGames, int betPerGame) {		
		this.name = name;
		this.money = 1000 - (betPerGame * amountOfGames);
		this.betPerGame = betPerGame;
		
		// Initialize the hands
		hands = new Hand[amountOfGames];
		this.reset();
	}
	
	/**
	 * Returns the player's current money.
	 * @return money.
	 */
	public double getMoney() {
		return money;
	}
	
	/**
	 * Add the given amount to the current balance.
	 * @param amount to add.
	 */
	public void addMoney(double amount) {
		money += amount;
	}
	
	/**
	 * Returns the name of the player.
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Only prints the cards in the given hand.
	 * @param i which hand.
	 * @return String of cards.
	 */
	public String printCardsInHand(int i) {
		return hands[i].printCards();
	}
	
	/**
	 * Prints the given hand.
	 * @param i which hand.
	 * @return String of the hand.
	 */
	public String printHand(int i) {
		return hands[i].toString();
	}
	
	/**
	 * Returns the hands
	 * @return Hand array
	 */
	public Hand[] getHands() {
		return hands;
	}
	
	/**
	 * Returns the amount of hands.
	 * @return hand amount.
	 */
	public int amountOfHands() {
		return hands.length;
	}
	
	/**
	 * Resets the player, clears all cards.
	 */
	public void reset() {
		// Clear the hands
		for (int i = 0; i < hands.length; i++)
			hands[i] = new Hand(betPerGame);
	}
	
	/**
	 * String representation of the player.
	 */
	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();
		for (int i = 0; i < hands.length; i++) {
			retString.append(name + ", hand " + (i + 1) + ": " + hands[i].toString() + "\n");
		}
		return retString.toString();
	}
}
