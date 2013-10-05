import java.io.*;
import java.util.*;


public class Paper implements Serializable, Comparator, Comparable{

	private String typeOfPaper;
	private String authors;
	private String titleOfPaper;
	private String titleOfSerial;
	private String pageNumber;
	private String publicationDate;
	private String digitalObjectIdentifier;
	
	
	public Paper(String typeOfPaper, String authors, String titleOfPaper, String titleOfSerial, String pageNumber, String publicationDate, String digitalObjectIdentifier){
		this.typeOfPaper = typeOfPaper;
		this.authors = authors;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.pageNumber = pageNumber;
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
	
	public String getPageNumber(){
		return this.pageNumber;
	}
	
	public String getPublicationDate(){
		return this.publicationDate;
	}
	
	public String getDigitalObjectIdentifier(){
		return this.digitalObjectIdentifier;
	}
	
	
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
