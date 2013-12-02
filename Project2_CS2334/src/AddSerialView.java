import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class AddSerialView extends JFrame implements ItemListener{
	private final String CONFERENCE = "Conference";
	private final String JOURNAL = "Journal";
	private JPanel cards;
	private JButton jbtAddSerial = new JButton("Add Serial");
	private JButton jbtSaveMeeting = new JButton("Save Meeting");
	
	public AddSerialView(){
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
		ConferenceView card1 = new ConferenceView();
		
		//Create Journal View panel
		JournalView card2 = new JournalView();
		
		
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
	
	private class ConferenceView extends JPanel implements ActionListener{
		
		/**
		 * 
		 */
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
		
		//private JButton jbtSaveMeeting = new JButton("Save Meeting");
		private ArrayList<String> numberOfMeetings = new ArrayList<String>();
	
		private JPanel cards;
		
		private JLabel programChairsLabel = new JLabel("Program Chairs");
		private JList  programChairs = new JList();
		private JLabel programCommitteeMembersLabel = new JLabel("Committe Members");
		private JList  programCommitteeMembers = new JList();
		
		private String meetingLabel = "Meeting Details";
		private Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		private TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, meetingLabel,
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
		
		public ConferenceView(){
			JPanel mainPanel1 = new JPanel();
			mainPanel1.setLayout(new BoxLayout(mainPanel1, BoxLayout.Y_AXIS));
			
			JPanel organizationPanel = new JPanel();
			organizationPanel.add(organizationLabel);
			organizationPanel.add(organization);
			
			numberOfMeetings.add("New Meeting");
			String[] numberOfMeetingsArray = new String[numberOfMeetings.size()];
			numberOfMeetingsArray = numberOfMeetings.toArray(numberOfMeetingsArray);
			JComboBox meetings = new JComboBox(numberOfMeetingsArray);
			
			cards = new JPanel();
			cards.setLayout(new CardLayout());
			cards.add(createMeetingPanel());
			
			
			mainPanel1.add(organizationPanel);
			mainPanel1.add(meetings);
			mainPanel1.add(cards);
			mainPanel1.add(jbtSaveMeeting);
			jbtSaveMeeting.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			pack();
			
			add(mainPanel1);
		}
		
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
			bottomPanel.setLayout(new GridLayout(1,2,4,4));
			
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

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class JournalView extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6311171508567796171L;
		
		private JLabel     journalNameLabel = new JLabel("Journal: ");
		private JTextField journalName = new JTextField(42);
		private JLabel	   organizationLabel = new JLabel("Organization: ");
		private JTextField organiztionName = new JTextField(39);
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
		
		private String journalLabel = "Journal Details";
		private Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		private TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, journalLabel,
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
		
		public JournalView(){
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(2,1,0,0));
			
			//top panel
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(5,1,0,0));
			
//			JPanel journalPanel = new JPanel();
//			journalPanel.add(journalNameLabel);
//			journalPanel.add(journalName);
			
			JPanel organizationPanel = new JPanel();
			organizationPanel.add(organizationLabel);
			organizationPanel.add(organiztionName);
			
			JPanel issuePanel = new JPanel();
			issuePanel.add(issueLabel);
			issuePanel.add(issueName);
			
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
			
//			topPanel.add(journalPanel);
			topPanel.add(organizationPanel);
			topPanel.add(issuePanel);
			topPanel.add(monthPanel);
			topPanel.add(cityPanel);
			topPanel.setBorder(titledBorder);
			
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
			
			mainPanel.add(topPanel);
			mainPanel.add(bottomPanel);
			
			add(mainPanel);
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cardPanels = (CardLayout)(cards.getLayout());
		cardPanels.show(cards, (String)(e.getItem()));
	}

	public JButton getJBTAddSerial(){
		return jbtAddSerial;
	}
	
	public JButton getJBTSaveMeeting(){
		return jbtSaveMeeting;
	}
}
