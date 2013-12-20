import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//REMAINING FEATURES:
	//WHENEVER A SCHOLAR IS DELETED, BROWSE THROUGH AND DELETE EMPTY SERIALS AND PAPERS
	//WHENEVER A SERIAL IS DELETED, BROWSE THROUGH AND DELETE EMPTY PAPERS
public class ScholarPubController {
	private ScholarshipModel model;
	private SelectionView mainView;
	private ArrayList<Scholar> openScholars = new ArrayList<Scholar>();
	private ArrayList<Conference> openConferences = new ArrayList<Conference>();
	private ArrayList<Journal> openJournals = new ArrayList<Journal>();
	private ArrayList<Paper> openPapers = new ArrayList<Paper>();
	private ArrayList<ScholarDataView> openScholarWindows = new ArrayList<ScholarDataView>();
	private ArrayList<ConferenceDataView> openConferenceWindows = new ArrayList<ConferenceDataView>();
	private ArrayList<JournalDataView> openJournalWindows = new ArrayList<JournalDataView>();
	
	ScholarPubController(){}
	
	public void setModel(ScholarshipModel mod){ // sets the model that the controller will use
		this.model = mod;
	}
	
	public void setView(SelectionView view){ // sets the primary view for the controller
		this.mainView = view;
	}
	
	public void setSelectionViewActionListeners(){
		if(mainView != null){
			mainView.getJBTAddScholar().addActionListener(new AddScholarListener());
			mainView.getJBTDeleteScholars().addActionListener(new DeleteScholarsListener());
			mainView.getJBTDeleteAllScholars().addActionListener(new DeleteAllScholarsListener());
			mainView.getJBTAddSerial().addActionListener(new AddSerialListener());
			mainView.getJBTDeleteSerials().addActionListener(new DeleteSerialsListener());
			mainView.getJBTDeleteAllSerials().addActionListener(new DeleteAllSerialsListener());
			mainView.getJBTAddPaper().addActionListener(new AddPaperListener());
			mainView.getJBTDeletePapers().addActionListener(new DeletePapersListener());
			mainView.getJBTDeleteAllPapers().addActionListener(new DeleteAllPapersListener());
			mainView.getListOfScholars().addListSelectionListener(new ListOfScholarsListener());
			mainView.getListOfSerials().addListSelectionListener(new ListOfSerialsListener());
			mainView.getListOfPapers().addListSelectionListener(new ListOfPapersListener());
		}
	}
	
	public void setAddScholarViewActionListeners(AddScholarView scholarView){
		if(scholarView != null){
			scholarView.getJBTAddScholar().addActionListener(new AddScholarToListListener(scholarView));
		}
	}
	
	public void setAddSerialViewActionListeners(AddSerialView serialView){
		if(serialView != null){
			serialView.getJBTAddSerial().addActionListener(new AddSerialToListListener(serialView));
			serialView.getJBTSaveMeeting().addActionListener(new SerialConferenceSaveMeetingListener(serialView));
			serialView.getJBTSaveIssue().addActionListener(new SerialJournalSaveIssueListener(serialView));
			serialView.getJBTNewVolume().addActionListener(new SerialJournalNewVolumeListener(serialView));
		}
	}
	
	private class ListOfScholarsListener implements ListSelectionListener{
		boolean newSelection = true;
		SecondMouseClickScholars doubleClick;
		MouseDraggedScholars mouseMoved;
		
		public ListOfScholarsListener(){
			doubleClick = new SecondMouseClickScholars();
			mouseMoved = new MouseDraggedScholars();
			mainView.getListOfScholars().addMouseListener(doubleClick);
			mainView.getListOfScholars().addMouseMotionListener(mouseMoved);
		}
		
		public void valueChanged(ListSelectionEvent arg0) {
			if(!arg0.getValueIsAdjusting()){
				if(newSelection){
					newSelection = false;
				}
			}
			else{
				newSelection = true;
				doubleClick.disable();
			}
		}
		
