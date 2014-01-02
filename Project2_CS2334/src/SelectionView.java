import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**The core view of the program. All other GUI windows are accessed through
 * this view.
 * 
 * @version 1.0
 */
public class SelectionView extends JFrame {
	private static final long serialVersionUID = 8190094167216851323L;
	private ScholarshipModel model;
	private JButton jbtAddScholar = new JButton("Add Scholar");
	private JButton jbtDeleteScholars = new JButton("Delete Selected Scholar(s)");
	private JButton jbtDeleteAllScholars = new JButton("Delete All Scholars");
	
	private JButton jbtAddSerial = new JButton("Add Serial");
	private JButton jbtDeleteSerials = new JButton("Delete Selected Serial(s)");
	private JButton jbtDeleteAllSerials = new JButton("Delete All Serials");
	
	private JButton jbtAddPaper = new JButton("Add Paper");
	private JButton jbtDeletePapers = new JButton("Delete Selected Papers(s)");
	private JButton jbtDeleteAllPapers = new JButton("Delete All Papers");
	
	private JList listOfScholars;
	private JList listOfSerials;
	private JList listOfPapers;
	
	//Menu Items
	private JMenuItem loadOption = new JMenuItem("Load");
	private JMenuItem saveOption = new JMenuItem("Save");
	private JMenuItem closeOption = new JMenuItem("Close");
	
	private JMenuItem typeOfPublication = new JMenuItem("Type of Publication");
	private JMenuItem publicationsPerYear = new JMenuItem("Publications Per Year");
	private JMenuItem conferencePapersPerYear = new JMenuItem("Conference Papers Per Year");
	private JMenuItem journalArticlesPerYear = new JMenuItem("Journal Articles Per Year");
	private JMenuItem numberOfCoauthorsPerPublication = new JMenuItem("Number of Co-Authors Per Publication");
	
