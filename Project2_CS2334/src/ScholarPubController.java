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
	//DOUBLE-CLICKING ON A SCHOLAR WILL OPEN THE SCHOLAR DATA VIEW WINDOW
public class ScholarPubController {
	private ScholarshipModel model;
	private SelectionView mainView;
	
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
		ArrayList<Scholar> openWindows = new ArrayList<Scholar>();
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
					doubleClick.enable();
				}
			}
			else{
				newSelection = true;
				doubleClick.disable();
			}
		}
		
		private class SecondMouseClickScholars implements MouseListener{
			boolean enabled = false;
			boolean clicked = false;
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {
				clicked = false;
				enabled = false;
			}
			public void mousePressed(MouseEvent arg0) {
				if(enabled){
					clicked = true;
					enabled = false;
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				if(clicked){
					ScholarDataView scholarDataView = new ScholarDataView(model.getScholar(mainView.getListOfScholars().getSelectedIndex()));
					openWindows.add(model.getScholar(mainView.getListOfScholars().getSelectedIndex()));
					clicked = false;
					enabled = false;
				}
			}
			public void enable(){
				enabled = true;
			}
			public void disable(){
				enabled = false;
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
		int lastSelectedIndex = -1;
		public void valueChanged(ListSelectionEvent arg0) {
			
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
}
