/**
 *
 */
package com.brobert;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.collect.Lists;

/**
 * @author brobert
 *
 */
public class FileWordGenerator implements StartingWordGenerator {

	List<Integer> validWordLengths = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28,
			29, 31, 45);

	private String dictionaryFile = "/words.txt";



	/* (non-Javadoc)
	 * @see com.brobert.StartingWordGenerator#generateStartingWord(char[])
	 */
	@Override
	public String generateStartingWord(char[] alphabet) {
		long lineCount = getLineCount();
		String word = getRandomWord(lineCount);
		while (isValid(word, alphabet) == false) {
			word = getRandomWord(lineCount);
		}

		return word;
	}



	/**
	 * @return
	 */
	private long getLineCount() {
		long lineCount = 0;
		try {
			InputStream is = getClass().getResourceAsStream(dictionaryFile);
			BufferedReader read = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = read.readLine()) != null) {
				lineCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineCount;
	}



	private String getRandomWord(long lineCount) {
		String word = null;
		int randomIndex = new Random().nextInt((int) lineCount - 1), currentIdx = 0;
		try (
				InputStream is = getClass().getResourceAsStream(dictionaryFile);
				BufferedReader read = new BufferedReader(new InputStreamReader(is));) {
			while (currentIdx < randomIndex) {
				currentIdx++;
				word = read.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return word;
	}



	/**
	 * @param line
	 * @return
	 */
	private boolean isValid(String line, char[] alphabet) {
		Set<Character> alph = new HashSet<>();
		for (char c : alphabet) {
			alph.add(c);
		}
		for (char c : line.toCharArray()) {
			if (alph.contains(c) == false) {
				return false;
			}
		}
		return true;
	}



	public void createSizedFiles(char[] alphabet) {
		System.out.println("generating words from file.");
		Set<Character> alph = new HashSet<>();
		for (char c : alphabet) {
			alph.add(c);
		}
		File file = new File("words.txt");
		try {
			LineIterator it = FileUtils.lineIterator(file, "UTF-8");
			while (it.hasNext()) {
				String line = it.nextLine();
				line = line.toLowerCase();
				if (line == null || line.length() < 1) {
					continue;
				}
				boolean valid = true;
				for (char c : line.toCharArray()) {
					if (c != '\n' && (alph.contains(c) == false)) {
						valid = false;
						break;
					}
				}
				if (!valid) {
					continue;
				}
				String fileNameByLength = "bin/words." + line.length() + ".txt";
				File countFile = new File(fileNameByLength);
				FileUtils.writeStringToFile(countFile, line + "\n", "UTF-8", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
