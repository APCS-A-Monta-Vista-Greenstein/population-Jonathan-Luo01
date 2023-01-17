/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Jonathan Luo
 *	@since	January 9, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name, state, designation;
	private int population;
	
	// constructor
	
	public City(String s, String n, String d, int p) {
		state = s;
		name = n;
		designation = d;
		population = p;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compareTo(City other) {
		 if(this.getPopulation() != other.getPopulation()) return this.population-other.population;
		 else if(!(this.getState().equals(other.getState()))) return this.getState().compareTo(other.getState());
		 else return this.getName().compareTo(other.getName());
	 }
	 
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other) {
		 return (this.getName().equals(other.getName()) && this.getState().equals(other.getState()));
	 }
	
	/**	Accessor methods */
	public String getName() { return name; }
	
	public String getState() { return state; } 
	
	public String getDesignation() { return designation; }
	
	public int getPopulation() { return population; }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
