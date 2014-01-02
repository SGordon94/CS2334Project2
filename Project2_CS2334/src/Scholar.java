import java.io.Serializable;
import java.util.ArrayList;

/**A data-oriented class. Scholars are the people used in this program.
 * 
 * @version 1.0
 */
public class Scholar implements Serializable {
    private static final long serialVersionUID = -6435144183026980478L;

    private ArrayList<Paper> containingPapers = new ArrayList<Paper>();
    private boolean isAuthor = false;
    private String secondaryName;
    private String primaryName;
    private ArrayList<String> institutionalAffiliations = new ArrayList<String>();
    private ArrayList<String> researchAreas = new ArrayList<String>();
    
    /**A constructor for Scholar objects.
     * 
     * @param fullName the complete name of the Scholar
     */
    public Scholar(String fullName){
            String[] wholeName = fullName.split(",");
            if(wholeName.length == 2){
            	this.primaryName = wholeName[0];
            	primaryName = primaryName.trim();
                this.secondaryName = wholeName[1];
                secondaryName = secondaryName.trim();
            }
            else{
            	this.primaryName = wholeName[0];
            }
    }
    
    /**A constructor for Scholar objects.
     * 
     * @param primary the primary name of the Scholar
     * @param secondary the secondary name of the Scholar
     * @param affiliations the Scholar's affiliations
     * @param researchPlaces the places of research for the Scholar
     */
    public Scholar(String primary, String secondary, String[] affiliations, String[] researchPlaces){
    	this.primaryName = primary;
    	this.secondaryName = secondary;
    	for(int i=0;i<affiliations.length;i++){
    		institutionalAffiliations.add(affiliations[i]);
    	}
    	for(int i=0;i<researchPlaces.length;i++){
    		researchAreas.add(researchPlaces[i]);
    	}
    }
    
    /**Adds a paper that contains this Scholar to the local ArrayList
     * of papers.
     * 
     * @param containingPaper the paper to add
     */
    public void setContainingPaper(Paper containingPaper){
    	this.containingPapers.add(containingPaper);
    }
    
    /**Returns the name of this Scholar in a string.
     * 
     * @return the name of this Scholar
     */
    public String returnNameInString(){
    	if(secondaryName == null){
    		return primaryName;
    	}
    	else{
    		return (primaryName+", "+secondaryName);
    	}
    }
    
    /**Returns the name of this Scholar in a string. Returns in a different
     * format than the method returnNameInString().
     * 
     * @return the name of this Scholar
     */
    public String returnNameInStringAlt(){
    	if(secondaryName == null){
    		return primaryName;
    	}
    	else{
    		return (secondaryName + " " + primaryName);
    	}
    }
    
    /**Returns the papers this Scholar belongs to as a string.
     * 
     * @return the papers this Scholar belongs to
     */
    public String displayPapers(){
    	String output = "";
    	for(int i=0;i<containingPapers.size();i++){
    		output += containingPapers.get(i).getPaper();
    		if (i != containingPapers.size() - 1){
                output += "\n\n";
    		}
    	}
    	return output;
    }
    
    /**Returns this Scholar's affiliations.
     * 
     * @return this Scholar's affiliations
     */
    public ArrayList getAffiliations(){
    	return this.institutionalAffiliations;
    }
    
    /**Returns this Scholar's research areas.
     * 
     * @return this Scholar's research areas.
     */
    public ArrayList getResearchAreas(){
    	return this.researchAreas;
    }
    
    /**Returns the ArrayList of papers that this Scholar belongs to.
     * 
     * @return the ArrayList of papers
     */
    public ArrayList<Paper> getPapers(){
    	return this.containingPapers;
    }
    
    /**Returns the indicated paper using the passed index.
     * 
     * @param index the index indicated the desired paper
     * @return the indicated paper
     */
    public Paper getPaper(int index){
    	return containingPapers.get(index);
    }
    
    /**Returns the size of the ArrayList containing the papers that
     * this Scholar belongs to.
     * 
     * @return the size of the Paper ArrayList
     */
    public int getPaperListSize(){
    	return containingPapers.size();
    }
}