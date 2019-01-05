/**
 * FastAddTest.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

/**
 * This class has the test cases to test all the functionalities of the FastAdd
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */
public class FastAddTest {

	/**
	 * This functions tests the add functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testAdd() {
		boolean result = true;
		FastAdd<Integer> f = new FastAdd<Integer>();
		
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
	 * This functions tests the get functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testGet() {
		
		boolean result = true;
		Integer element ;
		FastAdd<Integer> f = new FastAdd<Integer>();
		Integer[] intArray = {5,3,2,10};
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		int	index =0;
		while((element = f.get()) != null) {
			//Checking if element is coming in order
			if(intArray[index]!=element)
				result = false;
			index++;
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
		FastAdd<Integer> f = new FastAdd<Integer>();		
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
		FastAdd<Integer> f = new FastAdd<Integer>();		
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
	 * This functions tests the isEmpty functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testIsEmpty() {
		boolean result = true;
		FastAdd<Integer> f = new FastAdd<Integer>();		
		Integer[] intArray = {5,3,2,10};
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		if(f.isEmpty()) {
			result = false;
		}
		f.clear();
		if(!f.isEmpty()) {
			result = false;
		}
		return result;
	}
	
	
	/**
	 * This functions tests the sort functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testSort() {
		boolean result = true;
		FastAdd<Integer> f = new FastAdd<Integer>();		
		Integer[] intArray = {5,3,2,10};
		String str ="";
		Integer element;
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		//Sorting the elements in the Storage
		f.sort();
		while((element = f.get()) != null) {
			str += " " + element;
		}
		if (!str.trim().equals("2 3 5 10")) {
			result = false;
		}
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
		FastAdd<Integer> f = new FastAdd<Integer>();
		int size = 0;
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
			size++;
			if(f.size()!= size) {
				result = false;
			}
		}

		return result;
	}
	
	/**
	 * This functions tests the className functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testClassName() {
		boolean result = true;
		FastAdd<Integer> f = new FastAdd<Integer>();
		if(!f.getClassName().equals("FastAdd")) {
			result = false;
		}
		return result;
	}
	
	public void TestFastSorting() {
		 if ( ! testAdd() )
             System.err.println("testAdd failed");
		 if ( ! testGet() )
		         System.err.println("testGet failed");
		 if ( ! testClear() )
		         System.err.println("testClear failed");
		 if ( ! testContains() )
		         System.err.println("testContains failed");
		 if ( ! testIsEmpty())
		 	   System.err.println("testIsEmpty failed");
		 if ( ! testSort())
		 	   System.err.println("testSort failed");
		 if (! testSize())
		 	   System.err.println("testSize failed");
		 if (! testClassName())
		   System.err.println("testClassName failed");
		 System.out.println("All test cases completed");
	}
	
	public static void main(String[] args) {
		FastAddTest t= new FastAddTest();
		t.TestFastSorting();
	}

}
