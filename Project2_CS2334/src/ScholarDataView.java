import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ScholarDataView extends JFrame{
	JLabel nameLabel = new JLabel("Name: ");
	JLabel affiliationsLabel = new JLabel("Institutional Affiliations: ");
	JLabel researchLabel = new JLabel("Research Areas: ");
	JLabel publicationsLabel = new JLabel("Publications: ");
	JLabel chairLabel = new JLabel("Conferences attended as Program Chair: ");
	JLabel memberLabel = new JLabel("Conferences attended as Program Committee Member: ");
	JLabel editedLabel = new JLabel("Edited Journals: ");
	JLabel reviewedLabel = new JLabel("Reviewed Journals: ");
	JButton jbtOK = new JButton("OK");
	
	public ScholarDataView(Scholar scholar){
		setTitle(scholar.returnNameInStringAlt());
		setLayout(new BorderLayout());
		
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
		mainPanel.setLayout(new GridLayout(8,2,5,5));
		
		mainPanel.add(nameLabel);
		mainPanel.add(name);
		mainPanel.add(affiliationsLabel);
		mainPanel.add(affiliationsData);
		mainPanel.add(researchLabel);
		mainPanel.add(researchData);
		mainPanel.add(publicationsLabel);
		mainPanel.add(publicationsData);
		mainPanel.add(chairLabel);
		mainPanel.add(chairData);
		mainPanel.add(memberLabel);
		mainPanel.add(memberData);
		mainPanel.add(editedLabel);
		add(editedData);
		add(reviewedLabel);
		add(reviewedData);
		
		
		
		jbtOK.setAlignmentX(CENTER_ALIGNMENT);
		jbtOK.addActionListener(new jbtOKActionListener());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
		
		
		add(mainPanel);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(800,500);
		setLocationRelativeTo(null);
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
