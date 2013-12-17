import java.util.*;


public class Journal {
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
	
	public ArrayList<Volume> getVolumes(){
		return volumes;
	}
}
