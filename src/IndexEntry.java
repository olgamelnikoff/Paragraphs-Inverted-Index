/*
 * 1) Emilia Segura, 40138440, e_segur@encs.concordia.ca (Leader) 
 * Nour Yaghmour, 27006365, n_yagh@encs.concordia.ca  
 * Olga Melnikova, 40113342, o_melnik@encs.concordia.ca 
 */

import java.util.ArrayList;

//This is an object for one inverted index entry. It has one keyword 
//as a String and an array of integers that represent all the paragraphs
//where it is encountered.
public class IndexEntry {
	String keyword;
	ArrayList<Integer> thisKeywordParagraphs = new ArrayList<Integer>();
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<Integer> getThisKeywordParagraphs() {
		return thisKeywordParagraphs;
	}

	public void setThisKeywordParagraphs(ArrayList<Integer> thisKeywordParagraphs) {
		this.thisKeywordParagraphs = thisKeywordParagraphs;
	}

	public IndexEntry () {
		
	}
	
	public IndexEntry (String newKeyword, ArrayList<Integer> keywordParagraphs) {
		keyword = newKeyword;
		thisKeywordParagraphs = keywordParagraphs;
	}
	//method to print content of IndexEntry
	public void printEntry() {
		System.out.print("key: "+ this.keyword+ " paragraph: ");
		for (int i = 0; i < this.thisKeywordParagraphs.size(); ++i) {
			 System.out.print(thisKeywordParagraphs.get(i) + " "); 
		}
		System.out.println();
	}
}
