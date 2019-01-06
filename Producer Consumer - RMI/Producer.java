/**
 * Producer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Producer client that connects to the server and produces the items in the storage
 */
public class Producer extends Thread{
	private static int produced_items= 0;
	private int producer_id;
	
	/**
	 * Constructor which assigns the producer id to the thread
	 * 
	 * @param id 	int		ID of the thread
	 */
	public Producer(int id) {
		this.producer_id = id;
	}
	
	/**
	 * Shows the usage of the class
	 */
	private static void usage() {
		System.out.println("Usage: Producer <num_of_producers> <number_of_items>");
	}
	
	@Override
	/**
	 * Producing the items
	 */
	public void run() {
		while(true) {
			try {
				
				// Getting the registry from the server
				Registry register = LocateRegistry.getRegistry("glados.cs.rit.edu",2053);
				
				// Getting the remote object from the registry
				IProducerConsumer producer = (IProducerConsumer) register.lookup("sk7634_kp5955_prodCons");
				
				// Producing the items
				System.out.println(producer.produce(produced_items) + " by "+ producer_id);
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
		int producers = 0;
		if(args.length != 2) {
			// wrong parameters passed
			System.out.println("Wrong inputs provided \n");
			usage();
		}
		
		else {
			try {
				// Reading the values that are passed
				producers = Integer.parseInt(args[0]);
				produced_items = Integer.parseInt(args[1]);
			} catch(Exception e) {
				// wrong params passed
				System.out.println("Only integer values are allowed.  \n");
				usage();
			}
		}
		
		// Creating the producer threads and running
		for( int producer = 0; producer < producers; producer++ )
			new Producer(producer).start();		
	}
}