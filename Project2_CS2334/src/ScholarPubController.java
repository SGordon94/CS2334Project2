import java.awt.*;
import java.awt.event.*;

public class ScholarPubController {
	private ScholarshipModel model;
	private SelectionView mainView;
	
	ScholarPubController(){}
	
	public void setModel(ScholarshipModel mod){
		this.model = mod;
	}
	
	public void setView(SelectionView view){
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
			scholarView.getJBTAddScholar().addActionListener(new AddScholarToListListener());
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
			
		}
	}
	
	private class DeleteAllScholarsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	private class AddSerialListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
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
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Working.");
		}
	}
}
