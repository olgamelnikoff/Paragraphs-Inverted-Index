/*
 * 1) Emilia Segura, 40138440, e_segur@encs.concordia.ca (Leader) 
 * Nour Yaghmour, 27006365, n_yagh@encs.concordia.ca  
 * Olga Melnikova, 40113342, o_melnik@encs.concordia.ca 
 */

import java.util.ArrayList;
//Inverted index to store each keyword along with the paragraph numbers associated to it
//This class also normalizes keywords are removes noise words before they are inserted into index
public class InvertedIndex {

	ArrayList<IndexEntry> index;
	//constructor
	public InvertedIndex () {
		index = new ArrayList<IndexEntry>();
	}
	//Constructor to build the inverted index from document words
	public InvertedIndex(ArrayList<Paragraph> paragraphArray){
		
		index = new ArrayList<IndexEntry>();
		
	
		for( Paragraph paragraphObject: paragraphArray){			
			String alltext = paragraphObject.getParagraphContent(); //all the text of current paragraph in single string
			String [] alltextArray =  wordarray(alltext);
			

			String noise = noisewords();

			for (int j=0; j<alltextArray.length; j++) { //loop through the array that contains all the words in a paragraph
				String key = normalize(alltextArray[j]); 
				if (!noise.contains(key)) { //will only execute if words at index i is not a noise word
					if (!alreadyexists(key, index)) { //will only execute if word is not already in arraylist
						ArrayList<Integer> allparas = new ArrayList<Integer>();
						for (Paragraph p : paragraphArray) { //adding all paragraphs the word is in
							if (inParagraph(key, normalize(p.getParagraphContent()))) { 
								allparas.add(p.getParagraphNumber()); 
							}
						}
						IndexEntry newEntry = new IndexEntry(key, allparas); //creates a new entry when the word is not a noise word and not already in arraylist
						index.add(newEntry);
					}
				}
			}
		}
	}
	//getter to access the ArrayList
	public ArrayList<IndexEntry> getIndexElements(){
		return index;
	}

	//method to check if a word is in a paragraph
	private static boolean inParagraph(String word, String paragraph) {
		return paragraph.contains(word);
	}
	
	//method to check if word is already in ArrayList
	private  boolean alreadyexists(String word, ArrayList<IndexEntry> index) {
		for (int i=0; i<index.size(); i++) {
			String key =index.get(i).getKeyword(); //need to access the key value of an IndexEntry object
			if (word.equalsIgnoreCase(key)) {
				return true;
			}
			
		}
		return false;
	}

	//method to create an array list of noise words. So far I just copied all the words into a string... could read from a file instead
	private static String noisewords() {
		String stopwords = ":\n" + 
				",\n" + 
				".\n" + 
				"a\n" + 
				"an\n" + 
				"and\n" + 
				"are\n" + 
				"as\n" + 
				"at\n" + 
				"be\n" + 
				"but\n" + 
				"by\n" + 
				"for\n" + 
				"if\n" + 
				"in\n" + 
				"into\n" + 
				"is\n" + 
				"it\n" + 
				"no\n" + 
				"not\n" + 
				"of\n" + 
				"on\n" + 
				"or\n" + 
				"such\n" + 
				"that\n" + 
				"the\n" + 
				"their\n" + 
				"then\n" + 
				"there\n" + 
				"these\n" + 
				"they\n" + 
				"this\n" + 
				"however\n" + 
				"since\n" +
				"so\n"+
				"to\n" + 
				"was\n" + 
				"will\n" + 
				"with\n" + 
				"can";
		return stopwords;		
	}
	//method to normalize words
	private static String normalize(String word) {
		return word.replaceAll("[^A-Za-z]", "").toLowerCase();
	}
//method to split paragraph into String []
	private static String [] wordarray(String alltext) {
		String[] args = alltext.split("\\s");//saves each word into array
		return args;
	}
//main method here only to test functionality of class
	/**
	public static void main(String[] args) {
		//creating 3 simple paragraphs
		Paragraph first = new Paragraph("How do I test this in any other way?.", 1);
		Paragraph second = new Paragraph("Word paragraph again to see if duplicate word will be removed", 2);
		Paragraph third = new Paragraph("EVERY WORD IS ALL CAPS 1 2 3 4 5 6 7 8 9 0 ", 3);
		//creating arraylist of these paragraphs
		ArrayList<Paragraph> pArray = new ArrayList<Paragraph>();
		pArray.add(first);
		pArray.add(second);
		pArray.add(third);
		//printing out contents of arraylist to test
		for (int i=0; i< pArray.size();i++) {
			System.out.println(pArray.get(i).getParagraphContent());	
		}
	
		InvertedIndex index = new InvertedIndex(pArray);
		ArrayList<IndexEntry> myIndex = index.getIndexElements();
		//printing out all the keys contained in index
		for (int i =0; i< myIndex.size(); i++) {
			myIndex.get(i).printEntry();
		}	
	}
	*/
	
}
