/**
 * TestMath.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

/**
 * This program implements 3 Math functions: Max, abs and square root. 
 * Max takes two integers as input and returns the maximum between them
 * Abs returns the absolute value of the number
 * sqrt finds the square root of a number using Babylonian method and prints the error and square root
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */
public class TestMath {
    
	/**
     * @param 	number 	double value to which the absolute value has to be returned
     * @return			absolute value of the input
     */
	static double abs(double number){
		//Changing the magnitude before returning if the number is negative
		return number>=0 ? number:(-1*number);
	}
	
	/**
     * @param 	number1 	integer value to which has to be compared
     * @param 	number2 	integer value to which has to be compared
     * @return				maximum number between the two numbers
     */
	static int max(int number1, int number2) {
		//Comparing and returning the maximum number
		return number1>number2?number1:number2;
	}
	
	/**
     * @param 	number 	double value to which the square root has to be found
     * @return			approximate square root that is found using babylonian method
     */
	static double sqrt(double number) {
		//Returning number if number is 0 or 1 since the sqrt(0)=1 and sqrt(1) == 1 
		if(abs(number) == 0 || abs(number) == 1)
			return abs(number);

		//Finding the square root of absolute values since negative numbers dont have a square root
		if(number<0)
			System.out.print("Negative numbers can\'t have a square root. "
					+ "\nHence finding square root for its absolute value");
		number = abs(number);
		double root=number; //x0
		double epsilon= 0.000000001;
		int count=0;
		do {
			//calculating the root by Babylonian method
			root = (root+number/root)/2;
			System.out.println("x_"+count+": "+root);
			count++;
		}while(epsilon<(abs((root*root)-number)));		//Stopping the loop when the difference is less than epsilon
		System.out.println("Sqrt("+number+") ~= "+root+
				"\nSquare of the root found:"+(root*root)+
				"\nError: "+((root*root)-number));
		return root;
	}

	/**
     * @param 	NULL
     * @return	NULL
     */
	static void   testSqrt()    {
		if ( 0 != sqrt(0) )
	            System.out.println("Test 1: sqrt failed");
	    if ( 0 != sqrt(-0) )
	            System.out.println("Test 2: sqrt failed");
	    if ( Double.NaN == sqrt(-1) )
	            System.out.println("Test 3: sqrt failed");
	    if ( Double.NaN == sqrt(Double.NaN) )
	            System.out.println("Test 4: sqrt failed");
	    
	    double result;
	    double epsilon= 0.000000001;
	    double theDoubles[] = {-4,1, 2, 3, 4, 5 };
	    for ( int index = 0; index < theDoubles.length; index ++ )      {
	    	System.out.println("\n\nTesting Square Root for:" + theDoubles[index] );
	            result = sqrt(theDoubles[index]);
	            if ( abs( result * result - abs(theDoubles[index] )) > epsilon )
	                    System.out.println("Test 5: sqrt failed: " + ( result * result - abs(theDoubles[index] )));
	    }
	}

	/**
     * @param 	NULL
     * @return	NULL
     */
	static void testMax() {
		
		System.out.println("\n\nRunning test cases for Max function");
		if(max(4,5)!=5)
			System.out.println("Test 1 Failed");
		if(max(-1,-2)!=-1)
			System.out.println("Test 2 Failed");
		if(max(-1,3)!=3)
			System.out.println("Test 3 Failed");
		if(max(3,-1)!=3)
			System.out.println("Test 4 Failed");
		if(max(Integer.MAX_VALUE,Integer.MIN_VALUE)!=Integer.MAX_VALUE)
			System.out.println("Test 5 Failed");
		if(max(Integer.MAX_VALUE,Integer.MAX_VALUE)!=Integer.MAX_VALUE)
			System.out.println("Test 6 Failed");
		if(max(0,-1)!=0)
			System.out.println("Test 7 Failed");
		
		System.out.println("All test cases Completed running ");
	}
	
	/**
     * @param 	NULL
     * @return	NULL
     */
	static void testAbs() {
		System.out.println("\n\nRunning test cases for abs function");
		if(abs(-2)!=2)
			System.out.println("Test 1 Failed");
		if(abs(0)!=0)
			System.out.println("Test 2 Failed");
		if(abs(-0)!=0)
			System.out.println("Test 3 Failed");
		if(abs(2)!=2)
			System.out.println("Test 4 Failed");
		if(abs(Double.NaN)==Double.NaN)
			System.out.println("Test 5 Failed");
		
		System.out.println("All test cases Completed running ");
	}

	
	public static void main(String[] args) {
		/**
		 * The main program.
		 * @param    args    command line arguments (ignored)
		 */		
		testSqrt();
		testMax();
		testAbs();
	}

}
