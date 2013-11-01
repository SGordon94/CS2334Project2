import java.io.Serializable;
//

public class Authors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6435144183026980478L;

	private String secondaryName;
	private String primaryName;
	private String[] fullName;
	
	public Authors(String fullName){
		this.fullName = fullName.split(",");
		this.primaryName = this.fullName[0];
		this.secondaryName = this.fullName[1];
	}
}