		private class SecondMouseClickScholars implements MouseListener{
			boolean clicked = false;
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {
				clicked = false;
			}
			public void mousePressed(MouseEvent arg0) {
				if(!newSelection){
					clicked = true;
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				if(clicked){
					if(!openScholars.contains(model.getScholar(mainView.getListOfScholars().getSelectedIndex()))){
						ScholarDataView scholarDataView = new ScholarDataView(model.getScholar(mainView.getListOfScholars().getSelectedIndex()), openScholars, openScholarWindows, model);
						scholarDataView.getJBTOK().addActionListener(new ScholarDataViewOKButtonListener(scholarDataView));
						scholarDataView.addWindowListener(new ScholarDataViewWindowListener(scholarDataView));
						clicked = false;
					}
					else{
						clicked = false;
					}
				}
			}
			public void disable(){
				clicked = false;
			}
		}
		
		private class MouseDraggedScholars implements MouseMotionListener{
			public void mouseDragged(MouseEvent arg0) {
				doubleClick.disable();
			}
			public void mouseMoved(MouseEvent arg0) {}
		}
	}
	
	private class ScholarDataViewWindowListener implements WindowListener{
		ScholarDataView scholarDataView;
		public ScholarDataViewWindowListener(ScholarDataView view){
			scholarDataView = view;
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			scholarDataView.windowIsClosing();
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class ScholarDataViewOKButtonListener implements ActionListener{
		ScholarDataView localDataView;
		public ScholarDataViewOKButtonListener(ScholarDataView view){
			localDataView = view;
		}
		public void actionPerformed(ActionEvent arg0) {
			localDataView.windowIsClosing();
			localDataView.dispose();
		}
	}

	private class AddScholarListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			AddScholarView addScholarView = new AddScholarView();
			addScholarView.showAddScholarView();
			setAddScholarViewActionListeners(addScholarView);
		}
	}
	
