import java.util.*;


public class Conference {
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
	
	public ArrayList<Meeting> getMeetings(){
		return meetings;
	}
}
