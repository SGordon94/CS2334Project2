import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The class that governs the GUI for the Save and Load menu options.
 * 
 * @version 1.0
 */
public class SaveLoadGUI extends JFrame{
	private ScholarshipModel model = new ScholarshipModel();
	private JFileChooser fileChooser = new JFileChooser();
	private SelectionView localView;
	private int choice;
	private String option;
	
	public SaveLoadGUI(ScholarshipModel mod, String option, SelectionView selectionView) throws IOException, ClassNotFoundException{
		this.model = mod;
		this.option = option;
		localView = selectionView;
		switch(option){
			case "Load": 
				choice = fileChooser.showOpenDialog(this);
				
				if(choice == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					String temp = file.getPath();
					FileInputStream fileInput;
						try {
							fileInput = new FileInputStream(temp);
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog(null, "File not found.");
							return;
						}
					ObjectInputStream objectInput = new ObjectInputStream(fileInput);
					this.model.setModel(((ScholarshipModel) objectInput.readObject()).getModel());
					objectInput.close();
					if(model.getScholarListSize() == 0){
						localView.getJBTDeleteScholars().setEnabled(false);
						localView.getJBTDeleteAllScholars().setEnabled(false);
						localView.getJBTAddSerial().setEnabled(false);
						localView.getJBTDeleteSerials().setEnabled(false);
						localView.getJBTDeleteAllSerials().setEnabled(false);
						localView.getJBTAddPaper().setEnabled(false);
						localView.getJBTDeletePapers().setEnabled(false);
						localView.getJBTDeleteAllPapers().setEnabled(false);
					}
					else{
						localView.getJBTDeleteScholars().setEnabled(true);
						localView.getJBTDeleteAllScholars().setEnabled(true);
						localView.getJBTAddSerial().setEnabled(true);
						localView.getJBTDeleteSerials().setEnabled(false);
						localView.getJBTDeleteAllSerials().setEnabled(false);
						localView.getJBTAddPaper().setEnabled(false);
						localView.getJBTDeletePapers().setEnabled(false);
						localView.getJBTDeleteAllPapers().setEnabled(false);
					}
					if((model.getJournalListSize() == 0) && (model.getConferenceListSize() == 0)){
						localView.getJBTDeleteSerials().setEnabled(false);
						localView.getJBTDeleteAllSerials().setEnabled(false);
						localView.getJBTAddPaper().setEnabled(false);
						localView.getJBTDeletePapers().setEnabled(false);
						localView.getJBTDeleteAllPapers().setEnabled(false);
					}
					else{
						localView.getJBTDeleteSerials().setEnabled(true);
						localView.getJBTDeleteAllSerials().setEnabled(true);
						localView.getJBTAddPaper().setEnabled(true);
						localView.getJBTDeletePapers().setEnabled(false);
						localView.getJBTDeleteAllPapers().setEnabled(false);
					}
					if(model.getPaperListSize() == 0){
						localView.getJBTDeletePapers().setEnabled(false);
						localView.getJBTDeleteAllPapers().setEnabled(false);
					}
					else{
						localView.getJBTDeletePapers().setEnabled(true);
						localView.getJBTDeleteAllPapers().setEnabled(true);
					}
					localView.updateScholarList();
					localView.updateSerialList();
					localView.updatePaperList();
				}
				break;
			case "Save":
				choice = fileChooser.showSaveDialog(this);
				
				if(choice == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					String temp = file.getPath();
		            FileOutputStream fileOut = new FileOutputStream(temp);
		            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		            objectOut.writeObject(this.model);
		            objectOut.close();
				}
				break;
		}
	}
}
