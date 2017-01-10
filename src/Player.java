
public class Player {
	private final String name;
	private double money;
	private int betPerGame;
	private Hand[] hands;
	
	public Player(String name, int amountOfGames, int betPerGame) {		
		this.name = name;
		this.money = 1000 - (betPerGame * amountOfGames);
		this.betPerGame = betPerGame;
		
		// Initialize the hands
		hands = new Hand[amountOfGames];
		this.reset();
	}
	
	public double getMoney() {
		return money;
	}
	
	public void addMoney(double amount) {
		money += amount;
	}
	
	public String getName() {
		return name;
	}
	
	public String printCardsInHand(int i) {
		return hands[i].printCards();
	}
	
	public String printHand(int i) {
		return hands[i].toString();
	}
	
	public Hand[] getHands() {
		return hands;
	}
	
	public int amountOfHands() {
		return hands.length;
	}
	
	public void reset() {
		// Clear the hands
		for (int i = 0; i < hands.length; i++)
			hands[i] = new Hand(betPerGame);
	}
	
	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();
		for (int i = 0; i < hands.length; i++) {
			retString.append(name + ", hand " + (i + 1) + ": " + hands[i].toString() + "\n");
		}
		return retString.toString();
	}
}
