import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

/**Class that deals with the GUI for adding serials to the program.
 * 
 * @version 1.0
 */
public class AddSerialView extends JFrame implements ItemListener{
	private final String CONFERENCE = "Conference";
	private final String JOURNAL = "Journal";
	private JPanel cards;
	private JButton jbtAddSerial = new JButton("Add Serial");
	private JButton jbtSaveMeeting = new JButton("Save Meeting");
	private JButton jbtSaveIssue = new JButton("Save Issue");
	private JButton jbtNewVolume = new JButton("New Volume");
	private ScholarshipModel model;
	private ConferenceView card1;
	private JournalView card2;
	private ArrayList<Meeting> temporaryMeetings = new ArrayList<Meeting>();
	private ArrayList<AddSerialView> openAddSerialWindows;
	
	/**The constructor for the "Add Serial" window.
	 * 
	 * @param mod the program's model
	 * @param windows the window tracker for "Add Serial" windows
	 */
	public AddSerialView(ScholarshipModel mod, ArrayList<AddSerialView> windows){
		this.openAddSerialWindows = windows;
		this.model = mod;
		setTitle("Add Serial");
		setLayout(new BorderLayout());
		
		//Add JComboBox panel
		JPanel comboBoxPanel = new JPanel();
		String typesOfSerials[] = {CONFERENCE, JOURNAL};
		JComboBox serialOptions = new JComboBox(typesOfSerials);
		serialOptions.addItemListener(this);
		comboBoxPanel.add(serialOptions);
		add(comboBoxPanel, BorderLayout.NORTH);
		
		//Create Conference View panel
		card1 = new ConferenceView();
		
		//Create Journal View panel
		card2 = new JournalView();
		
		
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		cards.add(card1, CONFERENCE);
		cards.add(card2, JOURNAL);
		add(cards, BorderLayout.CENTER);
		
		//Add Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtAddSerial);
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		setSize(600, 470);
		setLocationRelativeTo(null);
		//pack();
		
		setVisible(true);
		
	}
	
	/**The class for the Conference Paper tab.
	 * 
	 */
	private class ConferenceView extends JPanel{
		private static final long serialVersionUID = -8523558338836188027L;
		private JLabel nameLabel = new JLabel("Name: ");
		private JTextField name = new JTextField(40);
		private JLabel organizationLabel = new JLabel("Organization: ");
		private JTextField organization = new JTextField(36);
		private JLabel monthLabel = new JLabel("Month: ");
		private JTextField month = new JTextField(7);
		private JLabel yearLabel = new JLabel("Year: ");
		private JTextField year = new JTextField(7);
		private JLabel cityLabel = new JLabel("City: ");
		private JTextField city = new JTextField(7);
		private JLabel stateProvinceLabel = new JLabel("State/Province: ");
		private JTextField stateProvince = new JTextField(12);
		private JLabel countryLabel = new JLabel("Country: ");
		private JTextField country = new JTextField(7);
		
		private JPanel cards;
		
		private JLabel programChairsLabel = new JLabel("Program Chairs");
		private JList  programChairs = new JList();
		private JLabel programCommitteeMembersLabel = new JLabel("Committe Members");
		private JList  programCommitteeMembers = new JList();
		
		private String meetingLabel = "Meeting Details";
		private Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		private TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, meetingLabel,
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
		
		/**The constructor for the Conference Paper tab.
		 * 
		 */
		public ConferenceView(){
			JPanel mainPanel1 = new JPanel();
			mainPanel1.setLayout(new BoxLayout(mainPanel1, BoxLayout.Y_AXIS));
			
			JPanel organizationPanel = new JPanel();
			organizationPanel.add(organizationLabel);
			organizationPanel.add(organization);
			
			cards = new JPanel();
			cards.setLayout(new CardLayout());
			cards.add(createMeetingPanel());
			
			
			mainPanel1.add(organizationPanel);
			mainPanel1.add(cards);
			mainPanel1.add(jbtSaveMeeting);
			jbtSaveMeeting.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			programChairs.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			programCommitteeMembers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			programChairs.setListData(model.getScholarNames());
			programCommitteeMembers.setListData(model.getScholarNames());
			
			pack();
			
			add(mainPanel1);
		}
		
