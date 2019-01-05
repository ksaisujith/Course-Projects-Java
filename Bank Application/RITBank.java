/**
 * RITBank.java
 * Version: $Id$
 * 
 * Revision: $log$   
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program implements inheritance, abstract class and interfaces
 * to provide all the financial functionalities
 * 
 * @author Sai Sujith Kammari
 * @author Keerthi Nagappa Pradhani
 */
public class RITBank {
	@SuppressWarnings("finally")
	public static void main(String[] args) {
		/**
		 * The main program.
		 * @param    args    command line arguments (ignored)
		 */
		
		//Declaring and ini3tializing the required variables
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int time =0;
		boolean success = false;
		String accountHolderName = null;
		int userInput = 0 ;
		double amount;
		
		System.out.println("Welcome to RIT bank!!\n"
				+ "How can we help you today.");
		do {
			//Display menu 
			System.out.println("Please select one option below\n"
					+ "1. Open a new account\n"
					+ "2. Close an existing account\n"
					+ "3. Credit money\n"
					+ "4. Debit money\n"
					+ "5. Time \n"
					+ "6. Summary of all the account in the bank\n"
					+ "7. Exit");
			try{
				userInput = scan.nextInt();
				switch(userInput) {
				case 1:
					//Opening an account
					//Display sub-menu
					System.out.println("What type of account\n"
							+ "1 - Savings Account\n"
							+ "2 - Checking Account\n"
							+ "3 - Credit Card Account\n"
							+ "4 - Loan Account");
					userInput = scan.nextInt();
					scan.nextLine();
					System.out.print("Please enter the account holder Name:");
					accountHolderName = scan.nextLine();
					System.out.print("\nPlease enter the Amount to be deposited");
					amount = scan.nextDouble();
					//Opening the specific Account Type
					switch(userInput) {
					//Opening the account based on the input received
					case 1:			
						bankAccounts.add(new SavingsAccount(accountHolderName,amount));
						break;
					case 2:
						bankAccounts.add(new CheckingAccount(accountHolderName,amount));
						break;
					case 3:
						bankAccounts.add(new CreditCardAccount(accountHolderName,amount));
						break;
					case 4:
						bankAccounts.add(new LoanAccount(accountHolderName,amount));
						break;
					default:
						System.out.println("Wrong Input. Please start over!!");
					}
					System.out.println("Account created successfully with account number: "+
					bankAccounts.get(bankAccounts.size()-1).getAccountNumber()+"\n");
					break;
				case 2:
					//Closing the account by taking the account number to be closed
					System.out.println("Please enter the account number to close");
					userInput = scan.nextInt();
					for(BankAccount b:bankAccounts) {
						if(b.getAccountNumber() == userInput) {
							success = b.closeAccount();
							System.out.println("Account Deactivation is successful");
							break;
						}
					}
					if(success!= true)
						System.out.println("Unable to find the account number");
					//clearing the read buffer
					scan.nextLine();
					break;
				case 3:
					//Crediting the account with the amount provided
					System.out.println("Enter the account number");
					userInput = scan.nextInt();
					System.out.println("Enter the amount you want to credit");
					amount = scan.nextDouble();
					for(BankAccount b:bankAccounts) {
						if(b.getAccountNumber() == userInput) {
							success = b.credit(amount);
							break;
						}
					}
					if(success != true)
						System.out.println("Account number is not found (or) there is a problem");
					break;
				case 4:
					//Debiting the  account with the amount provided
					System.out.println("Enter the account number");
					userInput = scan.nextInt();
					System.out.println("Enter the amount you want to credit");
					amount = scan.nextDouble();
					for(BankAccount b:bankAccounts) {
						if(b.getAccountNumber() == userInput) {
							success = b.debit(amount);
							break;
						}
					}
					if(success != true)
						System.out.println("Account number is not found (or) there is a problem");
					break;
				case 5:
					//Calculating the interest for all the accounts in the time mentioned
					System.out.println("Please enter the time in months");
					time = scan.nextInt();
					for(int i =0;i<20;i++)
						System.out.print("=");
					System.out.println("");
					for(BankAccount b:bankAccounts) {
						if(b.getAccountStatus().length() == 6)
						{
							//Only for all the accounts which are active
							b.calculateInterest(time);
							System.out.println("New account balance after interest calculation"
									+ ": " + b.getBalance()+"\n\n");
						}
					}
					for(int i =0;i<20;i++)
						System.out.print("=");
					System.out.println("");
					break;
				case 6:
					//Summary of all the accounts in the bank
					for(int i =0;i<40;i++)
						System.out.print("*");
					for(BankAccount b:bankAccounts) {
						System.out.println("\nBank Account Number:" + b.getAccountNumber() 
						+ "\nAccount type:" + b.getClass().getName()
								+  "\nAccount User name:" + b.getAccountHolderName() 
						+"\nBalance in the account:" + b.getBalance() 
						+"\nAccount Status:"+ b.getAccountStatus()
						+"\n");
					}
					//Clearing the read buffer
					scan.hasNextLine();
					for(int i =0;i<40;i++)
						System.out.print("*");
					System.out.println("*");
					break;
				case 7:
					//Exit the application
					System.exit(0);
				default:
					//Wrong input has been provided
					System.out.println("Wrong Input provided. Please try again");
					break;
				}
			}
			catch(Exception e){
				//Handling the wrong input
				System.out.print("Incorrect input! Please provide the index number\n\n");
				scan.nextLine();
			}
			finally {
				continue;
			}
		}while(true);
	}
}
