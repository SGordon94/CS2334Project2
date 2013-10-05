import java.util.*;


public class Paper implements Comparator<Paper>, Comparable<Paper>{
	private int volume = -1;
	private int issue = -1;
	private int startingPage = -1;
	private int endingPage = -1;
	private String typeOfPaper = null;
	private String authors = null;
	private String titleOfPaper = null;
	private String titleOfSerial = null;
	private String publicationDate = null;
	private String digitalObjectIdentifier = null;
	
	
	public Paper(String typeOfPaper, String authors, String titleOfPaper, String titleOfSerial, String pageNumber, String publicationDate, String digitalObjectIdentifier){
		this.typeOfPaper = typeOfPaper;
		this.authors = authors;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.publicationDate = publicationDate;
		this.digitalObjectIdentifier = digitalObjectIdentifier;
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
