import java.util.ArrayList;


public class ConferencePaper extends Paper {

	/**
	 * 
	 */
	private Meeting parentMeeting;
	private Conference parentSerial;
	
	private static final long serialVersionUID = -1864623661714427137L;
	public ConferencePaper(ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
			String numbers, String publicationDate, String digitalObjectIdentifier, Meeting meeting){
		this.authorsList = authorsList;
		this.titleOfPaper = titleOfPaper;
		this.titleOfSerial = titleOfSerial;
		this.numbers = numbers;
		this.publicationDate = publicationDate;
		this.digitalObjectIdentifier = digitalObjectIdentifier;
		this.parentMeeting = meeting;
		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ConferencePaper(ArrayList<Object> innerDetails){
		String[] fields = (String[])innerDetails.get(0);
		this.titleOfPaper = fields[0];
		this.numbers = fields[1];
		this.digitalObjectIdentifier = fields[2];
		this.parentMeeting = (Meeting)innerDetails.get(1);
		this.authorsList = (ArrayList<Scholar>)innerDetails.get(2);
		this.parentSerial = (Conference)innerDetails.get(3);
		parentMeeting.addPaper(this);
	}
	
	public void removePaper(){
		parentMeeting.removePaper(this);
	}
	
	public boolean isConference(){
		return true;
	}
}
