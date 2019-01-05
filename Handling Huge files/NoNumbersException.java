/**
 * NoNumbersException.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

/**
 * This class handles the exception when the file doesn't contain numbers in it
 */
public class NoNumbersException extends Exception {

	private static final long serialVersionUID = -2895689647449417744L;
	
	public NoNumbersException() {
		
	}
	public NoNumbersException(String message) {
		super (message);
	}
}
