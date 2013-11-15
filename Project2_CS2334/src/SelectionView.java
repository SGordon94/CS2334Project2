import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectionView extends JFrame implements ActionListener {
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
	
	

	JList listOfScholars = new JList();
	

	JList listOfSerials = new JList();
	

	JList listOfPapers = new JList();
	
	public SelectionView(){
		setTitle("ScholarPub");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,3,5,5));
		
		//
		JPanel scholarsPanel = new JPanel();
		scholarsPanel.setLayout(new BorderLayout());
		
		scholarsPanel.add(new JLabel("Scholars List"), BorderLayout.NORTH);
		
		scholarsPanel.add(new List(), BorderLayout.CENTER);
		
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
		
		serialsPanel.add(new List(), BorderLayout.CENTER);
		
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
		
		papersPanel.add(new JList(), BorderLayout.CENTER);
		
		JPanel papersButtonPanel = new JPanel();
		papersButtonPanel.setLayout(new GridLayout(2,2,2,3));
		papersButtonPanel.add(jbtAddPaper);
		papersButtonPanel.add(jbtDeletePapers);
		papersButtonPanel.add(jbtDeleteAllPapers);
		
		papersPanel.add(papersButtonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(scholarsPanel);
		
		mainPanel.add(serialsPanel);
		
		mainPanel.add(papersPanel);
		
		add(mainPanel);
		
		setSize(1200,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void setModel(ScholarshipModel mod){
		this.model = mod;
	}
}