		/**Creates the section that deals with the creation of Meetings.
		 * 
		 * @return the created Meeting panel
		 */
		public JPanel createMeetingPanel(){
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(2,1,0,0));
			// Create individual Panels that will go into top panel
			
			JPanel monthPanel = new JPanel();
			monthPanel.add(monthLabel);
			monthPanel.add(month);
			monthPanel.add(yearLabel);
			monthPanel.add(year);
			
			JPanel cityPanel = new JPanel();
			cityPanel.add(cityLabel);
			cityPanel.add(city);
			cityPanel.add(stateProvinceLabel);
			cityPanel.add(stateProvince);
			cityPanel.add(countryLabel);
			cityPanel.add(country);
			
			topPanel.add(monthPanel);
			topPanel.add(cityPanel);
			topPanel.setBorder(titledBorder);
			
			//bottom panel
			
			JPanel bottomPanel = new JPanel();
			int layoutGrid = 1;
			//Daylon: I just had to add bit shifts into the program, so I rewrote the following line
			bottomPanel.setLayout(new GridLayout(layoutGrid,layoutGrid<<1,layoutGrid<<2,layoutGrid<<2));
			//It's totally not necessary, but I like bit shifts :)
			
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(programChairsLabel, BorderLayout.NORTH);
			leftPanel.add(new JScrollPane(programChairs));
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(programCommitteeMembersLabel, BorderLayout.NORTH);
			rightPanel.add(new JScrollPane(programCommitteeMembers));
			bottomPanel.add(leftPanel);
			bottomPanel.add(rightPanel);
			
			JPanel meetingPanel = new JPanel();
			meetingPanel.setLayout(new BoxLayout(meetingPanel,BoxLayout.Y_AXIS));
			meetingPanel.add(topPanel);
			meetingPanel.add(bottomPanel);
			
			return meetingPanel;
		}
		
