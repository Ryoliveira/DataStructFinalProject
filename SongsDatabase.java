import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * SongsDatabase.java
 * @author Tina Nemati
 * CIS 22C, Course Project
 */

public class SongsDatabase {
	private static BST songsPrimary = new BST();
	private static BST songsSecondary = new BST();
	private static final String filename = "songs.txt";
	
	/**
	 * Insert songs from file into the two song BST
	 * 
	 * @precondition file should exist
	 * @throws IOException when precondition is violated
	 */
	public static void populateLibary() throws IOException {
		Scanner input = new Scanner(new File(filename));
		while (input.hasNextLine()) {
			String title = input.nextLine();
			String artist = input.nextLine();
			String album = input.nextLine();
			String genre = input.nextLine();
			String year = input.nextLine();
			String price = input.nextLine();
			if (input.hasNextLine()) {
				input.nextLine();
			}
			Song song = new Song(title, artist, album, genre, year, price);
			songsPrimary.insertPrimary(song);
			songsSecondary.insertSecondary(song);
		}
		input.close();
	}
	
	/**
	 * Adds a song object into the songs  
	 * primary and secondary BST
	 * 
	 * @param input the scanner
	 */
	public static void addSong(Scanner in) {
		System.out.print("Enter the title: ");
		String t = in.nextLine();
		System.out.print("Enter the artist's name: ");
		String ar = in.nextLine();
		System.out.print("Enter the album's name (if it's a single just write single): ");
		String al = in.nextLine();
		System.out.print("Enter the genre of the song: ");
		String g = in.nextLine();
		System.out.print("Enter the year the song was released: ");
		String y = in.nextLine();
		System.out.print("Enter the price of the song in dollars: ");
		String p = in.nextLine();
		Song s = new Song(t, ar, al, g, y, p);
		songsPrimary.insertPrimary(s);
		songsSecondary.insertSecondary(s);
		System.out.println("\n" + t + " by " +  ar + " has been succesfully added to the song database!\n");
	}
	
	/**
	 * Creates temporary song object to be used in removal
	 */
	public static Song tempSong() {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter the title: ");
		String ti = console.nextLine();
		System.out.print("Enter the artit's name: ");
		String ar = console.nextLine();
		Song tempSong = new Song(ti, ar, " ", " ", " ", " ");
		return tempSong;
	}
	
	/**
	 * Removes a song object from both song bst's
	 */
	public static void removeSong() {
		Song songToRemove = tempSong();
		if (songsPrimary.searchPrimary(songToRemove.getTitle()) && songsSecondary.searchSecondary(songToRemove.getArtist())) {
			songsPrimary.removePrimary(songToRemove);
			songsSecondary.removeSecondry(songToRemove);
			System.out.println("\n" + songToRemove.getTitle() + " by" + songToRemove.getArtist() + 
					" has been succcesfully removed from the song's list!\n");
		} else {
			System.out.println("\nWe don't have " + songToRemove.getTitle() + " by " + songToRemove.getArtist() + 
					"in our database.");
		}
		
	}
	
	/**
	 * Searches a song object in the database
	 * using the primary key
	 */
	public static void searchSongPrimary() {
		Song songToSearch1 = tempSong();
		if (songsPrimary.searchPrimary(songToSearch1.getTitle())) {
			System.out.println("\n" + songToSearch1.getTitle() + " is in the database!\n");
			System.out.println("Bellow is the record we found: \n");
			System.out.println(songsPrimary.getRecentSearch());
		} else {
			System.out.println("\nWe cannot find " + songToSearch1.getTitle() + " in our database!\n");
		}
	}
	
	/**
	 * Searches for a song object in the database
	 * using the secondary key
	 */
	public static void searchSongSecondary() {
		Song songToSearch2 = tempSong();
		if (songsSecondary.searchSecondary(songToSearch2.getArtist())) {
			System.out.println("\nWe have a song/songs by " + songToSearch2.getArtist() + " in our database!\n");
			System.out.println("If you are looking for a specific song from this artist; try searching by the primary key.\n");
		} else {
			System.out.println("\nWe do not have a song by " + songToSearch2.getArtist() + " in our database!\n");
		}
	}
	
	/**
	 * Display the song database sorted by the primary key
	 */
	public static void displayPrimary() {
		System.out.println("\n**Current songs available in the store sorted by the title**");
		songsPrimary.display();
		System.out.println("End of database!\n");
	}
	
	/**
	 * Display the song database sorted by the secondary key
	 */
	public static void displaySecondary() {
		System.out.println("\n**Current songs available in the store sorted by the artist's name**");
		songsSecondary.display();
		System.out.println("End of database!\n");
	}
	
	public static void main(String[] args) throws IOException {
		boolean loop = true;
		System.out.println("Welcome to the JavaTunes Song Database!\n");
		populateLibary();
		while (loop) {
			Scanner in = new Scanner(System.in);
			System.out.println("Please select from one of the following options:\n");
			System.out.println("A. Add a song\nD. Display songs\nR. Remove a song\nS. Search for a song\nX. Exit\n");
			System.out.print("Enter your choice: ");
			String choice = in.nextLine();
			if (!choice.matches("(?i)A|D|R|X|S")) {
				System.out.println("\nInvalid Selection!\n");

			} else if (choice.matches("(?i)A")) {
				System.out.println("\nAdding a song!\n");
				addSong(in);
			} else if (choice.matches("(?i)D")) {
				System.out.println("\nDisplaying Song Database:\n");
				boolean flag = true;
				while (flag) {
					System.out.println("Do you want to list products sorted by primary key or by secondary key?");
					System.out.println("P. Primary key\nS. Secondary key\n");
					System.out.print("Enter your desire option: ");
					String option = in.nextLine();
					if (!option.matches("(?i)P|S")) {
						System.out.println("\nInvalid Selection!\n");
					} else if (option.matches("(?i)P")) {
						flag = false;
						displayPrimary();
					} else {
						flag = false;
						displaySecondary();
					}
				}
			} else if (choice.matches("(?i)R")) {
				System.out.println("\nRemoving a song!\n");
				removeSong();
			} else if (choice.matches("(?i)S")) {
				System.out.println("\nSearching for a song!\n");
				boolean dummy = true;
				while (dummy) {
					System.out.println("Do you want to search by primary key or by secondary key?");
					System.out.println("P. Primary key\nS. Secondary key\n");
					System.out.print("Enter your desire option: ");
					String decision = in.nextLine();
					if (!decision.matches("(?i)P|S")) {
						System.out.println("\nInvalid Selection!\n");
					} else if (decision.matches("(?i)P")) {
						dummy = false;
						System.out.println();
						searchSongPrimary();
					} else {
						dummy = false;
						System.out.println();
						searchSongSecondary();
					}
				}
			} else if (choice.matches("(?i)X")) {
				loop = false;
				System.out.println("\nGoodbye!");
			}

		
	}
	}
}
