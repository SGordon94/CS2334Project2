import java.util.ArrayList;


public class ConferencePaper extends Paper {

	/**
	 * 
	 */
	private Meeting parentMeeting;
	
	private static final long serialVersionUID = -1864623661714427137L;
	public ConferencePaper(ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
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
	
	public ConferencePaper(ArrayList<Object> innerDetails){
		
	}
}
