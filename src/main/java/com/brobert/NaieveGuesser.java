/**
 *
 */
package com.brobert;

/**
 * @author brobert
 *
 */
public class NaieveGuesser implements Guesser {

	char[] mostToLeast = "etaoinshrdlcumwfgypbvkjxqz".toCharArray();



	@Override
	public char guessChar(State state) {
		for (char c : mostToLeast) {
			if (state.getGuessedChars().contains(c) == false) {
				return c;
			}
		}
		return '.';
	}
}
