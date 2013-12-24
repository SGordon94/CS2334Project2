import java.io.Serializable;
import java.util.*;

public class Volume implements Serializable {
	
	private static final long serialVersionUID = 154555674631899681L;
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
	
	public ArrayList<Issue> getIssues(){
		return this.issues;
	}
}
