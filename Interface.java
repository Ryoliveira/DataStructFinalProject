import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import Objects.*;
import Objects.Heap;

public class Interface {
	private ArrayList<Employee> EmployeeDirectory = new ArrayList<Employee>();
	private ArrayList<Customer> CustomerDirectory = new ArrayList<Customer>();
//	private ArrayList<Shipment> ShipmentDirectory = new ArrayList<Shipment>();
	SongsDatabase s = new SongsDatabase();
	Heap unShipped = new Heap(100);
	Heap shipped = new Heap(100);
	
	public void addSongs() {
		try {
			s.populateLibary();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchSongs(Scanner sc) {
		System.out.println("\nSearching for a song!\n");
		boolean dummy = true;
		while (dummy) {
			System.out.println("Do you want to search by primary key or by secondary key?");
			System.out.println("P. Primary key\nS. Secondary key\n");
			System.out.print("Enter your desire option: ");
			String decision = sc.nextLine();
			if (!decision.matches("(?i)P|S")) {
				System.out.println("\nInvalid Selection!\n");
			} else if (decision.matches("(?i)P")) {
				dummy = false;
				System.out.println();
				s.searchSongPrimary();
			} else {
				dummy = false;
				System.out.println();
				s.searchSongSecondary();
			}
		}
	}
	
	public void displaySongs(Scanner sc){
		System.out.println("\nDisplaying Song Database:\n");
		boolean flag = true;
		while (flag) {
			System.out.println("Do you want to list products sorted by primary key or by secondary key?");
			System.out.println("P. Primary key\nS. Secondary key\n");
			System.out.print("Enter your desire option: ");
			String option = sc.nextLine();
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
	}
	
////////////////////////////////	
	public static void main(String[] args) throws IOException {
		System.out.println("********************Welcome to Java Music store********************\n\n");
		Interface i = new Interface();
		i.addSongs();
		i.addEmployee();
		i.addCustomer();
		//i.WelcomeScreen();
		i.empMenu();
		
	}
	
	
	
	/**
	 * Welcome Screen
	 * @throws IOException 
	 */
	public void WelcomeScreen() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("\nAre you login in as an employee? Or as a customer?\n" + "E: Employee\n" + "C: Customer\n");
		System.out.print("Enter your choice: ");
		String choice = input.nextLine();
		if (choice.equalsIgnoreCase("E")) {
			empLogin();
		} else if (choice.equalsIgnoreCase("C")) {
			custLogin();
		}
	}
	
	/**
	 * AddEmployee from txt
	 */
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
	/**
	 * @throws IOException 
	 * 
	 */
	public void empLogin() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your user name: ");
		String Username = input.nextLine();
		System.out.print("Please enter your employee ID: ");
		String employeeID = input.nextLine();
		System.out.print("Please enter your password: ");
		String password = input.nextLine();
		System.out.print(Username+ employeeID+ password);
		empCheck(Username, employeeID, password);
		input.close();
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void empCheck(String UserName, String employeeID, String password) throws IOException {
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

					} else {
						System.out.println(
								"Could not find any employee with the matching username and ID. Please try again!");
						WelcomeScreen();
					}
				}

			}

		}
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public void empMenu(){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while(choice!=7) {
			System.out.println("**Main Menu**\n\n" + 
					"1. View Current Orders \n" + 
					"2. View shipment(Ship an order)\n"+
					"3. View Store Inventory\n" + 
					"4. View Customers information\n" + 
					"5. Search for a customer by name\n"+
					"6. Search, Add or Remove from the inventory\n" + 
					"7. Exit");
			System.out.print("\nPlease enter your choice(1-7):");
			choice = sc.nextInt();
			if (choice == 1) {
				try {
					currentOrder();
				} catch (IOException e) {
					System.out.print("No current orders in processing");
				}
			} else if (choice == 2) {
				try {
					Shipment();
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			} else if (choice == 3) {
				sc.nextLine();
				displaySongs(sc);
			} else if (choice == 4) {
				for (int i=0; i<CustomerDirectory.size(); i++) {
					System.out.println("\n");
					System.out.println(CustomerDirectory.get(i));
				}
			} else if (choice == 5) {
				System.out.print("Enter Customer's first name:");
				String custName = sc.next();
				boolean temp= false;
				for (int i=0; i<CustomerDirectory.size(); i++) {
					if (CustomerDirectory.get(i).getFirstName().equalsIgnoreCase(custName)) {
						System.out.println("We found a customer with the entered name! \n\n");
						System.out.println(CustomerDirectory.get(i));
						temp= true;
					}
				}
				if (temp==false) {
					System.out.println("No matches found! ");
				}
			} 
			else if (choice == 6) {
				boolean loop = true;
				while (loop) {

					System.out.println("Please select from one of the following options:\n");
					System.out.println("A. Add a song\n"
							         + "D. Display songs\n"
							         + "R. Remove a song\n"
							         + "S. Search for a song\n"
							         + "X. Exit\n");
					System.out.print("Enter your choice: ");
					String custName = sc.nextLine();
					if (!custName.matches("(?i)A|D|R|X|S")) {
						System.out.println("\nInvalid Selection!\n");
					} else if (custName.matches("(?i)A")) {
						System.out.println("\nAdding a song!\n");
						s.addSong(sc);
					} else if (custName.matches("(?i)D")) {
						displaySongs(sc);
					} else if (custName.matches("(?i)R")) {
						System.out.println("\nRemoving a song!\n");
						s.removeSong();
					} else if (custName.matches("(?i)S")) {
						searchSongs(sc);
					} else if (custName.matches("(?i)X")) {
						loop = false;
						System.out.println("\nReturning to main menu...\n");
					}
				}
			}

			else if (choice == 7) {
				System.out.println("\nGoodbye! ");
			}
		}
		sc.close();
	}
	/**
	 * 
	 */
	public void addCustomer() throws IOException{
		Scanner input = new Scanner(new FileInputStream("CustomerList.txt"), "UTF-8");
		while (input.hasNextLine()) {
		String firstName = input.nextLine();
		firstName = firstName.replaceAll("\\s","");
		String lastName = input.nextLine();
		String email = input.nextLine();
		String userID=email;
		String password = input.nextLine();
		String address = input.nextLine();
		String city = input.nextLine();
		String state = input.nextLine();
		String zip_try= input.nextLine();
		int zip = Integer.parseInt(zip_try);
		Customer c = new Customer(firstName, lastName, userID, password, email, address, city, state, zip);
		CustomerDirectory.add(c);
		
		}
	}
	
	/**
	 * 
	 */
	public void currentOrder() throws IOException {
		System.out.println("\n****CURRENT ORDERS****\n");
		File orderInfile = new File("currentOrders.txt");
		Scanner in = new Scanner(orderInfile);
		while (in.hasNextLine()) {
			String orderNum = in.nextLine();
			int num = Integer.parseInt(orderNum);
			String numItems = in.nextLine();
			int numItem = Integer.parseInt(numItems);
			String priority = in.nextLine();
			int p;
			if(priority.equalsIgnoreCase("overnight")) {
				p = 1;
			}
			else if(priority.equalsIgnoreCase("rush")) {
				p = 2;
			}
			else{
				p = 3;
			}
			int year = in.nextInt();
			int month = in.nextInt();
			int day = in.nextInt();
			in.nextLine();
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, day);
			String status = in.nextLine();
			Order o = new Order(num, numItem, p, cal, status);
			if(status.equalsIgnoreCase("shipped")) {
				shipped.insert(o);
			}
			else {
				unShipped.insert(o);
			}
		}
//		printShippedOrders();
//		printUnshippedOrders();
	}
	
	public void printShippedOrders() {
		System.out.println("Shipped Orders: ");
		shipped.displayArray();
	}
	
	public void printUnshippedOrders() {
		System.out.println("Unshipped Orders: ");
		unShipped.displayArray();
	}
	
	
	/**
	 * 
	 */
	public void Shipment() throws IOException {
		currentOrder();
		System.out.println("\n****SHIPMENT DETAILS****\n");
		System.out.print("These are the ");
		printUnshippedOrders();
		Order o = unShipped.getMax();
		System.out.println("Shipping Order: \n" + o.toString());
		unShipped.remove(1);
		shipped.insert(o);
		System.out.println("Order Number: " + o.getOrderNum() + " has been shipped!\n");
		System.out.print("Remaining ");
		printUnshippedOrders();
	}

