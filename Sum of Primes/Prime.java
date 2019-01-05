/**
 * Prime.Java
 * 
 * Version: 1
 * 
 * Revision: 1 
 */
import java.util.Arrays;
/**
 * This program adds all the prime factors of a number and 
 * prints the sum and prime factors
 * 
 * @author Sai sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */
public class Prime {
	/**Size of 4 since there are only 4 primes till 10. 
	*This size has to be increased if running for large number
	**/
	static int[] primeFactors = new int[4]; 
	
	/**
	 * @param 		n 		number to check if it is Prime
	 * @return  boolean True if prime else false
	 */
	public static boolean isPrime(int n) {
		for ( int index = 2; index < n; index ++ ) {
			if ( n % index  == 0 )
				return false;
			}
		 return true;
	}
	
	public static void main(String[] args) {
		/**
		 * The main program.
		 * @param    args    command line arguments (ignored)
		 */
		for (int i = 2; i <= 10; i ++ ) {
			//Reset the values for the next number
			int sum=0;
			int index = i;
			int primeFactorCount = 0;
			Arrays.fill(primeFactors, 0);
			System.out.print("Sum of prime factors of "+ index);
			
			//Checking all the numbers if prime and a factor
			for (int Number=2; Number<=index; Number++) {
				if (isPrime(Number)) { 
					while(index%Number==0) { 
						index = index/Number;
						sum+=Number;
						primeFactors[primeFactorCount] = Number; 
						primeFactorCount++;
					}
				}
			}
			System.out.print(": "+sum + "  (");
			
			//Printing the list of prime factors
			for(int listCount=0;listCount<=primeFactors.length;listCount++) {
				if(primeFactors[listCount]==0) {
					break;
				}
				System.out.print(primeFactors[listCount]+ " ");
			}
			System.out.println(")");
		}
	}	
}