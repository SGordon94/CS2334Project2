import javax.swing.*;
import java.awt.*;

public class AddPaperView extends JFrame{
	private JLabel paperTypeLabel = new JLabel(" Paper Type (Conference Paper/Journal Paper): ");
	private JTextField paperType = new JTextField();
	private JLabel paperNameLabel = new JLabel("Name: ");
	private JTextField paperName = new JTextField();
	private JLabel conferencePagesLabel = new JLabel("Page Numbers for Conference Paper: ");
	private JTextField conferencePages = new JTextField();
	private JLabel journalNumbersLabel = new JLabel("Volume(Issue) :Page Numbers");
	private JTextField journalNumbers = new JTextField();
	private JLabel publicationMonthLabel = new JLabel("Month of Publication: ");
	private JTextField publicationMonth = new JTextField();
	private JLabel publicationYearLabel = new JLabel("Year of Publication: ");
	private JTextField publicationYear = new JTextField();
	private JLabel scholarsListLabel = new JLabel("List of Scholars");
	private JList scholarList = new JList();
	private JLabel serialsListLabel = new JLabel("List of Serials");
	private JList serialsList = new JList();
}
