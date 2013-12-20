import java.util.ArrayList;


public class JournalPaper extends Paper {

	/**
	 * 
	 */
	private Issue parentIssue;
	
	private static final long serialVersionUID = -4382541146772288240L;
	public JournalPaper(ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate, String digitalObjectIdentifier, Issue issue){
		this.authorsList = authorsList;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
		this.digitalObjectIdentifier = digitalObjectIdentifier;
		this.parentIssue = issue;
		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}
	}
	
	public JournalPaper(ArrayList<Object> innerDetails){
		
	}
}
