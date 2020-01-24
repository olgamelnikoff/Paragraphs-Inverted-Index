/*
 * 1) Emilia Segura, 40138440, e_segur@encs.concordia.ca (Leader) 
 * Nour Yaghmour, 27006365, n_yagh@encs.concordia.ca  
 * Olga Melnikova, 40113342, o_melnik@encs.concordia.ca 
 */

import java.io.*;
import java.util.ArrayList;

public class ParagraphCreator {

	ArrayList<Paragraph> paragraphArray = new ArrayList<Paragraph>();

	
	//Class to create an arrayList of paragraphs
	public ParagraphCreator(String fileName) throws IOException {

		try {
			int parNumber = 1;
			BufferedReader reader= new BufferedReader(new FileReader(fileName)); //read from right file
			String currentline = reader.readLine();
			String paratext = ""; 

			while( currentline != null) {
				if(!currentline.isEmpty()) { //uses an empty line to define a paragraph break. This way it works on document that have a return at every line
					paratext += " "+currentline;
					
				}else if (currentline.isEmpty() && !paratext.isEmpty()) { //when an empty line is encountered, paragraph is complete and is added to ArrayList 
					paragraphArray.add(parNumber-1, new Paragraph (paratext, parNumber));
					parNumber++;

					paratext = ""; //reset the temporary variable that stores a paragraph
				}
				currentline = reader.readLine();
			}
			//for the last paragraph
			paragraphArray.add(parNumber-1, new Paragraph (paratext, parNumber));
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	//getter to access ArrayList of paragraphs
	public ArrayList<Paragraph> getParagraphArray() {
		return paragraphArray;
	}
}