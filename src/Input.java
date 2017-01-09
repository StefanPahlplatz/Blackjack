import java.util.Scanner;

public class Input {
	/**
	 * Gets a string from the user
	 * @param sc Scanner
	 * @return user input
	 */
	public static String getString(Scanner sc) {
		String value = "";
		if(sc.hasNext())
			value = sc.next();
		return value;
	}
	
	/**
	 * Gets a string from the user which is either yes or no
	 * @param sc Scanner
	 * @return yes if the user entered yes, false if the user entered no
	 */
	public static boolean getYesNo(Scanner sc) {
		String value;
		do {
			value = Input.getString(sc);
		} while (!value.equals("yes") && !value.equals("no"));
		return value.equals("yes");
	}
	
	/**
	 * Gets an integer from the user within the specified range
	 * @param sc Scanner
	 * @param min value to be accepted
	 * @param max value to be accepted
	 * @return user input
	 */
	public static int getInt(Scanner sc, int min, int max, String question) {
		int value;
		do {
			System.out.print(question);
			value = getInt(sc, question);
		} while (value < min || value > max);
		return value;
	}
	
	/**
	 * Gets an integer from the user
	 * @param sc Scanner
	 * @return user input
	 */
	public static int getInt(Scanner sc, String question) {
	    while (!sc.hasNextInt()) {
	        System.out.print("*** That's not a number! ***\n" + question);
	        sc.next();
	    }
		return sc.nextInt();
	}
}
