import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddSerialView extends JFrame implements ItemListener{
	private final String CONFERENCE = "Conference";
	private final String JOURNAL = "Journal";
	private JPanel cards;
	private JButton jbtAddSerial = new JButton("Add Serial");
	
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
		
		
		setSize(600, 410);
		setLocationRelativeTo(null);
		//pack();
		
		setVisible(true);
		
	}
	
	private class ConferenceView extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8523558338836188027L;
		private JLabel nameLabel = new JLabel("Name: ");
		private JTextField name = new JTextField(" Name of Conference", 30);
		private JLabel meetingLabel = new JLabel("Meeting Details");
		private JLabel monthLabel = new JLabel("Month: ");
		private JTextField month = new JTextField(" Month", 7);
		private JLabel yearLabel = new JLabel("Year: ");
		private JTextField year = new JTextField(" Year", 7);
		private JLabel cityLabel = new JLabel("City: ");
		private JTextField city = new JTextField(" City", 6);
		private JLabel stateProvinceLabel = new JLabel("State/Province: ");
		private JTextField stateProvince = new JTextField(" State/Province");
		private JLabel countryLabel = new JLabel("Country: ");
		private JTextField country = new JTextField(" Country", 5);
		
		private JLabel programChairsLabel = new JLabel("Program Chairs");
		private JList  programChairs = new JList();
		private JLabel programCommitteeMembersLabel = new JLabel("Committe Members");
		private JList  programCommitteeMembers = new JList();
		
		private JLabel publishedPapersLabel = new JLabel("Published Papers");
		private JList  publishedPapers = new JList();
		
		public ConferenceView(){
			JPanel mainPanel1 = new JPanel();
			mainPanel1.setLayout(new GridLayout(3,1,0,0));
			
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(4,1,0,0));
			// Create individual Panels that will go into top panel
			JPanel namePanel = new JPanel();
			namePanel.add(nameLabel);
			namePanel.add(name);
			
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
			
			
			topPanel.add(meetingLabel);
			topPanel.add(namePanel);
			topPanel.add(monthPanel);
			topPanel.add(cityPanel);
			
			//middle panel
			
			JPanel middlePanel = new JPanel();
			middlePanel.setLayout(new GridLayout(1,2,4,4));
//			middlePanel.add(programCommitteeMembersLabel);
//			middlePanel.add(programChairsLabel);
//			middlePanel.add(programCommitteeMembers);
//			middlePanel.add(programChairs);
			
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(programChairsLabel, BorderLayout.NORTH);
			leftPanel.add(new JScrollPane(programChairs));
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(programCommitteeMembersLabel, BorderLayout.NORTH);
			rightPanel.add(new JScrollPane(programCommitteeMembers));
			middlePanel.add(leftPanel);
			middlePanel.add(rightPanel);
			
			//lower panel
			JPanel lowerPanel = new JPanel();
			lowerPanel.setLayout(new GridLayout(2,1,0,0));
			lowerPanel.add(publishedPapersLabel);
			lowerPanel.add(new JScrollPane(publishedPapers));
			
			mainPanel1.add(topPanel);
			mainPanel1.add(middlePanel);
			//mainPanel1.add(lowerPanel);
			
			add(mainPanel1);
		}
	}
	
	private class JournalView extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6311171508567796171L;
		public JournalView(){
			JPanel panel = new JPanel();
			JButton jbt = new JButton("Journal Stuff");
			panel.add(jbt);
			add(panel);
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		CardLayout cardPanels = (CardLayout)(cards.getLayout());
		cardPanels.show(cards, (String)(e.getItem()));
	}

	public JButton getJBTAddSerial(){
		return jbtAddSerial;
	}
}
