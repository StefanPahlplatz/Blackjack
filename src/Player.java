
public class Player {
	private final String name;
	private int money;
	private Hand[] hands;
	
	public Player(String name, int amountOfGames, int betPerGame) {		
		this.name = name;
		this.money = 1000;
		
		// Initialize the hands
		hands = new Hand[amountOfGames];
		for (int i = 0; i < hands.length; i++)
			hands[i] = new Hand(betPerGame);
	}
	
	public int getMoney() {
		return money;
	}
	
	public String getName() {
		return name;
	}
	
	public String printHand(int i) {
		return hands[i].toString();
	}
	
	public Hand[] getHands() {
		return hands;
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
