/**
 * Java Tunes Final project
 * @author Sharanya Menon
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import Objects.*;

public class Interface {
	private ArrayList<Employee> EmployeeDirectory = new ArrayList<Employee>();
	private ArrayList<Customer> CustomerList = new ArrayList<Customer>();
	private HashTable<Customer> CustomerDirectory = new HashTable<Customer>(17);
	SongsDatabase s = new SongsDatabase();
	int orderNum = 100;
	Heap unShipped = new Heap(100);
	Heap shipped = new Heap(100);

	/**
	 * calls populateLibrary from SongsDatabase
	 */
	public void addSongs() {
		try {
			s.populateLibary();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds in employee into Directory from employee.txt
	 * @throws IOException
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
	 * calls searchSongPrimary/searchSongSeacondary based on input choice
	 * @param sc
	 */
	@SuppressWarnings("static-access")
	public void searchSongs(Scanner sc) {
		System.out.println("\nSearching for a song!\n");
		boolean dummy = true;
		while (dummy) {
			System.out.println("Do you want to search by primary key or by secondary key?");
			System.out.println("P. Primary key based on song Title\nS. Secondary key based on Artist name\n");
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

	/**
	 * Adds customer into Hash CustomerDirectory from CustomerList.txt
	 * @throws IOException
	 */
	public void addCustomer() throws IOException {
		Scanner input = new Scanner(new FileInputStream("CustomerList.txt"), "UTF-8");
		while (input.hasNextLine()) {
			String firstName = input.nextLine();
			firstName = firstName.replaceAll("\\s", "");
			String lastName = input.nextLine();
			String email = input.nextLine();
			String userID = email;
			String password = input.nextLine();
			String address = input.nextLine();
			String city = input.nextLine();
			String state = input.nextLine();
			String zip_try = input.nextLine();
			int zip = Integer.parseInt(zip_try);
			Customer c = new Customer(firstName, lastName, email, password, address, city, state, zip);
			CustomerDirectory.insert(c);

		}
	}

	/**
	 * Adds customer into ArrayList CustomerList from CustomerList.txt
	 * @throws IOException
	 */
	public void addCustomerList() throws IOException {
		Scanner input = new Scanner(new FileInputStream("CustomerList.txt"), "UTF-8");
		while (input.hasNextLine()) {
			String firstName = input.nextLine();
			firstName = firstName.replaceAll("\\s", "");
			String lastName = input.nextLine();
			String email = input.nextLine();
			String userID = email;
			String password = input.nextLine();
			String address = input.nextLine();
			String city = input.nextLine();
			String state = input.nextLine();
			String zip_try = input.nextLine();
			int zip = Integer.parseInt(zip_try);
			Customer c = new Customer(firstName, lastName, email, password, address, city, state, zip);
			CustomerList.add(c);

		}
	}

	/**
	 * calls displaySongPrimary/displaySongSeacondary based on input choice to display songs
	 * @param sc- Scanner object
	 */
	public void displaySongs(Scanner sc) {
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

	/**
	 * Prints list of current orders 
	 * @throws IOException
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
			if (priority.equalsIgnoreCase("overnight")) {
				p = 1;
			} else if (priority.equalsIgnoreCase("rush")) {
				p = 2;
			} else {
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
			if (status.equalsIgnoreCase("shipped")) {
				shipped.insert(o);
			} else {
				unShipped.insert(o);
			}
		}
	}

	/**
	 * Prints Shipped Orders
	 */
	public void printShippedOrders() {
		System.out.println("Shipped Orders: ");
		shipped.displayArray();
	}

	/**
	 * Prints Unshipped Orders
	 */
	public void printUnshippedOrders() {
		System.out.println("Unshipped Orders: ");
		unShipped.displayArray();
	}

	/**
	 * Ships order on top of heap
	 */
	public void Shipment() throws IOException {
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

	/**
	 * Welcome Screen
	 * @throws IOException
	 */
	public void WelcomeScreen() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out
				.println("\nAre you login in as an employee? Or as a customer?\n" + "E: Employee\n" + "C: Customer\n");
		System.out.print("Enter your choice: ");
		String choice = input.nextLine();
		if (choice.equalsIgnoreCase("E")) {
			empLogin();
		} else if (choice.equalsIgnoreCase("C")) {
			custLogin();
		}
	}

	/**
	 * @throws IOException
	 * Asks for employee credentials
	 */
	public void empLogin() throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your user name: ");
		String Username = input.nextLine();
		System.out.print("Please enter your employee ID: ");
		String employeeID = input.nextLine();
		System.out.print("Please enter your password: ");
		String password = input.nextLine();
		empCheck(Username, employeeID, password);
		input.close();
	}

	/**
	 * @throws IOException
	 * Checks employee credentials
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
					if ((EmployeeDirectory.get(i).getEmployeeID().equalsIgnoreCase(ID))
							&& (EmployeeDirectory.get(i).getUsername().equalsIgnoreCase(Username))) {
						System.out.print("\nEnter new password " + EmployeeDirectory.get(i).getUsername() + " : ");
						String newPassword = input.nextLine();
						EmployeeDirectory.get(i).setPassword(newPassword);
						System.out.println(
								"\n" + EmployeeDirectory.get(i).getUsername() + " you're password has been changed!");
						empCheck(UserName, employeeID, newPassword);

					} else {
						System.out.println(
								"\nCould not find any employee with the matching username and ID. Please try again!");
						WelcomeScreen();
					}
				}

			}

			else {
				WelcomeScreen();
			}

		}
	}

	/**
	 * @throws IOException
	 * EmployeeMenu
	 */
	public void empMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice != 7) {
			System.out.println("**Main Menu**\n\n" + "1. View Current Orders \n" + "2. Ship an order\n"
					+ "3. View Store Inventory\n" + "4. View Customers information\n"
					+ "5. Search for a customer by name\n" + "6. Search, Add or Remove from the inventory\n"
					+ "7. Exit");
			System.out.print("\nPlease enter your choice(1-7):");
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 1) {
				try {
					currentOrder();
					printShippedOrders();
					printUnshippedOrders();
				} catch (IOException e) {
					System.out.print("No current orders in processing");
				}

			} else if (choice == 2) {
				Shipment();
			} else if (choice == 3) {
				sc.nextLine();
				displaySongs(sc);
			} else if (choice == 4) {
				for (int i = 0; i < CustomerDirectory.getNumElements() - 1; i++) {
					CustomerDirectory.printBucket(i);
				}

			} else if (choice == 5) {
				System.out.print("Enter Customer's first name:");
				String FirstName = sc.nextLine();
				System.out.print("Enter Customer's last name:");
				String LastName = sc.nextLine();
				Customer tempCustomer = new Customer(FirstName, LastName);
				if (CustomerDirectory.search(tempCustomer) != -1) {
					System.out.println(tempCustomer.getFirstName() + " is in database. \n");
				} else {
					System.out.println("No matches found! ");
				}

			} else if (choice == 6) {
				boolean loop = true;
				while (loop) {
					System.out.println("Please select from one of the following options:\n");
					System.out.println("A. Add a song\n" + "D. Display songs\n" + "R. Remove a song\n"
							+ "S. Search for a song\n" + "X. Exit\n");
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
				return;
			}
		}
		sc.close();
	}

	/**
	 * Customer login screen
	 */
	public void custLogin() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nAre you logging is as a member or as a guest?\n" + "M: As a member\n" + "G: As a guest\n"
				+ "\n" + "Please enter your choice:");
		String MemGuest = input.nextLine();
		if (MemGuest.equalsIgnoreCase("m")) {
			memberLogin();
		} else if (MemGuest.equalsIgnoreCase("g")) {
			System.out.println("Logging in as guest....\n");
			cusMenu();
		}

	}

	/**
	 * login screen for members. Calls customerCheck to verify
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
	 * Adds new customer member
	 */
	public void addMember() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter First name:");
		String firstName = input.nextLine();
		System.out.print("Enter Last name:");
		String lastName = input.nextLine();
		System.out.print("Enter email:");
		String email = input.nextLine();
		System.out.print("Enter password:");
		String password = input.nextLine();
		System.out.print("Enter street adress:");
		String address = input.nextLine();
		System.out.print("Enter city:");
		String city = input.nextLine();
		System.out.print("Enter state:");
		String state = input.nextLine();
		System.out.print("Enter Zip:");
		int zip = input.nextInt();
		Customer c = new Customer(firstName, lastName, email, password, address, city, state, zip);
		CustomerDirectory.insert(c);
		System.out.println("\nWelcome " + firstName + "!\nHow can we help you today?\n");
		cusMenu();
	}

	/**
	 * Checks if customer is a member 
	 */
	public void customerCheck(String email, String password) {
		Scanner input = new Scanner(System.in);
		boolean match = false;
		for (int i = 0; i < CustomerList.size(); i++) {
			if (CustomerList.get(i).getEmail().equals(email) && CustomerList.get(i).getPassword().equals(password)) {
				System.out.println(
						"Welcome back " + CustomerList.get(i).getFirstName() + "! How can we help you today?\n");
				cusMenu();
				match = true;
			}
		}
		if (match == false) {
			System.out.println(
					"Sorry! We could not find an account with the username.\nWould you like to create a new one?(Y/N)");
			String newMem = input.nextLine();
			if (newMem.equalsIgnoreCase("y")) {
				addMember();
			} else {
				System.out.println("Logging in as guest...\n");
				custLogin();
			}
		}
	}

	/**
	 * Customer Menu
	 */
	public void cusMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice != 3) {
			System.out.println("**Main Menu**\n\n" + "1. Search/Display songs\n" + "2. Place an order\n"
					+ "3. Exit program\n" + "\n");
			System.out.print("Please enter your choice (1-3):");
			String choice_temp = sc.nextLine();
			choice = Integer.parseInt(choice_temp);
			if (choice == 1) {
				boolean loop = true;
				while (loop) {
					System.out.println("Please select from one of the following options:\n");
					System.out.println("\nD. Display songs\n" + "S. Search for a song\n" + "X. Exit\n");
					System.out.print("Enter your choice: ");
					String choice1 = sc.nextLine();
					if (!choice1.matches("(?i)D|X|S")) {
						System.out.println("\nInvalid Selection!\n");
						System.out.print("Enter your choice: ");
						choice1 = sc.nextLine();
					} else if (choice1.matches("(?i)D")) {
						displaySongs(sc);
					} else if (choice1.matches("(?i)S")) {
						searchSongs(sc);
					} else if (choice1.matches("(?i)X")) {
						loop = false;
						System.out.println("\nReturning to customer menu...\n");
						cusMenu();
					}
				}
			}

			else if (choice == 2) {
				int numElements = 0;
				int shippingSpeed = 3;
				System.out.print("Would you like to Search for the item you want to order? (Y/N)");
				String temp = sc.nextLine();
				while (!temp.equalsIgnoreCase("N")) {
					boolean flag = true;

					flag = searchSongs(sc, flag);
					if (flag) {
						System.out.print("Would you like to purcahse (Y/N) : ");
						String choice1 = sc.nextLine();
						if (choice1.equalsIgnoreCase("y")) {
							numElements++;
							System.out.print("Would you like to add more items? (Y/N) : ");
							temp = sc.nextLine();
						}
					}
				}
				if (numElements >= 1) {
					System.out.println("Select shipping speed: \n" + "1. Overnight\n" + "2.Rush\n" + "3.Standard\n");
					System.out.print("Enter choice(1-3) :");
					String shippingSpeed_temp = sc.nextLine();
					shippingSpeed= Integer.parseInt(shippingSpeed_temp);
					orderNum();
					placeOrder(numElements, shippingSpeed);

				} else {
					System.out.println("Hope to see you back soon!");
					return;
				}
			}

		}
		if (choice == 3) {
			System.out.print("Thank you for shopping with us. Goodbye!");
			return;
		}

	}

	/**
	 * calls primary serach song from database and searches for songs to place order
	 * @param sc Scanner
	 * @param flag returns if song in directory or not
	 * @return
	 */
	private boolean searchSongs(Scanner sc, boolean flag) {
		System.out.println("\nSearching for a song!\n");
		flag = s.searchSong();
		return flag;
	}

	/**
	 *increments orderNum when new order is created
	 */
	public void orderNum() {
		orderNum++;
	}

	/**
	 * Creates new order object based on customer selection
	 */
	public void placeOrder(int numElements, int shippingSpeed) {
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 2, 29);
		String status = "UNSHIPPED";
		Order o = new Order(orderNum, numElements, shippingSpeed, cal, status);
		System.out.println("Your order has been placed. Here are the details:\n");
		System.out.println(o);

	}
	
	/**
	 * Main method that creates an interface object and calls welcomeScreen
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("********************Welcome to Java Music store********************\n\n");
		Interface i = new Interface();
		i.addSongs(); // Reads in songs into BST
		i.addEmployee(); // Reads in employees into ArrayList
		i.addCustomer(); // Reads in customers into Hash
		i.addCustomerList(); // Reads in customers into Hash
		i.WelcomeScreen(); // Calls interface run program

	}

}
