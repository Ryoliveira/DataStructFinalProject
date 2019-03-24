import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import Objects.*;

public class Interface {
	private ArrayList<Employee> EmployeeDirectory = new ArrayList<Employee>();
	private ArrayList<Order> CurrentOrder = new ArrayList<Order>();

	public void currentOrder() throws IOException {
		System.out.println("\n****CURRENT ORDERS****\n");
		File orderInfile = new File("currentOrders.txt");
		Scanner in = new Scanner(orderInfile);
		while (in.hasNextLine()) {
			String orderNum = in.nextLine();
			String numItems = in.nextLine();
			String status = in.nextLine();
			System.out.println(
					"Order Number: " + orderNum + "\nNumber of Items: " + numItems + "\nStatuse: " + status + "\n");
		}
	}

	public void addSongs() throws IOException {
		File EmployeeInfile = new File("employeeDirectory.txt");
		Scanner in = new Scanner(EmployeeInfile);
		while (in.hasNextLine()) {
			String employeeName = in.nextLine();
			String ID = in.nextLine();
			String password = in.nextLine();
			Employee e = new Employee(employeeName, ID, password);
			EmployeeDirectory.add(e);
		}
	}

	public void addEmployee() throws IOException {
		File EmployeeInfile = new File("employeeDirectory.txt");
		Scanner in = new Scanner(EmployeeInfile);
		while (in.hasNextLine()) {
			String employeeName = in.nextLine();
			String ID = in.nextLine();
			String password = in.nextLine();
			Employee e = new Employee(employeeName, ID, password);
			EmployeeDirectory.add(e);
		}
	}

