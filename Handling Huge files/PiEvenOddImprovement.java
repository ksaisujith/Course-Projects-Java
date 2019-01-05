/**
 * PiEvenOddImprovement.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
/**
 * This program reads data from a file in bytes and counts even numbers and odd numbers in it
 */

public class PiEvenOddImprovement {
	// Initializing the values
	static int evenCount = 0;
	static int oddCount = 0;
	static byte[] byte_buffer = new byte[3024];
	
	/**
	 * Counts even and odd numbers in the passed string
	 * 
	 * @param buffered_number
	 */
	public static void countEvensOdds(String buffered_number) {
		int number;
		for(int i =0;i<buffered_number.length();i++) {
			// Taking each digit in the string passed
			number = Character.getNumericValue((buffered_number.charAt(i)));
			// Checking if passed are only numbers and not characters
			if(number >= 0 && number <10) {
				// Checking if the number is even or odd
				if ((number & 1 ) == 0)
					evenCount++;
				else
					oddCount++;
			}			
		}
	}

	/**
	 * Main function
	 * @param 		args 		String[] 	Command line arguments
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		boolean isZipfile = false;
		String file_location = null;
		// Main function
		if(args.length == 1) {
			isZipfile = args[0].contains(".gz") ? true : false;
			file_location = args[0];
		}
		
		else {
			// Reading the file from the user since no file has been passed through command line
			System.out.println("Please provide the file location");
			Scanner scan = new Scanner(System.in);
			file_location = scan.nextLine();
			isZipfile = file_location.contains(".gz") ? true : false;
			scan.close();
		}
		if(isZipfile) {
			// Reading the zipped file from command line argument
			
			GZIPInputStream zip_file = null;
			try {
				 zip_file = new GZIPInputStream(
						new FileInputStream(file_location));	
				
				 // Checking if file is empty
				if(zip_file.available() == 0)
					throw new EmptyFileException();
				startTime = System.currentTimeMillis();
				
				// Reading the data into the buffer
		        while (zip_file.read(byte_buffer) > 0) 
		        	countEvensOdds(new String(byte_buffer));
		        
		        // Checking if there is at least one number in the file
				if (oddCount ==0 && evenCount ==0)
					throw new NoNumbersException();
			}catch(FileNotFoundException e) {
				// Catching the exception if the file path is wrong 
				System.out.println("File not found at "+ file_location 
						+".\nPlease Check the path given");
			}
			catch(NoNumbersException e) {
				// Catching the exception if there are no numbers in file 
				System.out.println("No numbers are present in the file passed. Please check");
			}
			catch(EmptyFileException e) {
				 // Catching the exception if the file is empty
				System.out.println("File is empty. Please check it");
			}
			catch(Exception e) {
				// Handling all other errors
				System.out.println("Unknown error");
				e.printStackTrace();
			}
			
			finally{
				// Closing all the opened files
				try {
					zip_file.close();
				} catch (IOException e) {
					// Handling the exceptions while closing the file
					e.printStackTrace();
				}
				catch (Exception e) {					
				}
			}
		}
		
		else {
			FileInputStream textFile = null;

			try {
				// Reading the file that is passed
				textFile = new FileInputStream(file_location);

				// Checking if file is empty
				if (textFile.available() == 0)
					throw new EmptyFileException();
				startTime = System.currentTimeMillis();
				
				// Reading the data into the buffer
				while((textFile.read(byte_buffer))>0) 
					countEvensOdds(new String(byte_buffer)); //Counting the even and odd numbers in the file
				
				// Checking if at least one number is found
				if(evenCount == 0 && oddCount ==0)
					throw new NoNumbersException(); 	
			}catch(FileNotFoundException e) {
				// Catching the exception if the file path is wrong 
				System.out.println("File not found at "+ file_location 
						+".\nPlease Check the path given");
			}
			catch(NoNumbersException e) {
				//// Catching the exception if the there are no numbers
				System.out.println("No numbers are present in the file passed. Please check");
			}
			catch(EmptyFileException e) {
				// Catching the exception if the file is empty
				System.out.println("File is empty. Please check it");
			}
			catch(Exception e) {
				// Catching all the other exceptions 
				System.out.println("Unknown error");
				e.printStackTrace();
			}
			finally{
				try {
					// closing all the resources
					textFile.close();
				} catch (IOException e) {
					// Handling the exceptions while closing the file
					e.printStackTrace();
				}
				catch (Exception e) {					
				}
			}
		}
		
		// Printing the output
		System.out.println("Even = " + evenCount +
				"\nOdd = " + oddCount +
				"\nOdd/Even = " + (double)oddCount/evenCount +
				"\n\nTime taken to calculate: " + (double)((System.currentTimeMillis() - startTime) /1000) + "seconds" );
	}
}
