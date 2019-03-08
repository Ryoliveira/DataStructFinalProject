
public class Customer implements Comparable<Customer> {

	private String first_name;
	private String last_name;
	private String address;
    private String city;
    private String state;
    private int zip; 
    private List<Order> orders;
    
    public Customer(String first, String last, String add, String cit, String st, int z) {
    	first_name = first;
    	last_name = last;
    	address = add;
    	city = cit;
    	state = st;
    	zip = z;
    	List<Order> orders = new List<Order>();
    }
    
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	@Override
	public String toString() {
		return "Name: " + first_name + " " + last_name
				+ "\nAddress: " + address
			    + "\nCity: " + city
			    + "\nState: " + state
			    + "\nZip: " + zip;
	}
	
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
