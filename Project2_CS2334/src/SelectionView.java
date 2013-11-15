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
	
	JPanel mainPanel = new JPanel();
	LayoutManager layout1 = new GridLayout(1,3, 5, 5);
	
	JPanel scholarsPanel = new JPanel();
	JList listOfScholars = new JList();
	
	JPanel serialsPanel = new JPanel();
	JList listOfSerials = new JList();
	
	JPanel papersPanel = new JPanel();
	JList listOfPapers = new JList();
	
	public SelectionView(){
		setTitle("ScholarPub");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void setModel(ScholarshipModel mod){
		this.model = mod;
	}
}
