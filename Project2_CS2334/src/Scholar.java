import java.io.Serializable;
import java.util.ArrayList;


public class Scholar implements Serializable {

        
        private static final long serialVersionUID = -6435144183026980478L;

        private ArrayList<Paper> containingPapers = new ArrayList<Paper>();
        private boolean isAuthor = false;
        private String secondaryName;
        private String primaryName;
        private ArrayList<String> institutionalAffiliations = new ArrayList<String>();
        private ArrayList<String> researchAreas = new ArrayList<String>();
        
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
        
        public void setContainingPaper(Paper containingPaper){
        	this.containingPapers.add(containingPaper);
        }
        
        public String returnNameInString(){
        	if(secondaryName == null){
        		return primaryName;
        	}
        	else{
        		return (primaryName+", "+secondaryName);
        	}
        }
        
        public String returnNameInStringAlt(){
        	if(secondaryName == null){
        		return primaryName;
        	}
        	else{
        		return (secondaryName + " " + primaryName);
        	}
        }
        
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
        
        public ArrayList getAffiliations(){
        	return this.institutionalAffiliations;
        }
        public ArrayList getResearchAreas(){
        	return this.researchAreas;
        }
        public ArrayList<Paper> getPapers(){
        	return this.containingPapers;
        }
        
        public Paper getPaper(int index){
        	return containingPapers.get(index);
        }
        
        public int getPaperListSize(){
        	return containingPapers.size();
        }
        
}