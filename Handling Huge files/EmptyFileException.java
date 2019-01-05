/**
 * EmptyFileException.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

/**
 * This class handles the exception when the file is empty
 */
public class EmptyFileException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4556036916673656646L;

	public EmptyFileException() {
		
	}
	
	public EmptyFileException(String message) {
		super (message);
	}

}
