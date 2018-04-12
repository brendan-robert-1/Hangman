/**
 *
 */
package com.brobert;

/**
 * @author brobert
 *
 */
public class HangmanGame {

	private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	int maxGuesses = 8;



	public char[] getAlphabet() {
		return alphabet;
	}

	private State state;



	public void start(StartingWordGenerator wordGenerator) {
		state = new State(wordGenerator.generateStartingWord(alphabet));
	}



	public boolean won() {
		return true;
	}



	public boolean lost() {
		return false;
	}



	public boolean isOver() {

		if (state.getMisses() >= maxGuesses) {

			System.out.println("\nGame Over!");
			state.printWord();
			System.out.println("Guessed characters [" + state.getGuessedChars().toString() + "]");
			System.out.println("Final board [" + state.toString() + "]");
			return true;
		}

		if (state.wordFullyGuessed()) {
			for (Character c : state.getGuessedChars()) {
				System.out.println(c);
			}
			System.out.println("Winner!");
			state.printWord();
			System.out.println("Guessed characters [" + state.getGuessedChars().toString() + "]");
			System.out.println("Misses [" + state.getMisses() + "]");
			System.out.println("Final board [" + state.toString() + "]");
			return true;
		}

		return false;
	}



	public State getState() {
		return state;
	}

}
