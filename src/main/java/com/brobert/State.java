/**
 *
 */
package com.brobert;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author brobert
 *
 */
public class State {

	private String word;

	private Set<Character> guessedChars = new LinkedHashSet<>();

	private int misses = 0, turnNumber = 0;



	public String getCurrentBoard() {
		StringBuilder sb = new StringBuilder();
		for (char c : word.toCharArray()) {
			if (guessedChars.contains(c)) {
				sb.append(c);
			} else {
				sb.append("_");
			}
		}
		return sb.toString();
	}



	public State(String word) {
		this.word = word;
	}



	public boolean wordFullyGuessed() {
		for (char c : word.toCharArray()) {
			if (guessedChars.contains(c) == false) {
				return false;
			}
		}
		return true;
	}



	public boolean guessChar(char c) {
		turnNumber++;
		guessedChars.add(c);
		boolean hit = word.indexOf(c) >= 0;
		if (!hit) {
			misses++;
		}
		return hit;
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (char c : word.toCharArray()) {
			if (guessedChars.contains(c)) {
				sb.append(c + " ");
			} else {
				sb.append("_ ");
			}
		}
		return sb.toString();
	}



	public void printWord() {
		System.out.println("Word [" + word + "]");
	}



	public int wordLength() {
		return word.length();
	}



	public Set<Character> getGuessedChars() {
		return new LinkedHashSet<>(guessedChars);
	}



	public int getMisses() {
		return misses;
	}



	public int getTurns() {
		return turnNumber;
	}

}
