import java.util.*;

public class Volume {
	private ArrayList<Issue> issues = new ArrayList<Issue>();
	
	public Volume(){}
	
	public Issue getIssue(int index){
		return issues.get(index);
	}
	
	public void addIssue(Issue issue){
		issues.add(issue);
	}
	
	public int getSizeOfIssueList(){
		return issues.size();
	}
}
