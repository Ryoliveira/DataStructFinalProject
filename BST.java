import java.util.NoSuchElementException;

/**
 * BST.java
 * @author Tina Nemati
 * CIS 22C, Course Project
 */

public class BST {
	private class Node {
		private Song data;
		private Node left;
		private Node right;

		public Node(Song s) {
			this.data = s;
			left = null;
			right = null;
		}
	}
	
	private Node root;
	private Song recentSearch;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST 
	 * sets root to null
	 */
	public BST() {
		root = null;
		recentSearch = null;
	}
	
	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when 
	 * precondition is violated
	 */
	public Song getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getroot():" 
					+ "Tree is empty. No data to return!");
		}
		return root.data;
	}
	
	/**
	 * Returns the recent song search 
	 * 
	 * @precondition !isEmpty()
	 * @return the recent search song
	 * @throws NoSuchElementException when 
	 * precondition is violated
	 */
	public Song getRecentSearch() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getroot():" 
					+ "Tree is empty. No data to return!");
		}
		return recentSearch;
	}
	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of 
	 * the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		} else if (node.left == null && node.right == null) {
			return 1;
		} else {
			return 1 + getSize(node.left) + getSize(node.right);
		}
	}
	
	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when 
	 * the precondition is violated
	 */
	public Song findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): " 
					+ "Tree is empty. No data to return!");
		}
		return findMin(root);
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check 
	 * if it is the smallest
	 * @return the smallest value in the tree
	 */
	private Song findMin(Node node) {
		if (node.left != null) {
			return findMin(node.left);
		}
		return node.data;

	}

	/**
	 * Searches for a specified value 
	 * in the tree using the primary key
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public boolean searchPrimary(String title) {
		return searchP(title, root);
	}

	/**
	 * Helper method for the search primary method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private boolean searchP(String title, Node node) {
		if (node == null) {
			recentSearch = null;
			return false;
		} else if (node.data.getTitle().equals(title)) {
			recentSearch = node.data;
			return true;
		} else {
			if (title.compareTo(node.data.getTitle()) < 0) {
				return searchP(title, node.left);
			} else {
				return searchP(title, node.right);
			}
		}
	}
	
	/**
	 * Searches for a specified value 
	 * in the tree using the primary key
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	 public boolean searchSecondary(String artist) {
		 return searchS(artist, root);
	 }
	
	 /**
	  * Helper method for the search primary method
	  * 
	  * @param data the data to search for
	  * @param node the current node to check
	  * @return whether the data is stored in the tree
	  */
	private boolean searchS(String artist, Node node) {
		if (node == null) {
			recentSearch = null;
			return false;
		} else if (node.data.getArtist().equals(artist)) {
			recentSearch = node.data;
			return true;
		} else {
			if (artist.compareTo(node.data.getArtist()) < 0) {
				return searchS(artist, node.left);
			} else {
				return searchS(artist, node.right);
			}
		}
	}

	/**
	 * Determines whether two trees store 
	 * identical data in the same structural
	 * position in the tree
	 * 
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BST)) {
			return false;
		}
		BST t = (BST) o;
		if (getSize() != t.getSize()) {
			return false;
		}
		return equals(root, t.root);
	}

	/**
	 * Helper method for the equals method
	 * 
	 * @param node1 the node of the first bst
	 * @param node2 the node of the second bst
	 * @return whether the two trees contain 
	 * identical data stored in the same structural 
	 * position inside the trees
	 */
	private boolean equals(Node node1, Node node2) {
		if(node1 == null && node2 == null) {
			return true;
		}
		else if (node1 == null && node2 != null) {
			return false;
		}
		else if (node1 != null && node2 == null) {
			return false;
		}
		else if (!(node1.data.equals(node2.data))) {
			return false;
		}
		else if (!(equals(node1.left, node2.left))) {
			return false;
		} else if (!(equals(node1.right, node2.right))) {
			return false;
		}
		
		return true;
		
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree 
	 * using the primary key
	 * 
	 * @param data the data to insert
	 */
	public void insertPrimary(Song s) {
		if (isEmpty()) {
			root = new Node(s);
		} else {
			insertP(s, root);
		}

	}

	/**
	 * Helper method to insert 
	 * using primary key
	 * 
	 * Inserts a new value in the tree
	 * @param data the data to insert
	 * @param node the current node in the
	 * search for the correct location 
	 * in which to insert
	 */
	private void insertP(Song s, Node node) {
		if (s.getTitle().compareTo(node.data.getTitle()) == 0) {
			return;
		}else if (s.getTitle().compareTo(node.data.getTitle()) < 0) {
			if (node.left == null) {
				node.left = new Node(s);
			} else {
				insertP(s, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(s);
			} else {
				insertP(s, node.right);
			}
		}

	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree 
	 * using the secondary key
	 * 
	 * @param data the data to insert
	 */
	public void insertSecondary(Song s) {
		if (isEmpty()) {
			root = new Node(s);
		} else {
			insertS(s, root);
		}

	}

	/**
	 * Helper method to insert 
	 * using secondary key
	 * 
	 * Inserts a new value in the tree
	 * @param data the data to insert
	 * @param node the current node in the
	 * search for the correct location 
	 * in which to insert
	 */
	private void insertS(Song s, Node node) {
		if (s.getArtist().compareTo(node.data.getArtist()) == 0 && s.getTitle().compareTo(node.data.getTitle()) == 0) {
			return;
		}else if (s.getArtist().compareTo(node.data.getArtist()) < 0) {
			if (node.left == null) {
				node.left = new Node(s);
			} else {
				insertS(s, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(s);
			} else {
				insertS(s, node.right);
			}
		}

	}
	/**
	 * Removes a song from the BST using primary key
	 * 
	 * @param s the song to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when 
	 * the precondition is violated
	 */
	public void removePrimary(Song s) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("remove(): " 
					+ "Tree is empty. No data to remove!");
		} else if (!searchPrimary(s.getTitle())) {
			throw new NoSuchElementException("remove(): " 
					+ "Element not found. Cannot remove the data!");
		} else {
			root = removeP(s, root);
		}
	}
	
	/**
	 * Removes a song from the BST using secondary key
	 * 
	 * @param s the song to remove
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when 
	 * the precondition is violated
	 */
	public void removeSecondry(Song s) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("remove(): " 
					+ "Tree is empty. No data to remove!");
		} else if (!searchSecondary(s.getArtist())) {
			throw new NoSuchElementException("remove(): " 
					+ "Element not found. Cannot remove the data!");
		} else {
			root = removeS(s, root);
		}
	}

	/**
	 * Helper method to the remove primary method
	 * 
	 * @param s the song to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node removeP(Song s, Node node) {
		if(node == null) {
			return node;
		}
		else if(s.compareToTitle(node.data) < 0) {
			node.left = removeP(s, node.left);
		}
		else if (s.compareToTitle(node.data) > 0) {
			node.right = removeP(s, node.right);
		}
		else {
			if(node.left == null && node.right == null) {
				node = null;
			}
			else if(node.left != null && node.right == null) {
				node = node.left;
			}
			else if(node.right != null && node.left == null) {
				node = node.right;
			}
			else {
				node.data = findMin(node.right); 
				node.right = removeP(node.data, node.right);
			}
		}
		return node;
	}
	
	/**
	 * Helper method to the remove secondary method
	 * 
	 * @param s the song to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node removeS(Song s, Node node) {
		if(node == null) {
			return node;
		}
		else if(s.compareToArtist(node.data) < 0) {
			node.left = removeS(s, node.left);
		}
		else if (s.compareToArtist(node.data) > 0) {
			node.right = removeS(s, node.right);
		}
		else {
			if(node.left == null && node.right == null) {
				node = null;
			}
			else if(node.left != null && node.right == null) {
				node = node.left;
			}
			else if(node.right != null && node.left == null) {
				node = node.right;
			}
			else {
				node.data = findMin(node.right); 
				node.right = removeS(node.data, node.right);
			}
		}
		return node;
	}

	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Prints the data in sorted order 
	 * to the console
	 */
	public void display() {
		display(root);
		System.out.println();
	}

	/**
	 * Helper method to inOrderPrint method 
	 * Prints the data in sorted order to the
	 * console
	 */
	private void display(Node node) {
		if (node == null) {
			return;
		}
		display(node.left);
		System.out.print(node.data + "\n");
		display(node.right);
	}
}
