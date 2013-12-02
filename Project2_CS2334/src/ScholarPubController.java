import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
			int index = mainView.getScholarListPosition();
			model.removeScholar(index);
			if(model.getScholarListSize() == 0){
				model.emptyConferences();
				model.emptyJournals();
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
		}
	}
	
	private class DeleteAllScholarsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			model.emptyScholars();
			model.emptyConferences();
			model.emptyJournals();
			model.emptyPapers();
			mainView.updateScholarList();
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
	
	private class AddSerialListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			AddSerialView serialView = new AddSerialView(model);
			setAddSerialViewActionListeners(serialView);
		}
	}
	
	private class DeleteSerialsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	private class DeleteAllSerialsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	private class AddPaperListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			AddPaperView paperView = new AddPaperView();
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
					Conference tempConf = new Conference(localSerialView.getConferenceOrganizationName(), localSerialView.getMeetings());
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
				// CODE FOR THE JOURNAL SECTION GOES HERE
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
			if(arrayListOfDetails != null){
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
					// ADD JOURNAL CODE
				}
			}
		}
	}
}
