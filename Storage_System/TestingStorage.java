/**
 * TestingStorage.java
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * 
 * Version: $Id$
 * Revision: $log$   
 */

/**
 * This class has all the test cases for the Storage functions
 */

public class TestingStorage {
	
	/**
	 * This functions add functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	public static boolean testAdd()     {
        Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "a", "b", "c" };
        boolean rValue = true;
        for ( int index = 0; index < theStrings.length; index ++ )
                aStorage.add(theStrings[index]);
        for ( int index = 0; index < theStrings.length; index ++ )
                rValue &= aStorage.remove().equals(theStrings[index]);
        rValue &= aStorage.remove() == null;
        aStorage.add("c");
        return rValue;
    }

	/**
	 * This functions Removing element at Index functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	public static boolean testRemoveIndex()     {
		 Storage<String> aStorage = new Storage<String>();
	     String theStrings[] = { "a", "b", "c", "d" , "e" };
	     boolean rValue = true;
	     for (String s :theStrings)
	                aStorage.add(s);
	     if(aStorage.remove(0) != "a")
	    	 rValue = false;
	     if(aStorage.remove(-3) != null)
	    	 rValue = false;
	     if(aStorage.remove(1) != "c")
	    	 rValue = false;
	     if(aStorage.remove(aStorage.size()-1) != "e")
	    	 rValue = false;
	     if(aStorage.remove(55) != null) 
	    	 rValue = false;

	     return rValue;
	}
	
	/**
	 * This functions Adding at index functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */
	public static boolean testAddIndex()     {
		Storage<String> aStorage = new Storage<String>();
		String beforeOperation = null;
        String theStrings[] = { "b",  "c" ,"d"};
        boolean rValue = true;
        for ( String s : theStrings)
                aStorage.add(s);
        aStorage.add(0, "a");
        if (aStorage.element() != "a")
        	rValue = false;
        beforeOperation = aStorage.toString();
        aStorage.add(-1, "a");
        if(! aStorage.toString().equals(beforeOperation))
        	rValue = false;
        aStorage.add(55, "b");
        if(! aStorage.toString().equals(beforeOperation))
        	rValue = false;
        aStorage.add(aStorage.size(), "a");
        if(! aStorage.toString().equals(beforeOperation))
        	rValue = false;
        
        beforeOperation = aStorage.toString();
        aStorage.add(aStorage.size()-1, "a");
        if( aStorage.toString().equals(beforeOperation))
        	rValue = false;
		return rValue;
	}
	
	/**
	 * This functions tests clearing functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */	
	public static boolean testClear()     {
		Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "b",  "c" ,"d"};
        boolean rValue = true;
        aStorage.clear();
		if (aStorage.size() != 0)
			rValue = false;
        for ( String s : theStrings)
                aStorage.add(s);
        aStorage.clear();
		if (aStorage.size() != 0)
			rValue = false;
		return rValue;
	}
	
	/**
	 * This functions tests adding to the first functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */	
	public static boolean testAddFirst()     {
		Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "b",  "c" ,"d"};
        boolean rValue = true;
        for ( String s : theStrings) {
        	aStorage.addFirst(s);
        	if(! aStorage.element().equals(s)) {
        		rValue = false;
        	}
        }
        return rValue;
	}
	
	/**
	 * This functions tests Adding at the end functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */	
	public static boolean testAddLast()     {
		Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "b",  "c" ,"d"};
        boolean rValue = true;
        for ( String s : theStrings) {
        	aStorage.addLast(s);
        }
    	if(! aStorage.remove(aStorage.size()-1).equals(theStrings[2])) {
    		rValue = false;
    	}
        return rValue;
	}
	
	/**
	 * This functions tests getting the first element functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */	
	public static boolean testElement()     {
		Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "b",  "c" ,"d"};
        boolean rValue = true;
        for ( String s : theStrings) {
        	aStorage.addFirst(s);
        	if(! aStorage.element().equals(s)) {
        		rValue = false;
        	}
        }
        return rValue;
	}
	
	/**
	 * This functions tests checking the size functionality of the Storage
     * 
     * @return	boolean		true		Returns true if all test cases are passed
     */	
	public static boolean testSize()     {
		Storage<String> aStorage = new Storage<String>();
        String theStrings[] = { "b",  "c" ,"d"};
        boolean rValue = true;
        for ( String s : theStrings) {
        	aStorage.addFirst(s);
        }
        if (aStorage.size() != 3)
        	rValue = false;
        aStorage.remove();
        if (aStorage.size() != 2)
        	rValue = false;
        return rValue;
	}
	
	
   public static void exampleOfHowToUseIt( Storage<String> aStorage)   {
        aStorage.add("a");
        aStorage.add(0, "0");
        aStorage.add(aStorage.size(), "1");
        aStorage.add(aStorage.size() + 1, "2");
        System.out.println("aStorage: " + aStorage );

    }
   
   
    public static void test()   {
                if ( ! testAdd() )
                        System.err.println("testAdd failed");
                if ( ! testRemoveIndex() )
                        System.err.println("testRemoveIndex failed");
                if ( ! testAddIndex() )
                        System.err.println("testAddIndex failed");
                if ( ! testClear() )
                        System.err.println("testClear failed");
                if ( ! testAddFirst())
                	   System.err.println("testAddFirst failed");
                if ( ! testAddLast())
                	   System.err.println("testAddLast failed");
                if( ! testElement())
                	   System.err.println("testElement failed");
                if (! testSize())
                	   System.err.println("testSize failed");
                
    }
    public static void main(String args[] )     {
 	   /**
 		 * The main program.
 		 * @param    args    command line arguments (ignored)
 		 */
       test();
       exampleOfHowToUseIt(new Storage<String>());
    	
    }
}
