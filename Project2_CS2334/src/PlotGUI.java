import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**The class that contains the subclasses to display the plots.
 * 
 * @version 1.0
 */
public class PlotGUI {
	private ScholarshipModel model;
	private Scholar selectedScholar;
	private ArrayList<Paper> publishedPapers = new ArrayList<Paper>();
	private int scholarIndex;
	private String option;
	private int displayOption = 0;
	private int numberOfConferencePapers = 0;
	private int numberOfJournalArticles = 0;
	
	private ArrayList<String> yearStrings = new ArrayList<String>();
	private ArrayList<Integer> data = new ArrayList<Integer>();
	private ArrayList<Paper> conferencePapers = new ArrayList<Paper>();
	private ArrayList<Paper> journalArticles = new ArrayList<Paper>();
	private ArrayList<PlotGUI> plotGUIS = new ArrayList<PlotGUI>();
	private JList listOfScholarNames = new JList();;
	private PlotGUI thisGUI;
	private SelectScholarPanel thisScholarPanel;
	private Object thisOptionClass;
	private Point previousScreenLocation;
	private boolean buttonPressed = false;
	
	/**A constructor for PlotGUI windows.
	 * 
	 * @param model the program's model
	 * @param option determines which plot to graph
	 * @param GUIS the window tracker for PlotGUI windows
	 */
	public PlotGUI(ScholarshipModel model, String option, ArrayList<PlotGUI> GUIS){
		this.model = model;
		this.option = option;
		this.displayOption = 1; //uses Project 4
		SelectScholarPanel scholarPanel = new SelectScholarPanel();
		this.plotGUIS = GUIS;
		this.plotGUIS.add(this);
		this.thisGUI = this;
	}
	
	/**A constructor for PlotGUI windows.
	 * 
	 * @param scholar the scholar to display data for
	 * @param option determines which plot to graph
	 */
	public PlotGUI(Scholar scholar, String option){
		selectedScholar = scholar;
		this.option = option;
		this.displayOption = 2; //uses Project 3
		buttonPressed = true;
		updatePlot();
		buttonPressed = false;
	}
	
	private class LocalWindowListener implements WindowListener{
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			plotGUIS.remove(thisGUI);
			if(thisScholarPanel != null){
				thisScholarPanel.dispose();
			}
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class SelectScholarPanel extends JFrame{
		private JButton jbtOK = new JButton("OK");
		private JButton jbtCancel = new JButton("Cancel");
		
		public SelectScholarPanel(){
			this.addWindowListener(new LocalWindowListener());
			thisScholarPanel = this;
			setTitle("Scholars");
			setLayout(new BorderLayout());
			
			JLabel scholarsLabel = new JLabel("Scholars");
			updateList();
			JScrollPane listOfScholars = new JScrollPane(listOfScholarNames);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(jbtOK);
			buttonPanel.add(jbtCancel);
			buttonPanel.setAlignmentX(RIGHT_ALIGNMENT);
			
			add(scholarsLabel, BorderLayout.NORTH);
			add(listOfScholars);
			add(buttonPanel, BorderLayout.SOUTH);
			jbtOK.addActionListener(new PlotGUIjbtOKListener());
			
			setSize(400,300);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class PlotGUIjbtOKListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				thisScholarPanel.setVisible(false);
				scholarIndex = listOfScholarNames.getSelectedIndex();
				selectedScholar = model.getScholar(scholarIndex);
				buttonPressed = true;
				updatePlot();
				buttonPressed = false;
			}
		}
	}
	
	private class PublicationTypePanel extends JFrame{
		private JLabel conferencePapersLabel = new JLabel("Conference Papers (" + numberOfConferencePapers + ")");
		private JLabel journalArticlesLabel = new JLabel("Journal Articles (" + numberOfJournalArticles + ")");
		private ArrayList<JLabel> labels = new ArrayList<JLabel>();
		private ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		public PublicationTypePanel(){
			thisOptionClass = this;
			this.addWindowListener(new LocalWindowListener());
			labels.add(conferencePapersLabel);
			labels.add(journalArticlesLabel);
			numbers.add(numberOfConferencePapers);
			numbers.add(numberOfJournalArticles);
			
			setLayout(new GridLayout(2,2,5,5));
			
			for(int index = 0; index < labels.size(); ++index){
				add(labels.get(index));
				BarPanel barGraph = new BarPanel(numbers.get(index));
				add(barGraph);
				
			}
			
			setSize(600,500);
			if(previousScreenLocation == null){
				setLocationRelativeTo(null);
			}
			else{
				setLocation(previousScreenLocation);
			}
			setVisible(true);
			
		}
		
