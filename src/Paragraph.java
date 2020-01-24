/*
 * 1) Emilia Segura, 40138440, e_segur@encs.concordia.ca (Leader) 
 * Nour Yaghmour, 27006365, n_yagh@encs.concordia.ca  
 * Olga Melnikova, 40113342, o_melnik@encs.concordia.ca 
 */


//This is an object for a paragraph that has the paragraph number
//as an int and the paragraph content as a String
public class Paragraph {
	private int paragraphNumber;
	private String paragraphContent;
	
	public Paragraph () {
		paragraphContent = " ";
		paragraphNumber = 0;
	}
	
	public Paragraph (String paragraphContent, int paragraphNumber) {
		this.paragraphContent = paragraphContent;
		this.paragraphNumber = paragraphNumber;
	}
	
	public int getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(int paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public String getParagraphContent() {
		return paragraphContent;
	}
	public void setParagraphContent(String paragraphContent) {
		this.paragraphContent = paragraphContent;
	}
	//just switched order of printing. Paragraph # before content to make it easier to see
	public void writeOutput () {
		System.out.println(this.getParagraphNumber() + " " +this.getParagraphContent());
		
	}
	
}
