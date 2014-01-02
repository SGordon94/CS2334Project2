import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**The class that deals with the GUI for viewing Scholar information.
 * 
 * @version 1.0
 */
public class ScholarDataView extends JFrame{
	private String nameLabel = "Name: ";
	private String affiliationsLabel = "Institutional Affiliations: ";
	private String researchLabel = "Research Areas: ";
	private String publicationsLabel = "Publications: ";
	private String chairLabel = "Conferences attended as Program Chair: ";
	private String memberLabel = "Conferences attended as Program Committee Member: ";
	private String editedLabel = "Edited Journals: ";
	private String reviewedLabel = "Reviewed Journals: ";
	private JButton jbtOK = new JButton("OK");
	private ArrayList<Scholar> openWindowScholars;
	private ArrayList<ScholarDataView> scholarWindows;
	private Scholar usedScholar;
	private ScholarshipModel model;
	
	/**The constructor for the ScholarDataView window.
	 * 
	 * @param scholar the scholar to display information about
	 * @param openWindows the object tracker for scholars
	 * @param windows the window tracker for ScholarDataView windows
	 * @param model the program's model
	 */
	public ScholarDataView(Scholar scholar, ArrayList<Scholar> openWindows, ArrayList<ScholarDataView> windows, ScholarshipModel model){
		usedScholar = scholar;
		openWindowScholars = openWindows;
		openWindowScholars.add(usedScholar);
		scholarWindows = windows;
		scholarWindows.add(this);
		
		setTitle(scholar.returnNameInStringAlt());
		setLayout(new BorderLayout());
		
		String name = scholar.returnNameInStringAlt();
		
		String affiliations = "";
		for(int i = 0; i < scholar.getAffiliations().size(); ++i){
			affiliations += scholar.getAffiliations().get(i) + "\n";
		}
		
		String researchAreas = "";
		for(int i = 0; i < scholar.getResearchAreas().size(); ++i){
			researchAreas += scholar.getResearchAreas().get(i) + "\n";
		}
		
		String publications = model.getPaperTitlesForAuthor(usedScholar);
		
		String chairData = "";
		
		String memberData = "";
		
		String editedData = "";
		
		String reviewedData = "";
		
		for(int i=0;i<model.getConferenceListSize();i++){
			for(int j=0;j<model.getConference(i).getMeetingListSize();j++){
				if(model.getConference(i).getMeeting(j).containsCommitteeMember(usedScholar)){
					memberData += model.getConference(i).toString() + " // " + model.getConference(i).getMeeting(j).toString() + "\n";
				}
				if(model.getConference(i).getMeeting(j).containsProgramChair(usedScholar)){
					chairData += model.getConference(i).toString() + " // " + model.getConference(i).getMeeting(j).toString() + "\n";
				}
			}
		}
		
		for(int i=0;i<model.getJournalListSize();i++){
			for(int j=0;j<model.getJournal(i).getVolumeSize();j++){
				for(int k=0;k<model.getJournal(i).getVolume(j).getSizeOfIssueList();k++){
					if(model.getJournal(i).getVolume(j).getIssue(k).containsEditor(usedScholar)){
						editedData += model.getJournal(i).toString() + " // " + model.getJournal(i).getVolume(j).getIssue(k).toString() + "\n";
					}
					if(model.getJournal(i).getVolume(j).getIssue(k).containsReviewer(usedScholar)){
						reviewedData += model.getJournal(i).toString() + " // " + model.getJournal(i).getVolume(j).getIssue(k).toString() + "\n";
					}
				}
			}
		}
		
		JTextArea mainTextArea = new JTextArea(nameLabel + name + "\n\n" + affiliationsLabel + "\n" + affiliations + "\n" + researchLabel + "\n" + researchAreas
				+ "\n" + publicationsLabel + "\n" + publications + "\n" + chairLabel + "\n" + chairData + "\n" + memberLabel + "\n" + memberData + "\n" + editedLabel + "\n" +
				editedData + "\n" + reviewedLabel + "\n" + reviewedData);
		
		mainTextArea.setEditable(false);
		
		add(new JScrollPane(mainTextArea));
		
		jbtOK.setAlignmentX(CENTER_ALIGNMENT);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/** Removes this window from the window tracker ArrayList upon closing.
	 * 
	 */
	public void windowIsClosing(){
		openWindowScholars.remove(usedScholar);
		scholarWindows.remove(this);
	}
	
	/**Returns the Scholar that this class is displaying.
	 * 
	 * @return the used Scholar
	 */
	public Scholar getUsedScholar(){
		return usedScholar;
	}
	
	/**Accessor method that returns the JButton that closes this window.
	 * 
	 * @return the "OK" JButton
	 */
	public JButton getJBTOK(){
		return jbtOK;
	}

}
