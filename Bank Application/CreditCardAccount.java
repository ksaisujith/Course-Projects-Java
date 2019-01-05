/**
 * CreditCardAccount.java
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 * Version: $Id$
 * 
 * Revision: $log$   
 */


public class CreditCardAccount extends LiabilityAccount{
	//Interest rate per annum
	private int interest = 22;
	
	/**
	 * This constructor creates a new object, activates the account and assigns account holder name, 
     * balance to the account 
     * 
     * @param 	String		name		Name of the new account holder
     * @param	double		amount		Initial amount that is being deposited
     */
	public CreditCardAccount(String name, double amount) {
		this.AccountHolderName = name;
		this.AccountBalance = amount;
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
		// TODO Auto-generated method stub
		 interestAdded = this.AccountBalance*((interest/time)/100);
		System.out.println(this.AccountHolderName + " has been charged $" +interestAdded+" "
				+ "in past " + time + " months with the interest rate of "+ interest+"\n");
		this.AccountBalance +=  interestAdded;
	}
	
}
