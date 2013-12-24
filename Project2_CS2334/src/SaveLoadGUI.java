import javax.swing.JFileChooser;


public class SaveLoadGUI {
	ScholarshipModel model = new ScholarshipModel();
	JFileChooser fileChooser = new JFileChooser();
	
	public SaveLoadGUI(ScholarshipModel mod, String option){
		this.model = mod;
		
	}
}
