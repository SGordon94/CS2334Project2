import java.io.Serializable;
import java.util.ArrayList;


public class Authors implements Serializable {

	/**.
	 * 
	 */
	private static final long serialVersionUID = -6435144183026980478L;

	private ArrayList<Paper> containingPapers = new ArrayList<Paper>();
	private String secondaryName;
	private String primaryName;
	
	public Authors(String fullName){
		String[] wholeName = fullName.split(",");
		this.primaryName = wholeName[0];
		this.secondaryName = wholeName[1];
	}
	
	public void setContainingPaper(Paper containingPaper){
		this.containingPapers.add(containingPaper);
	}
	
	public String returnNameInString(){
		return (primaryName+","+secondaryName); 
	}
}
