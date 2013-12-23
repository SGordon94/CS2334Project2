import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//REMAINING FEATURES:
	//CAN ADD EMPTY SCHOLARS
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
	private ArrayList<ConferencePaperDataView> openConferencePaperWindows = new ArrayList<ConferencePaperDataView>();
	private ArrayList<JournalArticleDataView> openJournalArticleWindows = new ArrayList<JournalArticleDataView>();
	private ArrayList<AddScholarView> openAddScholarWindows = new ArrayList<AddScholarView>();
	private ArrayList<AddSerialView> openAddSerialWindows = new ArrayList<AddSerialView>();
	private ArrayList<AddPaperView> openAddPaperWindows = new ArrayList<AddPaperView>();
	
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
			mainView.getLoadOption().addActionListener(new LoadOptionListener());
			mainView.getSaveOption().addActionListener(new SaveOptionListener());
			mainView.getCloseOption().addActionListener(new CloseOptionListener());
			mainView.getTypeOfPublication().addActionListener(new TypeOfPublicationListener());
			mainView.getPublicationsPerYear().addActionListener(new PublicationsPerYearListener());
			mainView.getConferencePapersPerYear().addActionListener(new ConferencePapersPerYearListener());
			mainView.getJournalArticlesPerYear().addActionListener(new JournalArticlesPerYearListener());
			mainView.getNumberOfCoauthorsPerYear().addActionListener(new NumberOfCoauthorsPerPublicationListener());
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
				if(model.getScholarListSize() > 0){
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
			addScholarView.showAddScholarView(openAddScholarWindows);
			addScholarView.addWindowListener(new AddScholarWindowListener(addScholarView));
			setAddScholarViewActionListeners(addScholarView);
			openAddScholarWindows.add(addScholarView);
		}
		
		private class AddScholarWindowListener implements WindowListener{
			AddScholarView addScholarView;
			public AddScholarWindowListener(AddScholarView view){
				addScholarView = view;
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				addScholarView.windowIsClosing();
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		}
	}
	
	private class DeleteScholarsListener implements ActionListener{ // FINISH THIS BEHEMOTH 12/21/2013
		public void actionPerformed(ActionEvent arg0) {
			//-------------------------------------------------------/
			int[] indices = mainView.getScholarListPositions();
			////////////////
			//((Conference)model.getScholar(indices[i]).getPaper(n).getParentSerial())
			for(int i=indices.length-1;i>=0;i--){
				for(int o=model.getConferenceListSize()-1;o>=0;o--){
					boolean delete = false;
					for(int p=model.getConference(o).getMeetingListSize()-1;p>=0;p--){
						model.getConference(o).getMeeting(p).removeScholar(model.getScholar(indices[i]));
						if(model.getConference(o).getMeeting(p).isAListEmpty()){
							delete = true;
							for(int j=openConferenceWindows.size()-1;j>=0;j--){
								if(model.getConference(o) == openConferenceWindows.get(j).getUsedSerial()){
									openConferenceWindows.get(j).dispose();
									openConferenceWindows.remove(j);
								}
							}
							for(int j=model.getConference(o).getMeetingListSize()-1;j>=0;j--){
								for(int k=model.getConference(o).getMeeting(j).getPaperListSize()-1;k>=0;k--){
									for(int l=openConferencePaperWindows.size()-1;l>=0;l--){
										if(model.getConference(o).getMeeting(j).getPaper(k) == openConferencePaperWindows.get(l).getUsedPaper()){
											openConferencePaperWindows.get(l).dispose();
											openConferencePaperWindows.remove(l);
										}
									}
									for(int l=openPapers.size()-1;l>=0;l--){
										if(model.getConference(o).getMeeting(j).getPaper(k) == openPapers.get(l)){
											openPapers.remove(l);
										}
									}
									model.removePaper(model.getConference(o).getMeeting(j).getPaper(k));
									model.getConference(o).getMeeting(j).getPaper(k).removePaper();
								}
							}
						}
					}
					if(delete){
						model.removeSerial(model.getConference(o));	
					}
				}
				for(int o=model.getJournalListSize()-1;o>=0;o--){
					boolean delete = false;
					for(int p=model.getJournal(o).getVolumeSize()-1;p>=0;p--){
						for(int q=model.getJournal(o).getVolume(p).getSizeOfIssueList()-1;q>=0;q--){
							model.getJournal(o).getVolume(p).getIssue(q).removeScholar(model.getScholar(indices[i]));
							if(model.getJournal(o).getVolume(p).getIssue(q).isAListEmpty()){
								delete = true;
								for(int j=openJournalWindows.size()-1;j>=0;j--){
									if(model.getJournal(o) == openJournalWindows.get(j).getUsedSerial()){
										openJournalWindows.get(j).dispose();
										openJournalWindows.remove(j);
									}
								}
								for(int j=model.getJournal(o).getVolumeSize()-1;j>=0;j--){
									for(int k=model.getJournal(o).getVolume(j).getSizeOfIssueList()-1;k>=0;k--){
										for(int l=model.getJournal(o).getVolume(j).getIssue(k).getPaperListSize()-1;l>=0;l--){
											for(int m=openJournalArticleWindows.size()-1;m>=0;m--){
												if(model.getJournal(o).getVolume(j).getIssue(k).getPaper(l) == openJournalArticleWindows.get(m).getUsedPaper()){
													openJournalArticleWindows.get(m).dispose();
													openJournalArticleWindows.remove(m);
												}
											}
											for(int m=openPapers.size()-1;m>=0;m--){
												if(model.getJournal(o).getVolume(j).getIssue(k).getPaper(l) == openPapers.get(m)){
													openPapers.remove(m);
												}
											}
											model.removePaper(model.getJournal(o).getVolume(j).getIssue(k).getPaper(l));
											model.getJournal(o).getVolume(j).getIssue(k).getPaper(l).removePaper();
										}
									}
								}
							}
						}
					}
					if(delete){
						model.removeSerial(model.getJournal(o));
					}
				}
				
			}
			for(int i=openAddSerialWindows.size()-1;i>=0;i--){
				openAddSerialWindows.get(i).dispose();
				openAddSerialWindows.remove(i);
			}
			for(int i=openAddPaperWindows.size()-1;i>=0;i--){
				openAddPaperWindows.get(i).dispose();
				openAddPaperWindows.remove(i);
			}
			model.removeScholars(indices);
			if(model.getScholarListSize() == 0){
				mainView.getJBTDeleteScholars().setEnabled(false);
				mainView.getJBTDeleteAllScholars().setEnabled(false);
			}
			if((model.getJournalListSize() == 0) && (model.getConferenceListSize() == 0)){
				mainView.getJBTAddSerial().setEnabled(false);
				mainView.getJBTDeleteSerials().setEnabled(false);
				mainView.getJBTDeleteAllSerials().setEnabled(false);
			}
			if(model.getPaperListSize() == 0){
				mainView.getJBTAddPaper().setEnabled(false);
				mainView.getJBTDeletePapers().setEnabled(false);
				mainView.getJBTDeleteAllPapers().setEnabled(false);
			}
			mainView.updateScholarList();
			mainView.updateSerialList();
			mainView.updatePaperList();
			//-------------------------------------------------------/
		}
	}
	
	private class DeleteAllScholarsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			for(int i=openAddPaperWindows.size()-1;i>=0;i--){
				openAddPaperWindows.get(i).dispose();
				openAddPaperWindows.remove(i);
			}
			for(int i=openAddSerialWindows.size()-1;i>=0;i--){
				openAddSerialWindows.get(i).dispose();
				openAddSerialWindows.remove(i);
			}
			for(int i=openScholarWindows.size()-1;i>=0;i--){
				openScholarWindows.get(i).dispose();
				openScholarWindows.remove(i);
			}
			for(int i=openConferenceWindows.size()-1;i>=0;i--){
				openConferenceWindows.get(i).dispose();
				openConferenceWindows.remove(i);
			}
			for(int i=openJournalWindows.size()-1;i>=0;i--){
				openJournalWindows.get(i).dispose();
				openJournalWindows.remove(i);
			}
			for(int i=openConferencePaperWindows.size()-1;i>=0;i--){
				openConferencePaperWindows.get(i).dispose();
				openConferencePaperWindows.remove(i);
			}
			for(int i=openJournalArticleWindows.size()-1;i>=0;i--){
				openJournalArticleWindows.get(i).dispose();
				openJournalArticleWindows.remove(i);
			}
			openScholars.removeAll(openScholars);
			openConferences.removeAll(openConferences);
			openJournals.removeAll(openJournals);
			openPapers.removeAll(openPapers);
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
				if((model.getConferenceListSize() > 0) || (model.getJournalListSize() > 0)){
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
			AddSerialView serialView = new AddSerialView(model, openAddSerialWindows);
			serialView.addWindowListener(new AddSerialWindowListener(serialView));
			setAddSerialViewActionListeners(serialView);
			openAddSerialWindows.add(serialView);
		}
		
		private class AddSerialWindowListener implements WindowListener{
			AddSerialView serialView;
			public AddSerialWindowListener(AddSerialView view){
				serialView = view;
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				serialView.windowIsClosing();
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		}
	}
	
	private class DeleteSerialsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			int[] indices = mainView.getSerialListPositions();
			for(int i=indices.length-1;i>=0;i--){
				if(indices[i] >= model.getJournalListSize()){
					for(int j=openConferenceWindows.size()-1;j>=0;j--){
						if(model.getConference(indices[i]-model.getJournalListSize()) == openConferenceWindows.get(j).getUsedSerial()){
							openConferenceWindows.get(j).dispose();
							openConferenceWindows.remove(j);
						}
					}
					for(int j=model.getConference(indices[i]-model.getJournalListSize()).getMeetingListSize()-1;j>=0;j--){
						for(int k=model.getConference(indices[i]-model.getJournalListSize()).getMeeting(j).getPaperListSize()-1;k>=0;k--){
							for(int l=openConferencePaperWindows.size()-1;l>=0;l--){
								if(model.getConference(indices[i]-model.getJournalListSize()).getMeeting(j).getPaper(k) == openConferencePaperWindows.get(l).getUsedPaper()){
									openConferencePaperWindows.get(l).dispose();
									openConferencePaperWindows.remove(l);
								}
							}
							for(int l=openPapers.size()-1;l>=0;l--){
								if(model.getConference(indices[i]-model.getJournalListSize()).getMeeting(j).getPaper(k) == openPapers.get(l)){
									openPapers.remove(l);
								}
							}
							model.removePaper(model.getConference(indices[i]-model.getJournalListSize()).getMeeting(j).getPaper(k));
							model.getConference(indices[i]-model.getJournalListSize()).getMeeting(j).getPaper(k).removePaper();
						}
					}
				}
				else{
					for(int j=openJournalWindows.size()-1;j>=0;j--){
						if(model.getJournal(indices[i]) == openJournalWindows.get(j).getUsedSerial()){
							openJournalWindows.get(j).dispose();
							openJournalWindows.remove(j);
						}
					}
					for(int j=model.getJournal(indices[i]).getVolumeSize()-1;j>=0;j--){
						for(int k=model.getJournal(indices[i]).getVolume(j).getSizeOfIssueList()-1;k>=0;k--){
							for(int l=model.getJournal(indices[i]).getVolume(j).getIssue(k).getPaperListSize()-1;l>=0;l--){
								for(int m=openJournalArticleWindows.size()-1;m>=0;m--){
									if(model.getJournal(indices[i]).getVolume(j).getIssue(k).getPaper(l) == openJournalArticleWindows.get(m).getUsedPaper()){
										openJournalArticleWindows.get(m).dispose();
										openJournalArticleWindows.remove(m);
									}
								}
								for(int m=openPapers.size()-1;m>=0;m--){
									if(model.getJournal(indices[i]).getVolume(j).getIssue(k).getPaper(l) == openPapers.get(m)){
										openPapers.remove(m);
									}
								}
								model.removePaper(model.getJournal(indices[i]).getVolume(j).getIssue(k).getPaper(l));
								model.getJournal(indices[i]).getVolume(j).getIssue(k).getPaper(l).removePaper();
							}
						}
					}
				}	
			}
			for(int i=openAddPaperWindows.size()-1;i>=0;i--){
				openAddPaperWindows.get(i).dispose();
				openAddPaperWindows.remove(i);
			}
			model.removeSerials(mainView.getSerialListPositions());
			if((model.getJournalListSize() == 0) && (model.getConferenceListSize() == 0)){
				mainView.getJBTDeleteSerials().setEnabled(false);
				mainView.getJBTDeleteAllSerials().setEnabled(false);

			}
			if(model.getPaperListSize() == 0){
				mainView.getJBTAddPaper().setEnabled(false);
				mainView.getJBTDeletePapers().setEnabled(false);
				mainView.getJBTDeleteAllPapers().setEnabled(false);
			}
			mainView.updateSerialList();
			mainView.updatePaperList();
		}
	}
	
	private class DeleteAllSerialsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			for(int i=openAddPaperWindows.size()-1;i>=0;i--){
				openAddPaperWindows.get(i).dispose();
				openAddPaperWindows.remove(i);
			}
			for(int i=openConferenceWindows.size()-1;i>=0;i--){
				openConferenceWindows.get(i).dispose();
				openConferenceWindows.remove(i);
			}
			for(int i=openJournalWindows.size()-1;i>=0;i--){
				openJournalWindows.get(i).dispose();
				openJournalWindows.remove(i);
			}
			for(int i=openConferencePaperWindows.size()-1;i>=0;i--){
				openConferencePaperWindows.get(i).dispose();
				openConferencePaperWindows.remove(i);
			}
			for(int i=openJournalArticleWindows.size()-1;i>=0;i--){
				openJournalArticleWindows.get(i).dispose();
				openJournalArticleWindows.remove(i);
			}
			openConferences.removeAll(openConferences);
			openJournals.removeAll(openJournals);
			openPapers.removeAll(openPapers);
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
		boolean newSelection = true;
		SecondMouseClickPapers doubleClick;
		MouseDraggedPapers mouseMoved;
		
		public ListOfPapersListener(){
			doubleClick = new SecondMouseClickPapers();
			mouseMoved = new MouseDraggedPapers();
			mainView.getListOfPapers().addMouseListener(doubleClick);
			mainView.getListOfPapers().addMouseMotionListener(mouseMoved);
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
		
		private class SecondMouseClickPapers implements MouseListener{
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
				if(model.getPaperListSize() > 0){
					if(clicked){
						if(!openPapers.contains(model.getPaper(mainView.getListOfPapers().getSelectedIndex()))){
							if(model.getPaper(mainView.getListOfPapers().getSelectedIndex()).getClass() == ConferencePaper.class){
								ConferencePaperDataView paperDataView = new ConferencePaperDataView((ConferencePaper)model.getPaper(mainView.getListOfPapers().getSelectedIndex()), openPapers, openConferencePaperWindows, model);
								paperDataView.getJBTOK().addActionListener(new ConferencePaperDataViewOKButtonListener(paperDataView));
								paperDataView.addWindowListener(new ConferencePaperDataViewWindowListener(paperDataView));
							}
							else{
								JournalArticleDataView paperDataView = new JournalArticleDataView((JournalPaper)model.getPaper(mainView.getListOfPapers().getSelectedIndex()), openPapers, openJournalArticleWindows, model);
								paperDataView.getJBTOK().addActionListener(new JournalArticleDataViewOKButtonListener(paperDataView));
								paperDataView.addWindowListener(new JournalArticleDataViewWindowListener(paperDataView));
							}
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
		
		private class MouseDraggedPapers implements MouseMotionListener{
			public void mouseDragged(MouseEvent arg0) {
				doubleClick.disable();
			}
			public void mouseMoved(MouseEvent arg0) {}
		}
	}
	
	private class AddPaperListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			AddPaperView paperView = new AddPaperView(model, openAddPaperWindows);
			paperView.getJBTAddPaper().addActionListener(new AddPaperToListListener(paperView));
			paperView.addWindowListener(new AddPaperWindowListener(paperView));
			openAddPaperWindows.add(paperView);
		}
		
		private class AddPaperWindowListener implements WindowListener{
			AddPaperView paperView;
			public AddPaperWindowListener(AddPaperView view){
				paperView = view;
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				paperView.windowIsClosing();
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		}
	}
	
	private class ConferencePaperDataViewWindowListener implements WindowListener{
		ConferencePaperDataView conferencePaperView;
		public ConferencePaperDataViewWindowListener(ConferencePaperDataView view){
			conferencePaperView = view;
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			conferencePaperView.windowIsClosing();
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class ConferencePaperDataViewOKButtonListener implements ActionListener{
		ConferencePaperDataView localDataView;
		public ConferencePaperDataViewOKButtonListener(ConferencePaperDataView view){
			localDataView = view;
		}
		public void actionPerformed(ActionEvent arg0) {
			localDataView.windowIsClosing();
			localDataView.dispose();
		}
	}
	
	private class JournalArticleDataViewWindowListener implements WindowListener{
		JournalArticleDataView journalArticleView;
		public JournalArticleDataViewWindowListener(JournalArticleDataView view){
			journalArticleView = view;
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowClosing(WindowEvent arg0) {
			journalArticleView.windowIsClosing();
		}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class JournalArticleDataViewOKButtonListener implements ActionListener{
		JournalArticleDataView localDataView;
		public JournalArticleDataViewOKButtonListener(JournalArticleDataView view){
			localDataView = view;
		}
		public void actionPerformed(ActionEvent arg0) {
			localDataView.windowIsClosing();
			localDataView.dispose();
		}
	}
	
	private class DeletePapersListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			int[] indices = mainView.getPaperListPositions();
			for(int i=indices.length-1;i>=0;i--){
				for(int j=openConferencePaperWindows.size()-1;j>=0;j--){
					if(model.getPaper(indices[i]) == openConferencePaperWindows.get(j).getUsedPaper()){
						openConferencePaperWindows.get(j).dispose();
						openConferencePaperWindows.remove(j);
					}
				}
				for(int k=openJournalArticleWindows.size()-1;k>=0;k--){
					if(model.getPaper(indices[i]) == openJournalArticleWindows.get(k).getUsedPaper()){
						openJournalArticleWindows.get(k).dispose();
						openJournalArticleWindows.remove(k);
					}
				}
				for(int l=openPapers.size()-1;l>=0;l--){
					if(model.getPaper(indices[i]) == openPapers.get(l)){
						openPapers.remove(l);
					}
				}
			}
			model.removePapers(indices);
			if(model.getPaperListSize() == 0){
				mainView.getJBTDeletePapers().setEnabled(false);
				mainView.getJBTDeleteAllPapers().setEnabled(false);
			}
			mainView.updatePaperList();
		}
	}
	
	private class DeleteAllPapersListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			for(int i=openConferencePaperWindows.size()-1;i>=0;i--){
				openConferencePaperWindows.get(i).dispose();
				openConferencePaperWindows.remove(i);
			}
			for(int i=openJournalArticleWindows.size()-1;i>=0;i--){
				openJournalArticleWindows.get(i).dispose();
				openJournalArticleWindows.remove(i);
			}
			openPapers.removeAll(openPapers);
			model.emptyPapers();
			mainView.getJBTDeletePapers().setEnabled(false);
			mainView.getJBTDeleteAllPapers().setEnabled(false);
			mainView.updatePaperList();
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
				openAddScholarWindows.remove(localScholarView);
				for(int i=openAddSerialWindows.size()-1;i>=0;i--){
					openAddSerialWindows.get(i).dispose();
					openAddSerialWindows.remove(i);
				}
				for(int i=openAddPaperWindows.size()-1;i>=0;i--){
					openAddPaperWindows.get(i).dispose();
					openAddPaperWindows.remove(i);
				}
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
						for(int i=openAddPaperWindows.size()-1;i>=0;i--){
							openAddPaperWindows.get(i).dispose();
							openAddPaperWindows.remove(i);
						}
						model.addConference(tempConf);
						openAddSerialWindows.remove(localSerialView);
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
						for(int i=openAddPaperWindows.size()-1;i>=0;i--){
							openAddPaperWindows.get(i).dispose();
							openAddPaperWindows.remove(i);
						}
						model.addJournal(tempJour);
						openAddSerialWindows.remove(localSerialView);
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
	
	@SuppressWarnings("unchecked")
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
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class SaveOptionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class CloseOptionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class TypeOfPublicationListener implements ActionListener{
		String choice = "Type Of Publication";
		public void actionPerformed(ActionEvent e) {
			PlotGUI plotGUI = new PlotGUI(model, choice);
		}
	}
	
	private class PublicationsPerYearListener implements ActionListener{
		String choice = "Publications Per Year";
		public void actionPerformed(ActionEvent e) {
			PlotGUI plotGUI = new PlotGUI(model, choice);
		}
		
	}
	
	private class ConferencePapersPerYearListener implements ActionListener{
		String choice = "Conference Papers Per Year";
		public void actionPerformed(ActionEvent e) {
			PlotGUI plotGUI = new PlotGUI(model, choice);
		}
		
	}
	
	private class JournalArticlesPerYearListener implements ActionListener{
		String choice = "Journal Articles Per Year";
		public void actionPerformed(ActionEvent e) {
			PlotGUI plotGUI = new PlotGUI(model, choice);
		}
		
	}
	
	private class NumberOfCoauthorsPerPublicationListener implements ActionListener{
		String choice = "Number of Co-Authors Per Publication";
		public void actionPerformed(ActionEvent e) {
			PlotGUI plotGUI = new PlotGUI(model, choice);
		}
	}

	public class AddPaperToListListener implements ActionListener{
		AddPaperView localPaperView;
		public AddPaperToListListener(AddPaperView paperView){
			this.localPaperView = paperView;
		}
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Object> innerDetails = localPaperView.getInnerDetails();
			if(innerDetails.size() != 0){
				if(localPaperView.visibleCard().equals("Conference")){
					Paper pape = new ConferencePaper(innerDetails);
					boolean uniquePaper = !model.containsPaper(pape);
					if(uniquePaper){
						if(!mainView.getJBTDeletePapers().isEnabled()){
							mainView.getJBTDeletePapers().setEnabled(true);
						}
						if(!mainView.getJBTDeleteAllPapers().isEnabled()){
							mainView.getJBTDeleteAllPapers().setEnabled(true);
						}
						model.addPaper(pape);
						openAddPaperWindows.remove(localPaperView);
						localPaperView.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "This Conference Paper is already in the database.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
					}
					mainView.updatePaperList();
				}
				else{
					Paper pape = new JournalPaper(innerDetails);
					boolean uniquePaper = !model.containsPaper(pape);
					if(uniquePaper){
						if(!mainView.getJBTDeletePapers().isEnabled()){
							mainView.getJBTDeletePapers().setEnabled(true);
						}
						if(!mainView.getJBTDeleteAllPapers().isEnabled()){
							mainView.getJBTDeleteAllPapers().setEnabled(true);
						}
						model.addPaper(pape);
						openAddPaperWindows.remove(localPaperView);
						localPaperView.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "This Article is already in the database.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
					}
					mainView.updatePaperList();
				}
			}
		}
	}
}
