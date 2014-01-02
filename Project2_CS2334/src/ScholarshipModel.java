import java.util.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

/**The model for the program.
 * 
 * @version 1.0
 */
public class ScholarshipModel implements Serializable{
	private static final long serialVersionUID = 6519612819713173804L;
	
	private ArrayList<Conference> conferences = new ArrayList<Conference>();
	private ArrayList<Journal> journals = new ArrayList<Journal>();
	private ArrayList<Scholar> scholars = new ArrayList<Scholar>();
	private ArrayList<Paper> papers = new ArrayList<Paper>();
	
	/**Returns the names of the contained Scholars.
	 * 
	 * @return the names of the contained Scholars
	 */
	public synchronized String[] getScholarNames(){
		String[] names = new String[scholars.size()];
		for(int i=0;i<scholars.size();i++){
			names[i] = scholars.get(i).returnNameInString();
		}
		return names;
	}
	
	/**Returns the indicated Scholar.
	 * 
	 * @param index the index of the desired Scholar
	 * @return the indicated Scholar
	 */
	public synchronized Scholar getScholar(int index){
		return scholars.get(index);
	}
	
	/**Returns the indicated Scholars.
	 * 
	 * @param indices the indices of the desired Scholars
	 * @return the indicated Scholars
	 */
	public synchronized ArrayList<Scholar> getSelectedScholars(int[] indices){
		ArrayList<Scholar> selectedScholars = new ArrayList<Scholar>();
		for(int i=0;i<indices.length;i++){
			selectedScholars.add(scholars.get(indices[i]));
		}
		return selectedScholars;
	}
	
	/**Returns the names of the contained Papers.
	 * 
	 * @return the names of the contained Papers
	 */
	public synchronized String[] getPaperNames(){
		String[] names = new String[papers.size()];
		for(int i=0;i<papers.size();i++){
			names[i] = papers.get(i).toString();
		}
		return names;
	}
	
	/**Returns the titles of the contained Serials.
	 * 
	 * @return the titles of the contained Serials
	 */
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
	
	/**Adds a Scholar to the model which is created from the passed
	 * string array.
	 * 
	 * @param textFields the data to create a Scholar from
	 * @return whether the Scholar already exists in the model (true=unique/false=exists)
	 */
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
	
	/**Returns the amount of Scholars that the model has.
	 * 
	 * @return the size of the Scholar ArrayList
	 */
	public int getScholarListSize(){
		return scholars.size();
	}
	
	/**Removes the indicated Scholars from the model using the passed
	 * indices.
	 * 
	 * @param indices the indices indicated which Scholars to remove
	 */
	public synchronized void removeScholars(int[] indices){
		for(int i=(indices.length-1);i>=0;i--){
			scholars.remove(indices[i]);
		}
	}
	
	/**Clears the model of all Scholars.
	 * 
	 */
	public synchronized void emptyScholars(){
		scholars.removeAll(scholars);
		emptyPapers();
		emptySerials();
	}
	
	/**Clears the model of all Papers.
	 * 
	 */
	public synchronized void emptyPapers(){
		for(int i=0;i<papers.size();i++){
			papers.get(i).removePaper();
		}
		papers.removeAll(papers);
	}
	
	/**Adds the passed Conference to the model.
	 * 
	 * @param conf the passed Conference
	 */
	public synchronized void addConference(Conference conf){
		conferences.add(conf);
	}
	
	/**Returns the indicated Conference using the passed index.
	 * 
	 * @param index the index indicated the desired Conference
	 * @return the indicated Conference
	 */
	public Conference getConference(int index){
		return conferences.get(index);
	}
	
	/**Return the amount of Conferences the model contains.
	 * 
	 * @return the size of the Conference ArrayList
	 */
	public int getConferenceListSize(){
		return conferences.size();
	}
	
	/**Returns the Meeting indicated by using the 'enlarged' index, along
	 * with the Conference in which it was found.
	 * 
	 * @param index the index indicated the desired Meeting
	 * @return a generic Object ArrayList containing the Meeting and parent Conference
	 */
	public ArrayList<Object> getSelectedConferenceMeeting(int index){
		ArrayList<Object> returnStuff = new ArrayList<Object>();
		for(int i=0;i<conferences.size();i++){
			if(index >= conferences.get(i).getMeetingListSize()){
				index -= conferences.get(i).getMeetingListSize();
			}
			else{
				returnStuff.add(conferences.get(i).getMeeting(index));
				returnStuff.add(conferences.get(i));
				return returnStuff;
			}
		}
		returnStuff.add(conferences.get(0).getMeeting(0));
		returnStuff.add(conferences.get(0));
		return returnStuff;
	}
	
	/**Returns the Meetings contained in all of the Conferences.
	 * 
	 * @return the Meetings contained in all of the Conferences
	 */
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
	
	/**Checks if the passed Conference is present in the model.
	 * 
	 * @param conf the Conference to check
	 * @return whether the Conference is present
	 */
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
	
	/**Adds the passed Journal to the model.
	 * 
	 * @param jour the passed Journal
	 */
	public synchronized void addJournal(Journal jour){
		journals.add(jour);
	}
	
	/**Returns the indicated Journal using the passed index.
	 * 
	 * @param index the index indicated the desired Journal
	 * @return the indicated Journal
	 */
	public Journal getJournal(int index){
		return journals.get(index);
	}
	
