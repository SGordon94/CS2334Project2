import java.awt.*;

import javax.swing.*;


public class AddScholarView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6811104289568434678L;
	
	private JLabel primaryNameIndicator = new JLabel("Last Name: ");
	private JLabel firstNameIndicator = new JLabel("First Name: ");
	private JLabel middleInitialIndicator = new JLabel("Middle Name(s): ");
	private JLabel institutionalIndicator = new JLabel("Institutional Affiliation(s): ");
	private JLabel researchIndicator = new JLabel("Research Area(s): ");
	private JTextField primaryNameData = new JTextField(" Last Name", 10);
	private JTextField firstNameData = new JTextField(" First Name", 10);
	private JTextField middleInitialData = new JTextField(" Middle Name(s)", 10);
	private JTextField institutionalData = new JTextField("Enter data here. Separate with commas.", 40);
	private JTextField researchData = new JTextField("Enter data here. Separate with commas.", 50);

	public void showAddScholarView(){
		setTitle("Add Scholar");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		
		JPanel primaryNamePanel = new JPanel();
		primaryNamePanel.add(primaryNameIndicator);
		primaryNamePanel.add(primaryNameData);
		
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.add(firstNameIndicator);
		firstNamePanel.add(firstNameData);
		
		JPanel middleInitialPanel = new JPanel();
		middleInitialPanel.add(middleInitialIndicator);
		middleInitialPanel.add(middleInitialData);
		
		JPanel namePanel = new JPanel();
		namePanel.add(primaryNamePanel);
		namePanel.add(firstNamePanel);
		namePanel.add(middleInitialPanel);
		
		JPanel institutionalDataPanel = new JPanel();
		institutionalDataPanel.add(institutionalIndicator);
		institutionalDataPanel.add(institutionalData);

		JPanel researchDataPanel = new JPanel();
		researchDataPanel.add(researchIndicator);
		researchDataPanel.add(researchData);
		
		JPanel academicsPanel = new JPanel();
		academicsPanel.setLayout(new GridLayout(2,1, 0, 0));
		academicsPanel.add(institutionalDataPanel);
		academicsPanel.add(researchDataPanel);
		
		JPanel emptyPanel1 = new JPanel();
		JPanel emptyPanel2 = new JPanel();
		
		mainPanel.add(namePanel, BorderLayout.NORTH);
		//mainPanel.add(primaryNamePanel);
		//mainPanel.add(firstNamePanel);
		//mainPanel.add(middleInitialPanel);
		mainPanel.add(academicsPanel);
		//mainPanel.add(emptyPanel1);
		//mainPanel.add(emptyPanel2);
		//mainPanel.add(researchDataPanel);
		
		add(mainPanel);
		
		setSize(700,200);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
