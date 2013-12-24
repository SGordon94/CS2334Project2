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


public class SaveLoadGUI extends JFrame{
	ScholarshipModel model = new ScholarshipModel();
	JFileChooser fileChooser = new JFileChooser();
	int choice;
	String option;
	
	public SaveLoadGUI(ScholarshipModel mod, String option) throws IOException, ClassNotFoundException{
		this.model = mod;
		this.option = option;
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
					this.model = (ScholarshipModel) objectInput.readObject();
					objectInput.close();
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
