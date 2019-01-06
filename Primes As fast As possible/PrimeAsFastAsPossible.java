/**
 * PrimeAsFastAsPossible.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.lang.Math;

/**
 * This class reads an integer value and prints all the prime numbers till read number
 */
public class PrimeAsFastAsPossible extends Thread{

	// Declaring variables
    int primeCount = 0;
    int startIndex;
    int endIndex;
    int threadID;
    
    /**
     * Constructor for the threads. It also assigns the threads the partition of the numbers
     * 
     * @param threadId	int		ID of the thread
     * @param totalThreads	int		Total thread count
     * @param maxNumber		int		Max number till which primes are calculated
     */
    public PrimeAsFastAsPossible(int threadId, int totalThreads,int maxNumber ){
    	double partition = Math.ceil((maxNumber/(double)totalThreads));
        this.startIndex =  (int)partition*threadId;
        this.endIndex =  (int)partition*(threadId + 1);        
        if(endIndex > maxNumber)
        	endIndex = maxNumber;
        this.threadID = threadId;
    }
    
    /**
     * Function to check if the number is prime and return true or false
     * 
     * @param	num			int			The number which has to be checked if it is prime
     * @return	isprime		boolean		true if it prime else false
     */
    public boolean isPrime(int num){
        boolean isprime = true;
        if (num == 1)
            isprime = false;

        else if(num == 2 || num ==3)
            isprime = true;

        // All prime numbers are of 6k+1 or 6k+5 form.
        else if( num % 6 == 1 || num % 6 == 5 ){
            // Checking if the number is prime for number of form 6k+1 or 6k+5
            for(int i = 5; i<= Math.sqrt(num); i = i+2){
                if (num % i == 0){
                	// Checking if the 6k+1 and 6k+5 number is a prime
                    isprime = false;
                    break;
                }
            } 
        }
        else{
            // Ignoring other numbers
            isprime = false;
        }
        return isprime;
    }
    
    /**
     * This method is executed when the thread starts running
     * 
     * @return
     */
    public void run(){
        for(int number = startIndex ; number < endIndex; number++){
        	// Checking each number is prime or not
            if(this.isPrime(number)){
            	System.out.println(number);
            	primeCount++;
            }         
        }
    }
    
    
    /**
     * Main function 
     * 
     * @param args	String		Command Line arguments
     */
     public static void main(String []args){
    	 // Declaring value
    	 int upperBound = 0;
    	 int totalPrimes = 0;
    	 
    	 // Handling the command line arguments
    	 if (args.length == 0) {
    		 // No input has been passed
    		 System.out.println("Please provide the upperbound number in command line");
    		 System.exit(1);
    	 }
    	 
    	 else if (args.length ==1) {
    		 // Reading the value from the command line
    		 try{
    			 upperBound = Integer.parseInt(args[0]);
    			 if(upperBound < 0)
    				 // If the given number is a negative
    				 throw new NegativeNumberException();
    		 }catch(NegativeNumberException e) {
    			 // Handling negative numbers
    			 System.out.println("Negative numbers are not allowed");
    			 System.exit(1);
    		 }
    		 catch(Exception e) {
    			 // Handling non Negative numbers
    			 System.out.println("Unknown number. Only integers are allowed");
    			 System.exit(1);
    		 }
    	 }
    	 else if(args.length > 1) {
    		 // More than one argument has been passed
    		 System.out.println("only 1 integer value is allowed");
			 System.exit(1);
    	 }
    	 
    	 // Calculating the number of threads required
    	 int totalThreads = Runtime.getRuntime().availableProcessors() > upperBound/10 ?
                 Runtime.getRuntime().availableProcessors() : (Runtime.getRuntime().availableProcessors() +1 ) * (String.valueOf(upperBound).length());
    
    	 //Creating the threads
    	 PrimeAsFastAsPossible threads[] = new PrimeAsFastAsPossible[totalThreads];
         for(int threadCount =0; threadCount<totalThreads; threadCount++){
             threads[threadCount] = new PrimeAsFastAsPossible(threadCount, totalThreads, upperBound);
         }
         long startTime = System.currentTimeMillis();
         // Starting the threads
         for(PrimeAsFastAsPossible thread : threads) {
             thread.start();
         }
         // Joining to avoid main function continue before threads execution
         for(PrimeAsFastAsPossible thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// Handling the exception
				e.printStackTrace();
			}
         }


    // adding the count of primes in all the threads
    for(PrimeAsFastAsPossible thread : threads) {
    	totalPrimes += thread.primeCount;
    }
    // Displaying the result
    System.out.println("Time taken in seconds:" + (double)((System.currentTimeMillis() - startTime) )/1000);
    System.out.println("Total number of primes:" +totalPrimes);
       
     }
}