import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**The class that deals with the GUI for viewing Conference Paper information.
 * 
 * @version 1.0
 */
public class ConferencePaperDataView extends JFrame{
	String authorsLabel = "Authors: ";
	String paperTitleLabel = "Title: ";
	String conferencePaperReference = "Conference: ";
	String pageNumbers = "Page Numbers: ";
	String digitalObjectIdentifierLabel = "Digital Object Identifier: ";
	JButton jbtOK = new JButton("OK");
	ArrayList<Paper> openWindowPapers;
	ArrayList<ConferencePaperDataView> paperWindows;
	ConferencePaper usedPaper;
	ScholarshipModel model;

	/**The constructor for the ConferencePaperDataView window.
	 * 
	 * @param paper the conference paper to display information about
	 * @param openWindows the object tracker for papers
	 * @param windows the window tracker for ConferencePaperDataView windows
	 * @param model the program's model
	 */
	public ConferencePaperDataView(ConferencePaper paper, ArrayList<Paper> openWindows, ArrayList<ConferencePaperDataView> windows, ScholarshipModel model){
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
		
		String meetingNumber = "";
		for(int index = 0; index < paper.getParentSerial().getMeetingListSize(); ++index){
			if(paper.getParentSerial().getMeeting(index).equals(paper.getParentMeeting())){
				meetingNumber += index + 1;
			}
		}
		
		String meetingInfo = paper.getParentSerial().getOrganization() + " - Meeting " + meetingNumber;
		
		JTextArea mainTextArea = new JTextArea(paperTitleLabel + paper.getTitleOfPaper() + "\n\n" + authorsLabel + "\n" + paper.getAllAuthors() + "\n"
				+ conferencePaperReference + meetingInfo + "\n\n" + pageNumbers + paper.getNumbers() + "\n\n"
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
	
	/**Returns the ConferencePaper that this class is displaying.
	 * 
	 * @return the used conference paper
	 */
	public ConferencePaper getUsedPaper(){
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
