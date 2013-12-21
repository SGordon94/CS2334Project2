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


public class PlotGUI {
	ScholarshipModel plotModel;
	Scholar selectedScholar;
	int scholarIndex;
	String option;
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
				
				switch (option){
					case "Type Of Publication":
						PublicationTypePanel publicationTypePanel = new PublicationTypePanel();
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
		private JLabel conferencePapersLabel = new JLabel("Conference Papers ");
		private JLabel journalArticlesLabel = new JLabel("Journal Articles ");
		
		public PublicationTypePanel(){
			
			int numberOfConferencePapers = 0;
			int numberOfJournalArticles = 0;
			for(int index = 0; index < selectedScholar.getPapers().size(); ++index){
				if(selectedScholar.getPapers().get(index).getTypeOfPaper() == "Conference Paper"){
					++numberOfConferencePapers;
				}else{
					++numberOfJournalArticles;
				}
			}
			
			setLayout(new GridLayout(2,2,0,0));
			MainPanel mainPanel = new MainPanel();
			add(conferencePapersLabel);
			add(mainPanel);
			add(journalArticlesLabel);
			setName("Type Of Publication");
			setSize(400,300);
			setLocationRelativeTo(null);
			setVisible(true);
			
		}
		
		private class MainPanel extends JPanel{
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.fillRect(0, 0, 100, 50);
				
			}
		}
	}
	
	
	private class PublicationPerYear extends JFrame{
		public PublicationPerYear(){
			setName("Publicatons Per Year");
			setSize(400,300);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class mainPanel extends JPanel{
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
			}
		}
	}
	
	private class ConferencePaperPerYearPanel extends JFrame{
		public ConferencePaperPerYearPanel(){
			setName("Conference Papers Per Year");
			setSize(400,300);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private class mainPanel extends JPanel{
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
		
		private class mainPanel extends JPanel{
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
		
		private class mainPanel extends JPanel{
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				
				
			}
		}
	}
}
