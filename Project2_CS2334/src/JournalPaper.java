import java.util.ArrayList;


public class JournalPaper extends Paper {

	/**
	 * 
	 */
	private Issue parentIssue;
	private Journal parentSerial;
	
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
	}
	
	public void removePaper(){
		authorsList.remove(this);
		parentIssue.removePaper(this);
	}
	
	public Journal getParentSerial(){
		return this.parentSerial;
	}
}