////////////////////////////////////////////////Customer functions//////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	public void Customers() throws IOException {
		File EmployeeInfile = new File("CustomerList.txt");
		Scanner in = new Scanner(EmployeeInfile);
		while (in.hasNextLine()) {
			String firstName = in.nextLine();
			String lastName = in.nextLine();
			String userID = in.nextLine();
			String password = in.nextLine();
			String email = in.nextLine();
			String address = in.nextLine();
			String city = in.nextLine();
			String state = in.nextLine();
			int zip = in.nextInt();
			Customer c = new Customer(firstName, lastName, userID, password, email, address, city, state, zip);
			CustomerDirectory.add(c);
		}
	}
	
	/**
	 * 
	 */
	public void custLogin() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nAre you logging is as a member or as a guest?\n" + "M: As a member\n" + "G: As a guest\n"
				+ "\n" + "Please enter your choice:");
		String MemGuest = input.nextLine();
		if (MemGuest.equalsIgnoreCase("m")) {
			memberLogin();
		} else if (MemGuest.equalsIgnoreCase("g")) {
			cusMenu();
		}

	}
	
	/**
	 * 
	 */
	public void memberLogin() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your email ID: ");
		String email = input.nextLine();
		System.out.print("Please enter your password: ");
		String password = input.nextLine();
		customerCheck(email, password);
	}
	/**
	 * 
	 */
	public void addMember() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter First name:");
		String firstName = input.nextLine();
		System.out.print("Enter Last name:");
		String lastName = input.nextLine();
		System.out.print("Enter user ID:");
		String userID = input.nextLine();
		System.out.print("Enter password:");
		String password = input.nextLine();
		System.out.print("Enter email:");
		String email = input.nextLine();
		System.out.print("Enter street adress:");
		String address = input.nextLine();
		System.out.print("Enter city:");
		String city = input.nextLine();
		System.out.print("Enter state:");
		String state = input.nextLine();
		System.out.print("Enter Zip:");
		int zip = input.nextInt();
		Customer c = new Customer(firstName, lastName, userID, password, email, address, city, state, zip);
		CustomerDirectory.add(c);
		System.out.println("\nWelcome " + firstName + "!\nHow can we help you today?\n");
		cusMenu();
	}
	
	/**
	 * 
	 */
	public void customerCheck(String email, String password) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < EmployeeDirectory.size(); i++) {
			if (CustomerDirectory.get(i).getEmail().equalsIgnoreCase(email)
					&& CustomerDirectory.get(i).getPassword().equals(password)) {
				System.out.println("\nWelcome back " + CustomerDirectory.get(i).getFirstName()
						+ "!\nHow can we help you today?\n");
				cusMenu();
			} else {
				System.out.println("Sorry we could not find an account with the email/password combination!\n");
				System.out.print("Would you like to create an account now? (Y/N)");
				String choice1 = input.nextLine();
				if (choice1.equalsIgnoreCase("Y")) {
					addMember();
				} else {
					custLogin();
				}
			}
		}
		input.close();
	}
	/**
	 * 
	 */
	public void cusMenu() {
		Scanner input = new Scanner(System.in);
		SongsDatabase s = new SongsDatabase();
		try {
			s.populateLibary();
		} catch (IOException e) {
			System.out.println("IO Error:cusMenu()");
		}
		System.out.println("**Main Menu**\n\n" + 
				  "1. Search/Display songs\n" + 
				  "2. Place an order\n"
				+ "3. View recent purchase\n" + 
				  "4. Exit program\n" + "\n");
		System.out.print("Please enter your choice (1-4):\n");
		int choice = input.nextInt();
		while (choice != 4) {
			if (choice == 1) {
				boolean loop = true;
				while (loop) {
					System.out.println("Please select from one of the following options:\n");
					System.out.println("\nD. Display songs\n"
							+            "S. Search for a song\n"
							+            "X. Exit\n");
					System.out.print("Enter your choice: ");
					String choice1 = input.next();
					if (!choice1.equalsIgnoreCase("D") && !choice1.equalsIgnoreCase("S") && choice1.equalsIgnoreCase("X")) {
						System.out.println("\nInvalid Selection!\n");
						System.out.print("Enter your choice: ");
						choice1 = input.next();
					} 
					else if (choice1.matches("(?i)D")) {
//						try {
//							displaySongs();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
						
					} 
					else if (choice1.matches("(?i)S")) {
//						searchSongs(sc);
					} 
					else if (choice1.matches("(?i)X")) {
						loop = false;
						System.out.println("\nReturning to customer menu...\n");
						cusMenu();
					}
				}
			}

		 else if (choice == 2) {
				int numElements = 0;
				System.out.print("Would you like to Search for the item you want to order? (Y/N)");
				String temp = input.nextLine();
				while (!temp.equalsIgnoreCase("N")) {
					numElements++;
					s.searchSongPrimary();
					addToBag(numElements);
					System.out.print("Would you like to add more items? (Y/N)");
					temp = input.nextLine();
				}

			} else if (choice == 3) {
//////////////////////////////////////////////////////////////
			}
		}
		if (choice == 4) {
			System.out.print("Thank you for shopping with us. Goodbye!");
		}

	}
	
	/**
	 * 
	 */
	public void addToBag(int numElements) {
		numElements++;
		Scanner input = new Scanner(System.in);
		SongsDatabase s = new SongsDatabase();

		System.out.println();
		s.searchSongPrimary();

	}


}
