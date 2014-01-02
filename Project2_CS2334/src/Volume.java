import java.io.Serializable;
import java.util.*;

/**A data-oriented class. Volumes are contained within Journals.
 * They contain Issues.
 * 
 * @version 1.0
 */
public class Volume implements Serializable {
	private static final long serialVersionUID = 154555674631899681L;
	private ArrayList<Issue> issues = new ArrayList<Issue>();
	
	/**Returns the indicated Issue from this Volume.
	 * 
	 * @param index the index of the Issue to retrieve
	 * @return the indicated Issue
	 */
	public Issue getIssue(int index){
		return issues.get(index);
	}
	
	/**Adds the passed Issue to this Volume.
	 * 
	 * @param issue the Issue to add
	 */
	public void addIssue(Issue issue){
		issues.add(issue);
	}
	
	/**Returns the amount of Issues within this Volume.
	 * 
	 * @return the size of the Issue ArrayList
	 */
	public int getSizeOfIssueList(){
		return issues.size();
	}
	
	/**Returns the Issue ArrayList.
	 * 
	 * @return the ArrayList of issues
	 */
	public ArrayList<Issue> getIssues(){
		return this.issues;
	}
}
