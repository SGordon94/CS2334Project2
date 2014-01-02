import java.io.Serializable;
import java.util.*;

/**A data-oriented class. Journals are a type of serial.
 * 
 * @version 1.0
 */
public class Journal implements Serializable{
	
	private static final long serialVersionUID = 5796341236658134402L;
	private String nameOfOrganization;
	private Location location;
	private ArrayList<Volume> volumes;
	
	/**The constructor for Journal objects.
	 * 
	 * @param organization the name of the Journal organization
	 * @param locate the location this Journal takes place in
	 * @param vols the Volume ArrayList for this Journal
	 */
	public Journal(String organization, Location locate, ArrayList<Volume> vols){
		this.nameOfOrganization = organization;
		this.location = locate;
		this.volumes = vols;
	}
	
	/**Overrides the inherited equals() method from Object. Uses
	 * the organization name and Location as the basis for comparison.
	 * 
	 * @param jour the passed Journal to compare to
	 * @return whether the passed Journal equals this one
	 */
	public boolean equals(Journal jour){
		if(this.nameOfOrganization.equals(jour.getOrganization())){
			if(this.location.equals(jour.getLocation())){
				return true;
			}
		}
		return false;
	}
	
	/**Returns the organization name.
	 * 
	 * @return the organization name.
	 */
	public String getOrganization(){
		return nameOfOrganization;
	}
	
	/**Returns the Location.
	 * 
	 * @return the location.
	 */
	public Location getLocation(){
		return location;
	}
	
	/**Returns the indicated Volume.
	 * 
	 * @param index the index of the Volume to return
	 * @return the indicated Volume
	 */
	public Volume getVolume(int index){
		return volumes.get(index);
	}
	
	/**Returns the Volume ArrayList
	 * 
	 * @return the ArrayList of volumes
	 */
	public ArrayList<Volume> getVolumes(){
		return volumes;
	}
	
	/**Returns the amount of volumes contained in this Journal.
	 * 
	 * @return the amount of volumes within this Journal
	 */
	public int getVolumeSize(){
		return volumes.size();
	}
	
	/**Overrides the inherited toString() method from Object. Returns
	 * the name of the organization and the Location.
	 * 
	 * @return the name of the organization and Location
	 */
	public String toString(){
		return (nameOfOrganization+" // "+location.toString());
	}
}
