import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**Class that deals with the GUI for adding scholars to the program.
 * 
 * @version 1.0
 */
public class AddScholarView extends JFrame {
	private static final long serialVersionUID = -6811104289568434678L;
	
	private JLabel primaryNameIndicator = new JLabel("Last Name: ");
	private JLabel firstNameIndicator = new JLabel("First Name: ");
	private JLabel middleInitialIndicator = new JLabel("Middle Name(s): ");
	private JLabel institutionalIndicator = new JLabel("Institutional Affiliation(s): ");
	private JLabel researchIndicator = new JLabel("Research Area(s): ");
	private JTextField primaryNameData = new JTextField("", 10);
	private JTextField firstNameData = new JTextField("", 10);
	private JTextField middleInitialData = new JTextField("", 10);
	private JTextField institutionalData = new JTextField(" Separate with commas.", 40);
	private JTextField researchData = new JTextField(" Separate with commas.", 50);
	private JButton jbtAddScholar = new JButton("Add");
	private ArrayList<AddScholarView> openAddScholarWindows;

	private String meetingLabel = "Scholar Details";
	private Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	private TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder, meetingLabel,
			TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
	
	/**Sets up the contents of the "Add Scholar" window. Serves the same
	 * functional purpose as the constructor for the other "Add" classes.
	 * 
	 * @param windows the window tracker for "Add Scholar" windows
	 */
	public void showAddScholarView(ArrayList<AddScholarView> windows){
		this.openAddScholarWindows = windows;
		setTitle("Add Scholar");
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(15, 15));
		mainPanel.setBorder(titledBorder);
		
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
		researchDataPanel.add(jbtAddScholar);
		
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
		
		setSize(700,250);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**Accessor method that returns the JButton that adds the scholar to
	 * the program.
	 * 
	 * @return the "Add Scholar" JButton
	 */
	public JButton getJBTAddScholar(){
		return jbtAddScholar;
	}
	
	/** Removes this window from the window tracker ArrayList upon closing.
	 * 
	 */
	public void windowIsClosing(){
		openAddScholarWindows.remove(this);
	}
	
	/**Verifies that no text fields are left empty.
	 * 
	 * @return whether or not the fields have been filled out
	 */
	public boolean fieldsFilled(){
		if(primaryNameData.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Please input the name the Last Name.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if(firstNameData.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Please input the name the First Name.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if(institutionalData.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Please input the Institutional Affiliation(s).", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		if(researchData.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "Please input the Research Area(s).", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**Gathers the contents of the text fields into string array, and
	 * returns the created array.
	 * 
	 * @return the created string array
	 */
	public String[] getTextFields(){
		String[] fields = new String[5];
		fields[0] = primaryNameData.getText();
		fields[1] = firstNameData.getText();
		fields[2] = middleInitialData.getText();
		fields[3] = institutionalData.getText();
		fields[4] = researchData.getText();
		return fields;
	}
}
