/**
 * Consumer.java
 * @author : Sai Sujith Kammari
 * @author : Keerthi Nagappa Pradhani
 * 
 * @version : $Id$
 * @revision: $log$
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class acts as consumer which consumes items from the storage system
 * This connects to storage system and consumes when enough units are available
 */

public class Consumer extends Thread {
	 
	//Declaring and initializing the variables required
	private int consumption_rate = 0;
	private int consumers = 0;
	private String host = null;
	private int port = 0;
	private int Consumer_id = 0;
	

	/**
	 * Creates a consumer object that communicates the server with the given port number and hostname
	 * 
	 * @param consumer_count	int 	Number of consumers
	 * @param consumption		int		Number of units it consumes every time
	 * @param hostName			String	server name of the storage system
	 * @param port_num			int		port number on which the communication happens
	 */
	public Consumer(int consumer_count, int consumption,String hostName, int port_num) {
		// Initializing the variables
		consumption_rate = consumption;
		consumers = consumer_count;
		host = hostName;
		this.port = port_num;
	}
	
	/**
	 * Each consumer that communicates on the new port 
	 * 
	 * @param hostName			String	hostname of the server in which the storage server is hosted
	 * @param port_num			int		port through which the communication continues
	 * @param id				int		Id of the consumer
	 * @param consumption_rate	int		units that are consumer each time
	 */
	public Consumer(String hostName, int port_num, int id, int consumption_rate) {
		// Initialing the variables
		host = hostName;
		this.port = port_num;
		this.Consumer_id = id;
		this.consumption_rate = consumption_rate;
	}
	

	/**
	 * Creating the consumers and starting them
	 */
	public void consume() {
		// Storage to hold the consumers
		Consumer[] all_consumers = new Consumer[consumers];

		// Creating the consumer threads
		for (int index = 0; index < consumers; index++) {
			all_consumers[index] = new Consumer(host, port, index, consumption_rate);
		}
		
		// Starting the consumer Threads
		for(Consumer consumer: all_consumers)
			consumer.start();
		
		// Joining the consumer Threads
		for(Consumer consumer: all_consumers) {
			try {
				consumer.join();
			} catch (InterruptedException e) {
				// Handling the interrupted Exception
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Consuming the units by the consumer threads from the storage system
	 */
	public void run() {
		while(true) {
			int new_port = 0;
			// Getting new port for communication
			try(Socket get_newPort = new Socket(InetAddress.getByName(host), port)) {
				// Communicating with Storage system to ask for new port
				DataOutputStream toStorage  = new DataOutputStream(get_newPort.getOutputStream());
				toStorage.writeBytes("Trying Connect");
				// Receiving the new port number from the server
				BufferedReader fromStorage = new BufferedReader(
						new InputStreamReader(get_newPort.getInputStream()));
				
				// New port received
				new_port = Integer.parseInt(fromStorage.readLine());
				
				// Acknowledging that required information has been received by client
				toStorage.writeBytes("ReceivedNewPort\n");
			} catch (IOException e1) {
				// Handling the Exceptions
				e1.printStackTrace();
			}
			
			// Communicating with the new port
				try(Socket consumer_connection = new Socket(InetAddress.getByName(host), new_port)) {
					// Creating stream to send data to Storage
					DataOutputStream ctoStorage = new DataOutputStream(consumer_connection.getOutputStream());
					
					// Creating stream to read data from Storage
					BufferedReader cfromStorage = new BufferedReader(
							new InputStreamReader(consumer_connection.getInputStream()));
					
					// Message to Storage
					String outMessage = "Consumer "+ consumption_rate+ " "+ this.Consumer_id + "\n" ;
	
						// Sending message to Storage
					ctoStorage.writeBytes(outMessage);
						
						// Reading the response from Storage 
						System.out.println("FROM SERVER: " + cfromStorage.readLine());
						ctoStorage.writeBytes("DONE\n");
					
				} catch (IOException e) {
					// Handling the exception
					e.printStackTrace();
				}
		}
	}

	
	/**
	 * Main function
	 * 
	 * @param args 			String[] command line arguments	
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Declaring and Initializing the arguments
		String host_name = null;
		int port = 0;
		int consumption_rate = 0;
		int consumer_count = 0;

		if (args.length == 0) {
			// Assigning the values from standard input since there are no command line arguments
			BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
			try{
				System.out.println("Please enter number of consumers:");
				consumer_count = Integer.parseInt(scan.readLine());
				System.out.println("Please consumption rate:");
				consumption_rate = Integer.parseInt(scan.readLine());
				System.out.println("Please enter the hostname:");
				host_name = scan.readLine();
				System.out.println("Please enter the port number:");
				port = Integer.parseInt(scan.readLine());
			}catch(Exception e) {
				// Handling wrong inputs
				System.out.println("Invalid values");
				System.out.println("Usage:"
						+ "Producer.java  [<producers count>] [<production_rate>] [<hostname>] [<port>]");
				System.exit(1);
			}

		}
		// command line arguments are provided
		else if (args.length == 4) {
			try {
				consumer_count = Integer.parseInt(args[0]);
				consumption_rate = Integer.parseInt(args[1]);
				host_name = args[2];
				port = Integer.parseInt(args[3]);
			}
			catch(Exception e) {
				// handling the wrong inputs 
				System.out.println("Invalid parameres passed");
				System.out.println("Usage:"
						+ "Producer.java  [<Consumers count>] [<consumption_rate>] [<hostname>] [<port>]");
				System.exit(1);
			}
			
		}
		else if(args.length > 4) {
			// Wrong parameters passed
			System.out.println("Usage:"
					+ "Producer.java  [<Consumers count>] [<consumption_rate>] [<hostname>] [<port>]");
		}
		
		// Start to consume
		new Consumer(consumer_count,consumption_rate, host_name,port).consume();
	}

}
