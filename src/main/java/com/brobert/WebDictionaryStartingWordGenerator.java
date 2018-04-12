/**
 *
 */
package com.brobert;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * @author brobert
 *
 */
public class WebDictionaryStartingWordGenerator implements StartingWordGenerator {

	@Override
	public String generateStartingWord(char[] alphabet) {
		String startingWord = getStartingWord();
		while (!isValidWord(startingWord, alphabet)) {
			System.out.println(startingWord + " is invalid from the given alphabet. Getting new word.");
			startingWord = getStartingWord();
		}
		return startingWord;
	}



	/**
	 * @param startingWord
	 * @param alphabet
	 * @return
	 */
	private boolean isValidWord(String startingWord, char[] alphabet) {

		for (char c : startingWord.toCharArray()) {
			boolean letterInAlphabet = false;
			for (char cc : alphabet) {
				if (c == cc) {
					letterInAlphabet = true;
					break;
				}
			}
			if (!letterInAlphabet) {
				return false;
			}
		}
		return true;
	}



	/**
	 * @return
	 */
	private String getStartingWord() {
		String urlStr = "http://api.wordnik.com:80/v4/words.json/randomWords?hasDictionaryDef=true&minCor"
				+ "pusCount=0&minLength=5&maxLength=15&limit=1&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
		String json = null;
		try (CloseableHttpClient client = HttpClients.createDefault();) {
			HttpGet get = new HttpGet(urlStr);
			CloseableHttpResponse resp = client.execute(get);
			HttpEntity entity = resp.getEntity();
			json = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getWordFromJson(json);
	}



	/**
	 * @param json
	 * @return
	 */
	private String getWordFromJson(String json) {
		Gson gson = new Gson();
		RandomWord[] word = gson.fromJson(json, RandomWord[].class);
		return word[0].getWord();
	}

}
