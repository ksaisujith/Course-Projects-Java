/**
 * TestStorage.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

/**
 * This class has the test cases to test all the functionalities of the Storage Set
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */

public class TestStorage {
	/**
	 * This functions tests the add functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testAdd() {
		boolean result = true;
		Storage<Integer> f = new Storage<Integer>();
		
		Integer[] intArray = {5,3,2,10};
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		for(Integer n:intArray) {
			//Checking if every element is present in the Storage
			result &= f.contains(n);
		}		
		return result;
	}
	
	/**
	 * This functions tests the addAll functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testAddAll() {
		boolean result = true;
		Storage<Integer> aStorage = new Storage<Integer>();
		
		Integer[] aintArray = {5,3,2,10};
		for(Integer n:aintArray) {
			//Adding the values
			aStorage.add(n);
		}
		Storage<Integer> bStorage = new Storage<Integer>();
		Integer[] bintArray = {5,3,1,4};
		for(Integer n:bintArray) {
			//Adding values to another Storage
			bStorage.add(n);
		}
		//Union of two storage sets
		aStorage.addAll(bStorage);
		if(!aStorage.toString().equals("5 3 2 10 1 4 ")) {
			result = false;
		}
		return result;
	}
	
	/**
	 * This functions tests the removeAll functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testRemoveAll() {
		boolean result = true;
		Storage<Integer> aStorage = new Storage<Integer>();
		
		Integer[] aintArray = {5,3,2,10};
		for(Integer n:aintArray) {
			//Adding the values
			aStorage.add(n);
		}
		Storage<Integer> bStorage = new Storage<Integer>();
		Integer[] bintArray = {5,3,1,4};
		for(Integer n:bintArray) {
			//Adding the values
			bStorage.add(n);
		}
		//Difference of two sets
		aStorage.removeAll(bStorage);
		
		//Comparing the results
		if(!aStorage.toString().equals("2 10 ")) {
			result = false;
		}
		return result;
	}
	
	/**
	 * This functions tests the remove functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testRemove() {
		boolean result = true;
		Storage<Integer> aStorage = new Storage<Integer>();	
		Integer[] aintArray = {5,3,2,10};
		for(Integer n:aintArray) {
			//Adding the values
			aStorage.add(n);
		}
		//Removing an element in the storage
		aStorage.remove(3);
		//Checking if the element is present in the storage
		if(! aStorage.toString().equals("5 2 10 ")) {
			result = false;
		}
		//Removing another element to check in another way
		aStorage.remove(2);
		
		if(aStorage.contains(2)) {
			result = false;
		}
		return result;
	}

	
	/**
	 * This functions tests the clearing functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testClear() {
		boolean result = true;
		Storage<Integer> f = new Storage<Integer>();		
		Integer[] intArray = {5,3,2,10};
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		//Clearing the list
		f.clear();
		//Checking the size of the list
		if( f.size() != 0) {
			result = false;
		}
		return result;
	}
	

	/**
	 * This functions tests the contains functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testContains() {
		boolean result = true;
		Storage<Integer> f = new Storage<Integer>();		
		Integer[] intArray = {5,3,2,10};
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		for(Integer n:intArray) {
			//checking if all the elements are in the storage
			result &= f.contains(n);
		}
		//Checking if some random value returns false
		if(f.contains(99)) {
			result = false;
		}
		return result;
	}
	
	/**
	 * This functions tests the toArray functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testToArray() {
		boolean result = true;
		Storage<Integer> aStorage = new Storage<Integer>();		
		Integer[] intArray = {5,3,2,10};
		String str = "";
		for(Integer n:intArray) {
			//Adding the values
			aStorage.add(n);
		}
		//Converting the elements to array and appending values to the string
		for(Object ints : aStorage.toArray()) {
			str += ints+ " ";
		}
		
		//Comparing the results
		if(!str.equals( "5 3 2 10 "))
			result = false;
		return result;
	}

	/**
	 * This functions tests the size functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testSize() {
		boolean result = true;
		Integer[] intArray = {5,3,2,10};
		Storage<Integer> f = new Storage<Integer>();
		int size = 0;
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
			size++;
			if(f.size()!= size) {
				//checking the size of the storage and number of elements added
				result = false;
			}
		}

		return result;
	}

	/**
	 * This functions tests all the functionalities of the Storage class
     * 
     * @return	
     */

	public void TestStorageMethods() {
		 if ( ! testAdd() )
             System.err.println("testAdd failed");
		 if ( ! testAddAll() )
             System.err.println("testAddAll failed");
		 if ( ! testRemoveAll() )
             System.err.println("testRemoveAll failed");
		 if ( ! testToArray() )
		         System.err.println("testToArray failed");
		 if ( ! testContains() )
		         System.err.println("testContains failed");
		 if (! testSize())
		 	   System.err.println("testSize failed");
		 if (! testRemove())
		 	   System.err.println("testRemove failed");
		 if (! testClear())
		 	   System.err.println("testClear failed");

		 System.out.println("All test cases completed");
	}
	
	public static void main(String[] args) {
		TestStorage ts= new TestStorage();
		ts.TestStorageMethods();
	}
}
