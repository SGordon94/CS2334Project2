import java.util.*;


public class Meeting {
	private String month;
	private String year;
	private Location location;
	private ArrayList<Scholar> programChairs;
	private ArrayList<Scholar> committeeMembers;
	private ArrayList<ConferencePaper> papers;
	
	public Meeting(String[] fields, ArrayList<Scholar> chairs, ArrayList<Scholar> members){
		month = fields[0];
		year = fields[1];
		location = new Location(fields[2], fields[3], fields[4]);
		programChairs = chairs;
		committeeMembers = members;
	}
	
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
	
	public String getMonth(){
		return month;
	}
	
	public String getYear(){
		return year;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public String toString(){
		return (month+", "+year+" // "+location.toString());
	}
	
	public ArrayList<Scholar> getProgramChairs(){
		return programChairs;
	}
	
	public boolean containsProgramChair(Scholar key){
		if(programChairs.contains(key)){
			return true;
		}
		return false;
	}
	
	public ArrayList<Scholar> getCommitteeMembers(){
		return committeeMembers;
	}
	
	public boolean containsCommitteeMember(Scholar key){
		if(committeeMembers.contains(key)){
			return true;
		}
		return false;
	}
	
	public ArrayList<ConferencePaper> getPapers(){
		return papers;
	}
	
	public String getAllProgramChairs(){
		String allProgramChairs = "";
		for(int index = 0; index < programChairs.size(); ++index){
			allProgramChairs += programChairs.get(index).returnNameInString() + "\n";
		}
		return allProgramChairs;
	}
	
	public String getAllCommitteeMembers(){
		String allCommitteeMembers = "";
		for(int index = 0; index < committeeMembers.size(); ++index){
			allCommitteeMembers += committeeMembers.get(index).returnNameInString() + "\n";
		}
		return allCommitteeMembers;
	}
	public String getAllPapers(){
		String allPapers = "";
		if(papers != null){
			for(int index = 0; index < papers.size(); ++index){
				allPapers += papers.get(index).getTitleOfPaper() + "\n";
			}
		}else{
			allPapers = "No papers have been added.";
		}
		
		return allPapers;
	}
}
