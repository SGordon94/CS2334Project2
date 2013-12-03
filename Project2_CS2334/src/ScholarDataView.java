import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;


public class ScholarDataView extends JFrame{
	private String nameLabel = "Name: ";
	private String affiliationsLabel = "Institutional Affiliations: ";
	private String researchLabel = "Research Areas: ";
	private String publicationsLabel = "Publications: ";
	private String chairLabel = "Conferences attended as Program Chair: ";
	private String memberLabel = "Conferences attended as Program Committee Member: ";
	private String editedLabel = "Edited Journals: ";
	private String reviewedLabel = "Reviewed Journals: ";
	JButton jbtOK = new JButton("OK");
	
	public ScholarDataView(Scholar scholar){
		setTitle(scholar.returnNameInStringAlt());
		setLayout(new BorderLayout());
		
		String name = scholar.returnNameInStringAlt();
		
		String affiliations = "";
		for(int i = 0; i < scholar.getAffiliations().size(); ++i){
			affiliations += scholar.getAffiliations().get(i) + "\n";
		}
		
		String researchAreas = "";
		for(int i = 0; i < scholar.getResearchAreas().size(); ++i){
			researchAreas += scholar.getResearchAreas().get(i) + "\n";
		}
		
		String publications = scholar.displayPapers();
		
		String chairData = "";
		
		String memberData = "";
		
		String editedData = "";
		
		String reviewedData = "";
		
		JTextArea mainTextArea = new JTextArea(nameLabel + name + "\n\n" + affiliationsLabel + "\n" + affiliations + "\n" + researchLabel + "\n" + researchAreas
				+ "\n" + publicationsLabel + "\n" + publications + "\n" + chairLabel + "\n" + chairData + "\n" + memberLabel + "\n" + memberData + "\n" + editedLabel + "\n" +
				editedData + "\n" + reviewedLabel + "\n" + reviewedData);
		
		mainTextArea.setEditable(false);
		
		add(mainTextArea);
		
		jbtOK.setAlignmentX(CENTER_ALIGNMENT);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public JButton getJBTOK(){
		return jbtOK;
	}

}
