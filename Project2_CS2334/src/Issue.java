import java.util.*;


public class Issue {
	private String month;
	private String year;
	private ArrayList<Scholar> editors;
	private ArrayList<Scholar> reviewers;
	private ArrayList<JournalPaper> articles;
	
	public Issue(String[] fields, ArrayList<Scholar> editor, ArrayList<Scholar> reviewer){
		this.month = fields[0];
		this.year = fields[1];
		this.editors = editor;
		this.reviewers = reviewer;
	}
	
	public boolean equals(Issue issue){
		if(this.month.equals(issue.getMonth())){
			if(this.year.equals(issue.getYear())){
				return true;
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
	
	public ArrayList<Scholar> getEditors(){
		return editors;
	}
	
	public ArrayList<Scholar> getReviewers(){
		return reviewers;
	}
}
