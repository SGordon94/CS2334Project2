import javax.swing.JFrame;
import javax.swing.JPanel;
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
	
	public JournalDataView(Journal journal){
		setTitle("Journal Details");
		JPanel mainPanel = new JPanel();
		String organization = organizationLabel + journal.getOrganization();
		String location = locationLabel + journal.getLocation().getCityName() + ", " + journal.getLocation().getState() + ", "
				+ journal.getLocation().getCountryName();
		
		for(int firstIndex = 0; firstIndex < journal.getVolumes().size(); ++firstIndex){
			
			for(int secondIndex = 0; secondIndex < journal.getVolumes().get(secondIndex).getSizeOfIssueList(); ++secondIndex){
				
				String issueDate = journal.getVolumes().get(firstIndex).getIssue(secondIndex).getMonth() 
						+ " " + journal.getVolumes().get(firstIndex).getIssue(secondIndex).getYear();
				String editors = editorsLabel + "\n\t" + journal.getVolumes().get(firstIndex).getIssue(secondIndex).printEditors();
				String reviewers = reviewersLabel + "\n\t" + journal.getVolumes().get(firstIndex).getIssue(secondIndex).printReviewers();
				String articles = articlesLabel + "\b\t" + journal.getVolumes().get(firstIndex).getIssue(secondIndex).printArticles();
				
				issueDetails += issueDate + "\n\t" + editors + "\n" + reviewers + "\n" + articles + "\n";
				
			}
			volumeDetails = "Volume " + firstIndex + 1 + "\n" + issueLabel + "\n\t" + issueDetails;
		}
		JTextArea mainTextArea = new JTextArea(organization + "\n" + location + "\n" + volumeLabel + "\n" + volumeDetails);
		mainTextArea.setEditable(false);
		mainPanel.add(mainTextArea);
		add(mainPanel);
		setLocationRelativeTo(null);
		setSize(400,600);
		setVisible(true);
	}
}
