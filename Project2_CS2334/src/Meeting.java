import java.io.Serializable;
import java.util.*;

/**A data-oriented class. Meetings are contained within Conferences.
 * They contain ConferencePapers.
 * 
 * @version 1.0
 */
public class Meeting implements Serializable{
	
	private static final long serialVersionUID = 6553738379612061434L;
	private String month;
	private String year;
	private Location location;
	private ArrayList<Scholar> programChairs;
	private ArrayList<Scholar> committeeMembers;
	private ArrayList<ConferencePaper> papers = new ArrayList<ConferencePaper>();
	
	/**The constructor for Meeting objects.
	 * 
	 * @param fields the date the meeting took place
	 * @param chairs the program chairs of the meeting
	 * @param members the committee memebers of the meeting
	 */
	public Meeting(String[] fields, ArrayList<Scholar> chairs, ArrayList<Scholar> members){
		month = fields[0];
		year = fields[1];
		location = new Location(fields[2], fields[3], fields[4]);
		programChairs = chairs;
		committeeMembers = members;
	}
	
	/**Adds the passed ConferencePaper to this Meeting.
	 * 
	 * @param pape the passed paper
	 */
	public void addPaper(ConferencePaper pape){
		papers.add(pape);
	}
	
	/**Removes the passed ConferencePaper from this Meeting, if
	 * it is present.
	 * 
	 * @param pape the passed paper
	 */
	public void removePaper(ConferencePaper pape){
		papers.remove(pape);
	}
	
	/**Overrides the inherited equals() method from Object. Uses
	 * the date and Location as the basis for comparison.
	 * 
	 * @param meet the passed Meeting
	 * @return whether the passed Meeting equals this one
	 */
	public boolean equals(Meeting meet){
		if(this.month.equals(meet.getMonth())){
			if(this.year.equals(meet.getYear())){
				if(this.location.equals(meet.getLocation())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**Returns the month.
	 * 
	 * @return the month
	 */
	public String getMonth(){
		return month;
	}
	
	/**Returns the year.
	 * 
	 * @return the year
	 */
	public String getYear(){
		return year;
	}
	
	/**Returns the Location.
	 * 
	 * @return the Location
	 */
	public Location getLocation(){
		return location;
	}
	
	/**Returns the Scholar ArrayList of program chairs.
	 * 
	 * @return the ArrayList of program chairs
	 */
	public ArrayList<Scholar> getProgramChairs(){
		return programChairs;
	}
	
	/**Checks if the passed Scholar exists within the program chairs.
	 * 
	 * @param key the passed Scholar
	 * @return whether the Scholar was found
	 */
	public boolean containsProgramChair(Scholar key){
		if(programChairs.contains(key)){
			return true;
		}
		return false;
	}
	
	/**Returns the Scholar ArrayList of committee members.
	 * 
	 * @return the ArrayList of committee members
	 */
	public ArrayList<Scholar> getCommitteeMembers(){
		return committeeMembers;
	}
	
	/**Checks if the passed Scholar exists within the committee members.
	 * 
	 * @param key the passed Scholar
	 * @return whether the Scholar was found
	 */
	public boolean containsCommitteeMember(Scholar key){
		if(committeeMembers.contains(key)){
			return true;
		}
		return false;
	}
	
	/**Removes the passed Scholar from the contained ArrayLists.
	 * 
	 * @param key the passed Scholar
	 */
	public void removeScholar(Scholar key){
		programChairs.remove(key);
		committeeMembers.remove(key);
	}
	
	/**Checks to make sure scholars are present in both ArrayLists.
	 * 
	 * @return whether the ArrayLists are empty
	 */
	public boolean isAListEmpty(){
		if((programChairs.size() > 0) && (committeeMembers.size() > 0)){
			return false;
		}
		return true;
	}
	
	/**Returns the amount of papers contained within this Meeting.
	 * 
	 * @return the size of the ConferencePaper ArrayList
	 */
	public int getPaperListSize(){
		return papers.size();
	}
	
	/**Returns the indicated Conference.
	 * 
	 * @param index the index of the desired Conference
	 * @return the indicated Conference
	 */
	public ConferencePaper getPaper(int index){
		return papers.get(index);
	}
	
	/**Returns the ArrayList of ConferencePapers.
	 * 
	 * @return the ArrayList of papers
	 */
	public ArrayList<ConferencePaper> getPapers(){
		return papers;
	}
	
	/**Returns this Meeting's program chairs as a string.
	 * 
	 * @return the Meeting's program chairs
	 */
	public String getAllProgramChairs(){
		String allProgramChairs = "";
		for(int index = 0; index < programChairs.size(); ++index){
			allProgramChairs += programChairs.get(index).returnNameInString() + "\n\t";
		}
		return allProgramChairs;
	}
	
	/**Returns this Meeting's committee members as a string.
	 * 
	 * @return the Meeting's committee members
	 */
	public String getAllCommitteeMembers(){
		String allCommitteeMembers = "";
		for(int index = 0; index < committeeMembers.size(); ++index){
			allCommitteeMembers += committeeMembers.get(index).returnNameInString() + "\n\t";
		}
		return allCommitteeMembers;
	}
	/**Returns this Meeting's papers as a string.
	 * 
	 * @return the Meeting's papers
	 */
	public String getAllPapers(){
		String allPapers = "";
		if(papers != null){
			for(int index = 0; index < papers.size(); ++index){
				allPapers += papers.get(index).getTitleOfPaper() + "\n\t";
			}
		}else{
			allPapers = "No papers have been added.";
		}
		
		return allPapers;
	}
	
	/**Overrides the inherited toString() method from Object. Returns
	 * the month, year, and Location that this Meeting takes place.
	 * 
	 * @return the month, year, and Location
	 */
	public String toString(){
		return (month+", "+year+" // "+location.toString());
	}
}
