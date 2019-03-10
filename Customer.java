
public class Customer implements Comparable<Customer> {

	private String first_name;
	private String last_name;
	private String address;
    private String city;
    private String state;
    private int zip; 
    private List<Order> orders;
    
	/**
	 * Constructor for the Customer.java class. Initializes the Customer to be filled in
	 * with user provided information. Sets new empty orders list.
	 * 
	 * @param uFirst Customer first name
	 * @param uLast Customer last name
	 * @param uAddress Customer address
	 * @param uCity Customer city
	 * @param uState Customer state
	 * @param uZip Customer zip code
	 */
    public Customer(String uFirst, String uLast, String uAddress, String uCity, String uState, int uZip) {
    	first_name = uFirst;
    	last_name = uLast;
    	address = uAddress;
    	city = uCity;
    	state = uState;
    	zip = uZip;
    	List<Order> orders = new List<Order>();
    }
    
    /**
     * @return first_name First name of customer
     */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Sets the first name of customer
	 * 
	 * @param first_name Name to be set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
    /**
     * @return last_name Last name of customer
     */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * Sets the last name of customer
	 * 
	 * @param last_name Name to be set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

    /**
     * @return address Customer's address of residence
     */
	public String getAddress() {
		return address;
	}
	
	public String getFullAddress() {
		return address + ", " + city + ", " + state;
	}

	/**
	 * Sets the current address of the the customer
	 * 
	 * @param address Address to be set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

    /**
     * @return city Customer's current city of residence
     */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the customer's city of residence
	 * 
	 * @param city City to be set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
    /**
     * @return state Customer's current state of residence
     */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state of customer
	 * 
	 * @param state State to be set
	 */
	public void setState(String state) {
		this.state = state;
	}

    /**
     * @return zip Customer's current zip code of residence
     */
	public int getZip() {
		return zip;
	}

	/**
	 * Sets zip of customer.
	 * 
	 * @param zip Zip to be set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	
    /**
     * @return orders Customer's current list of orders
     */
	public List<Order> getOrders() {
		return orders;
	}
	
	/**
	 * Returns customer information in a formatted string
	 * 
	 * @return formatted string
	 */
	@Override
	public String toString() {
		return "Name: " + first_name + " " + last_name
				+ "\nAddress: " + address
			    + "\nCity: " + city
			    + "\nState: " + state
			    + "\nZip: " + zip;
	}
	
	/**
	 * Creates a hashCode for object to be stored in hashTable
	 * 
	 * @returns hashCode of current object
	 */
	@Override
	public int hashCode() {
		int sum = 0;
		final int prime = 31;
		String key = first_name + last_name;
		for(int i=0;i<key.length();i++) {
			sum += prime * key.charAt(i);
		}
		return sum;
	}
	
	/**
	 * Determines if object is equal to current object
	 * 
	 * @param obj Customer object to be compared
	 * @return boolean value true or false depending on comparison results.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		return true;
	}

	/**
	 * Compares customer object to another customer object
	 * 
	 * @param otherCust Other customer object to be compared
	 * @return result int value which determines if otherCust is greater
	 * or less than current customer
	 */
	@Override
	public int compareTo(Customer otherCust) {
		if(this.equals(otherCust)) {
			return 0;
		}
		else if(!this.first_name.equals(otherCust.first_name)) {
			return this.first_name.compareTo(otherCust.first_name);
		}
		else {
			return this.last_name.compareTo(otherCust.last_name);
		}
	}
}