	/**Return the amount of Journals the model contains.
	 * 
	 * @return the size of the Journal ArrayList
	 */
	public int getJournalListSize(){
		return journals.size();
	}
	
	/**Returns the Issue indicated by using the 'enlarged' index, along
	 * with the Journal in which it was found.
	 * 
	 * @param index the index indicated the desired Issue
	 * @return a generic Object ArrayList containing the Issue and parent Journal
	 */
	public ArrayList<Object> getSelectedJournalIssue(int index){
		ArrayList<Object> returnStuff = new ArrayList<Object>();
		for(int i=0;i<journals.size();i++){
			for(int j=0;j<journals.get(i).getVolumeSize();j++){
				if(index >= journals.get(i).getVolume(j).getSizeOfIssueList()){
					index -= journals.get(i).getVolume(j).getSizeOfIssueList();
				}
				else{
					returnStuff.add(journals.get(i).getVolume(j).getIssue(index));
					returnStuff.add(journals.get(i));
					return returnStuff;
				}
			}
		}
		returnStuff.add(journals.get(0).getVolume(0).getIssue(0));
		returnStuff.add(journals.get(0));
		return returnStuff;
	}
	
	/**Returns the Issues contained in all of the Journals.
	 * 
	 * @return the Issues contained in all of the Journals
	 */
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
	
	/**Checks if the passed Journal is present in the model.
	 * 
	 * @param jour the Journal to check
	 * @return whether the Journal is present
	 */
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
	
	/**Removes the passed Conference from the model if it exists.
	 * 
	 * @param conf the passed Conference
	 */
	public synchronized void removeSerial(Conference conf){
		conferences.remove(conf);
	}
	
	/**Removes the passed Journal from the model if it exists.
	 * 
	 * @param jour the passed Journal
	 */
	public synchronized void removeSerial(Journal jour){
		journals.remove(jour);
	}
	
	/**Removes either Conferences or Journals based on the passed indices.
	 * 
	 * @param indices the indices of the serials to be deleted
	 */
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

	/**Clears the model of all serials.
	 * 
	 */
	public synchronized void emptySerials(){
		conferences.removeAll(conferences);
		journals.removeAll(journals);
	}
	
	/**Adds the passed Paper to the model.
	 * 
	 * @param pape the passed Paper
	 */
	public synchronized void addPaper(Paper pape){
		papers.add(pape);
	}
	
	/**Returns the indicated Paper by using the passed index.
	 * 
	 * @param index the index indicated the desired Paper
	 * @return the indicated Paper
	 */
	public Paper getPaper(int index){
		return papers.get(index);
	}
	
	/**Return the amount of Papers contained within the model.
	 * 
	 * @return the size of the Paper ArrayList
	 */
	public int getPaperListSize(){
		return papers.size();
	}
	
	/**Checks if the passed Paper is already contained in the model.
	 * 
	 * @param pape the Paper to check
	 * @return whether the Paper is already present
	 */
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

	/**Returns the Papers contained within the passed Scholar as a string.
	 * 
	 * @param scholar the Scholar to get the Papers from
	 * @return the Papers contained in the Scholar
	 */
	public String getPaperTitlesForAuthor(Scholar scholar){
		String publishedPapers = "";
		for(int index = 0; index < papers.size(); ++index){
			if(papers.get(index).containsScholar(scholar)){
				publishedPapers += papers.get(index).getTitleOfPaper() + "\n";
			}
		}
		
		return publishedPapers;
	}
	
	/**Returns the Papers contained within the passed Scholar.
	 * 
	 * @param scholar the Scholar to get the Papers from
	 * @return the Papers contained in the Scholar
	 */
	public ArrayList<Paper> getPapersForAuthor(Scholar scholar){
		ArrayList<Paper> listOfPapers = new ArrayList<Paper>();
		for(int index = 0; index < papers.size(); ++index){
			if(papers.get(index).containsScholar(scholar)){
				listOfPapers.add(papers.get(index));
			}
		}
		
		return listOfPapers;
	}
	
	/**Remove the Papers indicated by the passed indices from the model.
	 * 
	 * @param indices the indices indicated the Papers to remove
	 */
	public synchronized void removePapers(int[] indices){
		for(int i=indices.length-1;i>=0;i--){
			papers.get(indices[i]).removePaper();
			papers.remove(indices[i]);
		}
	}
	
	/**Removes the passed Paper from the model if it exists.
	 * 
	 * @param pape the passed Paper
	 */
	public synchronized void removePaper(Paper pape){
		papers.remove(pape);
	}
	
	/**Returns this model in a generic Object ArrayList.
	 * 
	 * @return this model
	 */
	public ArrayList<Object> getModel(){
		ArrayList<Object> allData = new ArrayList<Object>();
		allData.add(scholars);
		allData.add(conferences);
		allData.add(journals);
		allData.add(papers);
		
		return allData;
	}
	
	/**Sets this model to the data contained in the passed generic Object
	 * ArrayList
	 * 
	 * @param loadedData the data to set the model to
	 */
	public void setModel(ArrayList<Object> loadedData){
		scholars = (ArrayList<Scholar>)loadedData.get(0);
		conferences = (ArrayList<Conference>)loadedData.get(1);
		journals = (ArrayList<Journal>)loadedData.get(2);
		papers = (ArrayList<Paper>)loadedData.get(3);
	}
}
