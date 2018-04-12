/**
 *
 */
package com.brobert;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

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



	/* (non-Javadoc)
	 * @see com.brobert.StartingWordGenerator#generateStartingWord(char[])
	 */
	@Override
	public String generateStartingWord(char[] alphabet) {
		long lineCount = getLineCount();
		String line = getRandomWord(lineCount);
		while (isValid(line, alphabet) == false) {
			line = getRandomWord(lineCount);
		}

		return line;
	}



	/**
	 * @return
	 */
	private long getLineCount() {
		long lineCount = 0;
		try {
			lineCount = Files.lines(Paths.get("words.txt")).count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineCount;
	}



	private String getRandomWord(long lineCount) {
		String line = null;
		int randomIndex = new Random().nextInt((int) lineCount - 1);
		try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
			line = lines.skip(randomIndex - 1).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
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
