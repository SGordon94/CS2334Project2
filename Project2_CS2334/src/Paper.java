import java.util.*;


public class Paper implements Comparator<Paper>, Comparable<Paper>{
	private String typeOfPaper = null;
	private String authors = null;
	private String titleOfPaper = null;
	private String titleOfSerial = null;
	private String numbers = null;
	private String publicationDate = null;
	private String digitalObjectIdentifier = null;
	
	public Paper(String typeOfPaper, String authors, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate, String digitalObjectIdentifier){
		this.typeOfPaper = typeOfPaper;
		this.authors = authors;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
		this.digitalObjectIdentifier = digitalObjectIdentifier;
	}
	
	public Paper(String typeOfPaper, String authors, String titleOfPaper,
			String titleOfSerial, String numbers, String publicationDate){
		this.typeOfPaper = typeOfPaper;
		this.authors = authors;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
	}
	
	public String getPaper(){
		String paper = "";
		paper += this.typeOfPaper + "\n";
		paper += this.authors + "\n";
		paper += this.titleOfPaper + "\n";
		paper += this.titleOfSerial + "\n";
		paper += this.numbers + "\n";
		if (digitalObjectIdentifier != null){
			paper += this.publicationDate + "\n";
			paper += this.digitalObjectIdentifier;
		}
		else{
			paper += this.publicationDate;
		}
		return paper;
	}
	
	public String getTypeOfPaper(){
		return this.typeOfPaper;
	}
	
	public String getAuthors(){
		return this.authors;
	}
	
	public String getTitleOfPaper(){
		return this.titleOfPaper;
	}
	
	public String getTitleOfSerial(){
		return this.titleOfSerial;
	}
	
	public String getNumbers(){
		return this.numbers;
	}
	
	public String getPublicationDate(){
		return this.publicationDate;
	}
	
	public String getDigitalObjectIdentifier(){
		return this.digitalObjectIdentifier;
	}
	
	
	@Override
	public int compare(Paper p1, Paper p2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Paper p) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
