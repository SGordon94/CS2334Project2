import java.io.Serializable;
import java.util.*;


public class Journal implements Serializable{
	
	private static final long serialVersionUID = 5796341236658134402L;
	private String nameOfOrganization;
	private Location location;
	private ArrayList<Volume> volumes;
	
	public Journal(String organization, Location locate, ArrayList<Volume> vols){
		this.nameOfOrganization = organization;
		this.location = locate;
		this.volumes = vols;
	}
	
	public boolean equals(Journal jour){
		if(this.nameOfOrganization.equals(jour.getOrganization())){
			if(this.location.equals(jour.getLocation())){
				return true;
			}
		}
		return false;
	}
	
	public String getOrganization(){
		return nameOfOrganization;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public Volume getVolume(int index){
		return volumes.get(index);
	}
	
	public ArrayList<Volume> getVolumes(){
		return volumes;
	}
	
	public int getVolumeSize(){
		return volumes.size();
	}
	
	public String toString(){
		return (nameOfOrganization+" // "+location.toString());
	}
}
