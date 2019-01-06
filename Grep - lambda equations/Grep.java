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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	 * @return	result		int			count of lines which contains pattern	
	 */
	public int option_c(String file,String pattern) {		
		try(BufferedReader textFile= new BufferedReader(new FileReader(file))) {
			return (int)
					textFile.lines().
					filter(line -> line.contains(pattern)).
					count();	            
		} catch (FileNotFoundException e) {
			// Handling if the file doesn't present in the provided location
			System.out.println("File not found at "+ file);
		}
		catch(Exception E) {
			// Handling all other exceptions
			System.out.println("Unknown error");
			E.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * Counts the occurrence of patterns as words in the string
	 * 
	 * @param 	file		String		Location of the file in which the patters has to be searched
	 * @param 	pattern		String		Pattern for which function searches
	 * @return	constant	Object[]	list of lines which contain the pattern in the file	
	 */
	public Object[] option_w(String file,String pattern) {
		try(BufferedReader textFile = new BufferedReader(new FileReader(file))) {
			// Reading the file
			return  textFile.lines().
					filter(line -> line.matches(".*(^|\\W)" + pattern + "(\\W|$).*")).
					toArray();
		} catch (FileNotFoundException e) {
			// Handling if the file is empty
			System.out.println("File not found at "+ file);
		}
		catch(Exception E) {
			// Handling all other errors
			System.out.println("Unknown error");
		}
		return new Object[1];
	}
	
	/**
	 * Searches all the files in the directory for the pattern
	 * 
	 * @param 	file		String			directory in which the patters has to be searched
	 * @param 	pattern		String			Pattern for which it has to search
	 * @return	file_list	List<String>	all the files in which the pattern is present	
	 */
	public List<String> option_l(String file, String pattern) {		
		// Initialization of the variables
		List<String> file_list =  new ArrayList<String>();
		try{
			for(File f : new File(file).listFiles()) {
			// Reading each file and searching for the pattern
				if(f.getName().contains(".txt")
						&& option_c(f.getCanonicalPath(),pattern)>0 ) { 
					file_list.add(f.getName());
					}
			}
		}catch (IOException e) {
				// Handling the exception while opening 
				e.printStackTrace();
			}
		catch(NullPointerException e) {
			System.out.println("wrong file provided. Only txt or directories are accepted");
		}
			catch(Exception e) {e.printStackTrace();}		
		return file_list;
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
		String outputResult = "";
		
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
				// Reading the command from stdin
				System.out.print("\n>> ");
				command = scan.nextLine();
			}
			
			if(command.equals("exit"))
				// if the command is a exit command
				break;
			else {
				
				int count = 0;
				if(command.contains("grep")) {
					// Counting the number of parameters the command 
					try {
					count = command.substring(0, 
							command.lastIndexOf('-')).length() - 
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
						if(outputResult.equals("") || outputResult.equals("0") ||  outputResult.contains("null"))
							System.out.println("1");
						else 
							System.out.println("0");
						outputResult = " ";
						continue;
					}
				else if(!command_params[0].equals("grep")) {
					// If some other command is given
					System.out.println("Unkown Command " + command_params[0]);
					continue;
				}
				else {
					// Reset the previous result
					outputResult = "";
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
					
					// All the options
					List<String> options_given =  Arrays.asList(options);
					 
					//reading the directory of the file given
					String file_dir = file.contains(".txt") && file.contains("\\") ? 
							file.substring(0, file.lastIndexOf("\\")+1) :
								file.substring(0, file.lastIndexOf("/")+1);
								

					// Read the option L
					if(options_given.contains("-l")) {
						for(String fileNames:g.option_l(file_dir, pattern))
									outputResult += fileNames +"\n";
					}
					
					// handling w option
					if(options_given.contains("-w") && !options_given.contains("-l")) {
							// Only option W
							for(Object line_with_pattern:g.option_w(file, pattern)) 
									outputResult += line_with_pattern + "\n";
					}
					else if (options_given.contains("-w") && options_given.contains("-l") ){
						String option_l_w = "";
						// combination of L and W
						for (String file_name : outputResult.split("\n")) {
							// Applying option W on the option L result
							option_l_w += "\n"+file_name + " :\n";
							for(Object line:g.option_w(file_dir+file_name, pattern)) {
								option_l_w += line +"\n";
							}
						}
						outputResult = option_l_w;							
					}
					
					//handling c option
					if(options_given.contains("-c") && !options_given.contains("-l") 
							&& !options_given.contains("-w")) {
						// Only option C
						outputResult = Integer.toString(g.option_c(file, pattern));
					}
					else if(options_given.contains("-c")){
						outputResult = Integer.toString(outputResult.split("\n").length);
					}
										
					//Handling q option
					if(!options_given.contains("-q")) {
						System.out.println(outputResult);
					}
				}
			}
		}
		scan.close();
	}
}