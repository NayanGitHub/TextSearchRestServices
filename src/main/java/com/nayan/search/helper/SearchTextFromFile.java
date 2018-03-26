package com.nayan.search.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nayan.search.springboot.model.Text;

import java.util.Set;
import java.util.TreeSet;




public class SearchTextFromFile {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTextFromFile.class);
	public static String FILE_LOCATION="src/main/resources/ExampleFile.txt";

	public static List<Text> getListTextAndCount() {
		String textFileLocation = FILE_LOCATION;
		String readWords = "";
		ArrayList<String> allWordList = new ArrayList<String>();
		ArrayList<Text> textList = new ArrayList<Text>();
		//Map<String, Integer> wordsWithCounts = new HashMap<String, Integer>();
		//Text txt = new Text();

		String[] excludedSymbols = { " ", ",", ".", "/", ":", ";", "<", ">", "\n", "\r" };
		String readByteCharByChar = "";
		boolean testIfWord = false;

		try {
			InputStream inputStream = new FileInputStream(textFileLocation);
			byte byte1 = (byte) inputStream.read();
			while (byte1 != -1) {

				readByteCharByChar += String.valueOf((char) byte1);
				for (int i = 0; i < excludedSymbols.length; i++) {
					if (readByteCharByChar.equals(excludedSymbols[i])) {
						if (!(readWords.equals(""))) {
							allWordList.add(readWords);
						}
						readWords = "";
						testIfWord = true;
						break;
					}
				}
				if (!testIfWord) {
					readWords += (char) byte1;
				}
				readByteCharByChar = "";
				testIfWord = false;
				byte1 = (byte) inputStream.read();
				if (byte1 == -1 && !(readWords.equals(""))) {
					allWordList.add(readWords);
				}
			}
			inputStream.close();
			//logger.info("all Word List::\n\n"+allWordList);
			//System.out.println(allWordList);
			//logger.info("The number of words in the choosen text file are: " + allWordList.size());

			String word = "";
			for (int i = 0; i < allWordList.size(); i++) {
				word = allWordList.get(i);
				int count = 0;
				for (int j = 0; j < allWordList.size() - 1; j++) {
					if (allWordList.get(j).equalsIgnoreCase(word)) {
						count++;
					}
				}
				textList.add(new Text(word,count));
			}
				Set<Text> searchTextSet = new TreeSet<>((t1, t2) 
					   -> t1.getText().compareTo(t2.getText()));
					   searchTextSet.addAll(textList);
				List<Text> noDuplicatesTextList = new ArrayList<>(searchTextSet);
			return noDuplicatesTextList;

		} catch (IOException ioException) {
			LOGGER.error("Error Reading From File", ioException);
		}
		return textList;
	}
}
