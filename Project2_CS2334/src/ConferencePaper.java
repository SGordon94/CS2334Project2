import java.io.Serializable;
import java.util.ArrayList;


public class ConferencePaper extends Paper implements Serializable{

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
	
	public ConferencePaper(String typeOfPaper, ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
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
	
	public ConferencePaper(String typeOfPaper, ArrayList<Scholar> authorsList, String titleOfPaper, String titleOfSerial,
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

		this.publicationDate = parentMeeting.getMonth() + " " + parentMeeting.getYear();

		for(int i=0; i<authorsList.size(); i++){
			authorsList.get(i).setContainingPaper(this);
		}

	}
	
	public void removePaper(){
		parentMeeting.removePaper(this);
		authorsList.remove(this);
	}
	
	public Conference getParentSerial(){
		return this.parentSerial;
	}
	
	public Meeting getParentMeeting(){
		return this.parentMeeting;
	}
}
