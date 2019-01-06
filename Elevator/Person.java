/**
 * Person.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

/**
 * This class creates person and he waits till elevator reaches his floor
 */
public class Person implements  Runnable {
	// Declaring the variables
	static Object lock = new Object();
	int src_floor = 0;
	int dst_floor = 0;
	int personId = 0;

	/**
	 * Constructor that creates the person
	 * 
	 * @param currentFloor	int 	floor in which the person has entered the elevator
	 * @param destFloor		int		floor in which the person has to alight
	 * @param id			int		Id of the person
	 */
	public Person(int currentFloor, int destFloor, int id ) {
		this.src_floor = currentFloor;
		this.dst_floor = destFloor;
		this.personId = id;
	}
	
	/**
	 * THis method makes the person wait till the elevator reaches the destination floor
	 */
	@Override
	public void run() {
		synchronized(lock){
			while(Elevator.currentFloor != dst_floor) {
				try {
					// elevator not reached yet
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			// elevator reached 
			System.out.println("Person " + personId + " entered elevator at "+ this.src_floor +
					" floor exiting ");
		}	
	}
}
