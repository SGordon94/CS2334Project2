import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SelectionView extends JFrame {
	/**
	 * 
	 */
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
	
	public SelectionView(){
		listOfScholars = new JList();
		listOfSerials = new JList();
		listOfPapers = new JList();
		
		setTitle("ScholarPub");
		
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
		
		setSize(1200,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public synchronized void updateScholarList(){
		listOfScholars.setListData(model.getScholarNames());
	}
	
	public synchronized void updateSerialList(){
		listOfSerials.setListData(model.getSerialTitles());
	}
	
	public synchronized void updatePaperList(){
		listOfPapers.setListData(model.getPaperNames());
	}
	
	public int getScholarListPosition(){
		return listOfScholars.getSelectedIndex();
	}
	
	public void setModel(ScholarshipModel mod){
		this.model = mod;
	}
	
	public JButton getJBTAddScholar(){
		return jbtAddScholar;
	}
	
	public JButton getJBTDeleteScholars(){
		return jbtDeleteScholars;
	}
	
	public JButton getJBTDeleteAllScholars(){
		return jbtDeleteAllScholars;
	}
	
	public JButton getJBTAddSerial(){
		return jbtAddSerial;
	}
	
	public JButton getJBTDeleteSerials(){
		return jbtDeleteSerials;
	}
	
	public JButton getJBTDeleteAllSerials(){
		return jbtDeleteAllSerials;
	}
	
	public JButton getJBTAddPaper(){
		return jbtAddPaper;
	}
	
	public JButton getJBTDeletePapers(){
		return jbtDeletePapers;
	}
	
	public JButton getJBTDeleteAllPapers(){
		return jbtDeleteAllPapers;
	}
}