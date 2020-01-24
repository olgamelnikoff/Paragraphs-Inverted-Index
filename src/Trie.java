/*
 * 1) Emilia Segura, 40138440, e_segur@encs.concordia.ca (Leader) 
 * Nour Yaghmour, 27006365, n_yagh@encs.concordia.ca  
 * Olga Melnikova, 40113342, o_melnik@encs.concordia.ca 
 */

import java.util.HashMap;
import java.util.Map;

public class Trie {

	private class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEndOfWord;

		public TrieNode() {

			children = new HashMap<>();
			isEndOfWord = false;

		}
	}

	private static TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(IndexEntry indexEntry) {
		TrieNode current = root;
		for (int i = 0; i < indexEntry.getKeyword().length(); i++) {
			char character = indexEntry.keyword.charAt(i);
			TrieNode node = current.children.get(character);
			if (node == null) {
				node = new TrieNode();
				current.children.put(character, node);
			}
			current = node;
		}
		current.isEndOfWord = true;

	}

	//Search type: just one word
	public static boolean search (String key) {
		key = key.toLowerCase();

		TrieNode current = root;
		for (int i = 0; i < key.length(); i++) {
			char character = key.charAt(i);
			TrieNode node = current.children.get(character);

			if (node == null) {
				return false;
			}
			current = node;

		}
		return current.isEndOfWord;

	}

	//Search type: keyword1 AND keyword2
	public static boolean bothKeywordsFoundInText (String key1, String key2) {
		if ((search(key1)) && (search(key2))) {
			return true;
		}

		else {
			return false;

		}
	}

	//Search type: keyword1 OR keyword2
	public static boolean oneOfTwoKeywordsFoundInText (String key1, String key2) {
		if ((search(key1)) || (search(key2))) {
			return true;
		}   		
		else {
			return false; 		
		}
	}
}