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
}