	/**The constructor for the core view.
	 * 
	 */
	public SelectionView(){
		listOfScholars = new JList();
		listOfSerials = new JList();
		listOfPapers = new JList();
		listOfScholars.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listOfSerials.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listOfPapers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		setTitle("ScholarPub");
		
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		
		//Menus
		JMenu fileOption = new JMenu("File");
		JMenu graphOption = new JMenu("Plot");
		
		//Add items to menu
		fileOption.add(loadOption);
		fileOption.add(saveOption);
		fileOption.add(closeOption);
		
		graphOption.add(typeOfPublication);
		graphOption.add(publicationsPerYear);
		graphOption.add(conferencePapersPerYear);
		graphOption.add(journalArticlesPerYear);
		graphOption.add(numberOfCoauthorsPerPublication);
		
		//Add menus to bar
		menuBar.add(fileOption);
		menuBar.add(graphOption);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,3,5,5));
		
		//
		JPanel scholarsPanel = new JPanel();
		scholarsPanel.setLayout(new BorderLayout());
		
		scholarsPanel.add(new JLabel("Scholars List"), BorderLayout.NORTH);
		
		scholarsPanel.add(new JScrollPane(listOfScholars), BorderLayout.CENTER);
		
		JPanel scholarsButtonPanel = new JPanel();
		scholarsButtonPanel.setLayout(new GridLayout(2,2,2,3));
		scholarsButtonPanel.add(jbtAddScholar);
		scholarsButtonPanel.add(jbtDeleteScholars);
		scholarsButtonPanel.add(jbtDeleteAllScholars);
		
		scholarsPanel.add(scholarsButtonPanel, BorderLayout.SOUTH);
		//
		
		//
		JPanel serialsPanel = new JPanel();
		serialsPanel.setLayout(new BorderLayout());
		
		serialsPanel.add(new JLabel("Serials List"), BorderLayout.NORTH);
		
		serialsPanel.add(new JScrollPane(listOfSerials), BorderLayout.CENTER);
		
		JPanel serialsButtonPanel = new JPanel();
		serialsButtonPanel.setLayout(new GridLayout(2,2,2,3));
		serialsButtonPanel.add(jbtAddSerial);
		serialsButtonPanel.add(jbtDeleteSerials);
		serialsButtonPanel.add(jbtDeleteAllSerials);
		
		serialsPanel.add(serialsButtonPanel, BorderLayout.SOUTH);
		//
		
		//
		JPanel papersPanel = new JPanel();
		papersPanel.setLayout(new BorderLayout());
		
		papersPanel.add(new JLabel("Papers List"), BorderLayout.NORTH);
		
		papersPanel.add(new JScrollPane(listOfPapers), BorderLayout.CENTER);
		
		JPanel papersButtonPanel = new JPanel();
		papersButtonPanel.setLayout(new GridLayout(2,2,2,3));
		papersButtonPanel.add(jbtAddPaper);
		papersButtonPanel.add(jbtDeletePapers);
		papersButtonPanel.add(jbtDeleteAllPapers);
		
		papersPanel.add(papersButtonPanel, BorderLayout.SOUTH);
		
		setJMenuBar(menuBar);
		
		mainPanel.add(scholarsPanel);
		
		mainPanel.add(serialsPanel);
		
		mainPanel.add(papersPanel);
		
		add(mainPanel);
		
		jbtDeleteScholars.setEnabled(false);
		jbtDeleteAllScholars.setEnabled(false);
		jbtAddSerial.setEnabled(false);
		jbtDeleteSerials.setEnabled(false);
		jbtDeleteAllSerials.setEnabled(false);
		jbtAddPaper.setEnabled(false);
		jbtDeletePapers.setEnabled(false);
		jbtDeleteAllPapers.setEnabled(false);
		saveOption.setEnabled(false);
		
		setSize(1200,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**Updates the list of Scholars with the current data from the model.
	 * 
	 */
	public synchronized void updateScholarList(){
		listOfScholars.setListData(model.getScholarNames());
	}
	
	/**Updates the list of Scholars with the current data from the model.
	 * Also updates the PlotGUI's list of Scholars.
	 * 
	 * @param plotGUIS the window tracker of PlotGUI windows
	 */
	public synchronized void updateScholarList(ArrayList<PlotGUI> plotGUIS){
		listOfScholars.setListData(model.getScholarNames());
		for(int i=0;i<plotGUIS.size();i++){
			plotGUIS.get(i).updateList();
		}
	}
	
	/**Updates the list of Serials with the current data from the model.
	 * 
	 */
	public synchronized void updateSerialList(){
		listOfSerials.setListData(model.getSerialTitles());
	}
	
	/**Updates the list of Papers with the current data from the model.
	 * 
	 */
	public synchronized void updatePaperList(){
		listOfPapers.setListData(model.getPaperNames());
	}
	
	/**Returns the selected positions from the list of Scholars.
	 * 
	 * @return the selected positions
	 */
	public int[] getScholarListPositions(){
		return listOfScholars.getSelectedIndices();
	}
	
	/**Returns the selected positions from the list of Serials.
	 * 
	 * @return the selected positions
	 */
	public int[] getSerialListPositions(){
		return listOfSerials.getSelectedIndices();
	}
	
	/**Returns the selected positions from the list of Papers.
	 * 
	 * @return the selected positions
	 */
	public int[] getPaperListPositions(){
		return listOfPapers.getSelectedIndices();
	}
	
	/**Sets the core view's model to the program's model.
	 * 
	 * @param mod the program's model
	 */
	public void setModel(ScholarshipModel mod){
		this.model = mod;
	}
	
	/**Sets the state of the Save Option menu item to the passed state.
	 * 
	 * @param state the state to set the Save Option menu item to
	 */
	public void setSaveOptionState(boolean state){
		saveOption.setEnabled(state);
	}
	
	/**Accessor method that returns the JButton that opens the "Add Scholar" window.
	 * 
	 * @return the "Add Scholar" JButton
	 */
	public JButton getJBTAddScholar(){
		return jbtAddScholar;
	}
	
	/**Accessor method that returns the JButton that deletes the selected Scholars.
	 * 
	 * @return the "Delete Selected Scholar(s)" JButton
	 */
	public JButton getJBTDeleteScholars(){
		return jbtDeleteScholars;
	}
	
	/**Accessor method that returns the JButton that deletes all Scholars.
	 * 
	 * @return the "Delete All Scholars" JButton
	 */
	public JButton getJBTDeleteAllScholars(){
		return jbtDeleteAllScholars;
	}
	
	/**Accessor method that returns the JButton that opens the "Add Serial" window.
	 * 
	 * @return the "Add Serial" JButton
	 */
	public JButton getJBTAddSerial(){
		return jbtAddSerial;
	}
	
	/**Accessor method that returns the JButton that deletes the selected Serials.
	 * 
	 * @return the "Delete Selected Serial(s)" JButton
	 */
	public JButton getJBTDeleteSerials(){
		return jbtDeleteSerials;
	}
	
	/**Accessor method that returns the JButton that deletes all Serials.
	 * 
	 * @return the "Delete All Serials" JButton
	 */
	public JButton getJBTDeleteAllSerials(){
		return jbtDeleteAllSerials;
	}
	
	/**Accessor method that returns the JButton that opens the "Add Paper" window.
	 * 
	 * @return the "Add Paper" JButton
	 */
	public JButton getJBTAddPaper(){
		return jbtAddPaper;
	}
	
	/**Accessor method that returns the JButton that deletes the selected Papers.
	 * 
	 * @return the "Delete Selected Paper(s)" JButton
	 */
	public JButton getJBTDeletePapers(){
		return jbtDeletePapers;
	}

	/**Accessor method that returns the JButton that deletes all Papers.
	 * 
	 * @return the "Delete All Papers" JButton
	 */
	public JButton getJBTDeleteAllPapers(){
		return jbtDeleteAllPapers;
	}
	
	/**Returns the displayed list of Scholars.
	 * 
	 * @return the JList of Scholars
	 */
	public JList getListOfScholars(){
		return listOfScholars;
	}
	
	/**Returns the displayed list of Serials.
	 * 
	 * @return the JList of Serials
	 */
	public JList getListOfSerials(){
		return listOfSerials;
	}
	
	/**Returns the displayed list of Papers.
	 * 
	 * @return the JList of Papers
	 */
	public JList getListOfPapers(){
		return listOfPapers;
	}
	
	/**Returns the Load menu option.
	 * 
	 * @return the Load menu option
	 */
	public JMenuItem getLoadOption(){
		return loadOption;
	}
	
	/**Returns the Save menu option.
	 * 
	 * @return the Save menu option
	 */
	public JMenuItem getSaveOption(){
		return saveOption;
	}
	
	/**Returns the Close menu option.
	 * 
	 * @return the Close menu option
	 */
	public JMenuItem getCloseOption(){
		return closeOption;
	}
	
	/**Returns the Type of Publication menu option.
	 * 
	 * @return the Type of Publication menu option
	 */
	public JMenuItem getTypeOfPublication(){
		return typeOfPublication;
	}
	
	/**Returns the Publications Per Year menu option.
	 * 
	 * @return the Publications Per Year menu option
	 */
	public JMenuItem getPublicationsPerYear(){
		return publicationsPerYear;
	}
	
	/**Returns the Conference Papers Per Year menu option.
	 * 
	 * @return the Conference Papers Per Year menu option
	 */
	public JMenuItem getConferencePapersPerYear(){
		return conferencePapersPerYear;
	}
	
	/**Returns the Journal Articles Per Year menu option.
	 * 
	 * @return the Journal Articles Per Year menu option
	 */
	public JMenuItem getJournalArticlesPerYear(){
		return journalArticlesPerYear;
	}
	
	/**Returns the Number of Co-Authors Per Year menu option.
	 * 
	 * @return the Number of Co-Authors Per Year menu option
	 */
	public JMenuItem getNumberOfCoauthorsPerYear(){
		return numberOfCoauthorsPerPublication;
	}
}