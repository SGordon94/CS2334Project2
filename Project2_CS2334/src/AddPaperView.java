import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

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
	
	public AddPaperView(ScholarshipModel mod){
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
			
			
			//Set the layout and add panels in order
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(paperTitlePanel);
			add(digitalObjectPanel);
			add(pageNumbersPanel);
			add(conferencesPanel);
			add(authorsPanel);
			setVisible(true);
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
			
			//Panel for both lists
			//JPanel listsPanel = new JPanel();
			//listsPanel.add(journalsPanel);
			//listsPanel.add(authorsPanel);
			
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
			
			//Set the layout and add panels in order
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(paperTitlePanel);
			add(digitalObjectPanel);
			add(pageNumbersPanel);
			add(journalsPanel);
			add(authorsPanel);
			setVisible(true);
		}
	}
	
	public String visibleCard(){
		if(card1.isVisible()){
			return "Conference";
		}
		return "Journal";
	}
	
	public JButton getJBTAddPaper(){
		return jbtAddPaper;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		CardLayout cardPanels = (CardLayout)(cards.getLayout());
		cardPanels.show(cards, (String)(e.getItem()));
	}
}
