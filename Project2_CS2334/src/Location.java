import java.io.Serializable;

/**A data-oriented class. Locations are places.
 * 
 * @version 1.0
 */
public class Location implements Serializable{
	
	private static final long serialVersionUID = 9076040722140323720L;
	private String cityName;
	private String state;
	private String countryName;
	
	/**The constructor for Location objects.
	 * 
	 * @param city the city
	 * @param stateName the state or province
	 * @param country the country
	 */
	public Location(String city, String stateName, String country){
		this.cityName = city;
		this.state = stateName;
		this.countryName = country;
	}
	
	/**Overrides the inherited equals() method from Object. Uses
	 * every value as the basis for comparison.
	 * 
	 * @param locate the Location to compare to
	 * @return whether they equal each other
	 */
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
	
	/**Returns the city name.
	 * 
	 * @return the city
	 */
	public String getCityName(){
		return cityName;
	}
	
	/**Returns the state or province name.
	 * 
	 * @return the state or province
	 */
	public String getState(){
		return state;
	}
	
	/**Returns the country name.
	 * 
	 * @return the country
	 */
	public String getCountryName(){
		return countryName;
	}
	
	/**Overrides the inherited toString() method from Object. Returns
	 * the city, state/province, and country.
	 * 
	 * @return the city, state/province, and country
	 */
	public String toString(){
		return (cityName+", "+state+", "+countryName);
	}
}
