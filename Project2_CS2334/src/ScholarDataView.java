import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ScholarDataView extends JFrame{
	JLabel nameLabel = new JLabel("Name: ");
	JLabel affiliationsLabel = new JLabel("Institutional Affiliations: ");
	JLabel researchLabel = new JLabel("Research Areas: ");
	JLabel publicationsLabel = new JLabel("Publications: ");
	JLabel chairLabel = new JLabel("Conferences attended as\nProgram Chair: ");
	JLabel memberLabel = new JLabel("Conferences attended as\nProgram Committee Member: ");
	JLabel editedLabel = new JLabel("Edited Journals: ");
	JLabel reviewedLabel = new JLabel("Reviewed Journals: ");
	JButton jbtOK = new JButton("OK");
	
	public ScholarDataView(Scholar scholar){
		setTitle(scholar.returnNameInStringAlt());
		
		JLabel name = new JLabel(scholar.returnNameInStringAlt());
		
		String affiliations = "";
		for(int i = 0; i < scholar.getAffiliations().size(); ++i){
			affiliations += scholar.getAffiliations().get(i) + "\n";
		}
		JLabel affiliationsData = new JLabel(affiliations);
		
		String researchAreas = "";
		for(int i = 0; i < scholar.getResearchAreas().size(); ++i){
			researchAreas += scholar.getResearchAreas().get(i);
		}
		JLabel researchData = new JLabel(researchAreas);
		
		JLabel publicationsData = new JLabel(scholar.displayPapers());
		
		JLabel chairData = new JLabel();
		
		JLabel memberData = new JLabel();
		
		JLabel editedData = new JLabel();
		
		JLabel reviewedData = new JLabel();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(name);
		
		JPanel affiliationsPanel = new JPanel();
		affiliationsPanel.add(affiliationsLabel);
		affiliationsPanel.add(affiliationsData);
		
		JPanel researchPanel = new JPanel();
		researchPanel.add(researchLabel);
		researchPanel.add(researchData);
		
		JPanel publicationsPanel = new JPanel();
		publicationsPanel.add(publicationsLabel);
		publicationsPanel.add(publicationsData);
		
		JPanel chairPanel = new JPanel();
		chairPanel.add(chairLabel);
		chairPanel.add(chairData);
		
		JPanel memberPanel = new JPanel();
		memberPanel.add(memberLabel);
		memberPanel.add(memberData);
		
		JPanel editorPanel = new JPanel();
		editorPanel.add(editedLabel);
		editorPanel.add(editedData);
		
		JPanel reviewerPanel = new JPanel();
		reviewerPanel.add(reviewedLabel);
		reviewerPanel.add(reviewedData);
		
		mainPanel.add(namePanel);
		mainPanel.add(affiliationsPanel);
		mainPanel.add(publicationsPanel);
		mainPanel.add(chairPanel);
		mainPanel.add(memberPanel);
		mainPanel.add(editorPanel);
		mainPanel.add(reviewerPanel);
		jbtOK.setAlignmentX(CENTER_ALIGNMENT);
		jbtOK.addActionListener(new jbtOKActionListener());
		mainPanel.add(jbtOK);
		
		add(mainPanel);
		
		setSize(400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	private class jbtOKActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dispose();
		}
	}

}
