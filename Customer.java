/**
* Customer.java
* @author Ryan Oliveira 
* CIS 22C
*/
public class Customer implements Comparable<Customer> {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
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
	 * @param uUserName Customer userName
	 * @param uPassword Customer password
	 * @param uAddress Customer address
	 * @param uCity Customer city
	 * @param uState Customer state
	 * @param uZip Customer zip code
	 */
    public Customer(String uFirst, String uLast, String uUserName, String uPassword, String uEmail, String uAddress, String uCity, String uState, int uZip) {
    	firstName = uFirst;
    	lastName = uLast;
    	userName = uUserName;
    	password = uPassword;
    	email = uEmail;
    	address = uAddress;
    	city = uCity;
    	state = uState;
    	zip = uZip;
    	List<Order> orders = new List<Order>();
    }
    
    /**
	 * Secondary constructor for the Customer.java class. Initializes the Customer with first and last name
	 * for data storage searching purposes.
	 * 
	 * @param uFirst Customer first name
	 * @param uLast Customer last name
	 */
    public Customer(String ufirst, String uLast) {
    	this(ufirst, uLast, "", "", "", "", "", "", 0);
    }
    
    /* Accessors */
    
    /**
     * @return firstName First name of customer
     */
	public String getFirstName() {
		return firstName;
	}
	
	/**
     * @return lastName Last name of customer
     */
	public String getLastName() {
		return lastName;
	}
	
	/**
     * @return userName User name of customer
     */
	public String getUserName() {
		return userName;
	}
	
	/**
     * @return password Password of customer
     */
	public String getPassword() {
		return password;
	}
	
	/**
     * @return email Email of customer
     */
	public String getEmail() {
		return email;
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
     * @return zip Customer's current zip code of residence
     */
	public int getZip() {
		return zip;
	}
	
	/**
     * @return city Customer's current city of residence
     */
	public String getCity() {
		return city;
	}
	
	 /**
     * @return state Customer's current state of residence
     */
	public String getState() {
		return state;
	}
	
	/* Mutators */

	/**
	 * Sets the first name of customer
	 * 
	 * @param first_name Name to be set
	 */
	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}
	
	/**
	 * Sets the last name of customer
	 * 
	 * @param last_name Name to be set
	 */
	public void setLastName(String last_name) {
		this.lastName = last_name;
	}
	
	/**
	 * Sets the user name of the the customer
	 * 
	 * @param userName User name to be set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Sets the password of the the customer
	 * 
	 * @param password Password to be set
	 */
	public void setPassWord(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the password of the the customer
	 * 
	 * @param email Email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Sets the customer's city of residence
	 * 
	 * @param city City to be set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * Sets zip code of customer.
	 * 
	 * @param zip Zip code to be set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	/* Order List Operations */
	
    /**
     * @return orders Customer's current list of orders
     */
	public List<Order> getOrders() {
		return orders;
	}
	
	/**
	 * Add order to customer orders
	 * @param newOrder Order to be added
	 */
	public void addOrder(Order newOrder) {
		orders.addLast(newOrder);
	}
	
	/**
	 * Delete order from customer orders
	 * @param OrdToDel Order to delete
	 */
	public void removeOrder(Order OrdToDel) {
		orders.pointIterator();
		while(!orders.offEnd()) {
			if(orders.getIterator() == OrdToDel) {
				orders.removeIterator();
			}
			orders.advanceIterator();
		}
	}
	
	/* Additional Methods */
	
	/**
	 * Returns customer information in a formatted string
	 * 
	 * @return formatted string
	 */
	@Override
	public String toString() {
		return "Name: " + firstName + " " + lastName
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
		String key = firstName + lastName;
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
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
		else if(!this.firstName.equals(otherCust.firstName)) {
			return this.firstName.compareTo(otherCust.firstName);
		}
		else {
			return this.lastName.compareTo(otherCust.lastName);
		}
	}
}
