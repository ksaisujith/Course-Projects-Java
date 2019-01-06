/**
 * Consumer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Consumer client that connects to the server and consumes items in the storage
 */
public class Consumer extends Thread{
	private static int consume_items= 0;
	private int consumer_id;
	
	/**
	 * 
	 * Constructor which assigns the producer id to the thread
	 *
	 * @param id 	int		Id of the thread
	 */
	public Consumer(int id) {
		this.consumer_id = id;
	}
	
	/**
	 * Shows the usage of the class
	 */
	private static void usage() {
		System.out.println("Usage: Consumer <num_of_consumers> <number_of_items>");
	}
	
	@Override
	/**
	 * Consuming the items
	 */
	public void run() {
		while(true) {
			try {
				// Getting the registry from the server
				Registry register = LocateRegistry.getRegistry("glados.cs.rit.edu",2053);
				
				// Getting the remote object from the registry
				IProducerConsumer consumer = (IProducerConsumer) register.lookup("sk7634_kp5955_prodCons");
				
				// Consuming the items
				System.out.println(consumer.consume(consume_items) + " by " + consumer_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * main function
	 * 
	 * @param args 		String[] 		Command line arguments
	 */
	public static void main(String args[]) {
		// Initialize the variables
		int consumers = 0;
		if(args.length != 2) {
			// wrong parameters passed
			System.out.println("Wrong inputs provided \n");
			usage();
		}
		else {
			try {
				// Reading the values that are passed
				consumers = Integer.parseInt(args[0]);
				consume_items = Integer.parseInt(args[1]);
			} catch(Exception e) {
				// wrong parameters passed
				System.out.println("Only integer values are allowed.  \n");
				usage();
			}
		}
		
		// Creating the Consumer threads and running
		for( int consumer = 0; consumer < consumers; consumer++ )
			new Consumer(consumer).start();		
	}
}