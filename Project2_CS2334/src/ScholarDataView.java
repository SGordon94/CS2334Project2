import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.JTextComponent;


public class ScholarDataView extends JFrame{
	private String nameLabel = "Name: ";
	private String affiliationsLabel = "Institutional Affiliations: ";
	private String researchLabel = "Research Areas: ";
	private String publicationsLabel = "Publications: ";
	private String chairLabel = "Conferences attended as Program Chair: ";
	private String memberLabel = "Conferences attended as Program Committee Member: ";
	private String editedLabel = "Edited Journals: ";
	private String reviewedLabel = "Reviewed Journals: ";
	JButton jbtOK = new JButton("OK");
	ArrayList<Scholar> openWindowScholars;
	ArrayList<ScholarDataView> scholarWindows;
	Scholar usedScholar;
	ScholarshipModel model;
	
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
		
		String publications = scholar.displayPapers();
		
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
	
	public void windowIsClosing(){
		openWindowScholars.remove(usedScholar);
		scholarWindows.remove(this);
	}
	
	public Scholar getUsedScholar(){
		return usedScholar;
	}
	
	public JButton getJBTOK(){
		return jbtOK;
	}

}
