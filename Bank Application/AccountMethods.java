/**
 * AccountMethods.java
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * Version: $Id$
 * 
 * Revision: $log$   
 */

/**
 * This interfaces has all the methods that provides information about the bank account 
 */

public interface AccountMethods {
	public abstract int getAccountNumber();
	public abstract String getAccountHolderName();
	public abstract double getBalance();
	public abstract String getAccountStatus();
	public abstract void calculateInterest(int time);
}
