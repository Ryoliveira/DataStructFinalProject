/**
 * Songs.java
 * @author Tina Nemati
 * CIS 22C, Course Project
 */

public class Song {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String year;
    private String price;
    
    /**
     * Constructor for the Song class
     * @param title the Song's title 
     * @param artist the Song's artist
     * @param album the Song's album
     * @param genre the Song's genre
     * @param year the year the Song was released
     * @param price the amount price
     * 
     */
    public Song(String title, String artist,
            String album, String genre, String year, String price) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
        this.price = price;
    }
    
    /**
     * Accesses the title of the Song
     * @return the Song's title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Accesses the artist of the Song
     * @return the Song's artist
     */
    public String getArtist() {
        return artist;
    }
    
    /**
     * Accesses the album of the Song
     * @return the Song's album
     */
    
    public String getAlbum() {
    	return album;
    }
    
    /**
     * Accesses the genre of the Song
     * @return the Song's genre
     */
    public String getGenre() {
    	return genre;
    }
    
    /**
     * Access the year the Song was released
     * @return the year the Song was released
     */
    public String getYear() {
        return year;
    }
    
    /**
     * Access the price amount of the 
     * Song in dollars
     * @return the Movie's gross in Millions
     */
    public String getPrice() {
        return price;
    }
    
    /**
     * Sets the title of the Song
     * @param title the Song's title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Sets the artist of the Song
     * @param artist the Song's artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    /**
     * Sets the album of the Song
     * @param album the Song's album
     */
    public void setAlbum(String album) {
    	this.album = album;
    }
    
    /**
     * Sets the genre of the Song
     * @param genre the Song's genre
     */
    public void setGenre(String genre) {
    	this.genre = genre;
    }
    
    /**
     * Sets the year the Song was released
     * @param year the year the Song was released
     */
    public void setYear(String year) {
        this.year = year;
    }
    
    /**
     * Sets the price of the Song in dollars
     * @param price the price of the Song
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
    /**
     * Formats the song for display, using
     * the following format:
     * Title: <title>
     * Artist: <artist>
     * Album: <album>
     * Genre: <genre>
     * Year: <year>
     * Price: $<price>
     * Note that there should be no <>
     * in the resulting String
     */
    @Override public String toString() {
        String result = "Title: " + title
                + "\nArtist: " + artist
                + "\nAlbum: " + album
                + "\nGenre: " + genre
                + "\nYear: " + year
                + "\nPrice in dollars: $" + price
        		+ "\n";
        return result;
    }
    
    /**
     * Determines whether two Song objects are 
     * equal by comparing titles and artist
     * @param otherSong the second Song object
     * @return whether the Song are equal
     */
    @Override public boolean equals(Object o) {
    	if (o == this) {
    		return true;
    	} else if (!(o instanceof Song)) {
    		return false;
    	} else {
    		Song s = (Song) o;
    		return this.getTitle().equalsIgnoreCase(s.getTitle()) && this.getArtist().equalsIgnoreCase(s.getArtist());
    	}
    }
    
    /**
     * Compares two Song objects to determine ordering
     * by comparing their title(primary key)
     * Returns 0 if the two items are equal
     * Return -1 if this Song's title comes alphabetically
     * before the other Song's title.
     * Returns 1 if the other Song's title comes
     * alphabetically before this song's title
     * @param the other Song object to compare to this
     * @return 0 (same song), -1 (this song ordered first)
     * or 1 (the other song ordered first) 
     */
    public int compareToTitle(Song otherSong) {
    	if (this.equals(otherSong)) {
    		return 0;
    	} else {
    		return this.getTitle().compareToIgnoreCase(otherSong.getTitle());
    	} 
    }
    
     /**
     * Compares two Song objects to determine ordering 
     * by comparing their artist(secondary key)
     * Returns 0 if the two items are equal
     * Return -1 if this Song's artist comes alphabetically
     * before the other Song's artist.
     * Returns 1 if the other Song's artist comes
     * alphabetically before this Song's artist
     * @param the other Song object to compare to this
     * @return 0 (same song), -1 (this song ordered first)
     * or 1 (the other song ordered first) 
     */
    public int compareToArtist(Song otherSong) {
    	if (this.equals(otherSong)) {
    		return 0;
    	} else {
    		return this.getArtist().compareToIgnoreCase(otherSong.getArtist());
    	}
    }

}
