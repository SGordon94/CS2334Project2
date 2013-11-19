import java.util.*;
import java.awt.event.*;


public class ScholarshipModel {
	ArrayList<ActionListener> actionListListener;
	
	private ArrayList<Conference> conferences = new ArrayList<Conference>();
	private ArrayList<Journal> journals = new ArrayList<Journal>();
	private ArrayList<Scholar> scholars = new ArrayList<Scholar>();
	public String[] scholarsNames = {"Empty"};
	private ArrayList<Paper> papers = new ArrayList<Paper>();
	
	public synchronized String[] getScholarNames(){
		String[] names = new String[scholars.size()];
		for(int i=0;i<scholars.size();i++){
			names[i] = scholars.get(i).returnNameInString();
		}
		return names;
	}
	
	public synchronized void addScholar(){
		scholars.add(new Scholar("Wilkins, Daylon"));
	}
}
