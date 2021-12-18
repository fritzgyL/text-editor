package fr.istic.aco.editorLI.app.utils;

/**
 * Class containing useful functions
 * 
 * @author Fritzgy Lubin
 *
 */
public class Utilities {
	/**
	 * @param character the parameter we want to check if it's valid
	 * @return if character is a valid alphanumeric character or a punctuation
	 */
	public static boolean isAValidChar(char character) {
		String patternAlphanumeric = "^[\\p{L}0-9]*$";
		String patternSymbol = "[.;!?\\-/$\"'()@#&|\\{}=*-+=%§¤£¨µ°:]";
		String letter = Character.toString(character);
		return letter.matches(patternAlphanumeric) || letter.matches(patternSymbol);
	}
}
