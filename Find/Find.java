/**
 * Find.java
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Pradhani
 *
 * @version : $Id$
 * @revision: $log$
 */

import java.io.File;
import java.util.Date;

/**
 * This class takes the command from the command line in the below format and process the request 
 * 
 * find  starting_directory [-name name|-type (f|d)]|-date|-length]
 */


public class Find {

	/**
	 * This function takes the file and returns the last modified date of the file
	 * 
	 * @param 	fileName		File		file name of which the date has to be found
	 * @return	constant		String		last modified date of the file
	 */
	public String option_date(File fileName) {
		return new Date(fileName.lastModified()).toString();
	}
	
	/**
	 * This function takes the file and returns the size of the file
	 * 
	 * @param 	fileName	File	file name of which the size has to be calculated
	 * @return	constant	String	size of the file in bytes
	 */
	public String option_len(File fileName) {
		return String.valueOf(fileName.length());
	}
	
	

	/**
	 * This function takes the parameters from the main and prints the searched files
	 * 
	 * @param	s_dir					String		The starting Directory
	 * @param 	search_name				String		string that has to be searched
	 * @param	isNameParamPassed		boolean		-name present in the command
	 * @param 	isTypeParamPassed		boolean		-type present in the command
	 * @param 	isFileSearch			boolean		true if the file has to be searched false if directories
	 * @param 	printSize				boolean		-length present in the command
	 * @param 	printLastModifiedDate	boolean		-Date present in the command
	 */
	public void File_search(String s_dir, String search_name, 	
		boolean isNameParamPassed,boolean isTypeParamPassed, boolean isFileSearch
		,boolean printSize, boolean printLastModifiedDate) {
		
		// Declaring and Initializing the variables
		File dir = new File(s_dir);		
		String[] all_files = dir.list();
		String output_list = "";
		
		try {
			// Reading all the files in the directory
			for(String file : all_files) {
				if(new File(s_dir+"/"+file).isDirectory()) {
					// print if the file is a directory and the flag is print directory
					if(! isFileSearch)
						output_list += s_dir+"/"+file;
					// move forward into the directory and proceed the same till it reaches to the last directory
					File_search(s_dir+"/"+file,search_name,
							isNameParamPassed, isTypeParamPassed,isFileSearch, printSize, printLastModifiedDate);
				}
				else {
					if(file.contains(search_name)) {
						// if the file is not a directory and matches the search pattern, print the same
						if(isFileSearch)
							output_list = "" + s_dir+"/"+file ;
						if(printSize)
							// If the print size flag is enabled, print the size to the file
							output_list += "\t size:" + this.option_len(new File(s_dir+"/"+file)) + " bytes";
						if(printLastModifiedDate)
							// If the last modified date flag is enabled, print the size to the file
							output_list += "\t date:" + this.option_date(new File(s_dir+"/"+file));
					}
				}
				
				if(! output_list.equals("")) {
					// Prints the output
					System.out.println(output_list);
					output_list = "";
				}					
			}
		}catch(Exception e) {}
	}

	/**
	 * Main function
	 * 
	 * @param args	String[]	command line arguments
	 */	
	public static void main(String[] args) {
		if(args.length == 0) {
			// No parameters passed
			System.out.println("Please pass the command in the below format \n "
					+ "find  starting_directory [-name name|-type (f|d)]|-date|-length]");
			System.exit(1);
		}
		else {
			
			// All parameters are passed
			Find find = new Find();
			File dir = new File(args[0]);
			if(! dir.isDirectory())
			{
				System.out.println("Wrong directory passed Please pass the command in the below format \n" + 
						"find  starting_directory [-name name|-type (f|d)]|-date|-length]");
				System.exit(1);
			}
			
			// Initializing the variables
			int name_param_index = -1;
			int name_value_index = -1;
			int type_param_index = -1;
			int date_param_index = -1;
			int len_param_index = -1;
			boolean isFileSearch = true;
			
			for(int index =0; index < args.length; index++) {
				
				if(args[index].equals("-name") && name_param_index == -1) {
					// Command contains -name 
					if(index == args.length - 1) {
						// -name passed but the value is not passed
						System.out.println("Please pass the name value");
						System.exit(1);
					}
					// reading the values of the name
					name_param_index = index;
					name_value_index = index+1;
				}
				
				if(args[index].equals("-type") && type_param_index == -1) {
					// Command contains -type
					type_param_index = index;
					if(index == args.length - 1) {
						// -type passed but the value is not passed
						System.out.println("Please pass the parameter of type");
						System.exit(1);
					}
					
					if(!(args[index + 1].equals("f") || args[index + 1].equals("d")))
					{
						// wrong parameter passed
						System.out.println("only f or d allowed in type value");
						System.exit(1);
					}
					isFileSearch = args[index + 1].equals("f")? isFileSearch : false; 
				}
				
				// -date parameter is passed in the command
				if(args[index].equals("-date") && date_param_index == -1) {
					date_param_index = index;
				}
				
				// -length is passed in the command
				if(args[index].equals("-length") && len_param_index == -1) {
					len_param_index = index;
				}
				
			}
			
			// Processing the command
			find.File_search(args[0], 
					name_value_index > 0 ? args[name_value_index] : "", 
					name_param_index > 0,type_param_index > 0, 
					isFileSearch, len_param_index > 0, date_param_index > 0);
		}
	}
}
