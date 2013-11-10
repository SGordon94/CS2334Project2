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
	
	public Authors(){
		
	}
	
	public Authors(String secondaryName, String primaryName){
		this.secondaryName = secondaryName;
		this.primaryName = primaryName;
	}
	
	public Authors(String primaryName){
		this.primaryName = primaryName;
	}
	
	public Authors makeAuthors(String fullName){
		
		String[] names = fullName.split(",");
		
		System.out.print(names[1]);
		return new Authors(names[0], names[1]);
		
		//for(String seperateNames : names)
			//System.out.println(seperateNames);
		//String[] wholeName = fullName.split(",");
		//String[] names = new String[1];
		
		//for(int index = 0; index < wholeName.length; ++index){
		//names[index] = wholeName[index];
		//	System.out.print(names[index]);
		//}
	}
	
	public Authors makeSingleNameAuthors(String fullName){
		return new Authors(fullName);
	}
	
	public void setContainingPaper(Paper containingPaper){
		this.containingPapers.add(containingPaper);
	}
	
	public String returnNameInString(){
		return (primaryName+","+secondaryName); 
	}
}
