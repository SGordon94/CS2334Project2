import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ConferenceDataView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7915585240977378050L;
	//Conference details
	private String organizationLabel = "Organization: ";
	private String meetingsLabel = "Meetings: ";
			
	//Meeting details
	private String dateLabel = "Date: ";
	private String meetingDetails = "";
	private String programChairsLabel = "Program Chairs: ";
	private String programCommitteeMembsLabel = "Program Committee Members: ";
	private String publishedPapersLabel = "Published Papers: ";
	private JButton jbtOK = new JButton("OK");
	ArrayList<Conference> openWindowConferences;
	ArrayList<ConferenceDataView> conferenceWindows;
	Conference usedConference;
	ScholarshipModel model;
			
	public ConferenceDataView(Conference conference, ArrayList<Conference> openWindows, ArrayList<ConferenceDataView> windows, ScholarshipModel model){
		usedConference = conference;
		openWindowConferences = openWindows;
		openWindowConferences.add(usedConference);
		conferenceWindows = windows;
		conferenceWindows.add(this);
		
		setLayout(new BorderLayout());
		setTitle("Conference Details");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
				
		for(int index = 0; index < conference.getMeetings().size(); ++index){
			String meetingMonth = conference.getMeetings().get(index).getMonth();
			String meetingYear = conference.getMeetings().get(index).getYear();
			String meetingCity = conference.getMeetings().get(index).getLocation().getCityName();
			String meetingState = conference.getMeetings().get(index).getLocation().getState();
			String meetingCountry = conference.getMeetings().get(index).getLocation().getCountryName();
			String programChairs = conference.getMeetings().get(index).getAllProgramChairs();
			String programCommitteeMembers = conference.getMeetings().get(index).getAllCommitteeMembers();
			
			String papers;
			papers = conference.getMeetings().get(index).getAllPapers();
					
			String date = meetingMonth + " " + meetingYear;
			String location = meetingCity + ", " + meetingState + ", " + meetingCountry;
					
					
			meetingDetails += "\nThis meeting took place during " + date + " in " + location + ".\n\t" + programChairsLabel
					+ "\n\t" + programChairs + "\n\t" + programCommitteeMembsLabel + "\n\t" + programCommitteeMembers + "\n\t" + publishedPapersLabel +
					"\n\t" + papers + "\n";
		
		}
				
		JTextArea mainTextArea = new JTextArea(organizationLabel + conference.getOrganization() + "\n\n" + meetingsLabel + "\n" + meetingDetails);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEditable(false);
		
		add(new JScrollPane(mainTextArea));
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(500,700);
		setLocationRelativeTo(null);
		setVisible(true);		
				
	}
	
	public void windowIsClosing(){
		openWindowConferences.remove(usedConference);
		conferenceWindows.remove(this);
	}
	
	public JButton getJBTOK(){
		return jbtOK;
	}
	
	public Conference getUsedSerial(){
		return usedConference;
	}
}
