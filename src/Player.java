
public class Player {
	private final String name;
	private int money;
	private int amountOfGames;
	private int budgetPerGame;
	
	public Player(String name, int amountOfGames, int budgetPerGame) {
		this.name = name;
		this.amountOfGames = amountOfGames;
		this.budgetPerGame = budgetPerGame;
		money = 1000;
	}
	
	public int getMoney() {
		return money;
	}
	
	public String getName() {
		return name;
	}
}
