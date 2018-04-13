/**
 *
 */
package com.brobert;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
			inDictionary();
			return commandLineWord;
		}
	}



	/**
	 *
	 */
	private void inDictionary() {
		List<String> allWord = getAllWords();
		if (allWord.contains(commandLineWord) == false) {
			System.out.println(commandLineWord + " is not in the dictionary. Not valid.");
			System.exit(1);
		}
	}



	/**
	 * @return
	 */
	private List<String> getAllWords() {
		List<String> allWords = new ArrayList<>();

		try (InputStream is = getClass().getResourceAsStream("/words.txt");
				BufferedReader read = new BufferedReader(new InputStreamReader(is));) {
			String line = null;
			while ((line = read.readLine()) != null) {
				allWords.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allWords;
	}

}
