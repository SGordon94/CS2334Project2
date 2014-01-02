import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**The Paper class for Paper objects that are imported into the program through a file, stored into a collection, sorted, searched through, and printed.
 * 
 * @version 1.0
 */
public class Paper implements Comparator<Paper>, Comparable<Paper>, Serializable{
	private static final long serialVersionUID = 1588972413579226921L;
	protected String typeOfPaper = null;
	protected ArrayList<Scholar> authorsList = new ArrayList<Scholar>(); 
	protected String authors = "N/A";
	protected String titleOfPaper = null;
	protected String titleOfSerial = null;
	protected String numbers = null;
	protected String publicationDate = null;
	protected String digitalObjectIdentifier = null;
	protected int compareValue = 6;
	protected Conference parentSerial;
	
	public Paper(){}
	
	public Paper(String typeOfPaper, ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate, String digitalObjectIdentifier){
		this.typeOfPaper = typeOfPaper;
		this.authorsList = authorsList;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
		this.digitalObjectIdentifier = digitalObjectIdentifier;
		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}
	}
	
	public Paper(String typeOfPaper, ArrayList<Scholar> authorsList, String titleOfPaper,
			String titleOfSerial, String numbers, String publicationDate){
		this.typeOfPaper = typeOfPaper;
		this.authorsList = authorsList;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}
	}
	
	/**Returns the paper as a String
	 * 
	 * @return the paper
	 */
	public String getPaper(){
		String paper = "";
		paper += this.typeOfPaper + "\n";
		for(int i=0;i<authorsList.size();i++){
			if(i != authorsList.size()-1){
				paper += authorsList.get(i).returnNameInString() + "; ";
			}
			else{
				paper += authorsList.get(i).returnNameInString();
			}
		}
		paper += "\n";
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
	
	/**Returns the type of the paper
	 * 
	 * @return the type of the paper
	 */
	public String getTypeOfPaper(){
		return this.typeOfPaper;
	}
	
	/**Returns the authors of the paper
	 * 
	 * @return the authors of the paper
	 */
	public String getAuthors(){
		return this.authors;
	}
	
	/**Returns the title of the paper
	 * 
	 * @return the title of the paper
	 */
	public String getTitleOfPaper(){
		return this.titleOfPaper;
	}
	
	/**Returns the title of the serial
	 * 
	 * @return the title of the serial
	 */
	public String getTitleOfSerial(){
		return this.titleOfSerial;
	}
	
	/**Returns the volume and issue number for a journal as well as the page numbers for both papers
	 * 
	 * @return the volume and issue number for a journal as well as the page numbers for both papers
	 */
	public String getNumbers(){
		return this.numbers;
	}
	
	/**Returns the date of publication for the paper
	 * 
	 * @return the date of publication for the paper
	 */
	public String getPublicationDate(){
		return this.publicationDate;
	}
	
	/**Returns the date of publication for the paper as a Date object
	 * 
	 * @return the date of publication for the paper as a Date object
	 * @throws ParseException
	 */
	public Date getPublicationDateFormatted() throws ParseException{
		Date date = new SimpleDateFormat("MMMM yyyy").parse(publicationDate);
		return date;
	}
	
	/**Returns the digital object identifier of the paper
	 * 
	 * @return the digital object identifier of the paper
	 */
	public String getDigitalObjectIdentifier(){
		return this.digitalObjectIdentifier;
	}
	
	/**Sets the compare value for the paper
	 * 
	 * @param value the compare value for the paper
	 */
	public void setCompareValue(int value){
		this.compareValue = value;
	}
	
	/**Overridden compare method 
	 * 
	 */
	@Override
	public int compare(Paper p1, Paper p2) {
		
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		
		switch (p1.compareValue){
		
		case 1:
			if(p1.authors.compareTo(p2.authors) < 0)
				return BEFORE;
			else if(p1.authors.compareTo(p2.authors) == 0)
				if(p1.titleOfPaper.compareTo(p2.titleOfPaper) < 0)
					return BEFORE;
				else if(p1.titleOfPaper.compareTo(p2.titleOfPaper) == 0)
					return EQUAL;
				else if(p1.titleOfPaper.compareTo(p2.titleOfPaper) > 0)
					return AFTER;
			else if(p1.authors.compareTo(p2.authors) > 0)
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
				Date dateOfP1 = new SimpleDateFormat("MMMM yyyy").parse(p1.getPublicationDate());
				Date dateOfP2 = new SimpleDateFormat("MMMM yyyy").parse(p2.getPublicationDate());
				
				if(dateOfP1.before(dateOfP2))
					return BEFORE;
				else if(dateOfP1.equals(dateOfP2))
					return EQUAL;
				else if(dateOfP1.after(dateOfP2))
					return AFTER;
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		}
		return 0;

	}
	
	/**Overridden compare method 
	 * 
	*/
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
					Date dateThis = new SimpleDateFormat("MMMM yyyy").parse(this.publicationDate);
					Date dateOfP = new SimpleDateFormat("MMMM yyyy").parse(p.publicationDate);
					
					if(dateThis.before(dateOfP))
						return BEFORE;
					else if(dateThis.equals(dateOfP))
						return EQUAL;
					else if(dateThis.after(dateOfP))
						return AFTER;
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
		
		}
		return 0;
		
	}
	
	/**Overrides the inherited toString() method from Object. Returns
	 * the title of the paper.
	 * 
	 * @return the paper title
	 */
	public String toString(){
		return titleOfPaper;
	}
	
	/**Returns all of the authors as a string.
	 * 
	 * @return all of the authors
	 */
	public String getAllAuthors(){
		String allAuthors = "";
		if(authorsList.size() == 1){
			allAuthors = authorsList.get(0).returnNameInString() + "\n";
		}else{
			for(int index = 0; index < authorsList.size(); ++index){
				allAuthors += authorsList.get(index).returnNameInString() + "\n";
			}
		}
		return allAuthors;
	}
	
	/**Checks if the passed Scholar is contained within this paper.
	 * 
	 * @param scholar the passed Scholar
	 * @return whether the Scholar was found
	 */
	public boolean containsScholar(Scholar scholar){
		for(int index = 0; index < authorsList.size(); ++index)
			if(authorsList.get(index).equals(scholar))
				return true;
		
		return false;
	}
		
	/**Returns the Scholar ArrayList.
	 * 
	 * @return the ArrayList of scholars
	 */
	public ArrayList<Scholar> getListOfScholars(){
		return this.authorsList;
	}
	
	/**Method to be overwritten when this class is inherited.
	 * 
	 * @return dummy Object
	 */
	public Object getParentSerial(){
		return new Object();
	}
	
	/**Method to be overwritten when this class is inherited.
	 * 
	 */
	public void removePaper(){
		
	}
	
	/**Returns the year this paper was made.
	 * 
	 * @return the year
	 */
	public String getYear(){
		String[] year = this.publicationDate.split(" ");
		return year[1];
	}
}
