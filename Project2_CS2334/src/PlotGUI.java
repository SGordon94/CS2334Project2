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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class PlotGUI {
	ScholarshipModel model;
	Scholar selectedScholar;
	ArrayList<Paper> publishedPapers = new ArrayList<Paper>();
	int scholarIndex;
	String option;
	int numberOfConferencePapers = 0;
	int numberOfJournalArticles = 0;
	
	private ArrayList<String> yearStrings = new ArrayList<String>();
	private ArrayList<Integer> data = new ArrayList<Integer>();
	private ArrayList<Paper> conferencePapers = new ArrayList<Paper>();
	private ArrayList<Paper> journalArticles = new ArrayList<Paper>();
	private ArrayList<PlotGUI> plotGUIS = new ArrayList<PlotGUI>();
	private JList listOfScholarNames = new JList();;
	private PlotGUI thisGUI;
	
	
	public PlotGUI(ScholarshipModel model, String option, ArrayList<PlotGUI> GUIS){
		this.model = model;
		this.option = option;
		SelectScholarPanel scholarPanel = new SelectScholarPanel();
		this.plotGUIS = GUIS;
		this.plotGUIS.add(this);
		this.thisGUI = this;
	}
	
	private class SelectScholarWindowListener implements WindowListener{
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			plotGUIS.remove(thisGUI);
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
			this.addWindowListener(new SelectScholarWindowListener());
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				scholarIndex = listOfScholarNames.getSelectedIndex();
				selectedScholar = model.getScholar(scholarIndex);
				publishedPapers = model.getPapersForAuthor(selectedScholar);
				switch (option){
					case "Type Of Publication":
						//Gather information
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
						CoAuthorsPerPublicationPanel coAuthorsPerPublicationPanel = new CoAuthorsPerPublicationPanel();
						break;
				}
			}
		}
	}
	
	private class PublicationTypePanel extends JFrame{
		private JLabel conferencePapersLabel = new JLabel("Conference Papers (" + numberOfConferencePapers + ")");
		private JLabel journalArticlesLabel = new JLabel("Journal Articles (" + numberOfJournalArticles + ")");
		private ArrayList<JLabel> labels = new ArrayList<JLabel>();
		private ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		public PublicationTypePanel(){
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
			setLocationRelativeTo(null);
			setVisible(true);
			
		}
		
		private class BarPanel extends JPanel{
			int width = 0;
			BarPanel(int width){
				this.width = width;
			}
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 65, width * 10, 100);
				
			}
		}
	}
	
	
	private class PublicationPerYearPanel extends JFrame{

		
		public PublicationPerYearPanel(){
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
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	private class ConferencePaperPerYearPanel extends JFrame{
		public ConferencePaperPerYearPanel(){
			
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
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	private class JournalArticlesPerYearPanel extends JFrame{
		public JournalArticlesPerYearPanel(){
			
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
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			int width;
			BarPanel(int width){
				this.width = width;
			}
			
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, width * 10, 100);
			}
		}
	}
	
	private class CoAuthorsPerPublicationPanel extends JFrame{
		public CoAuthorsPerPublicationPanel(){
			setName("Number of Co-Authors Per Publication");
			setSize(400,300);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class BarPanel extends JPanel{
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				
			}
		}
	}
	
	public void updateList(){
		listOfScholarNames.setListData(model.getScholarNames());
		listOfScholarNames.setSelectedIndex(-1);
	}
}
