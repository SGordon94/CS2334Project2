import java.util.*;


public class Issue {
	private String month;
	private String year;
	private ArrayList<Scholar> editors;
	private ArrayList<Scholar> reviewers;
	private ArrayList<JournalPaper> articles = new ArrayList<JournalPaper>();
	
	public Issue(String[] fields, ArrayList<Scholar> editor, ArrayList<Scholar> reviewer){
		this.month = fields[0];
		this.year = fields[1];
		this.editors = editor;
		this.reviewers = reviewer;
	}
	
	public void addPaper(JournalPaper pape){
		articles.add(pape);
	}
	
	public void removePaper(JournalPaper pape){
		articles.remove(pape);
	}
	
	public JournalPaper getPaper(int index){
		return articles.get(index);
	}
	
	public int getPaperListSize(){
		return articles.size();
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
	
	public boolean containsEditor(Scholar key){
		if(editors.contains(key)){
			return true;
		}
		return false;
	}
	
	public ArrayList<Scholar> getReviewers(){
		return reviewers;
	}
	
	public boolean containsReviewer(Scholar key){
		if(reviewers.contains(key)){
			return true;
		}
		return false;
	}
	
	public String printEditors(){
		String listOfEditors = "";
		for(int index = 0; index < editors.size(); ++index){
			listOfEditors += editors.get(index).returnNameInString() + "\n";
		}
		return listOfEditors;
	}
	
	public String printReviewers(){
		String listOfReviewers = "";
		for(int index = 0; index < reviewers.size(); ++index){
			listOfReviewers += reviewers.get(index).returnNameInString() + "\n";
		}
		return listOfReviewers;
	}
	
	public String printArticles(){
		String listOfArticles = "";
		if(articles.isEmpty()){
			listOfArticles = "No articles have been added.";
		}else{
			for(int index = 0; index < articles.size(); ++index){
				listOfArticles += articles.get(index).getTitleOfPaper();
			}
		}
		return listOfArticles;
	}
	
	public String toString(){
		return (month+", "+year);
	}
	
	public boolean containsPaper(Paper paper){
		for(int index = 0; index < articles.size(); ++index){
			if(articles.get(index).equals(paper)){
				return true;
			}
		}
		return false;
	}
}
