/**
 *
 */
package com.brobert;

/**
 * @author brobert
 *
 */
public class GameRunner {

	public static void main(String[] args) {
		HangmanGame game = new HangmanGame();
		StartingWordGenerator wordGenerator;

		if (args.length == 1) {
			wordGenerator = new CommandLineWordGenerator(args);
		} else if (args.length == 0) {
			wordGenerator = new FileWordGenerator();
		} else {
			throw new IllegalArgumentException("Must either pass in 1 word or none for a random word.");
		}

		game.start(wordGenerator);
		game.getState().printWord();

		Guesser guesser = new ReductionGuesser(game.getState());

		while (!game.isOver()) {
			char guessedChar = guesser.guessChar(game.getState());
			boolean charHit = game.getState().guessChar(guessedChar);
			System.out.print("\nTurn " + game.getState().getTurns() + " guessed [" + guessedChar + "]");
			if (charHit) {
				System.out.print(" Hit!");
			} else {
				System.out.print(" Miss! Total Misses[" + game.getState().getMisses() + "]");
			}
			System.out.println(" " + game.getState().toString());
		}

	}
}