	private class DeleteScholarsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			model.removeScholars(mainView.getScholarListPositions());
			if(model.getScholarListSize() == 0){
				model.emptySerials();
				model.emptyPapers();
				mainView.getJBTDeleteScholars().setEnabled(false);
				mainView.getJBTDeleteAllScholars().setEnabled(false);
				mainView.getJBTAddSerial().setEnabled(false);
				mainView.getJBTDeleteSerials().setEnabled(false);
				mainView.getJBTDeleteAllSerials().setEnabled(false);
				mainView.getJBTAddPaper().setEnabled(false);
				mainView.getJBTDeletePapers().setEnabled(false);
				mainView.getJBTDeleteAllPapers().setEnabled(false);
			}
			mainView.updateScholarList();
			mainView.updateSerialList();
			mainView.updatePaperList();
		}
	}
	
	private class DeleteAllScholarsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			model.emptyScholars();
			model.emptySerials();
			model.emptyPapers();
			mainView.updateScholarList();
			mainView.updateSerialList();
			mainView.updatePaperList();
			mainView.getJBTDeleteScholars().setEnabled(false);
			mainView.getJBTDeleteAllScholars().setEnabled(false);
			mainView.getJBTAddSerial().setEnabled(false);
			mainView.getJBTDeleteSerials().setEnabled(false);
			mainView.getJBTDeleteAllSerials().setEnabled(false);
			mainView.getJBTAddPaper().setEnabled(false);
			mainView.getJBTDeletePapers().setEnabled(false);
			mainView.getJBTDeleteAllPapers().setEnabled(false);
		}
	}
	
	private class ListOfSerialsListener implements ListSelectionListener{
		boolean newSelection = true;
		SecondMouseClickSerials doubleClick;
		MouseDraggedSerials mouseMoved;
		
		public ListOfSerialsListener(){
			doubleClick = new SecondMouseClickSerials();
			mouseMoved = new MouseDraggedSerials();
			mainView.getListOfSerials().addMouseListener(doubleClick);
			mainView.getListOfSerials().addMouseMotionListener(mouseMoved);
		}
		
		public void valueChanged(ListSelectionEvent arg0) {
			if(!arg0.getValueIsAdjusting()){
				if(newSelection){
					newSelection = false;
				}
			}
			else{
				newSelection = true;
				doubleClick.disable();
			}
		}
		
		private class SecondMouseClickSerials implements MouseListener{
			boolean clicked = false;
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {
				clicked = false;
			}
			public void mousePressed(MouseEvent arg0) {
				if(!newSelection){
					clicked = true;
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				if(clicked){
					if(mainView.getListOfSerials().getSelectedIndex() >= model.getJournalListSize()){
						if(!openConferences.contains(model.getConference(mainView.getListOfSerials().getSelectedIndex() - model.getJournalListSize()))){
							ConferenceDataView conferenceDataView = new ConferenceDataView(model.getConference(mainView.getListOfSerials().getSelectedIndex() - model.getJournalListSize()), openConferences, openConferenceWindows, model);
							conferenceDataView.getJBTOK().addActionListener(new ConferenceDataViewOKButtonListener(conferenceDataView));
							conferenceDataView.addWindowListener(new ConferenceDataViewWindowListener(conferenceDataView));
							clicked = false;
						}
						else{
							clicked = false;
						}
					}
					else if(mainView.getListOfSerials().getSelectedIndex() < model.getJournalListSize()){
						if(!openJournals.contains(model.getJournal(mainView.getListOfSerials().getSelectedIndex()))){
							JournalDataView journalDataView = new JournalDataView(model.getJournal(mainView.getListOfSerials().getSelectedIndex()), openJournals, openJournalWindows, model);
							journalDataView.getJBTOK().addActionListener(new JournalDataViewOKButtonListener(journalDataView));
							journalDataView.addWindowListener(new JournalDataViewWindowListener(journalDataView));
							clicked = false;
						}
						else{
							clicked = false;
						}
					}
				}
			}
			public void disable(){
				clicked = false;
			}
		}
		
		private class MouseDraggedSerials implements MouseMotionListener{
			public void mouseDragged(MouseEvent arg0) {
				doubleClick.disable();
			}
			public void mouseMoved(MouseEvent arg0) {}
		}
	}
	
	private class ConferenceDataViewWindowListener implements WindowListener{
		ConferenceDataView conferenceDataView;
		public ConferenceDataViewWindowListener(ConferenceDataView view){
			conferenceDataView = view;
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			conferenceDataView.windowIsClosing();
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class ConferenceDataViewOKButtonListener implements ActionListener{
		ConferenceDataView localDataView;
		public ConferenceDataViewOKButtonListener(ConferenceDataView view){
			localDataView = view;
		}
		public void actionPerformed(ActionEvent arg0) {
			localDataView.windowIsClosing();
			localDataView.dispose();
		}
	}
	
	private class JournalDataViewWindowListener implements WindowListener{
		JournalDataView journalDataView;
		public JournalDataViewWindowListener(JournalDataView view){
			journalDataView = view;
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			journalDataView.windowIsClosing();
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class JournalDataViewOKButtonListener implements ActionListener{
		JournalDataView localDataView;
		public JournalDataViewOKButtonListener(JournalDataView view){
			localDataView = view;
		}
		public void actionPerformed(ActionEvent arg0) {
			localDataView.windowIsClosing();
			localDataView.dispose();
		}
	}
	
	private class AddSerialListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			AddSerialView serialView = new AddSerialView(model);
			setAddSerialViewActionListeners(serialView);
		}
	}
	
	private class DeleteSerialsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			model.removeSerials(mainView.getSerialListPositions());
			if((model.getJournalListSize() == 0) && (model.getConferenceListSize() == 0)){
				model.emptyPapers();
				mainView.getJBTDeleteSerials().setEnabled(false);
				mainView.getJBTDeleteAllSerials().setEnabled(false);
				mainView.getJBTAddPaper().setEnabled(false);
				mainView.getJBTDeletePapers().setEnabled(false);
				mainView.getJBTDeleteAllPapers().setEnabled(false);
			}
			mainView.updateScholarList();
			mainView.updateSerialList();
			mainView.updatePaperList();
		}
	}
	
	private class DeleteAllSerialsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			model.emptySerials();
			model.emptyPapers();
			mainView.updateScholarList();
			mainView.updateSerialList();
			mainView.updatePaperList();
			mainView.getJBTDeleteSerials().setEnabled(false);
			mainView.getJBTDeleteAllSerials().setEnabled(false);
			mainView.getJBTAddPaper().setEnabled(false);
			mainView.getJBTDeletePapers().setEnabled(false);
			mainView.getJBTDeleteAllPapers().setEnabled(false);
		}
	}
	
	private class ListOfPapersListener implements ListSelectionListener{
		int lastSelectedIndex = -1;
		public void valueChanged(ListSelectionEvent arg0) {
			
		}
	}
	
	private class AddPaperListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			AddPaperView paperView = new AddPaperView(model);
		}
	}
	
	private class DeletePapersListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	private class DeleteAllPapersListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	private class AddScholarToListListener implements ActionListener{
		AddScholarView localScholarView;
		public AddScholarToListListener(AddScholarView scholarView){
			this.localScholarView = scholarView;
		}
		public void actionPerformed(ActionEvent arg0) {
			boolean uniqueScholar = model.addScholar(localScholarView.getTextFields());
			mainView.updateScholarList();
			if(!mainView.getJBTDeleteScholars().isEnabled()){
				mainView.getJBTDeleteScholars().setEnabled(true);
			}
			if(!mainView.getJBTDeleteAllScholars().isEnabled()){
				mainView.getJBTDeleteAllScholars().setEnabled(true);
			}
			if(!mainView.getJBTAddSerial().isEnabled()){
				mainView.getJBTAddSerial().setEnabled(true);
			}
			if(uniqueScholar){
				localScholarView.dispose();
			}
		}
	}
	
	private class AddSerialToListListener implements ActionListener{
		AddSerialView localSerialView;
		public AddSerialToListListener(AddSerialView serialView){
			this.localSerialView = serialView;
		}
		public void actionPerformed(ActionEvent arg0) {
			if(localSerialView.visibleCard().equals("Conference")){
				if(localSerialView.temporaryMeetingSize() != 0){
					Conference tempConf = new Conference(localSerialView.getConferenceOrganizationName().trim(), localSerialView.getMeetings());
					boolean uniqueSerial = !model.containsConference(tempConf);
					if(uniqueSerial){
						if(!mainView.getJBTDeleteSerials().isEnabled()){
							mainView.getJBTDeleteSerials().setEnabled(true);
						}
						if(!mainView.getJBTDeleteAllSerials().isEnabled()){
							mainView.getJBTDeleteAllSerials().setEnabled(true);
						}
						if(!mainView.getJBTAddPaper().isEnabled()){
							mainView.getJBTAddPaper().setEnabled(true);
						}
						model.addConference(tempConf);
						localSerialView.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "This conference is already in the database.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
					}
					mainView.updateSerialList();
				}
				else{
					JOptionPane.showMessageDialog(null, "Cannot add an empty serial.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				}
			}
			else{
				if(localSerialView.issuesPresent()){
					Journal tempJour = new Journal(localSerialView.getJournalOrganizationName().trim(), localSerialView.getJournalLocation(), localSerialView.getVolumes());
					boolean uniqueSerial = !model.containsJournal(tempJour);
					if(uniqueSerial){
						if(!mainView.getJBTDeleteSerials().isEnabled()){
							mainView.getJBTDeleteSerials().setEnabled(true);
						}
						if(!mainView.getJBTDeleteAllSerials().isEnabled()){
							mainView.getJBTDeleteAllSerials().setEnabled(true);
						}
						if(!mainView.getJBTAddPaper().isEnabled()){
							mainView.getJBTAddPaper().setEnabled(true);
						}
						model.addJournal(tempJour);
						localSerialView.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "This journal is already in the database.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
					}
					mainView.updateSerialList();
				}
				else{
					JOptionPane.showMessageDialog(null, "Cannot add an empty serial.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	private class SerialConferenceSaveMeetingListener implements ActionListener{
		AddSerialView localSerialView;
		public SerialConferenceSaveMeetingListener(AddSerialView serialView){
			this.localSerialView = serialView;
		}
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Object> arrayListOfDetails = localSerialView.getInnerDetails();
			if(arrayListOfDetails.size() != 0){
				String typeOfSerial = (String)arrayListOfDetails.get(0);
				String[] fields = (String[])arrayListOfDetails.get(1);
				ArrayList<Scholar> leftScholarList = (ArrayList<Scholar>)arrayListOfDetails.get(2);
				ArrayList<Scholar> rightScholarList = (ArrayList<Scholar>)arrayListOfDetails.get(3);
				if(typeOfSerial.equals("Conference")){
					Meeting tempMeeting = new Meeting(fields, leftScholarList, rightScholarList);
					if(localSerialView.containsMeeting(tempMeeting)){
						JOptionPane.showMessageDialog(null, "This meeting is already in the database.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
					}
					else{
						localSerialView.addTemporaryMeeting(tempMeeting);
					}
				}
				else if(typeOfSerial.equals("Journal")){
					Debug.Log("Error in SerialConferenceSaveMeetingListener: button does not match card");
					System.exit(-1);
				}
			}
		}
	}
	
	private class SerialJournalSaveIssueListener implements ActionListener{
		AddSerialView localSerialView;
		public SerialJournalSaveIssueListener(AddSerialView serialView){
			this.localSerialView = serialView;
		}
		public void actionPerformed(ActionEvent arg0){
			ArrayList<Object> arrayListOfDetails = localSerialView.getInnerDetails();
			if(arrayListOfDetails.size() != 0){
				String typeOfSerial = (String)arrayListOfDetails.get(0);
				String[] fields = (String[])arrayListOfDetails.get(1);
				ArrayList<Scholar> leftScholarList = (ArrayList<Scholar>)arrayListOfDetails.get(2);
				ArrayList<Scholar> rightScholarList = (ArrayList<Scholar>)arrayListOfDetails.get(3);
				if(typeOfSerial.equals("Journal")){
					Issue issue = new Issue(fields, leftScholarList, rightScholarList);
					if(localSerialView.containsIssue(issue)){
						JOptionPane.showMessageDialog(null, "This issue is present in the selected volume.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
					}
					else{
						localSerialView.addIssueToVolume(issue);
					}
				}
				else if(typeOfSerial.equals("Conference")){
					Debug.Log("Error in SerialJournalSaveIssueListener: button does not match card");
					System.exit(-1);
				}
			}
		}
	}
	
	private class SerialJournalNewVolumeListener implements ActionListener{
		AddSerialView localSerialView;
		public SerialJournalNewVolumeListener(AddSerialView serialView){
			this.localSerialView = serialView;
		}
		public void actionPerformed(ActionEvent arg0){
			localSerialView.newVolume();
		}
	}
	
	private class LoadOptionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class SaveOptionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CloseOptionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class TypeOfPublicationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class PublicationsPerYearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ConferencePapersPerYearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class JournalArticlesPerYearListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class NumberOfCoauthorsPerPublicationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	public class AddPaperToListListener implements ActionListener{
		AddPaperView localPaperView;
		public AddPaperToListListener(AddPaperView paperView){
			this.localPaperView = paperView;
		}
		public void actionPerformed(ActionEvent arg0) {
			if(localPaperView.visibleCard().equals("Conference")){
				
			}
			else{
				
			}
		}

	}
}
