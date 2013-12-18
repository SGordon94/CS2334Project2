import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JournalArticleDataView extends JFrame{
	String authorsLabel = "Authors: ";
	String paperTitleLabel = "Title: ";
	String articleIssueReference = "Issue: ";
	String pageNumbers = "Page Numbers: ";
	String digitalObjectIdentifierLabel = "Digital Object Identifier: ";
	JButton jbtOK = new JButton("OK");

	public JournalArticleDataView(Paper paper){
		setTitle(paper.getTitleOfPaper());
		String digitalObjectIdentifier = "";
		
		if(paper.getDigitalObjectIdentifier() != null){
			digitalObjectIdentifier = paper.getDigitalObjectIdentifier();
		}else{
			digitalObjectIdentifier = "N/A";
		}
		
		JTextArea mainTextArea = new JTextArea(paperTitleLabel + paper.getTitleOfPaper() + "\n" + authorsLabel + paper.getAuthors() + "\n"
				+ articleIssueReference + paper.getTitleOfSerial() + "\n" + pageNumbers + paper.getNumbers() + "\n"
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
}
