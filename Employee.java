package Objects;
public class Employee {
	
	private String username;
	private String employeeID;
	private String password;
	
	public Employee() {
		this.username= "Unknown";
		this.employeeID= "Unknown";
		this.password= "None";		
	}
	
	public Employee(String Username, String Id, String password) {
		this.username= Username;
		this.employeeID= Id;
		this.password= password;
			
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmployeeID() {
		return this.employeeID;
	}
	
	public void setPassword(String password) {
		this.password= password;
	}
	
	public boolean isPassword(String password) {
		return password.equals(this.password);
	}
	
	@Override public String toString() {
		return ""+ this.username;
	}

}
