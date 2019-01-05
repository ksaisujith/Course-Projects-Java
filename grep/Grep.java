/**
 * Grep.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program does a set of functionalities of the grep command in Linux machines
 */

public class Grep {
	
	/**
	 * Counts the occurrence of patterns in the string
	 * 
	 * @param 	file		String		Location of the file in which the patters has to be searched
	 * @param 	pattern		String		Pattern for which it has to search
	 * @return	result		String[]	all the lines in which the pattern is present	
	 */
	public String[] option_c(String file,String pattern) {
		int count = 0;
		String[] result = new String[500];
		BufferedReader textFile = null;
		try {
			// Reading the file to search
			 textFile = new BufferedReader(new FileReader(file));
			 String line = null;
			 // Reading each line in the input file
			while((line = textFile.readLine() )!= null){
				// Checking if the line has the search pattern
				if (line.contains(pattern)) {
					// adding the count
					result[count] = line;
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			// Handling if the file doesn't present in the provided location
			System.out.println("File not found at "+ file);
		}
		catch(Exception E) {
			// Handling all other exceptions
			System.out.println("Unknown error");
			E.printStackTrace();
		}
		finally {
			try {
				// closing the file
				textFile.close();
			} catch(Exception e) {				
			}
		}
		return result;
	}
	
	
	/**
	 * Counts the occurrence of patterns as words in the string
	 * 
	 * @param 	file		String		Location of the file in which the patters has to be searched
	 * @param 	pattern		String		Pattern for which function searches
	 * @return	result		String[]	all the lines in which the pattern is present	
	 */
	public String[] option_w(String file,String pattern) {
		String[] result = new String[500];
		int index =0;
		BufferedReader textFile = null;
		try {
			// Reading the file
			 textFile = new BufferedReader(new FileReader(file));
			 String line = null;
			while((line = textFile.readLine() )!= null){
				// Checking if the line has the search pattern
				if (line.matches(".*\\b"+pattern+"\\b.*")) {
					result[index] = line;
					// Adding that line to the result
					index++;
				}
			}
		} catch (FileNotFoundException e) {
			// Handling if the file is empty
			System.out.println("File not found at "+ file);
		}
		catch(Exception E) {
			// Handling all other errors
			System.out.println("Unknown error");
		}
		finally {
			try {
				// Closing the file
				textFile.close();
			} catch(Exception e) {				
			}
		}
		return result;
	}
	
	/**
	 * Searches all the files in the directory for the pattern
	 * 
	 * @param 	file		String		directory in which the patters has to be searched
	 * @param 	pattern		String		Pattern for which it has to search
	 * @return	result		String[]	all the files in which the pattern is present	
	 */
	public String[] option_l(String file, String pattern) {		
		// Initialization of the varables
		File dir = new File(file);
		File[] allFiles = null;
		if(file.contains(".txt")) {
			 allFiles = new File[1];
			 allFiles[0] = dir;
		}
		else {
			allFiles = dir.listFiles();
		}
		String[] result = new String[allFiles.length];
		int index = 0;
		int counter;
		for(File f : allFiles) {
			// Reading each file and searching for the pattern
			counter = 0;
			try {
				if(f.getName().contains(".txt"))
					// Reading only the text files and ignoring the rest
					for (String res : option_c(f.getCanonicalPath(),pattern)) {
						if (res != null)
							counter++;
					}
					if(counter>0) {
						// Adding file name to the result if pattern is found
						result[index] = f.getName();
						index++;
					}
			} catch (IOException e) {
				// Handling the exception while opening 
				e.printStackTrace();
			}
			catch(Exception e) {}
		}		
		return result;
	}
	
	/**
	 * Main function
	 * @param 		args 		String[] 	Command line arguments
	 */
	public static void main(String[] args) {
		Grep g = new Grep();
		// Initializing the variables
		System.out.println("Hello!! Please enter your command in the below format"
				+ "\ngrep [options] pattern [File]"
				+ "\ninput command exit to exit ");
		Scanner scan = new Scanner(System.in);
		String command = null;
		String pattern = null;
		String file = null;
		String[] options = null;
		String[] result = null;
		String outputResult = null;
		
		commandLoop : while(true) {
			if(args != null && args.length >0) {
				// Handling the command line Arguments
				System.out.println("\n");
				command = "grep ";
				for(String c : args)
					command += c+" ";
				command = command.trim();
				args = null;
			}
			else {
				// Reading the command from the user
				System.out.print("\n>> ");
				command = scan.nextLine();
			}
			if(command.equals("exit"))
				// if the command isa exit command
				break;
			else {
				int count = 0;
				if(command.contains("grep")) {
					// Splitting the command 
					try {
					count = command.substring(0, command.lastIndexOf('-')).length() -
							command.substring(0, command.lastIndexOf('-')).replace(" ", "").length();
					}catch(Exception e) {
						// Handling if wrong command is provided
						System.out.println("Please recheck the command: " + command);
						continue;
					}
				}
				
				// Splitting and reading the command given
				String[] command_params = command.split(" ", count+3);
				if(command_params[0].equals("echo")) {
					// If the command is echo
						boolean lastResultSuccess = false;
						if(result != null) {
							for (String res : result) {
								if(res != null) {
									// Checing for the last command is sucess or not
									lastResultSuccess = true;
									break;
								}
							}
						}
						else {
							result = new String[1];
						}
						if(lastResultSuccess)
							System.out.println("0");
						else 
							System.out.println("1");
						result[0] = "0";
						continue;
					}
				else if(!command_params[0].equals("grep")) {
					// If some other command is given
					System.out.println("Unkown Command " + command_params[0]);
					continue;
				}
				else {
					// Reset the previous result
					result = null;
					file = command_params[command_params.length - 1];
					pattern = command_params[command_params.length - 2];
					options = Arrays.copyOfRange(command_params, 1, command_params.length - 2);		
					// Validating the options
					for(String option: options) {
						if(option.charAt(0) != '-') {
							System.out.println("Unknown option" + option
									+ "options should start with -. Example: -c -w");
							continue commandLoop;
						}
						if(option.length() != 2 ||
								(option.charAt(1) != 'c' && option.charAt(1) != 'w' && option.charAt(1) != 'l' && option.charAt(1) != 'q')) {
							System.out.println("Unknown option "+ option 
									+ "\nonly available options are -c -w -l -q");
							continue commandLoop;
						}
					}
					
					
					// Read the option L
					if(Arrays.asList(options).contains("-l")) {
						outputResult = "";
						if(file.contains(".txt")) {
							// Taking only the directory if the file is given
							file = file.substring(0, file.lastIndexOf("\\")+1);
						}
						result = g.option_l(file, pattern);
						for(String res:result) {
							if(res != null)
								outputResult += res +"\n";
						}
					}
					
					// handling w option
					if(Arrays.asList(options).contains("-w")) {
						if(result == null) {
							// No option L was given
							outputResult = "";
							result = g.option_w(file, pattern);
							for(String res:result) {
								if(res != null)
									outputResult += res + "\n";
							}
						}
						else {
							// combination of L and W
							outputResult = "";
							for (String res : result) {
								if(res != null) {
									// Applying option W on the option L result
									String[] lines = g.option_w(file+res, pattern);
									for(String line:lines) {
										if(line != null) {
											outputResult += res +"\n";
											break;
										}
									}
								}
							}
							result = outputResult.split("\n");							
						}
					}
					
					//handling c option
					if(Arrays.asList(options).contains("-c")) {
						count = 0;
						if(result ==null) {
							// Only option C
							result = g.option_c(file, pattern);
							for(String res:result) {
								if(res!= null)
									count++;
							}
							outputResult = Integer.toString(count);
						}
						else if(Arrays.asList(options).contains("-w") && !Arrays.asList(options).contains("-l")) {
							// option c and w
							for(String res : result) {
								if(res!= null)
									count ++;
							}
							outputResult = Integer.toString(count);
						}
						else if(Arrays.asList(options).contains("-l")) {
							// All the three options
							outputResult = "";
							for(String res: result) {
								count = 0;
								if(res != null) {
									String[] lines = g.option_c(file+res, pattern);
									for(String str : lines)
										if(str != null)
											count++;
									outputResult += res + " : " + Integer.toString(count) + "\n";
								}
							}
						}
					}
					
					//Handling q option
					if(!Arrays.asList(options).contains("-q")) {
						System.out.println(outputResult);
					}
				}
			}
		}
		scan.close();
	}
}