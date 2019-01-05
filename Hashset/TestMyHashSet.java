/**
 * TestMyHashSet.java
 *
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 *
 * Version: $Id$
 * Revision: $log$
 */

/*
 * This class tests all the methods of MyHashSet
 */

public class TestMyHashSet {
	/**
	 * This method tests the add functionality of the hashset
	 * 
	 * @param
	 * @return		successStatus 		boolean		true if all the elements are added 
	 */
	public boolean testAdd() {
		// Initializing the variables
		boolean successStatus = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		// Adding the elements to current set
		for (String str : strings)
			aSet.add(str);
		// Checking if all the elements are present in the hashmap
		for(Object str2 : strings)
			successStatus &= aSet.contains(str2);
		// Checking if no extra element is added to the hashmap
		if(aSet.contains("bbb"))
			successStatus = false;
		return successStatus;
	}
	
	
	/**
	 * This method checks addAll functionality of the hashset
	 * 
	 * @param
	 * @return	result	boolean	True if all elements are added
	 */
	public boolean testAddAll() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		SetI<String> bSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		String[] strings_2 = {"pqr","Ss","ABC",null,"cab"};
		// Adding the elements to current set
		for (String str : strings)
			aSet.add(str);		
		for (String str : strings_2)
			aSet.add(str);	
		// Adding all the elements of bSet to aSet
		aSet.addAll(bSet);
		// Checking all elements of set aSet are present
		for (String str : strings)
			result &= aSet.contains(str);
		// Checking all elements of set bSet are present
		for(String str : strings_2)
			result &= aSet.contains(str);		
		return result;
	}
	
	
	/**
	 * This method tests the containsAll functionality of the hashset
	 * 
	 * @param
	 * @return result	boolean		true if all the elements are present in the passed 
	 */
	public boolean testContainsAll() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		SetI<String> bSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		// Adding the elements to both the sets
		for (String str : strings) {
			aSet.add(str);
			bSet.add(str);
		}
		// Checking all the elements are present in the hashset
		result &= aSet.containsAll(aSet);
		result &= aSet.containsAll(bSet);
		// Adding a new element to make the second set unequal
		bSet.add("Java");
		// Comparing it again with the new set
		result = !aSet.containsAll(bSet);
		return result;
	}
	
	
	/**
	 * This method tests the removeAll functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testRemoveAll() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		SetI<String> bSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		// Adding the elements to current set
		for (String str : strings) {
			aSet.add(str);
			bSet.add(str);
		}
		// Removing the elements
		aSet.removeAll(bSet);
		result = aSet.size() == 0 ? true:false;
		return result;
	}

	
	/**
	 * This method tests the clear functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testClear() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		// Adding the elements to current set
		for (String str : strings)
			aSet.add(str);
		result = aSet.size() != 0 ? true: false;
		//Clearing the hashSet
		aSet.clear();
		result = aSet.size() == 0 ? true : false;
		// Checking if still an element is present after clearing the set
		result = aSet.contains("abc") ? false : true;
		return result;
	}
	
	/**
	 * This method tests the contains functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testContains() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		// Adding the elements to current set
		for (String str : strings)
			aSet.add(str);
		for (String str : strings)
			result &= aSet.contains(str);
		result = aSet.contains("Java") ? false : true ;
		return result;
	}
	
	
	/**
	 * This method tests the equals functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testEquals() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		SetI<String> bSet = aSet;
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		// Adding the elements to current set
		for (String str : strings)
			aSet.add(str);

		// Comparing the objects
		result = aSet.equals(bSet);
		return result;
	}

	/**
	 * This method tests the hashcode functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testHashCode() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		SetI<String> bSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		
		// Adding the elements to current set
		for (String str : strings) {
			aSet.add(str);
			bSet.add(str);
		}
		
		// Both have the same elements, hash code should be same for both the sets
		result &= aSet.hashCode() == bSet.hashCode();
		
		return result;
	}
	
	
	/**
	 * This method tests the isEmpty functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testIsEmpty() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		
		// Adding the elements to current set
		for (String str : strings) {
			aSet.add(str);
		}
		// it is not empty
		result = !aSet.isEmpty();
		
		// Empty the hashset
		aSet.clear();
		result = aSet.isEmpty();
		
		return result;
	}
	
	/**
	 * This method tests the remove functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testRemove() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		
		// Adding the elements to current set
		for (String str : strings) {
			aSet.add(str);
		}
		
		// Removing null value
		aSet.remove(strings[2]);
		result &= !aSet.contains(strings[2]);
		
		// Remove the same value twice
		aSet.remove(strings[2]);
		result &= !aSet.contains(strings[2]);
		
		// Removing non-null value
		aSet.remove(strings[3]);
		result &= !aSet.contains(strings[3]);
		
		
		// Checking if removal didn't remove any different element
		result &= aSet.contains(strings[0]);
		
		return result;
	}
	
	
	/**
	 * This method tests the size functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testSize() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		
		// Adding the elements to current set
		for (String str : strings) {
			aSet.add(str);
		}
		// checking the size
		result &= aSet.size() == 7;
		int counter = 2;
		for (String str : strings) {
			if(aSet.remove(str)) {
				// Checking size after removal
				result &= aSet.size() == strings.length - counter;
				counter++ ;
			}
		}
		return result;
	}
	
	
	/**
	 * This method tests the toArray functionality of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if functionality is working correctly
	 */
	public boolean testToArray() {
		// Initializing the variables
		boolean result = true;
		SetI<String> aSet = new MyHashSet<String>();
		String[] strings = {"abc","ab",null,"123","xyz","cba","cab",null};
		
		// Adding the elements to current set
		for (String str : strings) {
			aSet.add(str);
		}
		// looping through each element
		for (Object obj : aSet.toArray()) {
			boolean found = false;
			for (int index = 0 ; index<strings.length; index++) {
				try {
					if(strings[index].equals(obj)) {
						found = true;
						break;
					}
				} catch(NullPointerException e) {
					if (strings[index] == null && obj == null) {	
						found = true;
						break;
					}
					continue;					
				}
			}
			result &= found;
		}
		return result;
	}
	
	
	/**
	 * This method tests all the functionals of the hashset
	 * 
	 * @param
	 * @return		result		boolean			True if all functionalities are working correctly
	 */
	public boolean testMyHashSet() {
		boolean allCasesPassed = true;
		if(!testAdd()) {
			System.err.println("Test Add Failed");
			allCasesPassed = false;
		}
		if(!testAddAll()) {
			System.err.println("testAddAll Failed");
			allCasesPassed = false;
		}
		if(!testContainsAll()) {
			System.err.println("testContainsAll Failed");
			allCasesPassed = false;
		}
		if(!testRemoveAll()) {
			System.err.println("testRemovesAll Failed");
			allCasesPassed = false;
		}
		if(!testClear()) {
			System.err.println("Test Clear Failed");
			allCasesPassed = false;
		}
		if(!testContains()) {
			System.err.println("testContains Failed");
			allCasesPassed = false;
		}
		if(!testEquals()) {
			System.err.println("testEquals Failed");
			allCasesPassed = false;
		}
		if(!testHashCode()) {
			System.err.println("testHashCode Failed");
			allCasesPassed = false;
		}
		if(!testIsEmpty()) {
			System.err.println("testIsEmpty Failed");
			allCasesPassed = false;
		}
		if(!testRemove()) {
			System.err.println("testRemove Failed");
			allCasesPassed = false;
		}
		if(!testSize()) {
			System.err.println("testSize Failed");
			allCasesPassed = false;
		}
		if(!testToArray()) {
			System.err.println("testToArray Failed");
			allCasesPassed = false;
		}
	
		System.out.println("Execution of all test cases completed");		
		return allCasesPassed;
	}
	
    public static void main(String args[] )      {
    	TestMyHashSet t = new TestMyHashSet(); 
    	System.out.println( t.testMyHashSet()? "All Cases passed" : "Please check previous errors");   	
    	
        SetI<String> aSet = new MyHashSet<String>();
        SetI<String> bSet = new MyHashSet<String>();

        String[] aStrings = { "a", "b", "c" };
        String[] bStrings = { "A", "B", "C" };
        aSet.add(aStrings[0]); aSet.add(aStrings[1]);           // setup a, b
        bSet.add(bStrings[0]); bSet.add(bStrings[1]);           // setup A, B

        System.out.println("aSet = " + aSet );                  // --> a, b

        for (int index = 0; index < aStrings.length; index ++ ) {       // contans a and b, not c
            System.out.println("does " +
                    ( aSet.contains(aStrings[index]) ? "" : " not " ) + "contain: " +
                    aStrings[index] );
        }
        System.out.println("aSet = " + aSet );                  // --> a, b

        System.out.println("aSet.remove(aStrings[0]); = " + aSet.remove(aStrings[0]) ); // contains b
        System.out.println("aSet.remove(aStrings[2]); = " + aSet.remove(aStrings[2]) ); // can not remove x
        System.out.println("aSet = " + aSet );

        aSet.addAll(bSet);                                      // --> b, A, B
        System.out.println("aSet = " + aSet );


        aSet.add(null);                                         // --> b, A, B, null
        System.out.println("aSet = " + aSet );
        aSet.add(null);
        System.out.println("aSet = " + aSet );
        System.out.println("aSet.remove(null); = " + aSet.remove(null) );       // can remove null
        System.out.println("aSet = " + aSet );
        
    }
}
