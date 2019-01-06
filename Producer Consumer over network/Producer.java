/**
 * Producer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class acts as producer which produces items in the storage system
 * This connects to storage system and produces when enough units are available
 */

public class Producer extends Thread{
	
	//Declaring and initializing the variables required
	private int production_rate = 0;
	private int producers = 0;
	private String host = null;
	private int port = 0;
	private int producer_id = 0;
	
	/**
	 * Creates a producer object that communicates the server with the given port number and hostname
	 * 
	 * @param producerCount		int 	Number of consumers
	 * @param production		int		Number of units it produces every time
	 * @param hostName			String	server name of the storage system
	 * @param port_num			int		port number on which the communication happens
	 */
	public Producer(int producerCount, int production,String hostName, int port_num) {
		// Initializing the variables
		production_rate = production;
		producers = producerCount;
		host = hostName;
		this.port = port_num;
	}
	
	/**
	 * creates each producer that communicates on the new port 
	 * 
	 * @param hostName			String	hostname of the server in which the storage server is hosted
	 * @param port_num			int		port through which the communication continues
	 * @param id				int		Id of the producer
	 * @param productionRate	int		units that are produced each time
	 */
	public Producer(String hostName, int port_num, int id,int productionRate) {
		// Initializing the variables
		this.host = hostName;
		this.port = port_num;
		this.producer_id = id;
		this.production_rate = productionRate;
	}
	
	/**
	 *  Creating the producers and starting them
	 */
	public void produce() {
		// Storage to hold the producers
		Producer[] all_producers = new Producer[producers];
		 
		// Creating producers
		for(int index = 0; index < producers; index++) {
			all_producers[index] = new Producer(host, port, index, production_rate);
		}
		
		// Starting the producers
		for(Producer producer : all_producers)
			producer.start();
		
		// Waiting to complete all the producers
		for (Producer producer: all_producers) {
			try {
				producer.join();
			} catch (InterruptedException e) {
				// Handling the Interrupted Exception
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Producing the units by the consumer threads from the storage system
	 */
	public void run() {
		while(true) {
			int newport = 0;
			// Getting new port for communication
			try(Socket get_newPort = new Socket(InetAddress.getByName(host), port)) {
				
				// Communicating with Storage system to ask for new port
				DataOutputStream toStorage  = new DataOutputStream(get_newPort.getOutputStream());
				toStorage.writeBytes("Trying Connect");

				// Receiving the new port number from the server
				BufferedReader fromStorage = new BufferedReader(
						new InputStreamReader(get_newPort.getInputStream()));
				
				// New port received
				newport = Integer.parseInt(fromStorage.readLine());

				// Acknowledging that required information has been received by client
				toStorage.writeBytes("ReceivedNewPort\n");
			} catch (IOException e1) {
				// Handling the exception
				e1.printStackTrace();
			}

			// Communicating with the new port
			try(Socket producer_connection = new Socket(InetAddress.getByName(host), newport)) {
				// Creating stream to send data to Storage
				DataOutputStream ptoStorage = new DataOutputStream(producer_connection.getOutputStream());
				
				// Creating stream to read data from Storage
				BufferedReader pfromStorage = new BufferedReader(
						new InputStreamReader(producer_connection.getInputStream()));
				
				// Message to Storage
				String outMessage = "Producer "+ production_rate + " "+ this.producer_id+ "\n" ;
	
					// Sending message to Storage
					ptoStorage.writeBytes(outMessage);
					// Reading the response from Storage
					System.out.println("FROM SERVER: " + pfromStorage.readLine());
					
					// Acknowledging the server
					ptoStorage.writeBytes("DONE\n");
				}catch (IOException e) {
				// handling the exception
				e.printStackTrace();
			}
	}
}

	/**
	 * Main function
	 * 
	 * @param args 	String[] 	Command line arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Declaring and Initializing the arguments
		String host_name = null;
		int port = 0;
		int production_rate = 0;
		int producers_count = 0;
		
		if (args.length == 0) {
			// Assigning the values from standard input since there are no command line arguments
			BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
			try{
				System.out.println("Please enter number of producers:");
				producers_count = Integer.parseInt(scan.readLine());
				System.out.println("Please production rate:");
				production_rate = Integer.parseInt(scan.readLine());
				System.out.println("Please enter the hostname:");
				host_name = scan.readLine();
				System.out.println("Please enter the port number:");
				port = Integer.parseInt(scan.readLine());
			} catch(Exception e) {
				// Handling wrong inputs
				System.out.println("Invalid values");
				System.out.println("Usage:"
						+ "Producer.java  [<producers count>] [<production_rate>] [<hostname>] [<port>]");
				System.exit(1);
			}

		}
		
		// command line arguments are  provided
		else if (args.length == 4) {
			try {
				producers_count = Integer.parseInt(args[0]);
				production_rate = Integer.parseInt(args[1]);
				host_name = args[2];
				port = Integer.parseInt(args[3]);
			}catch(Exception e) {
				// Handling wrong inputs
				System.out.println("Invalid parameres passed");
				System.out.println("Usage:"
						+ "Producer.java  [<producers count>] [<production_rate>] [<hostname>] [<port>]");
				System.exit(1);
			}
		}
		 
		// invalid number of parameters passed
		else if(args.length > 4) {
			System.out.println("Usage:"
					+ "Producer.java  [<producers count>] [<production_rate>] [<hostname>] [<port>]");
		}
		
		// Starting the producers
		new Producer(producers_count,production_rate, host_name,port).produce();;
	}
}
