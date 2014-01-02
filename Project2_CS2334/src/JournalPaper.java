import java.io.Serializable;
import java.util.ArrayList;

/**A data-oriented class. Journal Papers are a type of paper.
 * Also known as Journal Articles.
 * 
 * @version 1.0
 */
public class JournalPaper extends Paper implements Serializable{
	private Issue parentIssue;
	private Journal parentSerial;
	
	private static final long serialVersionUID = -4382541146772288240L;
	
	/**A constructor for JournalPaper objects.
	 * 
	 * @param typeOfPaper the type of the paper
	 * @param authorsList the list of scholars that made this paper
	 * @param titleOfPaper the title of this paper
	 * @param titleOfSerial the title of the serial of the paper
	 * @param numbers the page numbers of the paper
	 * @param publicationDate the publication date of the paper
	 * @param digitalObjectIdentifier the digital object identifier of the paper
	 */
	public JournalPaper(String typeOfPaper, ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate, String digitalObjectIdentifier){
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
	
	/**A constructor for JournalPaper objects.
	 * 
	 * @param typeOfPaper the type of the paper
	 * @param authorsList the list of scholars that made this paper
	 * @param titleOfPaper the title of this paper
	 * @param titleOfSerial the title of the serial of the paper
	 * @param numbers the page numbers of the paper
	 * @param publicationDate the publication date of the paper
	 */
	public JournalPaper(String typeOfPaper, ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate){
		this.authorsList = authorsList;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}
	}
	
	/**A constructor for JournalPaper objects.
	 * 
	 * @param innerDetails the objects and values that will be assigned
	 */
	@SuppressWarnings("unchecked")
	public JournalPaper(ArrayList<Object> innerDetails){
		String[] fields = (String[])innerDetails.get(0);
		this.titleOfPaper = fields[0];
		this.numbers = fields[1];
		this.digitalObjectIdentifier = fields[2];
		this.parentIssue = (Issue)innerDetails.get(1);
		this.authorsList = (ArrayList<Scholar>)innerDetails.get(2);
		this.parentSerial = (Journal)innerDetails.get(3);
		parentIssue.addPaper(this);

		this.publicationDate = parentIssue.getMonth() + " " + parentIssue.getYear();

		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}

	}
	
	/**Removes this references to this paper.
	 * 
	 */
	public void removePaper(){
		authorsList.remove(this);
		parentIssue.removePaper(this);
	}
	
	/**Overrides the getParentSerial() method in the Paper class. Returns
	 * the Journal that this paper belongs to.
	 * 
	 * @return the Journal this paper belongs to
	 */
	public Journal getParentSerial(){
		return this.parentSerial;
	}
}
