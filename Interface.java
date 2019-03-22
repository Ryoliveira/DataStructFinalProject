import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Interface extends Employee{
	public void addEmployee() throws IOException{
		File EmployeeInfile= new File("employeeDirectory.txt");
        Scanner in=new Scanner(EmployeeInfile);
        ArrayList<Employee> EmployeeDirectory= new ArrayList<Employee>();
        while (in.hasNextLine()) {
        	String employeeName= in.nextLine();
        	String ID= in.nextLine();
        	String password= in.nextLine();
        	Employee e= new Employee(employeeName, ID, password);
        	EmployeeDirectory.add(e);
        }
	}
	
	public void WelcomeScreen() {
		Scanner input = new Scanner(System.in);
		System.out.println("********************Hello! Welcome to Java Music store!********************\n\n");
        System.out.println("Are you login in as an employee? Or as a customer?\n"
        		+ "E: Employee\n" + 
        		  "C: Customer\n");
        System.out.print("Enter your choice: ");
        String choice= input.next();
        while ( !choice.equalsIgnoreCase("E") && !choice.equalsIgnoreCase("C")) {
        	System.out.println("Please choose E for employee or C for customer ");
        	 System.out.print("\nEnter your choice: ");
        	choice= input.next();
        }	
        if (choice.equalsIgnoreCase("E")) {
        	 empLogin(choice);	
        
        }
        else if (choice.equalsIgnoreCase("C")) {
        	cusLogin(choice);
        }
	}
	
	public void empLogin(String e) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your user name: ");
    	String Username = input.next();
    	System.out.println("Please enter your employee ID: ");
    	String employeeID = input.next();
    	System.out.println("Please enter your password: ");
    	String password= input.next();
	}
	
	public void cusLogin(String c) {
		Scanner input = new Scanner(System.in);
		System.out.println("**Main Menu**\n\n"
				+ "1. View Current Orders \n"
				+ "2. View shipment(Ship an order)\n"
				+ "3. View Store Inventory\n"
				+ "4.View Customers information\n"
				+ "5. Search for a customer by name\n"
				+ "6. Add or Remove from the inventory\n"
				+ "7. Exit");
		System.out.println("Please enter your user name: ");
    	String Username = input.next();
    	System.out.println("Please enter your employee ID: ");
    	String employeeID = input.next();
    	System.out.println("Please enter your password: ");
    	String password= input.next();
	}

	public static void main(String[] args) {
		Interface i= new Interface();
		try {
			i.addEmployee();
			}catch(IOException e) {
				System.out.println("Employee directory not found");
			}
				
		i.WelcomeScreen();
        }
        	  
	}

