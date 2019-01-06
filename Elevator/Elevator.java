/**
 * Elevator.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class as a elevator for the given number of floors. It moves to each floor and the
 * people will board and alight the elevator accordingly
 */

public class Elevator extends Thread {
	// Declaring required variables
	static Integer currentFloor = 1;
	static int personId = 0;
	int NoOfPersonsEntered = 0;
	int Max_Floor = 0;
	int exit_Floor =0;
	boolean going_up = true;
	
	/**
	 * Constructor which initializes the elevator with the given floors
	 * 
	 * @param 	floor 		int 		Number of floors in the building
	 */
	public Elevator(int floor) {
		currentFloor = 1;
		NoOfPersonsEntered = 0;
		Max_Floor = floor;
	}
	
	/**
	 * Thread method which runs when the elevator starts running
	 * 
	 */
	public synchronized void run() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// Creating the Thread Pool Executor
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors()*2, 
				10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);

		while(true) {
			synchronized(Person.lock) {
				// Reading number of persons in the floor
		        System.out.print("Enter the number of persons entering: ");
		        try {
		        NoOfPersonsEntered = input.nextInt();
		        if (NoOfPersonsEntered <0 )
		        	throw new NegativeNumberException();
		        for(int person=1; person<=NoOfPersonsEntered; person++){
		        	// Reading from the user about the exit floor
		            System.out.print("Please enter the floor where person "+person+" wants to exit :");
		            	exit_Floor = input.nextInt();
		            	if(exit_Floor > this.Max_Floor)
		            		// Raising invalid floor error 
		            		throw new InvalidInputException();
		            	if(exit_Floor <= 0)
		            		// Raising invalid floor error 
		            		throw new NegativeNumberException();
		            	// Adding the threads to the executor
		                executorPool.execute(new Person(currentFloor, exit_Floor, ++personId));
		                System.out.println("Person "+ personId + " entering the lift" );
		            }
		        } catch(InvalidInputException e) {
		        	// Invalid floor number entered
		        	System.err.println("There are only "+ this.Max_Floor + " floors in the building\n"
		        			+"Please try again");		        	
		        	continue;
		        } catch(NegativeNumberException e) {
		        	input.nextLine();
		        	System.err.println("Negative numbers are not allowed. Please try again\n");
		        	continue;
		        }
		        catch(Exception e) {	        
		        	// Invalid input given
		        	input.nextLine();
		        	System.err.println("Wrong input read. Please try again\n");
		        	e.printStackTrace();
		        	continue;
		        }
		        System.out.println("\n******** Doors closing *********");
		        
		        // Moving to next floor
		        if (going_up)
		        	currentFloor++;
		        else
			        currentFloor--;
		        
		        System.out.println("### Elevator reached "+ currentFloor + " floor ###");
		        System.out.println("******** Doors opening *********\n");

		        // Changing the direction of the elevator
		        if(currentFloor == Max_Floor)
		        	going_up = false;
		        if(currentFloor == 1)
		        	going_up = true;
		        
		        Person.lock.notifyAll();		        		        
		        do {
		        	// Waiting till all the persons are checked 
		        	try {
		        		Person.lock.wait(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        }while(executorPool.getTaskCount()  != personId );
			}
		}
	}
	
	@SuppressWarnings("resource")
	/**
	 * Main method
	 * 
	 * @param args 		String		command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Please enter number of floors:");
		Scanner scan = new Scanner(System.in);
		int floors = 0;
		try{
			// Reading number of floors in the building
			floors = Integer.parseInt(scan.nextLine()); 
			if(floors <= 2) {
				throw new InvalidInputException();
			}
		}catch(NumberFormatException e) {
			// Wrong input given
			System.out.print("Number of floors should be an integer value");
			System.exit(1);
		}
		catch(InvalidInputException e) {
			// can't build a elevator less than 2
			System.out.print("Floors should be minimum of two to construct an elevator");
			System.exit(1);
		}
		catch(Exception e) {
			// Handling all other exceptions
			System.out.print("Unknown error");
			System.exit(1);
		}
		
		System.out.println("Elevator has started in Floor "+ currentFloor);
		// Starting the elevator
		new Elevator(floors).start();
	}
}
