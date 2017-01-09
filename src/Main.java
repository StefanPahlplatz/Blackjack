

/*
 * Main. Hierin maak je een instantie van Casino aan en start het spel.
 */
public class Main {
	public static void main(String[] s) {
		/*for (int i = 0; i < 13; i++) {
			Card card = new Card(0, i);
			System.out.println(card.toString() + " - " + card.getValue());
		}*/
		
		Casino casino = new Casino();
		casino.run();
	}
}
