/**
 * BankAccount.java
 * @author Sai Sujith K
 * Version: $Id$
 * 
 * Revision: $log$   
 */


/** 
 * This interfaces has all the methods that all the bank account should have 
 */

public interface BankAccount extends AccountMethods {
	public boolean debit(double amount);
	public boolean credit(double amount);	
	public boolean closeAccount();
}
