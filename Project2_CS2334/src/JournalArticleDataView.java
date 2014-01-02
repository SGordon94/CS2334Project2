import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**The class that deals with the GUI for viewing Journal Article information.
 * 
 * @version 1.0
 */
public class JournalArticleDataView extends JFrame{
	String authorsLabel = "Authors: ";
	String paperTitleLabel = "Title: ";
	String articleIssueReference = "Issue: ";
	String pageNumbers = "Page Numbers: ";
	String digitalObjectIdentifierLabel = "Digital Object Identifier: ";
	JButton jbtOK = new JButton("OK");
	ArrayList<Paper> openWindowPapers;
	ArrayList<JournalArticleDataView> paperWindows;
	JournalPaper usedPaper;
	ScholarshipModel model;

	/**The constructor for the JournalArticleDataView window.
	 * 
	 * @param paper the journal article to display information about
	 * @param openWindows the object tracker for papers
	 * @param windows the window tracker for JournalArticleDataView windows
	 * @param model the program's model
	 */
	public JournalArticleDataView(JournalPaper paper, ArrayList<Paper> openWindows, ArrayList<JournalArticleDataView> windows, ScholarshipModel model){
		usedPaper = paper;
		openWindowPapers = openWindows;
		openWindowPapers.add(usedPaper);
		paperWindows = windows;
		paperWindows.add(this);
		
		setTitle(paper.getTitleOfPaper());
		String digitalObjectIdentifier = "";
		
		if(paper.getDigitalObjectIdentifier() != null){
			digitalObjectIdentifier = paper.getDigitalObjectIdentifier();
		}else{
			digitalObjectIdentifier = "N/A";
		}
		
		String volumeNumber = "";
		String issueNumber = "";
		
		for(int firstIndex = 0; firstIndex < paper.getParentSerial().getVolumeSize(); ++firstIndex){
			for(int secondIndex = 0; secondIndex < paper.getParentSerial().getVolumes().get(firstIndex).getSizeOfIssueList(); ++secondIndex){
				if(paper.getParentSerial().getVolume(firstIndex).getIssues().get(secondIndex).containsPaper(paper)){
					volumeNumber += firstIndex + 1;
					issueNumber += secondIndex + 1;
				}
			}
		}
		
		String issueDetails = paper.getParentSerial().getOrganization() + " - Volume " + volumeNumber 
				+ " Issue " + issueNumber;
		
		JTextArea mainTextArea = new JTextArea(paperTitleLabel + paper.getTitleOfPaper() + "\n\n" + authorsLabel + paper.getAllAuthors() + "\n"
				+ articleIssueReference + issueDetails + "\n\n" + pageNumbers + paper.getNumbers() + "\n\n"
				+ digitalObjectIdentifierLabel + digitalObjectIdentifier);
		mainTextArea.setEditable(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(mainTextArea));
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(400,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/** Removes this window from the window tracker ArrayList upon closing.
	 * 
	 */
	public void windowIsClosing(){
		openWindowPapers.remove(usedPaper);
		paperWindows.remove(this);
	}
	
	/**Returns the JournalPaper that this class is displaying.
	 * 
	 * @return the used journal article
	 */
	public JournalPaper getUsedPaper(){
		return usedPaper;
	}
	
	/**Accessor method that returns the JButton that closes this window.
	 * 
	 * @return the "OK" JButton
	 */
	public JButton getJBTOK(){
		return jbtOK;
	}
}
