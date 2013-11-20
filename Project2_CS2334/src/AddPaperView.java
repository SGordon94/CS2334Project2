import javax.swing.*;

import java.awt.*;

public class AddPaperView extends JFrame{
	private JLabel paperTypeLabel = new JLabel(" Paper Type (Conference Paper/Journal Paper): ");
	private JTextField paperType = new JTextField();
	private JLabel paperNameLabel = new JLabel("Name: ");
	private JTextField paperName = new JTextField();
	private JLabel conferencePagesLabel = new JLabel("Page Numbers for Conference Paper: ");
	private JTextField conferencePages = new JTextField();
	private JLabel journalNumbersLabel = new JLabel("Volume(Issue) :Page Numbers");
	private JTextField journalNumbers = new JTextField();
	private JLabel publicationMonthLabel = new JLabel("Month of Publication: ");
	private JTextField publicationMonth = new JTextField();
	private JLabel publicationYearLabel = new JLabel("Year of Publication: ");
	private JTextField publicationYear = new JTextField();
	private JLabel scholarsListLabel = new JLabel("List of Scholars");
	private JList scholarList = new JList();
	private JLabel serialsListLabel = new JLabel("List of Serials");
	private JList serialsList = new JList();
	private JButton jbtAddPaper = new JButton("Add Paper");
	
	public AddPaperView(){
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		JPanel paperTypePanel = new JPanel();
		paperTypePanel.add(paperTypeLabel);
		paperTypePanel.add(paperType);
		
		JPanel paperNamePanel = new JPanel();
		paperNamePanel.add(paperNameLabel);
		paperNamePanel.add(paperName);
		
		JPanel numbersPanel = new JPanel();
		numbersPanel.add(conferencePagesLabel);
		numbersPanel.add(conferencePages);
		numbersPanel.add(journalNumbersLabel);
		numbersPanel.add(journalNumbers);
		
		JPanel publicationDatePanel = new JPanel();
		publicationDatePanel.add(publicationMonthLabel);
		publicationDatePanel.add(publicationMonth);
		publicationDatePanel.add(publicationYearLabel);
		publicationDatePanel.add(publicationYear);
		
		topPanel.add(paperTypePanel);
		topPanel.add(paperNamePanel);
		topPanel.add(numbersPanel);
		topPanel.add(publicationDatePanel);
		
		//bottom panel
		JPanel bottomPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(scholarsListLabel, BorderLayout.NORTH);
		leftPanel.add(new JScrollPane(scholarList));
		JPanel rightPanel = new JPanel();
		rightPanel.add(serialsListLabel, BorderLayout.NORTH);
		rightPanel.add(new JScrollPane(serialsList));
		bottomPanel.add(leftPanel);
		bottomPanel.add(rightPanel);
		
		//button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtAddPaper);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel);
		mainPanel.add(buttonPanel);
		
		add(mainPanel);
		
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public JButton getJBTAddPaper(){
		return jbtAddPaper;
	}
}
