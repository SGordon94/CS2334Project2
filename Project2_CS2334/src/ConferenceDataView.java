import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private String meetingDetails;
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
		
		setLayout(new BorderLayout());
		setTitle("Conference Details");
		JPanel mainPanel = new JPanel();
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
					
					
			meetingDetails += dateLabel + date + "\n\t" + "This meeting took place on " + date + " at " + location + ".\n\n" + programChairsLabel
					+ "\n" + programChairs + "\n" + programCommitteeMembsLabel + "\n" + programCommitteeMembers + "\n" + publishedPapersLabel +
					"\n" + papers;
		}
				
		JTextArea mainTextArea = new JTextArea(organizationLabel + conference.getOrganization() + "\n\n" + meetingsLabel + "\n" + meetingDetails);
		mainTextArea.setEditable(false);
		mainPanel.add(mainTextArea);
		add(mainPanel);
		add(buttonPanel, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setSize(400,600);
		setVisible(true);		
				
	}
	
	public void windowIsClosing(){
		openWindowConferences.remove(usedConference);
		conferenceWindows.remove(this);
	}
	
	public JButton getJBTOK(){
		return jbtOK;
	}
}
