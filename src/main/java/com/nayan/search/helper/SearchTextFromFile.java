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

import com.nayan.search.springboot.model.Text;

import java.util.Set;
import java.util.TreeSet;




public class SearchTextFromFile {
	//private static final Logger logger = Logger.getLogger(SearchTextFromFile.class);
	public static boolean ASC = true;
	public static boolean DESC = false;
	
	public static String FILE_LOCATION = "C:\\FileRepos\\ExampleFile.txt";

	public static void main(String[] args) {
		//countWords();
	}

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
				/*txt.setText(word);
				txt.setCount(count);*/
				textList.add(new Text(word,count));
			}
				Set<Text> searchTextSet = new TreeSet<>((t1, t2) 
					   -> t1.getText().compareTo(t2.getText()));
					   searchTextSet.addAll(textList);
				List<Text> noDuplicatesTextList = new ArrayList<>(searchTextSet);
			// printMap(wordsWithCounts);
			//System.out.println("After sorting descindeng order......");
			//Map<String, Integer> sortedMapDesc = sortByComparator(wordsWithCounts, DESC);
			//printMap(sortedMapDesc);
			return noDuplicatesTextList;

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return textList;
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());
				}
			}
		});
		// Maintaining insertion order with the help of LinkedList
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static void printMap(Map<String, Integer> map) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
	}
}
