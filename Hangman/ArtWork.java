/**
 * ArtWork.java
 * Version: $Id$
 * Revision: $log$  
 *    
 */

import java.util.Scanner;
import java.io.*;
import java.util.Random;

/**
 * This program takes the complete path of a file as input and picks a random word. 
 * User has to guess the word, one character at a time.
 * For every wrong guess, a part of the artwork is revealed
 * For every right guess, the guessed letter position is revealed in the word.
 * Game ends when the user runs out of the maximum tries
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */
public class ArtWork {
	
	static public int openNLines(int number) {
		return ((number*(number+1))/2)+1;
	}

	/**
     * @param 	fileName 	Complete path of the file from which the random word has to be picked
     * @return	myWord		Random word that has to be guessed		
     */
	static public String getRandomWord(String fileName) {
		String myWord=null;
		int numOfLines=0;
		int randomLine=0;
		try{
			//Reading the number of lines in the file provided
			Scanner scan = new Scanner(new File(fileName));
			while(scan.hasNextLine()) {
				numOfLines++;
				scan.nextLine();
			}
			scan.close();
			scan=null;
			
			//Reading the word from the random line number
			scan = new Scanner(new File(fileName));
			randomLine = new Random().nextInt(numOfLines++);
			for(int line=0;line<numOfLines++;line++) {
				if(line==randomLine) {
					myWord = scan.nextLine();
				break;
				}
				else
					scan.nextLine();
			}
			scan.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			System.exit(0);
		}
		return myWord;
	}

	
	/**
     * @param 	WrongGuessCount 	Count of wrong guess
     * @param	MaxRetries			Count of Maximum wrong guesses allowed
     * @return			Null
     */
	static public void showArtWork(int wrongGuessCount,int MaxRetries) {
		int displayPart = 2*openNLines(wrongGuessCount); 
		
		//Filling the ArtWork
		char[][] myDisplay = new char[100][100];
		for(int i =0;i<100;i++) { //columns
			for(int j=0;j<100;j++) { //Rows
				if(i==j || i+j==99|| i==51 || j==51){
					myDisplay[i][j]='@';
				}
				else {
					myDisplay[i][j]='+';
				}
			}
		}
		
		//Displaying part of the ArtWork
		for(int i =0;i<100;i++) { //columns
			for(int j=0;j<100;j++) { //Rows
				if(j<displayPart || i<displayPart)
					System.out.print(myDisplay[i][j]);
				else
					System.out.print(".");
			}
			System.out.println("");
		}
	}
	
	
	/**
     * @param 	guessWord 	Takes a string, which has to be guessed by the player
     * @return				void
     */
	public static void PlayGame(String guessWord) {
		int wrongGuessCount = 0;
		int maxWrongGuess=9;
		maxWrongGuess -= 1;
		String displayString = null;
		Scanner scan = new Scanner(System.in);
		int index=-1;
		
		//Creating a Character array variable to store the display items
		char[] displayChars = new char[guessWord.length()];
		for(int i=0;i<displayChars.length;i++)
			displayChars[i]='_';
		do {
			//Print the word with _ and guessed words
			for(int i=0;i<displayChars.length;i++)
				System.out.print(displayChars[i]+" ");
			System.out.println("\nPlease guess a character");
			
			//Reading the guessed input from user
			char userInput=scan.nextLine().charAt(0);
			
			//Checking if the input character is present in the word
			if(guessWord.indexOf(userInput)>=0)
			{
				do{
				    index = guessWord.indexOf(Character.toString(userInput),
				    		index + 1);
				    if(index>=0)
				    	displayChars[index]=userInput;
				}while (index >= 0);
				
				//Checking if whole word is guessed
				displayString = new String(displayChars);
				if(displayString.indexOf("_")<0){
					System.out.println("Congratulations!! you have guessed correctly");
					System.out.println(displayString);
					break;
				}			
			}
			else
			{
				//Showing the artwork since the guess is wrong
				showArtWork(++wrongGuessCount,maxWrongGuess);
			}
		}while(wrongGuessCount<=maxWrongGuess);
		
		if(wrongGuessCount>=maxWrongGuess )
			//Ran out of maximum guesses
			System.out.println("\n\nMaximum Number of guesses reached :(  \nBetter Luck next Time!!!");
		scan.close();
	}
	
	public static void main(String[] args) {
		/**
		 * The main program.
		 * @param    args    command line arguments. Takes the complete path of the file to read the words
		 */
		
		if(args.length !=1) {
			System.out.println("Wrong path of the file has been give. Path of file shouldn't contain spaces");
			System.exit(1);
		}
		
		PlayGame(getRandomWord(args[0]));
	}
}
