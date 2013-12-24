import java.io.Serializable;


public class Location implements Serializable{
	
	private static final long serialVersionUID = 9076040722140323720L;
	private String cityName;
	private String state;
	private String countryName;
	
	public Location(String city, String stateName, String country){
		this.cityName = city;
		this.state = stateName;
		this.countryName = country;
	}
	
	public boolean equals(Location locate){
		if(this.cityName.equals(locate.getCityName())){
			if(this.state.equals(locate.getState())){
				if(this.countryName.equals(locate.getCountryName())){
					return true;
				}
			}
		}
		return false;
	}
	
	public String getCityName(){
		return cityName;
	}
	
	public String getState(){
		return state;
	}
	
	public String getCountryName(){
		return countryName;
	}
	
	public String toString(){
		return (cityName+", "+state+", "+countryName);
	}
}
