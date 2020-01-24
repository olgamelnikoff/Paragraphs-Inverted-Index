/*
 * 1) Emilia Segura, 40138440, e_segur@encs.concordia.ca (Leader) 
 * Nour Yaghmour, 27006365, n_yagh@encs.concordia.ca  
 * Olga Melnikova, 40113342, o_melnik@encs.concordia.ca 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.InputMismatchException;

public class Main {

	//Creating ArrayList of paragraphs
	private static ArrayList<Paragraph> paragraphArray;

	static {
		try {
			paragraphArray = new ParagraphCreator("Privacy-in-theAge-of-Information.txt").getParagraphArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Creating an InvertedIndex (an index of keywords followed by the numbers of paragraphs where they are encountered)
	private static ArrayList<IndexEntry> index = new InvertedIndex(paragraphArray).getIndexElements();

	// A method to get the paragraph number of any keyword
	public static ArrayList <Integer> getParagraphNumber(String key) {
		ArrayList <Integer> paragNum = new ArrayList<>();
		for (int i = 0; i < index.size(); i++) {
			if (index.get(i).getKeyword().equals(key)) {
				paragNum =  index.get(i).getThisKeywordParagraphs();
			}
		}
		return paragNum;
	}

	//A boolean showing the output type (paragraph numbers only or whole paragraphs)
	public static boolean outputNumbersOnly = false;

	public static void main(String[] args) throws IOException {

		//Printing the arraylist of paragraphs
		//Demonstrates that text file is separated by paragraph
		System.out.println("Whole text document output along with corresponding paragraph number: ");
		System.out.println();
		for (int i = 0 ; i < paragraphArray.size(); i++) {
			paragraphArray.get(i).writeOutput();
		}
		//Printing out all the keywords + paragraphs where they are encountered contained in index
		//demonstrates that each keyword is associated to the correct paragraph numbers
		System.out.println();
		System.out.println("All keywords with their corresponding paragraph number: ");
		System.out.println();
		for (int i =0; i< index.size(); i++) {
			index.get(i).printEntry();
		}

		//Creating the trie 
		Trie trie = new Trie();
		System.out.println("A new trie was created successfully.");

		// Inserting all the elements into the trie
		for (int i= 0 ; i<index.size() ; i++){
			trie.insert(index.get(i));
		}
		System.out.println("All keywords have been inserted to the trie.");

		//Printing the search options for the user
		printMenu();
		Scanner scanner = new Scanner(System.in);
		boolean answer = false;

		String word1;
		String word2;
		String word3;

		while (!answer) {
			System.out.println("Enter 6 to view Main Menu choices");

			try {

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					//Searching for one word
					printOutputChoices();
					System.out.println("Please Enter a keyword to search the trie: ");
					word1 = scanner.next();
					search1(word1);
					break;
				case 2:
					//Searching for keyword1 AND keyword2 combination
					printOutputChoices();
					System.out.println("Please Enter two keywords to search the trie.");
					System.out.println("First word:");
					word1 = scanner.next();
					System.out.println("Second word:");
					word2 = scanner.next();
					search2(word1, word2);
					break;
				case 3:
					//Searching for keyword1 OR keyword2 combination
					printOutputChoices();
					System.out.println("Please Enter two keywords to search the trie.");
					System.out.println("First word:");
					word1 = scanner.next();
					System.out.println("Second word:");
					word2 = scanner.next();
					search3(word1, word2);
					break;
				case 4:
					//Searching for keyword1 AND (keyword2 OR keyword3) combination
					printOutputChoices();
					System.out.println("Please Enter three keywords to search the trie.");
					System.out.println("First word:");
					word1 = scanner.next();
					System.out.println("Second word:");
					word2 = scanner.next();
					System.out.println("Third word:");
					word3 = scanner.next();
					search4(word1, word2, word3);
					break;
				case 5:
					//Searching for keyword1 OR (keyword2 AND keyword3) combination
					printOutputChoices();
					System.out.println("Please Enter three keywords to search the trie.");
					System.out.println("First word:");
					word1 = scanner.next();
					System.out.println("Second word:");
					word2 = scanner.next();
					System.out.println("Third word:");
					word3 = scanner.next();
					search5(word1, word2, word3);
					break;
				case 6:
					//Printing the Main Menu
					printMenu();
					break;
				case 7:
					//Exiting the program
					System.out.println("Exit the program");
					answer = true;
					scanner.close();
					break;
				default:
					System.out.println("Please enter a valid number from 1 to 7");
					break;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Error. Your input should be an integer. Exiting the program.");
				System.exit(0);
			}
		}


	}

	//A method to print the Main Menu
	public static void printMenu() {
		System.out.println("Please select the search type from the following options: ");
		System.out.print("1.keyword1\n" +
				"2.keyword1 AND keyword2\n" +
				"3.keyword1 OR keyword2\n" +
				"4.keyword1 AND (keyword2 OR keyword3)\n" +
				"5.keyword1 OR (keyword2 AND keyword3)\n" +
				"6.Display the Main Menu\n" + 
				"7.Exit the program\n");
	}

	//A method that offers two output options: one is printing only the paragraph numbers, 
	//and the other one is printing the whole paragraphs
	public static void printOutputChoices () {
		System.out.println("Type \"a\" to display paragraph numbers. "
				+ "Type \"b\" to display whole paragraph text.");
		Scanner scanner = new Scanner(System.in);
		try {
			String outputChoice = scanner.nextLine();
			outputChoice = outputChoice.toLowerCase();

			switch (outputChoice) {
			case "a":
				outputNumbersOnly = true;
				break;
			case "b":
				outputNumbersOnly = false;
				break;
			default:
				System.out.println("Your input should be letters \"a\" or \"b\". Exiting the program.");
				System.exit(0);
				scanner.close();
			}
		}

		catch (InputMismatchException e) {
			System.out.println("Error. Your input should be the letter \"a\" or \"b\". Exiting the program.");
			System.exit(0);
		}
	}
	// A method to output the content of a whole paragraph
	public static void wholeParagraphOutput(ArrayList<Integer> paraNum){

		for(int i=0 ; i<paraNum.size() ; i++){
			paragraphArray.get(paraNum.get(i)-1).writeOutput();
		}

	}

	//A method to find an intersection ("AND" operator) of two ArrayLists of paragraphs
	public static ArrayList<Integer> intersection (ArrayList<Integer> firstEntryParagraphs, ArrayList<Integer> secondEntryParagraphs) {
		ArrayList<Integer> sharedParagraphs = new ArrayList<Integer> ();
		for (Integer p : firstEntryParagraphs) {
			if (secondEntryParagraphs.contains(p)) {
				sharedParagraphs.add(p);
			}
		}
		return sharedParagraphs;
	}

	//A method to find a union ("OR" operator) of two ArrayLists of paragraphs
	public static ArrayList<Integer> union (ArrayList<Integer> firstEntryParagraphs, ArrayList<Integer> secondEntryParagraphs) {
		Set<Integer> set = new HashSet<Integer> ();
		set.addAll(firstEntryParagraphs);
		set.addAll(secondEntryParagraphs);
		ArrayList<Integer> allParagraphs = new ArrayList<Integer> (set);
		return allParagraphs;

	}

	//Searching for one word
	public static void search1 (String keyword) {
		keyword = keyword.toLowerCase();
		if(Trie.search(keyword)){
			ArrayList<Integer> thisEntryParagraphs = getParagraphNumber(keyword);
			if (outputNumbersOnly) {
				System.out.println("the keyword "+keyword+ " is found in the trie under paragraph numbers: "+ thisEntryParagraphs);
			}

			else {
				System.out.println("the keyword "+keyword+ 
						" is found in the following paragraphs (whole paragraphs are output): ");
				wholeParagraphOutput(thisEntryParagraphs);
			}

		}

		else {
			System.out.println("The keyword "+ keyword+ " does not exist in the trie");
		}
	}

	//Searching for keyword1 AND keyword2 combination
	public static void search2 (String keyword1, String keyword2) {
		keyword1 = keyword1.toLowerCase();
		keyword2 = keyword2.toLowerCase();

		if (Trie.bothKeywordsFoundInText(keyword1, keyword2)) {
			ArrayList<Integer> firstEntryParagraphs = getParagraphNumber(keyword1);
			ArrayList<Integer> secondEntryParagraphs = getParagraphNumber(keyword2);
			ArrayList<Integer> intersection = intersection (firstEntryParagraphs, secondEntryParagraphs);

			if (intersection.isEmpty()) {
				System.out.println(keyword1+" AND "+keyword2+" combination not found in the same paragraph.");
			}

			else {
				if (outputNumbersOnly) {
					System.out.print(keyword1+" AND "+keyword2+" combination found in the following paragraphs: ");
					for (int i = 0; i < intersection.size(); ++i) {
						System.out.print(intersection.get(i) + " ");
					}
					System.out.println();
				}

				else {
					System.out.println(keyword1+" AND "+keyword2+" combination found in the following paragraphs: "
							+ "(the whole paragraphs are output): ");
					wholeParagraphOutput(intersection);
				}

			}
		}

		else {
			System.out.println(keyword1 + " AND " + keyword2 + " combination not found in the same paragraph.");
		}
	}

	//Searching for keyword1 OR keyword2 combination
	public static void search3 (String keyword1, String keyword2) {
		keyword1 = keyword1.toLowerCase();
		keyword2 = keyword2.toLowerCase();

		if (Trie.oneOfTwoKeywordsFoundInText(keyword1, keyword2)) {
			ArrayList<Integer> firstEntryParagraphs = getParagraphNumber(keyword1);
			ArrayList<Integer> secondEntryParagraphs = getParagraphNumber(keyword2);
			ArrayList<Integer> union = union (firstEntryParagraphs, secondEntryParagraphs);

			if (outputNumbersOnly) {
				System.out.print(keyword1+" OR "+keyword2+" combination found in the following paragraphs: ");
				for (int i = 0; i < union.size(); ++i) {
					System.out.print(union.get(i) + " ");
				}
				System.out.println();
			}

			else {
				System.out.println(keyword1+" OR "+keyword2+" combination found in the following paragraphs: "
						+ "(the whole paragraphs are output): ");
				wholeParagraphOutput(union);
			}
		}
		else {
			System.out.println(keyword1+" OR "+keyword2+" not found in any paragraph.");
		}
	}

	//Searching for keyword1 AND (keyword2 OR keyword3) combination
	public static void search4 (String keyword1, String keyword2, String keyword3) {
		keyword1 = keyword1.toLowerCase();
		keyword2 = keyword2.toLowerCase();
		keyword3 = keyword3.toLowerCase();

		if ((Trie.oneOfTwoKeywordsFoundInText(keyword2, keyword3)) && (Trie.search(keyword1))) {
			ArrayList<Integer> firstEntryParagraphs = getParagraphNumber(keyword1);
			ArrayList<Integer> secondEntryParagraphs = getParagraphNumber(keyword2);
			ArrayList<Integer> thirdEntryParagraphs = getParagraphNumber(keyword3);
			ArrayList<Integer> union = union (secondEntryParagraphs, thirdEntryParagraphs);
			ArrayList<Integer> intersection = intersection (firstEntryParagraphs, union);

			if (intersection.isEmpty()) {
				System.out.println(keyword1+" AND ("+keyword2+" OR "+keyword3+") combination not found in the same paragraph.");
			}

			else {
				if (outputNumbersOnly) {
					System.out.print(keyword1+" AND ("+keyword2+" OR "+keyword3+") combination found in the following paragraphs: ");

					for (int i = 0; i < intersection.size(); ++i) {
						System.out.print(intersection.get(i) + " ");
					}
					System.out.println();
				}

				else {
					System.out.println(keyword1+" AND ("+keyword2+" OR "+keyword3+") combination found in the following paragraphs: "
							+ "(the whole paragraphs are output): ");
					wholeParagraphOutput(intersection);
				}
			}
		}

		else {
			System.out.println(keyword1+" AND ("+keyword2+" OR "+keyword3+") not found in any paragraph.");
		}
	}

	//Searching for keyword1 OR (keyword2 AND keyword3) combination
	public static void search5 (String keyword1, String keyword2, String keyword3) {
		keyword1 = keyword1.toLowerCase();
		keyword2 = keyword2.toLowerCase();
		keyword3 = keyword3.toLowerCase();

		if ((Trie.search(keyword1)) || (Trie.bothKeywordsFoundInText(keyword2, keyword3))) {
			ArrayList<Integer> firstEntryParagraphs = getParagraphNumber(keyword1);
			ArrayList<Integer> secondEntryParagraphs = getParagraphNumber(keyword2);
			ArrayList<Integer> thirdEntryParagraphs = getParagraphNumber(keyword3);
			ArrayList<Integer> intersection = intersection (secondEntryParagraphs, thirdEntryParagraphs);
			ArrayList<Integer> union = union(firstEntryParagraphs, intersection);

			if (outputNumbersOnly) {
				System.out.print(keyword1+" OR ("+keyword2+" AND "+keyword3+") found in the following paragraphs: ");
				for (int i = 0; i < union.size(); ++i) {
					System.out.print(union.get(i) + " ");
				}
				System.out.println();
			}

			else {
				System.out.println(keyword1+" OR ("+keyword2+" AND "+keyword3+") found in the following paragraphs: "
						+ "(the whole paragraphs are output): ");
				wholeParagraphOutput(union);
			}
		}

		else {
			System.out.println(keyword1+" OR ("+keyword2+" AND "+keyword3+") not found in any paragraph.");
		}
	}
}