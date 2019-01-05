/**
 * AssetAccount.java
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * Version: $Id$
 * 
 * Revision: $log$   
 */


import java.util.Random;
/**
 * This class provides the implementation of the abstract methods from the BankAccount
 * This class is an abstract because there shouldn't be any object of this class since no AssetAccount is a BankAccount
 * 
 * @author sujith
 *
 */
public abstract class AssetAccount implements BankAccount{
	
	int accountNumber;
	boolean isAccountActive;
	double AccountBalance;
	String AccountHolderName;
	double interestEarned;
	
	/**
	 * This constructor creates an account number and assigns it to the created bank account
     * 
     * @param 	null	
     * @return	void		null
     */
	public AssetAccount() {
		this.accountNumber = new Random().nextInt(1000);
	}

	/**
	 * Debits the amount from the account balance
	 * 
     * @param 	double	amount 		Amount that has to be debited
     * @return	boolean	true		Transaction status			
     */
	public boolean debit(double amount)
	{
		this.AccountBalance -= amount;
		return true;
	}
	
	/**
	 * Credits the amount from the account balance
	 * 
     * @param 	double	amount 		Amount that has to be debited
     * @return	boolean	true		Transaction status			
     */
	public boolean credit(double amount)
	{
		this.AccountBalance += amount;
		return true;
	}
	
	/**
	 * Closes the account
	 * 
     * @param 	NULL
     * @return	boolean	true		Transaction status			
     */
	public boolean closeAccount() {
		this.isAccountActive = false;
		return true;
	}

	// Account Details
	/**
	 * Fetches the account number of the bank account and returns the same
	 * 
     * @param 	NULL
     * @return	int		accountNumber		Transaction status			
     */
	public int getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * Fetches the account holder name of the bank account and returns the same
	 * 
     * @param 	NULL
     * @return	String AccountHolderName name of the account holder			
     */
	public String getAccountHolderName() {
		return this.AccountHolderName;
	}
	
	/**
	 * Fetches the account balance of the bank account and returns the same
	 * 
     * @param 	NULL
     * @return	double		accountBalance		Balance in the account			
     */
	public double getBalance() {
		return this.AccountBalance;
	}
	
	/**
	 * Fetches the account Status of the bank account and returns the same
	 * 
     * @param 	NULL
     * @return	String		Active/Inactive		If isAccountActive is true then Active else Inactive			
     */
	public String getAccountStatus() {
		return this.isAccountActive?"Active":"Inactive";
	}
	
}
