import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JournalDataView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5791177775391066257L;
	//Journal Details
	private String organizationLabel = "Organization: ";
	private String locationLabel = "Location: ";
	
	//Volume Details
	private String volumeLabel = "Volumes: ";
	private String volumeDetails = "";
	
	//Issue Details
	private String issueLabel = "Issues: ";
	private String issueDetails = "";
	private String editorsLabel = "Editors: ";
	private String reviewersLabel = "Reviewers: ";
	private String articlesLabel = "Articles: ";
	
	private JButton jbtOK = new JButton("OK");
	ArrayList<Journal> openWindowJournals;
	ArrayList<JournalDataView> journalWindows;
	Journal usedJournal;
	ScholarshipModel model;
	
	public JournalDataView(Journal journal, ArrayList<Journal> openWindows, ArrayList<JournalDataView> windows, ScholarshipModel model){
		usedJournal = journal;
		openWindowJournals = openWindows;
		openWindowJournals.add(usedJournal);
		journalWindows = windows;
		
		setTitle("Journal Details");
		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
		String organization = organizationLabel + journal.getOrganization();
		String location = locationLabel + journal.getLocation().getCityName() + ", " + journal.getLocation().getState() + ", "
				+ journal.getLocation().getCountryName();
		
		for(int firstIndex = 0; firstIndex < journal.getVolumes().size(); ++firstIndex){
			
			for(int secondIndex = 0; secondIndex < journal.getVolumes().get(secondIndex).getSizeOfIssueList(); ++secondIndex){
				
				String issueDate = journal.getVolumes().get(firstIndex).getIssue(secondIndex).getMonth() 
						+ " " + journal.getVolumes().get(firstIndex).getIssue(secondIndex).getYear();
				String editors = editorsLabel + "\n\t" + journal.getVolumes().get(firstIndex).getIssue(secondIndex).printEditors();
				String reviewers = reviewersLabel + "\n\t" + journal.getVolumes().get(firstIndex).getIssue(secondIndex).printReviewers();
				
				String articles;
				if(journal.getVolume(firstIndex).getIssue(secondIndex) != null){
					articles = articlesLabel + "\b\t" + journal.getVolumes().get(firstIndex).getIssue(secondIndex).printArticles();
				}else{
					articles = "No articles have been added.";
				}
		
				issueDetails += issueDate + "\n\t" + editors + "\n" + reviewers + "\n" + articles + "\n";
				
			}
			volumeDetails = "Volume " + firstIndex + 1 + "\n" + issueLabel + "\n\t" + issueDetails;
		}
		JTextArea mainTextArea = new JTextArea(organization + "\n" + location + "\n" + volumeLabel + "\n" + volumeDetails);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEditable(false);
		
		add(new JScrollPane(mainTextArea));
		add(buttonPanel, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setSize(400,600);
		setVisible(true);
	}
	
	public void windowIsClosing(){
		openWindowJournals.remove(usedJournal);
		journalWindows.remove(this);
	}
	
	public JButton getJBTOK(){
		return jbtOK;
	}
}