		private class BarPanel extends JPanel{
			int width = 0;
			BarPanel(int width){
				this.width = width;
			}
			
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 65, width * 10, 100);
				
			}
		}
	}
	
	
	private class PublicationPerYearPanel extends JFrame{
		public PublicationPerYearPanel(){
			thisOptionClass = this;
			this.addWindowListener(new LocalWindowListener());
			setSize(400,300);
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			for(int firstIndex = 0; firstIndex < yearStrings.size(); ++firstIndex){
				int counter = 0;
				for(int secondIndex = 0; secondIndex < publishedPapers.size(); ++secondIndex){
					if(yearStrings.get(firstIndex).equals(publishedPapers.get(secondIndex).getYear())){
						++counter;
					}
				}
				JLabel label = new JLabel(yearStrings.get(firstIndex) + " (" + counter + ")");
				mainPanel.add(label);
				BarPanel panel = new BarPanel(counter);
				mainPanel.add(panel);
			}
			add(mainPanel);
			
			setSize(600,500);
			if(previousScreenLocation == null){
				setLocationRelativeTo(null);
			}
			else{
				setLocation(previousScreenLocation);
			}
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	private class ConferencePaperPerYearPanel extends JFrame{
		public ConferencePaperPerYearPanel(){
			thisOptionClass = this;
			this.addWindowListener(new LocalWindowListener());
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			for(int firstIndex = 0; firstIndex < yearStrings.size(); ++firstIndex){
				int counter = 0;
				for(int secondIndex = 0; secondIndex < publishedPapers.size(); ++secondIndex){
					if((yearStrings.get(firstIndex).equals(publishedPapers.get(secondIndex).getYear())) && (publishedPapers.get(secondIndex).getClass() == ConferencePaper.class)){
						++counter;
					}
				}
				JLabel label = new JLabel(yearStrings.get(firstIndex) + " (" + counter + ")");
				mainPanel.add(label);
				BarPanel panel = new BarPanel(counter);
				mainPanel.add(panel);
			}
			add(mainPanel);
			
			setSize(600,500);
			if(previousScreenLocation == null){
				setLocationRelativeTo(null);
			}
			else{
				setLocation(previousScreenLocation);
			}
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	private class JournalArticlesPerYearPanel extends JFrame{
		public JournalArticlesPerYearPanel(){
			thisOptionClass = this;
			this.addWindowListener(new LocalWindowListener());
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			
			for(int firstIndex = 0; firstIndex < yearStrings.size(); ++firstIndex){
				int counter = 0;
				for(int secondIndex = 0; secondIndex < publishedPapers.size(); ++secondIndex){
					if((yearStrings.get(firstIndex).equals(publishedPapers.get(secondIndex).getYear())) && (publishedPapers.get(secondIndex).getClass() == JournalPaper.class)){
						++counter;
					}
				}
				JLabel label = new JLabel(yearStrings.get(firstIndex) + " (" + counter + ")");
				mainPanel.add(label);
				BarPanel panel = new BarPanel(counter);
				mainPanel.add(panel);
			}
			add(mainPanel);
			
			setSize(600,500);
			if(previousScreenLocation == null){
				setLocationRelativeTo(null);
			}
			else{
				setLocation(previousScreenLocation);
			}
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	private class CoAuthorsPerPublicationPanel extends JFrame{
		public CoAuthorsPerPublicationPanel(){
			thisOptionClass = this;
			this.addWindowListener(new LocalWindowListener());
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			if(previousScreenLocation == null){
				setLocationRelativeTo(null);
			}
			else{
				setLocation(previousScreenLocation);
			}
			for(int index = 0; index < publishedPapers.size(); ++index){
				int data = (publishedPapers.get(index).getListOfScholars()).size() - 1;
				JLabel label = new JLabel(publishedPapers.get(index).getTitleOfPaper() + " (" + data + ")");
				mainPanel.add(label);
				mainPanel.add(new BarPanel(data));
			}
			add(mainPanel);
			setSize(400,300);
			setVisible(true);

		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	/**Keeps the list of Scholar names in sync with the core view.
	 * 
	 */
	public void updateList(){
		listOfScholarNames.setListData(model.getScholarNames());
		listOfScholarNames.setSelectedIndex(-1);
	}
	
	/**Updates the plot with the current data for the chosen scholar.
	 * 
	 */
	public void updatePlot(){
		if(displayOption == 1){
			publishedPapers = model.getPapersForAuthor(selectedScholar);

		}else if(displayOption == 2){
			publishedPapers = selectedScholar.getPapers();
		}
		numberOfConferencePapers = 0;
		numberOfJournalArticles = 0;
		if((thisOptionClass != null) || buttonPressed){
			switch(option){
			case "Type Of Publication":
				//Gather information
				if(thisOptionClass != null){
					previousScreenLocation = ((PublicationTypePanel)(thisOptionClass)).getLocationOnScreen();
					((PublicationTypePanel)(thisOptionClass)).dispose();
				}
				for(int index = 0; index < publishedPapers.size(); ++index){
	
					if(publishedPapers.get(index).getClass() == ConferencePaper.class){
						++numberOfConferencePapers;
					}else{
						++numberOfJournalArticles;
					}
				}
				PublicationTypePanel publicationTypePanel = new PublicationTypePanel();
				publicationTypePanel.setTitle("Type of Publicaton");
				break;
				
			case "Publications Per Year":
				//Gather Information
				if(thisOptionClass != null){
					previousScreenLocation = ((PublicationPerYearPanel)(thisOptionClass)).getLocationOnScreen();
					((PublicationPerYearPanel)(thisOptionClass)).dispose();
				}
				for(int index = 0; index < publishedPapers.size(); ++index){
					String year = publishedPapers.get(index).getYear();
					yearStrings.add(year);
				}
				Set<String> set1 = new HashSet<String>(yearStrings); // get rid of duplicates
				yearStrings = new ArrayList<String>(set1);
				
				PublicationPerYearPanel publicationPerYearPanel = new PublicationPerYearPanel();
				publicationPerYearPanel.setTitle("Publications Per Year");
				break;
				
			case "Conference Papers Per Year":
				if(thisOptionClass != null){
					previousScreenLocation = ((ConferencePaperPerYearPanel)(thisOptionClass)).getLocationOnScreen();
					((ConferencePaperPerYearPanel)(thisOptionClass)).dispose();
				}
				for(int index = 0; index < publishedPapers.size(); ++index){
					if(publishedPapers.get(index).getClass() == ConferencePaper.class){
						String year = publishedPapers.get(index).getYear();
						yearStrings.add(year);
					}
					
				}
				Set<String> set2 = new HashSet<String>(yearStrings); // get rid of duplicates
				yearStrings = new ArrayList<String>(set2);
				
				ConferencePaperPerYearPanel conferencePaperPerYearPanel = new ConferencePaperPerYearPanel();
				conferencePaperPerYearPanel.setTitle("Conference Papers Per Year");
				break;
				
			case "Journal Articles Per Year":
				if(thisOptionClass != null){
					previousScreenLocation = ((JournalArticlesPerYearPanel)(thisOptionClass)).getLocationOnScreen();
					((JournalArticlesPerYearPanel)(thisOptionClass)).dispose();
				}
				for(int index = 0; index < publishedPapers.size(); ++index){
					if(publishedPapers.get(index).getClass() == JournalPaper.class){
						String year = publishedPapers.get(index).getYear();
						yearStrings.add(year);
					}
					
				}
				Set<String> set3 = new HashSet<String>(yearStrings); // get rid of duplicates
				yearStrings = new ArrayList<String>(set3);
				
				JournalArticlesPerYearPanel journalArticlesPerYearPanel = new JournalArticlesPerYearPanel();
				journalArticlesPerYearPanel.setTitle("Journal Articles Per Year");
				break;
				
			case "Number of Co-Authors Per Publication":
				if(thisOptionClass != null){
					previousScreenLocation = ((CoAuthorsPerPublicationPanel)(thisOptionClass)).getLocationOnScreen();
					((CoAuthorsPerPublicationPanel)(thisOptionClass)).dispose();
				}
				CoAuthorsPerPublicationPanel coAuthorsPerPublicationPanel = new CoAuthorsPerPublicationPanel();
				coAuthorsPerPublicationPanel.setTitle("Co-Authors Per Publication");
				break;
			}
		}
	}
	
	/**Removes the PlotGUI window and all attachments to it.
	 * 
	 */
	public void destroy(){
		plotGUIS.remove(thisGUI);
		if(thisScholarPanel != null){
			thisScholarPanel.dispose();
		}
		if(thisOptionClass != null){
			switch(option){
				case "Type Of Publication":
					((PublicationTypePanel)(thisOptionClass)).dispose();
					break;
					
				case "Publications Per Year":
					((PublicationPerYearPanel)(thisOptionClass)).dispose();
					break;
					
				case "Conference Papers Per Year":
					((ConferencePaperPerYearPanel)(thisOptionClass)).dispose();
					break;
					
				case "Journal Articles Per Year":
					((JournalArticlesPerYearPanel)(thisOptionClass)).dispose();
					break;
					
				case "Number of Co-Authors Per Publication":
					((CoAuthorsPerPublicationPanel)(thisOptionClass)).dispose();
					break;
			}
		}
		thisGUI = null;
	}
}
