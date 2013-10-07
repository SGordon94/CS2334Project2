import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Paper implements Comparator<Paper>, Comparable<Paper>{
	private String typeOfPaper = null;
	private String authors = null;
	private String titleOfPaper = null;
	private String titleOfSerial = null;
	private String numbers = null;
	private String publicationDate = null;
	private String digitalObjectIdentifier = null;
	private int compareValue = 0;
	public Paper(){
		
	}
	
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
	
	public void setCompareValue(int value){
		this.compareValue = value;
	}
	
	@Override
	public int compare(Paper p1, Paper p2) {
		
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		
		switch (p1.compareValue){
		
		case 1:
			if(p1.typeOfPaper.compareTo(p2.typeOfPaper) < 0)
				return BEFORE;
			else if(p1.typeOfPaper.compareTo(p2.typeOfPaper) == 0)
				return EQUAL;
			else if(p1.typeOfPaper.compareTo(p2.typeOfPaper) > 0)
				return AFTER;
			break;
			
		case 2:
			if(p1.authors.compareTo(p2.authors) < 0)
				return BEFORE;
			else if(p1.authors.compareTo(p2.authors) == 0)
				return EQUAL;
			else if(p1.authors.compareTo(p2.authors) > 0)
				return AFTER;
			break;
		
		case 3:
			if(p1.titleOfPaper.compareTo(p2.titleOfPaper) < 0)
				return BEFORE;
			else if(p1.titleOfPaper.compareTo(p2.titleOfPaper) == 0)
				return EQUAL;
			else if(p1.titleOfPaper.compareTo(p2.titleOfPaper) > 0)
				return AFTER;
			break;
			
		case 4:
			if(p1.titleOfSerial.compareTo(p2.titleOfSerial) < 0)
				return BEFORE;
			else if(p1.titleOfSerial.compareTo(p2.titleOfSerial) == 0)
				return EQUAL;
			else if(p1.titleOfSerial.compareTo(p2.titleOfSerial) > 0)
				return AFTER;
			break;
		
			
		case 5:
			try {
				Date dateOfP1 = new SimpleDateFormat("MMMM, yyyy").parse(p1.publicationDate);
				Date dateOfP2 = new SimpleDateFormat("MMMM, yyyy").parse(p2.publicationDate);
				
				if(dateOfP1.before(dateOfP2))
					return BEFORE;
				else if(dateOfP1.equals(dateOfP2))
					return EQUAL;
				else if(dateOfP1.after(dateOfP2))
					return AFTER;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
	}
	return 0;

}

@Override
public int compareTo(Paper p) {
	final int BEFORE = -1;
	final int EQUAL = 0;
	final int AFTER = 1;
	
	switch(p.compareValue){
	
		case 1:
			if(this.typeOfPaper.compareTo(p.typeOfPaper) < 0)
				return BEFORE;
			else if(this.typeOfPaper.compareTo(p.typeOfPaper) == 0)
				return EQUAL;
			else if(this.typeOfPaper.compareTo(p.typeOfPaper) > 0)
				return AFTER;
			break;
			
		case 2:
			if(this.authors.compareTo(p.authors) < 0)
				return BEFORE;
			else if(this.authors.compareTo(p.authors) == 0)
				return EQUAL;
			else if(this.authors.compareTo(p.authors) > 0)
				return AFTER;
			break;
			
		case 3:
			if(this.titleOfPaper.compareTo(p.titleOfPaper) < 0)
				return BEFORE;
			else if(this.titleOfPaper.compareTo(p.titleOfPaper) == 0)
				return EQUAL;
			else if(this.titleOfPaper.compareTo(p.titleOfPaper) > 0)
				return AFTER;
			break;
			
		case 4:
			if(this.titleOfSerial.compareTo(p.titleOfSerial) < 0)
				return BEFORE;
			else if(this.titleOfSerial.compareTo(p.titleOfSerial) == 0)
				return EQUAL;
			else if(this.titleOfSerial.compareTo(p.titleOfSerial) > 0)
				return AFTER;
			break;
			
		case 5:
			try {
				Date dateThis = new SimpleDateFormat("MMMM, yyyy").parse(this.publicationDate);
				Date dateOfP = new SimpleDateFormat("MMMM, yyyy").parse(p.publicationDate);
				
				if(dateThis.before(dateOfP))
					return BEFORE;
				else if(dateThis.equals(dateOfP))
					return EQUAL;
				else if(dateThis.after(dateOfP))
					return AFTER;
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
	
	}
	return 0;
	
}
	
	
}
