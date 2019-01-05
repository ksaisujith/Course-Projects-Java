/**
 * TestFastSort.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

/**
 * This class has the test cases to test all the functionalities of the FastSort
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */
public class TestFastSort {

	/**
	 * This functions tests the add functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	private boolean testAdd() {
		boolean result = true;
		FastSort<Integer> f = new FastSort<Integer>();
		
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
		String str ="";
		FastSort<Integer> f = new FastSort<Integer>();
		Integer[] intArray = {5,3,2,10};
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		while((element = f.get()) != null) {
			//getting all the values and adding to the string
			str += " " + element;
		}
		if (!str.trim().equals("2 3 5 10")) {
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
		FastSort<Integer> f = new FastSort<Integer>();		
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
		FastSort<Integer> f = new FastSort<Integer>();		
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
		FastSort<Integer> f = new FastSort<Integer>();		
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
		FastSort<Integer> f = new FastSort<Integer>();		
		Integer[] intArray = {5,3,2,10};
		String str ="";
		Integer element;
		for(Integer n:intArray) {
			//Adding the values
			f.add(n);
		}
		
		//Moving the iterator to the head position of the Storage
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
		FastSort<Integer> f = new FastSort<Integer>();
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
		FastSort<Integer> f = new FastSort<Integer>();
		if(!f.getClassName().equals("FastSort")) {
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
		 System.out.println("All test cases are completed");
	}
	
	public static void main(String[] args) {
		TestFastSort t= new TestFastSort();
		t.TestFastSorting();
	}

}
