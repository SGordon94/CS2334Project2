import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConferencePaperDataView extends JFrame{
	String authorsLabel = "Authors: ";
	String paperTitleLabel = "Title: ";
	String conferencePaperReference = "Conference: ";
	String pageNumbers = "Page Numbers: ";
	String digitalObjectIdentifierLabel = "Digital Object Identifier: ";
	JButton jbtOK = new JButton("OK");
	ArrayList<Paper> openWindowPapers;
	ArrayList<ConferencePaperDataView> paperWindows;
	ConferencePaper usedPaper;
	ScholarshipModel model;

	public ConferencePaperDataView(ConferencePaper paper, ArrayList<Paper> openWindows, ArrayList<ConferencePaperDataView> windows, ScholarshipModel model){
		usedPaper = paper;
		openWindowPapers = openWindows;
		openWindowPapers.add(usedPaper);
		paperWindows = windows;
		paperWindows.add(this);
		
		setTitle(paper.getTitleOfPaper());
		String digitalObjectIdentifier = "";
		
		if(paper.getDigitalObjectIdentifier() != null){
			digitalObjectIdentifier = paper.getDigitalObjectIdentifier();
		}else{
			digitalObjectIdentifier = "N/A";
		}
		
		JTextArea mainTextArea = new JTextArea(paperTitleLabel + paper.getTitleOfPaper() + "\n" + authorsLabel + paper.getAuthors() + "\n"
				+ conferencePaperReference + paper.getTitleOfSerial() + "\n" + pageNumbers + paper.getNumbers() + "\n"
				+ digitalObjectIdentifierLabel + digitalObjectIdentifier);
		mainTextArea.setEditable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(mainTextArea);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbtOK);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(mainPanel);
		add(buttonPanel);
		
		setSize(400,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void windowIsClosing(){
		openWindowPapers.remove(usedPaper);
		paperWindows.remove(this);
	}
	
	public ConferencePaper getUsedPaper(){
		return usedPaper;
	}
	
	public JButton getJBTOK(){
		return jbtOK;
	}
}
