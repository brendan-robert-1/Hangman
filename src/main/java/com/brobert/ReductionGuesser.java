/**
 *
 */
package com.brobert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

/**
 * @author brobert
 *
 */
public class ReductionGuesser implements Guesser {

	private List<String> possibleWords = new ArrayList<>();



	public ReductionGuesser(State startingState) {
		possibleWords = getPossibleWordsByLength(startingState.wordLength());
	}



	/**
	 * @param wordLength
	 * @return
	 */
	private List<String> getPossibleWordsByLength(int wordLength) {
		List<String> possibleWords = new ArrayList<>();
		File file = new File("bin/words." + wordLength + ".txt");
		try {
			LineIterator it = FileUtils.lineIterator(file, "UTF-8");
			while (it.hasNext()) {
				possibleWords.add(it.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return possibleWords;
	}



	/* (non-Javadoc)
	 * @see com.brobert.Guesser#guessChar(com.brobert.State)
	 */
	@Override
	public char guessChar(State state) {
		reducePossibleWords(state);
		System.out.println("Remaining possible words: " + possibleWords.size());
		return guessCharByRemaining(state);

	}



	/**
	 * @param state
	 * @return
	 */
	private char guessCharByRemaining(State state) {
		Multiset<Character> multi = getPossibleCharacters(state);
		char c = '.';
		for (Character c1 : Multisets.copyHighestCountFirst(multi).elementSet()) {
			c = c1;
			break;
		}
		return c;
	}



	/**
	 * @param state
	 * @return
	 */
	private Multiset<Character> getPossibleCharacters(State state) {
		Multiset<Character> chars = HashMultiset.create();
		for (String possibleWord : possibleWords) {
			for (char c : possibleWord.toCharArray()) {
				if (state.getGuessedChars().contains(c) == false) {
					chars.add(c);
				}
			}
		}
		return chars;
	}



	private void reducePossibleWords(State state) {
		List<String> toRemove = new ArrayList<>();
		int count = 0;
		char[] currentBoard = state.getCurrentBoard().toCharArray();
		for (String s : possibleWords) {
			for (char c : s.toCharArray()) {
				if (currentBoard[count] != '_' && currentBoard[count] != c) {
					toRemove.add(s);
				}
				count++;
			}
			count = 0;
		}
		possibleWords.removeAll(toRemove);
	}
}
