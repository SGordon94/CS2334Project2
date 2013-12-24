import java.io.Serializable;
import java.util.*;


public class Conference implements Serializable{
	
	private static final long serialVersionUID = -4823703551765579325L;
	private String nameOfOrganization;
	private ArrayList<Meeting> meetings;
	
	public Conference(String organization, ArrayList<Meeting> meets){
		this.nameOfOrganization = organization;
		this.meetings = meets;
	}
	
	public boolean equals(Conference conf){
		if(this.nameOfOrganization.equals(conf.getOrganization())){
			return true;
		}
		return false;
	}
	
	public String getOrganization(){
		return nameOfOrganization;
	}
	
	public Meeting getMeeting(int index){
		return meetings.get(index);
	}
	
	public ArrayList<Meeting> getMeetings(){
		return meetings;
	}
	
	public int getMeetingListSize(){
		return meetings.size();
	}
	
	public String toString(){
		return nameOfOrganization;
	}
}