		/**Verifies that no text fields are left empty, and that scholars have
		 * been selected.
		 * 
		 * @return whether or not the fields have been filled out
		 */
		public boolean fieldsFilled(){
			if(organization.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the name of the Organization.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(month.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Month.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(year.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Year.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(city.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a City.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(stateProvince.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a State or Province.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(country.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Country.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;			
			}
			if(programChairs.getSelectedIndices().length == 0){
				JOptionPane.showMessageDialog(null, "Please selected at least 1 Program Chair.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(programCommitteeMembers.getSelectedIndices().length == 0){
				JOptionPane.showMessageDialog(null, "Please selected at least 1 Committee Member.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			return true;
		}
		
		/**Verifies that the organization name has been filled out.
		 * 
		 * @return whether or not the organization name has been filled out
		 */
		public boolean fieldsFilledOnClosing(){
			if(organization.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the name of the Organization.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			return true;
		}
		
		/**Gathers the contents of the Conference Paper tab and adds them to an
		 * ArrayList of generic Objects.
		 * 
		 * @return the gathered contents in a generic ArrayList<Object>
		 */
		public ArrayList<Object> getInnerDetails(){ // gets the meeting text fields
			ArrayList<Object> returnStuff = new ArrayList<Object>(4);
			String[] fields = new String[5];
			fields[0] = month.getText().trim();
			fields[1] = year.getText().trim();
			fields[2] = city.getText().trim();
			fields[3] = stateProvince.getText().trim();
			fields[4] = country.getText().trim();
			if(programChairs.getSelectedIndices().length == 0){
				Debug.Log("yo");
			}
			ArrayList<Scholar> addedProgramChairs = model.getSelectedScholars(programChairs.getSelectedIndices());
			ArrayList<Scholar> addedCommitteeMembers = model.getSelectedScholars(programCommitteeMembers.getSelectedIndices());
			returnStuff.add("Conference");
			returnStuff.add(fields);
			returnStuff.add(addedProgramChairs);
			returnStuff.add(addedCommitteeMembers);
			return returnStuff;
		}
		
		/**Gathers the text from the organization text field.
		 * 
		 * @return the text of from the organization text field
		 */
		public String getOrganizationName(){
			return organization.getText();
		}
	}
	
	/**The class for the Journal Paper tab.
	 * 
	 */
	private class JournalView extends JPanel{
		private static final long serialVersionUID = 6311171508567796171L;
		
		private JLabel	   organizationLabel = new JLabel("Organization: ");
		private JTextField organizationName = new JTextField(39);
		private JLabel     issueLabel = new JLabel("Issue: ");
		private JTextField issueName = new JTextField(43);
		private JLabel     monthLabel = new JLabel("Month: ");
		private JTextField month = new JTextField(7);
		private JLabel     yearLabel = new JLabel("Year: ");
		private JTextField year = new JTextField(7);
		private JLabel     cityLabel = new JLabel("City: ");
		private JTextField city = new JTextField(7);
		private JLabel     stateProvinceLabel = new JLabel("State/Province: ");
		private JTextField stateProvince = new JTextField(12);
		private JLabel     countryLabel = new JLabel("Country: ");
		private JTextField country = new JTextField(7);
		   
		private JLabel editorsLabel = new JLabel("Editors");
		private JList  editors = new JList();
		private JLabel reviewersLabel = new JLabel("Reviewers");
		private JList  reviewers = new JList();
		
		private JComboBox volumeComboBox = new JComboBox();
		private ArrayList<Volume> temporaryVolumes = new ArrayList<Volume>();
		
		private String journalLabel = "Journal Details";
		private Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		private TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, journalLabel,
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
		
		/**The constructor for the Journal Paper tab.
		 * 
		 */
		public JournalView(){
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			JPanel organizationPanel = new JPanel();
			organizationPanel.add(organizationLabel);
			organizationPanel.add(organizationName);
			
			JPanel cityPanel = new JPanel();
			cityPanel.add(cityLabel);
			cityPanel.add(city);
			cityPanel.add(stateProvinceLabel);
			cityPanel.add(stateProvince);
			cityPanel.add(countryLabel);
			cityPanel.add(country);
			
			cards = new JPanel();
			cards.setLayout(new CardLayout());
			cards.add(createIssuePanel());
			
			jbtSaveIssue.setAlignmentX(CENTER_ALIGNMENT);	
			jbtNewVolume.setAlignmentX(CENTER_ALIGNMENT);
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(jbtSaveIssue);
			buttonPanel.add(jbtNewVolume);
			
			editors.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			reviewers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			editors.setListData(model.getScholarNames());
			reviewers.setListData(model.getScholarNames());
			
			mainPanel.add(organizationPanel);
			mainPanel.add(cityPanel);
			mainPanel.add(volumeComboBox);
			volumeComboBox.setAlignmentX(CENTER_ALIGNMENT);
			mainPanel.add(cards);
			buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
			mainPanel.add(buttonPanel);
			
			add(mainPanel);
		}
		
		/**Creates the section that deals with the creation of Issues.
		 * 
		 * @return the created Issue panel
		 */
		public JPanel createIssuePanel(){
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			JPanel monthPanel = new JPanel();
			monthPanel.add(monthLabel);
			monthPanel.add(month);
			monthPanel.add(yearLabel);
			monthPanel.add(year);
			
			//bottom Panel
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new GridLayout(1,2,4,4));
			
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(editorsLabel, BorderLayout.NORTH);
			leftPanel.add(new JScrollPane(editors));
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(reviewersLabel, BorderLayout.NORTH);
			rightPanel.add(new JScrollPane(reviewers));
			bottomPanel.add(leftPanel);
			bottomPanel.add(rightPanel);
			
			mainPanel.add(monthPanel);
			mainPanel.add(bottomPanel);
			
			return mainPanel;
		}
		
		/**Verifies that no text fields are left empty, and that scholars have
		 * been selected.
		 * 
		 * @return whether or not the fields have been filled out
		 */
		public boolean fieldsFilled(){
			if(organizationName.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the name of the Organization.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(city.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a City.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(stateProvince.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a State or Province.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(country.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Country.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;			
			}
			if(month.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Month.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(year.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Year.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(editors.getSelectedIndices().length == 0){
				JOptionPane.showMessageDialog(null, "Please selected at least 1 Editor.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(reviewers.getSelectedIndices().length == 0){
				JOptionPane.showMessageDialog(null, "Please selected at least 1 Reviewer.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			return true;
		}
		
		/**Verifies that no text fields are left empty.
		 * 
		 * @return whether or not the fields have been filled out
		 */
		public boolean fieldsFilledOnClosing(){
			if(organizationName.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the name of the Organization.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(city.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a City.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(stateProvince.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a State or Province.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(country.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input a Country.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;			
			}
			return true;
		}
		
		/**Gathers the contents of the Journal Paper tab and adds them to an
		 * ArrayList of generic Objects.
		 * 
		 * @return the gathered contents in a generic ArrayList<Object>
		 */
		public ArrayList<Object> getInnerDetails(){
			ArrayList<Object> returnStuff = new ArrayList<Object>(4);
			String[] fields = new String[2];
			fields[0] = month.getText().trim();
			fields[1] = year.getText().trim();
			ArrayList<Scholar> addedEditors = model.getSelectedScholars(editors.getSelectedIndices());
			ArrayList<Scholar> addedReviewers = model.getSelectedScholars(reviewers.getSelectedIndices());
			returnStuff.add("Journal");
			returnStuff.add(fields);
			returnStuff.add(addedEditors);
			returnStuff.add(addedReviewers);
			return returnStuff;
		}
		
		/**Gathers the text from the organization text field.
		 * 
		 * @return returns the text of from the organization text field
		 */
		public String getOrganizationName(){
			return organizationName.getText();
		}
		
		/**Reads the text fields and creates a Location object from the
		 * collected data.
		 * 
		 * @return the created Location object
		 */
		public Location getJournalLocation(){
			return new Location(city.getText(), stateProvince.getText(), country.getText());
		}
		
		/**Updates the contents of the Volume selection box with the
		 * current data.
		 * 
		 */
		public void updateVolumeComboBox(){
			if(temporaryVolumes.size() != 0){
				String[] volumeComboBoxItems = new String[temporaryVolumes.size()];
				for(int i=0;i<temporaryVolumes.size();i++){
					volumeComboBoxItems[i] = "Volume "+(i+1);
				}
				volumeComboBox.setModel(new DefaultComboBoxModel(volumeComboBoxItems));
			}
		}
	}
	
	/**Method needed to switch between card panels
	 * 
	 */
	public void itemStateChanged(ItemEvent e) {
		CardLayout cardPanels = (CardLayout)(cards.getLayout());
		cardPanels.show(cards, (String)(e.getItem()));
	}
	
	/**Returns a string indicating which card is selected.
	 * 
	 * @return the string indicating which card is selected.
	 */
	public String visibleCard(){
		if(card1.isVisible()){
			return "Conference";
		}
		return "Journal";
	}
	
	/**Calls the getInnerDetails() method contained in the currently
	 * selected tab.
	 * 
	 * @return the ArrayList<Object> that was passed to this method
	 */
	public ArrayList<Object> getInnerDetails(){
		if(card1.isVisible()){
			if(card1.fieldsFilled()){
				return card1.getInnerDetails();
			}
			else{
				return new ArrayList<Object>();
			}
		}
		else if(card2.isVisible()){
			if(card2.fieldsFilled()){
				return card2.getInnerDetails();
			}
			else{
				return new ArrayList<Object>();
			}
		}
		else{
			return new ArrayList<Object>();
		}
	}
	
	/**Call the fieldsFilledOnClosing() method contained in the currently
	 * selected tab.
	 * 
	 * @return the boolean returned from the inner method
	 */
	public boolean fieldsFilledOnClosing(){
		if(card1.isVisible()){
			return card1.fieldsFilledOnClosing();
		}
		else if(card2.isVisible()){
			return card2.fieldsFilledOnClosing();
		}
		else{
			JOptionPane.showMessageDialog(null, "INCORRECT PANEL PASSED; ADDSERIALVIEW CLASS", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		return false; // solely to remove compiler error
	}
	
	/**Returns the Meeting ArrayList of this window.
	 * 
	 * @return the ArrayList of meetings
	 */
	public ArrayList<Meeting> getMeetings(){
		return temporaryMeetings;
	}
	
	/**Adds the given Meeting to the Meeting ArrayList of this window.
	 * 
	 * @param meet the meeting to add to the ArrayList
	 */
	public void addTemporaryMeeting(Meeting meet){
		if(meet != null){
			temporaryMeetings.add(meet);
		}
	}
	
	/**Returns the size of the Meeting ArrayList.
	 * 
	 * @return the size of the Meeting ArrayList
	 */
	public int temporaryMeetingSize(){
		return temporaryMeetings.size();
	}
	
	/**Searches the Meeting ArrayList for the passed Meeting. Returns
	 * a boolean indicated whether it was found or not.
	 * 
	 * @param meet the meeting to search for
	 * @return whether the Meeting was found
	 */
	public boolean containsMeeting(Meeting meet){
		if(temporaryMeetings.size() != 0){
			for(int i=0;i<temporaryMeetings.size();i++){
				if(temporaryMeetings.get(i).equals(meet)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**Returns the data from the Conference organization text field.
	 * 
	 * @return the organization text field data
	 */
	public String getConferenceOrganizationName(){
		return card1.getOrganizationName();
	}
	
	/**Returns the Volume ArrayList of this window.
	 * 
	 * @return the ArrayList of volumes
	 */
	public ArrayList<Volume> getVolumes(){
		return card2.temporaryVolumes;
	}
	
	/**Adds the passed Issue to the currently selected Volume.
	 * 
	 * @param issue the Issue to be added
	 */
	public void addIssueToVolume(Issue issue){
		if(card2.temporaryVolumes.size() != 0){
			card2.temporaryVolumes.get(card2.volumeComboBox.getSelectedIndex()).addIssue(issue);
		}
		else{
			card2.temporaryVolumes.add(new Volume());
			card2.temporaryVolumes.get(0).addIssue(issue);
			card2.updateVolumeComboBox();
		}
	}
	
	/**Checks if the passed Issue is contained within the currently
	 * selected Volume.
	 * 
	 * @param issue the Issue to check
	 * @return whether the issue was found
	 */
	public boolean containsIssue(Issue issue){
		if(card2.volumeComboBox.getItemCount() != 0){
			for(int i=0;i<card2.temporaryVolumes.get(card2.volumeComboBox.getSelectedIndex()).getSizeOfIssueList();i++){
				if(card2.temporaryVolumes.get(card2.volumeComboBox.getSelectedIndex()).getIssue(i).equals(issue)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**Verifies that all volumes have issues within them.
	 * 
	 * @return whether all volumes have issues
	 */
	public boolean issuesPresent(){
		if(card2.temporaryVolumes.size() != 0){
			for(int i=0;i<card2.temporaryVolumes.size();i++){
				if(card2.temporaryVolumes.get(i).getSizeOfIssueList() != 0){
					return true;
				}
			}
		}
		return false;
	}
	
	/**Adds a new Volume to the Volume ArrayList.
	 * 
	 */
	public void newVolume(){
		card2.temporaryVolumes.add(new Volume());
		card2.updateVolumeComboBox();
	}
	
	/**Returns the data from the Journal organization text field.
	 * 
	 * @return the organization text field data
	 */
	public String getJournalOrganizationName(){
		return card2.getOrganizationName();
	}
	
	/**Calls the JournalView's getJournalLocation() method, and returns
	 * the Location object passed from it.
	 * 
	 * @return the passed Location object
	 */
	public Location getJournalLocation(){
		return card2.getJournalLocation();
	}

	/**Accessor method that returns the JButton that adds the serial to
	 * the program.
	 * 
	 * @return the "Add Serial" JButton
	 */
	public JButton getJBTAddSerial(){
		return jbtAddSerial;
	}
	
	/**Accessor method that returns the JButton that adds a Meeting
	 * to the Conference.
	 * 
	 * @return the "Save Meeting" JButton
	 */
	public JButton getJBTSaveMeeting(){
		return jbtSaveMeeting;
	}
	
	/**Accessor method that returns the JButton that adds a Issue
	 * to the selected Volume.
	 * 
	 * @return the "Save Issue" JButton
	 */
	public JButton getJBTSaveIssue(){
		return jbtSaveIssue;
	}
	
	/**Accessor method that returns the JButton that creates a new
	 * Volume.
	 * 
	 * @return the "New Volume" JButton
	 */
	public JButton getJBTNewVolume(){
		return  jbtNewVolume;
	}
	
	/** Removes this window from the window tracker ArrayList upon closing.
	 * 
	 */
	public void windowIsClosing(){
		openAddSerialWindows.remove(this);
	}
}
