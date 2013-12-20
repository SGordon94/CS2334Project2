import java.util.*;
import java.awt.event.*;

import javax.swing.*;


public class ScholarshipModel {
	ArrayList<ActionListener> actionListListener;
	
	private ArrayList<Conference> conferences = new ArrayList<Conference>();
	private ArrayList<Journal> journals = new ArrayList<Journal>();
	private ArrayList<Scholar> scholars = new ArrayList<Scholar>();
	private ArrayList<Paper> papers = new ArrayList<Paper>();
	
	public synchronized String[] getScholarNames(){
		String[] names = new String[scholars.size()];
		for(int i=0;i<scholars.size();i++){
			names[i] = scholars.get(i).returnNameInString();
		}
		return names;
	}
	
	public synchronized Scholar getScholar(int index){
		return scholars.get(index);
	}
	
	public synchronized ArrayList<Scholar> getSelectedScholars(int[] indices){
		ArrayList<Scholar> selectedScholars = new ArrayList<Scholar>();
		for(int i=0;i<indices.length;i++){
			selectedScholars.add(scholars.get(indices[i]));
		}
		return selectedScholars;
	}
	
	public synchronized String[] getPaperNames(){
		String[] names = new String[papers.size()];
		return names;
	}
	
	public synchronized String[] getSerialTitles(){
		String[] titles = new String[conferences.size()+journals.size()];
		for(int i=0;i<journals.size();i++){
			titles[i] = "Journal - " + journals.get(i).getOrganization();
		}
		for(int i=0;i<conferences.size();i++){
			titles[journals.size()+i] = "Conference - " + conferences.get(i).getOrganization();
		}
		return titles;
	}
	
	public synchronized boolean addScholar(String[] textFields){
		String secondaryName;
		textFields[0] = textFields[0].trim();
		textFields[1] = textFields[1].trim();
		textFields[2] = textFields[2].trim();
		textFields[3] = textFields[3].trim();
		textFields[4] = textFields[4].trim();
		secondaryName = textFields[1] + " " + textFields[2];
		String[] institutionalAffiliations = textFields[3].split(",");
		for(int i=0;i<institutionalAffiliations.length;i++){
			institutionalAffiliations[i] = institutionalAffiliations[i].trim();
		}
		String[] researchAreas = textFields[4].split(",");
		for(int i=0;i<researchAreas.length;i++){
			researchAreas[i] = researchAreas[i].trim();
		}
		boolean unique = true;
		for(int i=0;i<scholars.size();i++){
			if(scholars.get(i).returnNameInString().equals(textFields[0] + ", " + secondaryName)){
				unique = false;
			}
		}
		if(unique){
			scholars.add(new Scholar(textFields[0], secondaryName, institutionalAffiliations, researchAreas));
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "This author has already been entered.", "Request Ignored", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
	}
	
	public int getScholarListSize(){
		return scholars.size();
	}
	
	public synchronized void removeScholars(int[] indices){
		for(int i=(indices.length-1);i>=0;i--){
			scholars.remove(indices[i]);
		}
	}
	
	public synchronized void emptyScholars(){
		scholars.removeAll(scholars);
		emptyPapers();
		emptySerials();
	}
	
	public synchronized void emptyPapers(){
		papers.removeAll(papers);
	}
	
	public synchronized void addConference(Conference conf){
		conferences.add(conf);
	}
	
	public Conference getConference(int index){
		return conferences.get(index);
	}
	
	public int getConferenceListSize(){
		return conferences.size();
	}
	
	public String[] getConferenceMeetings(){
		int conferenceJListSize = 0;
		for(int i=0;i<conferences.size();i++){
			conferenceJListSize += conferences.get(i).getMeetingListSize();
		}
		String[] conferenceJListText = new String[conferenceJListSize];
		int conferenceJListCounter = 0;
		for(int i=0;i<conferences.size();i++){
			for(int j=0;j<conferences.get(i).getMeetingListSize();j++){
				conferenceJListText[conferenceJListCounter] = conferences.get(i).toString() + " // " + conferences.get(i).getMeeting(j).toString();
				++conferenceJListCounter;
			}
		}
		return conferenceJListText;
	}
	
	public boolean containsConference(Conference conf){
		if(conferences.size() != 0){
			for(int i=0;i<conferences.size();i++){
				if(conferences.get(i).equals(conf)){
					return true;
				}
			}
		}
		return false;
	}
	
	public synchronized void addJournal(Journal jour){
		journals.add(jour);
	}
	
	public Journal getJournal(int index){
		return journals.get(index);
	}
	
	public int getJournalListSize(){
		return journals.size();
	}
	
	public String[] getJournalIssues(){
		int journalJListSize = 0;
		for(int i=0;i<journals.size();i++){
			for(int j=0;j<journals.get(i).getVolumeSize();j++){
				journalJListSize += journals.get(i).getVolume(j).getSizeOfIssueList();
			}
		}
		String[] journalJListText = new String[journalJListSize];
		int journalJListCounter = 0;
		for(int i=0;i<journals.size();i++){
			for(int j=0;j<journals.get(i).getVolumeSize();j++){
				for(int k=0;k<journals.get(i).getVolume(j).getSizeOfIssueList();k++){
					journalJListText[journalJListCounter] = journals.get(i).toString() + " // Volume " + j + " // " + journals.get(i).getVolume(j).getIssue(k).toString();
					++journalJListCounter;
				}
			}
		}
		return journalJListText;
	}
	
	public boolean containsJournal(Journal jour){
		if(journals.size() != 0){
			for(int i=0;i<journals.size();i++){
				if(journals.get(i).equals(jour)){
					return true;
				}
			}
		}
		return false;
	}
	
	public synchronized void removeSerials(int[] indices){
		for(int i=(indices.length-1);i>=0;i--){
			if(indices[i] >= journals.size()){
				conferences.remove(indices[i]-journals.size());
			}
			else{
				journals.remove(indices[i]);
			}
		}
	}

	public synchronized void emptySerials(){
		conferences.removeAll(conferences);
		journals.removeAll(journals);
	}
	
	public boolean containsPaper(Paper pape){
		if(papers.size() != 0){
			for(int i=0;i<papers.size();i++){
				if(papers.get(i).getClass() == pape.getClass()){
					if(papers.get(i).getTitleOfPaper().equals(pape.getTitleOfPaper())){
						return true;
					}
				}
			}
		}
		return false;
	}
}
