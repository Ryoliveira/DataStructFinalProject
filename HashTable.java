import java.util.ArrayList;

public class HashTable<T extends Comparable<T>> {
	private int numElements;
	private ArrayList<List<T>> Table;

	/**
	 * Constructor for the HashTable.java class. Initializes the Table to be sized
	 * according to value passed in as a parameter Inserts size empty Lists into the
	 * table. Sets numElements to 0
	 * 
	 * @param size the table size
	 */
	public HashTable(int size) {
		Table = new ArrayList<List<T>>();
		for (int i = 0; i < size; i++) {
			Table.add(new List<T>());
		}
		numElements = 0;
	}

	/** Accessors */

	/**
	 * Returns the hash value in the Table for a given key by taking modulus of the
	 * hashCode value for that key and the size of the table
	 * 
	 * @param t the key
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		int index = code % Table.size();
		return index;
	}

	/**
	 * Counts the number of keys at this index
	 * 
	 * @param index the index in the Table
	 * @precondition 0 <= index < Table.length
	 * @return the count of keys at this index
	 * @throws IndexOutOfBoundsException
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > Table.size()) {
			throw new IndexOutOfBoundsException("countBucket():" + "Index is outside of bounds of table.");
		}
		return Table.get(index).getLength();
	}

	/**
	 * Returns total number of keys in the Table
	 * 
	 * @return total number of keys
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * Searches for a specified key in the Table
	 * 
	 * @param t the key to search for
	 * @return the index in the Table (0 to Table.length - 1) or -1 if t is not in
	 *         the Table
	 */
	public int search(T t) {
		int hashCode = hash(t);
		if (Table.get(hashCode).linearSearch(t) != -1) {
			return hashCode;
		}
		return -1;
	}

	/**Table Manipulation*/

	/**
	 * Inserts a new key in the Table calls the hash method to determine placement
	 * 
	 * @param t the key to insert
	 */
	public void insert(T t) {
		int index = hash(t);
		Table.get(index).addLast(t);
		numElements++;
		int limit = Table.size() / 2;
		if(numElements > limit) {
			int newSize = Table.size()*2;
			System.out.println("Resizing Hashtable");
			resize(newSize);
		}
	}

	/**
	 * removes the key t from the Table calls the hash method on the key to
	 * determine correct placement has no effect if t is not in the Table
	 * 
	 * @param t the key to remove
	 */
	public void remove(T t) {
		int hashCode = hash(t);
		int searchRes = Table.get(hashCode).linearSearch(t);
		if (searchRes != -1) {
			Table.get(hashCode).pointIterator();
			Table.get(hashCode).moveToIndex(searchRes);
			Table.get(hashCode).removeIterator();
			numElements--;
		}
	}
	
	/**
	 * retrieves the key t from the Table calls the hash method on the key to
	 * determine correct placement. returns given object if not found.
	 * 
	 * @param t the object to be retrieved
	 * @return t retrieved object if found. Otherwise will return provided object
	 */
	public T getObject(T t) {
		int hashCode = hash(t);
		int searchRes = Table.get(hashCode).linearSearch(t);
		if (searchRes != -1) {
			Table.get(hashCode).pointIterator();
			Table.get(hashCode).moveToIndex(searchRes);
			t = Table.get(hashCode).getIterator();
		}
		return t;
	}
	
	/**
	 * Resizes the hashTable to create less collisions.
	 * 
	 * @param newSize the new size of the hashTable
	 */
	
	private void resize(int newSize) {
		ArrayList<List<T>> temp = Table;
		Table = new ArrayList<List<T>>();
		for (int i = 0; i < newSize; i++) {
			Table.add(new List<T>());
		}
		numElements = 0;
		for(List<T> list: temp) {
			list.pointIterator();
			while(!list.offEnd()) {
				T val = list.getIterator();
				insert(val);
				list.advanceIterator();
			}
		}
	}

	/** Print Methods */

	/**
	 * Prints all the keys at a specified bucket in the Table. Each key displayed on
	 * its own line, with a blank line separating each key Above the keys
	 * 
	 * @param bucket the index in the Table
	 */
	public void printBucket(int bucket) {
		List<T> curBucket = Table.get(bucket);
		curBucket.pointIterator();
		while (!curBucket.offEnd()) {
			System.out.println(Table.get(bucket).getIterator().toString() + "\n");
			Table.get(bucket).advanceIterator();
		}
	}

	/**
	 * Prints out every item in the list.
	 */
	public void printTable() {
		int bucketNum = 0;
		for (List<T> bucket : Table) {
			if (!bucket.isEmpty()) {
				printBucket(bucketNum);
			}
			bucketNum++;
		}
	}
}
