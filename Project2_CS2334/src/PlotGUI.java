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
import java.util.ArrayList;


public class PlotGUI {
	ScholarshipModel plotModel;
	Scholar selectedScholar;
	ArrayList<Paper> publishedPapers = new ArrayList<Paper>();
	int scholarIndex;
	String option;
	int numberOfConferencePapers = 0;
	int numberOfJournalArticles = 0;
	public PlotGUI(ScholarshipModel model, String option){
		this.plotModel = model;
		this.option = option;
		SelectScholarPanel scholarPanel = new SelectScholarPanel();
		
		
	}
	
	private class SelectScholarPanel extends JFrame{
		private JButton jbtOK = new JButton("OK");
		private JButton jbtCancel = new JButton("Cancel");
		private JList listOfScholarNames;
		
		public SelectScholarPanel(){
			setTitle("Scholars");
			setLayout(new BorderLayout());
			
			JLabel scholarsLabel = new JLabel("Scholars");
			listOfScholarNames = new JList(plotModel.getScholarNames());
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
				selectedScholar = plotModel.getScholar(scholarIndex);
				publishedPapers = plotModel.getPapersForAuthor(selectedScholar);
				System.out.println(publishedPapers.get(0).getTitleOfPaper());
				//System.out.println(publishedPapers.get(0).getTypeOfPaper());


				
				for(int index = 0; index < publishedPapers.size(); ++index){
					if(publishedPapers.get(index).isConference()){
						System.out.print("Conference Paper");
						++numberOfConferencePapers;
					}else{
						System.out.print("Journal Articles");
						++numberOfJournalArticles;
					}
				}
				
				switch (option){
					case "Type Of Publication":
						PublicationTypePanel publicationTypePanel = new PublicationTypePanel();
						publicationTypePanel.setName("Type of Publicaton");
						break;
					case "Publications Per Year":
						PublicationPerYear publicationPerYear = new PublicationPerYear();
						break;
					case "Conference Papers Per Year":
						ConferencePaperPerYearPanel conferencePaperPerYearPanel = new ConferencePaperPerYearPanel();
						break;
					case "Journal Articles Per Year":
						JournalArticlesPerYearPanel journalArticlesPerYearPanel = new JournalArticlesPerYearPanel();
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
	
	
	private class PublicationPerYear extends JFrame{
		public PublicationPerYear(){
			this.setName("Publicatons Per Year");
			this.getContentPane().setName("Publications Per Year");
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
	
	private class ConferencePaperPerYearPanel extends JFrame{
		public ConferencePaperPerYearPanel(){
			setName("Conference Papers Per Year");
			setSize(800,600);
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
	
	private class JournalArticlesPerYearPanel extends JFrame{
		public JournalArticlesPerYearPanel(){
			setName("Journal Articles Per Year");
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
}
