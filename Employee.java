
public class Employee {
	
	private String username;
	private String employeeID;
	private String password;
	
	public Employee() {
		this.username= "Unknown";
		this.employeeID= "Unknown";
		this.password= "None";		
	}
	
	public Employee(String Username, String employeeId, String password) {
		this.username= Username;
		this.employeeID= employeeID;
		this.password= this.password;
			
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmployeeID() {
		return this.employeeID;
	}
	
	public boolean isPassword(String password) {
		return password.equals(this.password);
	}

}
