import java.io.Serializable;
import java.util.ArrayList;


public class Authors implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = -6435144183026980478L;

        private ArrayList<Paper> containingPapers = new ArrayList<Paper>();
        private String secondaryName;
        private String primaryName;
        
        public Authors(String fullName){
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
        
        public void setContainingPaper(Paper containingPaper){
                this.containingPapers.add(containingPaper);
        }
        
        public String returnNameInString(){
                return (primaryName+", "+secondaryName);
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
}