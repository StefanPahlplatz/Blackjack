
public class Player {
	private final String name;
	private double money;
	private Hand[] hands;
	
	public Player(String name, int amountOfGames, int betPerGame) {		
		this.name = name;
		this.money = 1000 - (betPerGame * amountOfGames);
		
		// Initialize the hands
		hands = new Hand[amountOfGames];
		for (int i = 0; i < hands.length; i++)
			hands[i] = new Hand(betPerGame);
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
	
	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();
		for (int i = 0; i < hands.length; i++) {
			retString.append(name + ", hand " + (i + 1) + ": " + hands[i].toString() + "\n");
		}
		return retString.toString();
	}
}
