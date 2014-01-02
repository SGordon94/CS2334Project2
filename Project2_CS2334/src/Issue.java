import java.io.Serializable;
import java.util.*;

/**A data-oriented class. Issues contain Journal Articles, and are contained
 * within Volumes.
 * 
 * @version 1.0
 */
public class Issue implements Serializable{

	private static final long serialVersionUID = 3709243214919179153L;
	private String month;
	private String year;
	private ArrayList<Scholar> editors;
	private ArrayList<Scholar> reviewers;
	private ArrayList<JournalPaper> articles = new ArrayList<JournalPaper>();
	
	/**The constructor for Issue objects.
	 * 
	 * @param fields contains the month and year for this issue
	 * @param editor the editor of this issue
	 * @param reviewer the reviewer of this issue
	 */
	public Issue(String[] fields, ArrayList<Scholar> editor, ArrayList<Scholar> reviewer){
		this.month = fields[0];
		this.year = fields[1];
		this.editors = editor;
		this.reviewers = reviewer;
	}
	
	/**Adds the passed JournalPaper to this issue.
	 * 
	 * @param pape the passed JournalPaper
	 */
	public void addPaper(JournalPaper pape){
		articles.add(pape);
	}
	
	/**Removes the passed JournalPaper from this issue if it exists.
	 * 
	 * @param pape the passed JournalPaper
	 */
	public void removePaper(JournalPaper pape){
		articles.remove(pape);
	}
	
	/**Returns the indicated JournalPaper from this issue.
	 * 
	 * @param index the index of the paper to retrieve
	 * @return the indicated JournalPaper
	 */
	public JournalPaper getPaper(int index){
		return articles.get(index);
	}
	
	/**Returns the number of papers this issue possesses.
	 * 
	 * @return the size of the JournalPaper ArrayList
	 */
	public int getPaperListSize(){
		return articles.size();
	}
	
	/**Removes the passed Scholar from this issue.
	 * 
	 * @param key the passed Scholar
	 */
	public void removeScholar(Scholar key){
		editors.remove(key);
		reviewers.remove(key);
	}
	
	/**Checks to make sure scholars are present in both ArrayLists.
	 * 
	 * @return whether the ArrayLists are empty
	 */
	public boolean isAListEmpty(){
		if((editors.size() > 0) && (reviewers.size() > 0)){
			return false;
		}
		return true;
	}
	
	/**Overrides the inherited equals() method from Object. Uses
	 * the date as the basis for comparison.
	 * 
	 * @param issue the Issue to be compared to
	 * @return whether the passed Issue equals this one
	 */
	public boolean equals(Issue issue){
		if(this.month.equals(issue.getMonth())){
			if(this.year.equals(issue.getYear())){
				return true;
			}
		}
		return false;
	}
	
	/**Returns the month of this issue.
	 * 
	 * @return the month of this issue
	 */
	public String getMonth(){
		return month;
	}
	
	/**Returns the year of this issue.
	 * 
	 * @return the year of this issue
	 */
	public String getYear(){
		return year;
	}
	
	/**Returns the Scholar ArrayList containing the editors of this issue.
	 * 
	 * @return the ArrayList of scholars contained as editors
	 */
	public ArrayList<Scholar> getEditors(){
		return editors;
	}
	
	/**Checks if the passed Scholar is contained within the editor
	 * ArrayList.
	 * 
	 * @param key the passed Scholar to check
	 * @return whether the Scholar is present
	 */
	public boolean containsEditor(Scholar key){
		if(editors.contains(key)){
			return true;
		}
		return false;
	}
	
	/**Returns the Scholar ArrayList containing the reviewers of this issue.
	 * 
	 * @return the ArrayList of scholars contained as reviewers
	 */
	public ArrayList<Scholar> getReviewers(){
		return reviewers;
	}
	
	/**Checks if the passed Scholar is contained within the reviewer
	 * ArrayList.
	 * 
	 * @param key the passed Scholar to check
	 * @return whether the Scholar is present
	 */
	public boolean containsReviewer(Scholar key){
		if(reviewers.contains(key)){
			return true;
		}
		return false;
	}
	
	/**Returns the editors of this issue as a string.
	 * 
	 * @return the string of editors
	 */
	public String printEditors(){
		String listOfEditors = "";
		for(int index = 0; index < editors.size(); ++index){
			listOfEditors += editors.get(index).returnNameInString() + "\n\t\t";
		}
		return listOfEditors;
	}
	
	/**Returns the reviewers of this issue as a string.
	 * 
	 * @return the string of reviewers
	 */
	public String printReviewers(){
		String listOfReviewers = "";
		for(int index = 0; index < reviewers.size(); ++index){
			listOfReviewers += reviewers.get(index).returnNameInString() + "\n\t\t";
		}
		return listOfReviewers;
	}
	
	/**Returns the journal articles contained in this issue as a string.
	 * 
	 * @return the string of journal articles
	 */
	public String printArticles(){
		String listOfArticles = "";
		if(articles.isEmpty()){
			listOfArticles = "No articles have been added.";
		}else{
			for(int index = 0; index < articles.size(); ++index){
				listOfArticles += articles.get(index).getTitleOfPaper() + "\n\t\t";
			}
		}
		return listOfArticles;
	}
	
	/**Overrides the inherited toString() method from Object. Returns
	 * the month and year that this issue takes place.
	 * 
	 * @return the month and year
	 */
	public String toString(){
		return (month+", "+year);
	}
	
	/**Checks if the passed paper is contained within this issue.
	 * 
	 * @param paper the passed Paper to be checked
	 * @return whether the paper is present
	 */
	public boolean containsPaper(Paper paper){
		for(int index = 0; index < articles.size(); ++index){
			if(articles.get(index).equals(paper)){
				return true;
			}
		}
		return false;
	}
}