	public void WelcomeScreen() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nAre you login in as an employee? Or as a customer?\n" + "E: Employee\n" + "C: Customer\n");
		System.out.print("Enter your choice: ");
		String choice = input.next();
		while (!choice.equalsIgnoreCase("E") && !choice.equalsIgnoreCase("C")) {
			System.out.println("Please choose E for employee or C for customer ");
			System.out.print("\nEnter your choice: ");
			choice = input.next();
		}
		if (choice.equalsIgnoreCase("E")) {
			try {
				addEmployee();
			} catch (IOException e) {
				System.out.println("Employee directory not found");
			}
			empLogin();
		} else if (choice.equalsIgnoreCase("C")) {

		}
	}

	public void empLogin() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your user name: ");
		String Username = input.nextLine();
		System.out.print("Please enter your employee ID: ");
		String employeeID = input.nextLine();
		System.out.print("Please enter your password: ");
		String password = input.nextLine();
		empCheck(Username, employeeID, password);
	}

	public void empCheck(String UserName, String employeeID, String password) {
		Scanner input = new Scanner(System.in);
		boolean match = false;
		for (int i = 0; i < EmployeeDirectory.size(); i++) {
			if (EmployeeDirectory.get(i).getEmployeeID().equalsIgnoreCase(employeeID)
					&& EmployeeDirectory.get(i).getUsername().equalsIgnoreCase(UserName)
					&& EmployeeDirectory.get(i).isPassword(password) == true) {
				match = true;
				System.out.println("\nWelcome back " + EmployeeDirectory.get(i).getUsername()
						+ "! What do you like to do today?\n");
				empMenu();
			}
		}
		if (match == false) {
			System.out.print("Looks like you entered the wrong password, would you like to reset your password?(Y/N) ");
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("y")) {
				System.out.print("Please enter your user name: ");
				String Username = input.nextLine();
				System.out.print("Please enter your employee ID: ");
				String ID = input.nextLine();
				for (int i = 0; i < EmployeeDirectory.size(); i++) {
					if (EmployeeDirectory.get(i).getEmployeeID().equalsIgnoreCase(ID)
							&& EmployeeDirectory.get(i).getUsername().equalsIgnoreCase(UserName)) {
						System.out.println("Enter new password " + EmployeeDirectory.get(i).getUsername());
						String newPassword = input.nextLine();
						EmployeeDirectory.get(i).setPassword(newPassword);
						System.out
								.println(EmployeeDirectory.get(i).getUsername() + " you're password has been changed!");
						empCheck(UserName, employeeID, newPassword);

					}else {
						System.out.println("Could not find any employee with the matching username and ID. Please try again!");
						WelcomeScreen();
					}
				}

			}

		}
	}

	public void viewOrders() {

	}

	public void empMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("**Main Menu**\n\n" + "1. View Current Orders \n" + "2. View shipment(Ship an order)\n"
				+ "3. View Store Inventory\n" + "4. View Customers information\n" + "5. Search for a customer by name\n"
				+ "6. Search, Add or Remove from the inventory\n" + "7. Exit");
		System.out.print("\nPlease enter your choice(1-7):");
		int choice = input.nextInt();
		if (choice == 1) {
			try {
				currentOrder();
			} catch (IOException e) {
				System.out.print("No current orders in processing");
			}
		} else if (choice == 2) {

		} else if (choice == 3) {
			SongsDatabase s= new SongsDatabase();
			try {
				s.populateLibary();
				s.displayPrimary();
			} catch (IOException e) {
				System.out.print("No items to display!");
			}

		} else if (choice == 4) {

		} else if (choice == 5) {

		} else if (choice == 6) {
			SongsDatabase s= new SongsDatabase();
			try {
				s.populateLibary();	
			} catch (IOException e) {
				System.out.print("No items to add/remove!");
			}
			boolean loop = true;
			while (loop) {
			
			System.out.println("Please select from one of the following options:\n");
			System.out.println("A. Add a song\nD. Display songs\nR. Remove a song\nS. Search for a song\nX. Exit\n");
			System.out.print("Enter your choice: ");
			String choice1 = input.nextLine();
			if (!choice1.matches("(?i)A|D|R|X|S")) {
						System.out.println("\nInvalid Selection!\n");
			}
			else if (choice1.matches("(?i)A")) {
				System.out.println("\nAdding a song!\n");
				s.addSong(input);
			} else if (choice1.matches("(?i)D")) {
				System.out.println("\nDisplaying Song Database:\n");
				boolean flag = true;
				while (flag) {
					System.out.println("Do you want to list products sorted by primary key or by secondary key?");
					System.out.println("P. Primary key\nS. Secondary key\n");
					System.out.print("Enter your desire option: ");
					String option = input.nextLine();
					if (!option.matches("(?i)P|S")) {
						System.out.println("\nInvalid Selection!\n");
					} else if (option.matches("(?i)P")) {
						flag = false;
						s.displayPrimary();
					} else {
						flag = false;
						s.displaySecondary();
					}
				}
			} else if (choice1.matches("(?i)R")) {
				System.out.println("\nRemoving a song!\n");
				s.removeSong();
			} else if (choice1.matches("(?i)S")) {
				System.out.println("\nSearching for a song!\n");
				boolean dummy = true;
				while (dummy) {
					System.out.println("Do you want to search by primary key or by secondary key?");
					System.out.println("P. Primary key\nS. Secondary key\n");
					System.out.print("Enter your desire option: ");
					String decision = input.nextLine();
					if (!decision.matches("(?i)P|S")) {
						System.out.println("\nInvalid Selection!\n");
					} 
					else if (decision.matches("(?i)P")) {
						dummy = false;
						System.out.println();
						s.searchSongPrimary();
					} 
					else {
						dummy = false;
						System.out.println();
						s.searchSongSecondary();
					}	
				}
			}
			else if (choice1.matches("(?i)X")) {
				loop = false;
				System.out.println("\nGoodbye!");
			}
		}
			}

		else if (choice == 7) {
			WelcomeScreen();
		}
		empMenu();
	}

	
	public static void main(String[] args) {
		System.out.println("********************Hello! Welcome to Java Music store!********************\n\n");
		Interface i = new Interface();
		i.WelcomeScreen();
	}

}

	}

