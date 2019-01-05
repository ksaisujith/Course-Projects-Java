/**
 * CheckingAccount.java
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * Version: $Id$
 * 
 * Revision: $log$   
 */
public class SavingsAccount extends AssetAccount {
	//Interest rate is per annum
	private double interest = 12.0;
	
	/**
	 * This constructor creates a new object, activates the account and assigns account holder name, 
     * balance to the account 
     * 
     * @param 	String		name		Name of the new account holder
     * @param	double		amount		Initial amount that is being deposited
     * @return	void		null
     */
	public SavingsAccount(String name,double Amount) {
		this.AccountHolderName = name;
		this.AccountBalance = Amount;
		this.isAccountActive = true;
	}

	/**
	 * This calculates the interest for the given amount of time in months and adds it to the Account 
	 * balance
	 * 
     * @param 	int		time		Time in months for which the interest has to be calculated
     * @return	void	null			
     */
	public void calculateInterest(int time) {
		// calculating the Interest rate for the time
		interestEarned = this.AccountBalance * ((interest/time)/100);
		System.out.println(this.AccountHolderName + " earned $" +interestEarned+""
				+ " in past " + time + " months with the interest rate of "+ interest+"\n");
		this.AccountBalance +=  interestEarned;
	}

}
