import java.io.Serializable;
import java.util.ArrayList;

/**A data-oriented class. Conference Papers are a type of paper.
 * 
 * @version 1.0
 */
public class ConferencePaper extends Paper implements Serializable{
	private Meeting parentMeeting;
	private Conference parentSerial;
	
	private static final long serialVersionUID = -1864623661714427137L;
	
	/**A constructor for ConferencePaper objects.
	 * 
	 * @param typeOfPaper the type of the paper
	 * @param authorsList the list of scholars that made this paper
	 * @param titleOfPaper the title of this paper
	 * @param titleOfSerial the title of the serial of the paper
	 * @param numbers the page numbers of the paper
	 * @param publicationDate the publication date of the paper
	 * @param digitalObjectIdentifier the digital object identifier of the paper
	 */
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
	
	/**A constructor for ConferencePaper objects.
	 * 
	 * @param typeOfPaper the type of the paper
	 * @param authorsList the list of scholars that made this paper
	 * @param titleOfPaper the title of this paper
	 * @param titleOfSerial the title of the serial of the paper
	 * @param numbers the page numbers of the paper
	 * @param publicationDate the publication date of the paper
	 */
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
	
	/**A constructor for ConferencePaper objects.
	 * 
	 * @param innerDetails the objects and values that will be assigned
	 */
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
	
	/**Removes this references to this paper.
	 * 
	 */
	public void removePaper(){
		parentMeeting.removePaper(this);
		authorsList.remove(this);
	}
	
	/**Overrides the getParentSerial() method in the Paper class. Returns
	 * the Conference that this paper belongs to.
	 * 
	 * @return the Conference this paper belongs to
	 */
	public Conference getParentSerial(){
		return this.parentSerial;
	}
	
	/**Returns the Meeting that this paper belongs to.
	 * 
	 * @return the Meeting this paper belongs to
	 */
	public Meeting getParentMeeting(){
		return this.parentMeeting;
	}
}
