/**
 * RegExTest.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/* 
 * 
 * This program takes the complete path of a file as input and reads it line by line. 
 * Every line is evaluated with the help of regular expressions for the below conditions:
 * ´a’ is part of the word.
 *	Palindrome anchored at the beginning and end of line.
 *	Include a palindrome which is 2 characters long.
 *	Include a palindrome which is 3 characters long.
 *	The word has at least one ’a’ in it.
 *	The word consist only of ’a’s or ’b’s.
 *	´a’s or ’b’s can not be part of the word.
 *	The word is == ’.’.
 *	The word includes a ’.’.
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */

public class RegExTest {
	
	public static boolean isPalindromeAnchored(String word) {
		boolean anchored=false;
		boolean beginPalindrome=false;
			for(int j=1;j<word.length();j++) {
				if(isPalindrome(word.substring(0, j).toCharArray())) {
					beginPalindrome = true;
					break;
				}
			}
			if(beginPalindrome) {
				char[] test ;
				for(int j=0;j<word.length();j++) {
					test = word.substring(j, word.length()).toCharArray();
					if(isPalindrome(test)) {
						anchored = true;
						break;
					}
				}	
			}
		
		return anchored;
	}
	
	/**
     * @param 	char[]		wordarray 	word from the file that has to be checked for palindrome
     * @return	boolean		palindrome	Returns True if Palindrome else false
     */
	public static boolean isPalindrome(char[] wordarray){
		boolean palindrome = false;
		//Checking if palindrome for 2 length words
		if(wordarray.length==2) {
			if(wordarray[0] == wordarray[1]){
                palindrome=true;
            }
		}
		//Checking if palindrome for even length words
		else if(wordarray.length%2 == 0){
	          for(int i = 0; i < wordarray.length/2-1; i++){
	              if(wordarray[i] != wordarray[wordarray.length-i-1]){
	                  return false;
	              }
	              else{
	                  palindrome = true;
	              }
	          }
	      }
		//Checking if palindrome for 3 length words
	      else if(wordarray.length==3)
	      {
	    	  if(wordarray[0] == wordarray[2])
				palindrome = true;
	    	  else
	    		  palindrome = false;
	       }
		//Checking if palindrome for odd length words
	      else {
	          for(int i = 0; i < (wordarray.length-1)/2-1; i++){
	              if(wordarray[i] != wordarray[wordarray.length-i-1]){
	                  return false;
	              }
	              else{
	                  palindrome = true;
	              }
	          }
	      }
	      return palindrome;
	  }
	

public static void main(String[] args) throws IOException {
	/**
	 * The main program.
	 * @param    args    command line arguments. Takes the complete path of the file to read the words
	 */
	if(args.length<=0) {
		//Taking the default file if no file is passed
		args = new String[1];
		args[0] = "//home//sujith//eclipse-workspace//RegExTest//Words.txt";
	}
	else if(args.length>1) {
		System.out.println("Wrong path of the file has been give. Path of file shouldn't contain spaces");
		System.exit(1);
	}
	
	String word  = null;
    Scanner input = new Scanner(new File(args[0])); 
    	while (input.hasNext()) {
    	  word  = input.next();
    	  System.out.println("The word read in the file is: " +word);
    	  char[] wordarray = word.toCharArray(); 
    	  
          //Defining Patterns for the tests
          Pattern pattern1 = Pattern.compile(".*a.*");
          Pattern pattern2 = Pattern.compile(".a.b.*");
          Pattern pattern3 = Pattern.compile(".");
          Pattern pattern4 = Pattern.compile(".*[.]+.*");
          Pattern pattern5 = Pattern.compile("([[^b]&&[^a]])*");
          
          //Matching the string with the Pattern
          Matcher containsA = pattern1.matcher(word);
          Matcher WordContainsOnlyAB = pattern2.matcher(word);
          Matcher equalsDot = pattern3.matcher(word);
          Matcher containsDot = pattern4.matcher(word);
          Matcher wordsWithNoAB = pattern5.matcher(word);
          
          
          if(isPalindrome(wordarray)) {
        	  System.out.println("\tIncludes a palindrome which is "+wordarray.length+ " characters long");
          }
          
          if(containsA.matches()) {
        	  System.out.println("\t'a' is the part of the word");
        	  System.out.println("\tThe word has at least one 'a' in it.");
          }
          
          if(WordContainsOnlyAB.matches()) {
              System.out.println("\tThe word consist only of as or b.");  
          }
          
          if(equalsDot.matches()) {
        	  System.out.println("\tThe word is == .");
          }
          
          if(containsDot.matches()) {
        	  System.out.println("\tThe word includes .");
          }
          
          if(wordsWithNoAB.matches()) {
        	  System.out.println("\tas or bs are not part of the word.");
          }
          
          if(isPalindromeAnchored(word)) {
        	  System.out.println("\tWord is anchored by Palindrome at the begin and end.");
          }
    	}
    	input.close();
  }
}