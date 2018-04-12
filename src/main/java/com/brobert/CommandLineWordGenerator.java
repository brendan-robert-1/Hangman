/**
 *
 */
package com.brobert;

/**
 * @author brobert
 *
 */
public class CommandLineWordGenerator implements StartingWordGenerator {

	private String commandLineWord;



	public CommandLineWordGenerator(String[] args) {
		commandLineWord = args[0].toLowerCase();
	}



	/* (non-Javadoc)
	 * @see com.brobert.StartingWordGenerator#generateStartingWord(char[])
	 */
	@Override
	public String generateStartingWord(char[] alphabet) {
		boolean validWord = true;
		for (char c : commandLineWord.toCharArray()) {
			boolean validChar = false;
			for (char cc : alphabet) {
				if (cc == c) {
					validChar = true;
					break;
				}
			}
			if (!validChar) {
				validWord = false;
				break;
			}
		}
		if (!validWord) {
			System.out.println(commandLineWord + " is not a valid word. Word may contain english letters only.");
			System.exit(1);
			return null;
		} else {
			return commandLineWord;
		}
	}

}
