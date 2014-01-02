import java.io.Serializable;
import java.util.*;

/**A data-oriented class. Conferences are a type of serial.
 * 
 * @version 1.0
 */
public class Conference implements Serializable{
	
	private static final long serialVersionUID = -4823703551765579325L;
	private String nameOfOrganization;
	private ArrayList<Meeting> meetings;
	
	/**The constructor for the Conference objects.
	 * 
	 * @param organization the name of the Conference organization
	 * @param meets the Meeting ArrayList for this Conference
	 */
	public Conference(String organization, ArrayList<Meeting> meets){
		this.nameOfOrganization = organization;
		this.meetings = meets;
	}
	
	/**Overrides the inherited equals() method from Object. Uses
	 * the organization name as the basis for comparison.
	 * 
	 * @param conf the passed Conference to compare to
	 * @return whether the passed Conference equals this one
	 */
	public boolean equals(Conference conf){
		if(this.nameOfOrganization.equals(conf.getOrganization())){
			return true;
		}
		return false;
	}
	
	/**Returns the organization name.
	 * 
	 * @return the organization name.
	 */
	public String getOrganization(){
		return nameOfOrganization;
	}
	
	/**Returns the indicated Meeting.
	 * 
	 * @param index the index of the Meeting to return
	 * @return the indicated Meeting
	 */
	public Meeting getMeeting(int index){
		return meetings.get(index);
	}
	
	/**Returns the Meeting ArrayList of this Conference.
	 * 
	 * @return the ArrayList of meetings
	 */
	public ArrayList<Meeting> getMeetings(){
		return meetings;
	}
	
	/**Returns the size of the Meeting ArrayList.
	 * 
	 * @return the size of the ArrayList of meetings
	 */
	public int getMeetingListSize(){
		return meetings.size();
	}
	
	/**Overrides the inherited toString() method from Object. Returns
	 * the name of the organization.
	 * 
	 */
	public String toString(){
		return nameOfOrganization;
	}
}
