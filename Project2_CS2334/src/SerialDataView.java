import javax.swing.*;


public class SerialDataView {
	public SerialDataView(){
		
	}
	
	private class ConferenceDataView extends JFrame{
		//Conference details
		private String organizationLabel = "Organization: ";
		private String meetingsLabel = "Meetings: ";
		
		//Meeting details
		private String dateLabel = "Date: ";
		private String meetingDetails;
		private String programChairsLabel = "Program Chairs: ";
		private String programCommitteeMembsLabel = "Program Committee Members: ";
		private String publishedPapersLabel = "Published Papers: ";
		
		public ConferenceDataView(Conference conference){
			setTitle("Conference Details");
			JPanel mainPanel = new JPanel();
			
			for(int index = 0; index < conference.getMeetings().size(); ++index){
				String meetingMonth = conference.getMeetings().get(index).getMonth();
				String meetingYear = conference.getMeetings().get(index).getYear();
				String meetingCity = conference.getMeetings().get(index).getLocation().getCityName();
				String meetingState = conference.getMeetings().get(index).getLocation().getState();
				String meetingCountry = conference.getMeetings().get(index).getLocation().getCountryName();
				String programChairs = conference.getMeetings().get(index).getAllProgramChairs();
				String programCommitteeMembers = conference.getMeetings().get(index).getAllCommitteeMembers();
				String papers = conference.getMeetings().get(index).getAllPapers();
				
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
			
			
		}
	}
	
	private class JournalDataView extends JFrame{
		
		//Journal Details
		private String organizationLabel = "Organization: ";
		private String locationLabel = "Location: ";
		
		//Volume Details
		private String volumeLabel = "Volume: ";
		
		//Issue Details
		private String issueLabel = "Issue: ";
		
		public JournalDataView(Journal journal){
			setTitle("Journal Details");
			JPanel mainPanel = new JPanel();
			
			for(int firstIndex = 0; firstIndex < journal.getVolumes().size(); ++firstIndex){
				for(int secondIndex = 0; secondIndex < journal.getVolumes().get(secondIndex).getSizeOfIssueList(); ++secondIndex){
					
				}
			}
			
			
		}
	}
}
