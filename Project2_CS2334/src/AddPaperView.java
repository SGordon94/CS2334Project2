import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddPaperView extends JFrame implements ItemListener{
	private final String CONFERENCEPAPER = "Conference Paper";
	private final String JOURNALPAPER = "Journal Paper";
	private String[] typeOfPapers = {CONFERENCEPAPER, JOURNALPAPER};
	private JComboBox paperSelector = new JComboBox(typeOfPapers);
	private ScholarshipModel model;
	private ConferencePaper card1;
	private JournalPaper card2;
	private JPanel cards;
	
	private JButton jbtAddPaper = new JButton("Add Paper");
	private ArrayList<AddPaperView> openAddPaperWindows;
	
	public AddPaperView(ScholarshipModel mod, ArrayList<AddPaperView> windows){
		this.openAddPaperWindows = windows;
		this.model = mod;
		setLayout(new BorderLayout());
		paperSelector.addItemListener(this);
		
		//Create panel for Conference Paper
		card1 = new ConferencePaper();
		
		//Create panel for Journal Paper
		card2 = new JournalPaper();
		
		//Create panel for paperSelector JComboBox
		JPanel paperSelectorPanel = new JPanel();
		paperSelectorPanel.add(paperSelector);
		
		//Create panel for the Add Paper button
		JPanel addPaperPanel = new JPanel();
		addPaperPanel.add(jbtAddPaper);
		
		
		//Add cards to panel
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		cards.add(card1, CONFERENCEPAPER);
		cards.add(card2, JOURNALPAPER);
		
		//Add components to main frame
		add(paperSelectorPanel, BorderLayout.NORTH);
		add(cards);
		add(addPaperPanel, BorderLayout.SOUTH);
		
		setTitle("Add Paper");
		setSize(600, 500);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		
	}
	
	private class ConferencePaper extends JPanel{
		private JLabel paperTitleLabel = new JLabel("Title: ");
		private JTextField paperTitle = new JTextField(20);
		private JLabel conferenceListLabel = new JLabel("Conferences");
		private JList conferencesJList = new JList();
		private JLabel scholarsListLabel = new JLabel("Scholars");
		private JList scholarsJList = new JList();
		private JLabel paperPageNumbersLabel = new JLabel("Page Numbers: ");
		private JTextField paperPageNumbers = new JTextField(10);
		private JLabel digitalObjectIdentifierLabel = new JLabel("Digital Object Identifier: ");
		private JTextField digitalObjectIdentifier = new JTextField(20);
		
		public ConferencePaper(){
			//Panel for the title of the paper
			JPanel paperTitlePanel = new JPanel();
			paperTitlePanel.add(paperTitleLabel);
			paperTitlePanel.add(paperTitle);
			
			//Panel for the list of Conferences
			JPanel conferencesPanel = new JPanel();
			conferencesPanel.setLayout(new BoxLayout(conferencesPanel, BoxLayout.Y_AXIS));
			conferencesPanel.add(conferenceListLabel);
			conferencesPanel.add(new JScrollPane(conferencesJList));
			
			//Panel for the list of Authors
			JPanel authorsPanel = new JPanel();
			authorsPanel.setLayout(new BoxLayout(authorsPanel, BoxLayout.Y_AXIS));
			authorsPanel.add(scholarsListLabel);
			authorsPanel.add(new JScrollPane(scholarsJList));
			
			//Panel for paper page numbers
			JPanel pageNumbersPanel = new JPanel();
			pageNumbersPanel.add(paperPageNumbersLabel);
			pageNumbersPanel.add(paperPageNumbers);
			
			//Panel for digital object identifier
			JPanel digitalObjectPanel = new JPanel();
			digitalObjectPanel.add(digitalObjectIdentifierLabel);
			digitalObjectPanel.add(digitalObjectIdentifier);
			
			//Set the contents of the JLists
			conferencesJList.setListData(model.getConferenceMeetings());
			scholarsJList.setListData(model.getScholarNames());
			conferencesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scholarsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			//Create additional Panes
			JPanel dataFieldsPanel = new JPanel();
			dataFieldsPanel.setLayout(new GridLayout(3,1));
			dataFieldsPanel.add(paperTitlePanel);
			dataFieldsPanel.add(pageNumbersPanel);
			dataFieldsPanel.add(digitalObjectPanel);
			
			JPanel listsPanel = new JPanel();
			listsPanel.setLayout(new GridLayout(2,1));
			listsPanel.add(conferencesPanel);
			listsPanel.add(authorsPanel);
			
			
			
			//Set the layout and add panels in order
			setLayout(new BorderLayout());
			//add(paperTitlePanel);
			//add(digitalObjectPanel);
			//add(pageNumbersPanel);
			//add(conferencesPanel);
			//add(authorsPanel);
			add(dataFieldsPanel, BorderLayout.NORTH);
			add(listsPanel);
			setVisible(true);
		}
		
		public boolean fieldsFilled(){
			if(paperTitle.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the name of the paper.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(paperPageNumbers.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the paper's page numbers.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(conferencesJList.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(null, "Please select the Meeting that this paper belongs to.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(scholarsJList.getSelectedIndices().length == 0){
				JOptionPane.showMessageDialog(null, "Please select at least on Scholar.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			return true;
		}
		
		public ArrayList<Object> getInnerDetails(){
			ArrayList<Object> returnStuff = new ArrayList<Object>(3);
			String[] fields = new String[3];
			fields[0] = paperTitle.getText().trim();
			fields[1] = paperPageNumbers.getText().trim();
			fields[2] = digitalObjectIdentifier.getText().trim();
			ArrayList<Object> meetingAndConference = model.getSelectedConferenceMeeting(conferencesJList.getSelectedIndex());
			Meeting addedMeeting = (Meeting)meetingAndConference.get(0);
			Conference addedConference = (Conference)meetingAndConference.get(1);;
			ArrayList<Scholar> addedScholars = model.getSelectedScholars(scholarsJList.getSelectedIndices());
			returnStuff.add(fields);
			returnStuff.add(addedMeeting);
			returnStuff.add(addedScholars);
			returnStuff.add(addedConference);
			return returnStuff;
		}
	}
	
	private class JournalPaper extends JPanel{
		private JLabel paperTitleLabel = new JLabel("Title: ");
		private JTextField paperTitle = new JTextField(20);
		private JLabel journalListLabel = new JLabel("Journal-Issue");
		private JList journalsJList = new JList();
		private JLabel scholarsListLabel = new JLabel("Scholars");
		private JList scholarsJList = new JList();
		private JLabel paperPageNumbersLabel = new JLabel("Page Numbers: ");
		private JTextField paperPageNumbers = new JTextField(10);
		private JLabel digitalObjectIdentifierLabel = new JLabel("Digital Object Identifier: ");
		private JTextField digitalObjectIdentifier = new JTextField(20);
		
		public JournalPaper(){
			//Panel for the title of the paper
			JPanel paperTitlePanel = new JPanel();
			paperTitlePanel.add(paperTitleLabel);
			paperTitlePanel.add(paperTitle);
			
			//Panel for the list of Journals
			JPanel journalsPanel = new JPanel();
			journalsPanel.setLayout(new BoxLayout(journalsPanel, BoxLayout.Y_AXIS));
			journalsPanel.add(journalListLabel);
			journalsPanel.add(new JScrollPane(journalsJList));
			
			//Panel for the list of Authors
			JPanel authorsPanel = new JPanel();
			authorsPanel.setLayout(new BoxLayout(authorsPanel, BoxLayout.Y_AXIS));
			authorsPanel.add(scholarsListLabel);
			authorsPanel.add(new JScrollPane(scholarsJList));
			
			//Panel for paper page numbers
			JPanel pageNumbersPanel = new JPanel();
			pageNumbersPanel.add(paperPageNumbersLabel);
			pageNumbersPanel.add(paperPageNumbers);
			
			//Panel for digital object identifier
			JPanel digitalObjectPanel = new JPanel();
			digitalObjectPanel.add(digitalObjectIdentifierLabel);
			digitalObjectPanel.add(digitalObjectIdentifier);
			
			//Set the contents of the JLists
			journalsJList.setListData(model.getJournalIssues());
			scholarsJList.setListData(model.getScholarNames());
			journalsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scholarsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			//Create additional Panes
			JPanel dataFieldsPanel = new JPanel();
			dataFieldsPanel.setLayout(new GridLayout(3,1));
			dataFieldsPanel.add(paperTitlePanel);
			dataFieldsPanel.add(pageNumbersPanel);
			dataFieldsPanel.add(digitalObjectPanel);
			
			JPanel listsPanel = new JPanel();
			listsPanel.setLayout(new GridLayout(2,1));
			listsPanel.add(journalsPanel);
			listsPanel.add(authorsPanel);
			
			//Set the layout and add panels in order
			setLayout(new BorderLayout());
			//add(paperTitlePanel);
			//add(digitalObjectPanel);
			//add(pageNumbersPanel);
			//add(journalsPanel);
			//add(authorsPanel);
			add(dataFieldsPanel, BorderLayout.NORTH);
			add(listsPanel);
			setVisible(true);
		}
		
		public boolean fieldsFilled(){
			if(paperTitle.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the name of the paper.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(paperPageNumbers.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please input the paper's page numbers.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(journalsJList.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(null, "Please select the Issue that this paper belongs to.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			if(scholarsJList.getSelectedIndices().length == 0){
				JOptionPane.showMessageDialog(null, "Please select at least on Scholar.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				return false;
			}
			return true;
		}
		
		public ArrayList<Object> getInnerDetails(){
			ArrayList<Object> returnStuff = new ArrayList<Object>(4);
			String[] fields = new String[3];
			fields[0] = paperTitle.getText().trim();
			fields[1] = paperPageNumbers.getText().trim();
			fields[2] = digitalObjectIdentifier.getText().trim();
			ArrayList<Object> issueAndJournal = model.getSelectedJournalIssue(journalsJList.getSelectedIndex());
			Issue addedIssue = (Issue)issueAndJournal.get(0);
			Journal addedJournal = (Journal)issueAndJournal.get(1);;
			ArrayList<Scholar> addedScholars = model.getSelectedScholars(scholarsJList.getSelectedIndices());
			returnStuff.add(fields);
			returnStuff.add(addedIssue);
			returnStuff.add(addedScholars);
			returnStuff.add(addedJournal);
			return returnStuff;
		}
	}
	
	public String visibleCard(){
		if(card1.isVisible()){
			return "Conference";
		}
		return "Journal";
	}
	
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
	
	public JButton getJBTAddPaper(){
		return jbtAddPaper;
	}
	
	public void windowIsClosing(){
		openAddPaperWindows.remove(this);
	}

	public void itemStateChanged(ItemEvent e) {
		CardLayout cardPanels = (CardLayout)(cards.getLayout());
		cardPanels.show(cards, (String)(e.getItem()));
	}
}
